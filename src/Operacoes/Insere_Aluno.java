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
public class Insere_Aluno extends javax.swing.JFrame {

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton btnInsereAluno;	
	private JLabel jLabel11;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JTextField txtDataNasc;
	private JTextField txtNomeUser;
	private JTextField txtCPF;
	private JTextField txtNomeCompleto;
	private JTextField txtRG;
	private JTextField txtEndereco;
	private JTextField txtCidadeOrigem;
	private JTextField txtCodCurso;
	private JTextField txtSenha;
	private JLabel jLabel6;
	private String NomeTabelaAl = new String("L03_ALUNO");
	private String NomeTabelaUsr = new String("L01_USUARIO");
	private String [] AtributosNovosUsr;
	private String [] NomeColunaUsr;
	private String [] AtributosNovosAl;
	private String [] NomeColunaAl;

	public Insere_Aluno() {
		super();
		initGUI();
		setTitle("Inserir Aluno");
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
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("Código curso:");
				jLabel7.setBounds(30, 167, 90, 16);
			}
			{
				jLabel8 = new JLabel();
				getContentPane().add(jLabel8);
				jLabel8.setText("Cidade origem:");
				jLabel8.setBounds(30, 195, 90, 16);
			}
			{
				jLabel11 = new JLabel();
				getContentPane().add(jLabel11);
				jLabel11.setText("Endereço:");
				jLabel11.setBounds(30, 221, 76, 16);
			}
			{
				txtCodCurso = new JTextField();
				getContentPane().add(txtCodCurso);
				txtCodCurso.setBounds(132, 164, 220, 23);
			}
			{
				txtCidadeOrigem = new JTextField();
				getContentPane().add(txtCidadeOrigem);
				txtCidadeOrigem.setBounds(132, 192, 220, 23);
			}
			{
				txtEndereco = new JTextField();
				getContentPane().add(txtEndereco);
				txtEndereco.setBounds(132, 218, 220, 23);
			}
			{
				btnInsereAluno = new JButton();
				getContentPane().add(btnInsereAluno);
				btnInsereAluno.setText("INSERIR");
				btnInsereAluno.setBounds(262, 253, 90, 25);
				btnInsereAluno.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnInsereAlunoActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(400, 328);
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
		 inter.inserir(NomeTabelaUsr, NomeColunaUsr, AtributosNovosUsr);		 
		 
		 /*inserir o usuario no campo que foi especificado, no caso Aluno*/
		 //pegando os campos para aluno
		 String[] AtributosNovosAl = new String[4];
		 AtributosNovosAl[0] = txtCPF.getText();
		 AtributosNovosAl[1] = txtCidadeOrigem.getText();
		 AtributosNovosAl[2] = txtEndereco.getText();
		 AtributosNovosAl[3] = txtCodCurso.getText();
		 
		 String[] NomeColunaAl = new String[4];
		 NomeColunaAl[0] = "CPFALUNO";
		 NomeColunaAl[1] = "CIDADEORIGEMALUNO";
		 NomeColunaAl[2] = "ENDERECOALUNO";
		 NomeColunaAl[3] = "CODCURSOALUNO";
		 
		 inter.inserir(NomeTabelaAl,NomeColunaAl,AtributosNovosAl);
		 dispose();
	}


}
