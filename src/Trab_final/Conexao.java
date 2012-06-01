package Trab_final;

import Operacoes.Menu;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Conexao extends Frame implements ActionListener{
    
    public Conexao(){
        setTitle("CONECTAR");
        setSize(250, 200);
        setLocationRelativeTo(null);
		
		Button conectar = new Button("CONECTAR AO BD");
		Button desconectar = new Button("DESCONECTAR DO BD");
		Button sair = new Button("SAIR");
		
		conectar.addActionListener(this);
		desconectar.addActionListener(this);
		sair.addActionListener(this);
                
                conectar.setBounds(50, 40, 150, 30);
                desconectar.setBounds(50, 80, 150, 30);
                sair.setBounds(70, 130, 100, 20);

		this.add(conectar);
		this.add(desconectar);
		this.add(sair);

		
		this.add(new Label());
		show();
	}


	public void actionPerformed(ActionEvent e) {
		
		String comando = e.getActionCommand();

		if (comando.equals("SAIR")) {
			dispose();
			
		} else if (comando.equals("CONECTAR AO BD")) {
			
			try {
				Interface.loadDriver();
				System.out.println("\nCarregou o driver!!!");
			} catch (ClassNotFoundException cnfe) {
				System.out.println(cnfe + "\nNão carregou o driver!!!");
				System.exit(0);
			}
			
			try {
				Interface.connection = Interface.getConnection();
				Interface.connection.setAutoCommit(true);
				System.out.println("\nEstabeleceu conexão!!!");
                                new Menu().setVisible(true);
                                dispose();
			} catch(SQLException sqle) {
				System.out.println(sqle + "\nNão estabeleceu conexão!!!");
				System.exit(0);
			}
			
			
			
		} else if (comando.equals("DESCONECTAR DO BD")) {
			
			try {
				Interface.connection.close();
				System.out.println("\nConexão terminada!!!");
			} catch(SQLException sqle) {
				System.out.println(sqle + "\nConexão com o BD não terminada com sucesso!!!");
				System.exit(0);
			}
			
			dispose();
		}
    }
    
}