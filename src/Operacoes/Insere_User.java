package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


public class Insere_User extends javax.swing.JFrame {
	private JRadioButton jR_Aluno;
	private JRadioButton jR_Prof;
	private JButton btnVoltar;
	private JButton btnInsereUsr;
	private JLabel jLabel1;
	private JRadioButton jR_Adm;

	public Insere_User() {
		super();
		initGUI();
		setTitle("Menu Inserir Usuarios");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jR_Aluno = new JRadioButton();
				getContentPane().add(jR_Aluno);
				jR_Aluno.setText("Aluno");
				jR_Aluno.setBounds(40, 84, 96, 20);
				jR_Aluno.setFont(new java.awt.Font("Segoe UI",0,18));
			}
			{
				jR_Adm = new JRadioButton();
				getContentPane().add(jR_Adm);
				jR_Adm.setText("Administrador");
				jR_Adm.setBounds(40, 138, 169, 20);
				jR_Adm.setFont(new java.awt.Font("Segoe UI",0,18));
			}
			{
				jR_Prof = new JRadioButton();
				getContentPane().add(jR_Prof);
				jR_Prof.setText("Professor");
				jR_Prof.setBounds(40, 111, 143, 20);
				jR_Prof.setFont(new java.awt.Font("Segoe UI",0,18));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Que tipo de usuario deseja inserir?");
				jLabel1.setBounds(40, 24, 371, 46);
				jLabel1.setFont(new java.awt.Font("Segoe UI",1,20));
			}
			{
				btnInsereUsr = new JButton();
				getContentPane().add(btnInsereUsr);
				btnInsereUsr.setText("INSERIR");
				btnInsereUsr.setBounds(279, 176, 90, 25);
				btnInsereUsr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnInsereUsrActionPerformed(evt);
					}
				});
			}
			{
				btnVoltar = new JButton();
				getContentPane().add(btnVoltar);
				btnVoltar.setText("VOLTAR");
				btnVoltar.setBounds(40, 176, 90, 25);
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnVoltarActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(418, 250);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnInsereUsrActionPerformed(ActionEvent evt) {
		if(jR_Prof.isSelected()){
			Insere_Professor insere_prof = new Insere_Professor();
			insere_prof.show();
			
		}
		
		else if(jR_Adm.isSelected()){
			Insere_Adm insere_adm = new Insere_Adm();
			insere_adm.show();
			
		}
		else if(jR_Aluno.isSelected()){
			Insere_Aluno insere_aluno = new Insere_Aluno();
			insere_aluno.show();
	
		}
	}
	
	private void btnVoltarActionPerformed(ActionEvent evt) {
		dispose();
	}

}
