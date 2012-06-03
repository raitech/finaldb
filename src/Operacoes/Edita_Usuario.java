package Operacoes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.SwingUtilities;


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
public class Edita_Usuario extends javax.swing.JFrame {

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton btnEditaAluno;	
	private JTextField txtDataNasc;
	private JTextField txtNomeUser;
	private JTextField txtCPF;
	private JTextField txtNomeCompleto;
	private JTextField txtRG;
	private JTextField txtSenha;
	private JLabel jLabel6;
	private String NomeTabelaAl = new String("L03_ALUNO");
	private String NomeTabelaUsr = new String("L01_USUARIO");
	
	private String[] pk;
	private String[] val_pk;
	/*
	private String [] AtributosNovosUsr;
	private String [] NomeColunaUsr;
	private String [] AtributosNovosAl;
	private String [] NomeColunaAl;*/

	public Edita_Usuario(String[] pk, String[] val_pk) {
		super();
		this.pk = pk;
		this.val_pk = val_pk;
		initGUI();
		setTitle("Editar Aluno");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("CPF: ");
				jLabel1.setBounds(31, 52, 58, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("RG:");
				jLabel2.setBounds(31, 81, 65, 16);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Data nascimento (AAAA-MM-DD ):");
				jLabel3.setBounds(30, 139, 195, 16);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Nome usu�rio:");
				jLabel4.setBounds(31, 110, 90, 16);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Senha:");
				jLabel5.setBounds(219, 114, 50, 16);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Nome completo:");
				jLabel6.setBounds(31, 23, 102, 16);
			}
			{
				txtSenha = new JTextField();
				getContentPane().add(txtSenha);
				txtSenha.setBounds(260, 107, 91, 23);
			}
			{
				txtRG = new JTextField();
				getContentPane().add(txtRG);
				txtRG.setBounds(133, 78, 219, 23);
			}
			{
				txtNomeCompleto = new JTextField();
				getContentPane().add(txtNomeCompleto);
				txtNomeCompleto.setBounds(133, 20, 219, 23);
			}
			{
				txtCPF = new JTextField();
				getContentPane().add(txtCPF);
				txtCPF.setBounds(133, 49, 219, 23);
			}
			{
				txtNomeUser = new JTextField();
				getContentPane().add(txtNomeUser);
				txtNomeUser.setBounds(133, 107, 68, 23);
			}
			{
				txtDataNasc = new JTextField();
				getContentPane().add(txtDataNasc);
				txtDataNasc.setBounds(225, 136, 126, 23);
			}
			{
				btnEditaAluno = new JButton();
				getContentPane().add(btnEditaAluno);
				btnEditaAluno.setText("EDITAR");
				btnEditaAluno.setBounds(261, 182, 90, 25);
				btnEditaAluno.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnEditaAlunoActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(400, 219);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnEditaAlunoActionPerformed(ActionEvent evt) {
		 /*Pegando os atributos para user:*/
		 String[] AtributosNovosUsr = new String[7];
		 AtributosNovosUsr[0] = txtCPF.getText();
		 AtributosNovosUsr[1] = txtRG.getText();
		 AtributosNovosUsr[2] = txtDataNasc.getText();
		 AtributosNovosUsr[3] =  txtNomeUser.getText();
		 AtributosNovosUsr[4] =  txtSenha.getText();
		 AtributosNovosUsr[5] =  txtNomeCompleto.getText();
		 AtributosNovosUsr[6] =  "aluno";
		 
		 String[] NomeColunaUsr = new String[7];		 
		 NomeColunaUsr[0] = "CPF_USR";
		 NomeColunaUsr[1] = "RG_USR";
		 NomeColunaUsr[2] = "DATANASCIMENTO";
		 NomeColunaUsr[3] = "NOMEUSUARIO";
		 NomeColunaUsr[4] = "SENHA";
		 NomeColunaUsr[5] = "NOMECOMPLETO";
		 NomeColunaUsr[6] = "TIPO_USUARIO";
		
		 /*inserir primeiro em usuarios*/
		 Trab_final.Interface inter = new Trab_final.Interface();
		 		 
		 
		 dispose();
	}


}
