package com.example.comunicacidadao;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import modelDominio.Usuario;


public class CadastroActivity extends AppCompatActivity {
    EditText etCadastroNome, etCadastroCPF, etCadastroTelefone, etCadastroLogin, etCadastroSenha;
    Button bCadastroCadastrar, bCadastroCancelar;
    InformacoesApp informacoesApp;

    Usuario usuario;
    String mensagemRecebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        etCadastroNome = findViewById(R.id.etCadastroNome);
        etCadastroCPF = findViewById(R.id.etCadastroCPF);
        etCadastroTelefone = findViewById(R.id.etCadastroTelefone);
        etCadastroLogin = findViewById(R.id.etCadastroLogin);
        etCadastroSenha = findViewById(R.id.etCadastroSenha);
        bCadastroCadastrar = findViewById(R.id.bCadastroCadastrar);
        bCadastroCancelar = findViewById(R.id.bCadastroCancelar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        informacoesApp = (InformacoesApp) getApplicationContext();


        bCadastroCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!etCadastroNome.getText().toString().equals("")) {

                    if (!etCadastroCPF.getText().toString().equals("")) {

                        if (!etCadastroTelefone.getText().toString().equals("")) {

                            if (!etCadastroLogin.getText().toString().equals("")) {

                                if (!etCadastroSenha.getText().toString().equals("")) {

                                    String nome = etCadastroNome.getText().toString();
                                    String CPF = etCadastroCPF.getText().toString();
                                    String telefone = etCadastroTelefone.getText().toString();
                                    String login = etCadastroLogin.getText().toString();
                                    String senha = etCadastroSenha.getText().toString();
                                    int tipo = 1;


                                    BigInteger a = null;

                                    try {

                                        MessageDigest md = MessageDigest.getInstance("MD5"); // MD5, SHA-1, SHA-256

                                        a = new BigInteger(1, md.digest(senha.getBytes()));

                                    }  catch (NoSuchAlgorithmException e) {
                                        System.out.println("Erro ao carregar o MessageDigest");
                                    }

                                    usuario = new Usuario(nome, CPF, telefone, tipo , login, a.toString(), 1);



                                        //criando uma nova thread para emviar o usuario ao servidor
                                        Thread thread = new Thread(new Runnable() {
                                            @Override
                                            public void run() {


                                                //iniciando o protocolo para fazer o cadastro
                                                try {

                                                    informacoesApp.out.writeObject("cadastrarUsuario");
                                                    mensagemRecebida = (String) informacoesApp.in.readObject();




                                                    informacoesApp.out.writeObject(usuario);
                                                    mensagemRecebida = (String) informacoesApp.in.readObject();

                                                    //codigo para esta tread agir sobre a tela
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(informacoesApp, "Mensagem recebida:" + mensagemRecebida, Toast.LENGTH_SHORT).show();

                                                            limparCampos();
                                                        }
                                                    });




                                                } catch (IOException ioe) {

                                                } catch (ClassNotFoundException classe) {

                                                }
                                            }
                                        });
                                        thread.start();






                                } else {
                                    etCadastroSenha.setError("Informe a senha!");
                                    etCadastroSenha.requestFocus();
                                }

                            } else {
                                etCadastroLogin.setError("Informe o Login!");
                                etCadastroLogin.requestFocus();
                            }

                        } else {
                            etCadastroTelefone.setError("Informe o telefone!");
                            etCadastroTelefone.requestFocus();
                        }

                    } else {
                        etCadastroCPF.setError("Informe o CPF!");
                        etCadastroCPF.requestFocus();
                    }
                } else{
                    etCadastroNome.setError("Informe o nome!");
                    etCadastroNome.requestFocus();

                }


            }
        });


        bCadastroCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCampos();
            }
        });



    }

    public void limparCampos() {
        etCadastroNome.setText("");
        etCadastroCPF.setText("");
        etCadastroTelefone.setText("");
        etCadastroLogin.setText("");
        etCadastroSenha.setText("");
    }

}
