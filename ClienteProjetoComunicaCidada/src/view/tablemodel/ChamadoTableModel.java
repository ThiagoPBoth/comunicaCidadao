/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.tablemodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelDominio.Chamado;
/**
 *
 * @author joao.pedro_combateaf
 */
public class ChamadoTableModel extends AbstractTableModel{
    
    private ArrayList<Chamado> listaChamados;
    
    public ChamadoTableModel(ArrayList<Chamado> listaChamados) {
        
        this.listaChamados = listaChamados;
    }
    
    public int getRowCount() {
        
        return listaChamados.size();
    
    }

    public int getColumnCount() {
        return 5;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
                Chamado chamado = listaChamados.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return chamado.getIdChamado();
            case 1:
                return chamado.getNome();
            case 2:
                return chamado.getTitulo();
            case 3:
                return chamado.getSetor();
            case 4:
                String st = null;
                if (chamado.getStatus() == 0) {
                    st = "Não solucionado";
                } else if (chamado.getStatus() == 1) {
                    st = "Em processo para solucionar";   
                } else if (chamado.getStatus() == 2) {
                    st = "Chamado solucionado";
                }
                return st;
            default:
                return "";
        }
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID do Chamado";
            case 1:
                    return "Nome do cliente";
            case 2:
                return "Título";
            case 3:
                return "Setor";
            case 4:
                return "Status";
            default:
               return "NoName";
        }
    }
    
   public Chamado getListaChamado(int row){
       return listaChamados.get(row);
       
   } 
}
