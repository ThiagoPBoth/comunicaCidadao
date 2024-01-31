package com.example.comunicacidadao;

import android.content.Intent;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import modelDominio.Chamado;


public class VisualizacaoChamadosActivity extends AppCompatActivity {
    RecyclerView rvVisualizacaoChamadosRecyclerView;
    String mensagemRecebida;
    ArrayList<Chamado> listaChamado;
    EditText etVisualizacaoChamadosPesquisar;
    Button bVisualizacaoChamadosBuscar;
    InformacoesApp informacoesApp;
    int contaBuscas = 0;
    ArrayList<Chamado> listaChamadosBuscados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_chamados);
        rvVisualizacaoChamadosRecyclerView = findViewById(R.id.rvVisualizacaoChamadosRecyclerView);
        etVisualizacaoChamadosPesquisar = findViewById(R.id.etVisualizacaoChamadosPesquisar);
        bVisualizacaoChamadosBuscar = findViewById(R.id.bVisualizacaoChamadosBuscar);
        informacoesApp = (InformacoesApp) getApplicationContext();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                //iniciando o protocolo para fazer o cadastro
                try {

                    informacoesApp.out.writeObject("visualizarChamado");


                    listaChamado = (ArrayList<Chamado>) informacoesApp.in.readObject();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            System.out.println(listaChamado);

                            ChamadoAdapter chamadoAdapter = new ChamadoAdapter(listaChamado, trataCliqueItem);
                            rvVisualizacaoChamadosRecyclerView.setLayoutManager(new LinearLayoutManager(VisualizacaoChamadosActivity.this));
                            rvVisualizacaoChamadosRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            rvVisualizacaoChamadosRecyclerView.setAdapter(chamadoAdapter);
                            ///////////////////////////////


                        }
                    });



                } catch (IOException ioe) {
                    System.out.println(ioe);
                } catch (ClassNotFoundException classe) {
                    System.out.println(classe);
                }
            }
        });
        thread.start();


        bVisualizacaoChamadosBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String busca = etVisualizacaoChamadosPesquisar.getText().toString();
                listaChamadosBuscados = new ArrayList<>();
                for (int i = 0; i < listaChamado.size(); i++) {
                    Chamado chamado = listaChamado.get(i);
                    String a = chamado.getTitulo();

                    if (a.replaceAll(" ", "").equalsIgnoreCase(busca.replaceAll(" ", "")) ) {
                        System.out.println("sim");
                        Toast.makeText(informacoesApp, "sssss", Toast.LENGTH_SHORT).show();

                        listaChamadosBuscados.add(chamado);
                    }


                }

                ChamadoAdapter chamadoAdapter = new ChamadoAdapter(listaChamadosBuscados, trataCliqueItem);
                rvVisualizacaoChamadosRecyclerView.setLayoutManager(new LinearLayoutManager(VisualizacaoChamadosActivity.this));
                rvVisualizacaoChamadosRecyclerView.setItemAnimator(new DefaultItemAnimator());
                rvVisualizacaoChamadosRecyclerView.setAdapter(chamadoAdapter);
                contaBuscas++;

            }
        });



    }


    ChamadoAdapter.ChamadoOnClickListener trataCliqueItem = new ChamadoAdapter.ChamadoOnClickListener() {
        @Override
        public void onClickChamado(View view, int position) {
            //FAZENDO O QUE FOE NECESSÁRO A CADA CLIQUE

            if (contaBuscas > 0) {


                Chamado chamado = listaChamadosBuscados.get(position);

                Log.d("Posição: ", chamado.toString());
                Intent it = new Intent(VisualizacaoChamadosActivity.this, VisualizacaoDetalhadaChamadosActivity.class);
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



            } else {


                Chamado chamado = listaChamado.get(position);

                Log.d("Posição: ", chamado.toString());
                Intent it = new Intent(VisualizacaoChamadosActivity.this, VisualizacaoDetalhadaChamadosActivity.class);
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


        }
    };


    }


