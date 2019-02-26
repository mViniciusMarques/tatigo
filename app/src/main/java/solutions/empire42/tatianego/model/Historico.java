package solutions.empire42.tatianego.model;

import java.util.Date;

public class Historico {

    private String objectId;
    private String produto;
    private String usuario;
    private Date dataHora;
    private Boolean ativo;
    private int imagem;

    public Historico(String produto, String usuario, Date dataHora, Boolean ativo, int imagem ) {
        this.produto = produto;
        this.usuario = usuario;
        this.dataHora = dataHora;
        this.ativo = ativo;
        this.imagem = imagem;
    }

    public Historico() {
    }

    public Historico(String objectId, String produto, String usuario, Date dataHora, Boolean ativo, int imagem) {
        this.objectId = objectId;
        this.produto = produto;
        this.usuario = usuario;
        this.dataHora = dataHora;
        this.ativo = ativo;
        this.imagem = imagem;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
