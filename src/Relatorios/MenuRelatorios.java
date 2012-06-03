package Relatorios;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import Operacoes.Tela;


public class MenuRelatorios extends javax.swing.JFrame {
	private JRadioButton Cursos;
	private JRadioButton Repetentes;
	private JRadioButton PreRequisitos;
	private JButton btnVoltar;
	private JButton btnOK;
	private JRadioButton Referencias;
	
	public MenuRelatorios() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Relatorios");
			{
				Cursos = new JRadioButton();
				getContentPane().add(Cursos);
				Cursos.setText("Alunos matriculados nos cursos");
				Cursos.setBounds(57, 33, 250, 20);
			}
			{
				Repetentes = new JRadioButton();
				getContentPane().add(Repetentes);
				Repetentes.setText("Alunos repetentes");
				Repetentes.setBounds(57, 68, 250, 20);
			}
			{
				PreRequisitos = new JRadioButton();
				getContentPane().add(PreRequisitos);
				PreRequisitos.setText("Disciplinas pre-requisitos");
				PreRequisitos.setBounds(57, 102, 250, 20);
			}
			{
				Referencias = new JRadioButton();
				getContentPane().add(Referencias);
				Referencias.setText("Referências das disciplinas");
				Referencias.setBounds(57, 137, 250, 20);
			}
			{
				btnOK = new JButton();
				getContentPane().add(btnOK);
				btnOK.setText("OK");
				btnOK.setBounds(239, 186, 90, 25);
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnOKActionPerformed(evt);
					}
				});
			}
			{
				btnVoltar = new JButton();
				getContentPane().add(btnVoltar);
				btnVoltar.setText("Voltar");
				btnVoltar.setBounds(23, 186, 90, 25);
				btnVoltar.setSize(90, 25);
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnVoltarActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(376, 278);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void btnVoltarActionPerformed(ActionEvent evt) {
		dispose();
	}
	
	private void btnOKActionPerformed(ActionEvent evt) {

		if(Cursos.isSelected())
		{
			Relatorios rel = new Relatorios("v_lista_cursos");
	        rel.show();   
		}
		if(Repetentes.isSelected())
		{
			Relatorios rel = new Relatorios("v_repetentes");
	        rel.show();   
		}
		if(PreRequisitos.isSelected())
		{
			Relatorios rel = new Relatorios("v_requisitos");
	        rel.show();   
		}
		if(Referencias.isSelected())
		{
			Relatorios rel = new Relatorios("v_disciplinas_referencias");
	        rel.show();   	
		}
	}
}
