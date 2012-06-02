package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

public class Insere_Aula_equipamento extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextField txtCodEqui;
	private JTextField txtSiglaDisc;
	private JTextField txtData;
	private JButton btnInserirAE;
	private String NomeTabela = new String("L13_USA_AULAEQUIPAMENTO");

	public Insere_Aula_equipamento() {
		super();
		initGUI();
		setTitle("Inserir Aula com equipamento");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Código do equipamento:");
				jLabel1.setBounds(27, 19, 133, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Sigla disciplina:");
				jLabel2.setBounds(27, 48, 92, 16);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Data:");
				jLabel3.setBounds(27, 77, 40, 16);
			}
			{
				txtCodEqui = new JTextField();
				getContentPane().add(txtCodEqui);
				txtCodEqui.setBounds(167, 16, 182, 23);
			}
			{
				txtSiglaDisc = new JTextField();
				getContentPane().add(txtSiglaDisc);
				txtSiglaDisc.setBounds(167, 45, 182, 23);
			}
			{
				txtData = new JTextField();
				getContentPane().add(txtData);
				txtData.setBounds(167, 74, 182, 23);
			}
			{
				btnInserirAE = new JButton();
				getContentPane().add(btnInserirAE);
				btnInserirAE.setText("INSERIR");
				btnInserirAE.setBounds(259, 103, 90, 25);
				btnInserirAE.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnInserirAEActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(400, 172);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnInserirAEActionPerformed(ActionEvent evt) {
		String[] AtributosNovos = new String[3];
		AtributosNovos[0] = txtCodEqui.getText();
		AtributosNovos[1] = txtSiglaDisc.getText();	
		AtributosNovos[2] = txtData.getText();
		
		String[] NomeColuna = new String[3];
		 NomeColuna[0] = "CODEQUIPAMENTO_USA";
		 NomeColuna[1] = "SIGLADISCAP_USA";
		 NomeColuna[2] = "DATAAP_USA";
		 
		Trab_final.Interface inter = new Trab_final.Interface();
		inter.inserir(NomeTabela, NomeColuna, AtributosNovos);
		dispose();
		dispose();
	}

}
