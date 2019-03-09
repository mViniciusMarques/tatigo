package solutions.empire42.tatianego.model;

import java.util.Date;

public class FiltroUsuario {

    private String nome;
    private String email;
    private Date dataInicio;

    public FiltroUsuario(String nome, String email, Date dataInicio) {
        this.nome = nome;
        this.email = email;
        this.dataInicio = dataInicio;
    }

    public FiltroUsuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
}
