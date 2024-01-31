package modelDominio;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 123L;

    private int idUsuario;
    private String nome;
    private String cpf;
    private String telefone;
    private int tipo;
    private String login;
    private String senha;
    private int status;

    public Usuario(int idUsuario, String nome, String cpf, String telefone, int tipo, String login, String senha, int status) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.tipo = tipo;
        this.login = login;
        this.senha = senha;
        this.status = status;
    }

    public Usuario(String nome, String cpf, String telefone, int tipo, String login, String senha, int status) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.tipo = tipo;
        this.login = login;
        this.senha = senha;
        this.status = status;
    }

    public Usuario(int tipo, String login, String senha, int status) {
        this.tipo = tipo;
        this.login = login;
        this.senha = senha;
        this.status = status;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", cpf=" + cpf +
                ", telefone=" + telefone +
                ", tipo=" + tipo +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
