package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import dao.PesqDetalhesProc;
import dao.PesqDetalhesServ;
import dao.PesqDetalhesTrab;
import dao.conectarBD;
import model.contrato;
import model.servico;

public class DetalhesServPendTrab extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    TextView lblTituloTrabPend, lblProcPend, lblTelProcPend, lblPagtoTrabPend,lblObsTrabPend,lblCepTrabPend,lblNumResTrabPend,lblComplTrabPend,lblValorTrabPend;

    PesqDetalhesTrab pesq;
    servico servTela;

    contrato contratoTela;

    Button btnVoltarDetalhesServTrab,btnCadDetalhesTrab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_serv_pend_trab);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        try {

            lblCepTrabPend = (TextView) findViewById(R.id.lblCepTrabPend);

            lblComplTrabPend = (TextView) findViewById(R.id.lblComplTrabPend);

            lblNumResTrabPend = (TextView) findViewById(R.id.lblNumResTrabPend);

            lblObsTrabPend = (TextView) findViewById(R.id.lblObsTrabPend);

            lblPagtoTrabPend = (TextView) findViewById(R.id.lblPagtoTrabPend);

            lblTelProcPend = (TextView) findViewById(R.id.lblTelProcPend);

            lblProcPend = (TextView) findViewById(R.id.lblProcPend);

            lblTituloTrabPend = (TextView) findViewById(R.id.lblTituloTrabPend);

            lblValorTrabPend = (TextView) findViewById(R.id.lblValorTrabPend);

            btnCadDetalhesTrab = (Button) findViewById(R.id.btnCadDetalhesTrab);

            btnVoltarDetalhesServTrab = (Button) findViewById(R.id.btnVoltarDetalhesServTrab);

            //evento click em no botão da tela
            btnCadDetalhesTrab.setOnClickListener(this);

            btnVoltarDetalhesServTrab.setOnClickListener(this);


            // instancia as classes
            pesq = new PesqDetalhesTrab(DetalhesServPendTrab.this);
            servTela = new servico();

            //executa os comandos na classe DetalhesServPend
            pesq.execute().get();
            // retorna os valores recebidos da classe
            servTela = pesq.getServClasse();

            // coloca os dados recebidos da classe nos objetos dateça
            lblValorTrabPend.setText(servTela.getValor());
            lblTituloTrabPend.setText(servTela.getTitulo());
            lblProcPend.setText(servTela.getNome_proc());
            lblTelProcPend.setText(servTela.getTel_proc());
            lblPagtoTrabPend.setText(servTela.getPagto());
            lblObsTrabPend.setText(servTela.getObservacoes());
            lblNumResTrabPend.setText(servTela.getNum_residencial());
            lblComplTrabPend.setText(servTela.getComplemento());
            lblCepTrabPend.setText(servTela.getCep());





        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnVoltarDetalhesServTrab:
                // redireciona a tela de serviços pendentes
                Intent tela = new Intent(this, ServPendTrab.class);
                startActivity(tela);
                break;
            case R.id.btnCadDetalhesTrab:

                // instancia a classe
                conectarBD alt = new conectarBD(this);

                contratoTela = new contrato();


                alt.setContratoClass(contratoTela);

                //executa um comando do conectarBD no banco de dados
                alt.execute(17);
                break;
        }


    }




}

