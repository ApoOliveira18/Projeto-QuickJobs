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

    // N O M E A Ç Ã O  D E  O B J E T O S //

    TextView lblTituloTrabPend, lblProcPend, lblTelProcPend, lblPagtoTrabPend,lblObsTrabPend,lblCepTrabPend,lblNumResTrabPend,lblComplTrabPend,lblValorTrabPend;

    PesqDetalhesTrab pesq;
    servico servTela;

    contrato contratoTela;

    Button btnVoltarDetalhesServTrab,btnCadDetalhesTrab;

    /////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_serv_pend_trab);

        //  A S S O C I A Ç Ã O  D E  O B J E T O S  //
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
            ////////////////////////////////////////////

            // Evento dos botões acionáveis
            btnCadDetalhesTrab.setOnClickListener(this);
            btnVoltarDetalhesServTrab.setOnClickListener(this);
            pesq = new PesqDetalhesTrab(DetalhesServPendTrab.this);

            // Instância da classe serviço
            servTela = new servico();

            // Execução da pesquisa
            pesq.execute().get();

            // Recebimento das informações da classe
            servTela = pesq.getServClasse();

            lblValorTrabPend.setText(servTela.getValor());
            lblTituloTrabPend.setText(servTela.getTitulo());
            lblProcPend.setText(servTela.getNome_proc());
            lblTelProcPend.setText(servTela.getTel_proc());
            lblPagtoTrabPend.setText(servTela.getPagto());
            lblObsTrabPend.setText(servTela.getObservacoes());
            lblNumResTrabPend.setText(servTela.getNum_residencial());
            lblComplTrabPend.setText(servTela.getComplemento());
            lblCepTrabPend.setText(servTela.getCep());

            // Caso haja a interrupção do carregamento dos dados
        } catch (InterruptedException e) {
            // Exibição do erro
            e.printStackTrace();
            // Caso haja erro na hora da execução
        } catch (ExecutionException e) {
            // Exibição do erro
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        // Estrutura de decisão para os botões
        switch (v.getId()) {

            // Caso seja necessário retornar à tela anterior
            case R.id.btnVoltarDetalhesServTrab:
                Intent tela = new Intent(this, ServPendTrab.class);
                startActivity(tela);
                break;

            // Caso haja tentativa de alteração dos dados da tela

            case R.id.btnCadDetalhesTrab:

                // Chamado do conectarBD
                conectarBD alt = new conectarBD(this);

                // Instância da classe contrato
                contratoTela = new contrato();

                // Alteração do status para concluído
                contratoTela.setStatus("concluido");

                alt.setContratoClass(contratoTela);

                // Execução do método do conectarBD
                alt.execute(17);
                break;
        }


    }




}

