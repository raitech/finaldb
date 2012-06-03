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
public class Insere_Professor extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField txtCodCor;
	private JTextField txtCategoria;
	private JTextField txtTitulacao;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JButton btnInsereProf;
	private JTextField txtDataNasc;
	private JTextField txtNomeUser;
	private JTextField txtCPF;
	private JTextField txtNomeCompleto;
	private JTextField txtRG;
	private JTextField txtSenha;
	private JLabel jLabel6;
	private String NomeTabelaProf = new String("L04_PROFESSOR");
	private String NomeTabelaUsr = new String("L01_USUARIO");

	public Insere_Professor() {
		super();
		initGUI();
		setTitle("Inserir Professor");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("CPF: ");
				jLabel1.setBounds(31, 52, 129, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("RG:");
				jLabel2.setBounds(31, 81, 123, 16);
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
				jLabel4.setText("Nome usuario:");
				jLabel4.setBounds(31, 110, 129, 16);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Senha:");
				jLabel5.setBounds(239, 110, 50, 16);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Nome completo:");
				jLabel6.setBounds(31, 23, 135, 16);
			}
			{
				txtSenha = new JTextField();
				getContentPane().add(txtSenha);
				txtSenha.setBounds(294, 107, 91, 23);
			}
			{
				txtRG = new JTextField();
				getContentPane().add(txtRG);
				txtRG.setBounds(166, 78, 219, 23);
			}
			{
				txtNomeCompleto = new JTextField();
				getContentPane().add(txtNomeCompleto);
				txtNomeCompleto.setBounds(166, 20, 219, 23);
			}
			{
				txtCPF = new JTextField();
				getContentPane().add(txtCPF);
				txtCPF.setBounds(166, 49, 219, 23);
			}
			{
				txtNomeUser = new JTextField();
				getContentPane().add(txtNomeUser);
				txtNomeUser.setBounds(166, 107, 68, 23);
			}
			{
				txtDataNasc = new JTextField();
				getContentPane().add(txtDataNasc);
				txtDataNasc.setBounds(166, 136, 218, 23);
			}
			
			{
				btnInsereProf = new JButton();
				getContentPane().add(btnInsereProf);
				btnInsereProf.setText("INSERIR");
				btnInsereProf.setBounds(290, 255, 90, 25);
				btnInsereProf.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnInsereAlunoActionPerformed(evt);
					}
				});
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("Titulacao");
				jLabel7.setBounds(30, 167, 57, 16);
			}
			{
				jLabel8 = new JLabel();
				getContentPane().add(jLabel8);
				jLabel8.setText("Categoria:");
				jLabel8.setBounds(30, 195, 70, 16);
			}
			{
				jLabel9 = new JLabel();
				getContentPane().add(jLabel9);
				jLabel9.setText("Codigo cordenador:");
				jLabel9.setBounds(30, 223, 122, 16);
			}
			{
				txtTitulacao = new JTextField();
				getContentPane().add(txtTitulacao);
				txtTitulacao.setBounds(166, 164, 218, 23);
			}
			{
				txtCategoria = new JTextField();
				getContentPane().add(txtCategoria);
				txtCategoria.setBounds(166, 192, 218, 23);
			}
			{
				txtCodCor = new JTextField();
				getContentPane().add(txtCodCor);
				txtCodCor.setBounds(168, 220, 216, 23);
			}
			pack();
			this.setSize(434, 330);
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
		 AtributosNovosUsr[6] =  "professor";
		 
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
		 
		 /*inserir o usuario no campo que foi especificado, no caso Professor*/
		 //pegando os campos para professor
		 String[] AtributosNovosAl = new String[4];
		 AtributosNovosAl[0] = txtCPF.getText();
		 AtributosNovosAl[1] = txtCodCor.getText();
		 AtributosNovosAl[2] = txtCategoria.getText();
		 AtributosNovosAl[3] = txtTitulacao.getText();
		 
		 String[] NomeColunaAl = new String[4];
		 NomeColunaAl[0] = "CPFPROFESSOR";
		 NomeColunaAl[1] = "TITULACAOPROFESSOR";
		 NomeColunaAl[2] = "CATEGORIAPROFESSOR";
		 NomeColunaAl[3] = "CODCURSOCOORDENADOR";
		 
		 inter.inserir(NomeTabelaProf,NomeColunaAl,AtributosNovosAl);
		dispose();
	}

}
