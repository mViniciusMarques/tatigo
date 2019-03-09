package solutions.empire42.tatianego.model;

import java.util.Date;
import java.util.List;

public class Caderneta {

    private String nome;
    private String usuario;
    private Date dataInicio;

    private List<Produto> produtos;

    public Caderneta(String nome, String usuario, Date dataInicio, List<Produto> produtos) {
        this.nome = nome;
        this.usuario = usuario;
        this.dataInicio = dataInicio;
        this.produtos = produtos;
    }

    public Caderneta() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
