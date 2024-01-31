package com.example.comunicacidadao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import modelDominio.Chamado;

public class VisualizacaoMeusChamadosActivity extends AppCompatActivity {
    RecyclerView rvVisualizacaoMeusChamadosRecyclerView;
    InformacoesApp informacoesApp;
    ArrayList<Chamado> listaChamado;
    String msg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_meus_chamados);
        rvVisualizacaoMeusChamadosRecyclerView = findViewById(R.id.rvVisualizacaoMeusChamadosRecyclerView);
        informacoesApp = (InformacoesApp) getApplicationContext();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                //iniciando o protocolo para fazer o cadastro
                try {

                    informacoesApp.out.writeObject("visualizarMeusChamado");
                    msg = (String) informacoesApp.in.readObject();

                    if (msg.equalsIgnoreCase("ok")) {

                        informacoesApp.out.writeObject(informacoesApp.getUsuarioLogado());

                        listaChamado = (ArrayList<Chamado>) informacoesApp.in.readObject();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                System.out.println(listaChamado);

                                ChamadoAdapter chamadoAdapter = new ChamadoAdapter(listaChamado, trataCliqueItem);
                                rvVisualizacaoMeusChamadosRecyclerView.setLayoutManager(new LinearLayoutManager(VisualizacaoMeusChamadosActivity.this));
                                rvVisualizacaoMeusChamadosRecyclerView.setItemAnimator(new DefaultItemAnimator());
                                rvVisualizacaoMeusChamadosRecyclerView.setAdapter(chamadoAdapter);
                                ///////////////////////////////


                            }
                        });


                    }




                } catch (IOException ioe) {
                    System.out.println(ioe);
                } catch (ClassNotFoundException classe) {
                    System.out.println(classe);
                }
            }
        });
        thread.start();


    }

    ChamadoAdapter.ChamadoOnClickListener trataCliqueItem = new ChamadoAdapter.ChamadoOnClickListener() {
        @Override
        public void onClickChamado(View view, int position) {
            //FAZENDO O QUE FOE NECESSÁRO A CADA CLIQUE
            Chamado chamado = listaChamado.get(position);

            Log.d("Posição: ", chamado.toString());
            Intent it = new Intent(VisualizacaoMeusChamadosActivity.this, VisualizacaoDetalhadaChamadosActivity.class);
            it.putExtra("titulo", chamado.getTitulo());
            it.putExtra("setor", chamado.getSetor());
            it.putExtra("descricao", chamado.getDescricao());
            it.putExtra("imagem", chamado.getImagem());
            String status = null;
            if (chamado.getStatus() == 0) {
                status = "Não Solucionado";
            } else if (chamado.getStatus() == 1) {
                status = "Em processo para soluciobar";
            } else if (chamado.getStatus() == 2) {
                status = "Solucionado";
            }
            it.putExtra("status", status);

            startActivity(it);

        }
    };

}
