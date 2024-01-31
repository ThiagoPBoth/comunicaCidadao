/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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

/**
 *
 * @author Thiago Both
 */
public class UsuarioDao {

    private Connection con;

    public UsuarioDao() {
        con = Conector.getConnection();
    }

    public int inserir(Usuario usuario) {
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);

                String sql = "insert into usuario (cpf, nome, telefone, tipo, login, senha, statos) value (?,?,?,?,?,?,1)";
                stmt = con.prepareStatement(sql);
                //tROCAR O SPARAMETROS
                stmt.setString(1, usuario.getCpf());
                stmt.setString(2, usuario.getNome());
                stmt.setString(3, usuario.getTelefone());
                stmt.setInt(4, usuario.getTipo());
                stmt.setString(5, usuario.getLogin());
                stmt.setString(6, usuario.getSenha());

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
    
    public int excluirUsuario (int idUsuario) throws SQLException{
       
        int possuiChamados = possuiChamadosAbertos(idUsuario);

        System.out.println("aaa" + possuiChamados);
        int resultado = 0;
        
        if (possuiChamados > 0) {
            
            resultado = atualizarStatos(idUsuario);//retorna 1
            
        } else {
            
            resultado = excluir(idUsuario);//retorna 2
            
            
        }
        return resultado;
        
        
    }
    
    private int possuiChamadosAbertos(int idUsuario) throws SQLException {
        PreparedStatement stmt = null;

        // prepara a consulta SQL
        stmt = con.prepareStatement("SELECT COUNT(*) as qnt FROM chamado WHERE fk_idUsuario = ?");
        stmt.setInt(1, idUsuario);

        // executa a consulta e obtém o resultado
        ResultSet resultado = stmt.executeQuery();
        resultado.next();
        int numChamadosAbertos = resultado.getInt(1);

        System.out.println("chegou aqui loko?" + numChamadosAbertos);
        return numChamadosAbertos;
        // retorna se o usuário possui chamados abertos

    }
    
    public int atualizarStatos(int idUsuario) throws SQLException {
        PreparedStatement stmt = null;

        // prepara o comando SQL
        stmt = con.prepareStatement("UPDATE usuario SET statos = 2 WHERE idusuario = ?");
        stmt.setInt(1, idUsuario);
        
        // executa o comando
        stmt.executeUpdate();
        
        return 1;
    }
    
    public int excluir(int idUsuario) throws SQLException {
        PreparedStatement stmt = null;

        // prepara o comando SQL
        stmt = con.prepareStatement("DELETE FROM usuario WHERE idusuario = ?");
        stmt.setInt(1, idUsuario);

        // executa o comando
        stmt.executeUpdate();
        return 2;
    }    

    //===================================
    //public Usuario consultar(Usuario usuario) {
    public Usuario consultar(Usuario usuario) {
        PreparedStatement stmt = null;
        Usuario usuarioSelecionado = null;
        try {
            try {

                String sql = "select * from usuario where login = ? and senha = ? and tipo = ? and statos = ?";
                stmt = con.prepareStatement(sql);
                //tROCAR O SPARAMETROS
                stmt.setString(1, usuario.getLogin());
                //transformar em hash
                stmt.setString(2, usuario.getSenha());
                stmt.setInt(3, usuario.getTipo());
                stmt.setInt(4, usuario.getStatus());

                System.out.println(stmt);
                System.out.println("After : " + stmt.toString());
                //EXECUTAR O SCRIPT
                //stmt.execute();
                ResultSet res = stmt.executeQuery();
                while (res.next()) {

                    usuarioSelecionado = new Usuario(res.getInt("idUsuario"), res.getString("cpf"), res.getString("nome"), res.getString("telefone"), res.getInt("tipo"), res.getString("login"), res.getString("senha"),res.getInt("statos"));

                }

                System.out.println(usuarioSelecionado);

                return usuarioSelecionado; //DEU TDO CERTO

                //RETORNAR O USUARIO SELECIONADO
                //TROCAR A EXCEPTION DEPOIS
            } catch (SQLException e) {

                try {
                    e.printStackTrace();
                    //cancela se deu erro
                    con.rollback();
                    return null;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return null;
                }
            }

        } finally {

            //ENTRA AQUI INDEPENDENTE DE SEU ERRO
            //ENTRA AQUI INDEPENDENTE DE SEU ERRO
            try {
                stmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
//            
        }
    }
    
    public ArrayList<Usuario> getListaUsuarios() {
        
        Statement stmt = null;
        ArrayList<Usuario> listausuarios = new ArrayList<>();
        try {
            stmt = con.createStatement();
            
            
            ResultSet res = stmt.executeQuery("select * from usuario;");
            //ResultSet res = stmt.executeQuery("select chamado.*, usuario.nome from chamado inner join usuario;");
           
            
            while (res.next()) {
                
                System.out.println("cheguei aqui");
                //Chamado chamado = new Chamado(res.getString("nome"), res.getInt("idChamado"), res.getString("titulo"), res.getString("setor"), res.getString("descricao"), res.getInt("statos"));
                Usuario usuario = new Usuario(res.getInt("idUsuario"), res.getString("nome"), res.getString("cpf"), res.getString("telefone"),res.getInt("tipo"),res.getString("login"),res.getString("senha"),res.getInt("statos"));
                System.out.println(usuario);
                
                

                listausuarios.add(usuario);

            }

            res.close();
            stmt.close();
            con.close();

            return listausuarios;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
            return null;

        }

    }
    
}
