package Relatorios;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import Operacoes.Tela;


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
@SuppressWarnings({ "serial", "unused" })
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
			this.setTitle("Relatórios");
			{
				Cursos = new JRadioButton();
				getContentPane().add(Cursos);
				Cursos.setText("Alunos matriculados nos cursos");
				Cursos.setBounds(57, 33, 186, 20);
			}
			{
				Repetentes = new JRadioButton();
				getContentPane().add(Repetentes);
				Repetentes.setText("Alunos repetentes");
				Repetentes.setBounds(57, 68, 113, 20);
			}
			{
				PreRequisitos = new JRadioButton();
				getContentPane().add(PreRequisitos);
				PreRequisitos.setText("Disciplinas pré-requisitos");
				PreRequisitos.setBounds(57, 102, 150, 20);
			}
			{
				Referencias = new JRadioButton();
				getContentPane().add(Referencias);
				Referencias.setText("Referências das disciplinas");
				Referencias.setBounds(57, 137, 160, 20);
			}
			{
				btnOK = new JButton();
				getContentPane().add(btnOK);
				btnOK.setText("OK");
				btnOK.setBounds(194, 186, 90, 25);
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
			this.setSize(328, 278);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	private void btnVoltarActionPerformed(ActionEvent evt) {
		dispose();
	}
	
	private void btnOKActionPerformed(ActionEvent evt) {

		if(Cursos.isSelected())
		{
			Relatorios rel = new Relatorios();
	        rel.show();   
		}
		if(Repetentes.isSelected())
		{
			Relatorios rel = new Relatorios();
	        rel.show();   
		}
		if(PreRequisitos.isSelected())
		{
			Relatorios rel = new Relatorios();
	        rel.show();   
		}
		if(Referencias.isSelected())
		{
			Relatorios rel = new Relatorios();
	        rel.show();   	
		}
	}
}
