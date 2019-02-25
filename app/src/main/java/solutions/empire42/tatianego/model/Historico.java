package solutions.empire42.tatianego.model;

import java.util.Date;

public class Historico {

    private String produto;
    private String usuario;
    private Date dataHora;
    private String ativo;

    public Historico(String produto, String usuario, Date dataHora, String ativo ) {
        this.produto = produto;
        this.usuario = usuario;
        this.dataHora = dataHora;
        this.ativo = ativo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}
