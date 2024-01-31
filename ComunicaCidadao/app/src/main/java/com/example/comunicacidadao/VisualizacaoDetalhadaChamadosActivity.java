package com.example.comunicacidadao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



public class VisualizacaoDetalhadaChamadosActivity extends AppCompatActivity {
    TextView tvVisualizacaoDetalhaChamadosTitulo, tvVisualizacaoDetalhaChamadosSetor, tvVisualizacaoDetalhaChamadosDescricao, tvVisualizacaoDetalhaChamadosStatus;
    ImageView ivVisualizacaoDetalhadaCgamadosImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_detalhada_chamados);
        tvVisualizacaoDetalhaChamadosTitulo = findViewById(R.id.tvVisualizacaoDetalhaChamadosTitulo);
        tvVisualizacaoDetalhaChamadosSetor = findViewById(R.id.tvVisualizacaoDetalhaChamadosSetor);
        tvVisualizacaoDetalhaChamadosDescricao = findViewById(R.id.tvVisualizacaoDetalhaChamadosDescricao);
        tvVisualizacaoDetalhaChamadosStatus = findViewById(R.id.tvVisualizacaoDetalhaChamadosStatus);
        ivVisualizacaoDetalhadaCgamadosImagem = findViewById(R.id.ivVisualizacaoDetalhadaChamadosimagem);
        Bitmap imagemBit = null;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent it = getIntent();

        String titulo = it.getStringExtra("titulo");
        String setor = it.getStringExtra("setor");
        String descricao = it.getStringExtra("descricao");
        byte[] imagem = it.getByteArrayExtra("imagem");
        String status = it.getStringExtra("status");



        if (imagem != null) {
            imagemBit = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
        }


        tvVisualizacaoDetalhaChamadosTitulo.setText(titulo);
        tvVisualizacaoDetalhaChamadosSetor.setText(setor);
        tvVisualizacaoDetalhaChamadosDescricao.setText(descricao);
        ivVisualizacaoDetalhadaCgamadosImagem.setImageBitmap(imagemBit);
        tvVisualizacaoDetalhaChamadosStatus.setText(status);



    }

}
