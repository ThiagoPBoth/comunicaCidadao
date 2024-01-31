/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import java.sql.*;

public class Conector {
    
    private static Connection con;

    public static Connection getConnection() {

        try {

            String url = "jdbc:mysql:// localhost:3306/";
            String banco = "bdcomunicacidadao";
            String usuario = "root";
            String senha = "";

            con = DriverManager.getConnection(url + banco, usuario, senha);
            System.out.println("Conectado com sucesso com o banco" + banco);
            return con;

        } catch (Exception e) {
            //O PRINTsTACKtRACE IMPRIME TODA A PILHA DO ERRO QUE ACONTECE
            e.printStackTrace();
            return null;
        }

    }
}
