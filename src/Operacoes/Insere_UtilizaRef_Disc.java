package Operacoes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


public class Insere_UtilizaRef_Disc extends javax.swing.JFrame {
	private JButton btnInseirPreRequisito;
	private JTextField txtCodRed;
	private JLabel jLabel2;
	private JTextField txtSiglaDisc;
	private JLabel jLabel1;
	private String NomeTabela = new String("L17_UTILIZA_DISCREFERENCIA");
	
	public Insere_UtilizaRef_Disc() {
		super();
		initGUI();
		setTitle("Inserir Referencia para disciplina");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				btnInseirPreRequisito = new JButton();
				getContentPane().add(btnInseirPreRequisito);
				btnInseirPreRequisito.setText("INSERIR");
				btnInseirPreRequisito.setBounds(256, 106, 90, 25);
				btnInseirPreRequisito.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnInseirPreRequisitoActionPerformed(evt);
					}
				});
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Codigo referencia:");
				jLabel1.setBounds(37, 39, 111, 16);
			}
			{
				txtCodRed = new JTextField();
				getContentPane().add(txtCodRed);
				txtCodRed.setBounds(166, 36, 180, 23);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Sigla disciplina:");
				jLabel2.setBounds(37, 68, 129, 16);
			}
			{
				txtSiglaDisc = new JTextField();
				getContentPane().add(txtSiglaDisc);
				txtSiglaDisc.setBounds(166, 65, 180, 23);
			}
			pack();
			this.setSize(374, 181);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnInseirPreRequisitoActionPerformed(ActionEvent evt) {
		String[] AtributosNovos = new String[2];
		AtributosNovos[0] = txtCodRed.getText();
		AtributosNovos[1] = txtSiglaDisc.getText();
		
		String[] NomeColuna = new String[2];
		 NomeColuna[0] = "CODREFERENCIA_UTIL";
		 NomeColuna[1] = "SIGDISCIPLINA_UTIL";
		
		
		Trab_final.Interface inter = new Trab_final.Interface();
		inter.inserir(NomeTabela, NomeColuna, AtributosNovos);
		dispose();
	}
}
