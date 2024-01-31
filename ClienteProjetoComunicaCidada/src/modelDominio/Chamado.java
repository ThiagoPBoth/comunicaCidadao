/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDominio;



import java.io.Serializable;

/**
 *
 * @author Colaborador
 */
public class Chamado implements Serializable{
   private static final long serialVersionUID = 123L;
    private int idChamado;
    private int fkIdUsuario;
    private String titulo;
    private String setor;
    private String descricao;
    private byte[] imagem;
    private int status;
    private String statusReal;
    private String nome;
    private java.sql.Blob imagemBlob;

    public Chamado(int idChamado,String nome, String titulo, String setor, String descricao, byte[] imagem, int status ) {
        this.idChamado = idChamado;
        this.nome = nome;
        this.titulo = titulo;
        this.setor = setor;
        this.descricao = descricao;
        this.imagem = imagem;
        this.status = status;
        
    }
    
    public Chamado(int fkIdUsuario, String titulo, String setor, String descricao, byte[] imagem, int status) {
        this.fkIdUsuario = fkIdUsuario;
        this.titulo = titulo;
        this.setor = setor;
        this.descricao = descricao;
        this.imagem = imagem;
        this.status = status;
    }

    public Chamado(int idChamado, int status) {
        this.idChamado = idChamado;
        this.status = status;
    }
    
    
    
    public Chamado(int fkIdUsuario, String titulo, String setor, String descricao, int status) {
        this.fkIdUsuario = fkIdUsuario;
        this.titulo = titulo;
        this.setor = setor;
        this.descricao = descricao;
       
        this.status = status;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    public int getFkIdUsuario() {
        return fkIdUsuario;
    }

    public void setFkIdUsuario(int fkIdUsuario) {
        this.fkIdUsuario = fkIdUsuario;
    }

    public int getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(int idChamado) {
        this.idChamado = idChamado;
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

    public java.sql.Blob getImagemBlob() {
        return imagemBlob;
    }

    public void setImagemBlob(java.sql.Blob imagemBlob) {
        this.imagemBlob = imagemBlob;
    }

    @Override
    public String toString() {
        return "Chamado{" + "idChamado=" + idChamado + ", fkIdUsuario=" + fkIdUsuario + ", titulo=" + titulo + ", setor=" + setor + ", descricao=" + descricao + ", imagem=" + imagem + ", status=" + status + ", statusReal=" + statusReal + ", nome=" + nome + ", imagemBlob=" + imagemBlob + '}';
    }
    
    
}
