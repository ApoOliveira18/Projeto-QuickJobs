package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import dao.PesqDetalhesProc;
import dao.conectarBD;
import model.contrato;
import model.servico;

public class DetalhesServProc extends AppCompatActivity implements View.OnClickListener {

    // N O M E A Ç Ã O  D E  O B J E T O S //

    TextView lblTituloProc, lblTrab, lblTelTrab, lblPagtoProc,lblObsProc,lblCepProc,lblNumResProc,lblComplProc,lblValorProc;

    PesqDetalhesProc pesq;
    servico servTela;

    contrato contratoTela;

    Button btnVoltarDetalhesServProc,btnCadDetalhesProc;

    /////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_serv_proc);

        //  A S S O C I A Ç Ã O  D E  O B J E T O S  //
        try {

            lblCepProc = (TextView) findViewById(R.id.lblCepProc);
            lblComplProc = (TextView) findViewById(R.id.lblComplProc);
            lblNumResProc = (TextView) findViewById(R.id.lblNumResProc);
            lblObsProc = (TextView) findViewById(R.id.lblObsProc);
            lblPagtoProc = (TextView) findViewById(R.id.lblPagtoProc);
            lblTelTrab = (TextView) findViewById(R.id.lblTelTrab);
            lblTrab = (TextView) findViewById(R.id.lblTrab);
            lblTituloProc = (TextView) findViewById(R.id.lblTituloProc);
            lblValorProc = (TextView) findViewById(R.id.lblValorProc);

            btnCadDetalhesProc = (Button) findViewById(R.id.btnCadDetalhesProc);
            btnVoltarDetalhesServProc = (Button) findViewById(R.id.btnVoltarDetalhesServProc);
            ////////////////////////////////////////////

            // Evento dos botões acionáveis
            btnVoltarDetalhesServProc.setOnClickListener(this);
            btnCadDetalhesProc.setOnClickListener(this);

            // Pesquisa dos detalhes do serviço postados pelo procurador
            pesq = new PesqDetalhesProc(DetalhesServProc.this);

            // Instância da calsse serviço
            servTela = new servico();

            // Execução da pesquisa
            pesq.execute().get();

            // Recebimento das informações da classe
            servTela = pesq.getServClasse();

            // Associação dos dados da classe com os objetos da tela
            lblValorProc.setText(servTela.getValor());
            lblTituloProc.setText(servTela.getTitulo());
            lblTrab.setText(servTela.getNome_trab());
            lblTelTrab.setText(servTela.getTel_trab());
            lblPagtoProc.setText(servTela.getPagto());
            lblObsProc.setText(servTela.getObservacoes());
            lblNumResProc.setText(servTela.getNum_residencial());
            lblComplProc.setText(servTela.getComplemento());
            lblCepProc.setText(servTela.getCep());

            // Caso haja erro na hora de carregar a lista
        } catch (InterruptedException e) {
            // Exibição do erro
            e.printStackTrace();
            // Caso haja erro na hora da execução dos comandos
        } catch (ExecutionException e) {
            // Exibição do erro
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        // Estrutura de decisão para os botões
        switch (v.getId()) {


            case R.id.btnVoltarDetalhesServProc:
                Intent tela = new Intent(this, ListaServTrab.class);
                startActivity(tela);
                break;
            case R.id.btnCadDetalhesProc:

                conectarBD alt = new conectarBD(this);



                alt.execute(16);
                break;
        }


    }
}

