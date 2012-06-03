/*DROP TABLE*/
DROP TABLE L17_utiliza_discReferencia CASCADE CONSTRAINTS;
DROP TABLE L16_artigo CASCADE CONSTRAINTS;
DROP TABLE L15_livro CASCADE CONSTRAINTS;
DROP TABLE L14_referencia CASCADE CONSTRAINTS;
DROP TABLE L13_usa_aulaEquipamento CASCADE CONSTRAINTS;
DROP TABLE L12_equipamento CASCADE CONSTRAINTS;
DROP TABLE L11_aulaPratica CASCADE CONSTRAINTS;
DROP TABLE L10_possui_cursoInstituto CASCADE CONSTRAINTS;
DROP TABLE L09_cursa CASCADE CONSTRAINTS;
DROP TABLE L08_preRequisito CASCADE CONSTRAINTS;
DROP TABLE L07_disciplina CASCADE CONSTRAINTS;
DROP TABLE L06_instituto CASCADE CONSTRAINTS;
DROP TABLE L05_administrador CASCADE CONSTRAINTS;
DROP TABLE L04_professor CASCADE CONSTRAINTS;
DROP TABLE L03_aluno CASCADE CONSTRAINTS;
DROP TABLE L02_curso CASCADE CONSTRAINTS;
DROP TABLE L01_usuario CASCADE CONSTRAINTS;

/*CREATE TABLE*/
CREATE TABLE L01_usuario (
	cpf_usr            NUMBER(15),
	rg_usr             VARCHAR(15) NOT NULL,
	dataNascimento     DATE,         
	nomeUsuario        VARCHAR(50) NOT NULL,
	senha              VARCHAR(15) NOT NULL,
	nomeCompleto       VARCHAR(100),
	tipo_usuario       VARCHAR(15) NOT NULL, --CK
		
	CONSTRAINT pk_usuario PRIMARY KEY (cpf_usr),
	CONSTRAINT usr_psw UNIQUE (nomeUsuario, senha),
	CONSTRAINT ck_tipoUsr CHECK (tipo_Usuario IN ('aluno', 'administrador', 'professor'))
);

CREATE TABLE L02_curso (
	codCurso NUMBER(5), --SQ
	nomeCurso VARCHAR(100)         NOT NULL,
	qtdSemetresCurso NUMBER(2)     DEFAULT 10 NOT NULL,
	qtdMaxSemestresCurso NUMBER(2) DEFAULT 18 NOT NULL,
	nroCredTotalCurso NUMBER(3)    NOT NULL,

	CONSTRAINT pk_curso PRIMARY KEY (codCurso)
);

CREATE TABLE L03_aluno (
	cpfAluno          NUMBER(15),
	cidadeOrigemAluno VARCHAR(50) NOT NULL,
	enderecoAluno     VARCHAR(100)NOT NULL,		
	codCursoAluno     NUMBER(5),
	
	CONSTRAINT pk_aluno PRIMARY KEY (cpfAluno),	
	CONSTRAINT fk_aluno_cpf FOREIGN KEY (cpfAluno) REFERENCES L01_usuario(cpf_usr),
	CONSTRAINT fk_aluno_codCurso FOREIGN KEY (codCursoAluno) REFERENCES L02_curso(codCurso) 
);

CREATE TABLE L04_professor (	
	cpfProfessor      NUMBER(15),
	titulacaoProfessor VARCHAR(50) NOT NULL,  --CK
	categoriaProfessor VARCHAR(10) NOT NULL,  --CK
	codCursoCoordenador NUMBER(5),
	
	CONSTRAINT pk_professor PRIMARY KEY (cpfProfessor),	
	CONSTRAINT fk_professor_cpf FOREIGN KEY (cpfProfessor) REFERENCES L01_usuario(cpf_usr), 
	CONSTRAINT fk_professor_codCurso FOREIGN KEY (codCursoCoordenador) REFERENCES L02_curso(codCurso),
	CONSTRAINT ck_tit_prof CHECK (titulacaoProfessor IN ('graduado','mestre', 'doutor')),
	CONSTRAINT ck_categoria_prof CHECK (categoriaProfessor IN ('C1','C2', 'C3'))
);

CREATE TABLE L05_administrador (
	cpfAdministrador NUMBER(15),
	privilegioAdministrador NUMBER(1) NOT NULL,	--CK

	CONSTRAINT pk_administrador PRIMARY KEY (cpfAdministrador),	
	CONSTRAINT fk_administrador_cpf FOREIGN KEY (cpfAdministrador) REFERENCES L01_usuario(cpf_usr),
	CONSTRAINT ck_priv_adm CHECK (privilegioAdministrador IN (1,2))
);

CREATE TABLE L06_instituto (
	siglaInstituto VARCHAR(5),      
	nomeInstituto VARCHAR(100) NOT NULL,
	cidadeInstituto VARCHAR(50) NOT NULL,
	
	CONSTRAINT pk_inst PRIMARY KEY (siglaInstituto)
);

CREATE TABLE L07_disciplina (
	siglaDisc              VARCHAR(10),
	cpfProfDisc            NUMBER(15),	
	siglaInstitutoDisc       VARCHAR(5),
	QtdCreditosTotalDisc   NUMBER(3),
	QtdAulasTotalDisc      NUMBER(3),	
	SemestreDisc           NUMBER(1) NOT NULL,
	AnoDisc                NUMBER(4) NOT NULL,
	NomeDisc               VARCHAR(50) NOT NULL,
	ProgramaDisc           VARCHAR(300),
	MetodoAvalDisc	       VARCHAR(200),

	CONSTRAINT pk_disciplina PRIMARY KEY (siglaDisc),
	CONSTRAINT fk_disc_cpf     FOREIGN KEY  (cpfProfDisc) REFERENCES L04_professor(cpfProfessor),
	CONSTRAINT fk_disc_sigInst FOREIGN KEY  (siglaInstitutoDisc) REFERENCES L06_instituto(siglaInstituto)
);


CREATE TABLE L08_preRequisito(
	siglaDisc             VARCHAR(10),
	siglaReq              VARCHAR(10),

	CONSTRAINT pk_pre PRIMARY KEY (siglaDisc, siglaReq),
	CONSTRAINT fk_pre FOREIGN KEY (siglaDisc) REFERENCES L07_disciplina(siglaDisc),
	CONSTRAINT fk_pre_req   FOREIGN KEY  (siglaReq) REFERENCES L07_disciplina(siglaDisc)
);

CREATE TABLE L09_cursa (
	cpfCursa              NUMBER(15), 
	siglaDisciplinaCursa  VARCHAR(10),
	anoSemestreCursa     VARCHAR(10) NOT NULL,
	aprovadoCursa        CHAR(1),   --CK
	notaCursa            NUMBER(2), --CK
	nroAulasFreqCursa    NUMBER(3),
	porcentPresencaCursa NUMBER(5,2),
	
	CONSTRAINT pk_cursa PRIMARY KEY (cpfCursa, siglaDisciplinaCursa, anoSemestreCursa),
	CONSTRAINT fk_cursa_cpf FOREIGN KEY (cpfCursa) REFERENCES L03_aluno(cpfAluno),
	CONSTRAINT fk_cursa_siglaDisc FOREIGN KEY (siglaDisciplinaCursa) REFERENCES L07_disciplina (siglaDisc),
	CONSTRAINT ck_aprovado CHECK ( aprovadoCursa in ('S','N','-')),
	CONSTRAINT ck_nota     CHECK ( notaCursa >= 0 and notaCursa <= 10)
);

CREATE TABLE L10_possui_cursoInstituto (
	codCursoP      NUMBER(5),
	sigInstitutoP  VARCHAR(5),

	CONSTRAINT pk_possui PRIMARY KEY (codCursoP,sigInstitutoP),
	CONSTRAINT fk_possui_codCurso FOREIGN KEY  (codCursoP) REFERENCES L02_curso(codCurso),
	CONSTRAINT fk_possui_sigInst  FOREIGN KEY  (sigInstitutoP) REFERENCES L06_instituto(siglaInstituto)	
);

CREATE TABLE L11_aulaPratica (
	siglaDisciplinaAP  VARCHAR(10),
	dataAP             date,
	
	CONSTRAINT pk_AP PRIMARY KEY (siglaDisciplinaAP,dataAP),
	CONSTRAINT fk_AP FOREIGN KEY  (siglaDisciplinaAP) REFERENCES L07_disciplina(siglaDisc)
);

CREATE TABLE L12_equipamento (
	codEquipamento NUMBER(5),
	nomeEquipamento VARCHAR(50),

	CONSTRAINT pk_eqpto PRIMARY KEY (codEquipamento)
);

CREATE TABLE L13_usa_aulaEquipamento (
	codEquipamento_usa NUMBER(5),
	siglaDiscAP_usa  VARCHAR(10),
	dataAP_usa             date,
	
	CONSTRAINT pk_usa PRIMARY KEY (codEquipamento_usa,siglaDiscAP_usa,dataAP_usa),
	CONSTRAINT fk_usa_eqpto FOREIGN KEY (codEquipamento_usa) REFERENCES L12_equipamento(codEquipamento),
	CONSTRAINT fk_usa_AP    FOREIGN KEY (siglaDiscAP_usa,dataAP_usa) REFERENCES L11_aulaPratica(siglaDisciplinaAP,dataAP)
);

CREATE TABLE L14_referencia (
	codReferencia NUMBER(5),
	qtdPaginas    NUMBER(4),
	descricao     VARCHAR(100),
	titulo        VARCHAR(200) NOT NULL,
	autor         VARCHAR(200) NOT NULL,
	tipoReferencia VARCHAR(10) NOT NULL, --CK
	
	CONSTRAINT pk_ref PRIMARY KEY (codReferencia),
	CONSTRAINT ck_ CHECK ( tipoReferencia in ('artigo','livro','outro'))
);

CREATE TABLE L15_livro (
	codLivro NUMBER(5),
	editora  VARCHAR(50),
	
	CONSTRAINT pk_liv PRIMARY KEY (codLivro),
	CONSTRAINT fk_liv_ref FOREIGN KEY (codLivro) REFERENCES L14_referencia(codReferencia)
);

CREATE TABLE L16_artigo (
	codArtigo NUMBER(5),
	url       VARCHAR(100),
	conferencia VARCHAR(100),

	CONSTRAINT pk_art PRIMARY KEY (codArtigo),
	CONSTRAINT fk_art_ref FOREIGN KEY (codArtigo) REFERENCES L14_referencia(codReferencia)  
);

CREATE TABLE L17_utiliza_discReferencia (
	codReferencia_util NUMBER(5),
	sigDisciplina_util VARCHAR(10),
	
	CONSTRAINT pk_util PRIMARY KEY (codReferencia_util,sigDisciplina_util),
	CONSTRAINT fk_util_ref FOREIGN KEY (codReferencia_util) REFERENCES L14_referencia(codReferencia),
	CONSTRAINT fk_util_disc FOREIGN KEY (sigDisciplina_util) REFERENCES L07_disciplina(siglaDisc)
);

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



--dados

--Usuario
INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (33276854215, '321123321', to_date('01/11/1990', 'dd/mm/yyyy'), 'Josualdo', 'josef', 'Josualdo de Oliveira Machado', 'administrador');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (64521585232, '159753456', to_date('09/07/1993', 'dd/mm/yyyy'), 'gil', 'preta', 'Preta Gil', 'administrador');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (74154154134, '123145647', to_date('01/12/1991', 'dd/mm/yyyy'), 'Francisco', 'franfran', 'Francisco Rui de Mattos', 'professor');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (3356589519, '654321122', to_date('01/10/1970', 'dd/mm/yyyy'), 'Eduardo', 'dudu', 'Eduardo Silvo e Silva', 'professor');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (0195367919, '115227733', to_date('10/06/1993', 'dd/mm/yyyy'), 'raissa', 'r123', 'Raissa Da Silva', 'professor');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (2158742963, '123987456', to_date('20/04/1995', 'dd/mm/yyyy'), 'halen', 'h321', 'Phill Halen', 'professor');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (2245697895, '333222111', to_date('01/01/1989', 'dd/mm/yyyy'), 'Marcelo', 'marc', 'Marcelo Tsglusits', 'aluno');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (6452212453, '124578451', to_date('01/01/1988', 'dd/mm/yyyy'), 'Daniela', 'dani', 'Daniela dos Santos', 'aluno');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (74165895321, '121245421', to_date('01/01/1991', 'dd/mm/yyyy'), 'Barbara', 'barbi', 'Barbara da Silva', 'aluno');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (6452157632, '333222111', to_date('02/05/1990', 'dd/mm/yyyy'), 'marco', 'm123', 'Marco Vicci', 'aluno');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (1123456689, '123321222', to_date('02/05/1990', 'dd/mm/yyyy'), 'j01', 'j01', 'Jefferson Martins', 'aluno');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (0123455789, '123321333', to_date('02/05/1990', 'dd/mm/yyyy'), 'jaum', 'j123', 'Joao Antonio', 'aluno');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (0353446789, '123321111', to_date('02/05/1990', 'dd/mm/yyyy'), 'huguinho', 'Hugo Andrade', 'Marco Vicci', 'aluno');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (523356789, '345543444', to_date('02/05/1990', 'dd/mm/yyyy'), 'donald', 'opato', 'Donald da Silva', 'aluno');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (122456789, '345543555', to_date('02/05/1990', 'dd/mm/yyyy'), 'gui', 'guizao', 'Guilherme Antonio Morais', 'aluno');

INSERT INTO L01_USUARIO (cpf_usr, rg_usr, dataNascimento, nomeUsuario, senha, nomeCompleto, tipo_usuario)
VALUES (3321987456, '3571159456', to_date('05/03/1991', 'dd/mm/yyyy'), 'jose', 'jj123', 'Jose Antonio da Silva', 'aluno');



--curso
INSERT INTO L02_CURSO (codCurso, nomeCurso, qtdSemetresCurso, qtdMaxSemestresCurso, nroCredTotalCurso)
VALUES (1, 'Informática', 8, 14, 300);

INSERT INTO L02_CURSO (codCurso, nomeCurso, qtdSemetresCurso, qtdMaxSemestresCurso, nroCredTotalCurso)
VALUES (2, 'Ciências de Computação', 10, 18, 400);

INSERT INTO L02_CURSO (codCurso, nomeCurso, qtdSemetresCurso, qtdMaxSemestresCurso, nroCredTotalCurso)
VALUES (3, 'Física', 8, 14, 300);

INSERT INTO L02_CURSO (codCurso, nomeCurso, qtdSemetresCurso, qtdMaxSemestresCurso, nroCredTotalCurso)
VALUES (4, 'Arquitetura', 8, 14, 300);

INSERT INTO L02_CURSO (codCurso, nomeCurso, qtdSemetresCurso, qtdMaxSemestresCurso, nroCredTotalCurso)
VALUES (5, 'Engenharia Elétrica', 10, 18, 400);

INSERT INTO L02_CURSO (codCurso, nomeCurso, qtdSemetresCurso, qtdMaxSemestresCurso, nroCredTotalCurso)
VALUES (6, 'Engenharia Civil', 10, 18, 400);

INSERT INTO L02_CURSO (codCurso, nomeCurso, qtdSemetresCurso, qtdMaxSemestresCurso, nroCredTotalCurso)
VALUES (7, 'Engenharia de Minas', 10, 18, 400);

INSERT INTO L02_CURSO (codCurso, nomeCurso, qtdSemetresCurso, qtdMaxSemestresCurso, nroCredTotalCurso)
VALUES (8, 'Tenologia da Informacao', 8, 14, 300);


--aluno
INSERT INTO L03_ALUNO(cpfAluno, cidadeOrigemAluno, enderecoAluno, codCursoAluno)
VALUES (2245697895, 'Aracaju', 'Av. Jose da Costa, 99', 4);

INSERT INTO L03_ALUNO(cpfAluno, cidadeOrigemAluno, enderecoAluno, codCursoAluno)
VALUES (6452212453, 'Rio Branco', 'Av. Jose, 89', 3);

INSERT INTO L03_ALUNO(cpfAluno, cidadeOrigemAluno, enderecoAluno, codCursoAluno)
VALUES (74165895321, 'Belo Horizonte', 'Rua das Orquideas, 29', 8);

INSERT INTO L03_ALUNO(cpfAluno, cidadeOrigemAluno, enderecoAluno, codCursoAluno)
VALUES (6452157632, 'Sao Paulo', 'Rua Seca, 39', 4);

INSERT INTO L03_ALUNO(cpfAluno, cidadeOrigemAluno, enderecoAluno, codCursoAluno)
VALUES (1123456689, 'Rio de Janeiro', 'Av. Mococa, 19', 4);

INSERT INTO L03_ALUNO(cpfAluno, cidadeOrigemAluno, enderecoAluno, codCursoAluno)
VALUES (0123455789, 'Jacarei', 'Rua das Flores, 99', 1);

INSERT INTO L03_ALUNO(cpfAluno, cidadeOrigemAluno, enderecoAluno, codCursoAluno)
VALUES (0353446789, 'Santos', 'Rua dos Morros, 8', 2);

INSERT INTO L03_ALUNO(cpfAluno, cidadeOrigemAluno, enderecoAluno, codCursoAluno)
VALUES (523356789, 'Diadema', 'Av. Francisco, 288', 4);

INSERT INTO L03_ALUNO(cpfAluno, cidadeOrigemAluno, enderecoAluno, codCursoAluno)
VALUES (122456789, 'Iracemapolis', 'Av. Jose da Silva, 9', 4);

INSERT INTO L03_ALUNO(cpfAluno, cidadeOrigemAluno, enderecoAluno, codCursoAluno)
VALUES (3321987456, 'Sao Paulo', 'Av. Paulista, 600', 5);


--professor
INSERT INTO L04_PROFESSOR(cpfProfessor, titulacaoProfessor, categoriaProfessor, codCursoCoordenador)
VALUES (74154154134, 'mestre', 'C1', 1);

INSERT INTO L04_PROFESSOR(cpfProfessor, titulacaoProfessor, categoriaProfessor, codCursoCoordenador)
VALUES (3356589519, 'doutor', 'C3', 2);

INSERT INTO L04_PROFESSOR(cpfProfessor, titulacaoProfessor, categoriaProfessor, codCursoCoordenador)
VALUES (0195367919, 'doutor', 'C3', 3);

INSERT INTO L04_PROFESSOR(cpfProfessor, titulacaoProfessor, categoriaProfessor, codCursoCoordenador)
VALUES (2158742963, 'doutor', 'C2', 4);


--administrador
INSERT INTO L05_ADMINISTRADOR(cpfAdministrador, privilegioAdministrador)
VALUES (33276854215, 2);

INSERT INTO L05_ADMINISTRADOR(cpfAdministrador, privilegioAdministrador)
VALUES (64521585232, 1);


--instituto
INSERT INTO L06_INSTITUTO(siglaInstituto, nomeInstituto, cidadeInstituto)
VALUES ('ICMC', 'Instituto de Ciências Matemáticas e de Computação', 'São Carlos');

INSERT INTO L06_INSTITUTO(siglaInstituto, nomeInstituto, cidadeInstituto)
VALUES ('IQSC', 'Instituto de Química de São Carlos', 'São Carlos');

INSERT INTO L06_INSTITUTO(siglaInstituto, nomeInstituto, cidadeInstituto)
VALUES ('IFSC', 'Instituto de Física de São Carlos', 'São Carlos');

INSERT INTO L06_INSTITUTO(siglaInstituto, nomeInstituto, cidadeInstituto)
VALUES ('EESC', 'Escola de Engenharia de São Carlos', 'São Carlos');

INSERT INTO L06_INSTITUTO(siglaInstituto, nomeInstituto, cidadeInstituto)
VALUES ('IAU', 'Instituto de Arquitetura e Urbanismo', 'São Carlos');

INSERT INTO L06_INSTITUTO(siglaInstituto, nomeInstituto, cidadeInstituto)
VALUES ('IA', 'Instituto de Artes', 'Campinas');

INSERT INTO L06_INSTITUTO(siglaInstituto, nomeInstituto, cidadeInstituto)
VALUES ('IB', 'Instituto de Biociencias', 'São Paulo');

INSERT INTO L06_INSTITUTO(siglaInstituto, nomeInstituto, cidadeInstituto)
VALUES ('IP', 'Instituto de Psicologia', 'São Paulo');


--disciplina
INSERT INTO L07_DISCIPLINA(siglaDisc, cpfProfDisc, siglaInstitutoDisc, QtdCreditosTotalDisc, QtdAulasTotalDisc, SemestreDisc, AnoDisc, NomeDisc, ProgramaDisc, MetodoAvalDisc)
VALUES ('SCC01', 74154154134, 'ICMC', 30, 10, 1, 2012, 'Banco de Dados', 'Algebra Relacional', 'Média Ponderada');

INSERT INTO L07_DISCIPLINA(siglaDisc, cpfProfDisc, siglaInstitutoDisc, QtdCreditosTotalDisc, QtdAulasTotalDisc, SemestreDisc, AnoDisc, NomeDisc, ProgramaDisc, MetodoAvalDisc)
VALUES ('SCC02', 3356589519, 'ICMC', 30, 10, 1, 2012, 'Alg 1', 'Algoritmos Gerais', 'Média Ponderada');

INSERT INTO L07_DISCIPLINA(siglaDisc, cpfProfDisc, siglaInstitutoDisc, QtdCreditosTotalDisc, QtdAulasTotalDisc, SemestreDisc, AnoDisc, NomeDisc, ProgramaDisc, MetodoAvalDisc)
VALUES ('SCC03', 3356589519, 'ICMC', 30, 10, 1, 2012, 'Alg 2', 'Algoritmos Gerais', 'Média Ponderada');

INSERT INTO L07_DISCIPLINA(siglaDisc, cpfProfDisc, siglaInstitutoDisc, QtdCreditosTotalDisc, QtdAulasTotalDisc, SemestreDisc, AnoDisc, NomeDisc, ProgramaDisc, MetodoAvalDisc)
VALUES ('SCC04', 2158742963, 'ICMC', 30, 10, 1, 2012, 'ICC 1', 'Conceitos introdutorios a programacao em C', 'Média Ponderada');

INSERT INTO L07_DISCIPLINA(siglaDisc, cpfProfDisc, siglaInstitutoDisc, QtdCreditosTotalDisc, QtdAulasTotalDisc, SemestreDisc, AnoDisc, NomeDisc, ProgramaDisc, MetodoAvalDisc)
VALUES ('SCC05', 2158742963, 'ICMC', 30, 10, 1, 2012, 'ICC 2', 'Linguagem C', 'Média Ponderada');

INSERT INTO L07_DISCIPLINA(siglaDisc, cpfProfDisc, siglaInstitutoDisc, QtdCreditosTotalDisc, QtdAulasTotalDisc, SemestreDisc, AnoDisc, NomeDisc, ProgramaDisc, MetodoAvalDisc)
VALUES ('FFI01', 0195367919, 'IFSC', 30, 10, 1, 2012, 'Fisica 1', 'Mecanica', 'Média Ponderada');


--preRequisito
INSERT INTO L08_PREREQUISITO(siglaDisc, siglaReq)
VALUES ('SCC01', 'SCC03');

INSERT INTO L08_PREREQUISITO(siglaDisc, siglaReq)
VALUES ('SCC01', 'FFI01');

INSERT INTO L08_PREREQUISITO(siglaDisc, siglaReq)
VALUES ('SCC03', 'SCC02');

INSERT INTO L08_PREREQUISITO(siglaDisc, siglaReq)
VALUES ('SCC05', 'SCC04');

INSERT INTO L08_PREREQUISITO(siglaDisc, siglaReq)
VALUES ('SCC02', 'SCC05');


--cursa
INSERT INTO L09_CURSA(cpfCursa, siglaDisciplinaCursa, anoSemestreCursa, aprovadoCursa, notaCursa, nroAulasFreqCursa, porcentPresencaCursa)
VALUES (2245697895, 'SCC01', '01/2012', '-', 9, 5, 50);

INSERT INTO L09_CURSA(cpfCursa, siglaDisciplinaCursa, anoSemestreCursa, aprovadoCursa, notaCursa, nroAulasFreqCursa, porcentPresencaCursa)
VALUES (2245697895, 'SCC01', '01/2011', '-', 1, 5, 50);

INSERT INTO L09_CURSA(cpfCursa, siglaDisciplinaCursa, anoSemestreCursa, aprovadoCursa, notaCursa, nroAulasFreqCursa, porcentPresencaCursa)
VALUES (6452212453, 'SCC02', '01/2012', '-', 7, 5, 50);

INSERT INTO L09_CURSA(cpfCursa, siglaDisciplinaCursa, anoSemestreCursa, aprovadoCursa, notaCursa, nroAulasFreqCursa, porcentPresencaCursa)
VALUES (6452212453, 'SCC04', '01/2012', '-', 4, 5, 50);

INSERT INTO L09_CURSA(cpfCursa, siglaDisciplinaCursa, anoSemestreCursa, aprovadoCursa, notaCursa, nroAulasFreqCursa, porcentPresencaCursa)
VALUES (74165895321, 'FFI01', '01/2012', '-', 6, 5, 50);

INSERT INTO L09_CURSA(cpfCursa, siglaDisciplinaCursa, anoSemestreCursa, aprovadoCursa, notaCursa, nroAulasFreqCursa, porcentPresencaCursa)
VALUES (1123456689, 'SCC03', '01/2012', '-', 4, 5, 50);

INSERT INTO L09_CURSA(cpfCursa, siglaDisciplinaCursa, anoSemestreCursa, aprovadoCursa, notaCursa, nroAulasFreqCursa, porcentPresencaCursa)
VALUES (0353446789, 'SCC03', '01/2012', '-', 9, 5, 50);

INSERT INTO L09_CURSA(cpfCursa, siglaDisciplinaCursa, anoSemestreCursa, aprovadoCursa, notaCursa, nroAulasFreqCursa, porcentPresencaCursa)
VALUES (0353446789, 'SCC04', '01/2012', '-', 2, 5, 50);


--possui_cursoInstituto
INSERT INTO L10_POSSUI_CURSOINSTITUTO(codCursoP, sigInstitutoP) VALUES (1, 'ICMC');
INSERT INTO L10_POSSUI_CURSOINSTITUTO(codCursoP, sigInstitutoP) VALUES (2, 'ICMC');
INSERT INTO L10_POSSUI_CURSOINSTITUTO(codCursoP, sigInstitutoP) VALUES (3, 'IFSC');
INSERT INTO L10_POSSUI_CURSOINSTITUTO(codCursoP, sigInstitutoP) VALUES (4, 'IAU');
INSERT INTO L10_POSSUI_CURSOINSTITUTO(codCursoP, sigInstitutoP) VALUES (5, 'EESC');


--aulaPratica
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC01', to_date('21/03/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC01', to_date('27/03/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC01', to_date('04/04/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC01', to_date('14/04/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC01', to_date('23/04/2012', 'dd/mm/yyyy'));

INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC02', to_date('21/03/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC02', to_date('27/03/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC02', to_date('04/04/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC02', to_date('14/04/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC02', to_date('23/04/2012', 'dd/mm/yyyy'));

INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC03', to_date('22/03/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC03', to_date('28/03/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC03', to_date('05/04/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC03', to_date('15/04/2012', 'dd/mm/yyyy'));
INSERT INTO L11_AULAPRATICA(siglaDisciplinaAP, dataAP) VALUES ('SCC03', to_date('24/04/2012', 'dd/mm/yyyy'));


--equipamento
INSERT INTO L12_EQUIPAMENTO(codEquipamento, nomeEquipamento) VALUES (1, 'Projetor');
INSERT INTO L12_EQUIPAMENTO(codEquipamento, nomeEquipamento) VALUES (2, 'Mesa de prototipação');
INSERT INTO L12_EQUIPAMENTO(codEquipamento, nomeEquipamento) VALUES (3, 'FPGA');
INSERT INTO L12_EQUIPAMENTO(codEquipamento, nomeEquipamento) VALUES (4, 'Maquina de Solda');
INSERT INTO L12_EQUIPAMENTO(codEquipamento, nomeEquipamento) VALUES (5, 'ProtoBoard');
INSERT INTO L12_EQUIPAMENTO(codEquipamento, nomeEquipamento) VALUES (6, 'Equipamento de Som');
INSERT INTO L12_EQUIPAMENTO(codEquipamento, nomeEquipamento) VALUES (7, 'Roteador');
INSERT INTO L12_EQUIPAMENTO(codEquipamento, nomeEquipamento) VALUES (8, 'notebook');

select * from l11_aulapratica
--usa_aulaEquipamento
INSERT INTO L13_USA_AULAEQUIPAMENTO(codEquipamento_usa, siglaDiscAP_usa, dataAP_usa) VALUES (1, 'SCC01',  to_date('21/03/2012', 'dd/mm/yyyy'));
INSERT INTO L13_USA_AULAEQUIPAMENTO(codEquipamento_usa, siglaDiscAP_usa, dataAP_usa) VALUES (6, 'SCC01',  to_date('21/03/2012', 'dd/mm/yyyy'));
INSERT INTO L13_USA_AULAEQUIPAMENTO(codEquipamento_usa, siglaDiscAP_usa, dataAP_usa) VALUES (1, 'SCC03',  to_date('22/03/2012', 'dd/mm/yyyy'));
INSERT INTO L13_USA_AULAEQUIPAMENTO(codEquipamento_usa, siglaDiscAP_usa, dataAP_usa) VALUES (6, 'SCC02',  to_date('04/04/2012', 'dd/mm/yyyy'));
INSERT INTO L13_USA_AULAEQUIPAMENTO(codEquipamento_usa, siglaDiscAP_usa, dataAP_usa) VALUES (1, 'SCC02',  to_date('23/04/2012', 'dd/mm/yyyy'));

--referencia
INSERT INTO L14_REFERENCIA(codReferencia, qtdPaginas, descricao, titulo, autor, tipoReferencia)
VALUES (1, 10, 'Manual de Ligar um Computador', 'Desvendando o Power Button', 'MicroDum', 'outro');

INSERT INTO L14_REFERENCIA(codReferencia, qtdPaginas, descricao, titulo, autor, tipoReferencia)
VALUES (2, 250, 'Introdução a Linguagem C', 'C Completo Total', 'José', 'livro');

INSERT INTO L14_REFERENCIA(codReferencia, qtdPaginas, descricao, titulo, autor, tipoReferencia)
VALUES (3, 70, 'Introdução a regras de associação', 'Association Rules: A Survey', 'Aggraw', 'artigo');

INSERT INTO L14_REFERENCIA(codReferencia, qtdPaginas, descricao, titulo, autor, tipoReferencia)
VALUES (4, 10, 'A mulher e a cozinha', 'Para a mulher moderna', 'Solucoes', 'outro');

INSERT INTO L14_REFERENCIA(codReferencia, qtdPaginas, descricao, titulo, autor, tipoReferencia)
VALUES (5, 250, 'Aprendendo C em dois dias', 'C Completo Total', 'Silva', 'livro');

INSERT INTO L14_REFERENCIA(codReferencia, qtdPaginas, descricao, titulo, autor, tipoReferencia)
VALUES (6, 70, 'Introdução a metodos de acesso metricos', 'MAMs', 'C. T. Jr.', 'artigo');

--livro
INSERT INTO L15_LIVRO(codLivro, editora)
VALUES (2, 'Abril');

INSERT INTO L15_LIVRO(codLivro, editora)
VALUES (5, 'Globo');

--artigo
INSERT INTO L16_ARTIGO(codArtigo, url, conferencia)
VALUES (3, 'www.kddsigmod.com/123213', 'kdd sigmod');
INSERT INTO L16_ARTIGO(codArtigo, url, conferencia)
VALUES (6, 'www.acm.com/123456', 'acm');


--utiliza_discReferencia
INSERT INTO L17_UTILIZA_DISCREFERENCIA(codReferencia_util, sigDisciplina_util) VALUES (1, 'SCC01');
INSERT INTO L17_UTILIZA_DISCREFERENCIA(codReferencia_util, sigDisciplina_util) VALUES (1, 'SCC02');
INSERT INTO L17_UTILIZA_DISCREFERENCIA(codReferencia_util, sigDisciplina_util) VALUES (2, 'SCC01');
INSERT INTO L17_UTILIZA_DISCREFERENCIA(codReferencia_util, sigDisciplina_util) VALUES (2, 'SCC03');
INSERT INTO L17_UTILIZA_DISCREFERENCIA(codReferencia_util, sigDisciplina_util) VALUES (3, 'SCC03');
INSERT INTO L17_UTILIZA_DISCREFERENCIA(codReferencia_util, sigDisciplina_util) VALUES (2, 'SCC02');

INSERT INTO L17_UTILIZA_DISCREFERENCIA(codReferencia_util, sigDisciplina_util) VALUES (1, 'SCC03');
INSERT INTO L17_UTILIZA_DISCREFERENCIA(codReferencia_util, sigDisciplina_util) VALUES (4, 'SCC03');
INSERT INTO L17_UTILIZA_DISCREFERENCIA(codReferencia_util, sigDisciplina_util) VALUES (5, 'SCC04');
INSERT INTO L17_UTILIZA_DISCREFERENCIA(codReferencia_util, sigDisciplina_util) VALUES (6, 'SCC04');




/*
refs:
http://www.adp-gmbh.ch/ora/plsql/coll/return_table.html
http://docs.oracle.com/cd/E11882_01/server.112/e25513/statviews_1046.htm#REFRN20047
*/

CREATE OR REPLACE TYPE data_array_t IS VARRAY(60) OF VARCHAR2(1000);
CREATE OR REPLACE TYPE cols_names_t IS VARRAY(60) OF VARCHAR2(1000);
CREATE OR REPLACE TYPE cols_types_t IS VARRAY(60) OF VARCHAR2(1000);
CREATE OR REPLACE TYPE cons_cols_t  IS VARRAY(60) OF VARCHAR2(1000);

CREATE OR REPLACE PACKAGE maneja_tabela AS

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
    attr data_array_t;
    attrcons data_array_t;
    nattr NUMBER;
    cur sys_refcursor := maneja_tabela.listar('l01_usuario', nattr, attrcons, attr);
    v_tab l01_usuario%ROWTYPE;
  begin
    dbms_output.put_line('No de atributos: ' || nattr);
    for i in attr.first .. attr.last loop
      dbms_output.put_line(attr(i) || ': ' || attrcons(i));
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
    n_cols_cons OUT data_array_t,
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
    n_cols_cons OUT data_array_t,
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

    idx1 NUMBER := 0;
  BEGIN
		SELECT COUNT(*) INTO tab_num FROM user_objects WHERE object_type = 'TABLE' OR object_type = 'VIEW' AND object_name = n_tab;
		IF tab_num > 0 THEN
      OPEN c_tab;
      FETCH c_tab BULK COLLECT INTO info.cols_names, info.cols_types;
      CLOSE c_tab;

      stmt := 'SELECT * FROM ' || n_tab;
      OPEN cur FOR stmt;

      n_cols := data_array_t();
      n_cols_cons := data_array_t();

      FOR i IN info.cols_names.FIRST .. info.cols_names.LAST LOOP
        n_cols.EXTEND;
        n_cols(n_cols.LAST) := info.cols_names(i);
        n_cols_cons.EXTEND;
        n_cols_cons(n_cols_cons.LAST) := 'NULL';
      END LOOP;

      OPEN c_cons_cols;

      LOOP
        FETCH c_cons_cols INTO v_cons_cols;
        EXIT WHEN c_cons_cols%NOTFOUND;

        idx1 := search_varray(v_cons_cols.column_name, n_cols);
        IF n_cols_cons(idx1) = 'NULL' THEN
          n_cols_cons(idx1) := v_cons_cols.constraint_type;
        ELSE
          n_cols_cons(idx1) := n_cols_cons(idx1) || ',' || v_cons_cols.constraint_type;
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


--VIEW DO EXERCICIO 2: lista de cursos com os respectivos alunos matriculados
CREATE OR REPLACE VIEW v_lista_cursos AS
  SELECT NOMECURSO, NOME_PROFESSOR, NOMECOMPLETO AS NOME_ALUNO FROM
    (SELECT CODCURSO, NOMECURSO, CPFPROFESSOR, NOMECOMPLETO AS NOME_PROFESSOR, CPFALUNO FROM 
      (SELECT CODCURSO, NOMECURSO, CPFPROFESSOR, CPFALUNO FROM 
        (SELECT CODCURSO, NOMECURSO, CPFALUNO FROM L03_ALUNO RIGHT JOIN
          (SELECT CODCURSO, NOMECURSO, COUNT(CPFALUNO) AS CONTADOR FROM L02_CURSO 
           LEFT JOIN L03_ALUNO 
           ON CODCURSO=codcursoaluno GROUP BY CODCURSO, NOMECURSO ORDER BY CONTADOR)
         ON CODCURSO=codcursoaluno
         ORDER BY CONTADOR)
      LEFT JOIN L04_PROFESSOR
      ON CODCURSO=CODCURSOCOORDENADOR)
    LEFT JOIN L01_USUARIO
    ON CPFPROFESSOR=CPF_USR)
  LEFT JOIN L01_USUARIO
  ON CPFALUNO=CPF_USR;


-- VIEW EXERCICIO 3: lista alunos repetentes
CREATE OR REPLACE VIEW v_repetentes AS
  SELECT NOMEUSUARIO, NOMEDISC, 'PRECISA REFAZE-LAS' AS SITUACAO
    FROM L09_CURSA CUR 
    LEFT JOIN L01_USUARIO U ON CUR.CPFCURSA = U.CPF_USR 
    LEFT JOIN L07_DISCIPLINA D ON CUR.SIGLADISCIPLINACURSA = D.SIGLADISC
    WHERE (CPFCURSA, SIGLADISCIPLINACURSA) NOT IN( 
          SELECT CPFCURSA, SIGLADISCIPLINACURSA
            FROM L09_CURSA WHERE APROVADOCURSA = 'S'  
            )
    UNION
      SELECT NOMEUSUARIO, NOMEDISC, 'CONCLUIDAS' AS SITUACAO 
        FROM L09_CURSA CUR
        LEFT JOIN L01_USUARIO U ON CUR.CPFCURSA = U.CPF_USR 
        LEFT JOIN L07_DISCIPLINA D ON CUR.SIGLADISCIPLINACURSA = D.SIGLADISC 
        WHERE (CPFCURSA, SIGLADISCIPLINACURSA) IN( 
          SELECT CPFCURSA, SIGLADISCIPLINACURSA  
            FROM L09_CURSA 
            GROUP BY (CPFCURSA, SIGLADISCIPLINACURSA) HAVING COUNT (*)>1
        )AND APROVADOCURSA = 'S';
        
        
--VIEW DO EXERCICIO 4: listas disciplinas requisitos
CREATE OR REPLACE VIEW v_requisitos AS
  SELECT SIGLA, DISCIPLINA, SIGLAREQ, DD.NOMEDISC AS REQUISITO 
    FROM (SELECT D.SIGLADISC AS SIGLA, D.NOMEDISC AS DISCIPLINA, P.SIGLAREQ AS SIGLAREQ 
      FROM L07_DISCIPLINA D 
      LEFT JOIN L08_PREREQUISITO P 
      ON D.SIGLADISC=P.SIGLADISC
      ORDER BY D.SIGLADISC, P.SIGLAREQ)
    JOIN L07_DISCIPLINA DD
    ON SIGLAREQ=DD.SIGLADISC;   


--VIEW DO EXERCICIO 5: lista referencias das disciplinas
CREATE OR REPLACE VIEW v_disciplinas_referencias AS
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
  LEFT JOIN l15_livro l ON r.codreferencia = l.codlivro
  LEFT JOIN l16_artigo a ON r.codreferencia = a.codartigo;


