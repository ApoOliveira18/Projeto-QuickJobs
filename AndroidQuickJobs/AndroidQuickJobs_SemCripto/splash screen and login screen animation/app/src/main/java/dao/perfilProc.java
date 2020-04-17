package dao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.procurador;
import utils.pesqProcPerfil;

public class perfilProc extends AsyncTask<Integer, Object, Boolean> {

    Connection conexao;

    Context tela;

    ProgressDialog dialogo;

    private procurador ProcClass;
    public procurador getProcClass() {
        return ProcClass;
    }

    public void setProcClass(procurador procClass) {
        ProcClass = procClass;
    }

    public perfilProc(Context tela) {
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

        resp = pesquisarProc();

        return resp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        disconnect();
    }

    // faz um select e retorna as informações do procurador logado
    public Boolean pesquisarProc() {
        try {
            Date dataTMP;
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataTMP1;
            SimpleDateFormat formatoData2 = new SimpleDateFormat("dd/MM/yyyy");

            String sql = "select * from procurador where id_procurador=?";


            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, pesqProcPerfil.getId());
            ResultSet tabelaMemoria = comando.executeQuery();

            if (tabelaMemoria.next()) {
                ProcClass = new procurador();

                ProcClass.setId_procurador(tabelaMemoria.getInt("id_procurador"));
                ProcClass.setNome(tabelaMemoria.getString("nome"));
                ProcClass.setFoto(tabelaMemoria.getString("foto"));
                ProcClass.setSexo(tabelaMemoria.getString("sexo"));

                dataTMP = tabelaMemoria.getDate("data_nasc");
                ProcClass.setData_nasc(formatoData.format(dataTMP));

                dataTMP1 = tabelaMemoria.getDate("data_cadastro");
                ProcClass.setData_cadastro(formatoData2.format(dataTMP1));

                ProcClass.setCpf(tabelaMemoria.getString("cpf"));
                ProcClass.setTel(tabelaMemoria.getString("tel"));
                ProcClass.setEmail(tabelaMemoria.getString("email"));
                ProcClass.setSenha(tabelaMemoria.getString("senha"));
                ProcClass.setCep(tabelaMemoria.getString("cep"));
                ProcClass.setNum_residencial(tabelaMemoria.getString("num_residencial"));
                ProcClass.setComplemento(tabelaMemoria.getString("complemento"));
                ProcClass.setPontuacao(tabelaMemoria.getString("pontuacao"));


                return true;
            } else {

                ProcClass = null;
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }


    }


}
