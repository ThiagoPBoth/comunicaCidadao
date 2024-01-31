/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Autor: Thiago Both
 */
package view;

import controller.TrataClienteController;
import factory.Conector;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Principal {
    
        public static void main(String[] args) {

        Connection con;
        //DESCOMENTAR DEPOIS
        con = Conector.getConnection();

        try {

            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor Socket inicializado. Aguardando conexão");
            ConectaServidor cs = new ConectaServidor(servidor);
            cs.start();
            

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
}

class ConectaServidor extends Thread {

    private ServerSocket servidor;
    private int idUnico;

    public ConectaServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    @Override
    public void run() {

        try {
            while (true) {

                Socket cliente = servidor.accept();
                System.out.println("Novo cliente conectou");
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                
                idUnico++; //imcrementando o id do cliente conectado
                
                //AQUI CRIAMOS UMA THREAD QUE FICARÁ ESCUTANDO OS COMANDOS DOS CLIENTES
                System.out.println("Iniciando uma nova Thread para o cliente" +idUnico);
                TrataClienteController tcc = new TrataClienteController(in, out, idUnico, cliente);
                tcc.start();
                

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}