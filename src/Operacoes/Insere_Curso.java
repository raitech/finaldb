package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

public class Insere_Curso extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton btnInsereCurso;
	private JTextField txtCredTotais;
	private JTextField txtQtdSemetre;
	private JTextField txtQtdMaxSemestre;
	private JTextField txtCodCurso;
	private JTextField txtNomeCurso;

	
	public Insere_Curso() {
		super();
		initGUI();
		setTitle("Inserir Curso");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Nome:");
				jLabel1.setBounds(25, 35, 76, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Codigo:");
				jLabel2.setBounds(22, 67, 89, 16);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Quantidade de semestres:");
				jLabel3.setBounds(22, 127, 155, 16);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Creditos Totais:");
				jLabel4.setBounds(22, 155, 91, 16);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Quantidade maxima de semestres:");
				jLabel5.setBounds(22, 99, 199, 16);
			}
			{
				txtNomeCurso = new JTextField();
				getContentPane().add(txtNomeCurso);
				txtNomeCurso.setBounds(81, 35, 251, 23);
			}
			{
				txtCodCurso = new JTextField();
				getContentPane().add(txtCodCurso);
				txtCodCurso.setBounds(81, 64, 251, 22);
			}
			{
				txtQtdMaxSemestre = new JTextField();
				getContentPane().add(txtQtdMaxSemestre);
				txtQtdMaxSemestre.setBounds(221, 96, 110, 23);
			}
			{
				txtQtdSemetre = new JTextField();
				getContentPane().add(txtQtdSemetre);
				txtQtdSemetre.setBounds(174, 124, 157, 23);
			}
			{
				txtCredTotais = new JTextField();
				getContentPane().add(txtCredTotais);
				txtCredTotais.setBounds(119, 152, 212, 23);
			}
			{
				btnInsereCurso = new JButton();
				getContentPane().add(btnInsereCurso);
				btnInsereCurso.setText("INSERIR");
				btnInsereCurso.setBounds(241, 226, 90, 25);
				btnInsereCurso.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnInsereCursoActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(373, 300);
		} catch (Exception e) {
		    			e.printStackTrace();
		}
	}
	
	private void btnInsereCursoActionPerformed(ActionEvent evt) {
		dispose();
	}

}
