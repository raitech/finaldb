package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


public class Insere_PossuiCursoDisciplina extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField txtSiglaDisc;
	private JButton txtInserirDiscInst;
	private JTextField txtInstituto;
	private String NomeTabela = new String("L10_POSSUI_CURSOINSTITUTO");

	
	public Insere_PossuiCursoDisciplina() {
		super();
		initGUI();
		setTitle("Inserir Instituto da Disciplina");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Sigla disciplina:");
				jLabel1.setBounds(39, 35, 90, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Instituto:");
				jLabel2.setBounds(39, 63, 68, 16);
			}
			{
				txtSiglaDisc = new JTextField();
				getContentPane().add(txtSiglaDisc);
				txtSiglaDisc.setBounds(128, 32, 190, 23);
			}
			{
				txtInstituto = new JTextField();
				getContentPane().add(txtInstituto);
				txtInstituto.setBounds(128, 60, 190, 23);
			}
			{
				txtInserirDiscInst = new JButton();
				getContentPane().add(txtInserirDiscInst);
				txtInserirDiscInst.setText("INSERIR");
				txtInserirDiscInst.setBounds(228, 95, 90, 25);
				txtInserirDiscInst.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						txtInserirDiscInstActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(368, 174);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	private void txtInserirDiscInstActionPerformed(ActionEvent evt) {
		String[] AtributosNovos = new String[2];
		AtributosNovos[0] = txtSiglaDisc.getText();
		AtributosNovos[1] = txtInstituto.getText();
		
		String[] NomeColuna = new String[2];
		 NomeColuna[0] = "CODCURSOP";
		 NomeColuna[1] = "SIGINSTITUTOP";
		
		Trab_final.Interface inter = new Trab_final.Interface();
		inter.inserir(NomeTabela, NomeColuna, AtributosNovos);
		dispose();
	}

}
