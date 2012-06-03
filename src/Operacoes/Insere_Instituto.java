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

public class Insere_Instituto extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton btnInsereInst;
	private JTextField txtCidadeInstituto;
	private JTextField txtSiglaInstituto;
	private JTextField txtNomeInstituto;
	private String NomeTabela = new String("L06_INSTITUTO");
	

		
	public Insere_Instituto() {
		super();
		initGUI();
		setTitle("Inserir Instituto");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Nome Instituto:");
				jLabel1.setBounds(12, 61, 85, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Sigla instituto:");
				jLabel2.setBounds(12, 91, 77, 16);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Cidade:");
				jLabel3.setBounds(17, 119, 59, 16);
			}
			{
				btnInsereInst = new JButton();
				getContentPane().add(btnInsereInst);
				btnInsereInst.setText("INSERIR");
				btnInsereInst.setBounds(276, 178, 90, 25);
				btnInsereInst.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnInsereInstActionPerformed(evt);
					}
				});
			}
			{
				txtNomeInstituto = new JTextField();
				getContentPane().add(txtNomeInstituto);
				txtNomeInstituto.setBounds(101, 58, 265, 23);
			}
			{
				txtSiglaInstituto = new JTextField();
				getContentPane().add(txtSiglaInstituto);
				txtSiglaInstituto.setBounds(101, 88, 265, 23);
			}
			{
				txtCidadeInstituto = new JTextField();
				getContentPane().add(txtCidadeInstituto);
				txtCidadeInstituto.setBounds(101, 116, 265, 23);
			}
			pack();
			this.setSize(400, 248);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnInsereInstActionPerformed(ActionEvent evt) {
		String[] AtributosNovos = new String[3];
		AtributosNovos[0] = txtSiglaInstituto.getText();
		AtributosNovos[1] = txtNomeInstituto.getText();
		AtributosNovos[2] = txtCidadeInstituto.getText();
		
		String[] NomeColuna = new String[3];
		 NomeColuna[0] = "SIGLAINSTITUTO";
		 NomeColuna[1] = "NOMEINSTITUTO";
		 NomeColuna[2] = "CIDADEINSTITUTO";
		
		Trab_final.Interface inter = new Trab_final.Interface();
		inter.inserir(NomeTabela, NomeColuna, AtributosNovos);
				 
		dispose();
	}

}
