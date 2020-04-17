package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.ListarServico;
import model.listaservico;
import utils.PesqServ;

public class ListaServTrab extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListarServico listar;
    ListView lvServico;

    List<listaservico> listaServ;
    ListaServAdapter adListServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_serv_trab);

        try {
            lvServico = (ListView) findViewById(R.id.lvServico);

            listar = new ListarServico(this);

            listar.execute().get();

            listaServ = listar.getListaServ();

            adListServ = new ListaServAdapter(this, listaServ);

            lvServico.setAdapter(adListServ);

            lvServico.setOnItemClickListener(this);

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

        // Toast.makeText(this, "Evento: " + evento.getNomeEven() + "ID: " + evento.getId(),
        //    Toast.LENGTH_LONG).show();

        PesqServ.setId(serv.getId_servico());

        Intent tela = new Intent(ListaServTrab.this, DetalhesServ.class);
        startActivity(tela);

    }
}
