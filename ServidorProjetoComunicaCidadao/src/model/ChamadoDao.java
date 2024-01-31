/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Colaborador
 */

import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.protocol.Resultset;
import modelDominio.Usuario;
import factory.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelDominio.Chamado;

public class ChamadoDao {
    
    private Connection con;

    public ChamadoDao() {
        con = Conector.getConnection();
    }
    
    
    public int inserir(Chamado chamado) {
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);

                String sql = "INSERT INTO chamado(fk_idUsuario, titulo, descricao, setor, statos, imagem) values (?, ?, ?, ?, ?, ?)";
                stmt = con.prepareStatement(sql);
                //tROCAR O SPARAMETROS
                stmt.setInt(1, chamado.getFkIdUsuario());
                stmt.setString(2, chamado.getTitulo());
                stmt.setString(3, chamado.getDescricao());
                stmt.setString(4, chamado.getSetor());
                stmt.setInt(5, chamado.getStatus());
                stmt.setBytes(6, chamado.getImagem());
                

                //EXECUTAR O SCRIPT
                stmt.execute();
                //EFETIVAR A TRANSAÇÃO BEM IMPORTANTE
                con.commit();
                return -1; //DEU TDO CERTO
            } catch (SQLException e) {
                try {
                    e.printStackTrace();
                    //cancela se deu erro
                    con.rollback();
                    return e.getErrorCode();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return ex.getErrorCode();
                }
            }

        } finally {

            //ENTRA AQUI INDEPENDENTE DE SEU ERRO
            try {
                stmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return e.getErrorCode();
            }
        }
    }
    
    public int alterar(Chamado chamado){
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);
                
                String sql = "update chamado set statos = ? where idChamado = ?;";
                stmt = con.prepareStatement(sql);
                
                stmt.setInt(1, chamado.getStatus());
                stmt.setInt(2, chamado.getIdChamado());
                
                stmt.execute();
                con.commit();
                
                return -1;
                
            } catch (SQLException e) {
                try {
                    
                    con.rollback();
                    e.printStackTrace();
                    return e.getErrorCode();
                    
                } catch (SQLException ex) {
                    
                    ex.printStackTrace();
                    return ex.getErrorCode();
                }
            }
        } finally {
            
            try {
                stmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return e.getErrorCode();
            }
        }
    }
            
    public ArrayList<Chamado> getListaChamados() {
        
        Statement stmt = null;
        ArrayList<Chamado> listachamados = new ArrayList<>();
        try {
            stmt = con.createStatement();
            
            
            ResultSet res = stmt.executeQuery("select chamado.*, usuario.nome from chamado inner join usuario on chamado.fk_idUsuario = usuario.idUsuario");
            //ResultSet res = stmt.executeQuery("select chamado.*, usuario.nome from chamado inner join usuario;");
           
            
            while (res.next()) {
                
                System.out.println("cheguei aqui");
                //Chamado chamado = new Chamado(res.getString("nome"), res.getInt("idChamado"), res.getString("titulo"), res.getString("setor"), res.getString("descricao"), res.getInt("statos"));
                Chamado chamado = new Chamado(res.getInt("idChamado"), res.getString("nome"), res.getString("titulo"), res.getString("setor"), res.getString("descricao"),  res.getBytes("imagem"), res.getInt("statos"));
                System.out.println(chamado);
                
                

                listachamados.add(chamado);

            }

            res.close();
            stmt.close();
            con.close();

            return listachamados;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
            return null;

        }
    }

    public ArrayList<Chamado> getListaMeusChamados(Usuario usuario) {
        
        PreparedStatement stmt = null;
        ArrayList<Chamado> listachamados = new ArrayList<>();
        try {
            
            String sql = "select chamado.*, usuario.nome from chamado inner join usuario on chamado.fk_idUsuario = usuario.idUsuario and usuario.idUsuario = (?)";
            stmt = con.prepareStatement(sql);
                //tROCAR O SPARAMETROS
            stmt.setInt(1, usuario.getIdUsuario());
            
            ResultSet res = stmt.executeQuery();
            //ResultSet res = stmt.executeQuery("select chamado.*, usuario.nome from chamado inner join usuario on chamado.fk_idUsuario = usuario.idUsuario");
            //ResultSet res = stmt.executeQuery("select chamado.*, usuario.nome from chamado inner join usuario;");
           
            
            while (res.next()) {
                
                System.out.println("cheguei aqui");
                //Chamado chamado = new Chamado(res.getString("nome"), res.getInt("idChamado"), res.getString("titulo"), res.getString("setor"), res.getString("descricao"), res.getInt("statos"));
                Chamado chamado = new Chamado(res.getInt("idChamado"), res.getString("nome"), res.getString("titulo"), res.getString("setor"), res.getString("descricao"),  res.getBytes("imagem"), res.getInt("statos"));
                System.out.println(chamado);
                
                

                listachamados.add(chamado);

            }

            res.close();
            stmt.close();
            con.close();

            return listachamados;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
            return null;

        }
    }
    
    

}
