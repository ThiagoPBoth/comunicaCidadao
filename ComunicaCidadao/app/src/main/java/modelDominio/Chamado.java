package modelDominio;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Chamado implements Serializable {
    private static final long serialVersionUID = 123L;

    private int fkIdUsuario;
    private String titulo;
    private String setor;
    private String descricao;
    private byte[] imagem;
    private int status;
    private String statusReal;
    private String nome;
    private int idChamado;
    private Bitmap imagemBit;

    public Chamado(int fkIdUsuario, String titulo, String setor, String descricao, byte[] imagem, int status) {
        this.fkIdUsuario = fkIdUsuario;
        this.titulo = titulo;
        this.setor = setor;
        this.descricao = descricao;
        this.imagem = imagem;
        this.status = status;
    }

    public Chamado(String nome, int idChamado, String titulo, String setor, String descricao,  byte[] imagem, int status) {
        this.nome = nome;
        this.idChamado = idChamado;
        this.titulo = titulo;
        this.setor = setor;
        this.descricao = descricao;
        this.imagem = imagem;
        this.status = status;
    }




    public int getFkIdUsuario() {
        return fkIdUsuario;
    }

    public void setFkIdUsuario(int fkIdUsuario) {
        this.fkIdUsuario = fkIdUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(int idChamado) {
        this.idChamado = idChamado;
    }



    @Override
    public String toString() {

        if (status == 0) {
            statusReal = "Não solucionado";
        }

        return "Chamado{" +
                "fkidusuario='" + fkIdUsuario + '\'' +
                "titulo='" + titulo + '\'' +
                ", setor='" + setor + '\'' +
                ", descricao='" + descricao + '\'' +
                ", imagem=" + imagem + '\'' +
                ", status=" + status +
                '}';
    }
}
