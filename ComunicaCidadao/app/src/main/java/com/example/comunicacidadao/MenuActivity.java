package com.example.comunicacidadao;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MenuActivity extends AppCompatActivity {
    Button bMenuCriarChamado, bMenuConsultarChamados, bMenuConsultarMeusChamados;
    TextView tvMenuOla;
    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bMenuCriarChamado = findViewById(R.id.bMenuCriarChamado);
        bMenuConsultarChamados = findViewById(R.id.bMenuConsultarChamados);
        bMenuConsultarMeusChamados = findViewById(R.id.bMenuConsultarMeusChamados);
        tvMenuOla = findViewById(R.id.tvMenuOla);
        informacoesApp = (InformacoesApp) getApplicationContext();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    tvMenuOla.setText("Seja bem vindo, " + informacoesApp.getUsuarioLogado().getCpf() + ". O que você deseja fazer?");

        bMenuCriarChamado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent
                Intent it = new Intent(MenuActivity.this, CriarChamadoActivity.class);
                startActivity(it);
            }
        });

        bMenuConsultarChamados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MenuActivity.this, VisualizacaoChamadosActivity.class);
                startActivity(it);
            }
        });


        bMenuConsultarMeusChamados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent
                Intent it = new Intent(MenuActivity.this, VisualizacaoMeusChamadosActivity.class);
                startActivity(it);
            }
        });



    }

}
