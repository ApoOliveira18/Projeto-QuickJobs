package dao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import model.servico;
import utils.PesqServ;

public class PesqDetalhesTrab extends AsyncTask<Integer, Object, Boolean> {

    Connection conexao;

    Context tela;

    ProgressDialog dialogo;

    private servico servClasse;

    public PesqDetalhesTrab(Context tela) {
        super();
        this.tela = tela;

    }


    public Boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexao = DriverManager.getConnection("jdbc:mysql://172.25.74.35:3306/quickjobs", "root", "pedro123");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void disconnect() {
        try {
            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    protected Boolean doInBackground(Integer... integers) {
        Boolean resp;

        connect();

        resp = listarTudo();

        return resp;
    }

    // faz uma pesquisa nos serviços com status 2 (em andamento) com o id do procurador
    public Boolean listarTudo()
    {
        try {
            Date dataTMP;
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            String sql = ("select * from servico,pagamento,procurador where " +
                    "procurador.id_procurador = servico.id_procurador and pagamento.id_pagamento = servico.id_pagamento and id_servico=? and servico.status=2");
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, PesqServ.getId());


            ResultSet tab = comando.executeQuery();

            if (tab.next()) {
                servClasse = new servico();

                servClasse.setComplemento(tab.getString("complemento"));
                dataTMP = tab.getDate("data_registro");
                servClasse.setData_registro(formatoData.format(dataTMP));
                servClasse.setCep(tab.getString("cep"));
                servClasse.setNome_proc(tab.getString("nome"));
                servClasse.setTitulo(tab.getString("titulo"));
                servClasse.setTel_proc(tab.getString("tel"));
                servClasse.setPagto(tab.getString("forma"));
                servClasse.setObservacoes(tab.getString("observacoes"));
                servClasse.setNum_residencial(tab.getString("num_residencial"));
                servClasse.setValor(tab.getString("valor"));



                return true;
            } else {

                servClasse = null;
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialogo = new ProgressDialog(tela);
        dialogo.setMessage("Aguarde ...");
        dialogo.show();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result == false)
            Toast.makeText(tela,
                    "ERRO!!!",
                    Toast.LENGTH_SHORT).show();

        dialogo.dismiss();

        disconnect();
    }

    public servico getServClasse() {
        return servClasse;
    }

    public void setServClasse(servico servClasse) {
        this.servClasse = servClasse;
    }
}
