package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


public class Insere_Pre_requisito extends javax.swing.JFrame {
	private JButton btnInseirPreRequisito;
	private JTextField txtSiglaDisPossuiPre;
	private JLabel jLabel2;
	private JTextField txtPreRequi;
	private JLabel jLabel1;
	private String NomeTabela = new String("L08_PREREQUISITO");
	
	public Insere_Pre_requisito() {
		super();
		initGUI();
		setTitle("Inserir Pre-requisito");
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
				jLabel1.setText("Sigla disciplina:");
				jLabel1.setBounds(37, 39, 111, 16);
			}
			{
				txtSiglaDisPossuiPre = new JTextField();
				getContentPane().add(txtSiglaDisPossuiPre);
				txtSiglaDisPossuiPre.setBounds(166, 36, 180, 23);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Sigla do pre-requisito:");
				jLabel2.setBounds(37, 68, 129, 16);
			}
			{
				txtPreRequi = new JTextField();
				getContentPane().add(txtPreRequi);
				txtPreRequi.setBounds(166, 65, 180, 23);
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
		AtributosNovos[0] = txtSiglaDisPossuiPre.getText();
		AtributosNovos[1] = txtPreRequi.getText();		
		
		String[] NomeColuna = new String[2];
		 NomeColuna[0] = "SIGLADISC";
		 NomeColuna[1] = "SIGLAREQ";
		 
		Trab_final.Interface inter = new Trab_final.Interface();
		inter.inserir(NomeTabela, NomeColuna, AtributosNovos);
		dispose(); 
	}

}
