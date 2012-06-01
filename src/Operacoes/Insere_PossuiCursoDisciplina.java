package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Insere_PossuiCursoDisciplina extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField txtSiglaDisc;
	private JButton txtInserirDiscInst;
	private JTextField txtInstituto;

	
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
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void txtInserirDiscInstActionPerformed(ActionEvent evt) {
		dispose();
	}

}
