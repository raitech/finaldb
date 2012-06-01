package Operacoes;


import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


public class ModeloTabela extends DefaultTableModel{
    private String[] colunas;  
    private Object[][] conteudo;
    
    
    // Construtor
    public ModeloTabela(){
    	super();
    }
    
    public ModeloTabela(String[] colunas, Object[][] conteudo){
        this.colunas = colunas;
        this.conteudo = conteudo;
    }

      @Override
    public int getRowCount() {
        return conteudo.length;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return conteudo[rowIndex][columnIndex];
    }
    
    @Override
    public String getColumnName(int col){  
        return colunas[col];  
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
    
    @Override
     public void setValueAt(Object value, int row, int col) {  
         conteudo[row][col] = value;  
         fireTableCellUpdated(row, col);  
     }  
}
