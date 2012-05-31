/*
refs:
http://www.adp-gmbh.ch/ora/plsql/coll/return_table.html
http://docs.oracle.com/cd/E11882_01/server.112/e25513/statviews_1046.htm#REFRN20047

em tendo como criar tipos de dados dinamicamente, posso criar tipos
específicos somente para retornar dados de uma tabela. Mas o problema
disso é que os novos tipos só estão disponíveis no meio da execução da
função, então não podem ser o tipo de retorno dela.

no fim, esse é o tipo de função extremamente específico, que deve ser 
sobrecarregada para cada tipo de ROWTYPE de cada tabela... e o jeito mais
fácil de implementar é permitir que se retorne somente o ROWTYPE, e e não
um subcojunto de colunas.

uma solução seria usar REF CURSOR, que o Java entende como ResultSet. :)
*/

CREATE OR REPLACE PACKAGE maneja_tabela AS
	TYPE data_array_t IS VARRAY(60) OF VARCHAR2(1000);
  TYPE col_cons_types_t IS TABLE OF VARCHAR2(1000) INDEX BY VARCHAR2(1000);
	TYPE cols_names_t IS VARRAY(60) OF user_tab_cols.column_name%TYPE;
	TYPE cols_types_t IS VARRAY(60) OF user_tab_cols.data_type%TYPE;
  TYPE cons_cols_t  IS VARRAY(60) OF user_cons_columns.column_name%TYPE;
	TYPE tab_cols_info_t IS RECORD (cols_names cols_names_t, cols_types cols_types_t);

	-- function pra fazer busca de um elemento num varray
	-- pega um tipo de dentro do varray e o varray, retorna -1 se nao
	-- encontrou ou o indice, se encontrou
	FUNCTION search_varray(o VARCHAR2, v data_array_t) RETURN NUMBER;
	FUNCTION search_varray(o user_tab_cols.column_name%TYPE, v cols_names_t) RETURN NUMBER;
	FUNCTION search_varray(o user_tab_cols.data_type%TYPE, v cols_types_t) RETURN NUMBER;
	FUNCTION search_varray(o user_cons_columns.column_name%TYPE, v cons_cols_t) RETURN NUMBER;

	PROCEDURE inserir(
		nome_tabela user_objects.object_name%TYPE,
		attr		data_array_t,
		val			data_array_t
	);

	PROCEDURE remover(
		nome_tabela user_objects.object_name%TYPE,
		pk      data_array_t,
		pkval		data_array_t
	);


  /*

  recebe o nome da tabela
  recebe variavel OUT para contagem de colunas da tabela
  recebe variavel OUT para guardar os tipos de constraints de cada coluna
  recebe variavel OUT para guardar os nomes das colunas
  TODO:
  recebe limite maximo de tuplas a pegar (opcional, com padrao em 60)
  recebe os atributos específicos a listar (opcional)
  recebe a(s) pk(s) para listar uma unica tupla (opcional)

  devolve um cursor para o conjunto de tuplas selecionadas

  ainda nao tem a capacidade de retornar a checagem da constraint, mas pelo
  menos pode-se checar se e ou nao PK

  codigo pl/sql de teste:

  set serveroutput on;
  declare
    attr maneja_tabela.data_array_t;
    attrcons maneja_tabela.col_cons_types_t;
    nattr NUMBER;
    cur sys_refcursor := maneja_tabela.listar('l01_usuario', nattr, attrcons, attr);
    v_tab l01_usuario%ROWTYPE;
  begin
    dbms_output.put_line('No de atributos: ' || nattr);
    for i in attr.first .. attr.last loop
      dbms_output.put_line(attr(i) || ': ' || attrcons(attr(i)));
    end loop;
    
    loop
      fetch cur into v_tab;
      exit when cur%notfound;
      dbms_output.put_line('cpf: ' || v_tab.cpf_usr || ', rg: ' || v_tab.rg_usr || ', nasc: ' || v_tab.datanascimento || ', nick: ' || v_tab.nomeusuario || ', senha: ' || v_tab.senha || ', nome: ' || v_tab.nomecompleto || ', tipo: ' || v_tab.tipo_usuario);
    end loop;
    close cur;
  end;
*/

	FUNCTION listar(
		nome_tabela user_objects.object_name%TYPE,
    c_cols OUT NUMBER,
    n_cols_cons OUT col_cons_types_t,
    n_cols OUT data_array_t
    -- max     NUMBER DEFAULT 60, -- default 60 => exibir 60 resultados por vez
		-- attr		data_array_t DEFAULT data_array_t(), -- default vazio => listar tudo
		-- pk			data_array_t DEFAULT data_array_t(),
		-- pkval		data_array_t DEFAULT data_array_t(),
	) RETURN SYS_REFCURSOR;

	PROCEDURE atualizar(
		nome_tabela user_objects.object_name%TYPE,
		attr		data_array_t,
		val 		data_array_t,
		pk  		data_array_t,
		pkval  	data_array_t
	);

END maneja_tabela;


CREATE OR REPLACE PACKAGE BODY maneja_tabela AS

	FUNCTION search_varray(o VARCHAR2, v data_array_t) RETURN NUMBER AS
	BEGIN
		FOR i IN v.FIRST .. v.LAST LOOP
			IF v(i) = o THEN
				RETURN i;
			END IF;
		END LOOP;
		RETURN -1;
	END search_varray;

	FUNCTION search_varray(o user_tab_cols.column_name%TYPE, v cols_names_t) RETURN NUMBER AS
	BEGIN
		FOR i IN v.FIRST .. v.LAST LOOP
			IF v(i) = o THEN
				RETURN i;
			END IF;
		END LOOP;
		RETURN -1;
	END search_varray;

	FUNCTION search_varray(o user_tab_cols.data_type%TYPE, v cols_types_t) RETURN NUMBER AS
	BEGIN
		FOR i IN v.FIRST .. v.LAST LOOP
			IF v(i) = o THEN
				RETURN i;
			END IF;
		END LOOP;
		RETURN -1;
	END search_varray;

	FUNCTION search_varray(o user_cons_columns.column_name%TYPE, v cons_cols_t) RETURN NUMBER AS
	BEGIN
		FOR i IN v.FIRST .. v.LAST LOOP
			IF v(i) = o THEN
				RETURN i;
			END IF;
		END LOOP;
		RETURN -1;
	END search_varray;

  -- insercao
	PROCEDURE inserir(
		nome_tabela user_objects.object_name%TYPE,
		attr		data_array_t,
		val			data_array_t
	) AS
		n_tab user_objects.object_name%TYPE := UPPER(nome_tabela);
		CURSOR c_tab IS SELECT column_name, data_type FROM user_tab_cols WHERE table_name = n_tab;
		info tab_cols_info_t; -- armazena as info. das colunas, obtidas pelo cursor
		tab_num NUMBER := 0; -- verifica se existe pelo menos uma tabela com o nome passado
		cmd1 VARCHAR2(2000); -- lista de binds para nomes de colunas
		cmd2 VARCHAR2(2000); -- lista de binds para valores
		cmd VARCHAR2(32000); -- comando final
		idx NUMBER := 0;
		c NUMBER;
		res NUMBER;
	BEGIN
		SELECT COUNT(*) INTO tab_num FROM user_objects WHERE object_type = 'TABLE' AND object_name = n_tab;
		IF tab_num > 0 AND attr.COUNT > 0 AND attr.COUNT = val.COUNT THEN

			OPEN c_tab;
			FETCH c_tab BULK COLLECT INTO info.cols_names, info.cols_types;
			CLOSE c_tab;

			cmd1 := '(';
			cmd2 := '(';

			FOR i IN attr.FIRST .. attr.LAST LOOP
				idx := search_varray(UPPER(attr(i)), info.cols_names);
				IF idx > 0 THEN
					IF i = attr.FIRST THEN
						cmd1 := cmd1 || info.cols_names(idx);
						cmd2 := cmd2 || ':y' || i;
					ELSIF i = attr.LAST THEN
						cmd1 := cmd1 || ', ' || info.cols_names(idx) || ')';
						cmd2 := cmd2 || ', :y' || i || ')';
					ELSE
						cmd1 := cmd1 || ', ' || info.cols_names(idx);
						cmd2 := cmd2 || ', :y' || i;
					END IF;
				END IF;
			END LOOP;
			cmd := 'INSERT INTO ' || n_tab || ' ' || cmd1 || ' VALUES ' || cmd2;

			c := DBMS_SQL.OPEN_CURSOR;
			DBMS_SQL.PARSE(c, cmd, DBMS_SQL.NATIVE);

			FOR i IN attr.FIRST .. attr.LAST LOOP
				idx := search_varray(UPPER(attr(i)), info.cols_names);
				IF idx > 0 THEN
					CASE
					WHEN info.cols_types(idx) = 'VARCHAR2' THEN DBMS_SQL.BIND_VARIABLE(c, ':y' || i, val(i));
					WHEN info.cols_types(idx) = 'CHAR' THEN DBMS_SQL.BIND_VARIABLE(c, ':y' || i, val(i));
					WHEN info.cols_types(idx) = 'NUMBER' THEN DBMS_SQL.BIND_VARIABLE(c, ':y' || i, TO_NUMBER(val(i)));
					WHEN info.cols_types(idx) = 'DATE' THEN DBMS_SQL.BIND_VARIABLE(c, ':y' || i, TO_DATE(val(i), 'YYYY-MM-DD'));
					END CASE;
				END IF;
			END LOOP;

			res := DBMS_SQL.EXECUTE(c);
		END IF;
	END inserir;

  -- remocao
	PROCEDURE remover(
		nome_tabela user_objects.object_name%TYPE,
		pk      data_array_t,
		pkval		data_array_t
	) AS
		n_tab user_objects.object_name%TYPE := UPPER(nome_tabela);
		cont_tab NUMBER := 0;
		idx1 NUMBER := 0;
		idx2 NUMBER := 0;
    c NUMBER;
    res NUMBER;
		cmd VARCHAR2(32000);
		CURSOR c_tab IS SELECT column_name, data_type FROM user_tab_cols WHERE table_name = n_tab;
    CURSOR c_cons_cols IS SELECT c1.column_name FROM user_cons_columns c1, user_constraints c2
            WHERE c1.table_name = n_tab AND c1.constraint_name = c2.constraint_name AND c2.constraint_type = 'P';
		info tab_cols_info_t; -- armazena as info. das colunas, obtidas pelo cursor
    pk_cols_names cons_cols_t;
	BEGIN

		SELECT COUNT(*) INTO cont_tab FROM user_objects WHERE object_type = 'TABLE' AND object_name = n_tab;
    OPEN c_cons_cols;
    FETCH c_cons_cols BULK COLLECT INTO pk_cols_names;
    CLOSE c_cons_cols;

		IF cont_tab > 0 AND pk_cols_names.COUNT > 0 AND pk.COUNT = pkval.COUNT THEN

			OPEN c_tab;
			FETCH c_tab BULK COLLECT INTO info.cols_names, info.cols_types;
			CLOSE c_tab;

      cmd := 'DELETE FROM ' || n_tab || ' WHERE';

      FOR i IN pk.FIRST .. pk.LAST LOOP
        idx1 := search_varray(UPPER(pk(i)), info.cols_names);
        idx2 := search_varray(UPPER(pk(i)), pk_cols_names);

        IF idx1 > 0 AND idx2 > 0 THEN
          IF i != pk.FIRST THEN
            cmd := cmd || ' AND';
          END IF;

          cmd := cmd ||  ' ' || info.cols_names(i) || ' = :y' || i;
        END IF;

      END LOOP;

      c := DBMS_SQL.OPEN_CURSOR;
      DBMS_SQL.PARSE(c, cmd, DBMS_SQL.NATIVE);

      FOR i IN pk.FIRST .. pk.LAST LOOP
        idx1 := search_varray(UPPER(pk(i)), info.cols_names);
        idx2 := search_varray(UPPER(pk(i)), pk_cols_names);

        IF idx1 > 0 AND idx2 > 0 THEN
          CASE
            WHEN info.cols_types(idx1) = 'NUBMER' THEN DBMS_SQL.BIND_VARIABLE(c, ':y' || i, TO_NUMBER(pkval(i)));
            WHEN info.cols_types(idx1) = 'DATE' THEN DBMS_SQL.BIND_VARIABLE(C, ':y' || i, TO_DATE(pkval(i), 'YYYY-MM-DD'));
            ELSE DBMS_SQL.BIND_VARIABLE(C, ':y' || i, pkval(i));
          END CASE;
        END IF;

      END LOOP;

      res := DBMS_SQL.EXECUTE(c);

		END IF;
	END remover;

	FUNCTION listar(
		nome_tabela user_objects.object_name%TYPE,
    c_cols OUT NUMBER,
    n_cols_cons OUT col_cons_types_t,
    n_cols OUT data_array_t
	) RETURN SYS_REFCURSOR AS

    n_tab user_objects.object_name%TYPE := UPPER(nome_tabela);
 		CURSOR c_tab IS SELECT column_name, data_type FROM user_tab_cols WHERE table_name = n_tab;
		info tab_cols_info_t;

    CURSOR c_cons_cols IS SELECT c1.column_name, c2.constraint_type, c2.search_condition FROM 
      (SELECT column_name, constraint_name FROM user_cons_columns WHERE table_name = n_tab) c1 
      left join user_constraints c2 on c1.constraint_name = c2.constraint_name;
    v_cons_cols c_cons_cols%ROWTYPE;

    tab_num NUMBER := 0;
    cur SYS_REFCURSOR;
    stmt VARCHAR2(10000);
  BEGIN
		SELECT COUNT(*) INTO tab_num FROM user_objects WHERE object_type = 'TABLE' OR object_type = 'VIEW' AND object_name = n_tab;
		IF tab_num > 0 THEN
      OPEN c_tab;
      FETCH c_tab BULK COLLECT INTO info.cols_names, info.cols_types;
      CLOSE c_tab;

      stmt := 'SELECT * FROM ' || n_tab;
      OPEN cur FOR stmt;

      n_cols := data_array_t();

      FOR i IN info.cols_names.FIRST .. info.cols_names.LAST LOOP
        n_cols.EXTEND;
        n_cols(n_cols.LAST) := info.cols_names(i);
        n_cols_cons(info.cols_names(i)) := 'NULL';
      END LOOP;

      OPEN c_cons_cols;

      LOOP
        FETCH c_cons_cols INTO v_cons_cols;
        EXIT WHEN c_cons_cols%NOTFOUND;
        IF n_cols_cons(v_cons_cols.column_name) = 'NULL' THEN
          n_cols_cons(v_cons_cols.column_name) := v_cons_cols.constraint_type;
        ELSE
          n_cols_cons(v_cons_cols.column_name) := n_cols_cons(v_cons_cols.column_name) || ',' || v_cons_cols.constraint_type;
        END IF;
      END LOOP;

      CLOSE c_cons_cols;

      c_cols := info.cols_names.COUNT;

      RETURN cur;
    END IF;
    RETURN NULL;
  END listar;

	PROCEDURE atualizar(
		nome_tabela user_objects.object_name%TYPE,
		attr		data_array_t,
		val 		data_array_t,
		pk  		data_array_t,
		pkval  	data_array_t
	) AS
		n_tab user_objects.object_name%TYPE := UPPER(nome_tabela);
		CURSOR c_tab IS SELECT column_name, data_type FROM user_tab_cols WHERE table_name = n_tab;
    CURSOR c_cons_cols IS SELECT c1.column_name FROM user_cons_columns c1, user_constraints c2
            WHERE c1.table_name = n_tab AND c1.constraint_name = c2.constraint_name AND c2.constraint_type = 'P';
		info tab_cols_info_t;
    pk_cols_names cons_cols_t;
		cont_tab NUMBER := 0;
		cmd VARCHAR2(32000);
		idx1 NUMBER := 0;
		idx2 NUMBER := 0;
		c NUMBER;
		res NUMBER;
  BEGIN
		SELECT COUNT(*) INTO cont_tab FROM user_objects WHERE object_type = 'TABLE' AND object_name = n_tab;
    OPEN c_cons_cols;
    FETCH c_cons_cols BULK COLLECT INTO pk_cols_names;
    CLOSE c_cons_cols;

		IF cont_tab > 0 AND pk_cols_names.COUNT > 0 AND pk.COUNT = pkval.COUNT AND attr.COUNT = val.COUNT THEN

			OPEN c_tab;
			FETCH c_tab BULK COLLECT INTO info.cols_names, info.cols_types;
			CLOSE c_tab;

      cmd := 'UPDATE ' || n_tab || ' SET';

      FOR i IN attr.FIRST .. attr.LAST LOOP
        idx1 := search_varray(UPPER(attr(i)), info.cols_names);

        IF idx1 > 0 THEN
          IF i != attr.FIRST THEN
            cmd := cmd || ', ';
          END IF;

          cmd := cmd || ' ' || info.cols_names(idx1) || ' = :x' || i;
        END IF;

      END LOOP;

      cmd := cmd || ' WHERE';

      FOR i IN pk.FIRST .. pk.LAST LOOP
        idx1 := search_varray(UPPER(pk(i)), info.cols_names);
        idx2 := search_varray(UPPER(pk(i)), pk_cols_names);

        IF idx1 > 0 AND idx2 > 0 THEN
          IF i != pk.FIRST THEN
            cmd := cmd || ' AND';
          END IF;

          cmd := cmd ||  ' ' || info.cols_names(i) || ' = :y' || i;
        END IF;

      END LOOP;

      c := DBMS_SQL.OPEN_CURSOR;
      DBMS_SQL.PARSE(c, cmd, DBMS_SQL.NATIVE);

      FOR i IN attr.FIRST .. attr.LAST LOOP
        idx1 := search_varray(UPPER(attr(i)), info.cols_names);

        IF idx1 > 0 THEN
          CASE
            WHEN info.cols_types(idx1) = 'NUBMER' THEN DBMS_SQL.BIND_VARIABLE(c, ':x' || i, TO_NUMBER(val(i)));
            WHEN info.cols_types(idx1) = 'DATE' THEN DBMS_SQL.BIND_VARIABLE(C, ':x' || i, TO_DATE(val(i), 'YYYY-MM-DD'));
            ELSE DBMS_SQL.BIND_VARIABLE(C, ':x' || i, val(i));
          END CASE;
        END IF;

      END LOOP;


      FOR i IN pk.FIRST .. pk.LAST LOOP
        idx1 := search_varray(UPPER(pk(i)), info.cols_names);
        idx2 := search_varray(UPPER(pk(i)), pk_cols_names);

        IF idx1 > 0 AND idx2 > 0 THEN
          CASE
            WHEN info.cols_types(idx1) = 'NUBMER' THEN DBMS_SQL.BIND_VARIABLE(c, ':y' || i, TO_NUMBER(pkval(i)));
            WHEN info.cols_types(idx1) = 'DATE' THEN DBMS_SQL.BIND_VARIABLE(C, ':y' || i, TO_DATE(pkval(i), 'YYYY-MM-DD'));
            ELSE DBMS_SQL.BIND_VARIABLE(C, ':y' || i, pkval(i));
          END CASE;
        END IF;

      END LOOP;

      res := DBMS_SQL.EXECUTE(c);

		END IF;

  END atualizar;
END;

--VIEW DO EXERCICIO 2 - PAULA
CREATE OR REPLACE VIEW v_lista_cursos AS
SELECT NOMECURSO, NOME_PROFESSOR, NOMECOMPLETO AS NOME_ALUNO FROM
                    (SELECT CODCURSO, NOMECURSO, CPFPROFESSOR, NOMECOMPLETO AS NOME_PROFESSOR, CPFALUNO FROM 
                      (SELECT CODCURSO, NOMECURSO, CPFPROFESSOR, CPFALUNO FROM 
                        (SELECT CODCURSO, NOMECURSO, CPFALUNO FROM L03_ALUNO RIGHT JOIN
                          (SELECT CODCURSO, NOMECURSO, COUNT(CPFALUNO) AS CONTADOR FROM L02_CURSO LEFT JOIN L03_ALUNO 
                            ON CODCURSO=codcursoaluno GROUP BY CODCURSO, NOMECURSO ORDER BY CONTADOR)
                          ON CODCURSO=codcursoaluno
                          ORDER BY CONTADOR)
                        LEFT JOIN L04_PROFESSOR
                        ON CODCURSO=CODCURSOCOORDENADOR)
                      LEFT JOIN L01_USUARIO
                      ON CPFPROFESSOR=CPF_USR)
                    LEFT JOIN L01_USUARIO
                    ON CPFALUNO=CPF_USR;

-- TRIGGER E VIEW EXERCICIO 3 - CAMILA
CREATE OR REPLACE TRIGGER Atualiza_aprovacao
BEFORE UPDATE OR INSERT ON L09_cursa 
FOR EACH ROW /* nível de linha */
BEGIN
   IF(:new.NOTACURSA >=5) THEN
      :new.APROVADOCURSA := 'S';
   ELSE
      :new.APROVADOCURSA := 'N';
   END IF;
END;

/*
Gerar um relatório sobre quais alunos são repetentes, e em quais disciplinas reprovaram. Com
relação apenas a estas disciplinas onde houve reprovação, indicar para cada uma se um dado aluno já
conseguiu conclusão ou se ainda precisa refazê-la;
*/

CREATE OR REPLACE VIEW v_
  SELECT NOMEUSUARIO, NOMEDISC, 'PRECISA REFAZÊ-LA' AS SITUACAO
    FROM L09_CURSA CUR 
    LEFT JOIN L01_USUARIO U ON CUR.CPFCURSA = U.CPF_USR 
    LEFT JOIN L07_DISCIPLINA D ON CUR.SIGLADISCIPLINACURSA = D.SIGLADISC
    WHERE (CPFCURSA, SIGLADISCIPLINACURSA) NOT IN( 
          SELECT CPFCURSA, SIGLADISCIPLINACURSA
            FROM L09_CURSA WHERE APROVADOCURSA = 'S'  
            )
    UNION
      SELECT NOMEUSUARIO, NOMEDISC, 'CONCLUÍDA' AS SITUACAO 
        FROM L09_CURSA CUR
        LEFT JOIN L01_USUARIO U ON CUR.CPFCURSA = U.CPF_USR 
        LEFT JOIN L07_DISCIPLINA D ON CUR.SIGLADISCIPLINACURSA = D.SIGLADISC 
        WHERE (CPFCURSA, SIGLADISCIPLINACURSA) IN( 
          SELECT CPFCURSA, SIGLADISCIPLINACURSA  
            FROM L09_CURSA 
            GROUP BY (CPFCURSA, SIGLADISCIPLINACURSA) HAVING COUNT (*)>1
        )AND APROVADOCURSA = 'S';

--VIEW DO EXERCICIO 5 - SPLINTER
CREATE OR REPLACE VIEW v_disciplina_referencia AS
  SELECT UNIQUE
  d.nomedisc AS "Disciplina",
  r.qtdpaginas AS "PÃ¡ginas",
  r.descricao AS "DescriÃ§Ã£o",
  r.titulo AS "TÃ­tulo",
  r.autor AS "Autor",
  r.tiporeferencia AS "Tipo",
  l.editora AS "Editora",
  a.url AS "URL",
  a.conferencia AS "ConferÃªncia"
  FROM
  l07_disciplina d JOIN l17_utiliza_discreferencia u ON d.sigladisc = u.sigdisciplina_util
  JOIN l14_referencia r ON u.codreferencia_util = r.codreferencia
  left JOIN l15_livro l ON r.codreferencia = l.codlivro
  left JOIN l16_artigo a ON r.codreferencia = a.codartigo;


