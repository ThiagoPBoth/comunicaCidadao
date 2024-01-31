/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.UsuarioDao;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import model.ChamadoDao;
import modelDominio.Chamado;
import modelDominio.Usuario;

public class TrataClienteController extends Thread {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private int idUnico;
    private Socket cliente;

    public TrataClienteController(ObjectInputStream in, ObjectOutputStream out, int idUnico, Socket cliente) {
        this.in = in;
        this.out = out;
        this.idUnico = idUnico;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        System.out.println("Esperando comando fo cliente" + idUnico);
        String comando;

        try {

            comando = (String)  in.readObject();
            System.out.println(comando);
            while (!comando.equalsIgnoreCase("fim")) {

                System.out.println("O cliente " + idUnico + " enviou um comando" + comando);

                //IFs para ver qual foi  o comando enviado
                if (comando.equalsIgnoreCase("cadastrarUsuario")) {
                    out.writeObject("ok");

                    Usuario usuario = (Usuario) in.readObject();
                    UsuarioDao usuarioDao = new UsuarioDao();
                    int result = usuarioDao.inserir(usuario);

                    if (result == -1) {
                        out.writeObject("Cadastrado com Sucesso");
                    } else {
                        out.writeObject("nok");
                    }

                    
                    System.out.println(usuario);
                } else if (comando.equalsIgnoreCase("consultarUsuario")) {
                    out.writeObject("ok");

                    Usuario usuario = (Usuario) in.readObject();

                    System.out.println(usuario);
                    UsuarioDao usuarioDao = new UsuarioDao();
                    
                    Usuario result = usuarioDao.consultar(usuario);

                    out.writeObject(result);

                    //out.writeObject(usuarioSelecionado);
                    //System.out.println(usuarioSelecionado);
                } else if (comando.equalsIgnoreCase("cadastrarChamado")) {
                    System.out.println("vish");

                    out.writeObject("okkkkk");

                    System.out.println(comando);

                    System.out.println("chamado nao recebido");

                    Chamado chamado = (Chamado) in.readObject();
                    System.out.println("chamado recebido" + chamado);

                    ChamadoDao chamadoDao = new ChamadoDao();
                    int result = chamadoDao.inserir(chamado);

                    if (result == -1) {
                        out.writeObject("Cdastrado com Sucesso");
                    } else {
                        out.writeObject("nok");
                    }

                    

                } else if (comando.equalsIgnoreCase("visualizarChamado")) {
                    

                    System.out.println(comando);

                    ChamadoDao chamadoDao = new ChamadoDao();
                    ArrayList<Chamado> result = chamadoDao.getListaChamados();

                    System.out.println(result);
                    out.writeObject(result);
                    

                } else if (comando.equalsIgnoreCase("visualizarMeusChamado")) {
                    out.writeObject("ok");

                    System.out.println(comando);
                    
                    Usuario usuario = (Usuario) in.readObject();
                    System.out.println(usuario);
                    ChamadoDao chamadoDao = new ChamadoDao();
                    ArrayList<Chamado> result = chamadoDao.getListaMeusChamados(usuario);

                    System.out.println(result);
                    out.writeObject(result);
                    

                } else if (comando.equalsIgnoreCase("visualizarUsuario")) {
                    

                    System.out.println(comando);

                    UsuarioDao usuarioDao = new UsuarioDao();
                    ArrayList<Usuario> result = usuarioDao.getListaUsuarios();

                    System.out.println(result);
                    out.writeObject(result);
                    

                } else if (comando.equalsIgnoreCase("AlterarChamado")) {
                    
                    out.writeObject("ok");
                    Chamado chamado = (Chamado) in.readObject();
                    
                    ChamadoDao chamadoDao = new ChamadoDao();
                    int result = chamadoDao.alterar(chamado);
                    
                    if (result == -1) {
                        out.writeObject("ok");
                    } else {
                        out.writeObject("nok");
                    }
                    
                    
                } else if (comando.equalsIgnoreCase("usuarioExcluir")) {
                    
                    out.writeObject("ok");
                    int idUsuario = (int) in.readObject();
                    
                    UsuarioDao usuarioDao = new UsuarioDao();
                    int result = usuarioDao.excluirUsuario(idUsuario);
                    
                    out.writeObject(result);
                    
                    
                }

                //LENDO PROXIMO COMANDO
                comando = (String) in.readObject();

            }

        } catch (Exception e) {
        }

    }
}
