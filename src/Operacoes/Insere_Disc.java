package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

public class Insere_Disc extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton btnOKInsDic;
	private JTextField txtPrograma;
	private JTextField txtMetodoAva;
	private JTextField txtCPFprof;
	private JTextField txtSiglaIns;
	private JTextField txtCreditos;
	private JTextField txtQtdAulas;
	private JTextField txtAno;
	private JTextField txtSemestre;
	private JTextField txtNomeDisc;
	private JTextField txtSiglaDisc;
	private JLabel jLabel10;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private String NomeTabela = new String("L07_DISCIPLINA");

	public Insere_Disc() {
		super();
		initGUI();
		setTitle("Inserir Disciplina");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Sigla Disciplina");
				jLabel1.setBounds(19, 25, 79, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Nome Disciplina");
				jLabel2.setBounds(19, 53, 87, 16);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Semestre");
				jLabel3.setBounds(19, 81, 48, 16);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Ano");
				jLabel4.setBounds(221, 84, 22, 16);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Quantidade de aulas");
				jLabel5.setBounds(324, 84, 108, 16);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Creditos");
				jLabel6.setBounds(19, 113, 44, 16);
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("Sigla instituto");
				jLabel7.setBounds(221, 113, 72, 16);
			}
			{
				jLabel8 = new JLabel();
				getContentPane().add(jLabel8);
				jLabel8.setText("CPF Professor");
				jLabel8.setBounds(19, 141, 73, 16);
			}
			{
				jLabel9 = new JLabel();
				getContentPane().add(jLabel9);
				jLabel9.setText("Metodo Avaliacao");
				jLabel9.setBounds(19, 169, 96, 16);
			}
			{
				jLabel10 = new JLabel();
				getContentPane().add(jLabel10);
				jLabel10.setText("Programa");
				jLabel10.setBounds(24, 242, 52, 16);
			}
			{
				txtSiglaDisc = new JTextField();
				getContentPane().add(txtSiglaDisc);
				txtSiglaDisc.setBounds(127, 22, 373, 23);
			}
			{
				txtNomeDisc = new JTextField();
				getContentPane().add(txtNomeDisc);
				txtNomeDisc.setBounds(127, 50, 373, 23);
			}
			{
				txtSemestre = new JTextField();
				getContentPane().add(txtSemestre);
				txtSemestre.setBounds(127, 81, 71, 23);
			}
			{
				txtAno = new JTextField();
				getContentPane().add(txtAno);
				txtAno.setBounds(255, 81, 51, 23);
			}
			{
				txtQtdAulas = new JTextField();
				getContentPane().add(txtQtdAulas);
				txtQtdAulas.setBounds(438, 81, 62, 23);
			}
			{
				txtCreditos = new JTextField();
				getContentPane().add(txtCreditos);
				txtCreditos.setBounds(127, 110, 82, 23);
			}
			{
				txtSiglaIns = new JTextField();
				getContentPane().add(txtSiglaIns);
				txtSiglaIns.setBounds(300, 110, 200, 23);
			}
			{
				txtCPFprof = new JTextField();
				getContentPane().add(txtCPFprof);
				txtCPFprof.setBounds(127, 138, 373, 23);
			}
			{
				txtMetodoAva = new JTextField();
				getContentPane().add(txtMetodoAva);
				txtMetodoAva.setBounds(127, 166, 373, 64);
			}
			{
				txtPrograma = new JTextField();
				getContentPane().add(txtPrograma);
				txtPrograma.setBounds(127, 239, 373, 132);
			}
			{
				btnOKInsDic = new JButton();
				getContentPane().add(btnOKInsDic);
				btnOKInsDic.setText("INSERIR");
				btnOKInsDic.setBounds(410, 386, 90, 25);
				btnOKInsDic.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnOKInsDicActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(534, 456);
		} catch (Exception e) {
		    			e.printStackTrace();
		}
	}
	
	private void btnOKInsDicActionPerformed(ActionEvent evt) {
		 /*Pegando os atributos para user:*/
		 String[] AtributosNovos = new String[10];
		 AtributosNovos[0] = txtSiglaDisc.getText();
		 AtributosNovos[1] = txtCPFprof.getText();
		 AtributosNovos[2] = txtSiglaIns.getText();
		 AtributosNovos[3] =  txtCreditos.getText();
		 AtributosNovos[4] =  txtQtdAulas.getText();
		 AtributosNovos[5] =  txtSemestre.getText();
		 AtributosNovos[6] =  txtAno.getText();
		 AtributosNovos[7] =  txtNomeDisc.getText();
		 AtributosNovos[8] =  txtPrograma.getText();
		 AtributosNovos[9] =  txtMetodoAva.getText();
		 
		 String[] NomeColuna = new String[10];		 
		 NomeColuna[0] = "SIGLADISC";
		 NomeColuna[1] = "CPFPROFDISC";
		 NomeColuna[2] = "SIGLAINSTITUTODISC";
		 NomeColuna[3] = "QTDCREDITOSTOTALDISC";
		 NomeColuna[4] = "QTDAULASTOTALDISC";
		 NomeColuna[5] = "SEMESTREDISC";
		 NomeColuna[6] = "ANODISC";
		 NomeColuna[7] = "NOMEDISC";
		 NomeColuna[8] = "PROGRAMADISC";
		 NomeColuna[9] = "METODOAVALDISC";
		 
		 /*inserir primeiro em usuarios*/
		 Trab_final.Interface inter = new Trab_final.Interface();
		 inter.inserir(NomeTabela, NomeColuna, AtributosNovos);		
		 dispose();
	}

}
