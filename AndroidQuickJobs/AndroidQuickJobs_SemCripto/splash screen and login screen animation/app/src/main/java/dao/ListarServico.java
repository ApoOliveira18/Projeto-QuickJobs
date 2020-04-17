package dao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.listaservico;
import utils.PesqTrab;
import utils.pesqProcPerfil;

public class ListarServico extends AsyncTask<Object, Object, Boolean> {

    private Connection conexao;

    private Context tela;

    private ProgressDialog dialogo;

    private List<listaservico> listaServ = new ArrayList<listaservico>();

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public Context getTela() {
        return tela;
    }

    public void setTela(Context tela) {
        this.tela = tela;
    }

    public ProgressDialog getDialogo() {
        return dialogo;
    }

    public void setDialogo(ProgressDialog dialogo) {
        this.dialogo = dialogo;
    }



    public ListarServico(Context tela) {
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

    public void disconnect(){
        try {
            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    protected Boolean doInBackground(Object... objects) {


        Boolean resp;

        connect();

        resp = listarTudo();

        return resp;

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
        // TODO Auto-generated method stub
        super.onPostExecute(result);

        if(result == false)
            Toast.makeText(tela,
                    "ERRO!!!",
                    Toast.LENGTH_SHORT).show();

        dialogo.dismiss();

        disconnect();
    }


    // faz um select retornando os serviços com status 0 (disponíveis)
    public Boolean listarTudo()
    {
        try{
            String sql = "select * from servico where status=0";
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet tab = comando.executeQuery();

            while(tab.next()){
                listaservico servico = new listaservico();

                servico.setId_servico(tab.getInt("id_servico"));
                servico.setTitulo(tab.getString("titulo"));
                servico.setDescricao(tab.getString("descricao"));


                listaServ.add(servico);
            }
            return true;

        }catch(SQLException e){
            e.printStackTrace();

            return false;
        }
    }


    public List<listaservico> getListaServ() {
        return listaServ;
    }

    public void setListaServ(List<listaservico> listaServ) {
        this.listaServ = listaServ;
    }
}
