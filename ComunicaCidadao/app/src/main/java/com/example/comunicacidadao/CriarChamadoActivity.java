package com.example.comunicacidadao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.BitSet;

import modelDominio.Chamado;

public class CriarChamadoActivity extends AppCompatActivity {
    EditText etCriarChamadoTitulo, etCriarChamadoDescricao;
    String[] setores = {"selecione aqui", "Saúde", "Educação", "Infraestrutura"};
    Spinner sCriarChamadoSetor;
    String setorSelecionado;
    Button bCriarChamadoImagem, bCriarChamadoCriar;
    Chamado chamado;
    ImageView ivCriarChamadoVerimagem;
    byte[] img = null;
    Bitmap fotoBuscada = null;
    String mensagemRecebida = null;
    InformacoesApp informacoesApp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_chamado);
        etCriarChamadoTitulo = findViewById(R.id.etCriarChamadoTitulo);
        etCriarChamadoDescricao = findViewById(R.id.etCriarChamadoDescricao);
        sCriarChamadoSetor = findViewById(R.id.sCriarChamadoSetor);
        bCriarChamadoImagem = findViewById(R.id.bCriarChamadoImagem);
        ivCriarChamadoVerimagem = findViewById(R.id.ivCriarChamadoVerImagem);
        bCriarChamadoCriar = findViewById(R.id.bCriarChamadoCriar);
        informacoesApp = (InformacoesApp) getApplicationContext();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CriarChamadoActivity.this, android.R.layout.simple_spinner_dropdown_item, setores);
        sCriarChamadoSetor.setAdapter(adapter);






        //essa parte vai dentro dos ifs
        sCriarChamadoSetor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setorSelecionado = setores[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        bCriarChamadoImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_GET_CONTENT);
                it.setType("image/*");

                startActivityForResult(it, 2);

            }
        });


        bCriarChamadoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //testar se os campos obrigatorios foram preenchidos e criar o objeto
                System.out.println("rodando");
                if (!etCriarChamadoTitulo.getText().toString().equals("")) {

                    if (setorSelecionado != "selecione aqui") {

                        if (!etCriarChamadoDescricao.getText().toString().equals("")) {

                            int fkIdUsuario = informacoesApp.getUsuarioLogado().getIdUsuario();
                            String titulo = etCriarChamadoTitulo.getText().toString();
                            String descricao = etCriarChamadoDescricao.getText().toString();


                            chamado = new Chamado(fkIdUsuario, titulo, setorSelecionado, descricao, img, 0);


                            //criando uma nova thread para emviar o usuario ao servidor
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {


                                    //iniciando o protocolo para fazer o cadastro
                                    try {

                                        informacoesApp.out.writeObject("cadastrarChamado");


                                        mensagemRecebida = (String) informacoesApp.in.readObject();

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(informacoesApp, "chegou  " + mensagemRecebida, Toast.LENGTH_SHORT).show();


                                                limparCampos();
                                            }
                                        });




                                        informacoesApp.out.writeObject(chamado);
                                        mensagemRecebida = (String) informacoesApp.in.readObject();

                                        //codigo para esta tread agir sobre a tela
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(informacoesApp, "Mensagem recebida:" + mensagemRecebida, Toast.LENGTH_SHORT).show();

                                                //limparCampos();
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



                        } else {
                            etCriarChamadoDescricao.setError("Digite uma descrição!");
                            etCriarChamadoDescricao.requestFocus();
                        }

                    } else {
                        Toast.makeText(CriarChamadoActivity.this, "Selecione um setor para direcionar o chamado!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    etCriarChamadoTitulo.setError("informe o título!");
                    etCriarChamadoTitulo.requestFocus();
                }


            }
        });





    }



    protected void onActivityResult (int requestCode, int resultCode, Intent dados) {

        super.onActivityResult(requestCode, resultCode, dados);

        if (requestCode == 2) {
            try {

                Uri imageUri = dados.getData();

                fotoBuscada = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ivCriarChamadoVerimagem.setImageBitmap(fotoBuscada);
                img = convertImageViewToByteArray(ivCriarChamadoVerimagem);

            } catch (Exception e) {

            }
        }
    }

    public byte[] convertImageViewToByteArray(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        System.out.println("CHEGOU");
        return stream.toByteArray();
    }

    public void limparCampos() {
        etCriarChamadoTitulo.setText("");
        sCriarChamadoSetor.setSelection(0);
        etCriarChamadoDescricao.setText("");
        ivCriarChamadoVerimagem.setImageBitmap(null);
    }

}

