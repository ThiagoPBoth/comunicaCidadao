/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.tablemodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelDominio.Usuario;

/**
 *
 * @author joao.pedro_combateaf
 */
public class UsuarioTableModel extends AbstractTableModel {
    
    private ArrayList<Usuario> listaUsuarios;
    
    public UsuarioTableModel(ArrayList<Usuario> listaUsuarios) {
        
        this.listaUsuarios = listaUsuarios;
    }
    
    @Override
    public int getRowCount() {

        return listaUsuarios.size();

    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Usuario usuario = listaUsuarios.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return usuario.getIdUsuario();
            case 1:
                return usuario.getNome();
            case 2:
                return usuario.getTelefone();
            case 3:
                return usuario.getCpf();
            case 4:
                String statusReal = null;
                if (usuario.getStatus() == 1) {
                    return "Ativo";
                } else if (usuario.getStatus() == 2) {
                    return "Inativo";
                }
            default:
                return "";
        }
        
    }
    
    
        public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID do Usuario";
            case 1:
                    return "Nome";
            case 2:
                return "Telefone";
            case 3:
                return "CPF";
            case 4:
                return "Status";
            default:
               return "NoName";
        }
    }
    
   public Usuario getListaUsuario(int row){
       return listaUsuarios.get(row);
       
   } 
        
}
    
    

