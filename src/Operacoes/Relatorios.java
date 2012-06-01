package Operacoes;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTable;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;


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

}
