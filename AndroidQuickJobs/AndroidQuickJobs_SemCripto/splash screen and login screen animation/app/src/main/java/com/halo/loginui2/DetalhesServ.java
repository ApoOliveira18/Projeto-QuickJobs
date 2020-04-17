package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import dao.PesqDetalhesServ;
import dao.conectarBD;
import model.servico;

public class DetalhesServ extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    TextView lblTituloServ, lblProc, lblTelProcServ, lblPagto, lblDescServ,lblObsServ,lblDataRegServ,lblCepServ,lblNumResServ,lblComplServ;

    EditText txtValorServ;

    PesqDetalhesServ pesq;
    servico servTela;

    Button btnVoltarDetalhesServ,btnCadDetalhesServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_serv);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        try {

        lblCepServ = (TextView) findViewById(R.id.lblCepServ);

        lblComplServ = (TextView) findViewById(R.id.lblComplServ);

        lblDataRegServ = (TextView) findViewById(R.id.lblDataRegServ);

        lblNumResServ = (TextView) findViewById(R.id.lblNumResServ);

        lblObsServ = (TextView) findViewById(R.id.lblObsServ);

        lblPagto = (TextView) findViewById(R.id.lblPagto);

        lblProc = (TextView) findViewById(R.id.lblProc);

        lblTelProcServ = (TextView) findViewById(R.id.lblTelProcServ);

        lblTituloServ = (TextView) findViewById(R.id.lblTituloServ);

        txtValorServ = (EditText) findViewById(R.id.txtValorServ);

        btnVoltarDetalhesServ = (Button) findViewById(R.id.btnVoltarDetalhesServ);

        btnCadDetalhesServ = (Button) findViewById(R.id.btnCadDetalhesServ);

            //evento click em no botão da tela
        btnVoltarDetalhesServ.setOnClickListener(this);

        btnCadDetalhesServ.setOnClickListener(this);


            // instancia as classes
        pesq = new PesqDetalhesServ(DetalhesServ.this);
        servTela = new servico();

        //executa os comandos na classe PesqDetalhesServ
        pesq.execute().get();

        // retorna os valores recebidos da classe
        servTela = pesq.getServClasse();


        // coloca os dados recebidos da classe nos objetos dateça
            lblTituloServ.setText(servTela.getTitulo());
            lblTelProcServ.setText(servTela.getTel_proc());
            lblObsServ.setText(servTela.getObservacoes());
            lblNumResServ.setText(servTela.getNum_residencial());
            lblPagto.setText(servTela.getPagto());
            lblDataRegServ.setText(servTela.getData_registro());
            lblComplServ.setText(servTela.getComplemento());
            lblCepServ.setText(servTela.getCep());
            lblProc.setText(servTela.getNome_proc());





        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnVoltarDetalhesServ:
                // retorna a lista de serviços
                Intent tela = new Intent(this, ListaServTrab.class);
                startActivity(tela);
                break;
            case R.id.btnCadDetalhesServ:

                // instancia a classe
                conectarBD alt = new conectarBD(this);

                servTela.setValor(txtValorServ.getText().toString());

                servTela.setStatus(1);

                alt.setServClass(servTela);

                // executa um comando do conectarBD no banco de dados
                alt.execute(15);

                // limpa o objeto na tela
                txtValorServ.setText("");
                break;
        }
    }
}
