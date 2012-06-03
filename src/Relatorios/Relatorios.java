package Relatorios;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

import Operacoes.ModeloTabela;
import Trab_final.Interface;

public class Relatorios extends javax.swing.JFrame {
	private JButton btnVoltar;
	private JTable TabRelatorio;
	private String NomeTabela;
	private ModeloTabela modelo;
	private JTable tabela;
	private JScrollPane scpane;
	private Interface inter;
	
    private ArrayList<String> pk;
    
	public Relatorios(String NomeTabela) {
		super();
		initGUI();
		this.NomeTabela = NomeTabela;
		inter = new Interface();
		modelo = new ModeloTabela();
		pk = new ArrayList<String>();
		
		//Utilizar nome da tabela
		inter.listar(this.NomeTabela, modelo, pk);
		tabela = new JTable(modelo);
        tabela.setBounds(0, 0, 560, 440);
        
        // Torna a tabela verticalmente rolavel
        scpane = new JScrollPane(tabela);
        scpane.setBounds(10, 10, 560, 440);
        scpane.setViewportView(tabela);
        
        // Torna a tabela horizontalmente rolavel
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        add(scpane,null);
      
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
			pack();
			setSize(500, 400);
		} catch (Exception e) {
		    			e.printStackTrace();
		}
	}
	public ArrayList<String> getPK(){     	
	    	return pk;
    }
	  
	private void btnVoltarActionPerformed(ActionEvent evt) {
		dispose();
	}
	}
