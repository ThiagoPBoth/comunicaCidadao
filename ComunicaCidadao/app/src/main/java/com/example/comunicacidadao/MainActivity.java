package com.example.comunicacidadao;
//Autor: Thiago Both
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Button bMainLogin, getbMainCadastro;
    InformacoesApp informacoesApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bMainLogin = findViewById(R.id.bMainLogin);
        getbMainCadastro = findViewById(R.id.bMainCadastro);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        informacoesApp = (InformacoesApp) getApplicationContext();


        if (informacoesApp.socket != null) {
            bMainLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent para chamar próxima tela
                    Intent it = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(it);


                }
            });

            getbMainCadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent para chamar a proxima tela
                    Intent it = new Intent(MainActivity.this, CadastroActivity.class);
                    startActivity(it);

                }
            });
        } else {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //criando a conexao com o servidor


                    try{

                        //10.0.2.2 = localhost de onde o emulador está executando
                        System.out.println("AQUI");
                        informacoesApp.socket = new Socket("192.168.0.111", 12345);
                        System.out.println("AAQQUII");


                        informacoesApp.out = new ObjectOutputStream(informacoesApp.socket.getOutputStream());
                        informacoesApp.in = new ObjectInputStream(informacoesApp.socket.getInputStream());

                        //sincronizar as tread
                        //dentro da thread eu quero agir sobre a tela
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(informacoesApp, "Conexao efetuada com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (IOException ioe){//exceção de entrada e saida
                        System.out.println(ioe.getMessage());
                    }
                }
            });
            thread.start();




            bMainLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent para chamar próxima tela
                    Intent it = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(it);


                }
            });

            getbMainCadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent para chamar a proxima tela
                    Intent it = new Intent(MainActivity.this, CadastroActivity.class);
                    startActivity(it);

                }
            });



        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
