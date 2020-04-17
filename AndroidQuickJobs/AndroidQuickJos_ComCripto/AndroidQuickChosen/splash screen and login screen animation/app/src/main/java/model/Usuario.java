package model;

public class Usuario {


    private int id_usu;
    private String nome_usu;
    private int senha_usu;
    private String email_usu;
    private String datanasc_usu;
    private String sexo_usu;


    public int getId_usu() {
        return id_usu;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public String getNome_usu() {
        return nome_usu;
    }

    public void setNome_usu(String nome_usu) {
        this.nome_usu = nome_usu;
    }

    public int getSenha_usu() {
        return senha_usu;
    }

    public void setSenha_usu(int senha_usu) {
        this.senha_usu = senha_usu;
    }

    public String getEmail_usu() {
        return email_usu;
    }

    public void setEmail_usu(String email_usu) {
        this.email_usu = email_usu;
    }

    public String getDatanasc_usu() {
        return datanasc_usu;
    }

    public void setDatanasc_usu(String datanasc_usu) {
        this.datanasc_usu = datanasc_usu;
    }

    public String getSexo_usu() {
        return sexo_usu;
    }

    public void setSexo_usu(String sexo_usu) {
        this.sexo_usu = sexo_usu;
    }
}
