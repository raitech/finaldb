package Trab_final;


import java.awt.List;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.*;
import oracle.jdbc.*;

public class Interface {
        /*Estabelecer uma conexão*/
        static Connection connection;
        
        static void loadDriver() throws ClassNotFoundException {
                        String driver = "oracle.jdbc.driver.OracleDriver";
                        Class.forName(driver);
                }

        static Connection getConnection() throws SQLException {
                       return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "u7152138", "u7152138");
                }

        
        /*Inserir em uma tabela*/
	    public static void inserir(String nome_tabela, String[] campos_da_tabela, String[] novos_atributos){
	      
	        try{
	            CallableStatement psmt;
	            Connection myConnection = connection;
	            
	            String cmd_sql = new String();
	            cmd_sql = "DECLARE atribs maneja_tabela.data_array_t :=  maneja_tabela.data_array_t() ; vals maneja_tabela.data_array_t := maneja_tabela.data_array_t(); BEGIN ";
	            
	            // inserindo nomes de atributos do varray
	            for(int i=0 ; i<campos_da_tabela.length ; i++){
	                cmd_sql += " atribs.extend();  atribs(atribs.LAST) := '" + campos_da_tabela[i] + "'; ";
	            }
	            
	            // inserindo valores de cada atributo no varray
	            for(int i=0 ; i<campos_da_tabela.length ; i++){
	                cmd_sql += " vals.extend();  vals(vals.LAST) := '" + novos_atributos[i] + "'; ";
	            }
	            
	            cmd_sql += " maneja_tabela.inserir('" + nome_tabela + "', atribs, vals); END;";
	            
	            System.out.println(cmd_sql);
	            psmt = myConnection.prepareCall(cmd_sql); 
	            psmt.executeUpdate();
	            
	            
	        }catch(SQLException sqle){
	            if(sqle.getErrorCode() == 20002){
	                System.out.println("\nNão é possível inserir chave duplicada!\n");
	            }
	            if(sqle.getErrorCode() == 20001){
	                System.out.println("\nVerifique se os valores de liderança, conhecimento técnico, conhecimento"
	                        + "geral, relacionamento social e tolerancia à hierarquia estão entre 0 e 10 ");
	            }
	            if(sqle.getErrorCode() == 20000){
	                System.out.println("\nCampos obrigatórios não preenchidos");
	            }
	            sqle.printStackTrace();
	            System.out.println(sqle + "\nProblema na hora da inserção do candidato!\n");
	        }
	    }
	    
	    /*remove uma tupla*/
	    public static void remover(String nome_tabela, String[] pk, String[] val_pk){
		      
	        try{
	            CallableStatement psmt;
	            Connection myConnection = connection;
	            
	            String cmd_sql = new String();
	            cmd_sql = "DECLARE atribs maneja_tabela.data_array_t :=  maneja_tabela.data_array_t() ; vals maneja_tabela.data_array_t := maneja_tabela.data_array_t(); BEGIN ";
	            
	            // inserindo nomes de atributos do varray
	            for(int i=0 ; i<pk.length ; i++){
	                cmd_sql += " atribs.extend();  atribs(atribs.LAST) := '" + pk[i] + "'; ";
	            }
	            
	            // inserindo valores de cada atributo no varray
	            for(int i=0 ; i<pk.length ; i++){
	                cmd_sql += " vals.extend();  vals(vals.LAST) := '" + val_pk[i] + "'; ";
	            }
	            
	            cmd_sql += " maneja_tabela.remover('" + nome_tabela + "', atribs, vals); END;";
	            
	            System.out.println(cmd_sql);
	            psmt = myConnection.prepareCall(cmd_sql); 
	            psmt.executeUpdate();
	            
	            
	        }catch(SQLException sqle){
	            if(sqle.getErrorCode() == 20002){
	                System.out.println("\nNão é possível inserir chave duplicada!\n");
	            }
	            if(sqle.getErrorCode() == 20001){
	                System.out.println("\nVerifique se os valores de liderança, conhecimento técnico, conhecimento"
	                        + "geral, relacionamento social e tolerancia à hierarquia estão entre 0 e 10 ");
	            }
	            if(sqle.getErrorCode() == 20000){
	                System.out.println("\nCampos obrigatórios não preenchidos");
	            }
	            sqle.printStackTrace();
	            System.out.println(sqle + "\nProblema na hora da inserção do candidato!\n");
	        }
	    }	
	
	    
	    public /*static*/ void listar(String nome_tabela, DefaultTableModel modelo, String[] NomeColunas, String[]TipoColunas, int nColunas, String[][] tuplas ){
	    	try{
	    		OracleCallableStatement psmt = null; 
	    		Connection myConnection = connection;

	            String cmd_sql = new String();
	            cmd_sql =  "{ call ? := maneja_tabela.listar('" + nome_tabela + "', ?, ?, ?) }";            

	            System.out.println(cmd_sql);
	            psmt = (OracleCallableStatement) myConnection.prepareCall(cmd_sql);

	            psmt.registerOutParameter(1, OracleTypes.CURSOR);
	            psmt.registerOutParameter(2, OracleTypes.NUMBER);
	            psmt.registerOutParameter(3, OracleTypes.ARRAY, "DATA_ARRAY_T");
	            psmt.registerOutParameter(4, OracleTypes.ARRAY, "DATA_ARRAY_T");

	            psmt.executeUpdate();

	            //if(psmt.getArray(4) != null)System.out.println("not null");

	            ResultSet rsc = (ResultSet)psmt.getObject(1);
	            ARRAY NomeAtrib = psmt.getARRAY(4);
	            ARRAY TipoConstraintAtrib = psmt.getARRAY(3);

	            nColunas = psmt.getInt(2);
	            //Array TipoCons = psmt.getArray(3);
	            //Array NomeAtrib = psmt.getArray(4);


	            //NomeColunas = new String[nColunas];
	            //TipoColunas = new String[nColunas];
	            NomeColunas = (String[]) NomeAtrib.getArray();
	            TipoColunas = (String[]) TipoConstraintAtrib.getArray();
	            //tabela_tuplas = new ArrayList<String>();
	            
	            modelo.addColumn("CheckBox");
	            for(int i=0;i<nColunas;i++){
	            	//NomeColunas[i] = (String)rsACol[i];
	            	//TipoColunas[i] = (String)rsATipo[i];
	            	modelo.addColumn(NomeColunas[i]);
	            }

	            while (rsc.next()) {
	            	Vector tmp = new Vector(0,1);
	            	tmp.add(new Boolean(false));
	            	for(int i=1;i<=nColunas;i++){
	            		tmp.add(rsc.getString(i));
	            	}
	            	modelo.addRow(tmp);
				}


	        }catch(SQLException sqle){
	            if(sqle.getErrorCode() == 20000){
	                System.out.println("\nNenhum dado encontrado\n");
	            }
	            System.out.println(sqle + "\n Ocorreu um problema na listagem!!\n");
	        }


	    }
	    
    
    

} 