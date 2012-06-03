package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

public class Insere_Artigo extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JTextField txtCod;
	private JLabel jLabel2;
	private JTextField txtURL;
	private JLabel jLabel3;
	private JButton btnVoltar;
	private JButton btnOK;
	private JTextField txtConf;
	private String NomeTabela = new String("L16_ARTIGO");

	public Insere_Artigo() {
		super();
		initGUI();
		setTitle("Inserir Artigo");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Codigo:");
				jLabel1.setBounds(27, 27, 42, 16);
			}
			{
				txtCod = new JTextField();
				getContentPane().add(txtCod);
				txtCod.setBounds(75, 24, 273, 23);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("URL:");
				jLabel2.setBounds(27, 60, 24, 16);
			}
			{
				txtURL = new JTextField();
				getContentPane().add(txtURL);
				txtURL.setBounds(75, 57, 273, 23);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Conferência:");
				jLabel3.setBounds(27, 88, 80, 16);
			}
			{
				txtConf = new JTextField();
				getContentPane().add(txtConf);
				txtConf.setBounds(101, 85, 247, 23);
			}
			{
				btnOK = new JButton();
				getContentPane().add(btnOK);
				btnOK.setText("OK");
				btnOK.setBounds(258, 131, 90, 25);
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnOKActionPerformed(evt);
					}
				});
			}
			{
				btnVoltar = new JButton();
				getContentPane().add(btnVoltar);
				btnVoltar.setText("VOLTAR");
				btnVoltar.setBounds(27, 131, 90, 25);
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnVoltarActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(400, 206);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void btnOKActionPerformed(ActionEvent evt) {
		String[] AtributosNovos = new String[3];
		AtributosNovos[0] = txtCod.getText();
		AtributosNovos[1] = txtURL.getText();
		AtributosNovos[2] = txtConf.getText();
		
		String[] NomeColuna = new String[3];
		 NomeColuna[0] = "CODARTIGO";
		 NomeColuna[1] = "URL";
		 NomeColuna[2] = "CONFERENCIA";
		
		
		Trab_final.Interface inter = new Trab_final.Interface();
		inter.inserir(NomeTabela, NomeColuna, AtributosNovos);
	}
	
	private void btnVoltarActionPerformed(ActionEvent evt) {
		dispose();
	}

}
