/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelDominio.Chamado;
import modelDominio.Usuario;

/**
 *
 * @author joao.pedro_combateaf
 */
public class ConexaoController {
    
    
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int idUnico;
    //ESSE ATRIBUTO VAI ARMAZENAR O USUARIO LOGADO NO SISTEMA
    public Usuario usuario;

    
    public ConexaoController(ObjectOutputStream out, ObjectInputStream in, int idUnico) {
        this.out = out;
        this.in = in;
        this.idUnico = idUnico;
    }
    
    public Usuario consultarUsuario (Usuario usr){
        
        String msg;
        
        try {
            out.writeObject("consultarUsuario");
            msg = (String) in.readObject();
            out.writeObject(usr);
            Usuario usrSelecionado = (Usuario)in.readObject();
            
            return usrSelecionado;
            
        } catch (Exception e) {   
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean alterarChamado (Chamado chamado) {
        
        String msg;
        try {
            out.writeObject("AlterarChamado");
            msg = (String) in.readObject();
            out.writeObject(chamado);
            msg = (String) in.readObject();
            if (msg.equals("ok")){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {   
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Chamado> ChamadoLista (){
        String msg;
        try {
            out.writeObject("visualizarChamado");
            
            
            ArrayList <Chamado> listachamado = (ArrayList<Chamado>) in.readObject();
            System.out.println("testeeeee" + listachamado);
            return listachamado;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Usuario> UsuarioLista() {
        
        try {
            out.writeObject("visualizarUsuario");
            
            ArrayList <Usuario> listausuario = (ArrayList<Usuario>)in.readObject();
            System.out.println("teste usuario");
            return listausuario;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        
    }

    public int usuarioExcluir(int idUsuario) {
        
        String msg;
        int msgInt;        
        try {
            out.writeObject("usuarioExcluir");
            msg = (String) in.readObject();
            out.writeObject(idUsuario);
            msgInt = (int) in.readObject();
            
            return msgInt;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}
