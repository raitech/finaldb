package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
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
public class Insere_Ref extends javax.swing.JFrame {
	private JButton btnOK;
	private JButton btnVoltar;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField txtAutor;
	private JTextField txttitulo;
	private JTextField tyxtdescricao;
	private JTextField txtQtd;
	private JTextField txtCodigo;
	private JRadioButton jRadioArtigo;
	private JRadioButton jRadioLivro;
	private JLabel jLabel6;
	private JLabel jLabel1;

	public Insere_Ref() {
		super();
		initGUI();
		setTitle("Inserir Referencia");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				btnOK = new JButton();
				getContentPane().add(btnOK);
				btnOK.setText("OK");
				btnOK.setBounds(357, 279, 90, 25);
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnOKActionPerformed(evt);
					}
				});
			}
			{
				btnVoltar = new JButton();
				getContentPane().add(btnVoltar);
				btnVoltar.setText("VOLTAR");
				btnVoltar.setBounds(21, 279, 90, 25);
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnVoltarActionPerformed(evt);
					}
				});
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Código:");
				jLabel1.setBounds(21, 27, 42, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Quantidade de páginas:");
				jLabel2.setBounds(21, 56, 125, 16);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Descrição:");
				jLabel3.setBounds(17, 141, 54, 16);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Título:");
				jLabel4.setBounds(21, 84, 34, 16);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Autor:");
				jLabel5.setBounds(22, 113, 33, 16);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Tipo referência:");
				jLabel6.setBounds(21, 202, 82, 16);
			}
			{
				jRadioLivro = new JRadioButton();
				getContentPane().add(jRadioLivro);
				jRadioLivro.setText("Livro");
				jRadioLivro.setBounds(127, 206, 44, 20);
			}
			{
				jRadioArtigo = new JRadioButton();
				getContentPane().add(jRadioArtigo);
				jRadioArtigo.setText("Artigo");
				jRadioArtigo.setBounds(127, 227, 51, 20);
			}
			{
				txtCodigo = new JTextField();
				getContentPane().add(txtCodigo);
				txtCodigo.setBounds(84, 24, 351, 23);
			}
			{
				txtQtd = new JTextField();
				getContentPane().add(txtQtd);
				txtQtd.setBounds(152, 53, 283, 23);
			}
			{
				tyxtdescricao = new JTextField();
				getContentPane().add(tyxtdescricao);
				tyxtdescricao.setBounds(83, 143, 351, 57);
			}
			{
				txttitulo = new JTextField();
				getContentPane().add(txttitulo);
				txttitulo.setBounds(84, 81, 351, 23);
			}
			{
				txtAutor = new JTextField();
				getContentPane().add(txtAutor);
				txtAutor.setBounds(84, 110, 350, 23);
			}
			pack();
			this.setSize(487, 353);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnOKActionPerformed(ActionEvent evt) {
		/*inserir na tabela referancia*/
		
		/*Chamar um artigo ou livro*/
		
		if(jRadioArtigo.isSelected()){
				new Insere_Artigo();			
		}
		
		else if(jRadioLivro.isSelected()){new Insere_Livro();}
	}
	
	private void btnVoltarActionPerformed(ActionEvent evt) {
		dispose();
	}

}
