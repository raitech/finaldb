package Operacoes;

import javax.swing.WindowConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.SwingUtilities;
import javax.swing.SwingUtilities;


public class Insere_Adm extends javax.swing.JFrame {

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton btnInsereAluno;
	private JTextField txtPriv;
	private JLabel jLabel7;
	private JTextField txtDataNasc;
	private JTextField txtNomeUser;
	private JTextField txtCPF;
	private JTextField txtNomeCompleto;
	private JTextField txtRG;
	private JTextField txtSenha;
	private JLabel jLabel6;
	private String NomeTabelaAdm = new String("L05_ADMINISTRADOR");
	private String NomeTabelaUsr = new String("L01_USUARIO");

	public Insere_Adm() {
		super();
		initGUI();
		setTitle("Inserir Administrador");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("CPF: ");
				jLabel1.setBounds(31, 52, 27, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("RG:");
				jLabel2.setBounds(31, 81, 27, 16);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Data nascimento:");
				jLabel3.setBounds(30, 139, 108, 16);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Nome usuário:");
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
				jLabel6.setBounds(31, 23, 90, 16);
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
				txtDataNasc.setBounds(133, 136, 218, 23);
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("Privilégio:");
				jLabel7.setBounds(30, 167, 90, 16);
			}
			{
				txtPriv = new JTextField();
				getContentPane().add(txtPriv);
				txtPriv.setBounds(132, 164, 220, 23);
			}
			{
				btnInsereAluno = new JButton();
				getContentPane().add(btnInsereAluno);
				btnInsereAluno.setText("INSERIR");
				btnInsereAluno.setBounds(262, 193, 90, 25);
				btnInsereAluno.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnInsereAlunoActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(400, 268);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnInsereAlunoActionPerformed(ActionEvent evt) {
		/*Pegando os atributos para user:*/
		 String[] AtributosNovosUsr = new String[7];
		 AtributosNovosUsr[0] = txtCPF.getText();
		 AtributosNovosUsr[1] = txtRG.getText();
		 AtributosNovosUsr[2] = txtDataNasc.getText();
		 AtributosNovosUsr[3] =  txtNomeUser.getText();
		 AtributosNovosUsr[4] =  txtSenha.getText();
		 AtributosNovosUsr[5] =  txtNomeCompleto.getText();
		 AtributosNovosUsr[6] =  "administrador";
		 
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
		 inter.inserir(NomeTabelaUsr, NomeColunaUsr, AtributosNovosUsr);		 
		 
		 /*inserir o usuario no campo que foi especificado, no caso Aluno*/
		 //pegando os campos para aluno
		 String[] AtributosNovosAl = new String[2];
		 AtributosNovosAl[0] = txtCPF.getText();
		 AtributosNovosAl[1] = txtPriv.getText();
		 
		 
		 String[] NomeColunaAl = new String[2];
		 NomeColunaAl[0] = "CPFADMINISTRADOR";
		 NomeColunaAl[1] = "PRIVILEGIOADMINISTRADOR";
		 
		 
		 inter.inserir(NomeTabelaAdm,NomeColunaAl,AtributosNovosAl);
		dispose();
	}

}
