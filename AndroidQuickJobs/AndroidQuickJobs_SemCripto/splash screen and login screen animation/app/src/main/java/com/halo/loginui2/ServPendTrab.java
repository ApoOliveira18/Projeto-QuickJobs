package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.ListarServPendTrab;
import dao.ListarServico;
import model.listaservico;
import utils.PesqServ;

public class ServPendTrab extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    ListarServPendTrab listar;
    ListView lvServPendTrab;

    List<listaservico> listaServ;
    ListaServAdapter adListServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_pend_trab);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        try {
            lvServPendTrab = (ListView) findViewById(R.id.lvServPendTrab);

            //instancia a classe
            listar = new ListarServPendTrab(this);

            // retorna os valores da classe
            listar.execute().get();

            // retorna os valores da lista
            listaServ = listar.getListaServ();

            // instancia a lista na classe
            adListServ = new ListaServAdapter(this, listaServ);

            //coloca os valores recebidos na lista
            lvServPendTrab.setAdapter(adListServ);

            //evento click em na lista da tela
            lvServPendTrab.setOnItemClickListener(this);

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listaservico serv = (listaservico) adListServ.getItem(position);

        PesqServ.setId(serv.getId_servico());

        //redireciona a tela detalhes
        Intent tela = new Intent(ServPendTrab.this, DetalhesServPendTrab.class);
        startActivity(tela);
    }
}
