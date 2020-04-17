package model;

public class servico {

   private int  id_servico;
    private int id_procurador;
    private int id_trabalhador;
    private int id_pagamento;
    private int id_tipo_trab;

private String  titulo;
    private String descricao;
    private String valor;
    private String observacoes;
    private String data_registro;
    private String cep;
    private String num_residencial;
    private String complemento;
    private int status;
    private String tel_proc;
    private String nome_proc;
    private String pagto;
    private String nome_trab;
    private String tel_trab;


    public int getId_servico() {
        return id_servico;
    }

    public void setId_servico(int id_servico) {
        this.id_servico = id_servico;
    }

    public int getId_procurador() {
        return id_procurador;
    }

    public void setId_procurador(int id_procurador) {
        this.id_procurador = id_procurador;
    }

    public int getId_trabalhador() {
        return id_trabalhador;
    }

    public void setId_trabalhador(int id_trabalhador) {
        this.id_trabalhador = id_trabalhador;
    }

    public int getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public int getId_tipo_trab() {
        return id_tipo_trab;
    }

    public void setId_tipo_trab(int id_tipo_trab) {
        this.id_tipo_trab = id_tipo_trab;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getData_registro() {
        return data_registro;
    }

    public void setData_registro(String data_registro) {
        this.data_registro = data_registro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNum_residencial() {
        return num_residencial;
    }

    public void setNum_residencial(String num_residencial) {
        this.num_residencial = num_residencial;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTel_proc() {
        return tel_proc;
    }

    public void setTel_proc(String tel_proc) {
        this.tel_proc = tel_proc;
    }

    public String getNome_proc() {
        return nome_proc;
    }

    public void setNome_proc(String nome_proc) {
        this.nome_proc = nome_proc;
    }

    public String getPagto() {
        return pagto;
    }

    public void setPagto(String pagto) {
        this.pagto = pagto;
    }

    public String getNome_trab() {
        return nome_trab;
    }

    public void setNome_trab(String nome_trab) {
        this.nome_trab = nome_trab;
    }

    public String getTel_trab() {
        return tel_trab;
    }

    public void setTel_trab(String tel_trab) {
        this.tel_trab = tel_trab;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
