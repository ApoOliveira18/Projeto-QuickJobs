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

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    TextView lblTituloProc, lblTrab, lblTelTrab, lblPagtoProc,lblObsProc,lblCepProc,lblNumResProc,lblComplProc,lblValorProc;

    PesqDetalhesProc pesq;
    servico servTela;

    contrato contratoTela;

    Button btnVoltarDetalhesServProc,btnCadDetalhesProc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_serv_proc);


        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela
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

            //evento click em no botão da tela
            btnVoltarDetalhesServProc.setOnClickListener(this);

            btnCadDetalhesProc.setOnClickListener(this);


            // instancia as classes
            pesq = new PesqDetalhesProc(DetalhesServProc.this);
            servTela = new servico();

            //executa os comandos na classe DetalhesServPend
            pesq.execute().get();

            // retorna os valores recebidos da classe
            servTela = pesq.getServClasse();

            // coloca os dados recebidos da classe nos objetos dateça
            lblValorProc.setText(servTela.getValor());
            lblTituloProc.setText(servTela.getTitulo());
            lblTrab.setText(servTela.getNome_trab());
            lblTelTrab.setText(servTela.getTel_trab());
            lblPagtoProc.setText(servTela.getPagto());
            lblObsProc.setText(servTela.getObservacoes());
            lblNumResProc.setText(servTela.getNum_residencial());
            lblComplProc.setText(servTela.getComplemento());
            lblCepProc.setText(servTela.getCep());





        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.btnVoltarDetalhesServProc:
                // redireciona a tela de serviços do trabalhador
                Intent tela = new Intent(this, ListaServTrab.class);
                startActivity(tela);
                break;
            case R.id.btnCadDetalhesProc:

                conectarBD alt = new conectarBD(this);

                //executa um comando do conectarBD no banco de dados
                alt.execute(16);
                break;
        }


    }
}

