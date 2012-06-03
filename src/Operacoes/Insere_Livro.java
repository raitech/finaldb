package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


public class Insere_Livro extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JButton btnVoltar;
	private JButton btnOK;
	private JTextField txtEditora;
	private JTextField txtCod;
	private String NomeTabela = new String("L15_LIVRO");

	public Insere_Livro() {
		super();
		initGUI();
		setTitle("Inserir Livro");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Codigo:");
				jLabel1.setBounds(24, 33, 63, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Editora:");
				jLabel2.setBounds(24, 61, 40, 16);
			}
			{
				txtCod = new JTextField();
				getContentPane().add(txtCod);
				txtCod.setBounds(75, 30, 277, 23);
			}
			{
				txtEditora = new JTextField();
				getContentPane().add(txtEditora);
				txtEditora.setBounds(76, 58, 276, 23);
			}
			{
				btnOK = new JButton();
				getContentPane().add(btnOK);
				btnOK.setText("OK");
				btnOK.setBounds(262, 109, 90, 25);
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
				btnVoltar.setBounds(28, 109, 58, 23);
				btnVoltar.setSize(90, 25);
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnVoltarActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(400, 191);
		} catch (Exception e) {
		    			e.printStackTrace();
		}
	}
	
	private void btnOKActionPerformed(ActionEvent evt) {
		String[] AtributosNovos = new String[2];
		AtributosNovos[0] = txtCod.getText();
		AtributosNovos[1] = txtEditora.getText();
		
		String[] NomeColuna = new String[2];
		 NomeColuna[0] = "CODLIVRO";
		 NomeColuna[1] = "EDITORA";
		
		
		Trab_final.Interface inter = new Trab_final.Interface();
		inter.inserir(NomeTabela, NomeColuna, AtributosNovos);
	}
	
	private void btnVoltarActionPerformed(ActionEvent evt) {
		dispose();
	}

}
