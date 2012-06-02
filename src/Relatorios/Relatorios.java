package Relatorios;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
public class Relatorios extends javax.swing.JFrame {
	private JButton btnVoltar;
	private JTable TabRelatorio;
	
	public Relatorios() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				btnVoltar = new JButton();
				getContentPane().add(btnVoltar);
				btnVoltar.setText("VOLTAR");
				btnVoltar.setBounds(194, 326, 90, 25);
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnVoltarActionPerformed(evt);
					}
				});

			}
			{
				TableModel TabRelatorioModel = 
						new DefaultTableModel(
								new String[][] { { "One", "Two" }, { "Three", "Four" } },
								new String[] { "Column 1", "Column 2" });
				TabRelatorio = new JTable();
				GridLayout TabRelatorioLayout = new GridLayout(1, 1);
				TabRelatorioLayout.setColumns(1);
				TabRelatorioLayout.setHgap(5);
				TabRelatorioLayout.setVgap(5);
				getContentPane().add(TabRelatorio);
				TabRelatorio.setModel(TabRelatorioModel);
				TabRelatorio.setLayout(null);
				TabRelatorio.setBounds(0, 0, 484, 317);
			}
			pack();
			setSize(500, 400);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnVoltarActionPerformed(ActionEvent evt) {
		dispose();
	}
}
