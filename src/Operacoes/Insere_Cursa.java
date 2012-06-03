package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

public class Insere_Cursa extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton InserirCursa;
	private JTextField txtPorPrese;
	private JTextField txtSemeAno;
	private JTextField txtNota;
	private JTextField txtFreq;
	private JTextField txtSiglaDiscCursa;
	private JTextField txtCPFCurso;
	private JLabel jLabel6;
	private String NomeTabela = new String("L09_CURSA");

	
	public Insere_Cursa() {
		super();
		initGUI();
		setTitle("Inserir Cursista");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("CPF aluno:");
				jLabel1.setBounds(27, 24, 70, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Sigla disciplina:");
				jLabel2.setBounds(28, 52, 81, 16);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Semestre e Ano (SS/AAAA) :");
				jLabel3.setBounds(109, 108, 157, 16);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Nota ");
				jLabel4.setBounds(28, 108, 29, 16);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Frequencia");
				jLabel5.setBounds(28, 80, 58, 16);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Porcentagem presenca");
				jLabel6.setBounds(28, 137, 121, 16);
			}
			{
				txtCPFCurso = new JTextField();
				getContentPane().add(txtCPFCurso);
				txtCPFCurso.setBounds(118, 21, 199, 23);
			}
			{
				txtSiglaDiscCursa = new JTextField();
				getContentPane().add(txtSiglaDiscCursa);
				txtSiglaDiscCursa.setBounds(118, 49, 199, 23);
			}
			{
				txtFreq = new JTextField();
				getContentPane().add(txtFreq);
				txtFreq.setBounds(118, 77, 199, 23);
			}
			{
				txtNota = new JTextField();
				getContentPane().add(txtNota);
				txtNota.setBounds(57, 105, 49, 23);
			}
			{
				txtSemeAno = new JTextField();
				getContentPane().add(txtSemeAno);
				txtSemeAno.setBounds(273, 105, 44, 23);
			}
			{
				txtPorPrese = new JTextField();
				getContentPane().add(txtPorPrese);
				txtPorPrese.setBounds(155, 134, 162, 23);
			}
			{
				InserirCursa = new JButton();
				getContentPane().add(InserirCursa);
				InserirCursa.setText("INSERIR");
				InserirCursa.setBounds(227, 163, 90, 25);
				InserirCursa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						InserirCursaActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(353, 234);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	private void InserirCursaActionPerformed(ActionEvent evt) {
		/*Pegando os atributos para user:*/
		 String[] AtributosNovos = new String[6];
		 AtributosNovos[0] = txtCPFCurso.getText();
		 AtributosNovos[1] = txtSiglaDiscCursa.getText();
		 AtributosNovos[2] = txtSemeAno.getText();
		 AtributosNovos[3] =  txtNota.getText();
		 AtributosNovos[4] =  txtFreq.getText();
		 AtributosNovos[5] =  txtPorPrese.getText();
		 
		 String[] NomeColuna = new String[6];		 
		 NomeColuna[0] = "CPFCURSA";
		 NomeColuna[1] = "SIGLADISCIPLINACURSA";
		 NomeColuna[2] = "ANOSEMESTRECURSA";
		 NomeColuna[3] = "NOTACURSA";
		 NomeColuna[4] = "NROAULASFREQCURSA";
		 NomeColuna[5] = "PORCENTPRESENCACURSA";
		 
		 /*inserir primeiro em usuarios*/
		 Trab_final.Interface inter = new Trab_final.Interface();
		 inter.inserir(NomeTabela, NomeColuna, AtributosNovos);	
		dispose();
	}

}
