package Operacoes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



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
public class Menu extends javax.swing.JFrame {
	private JButton btnOk;
	private JButton btnSair;
	private JButton btnRel;
	private JLabel lblnome;
	private JLabel lblescolha;
	private JComboBox cbonome_tabela;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		  new Trab_final.Conexao();
	}
	
	public Menu() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("Sistema Calipso");
				{
					btnOk = new JButton();
					getContentPane().add(btnOk);
					btnOk.setText("OK");
					btnOk.setBounds(290, 219, 50, 25);
					btnOk.setSize(70, 25);
					btnOk.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnOkActionPerformed(evt);
						}
					});
				}
				{
					btnSair = new JButton();
					getContentPane().add(btnSair);
					btnSair.setText("SAIR");
					btnSair.setBounds(42, 219, 58, 25);
					btnSair.setSize(70, 25);
					btnSair.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnSairActionPerformed(evt);
						}
					});
				}
				{
					ComboBoxModel cbonome_tabelaModel = 
							new DefaultComboBoxModel(
									new String[] { "L01_USUARIO", "L02_CURSO","L06_INSTITUTO","L07_DISCIPLINA","L08_PREREQUISITO",
													"L09_CURSA","L10_POSSUI_CURSOINSTITUTO","L11_AULAPRATICA",
											       "L12_EQUIPAMENTO","L13_USA_AULAEQUIPAMENTO","L14_REFERENCIA","L17_UTILIZA_DISCREFERENCIA" });
					cbonome_tabela = new JComboBox();
					getContentPane().add(cbonome_tabela);
					cbonome_tabela.setModel(cbonome_tabelaModel);
					cbonome_tabela.setBounds(166, 113, 181, 23);
				}
				{
					lblnome = new JLabel();
					getContentPane().add(lblnome);
					lblnome.setText("Sistema Calipso");
					lblnome.setBounds(44, 11, 294, 73);
					lblnome.setFont(new java.awt.Font("Tahoma",1,36));
				}
				{
					lblescolha = new JLabel();
					getContentPane().add(lblescolha);
					lblescolha.setText("Escolha uma tabela:");
					lblescolha.setBounds(25, 116, 144, 14);
					lblescolha.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					btnRel = new JButton();
					getContentPane().add(btnRel);
					btnRel.setText("RELATÓRIOS");
					btnRel.setBounds(141, 219, 119, 25);
					btnRel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnRelActionPerformed(evt);
						}
					});
				}
			}
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void btnOkActionPerformed(ActionEvent evt) {
		System.out.println(cbonome_tabela.getSelectedItem().toString());
        Tela tela = new Tela(cbonome_tabela.getSelectedItem().toString());
        tela.show();       
	}
	
	private void btnSairActionPerformed(ActionEvent evt) {
		System.out.println("btnSair.actionPerformed, event="+evt);
				dispose();
	}
	
	private void btnRelActionPerformed(ActionEvent evt) {
		/*da um new na tabela da camila*/
	}

}
