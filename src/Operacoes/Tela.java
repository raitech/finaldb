package Operacoes;
import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;

import Trab_final.Interface;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Tela extends javax.swing.JFrame {
	private JButton btnInserir;
	private JButton btnVoltar;
	private JButton btnAtualizar;
	private JButton btnRemover;
	private String NomeTabela;
	private ModeloTabela modelo;
	private JTable tabela;
	private JScrollPane scpane;
	private Interface inter;
	
    private ArrayList<String> pk;
    private ArrayList<String> val_pk;

	public Tela(String NomeTabela) {
		super();
		initGUI();
		this.NomeTabela = NomeTabela;
		inter = new Interface();
		modelo = new ModeloTabela();
		pk = new ArrayList<String>();
		val_pk = new ArrayList<String>();
		
		/*Precisa usar o nome tabela para chamar a funcao do splinter para criar a tabela*/  
		inter.listar(this.NomeTabela, modelo, pk);
		tabela = new JTable(modelo);
        tabela.setBounds(0, 0, 560, 440);
        
        // Torna a tabela verticalmente rolavel
        scpane = new JScrollPane(tabela);
        scpane.setBounds(10, 10, 560, 440);
        scpane.setViewportView(tabela);
        
        // Torna a tabela horizontalmente rolavel
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        add(scpane,null);
      
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				btnInserir = new JButton();
				getContentPane().add(btnInserir);
				btnInserir.setText("INSERIR");
				btnInserir.setBounds(583, 12, 100, 25);
				btnInserir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnInserirActionPerformed(evt);
					}
				});
			}
			  {
		        	btnAtualizar = new JButton();
		        	getContentPane().add(btnAtualizar);
		        	btnAtualizar.setText("ATUALIZAR");
		        	btnAtualizar.setBounds(583, 84, 100, 25);
		        	btnAtualizar.addActionListener(new ActionListener() {
		        		public void actionPerformed(ActionEvent evt) {
		        			btnAtualizarActionPerformed(evt);
		        		}
		        	});
		        }
			{
				btnRemover = new JButton();
				getContentPane().add(btnRemover);
				btnRemover.setText("REMOVER");
				btnRemover.setBounds(583, 48, 100, 25);
				btnRemover.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnRemoverActionPerformed(evt);
					}
				});
			}
			{
				btnVoltar = new JButton();
				getContentPane().add(btnVoltar);
				btnVoltar.setText("VOLTAR");
				btnVoltar.setBounds(584, 220, 71, 23);
				btnVoltar.setSize(100, 25);
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnVoltarActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(714, 500);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
    
    public ArrayList<String> getPK(){     	
    	return pk;
    }
    
    public ArrayList<String> getvalPk(){     	
    	return val_pk;
    }
    
   	
	private void btnVoltarActionPerformed(ActionEvent evt) {		
		dispose();
	}
	
		
	private void btnAtualizarActionPerformed(ActionEvent evt) {
		System.out.println("btnATUALIZAR.actionPerformed, event="+evt);
		//TODO add your code for btnListar.actionPerformed
	}
	
	
	private void btnRemoverActionPerformed(ActionEvent evt) {
		//retorna o valor da columnIndex and rowIndex.
		int nlinhas = tabela.getRowCount();
		int ncol = tabela.getColumnCount(); 
		ArrayList<Integer> v_coluna_pk = new ArrayList<Integer>();
		
		//percorre em que coluna � a chave primaria
		for(int j = 0; j<pk.size();j++ ){
			for(int i = 0; i< ncol;i++)
				if(tabela.getColumnName(i).equals(pk.get(j))){
						v_coluna_pk.add(i);
						//System.out.println(i);
						break;
			}
			
		}
		
		for(int i = 0; i<nlinhas;i++){
			Boolean resp = (Boolean)tabela.getValueAt(i,0);
			if(resp){//se o checkbox estiver marcado
				for(int j = 0; j<v_coluna_pk.size();j++)
				val_pk.add((String)tabela.getValueAt(i, v_coluna_pk.get(j))); 
				//System.out.println(val_pk);
			}
		}
		System.out.println(NomeTabela);
		System.out.println(pk);
		System.out.println(val_pk);
		inter.remover(NomeTabela, pk, val_pk);
		/*modelo = new ModeloTabela();
		inter.listar(this.NomeTabela, modelo, pk);
		tabela.setModel(modelo);*/

		
	}
	
		
	private void btnInserirActionPerformed(ActionEvent evt) {
		 if(NomeTabela.equals("L01_USUARIO")){
			 Insere_User insere_user = new Insere_User();
			 insere_user.show();
		 }
		
		 else if(NomeTabela.equals("L02_CURSO")){
				Insere_Curso insere_curso = new Insere_Curso();
				insere_curso.show();
		}
		 
	     else if(NomeTabela.equals("L07_DISCIPLINA")){
			Insere_Disc insere_disc = new Insere_Disc();
			insere_disc.show();
		}
		
		else if(NomeTabela.equals("L06_INSTITUTO")){
			Insere_Instituto insere_inst = new Insere_Instituto();
			insere_inst.show();
		}
		 
		else if(NomeTabela.equals("L08_PREREQUISITO")){
			Insere_Pre_requisito insere_pre = new Insere_Pre_requisito();
			insere_pre.show();
		}
		 
		else if(NomeTabela.equals("L09_CURSA")){
			Insere_Cursa insere_cursa = new Insere_Cursa();
			insere_cursa.show();
		}
		 
		else if(NomeTabela.equals("L10_POSSUI_CURSOINSTITUTO")){
			Insere_PossuiCursoDisciplina insere_pcurso = new Insere_PossuiCursoDisciplina();
			insere_pcurso.show();
		}
		 
		else if(NomeTabela.equals("L11_AULAPRATICA")){
			Insere_Aula_Pratica insere_ap = new Insere_Aula_Pratica();
			insere_ap.show();
		}
		 
		else if(NomeTabela.equals("L12_EQUIPAMENTO")){
			Insere_Equipamento insere_eq = new Insere_Equipamento();
			insere_eq.show();
		}
		
		else if(NomeTabela.equals("L13_USA_AULAEQUIPAMENTO")){
			Insere_Aula_equipamento insere_Aequi = new Insere_Aula_equipamento();
			insere_Aequi.show();
		}
		 
		else if(NomeTabela.equals("L14_REFERENCIA")){
			Insere_Instituto insere_inst = new Insere_Instituto();
			insere_inst.show();
		}
		 
		else if(NomeTabela.equals("Livro")){
			Insere_Instituto insere_inst = new Insere_Instituto();
			insere_inst.show();
		}
		 
		else if(NomeTabela.equals("Artigo")){
			Insere_Instituto insere_inst = new Insere_Instituto();
			insere_inst.show();
		}
		 
		else if(NomeTabela.equals("Utiliza_Disreferencia")){
			Insere_UtilizaRef_Disc insere_Rdisc = new Insere_UtilizaRef_Disc();
			insere_Rdisc.show();
		}
	}

}
