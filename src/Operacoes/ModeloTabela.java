package Operacoes;


import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


public class ModeloTabela extends DefaultTableModel{
   
    // Construtor
    public ModeloTabela(){
    	super();
    }
       
    @Override
    public Class getColumnClass(int c){
        if(c == 0) return Boolean.class;
        else return String.class;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        if(columnIndex == 0) return true;
        else return false;
    }    
  
}
