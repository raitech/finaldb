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
public class Insere_Equipamento extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField txtEquip;
	private JTextField txtCodEquip;
	private JButton btnInsereEqui;
	private String NomeTabela = new String("L12_EQUIPAMENTO");

	public Insere_Equipamento() {
		super();
		initGUI();
		setTitle("Inserir Equipamento");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Código:");
				jLabel1.setBounds(32, 34, 53, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Equipamento");
				jLabel2.setBounds(32, 62, 71, 16);
			}
			{
				btnInsereEqui = new JButton();
				getContentPane().add(btnInsereEqui);
				btnInsereEqui.setText("INSERIR");
				btnInsereEqui.setBounds(283, 118, 90, 25);
				btnInsereEqui.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnInsereEquiActionPerformed(evt);
					}
				});
			}
			{
				txtCodEquip = new JTextField();
				getContentPane().add(txtCodEquip);
				txtCodEquip.setBounds(77, 31, 253, 23);
			}
			{
				txtEquip = new JTextField();
				getContentPane().add(txtEquip);
				txtEquip.setBounds(109, 59, 221, 23);
			}
			pack();
			this.setSize(400, 188);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnInsereEquiActionPerformed(ActionEvent evt) {
		String[] AtributosNovos = new String[2];
		AtributosNovos[0] = txtCodEquip.getText();
		AtributosNovos[1] = txtEquip.getText();
		
		String[] NomeColuna = new String[2];
		 NomeColuna[0] = "CODEQUIPAMENTO";
		 NomeColuna[1] = "NOMEEQUIPAMENTO";
		
		Trab_final.Interface inter = new Trab_final.Interface();
		inter.inserir(NomeTabela, NomeColuna, AtributosNovos);
		dispose();
	}

}
