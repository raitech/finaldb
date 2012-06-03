package Operacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Insere_Aula_Pratica extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JButton btnInserirAula;
	private JTextField txtdataAP;
	private JTextField txtDisciplina;
	private String NomeTabela = new String("L11_AULAPRATICA");
	

	public Insere_Aula_Pratica() {
		super();
		initGUI();
		setTitle("Inserir Aula Pratica");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Sigla disciplina:");
				jLabel1.setBounds(26, 36, 103, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Data da aula pratica:");
				jLabel2.setBounds(26, 65, 107, 16);
			}
			{
				txtDisciplina = new JTextField();
				getContentPane().add(txtDisciplina);
				txtDisciplina.setBounds(141, 33, 198, 23);
			}
			{
				txtdataAP = new JTextField();
				getContentPane().add(txtdataAP);
				txtdataAP.setBounds(141, 62, 198, 23);
			}
			{
				btnInserirAula = new JButton();
				getContentPane().add(btnInserirAula);
				btnInserirAula.setText("INSERIR");
				btnInserirAula.setBounds(249, 92, 90, 25);
				btnInserirAula.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnInserirAulaActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(400, 166);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void btnInserirAulaActionPerformed(ActionEvent evt) {
		String[] AtributosNovos = new String[2];
		AtributosNovos[0] = txtDisciplina.getText();
		AtributosNovos[1] = txtdataAP.getText();
		
		String[] NomeColuna = new String[2];
		 NomeColuna[0] = "SIGLADISCIPLINAAP";
		 NomeColuna[1] = "DATAAP";
		
		Trab_final.Interface inter = new Trab_final.Interface();
		inter.inserir(NomeTabela, NomeColuna, AtributosNovos);
		
		dispose();
	}

}
