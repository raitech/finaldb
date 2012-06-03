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
public class Insere_Ref_Disc extends javax.swing.JFrame {
	private JButton btnInseirPreRequisito;
	private JTextField txtCodRed;
	private JLabel jLabel2;
	private JTextField txtSiglaDisc;
	private JLabel jLabel1;
	
	public Insere_Ref_Disc() {
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
				jLabel1.setText("Código referencia:");
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
		dispose();
	}
}
