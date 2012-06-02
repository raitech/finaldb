package Operacoes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Relatorios.MenuRelatorios;
import Relatorios.Relatorios;


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
									new String[] { 
											"L01_USUARIO",
											"L02_CURSO",
											"L03_ALUNO",
											"L04_PROFESSOR",
											"L05_ADMINISTRADOR",
											"L06_INSTITUTO",
											"L07_DISCIPLINA",
											"L08_PREREQUISITO",
											"L09_CURSA",
											"L10_POSSUI_CURSOINSTITUTO",
											"L11_AULAPRATICA",
											"L12_EQUIPAMENTO",
											"L13_USA_AULAEQUIPAMENTO",
											"L14_REFERENCIA",
											"L15_LIVRO",
											"L16_ARTIGO",
											"L17_UTILIZA_DISCREFERENCIA"											
									});
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
					btnRel.setText("RELAT�RIOS");
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
		MenuRelatorios mrel = new MenuRelatorios();
		mrel.show();
	}

}
