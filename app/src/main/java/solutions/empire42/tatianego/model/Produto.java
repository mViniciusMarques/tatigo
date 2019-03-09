package solutions.empire42.tatianego.model;

import java.util.Date;

public class Produto {

    private String nome;
    private Date dataBase;
    private String usuario;
    private Integer quantidade;

    public Produto(String nome, Date dataBase, String usuario, Integer quantidade) {
        this.nome = nome;
        this.dataBase = dataBase;
        this.usuario = usuario;
        this.quantidade = quantidade;
    }

    public Produto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataBase() {
        return dataBase;
    }

    public void setDataBase(Date dataBase) {
        this.dataBase = dataBase;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
