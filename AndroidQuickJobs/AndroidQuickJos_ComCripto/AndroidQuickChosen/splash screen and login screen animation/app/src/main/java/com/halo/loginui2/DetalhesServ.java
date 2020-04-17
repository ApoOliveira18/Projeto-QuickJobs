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

    // N O M E A Ç Ã O  D E  O B J E T O S //

    TextView lblTituloServ, lblProc, lblTelProcServ, lblPagto, lblDescServ, lblObsServ, lblDataRegServ, lblCepServ, lblNumResServ, lblComplServ;

    EditText txtValorServ;

    PesqDetalhesServ pesq;
    servico servTela;

    Button btnVoltarDetalhesServ, btnCadDetalhesServ;

    //////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_serv);
        //  A S S O C I A Ç Ã O  D E  O B J E T O S  //

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

            ////////////////////////////////////////////////

            // Evento dos botões acionáveis
            btnVoltarDetalhesServ.setOnClickListener(this);
            btnCadDetalhesServ.setOnClickListener(this);

            // Efetuar comando de pesquisa dos itens descritos
            pesq = new PesqDetalhesServ(DetalhesServ.this);
            // Instância da classe Serviço
            servTela = new servico();
            // Execução da pesquisa do no BD
            pesq.execute().get();

            // Recebimento das informações da armazenadas na classe
            servTela = pesq.getServClasse();

            // Associação dos dados da classe nos objetos da tela
            lblTituloServ.setText(servTela.getTitulo());
            lblTelProcServ.setText(servTela.getTel_proc());
            lblObsServ.setText(servTela.getObservacoes());
            lblNumResServ.setText(servTela.getNum_residencial());
            lblPagto.setText(servTela.getPagto());
            lblDataRegServ.setText(servTela.getData_registro());
            lblComplServ.setText(servTela.getComplemento());
            lblCepServ.setText(servTela.getCep());
            lblProc.setText(servTela.getNome_proc());

            // Caso haja a interrupção no carregamento dos dados
        } catch (InterruptedException e) {
            // Exibição do erro
            e.printStackTrace();
            // Caso haja erro na hora de excutar o comando
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
            case R.id.btnVoltarDetalhesServ:
                Intent tela = new Intent(this, ListaServTrab.class);
                startActivity(tela);
                break;

            // Caso haja tentativa de alteração nos dados da tela
            case R.id.btnCadDetalhesServ:
                // Chamado do conectarBD -> método "alt"

                conectarBD alt = new conectarBD(this);

                // Imposição do valor desejado
                servTela.setValor(txtValorServ.getText().toString());

                servTela.setStatus(1);

                // Instância da classe servTela
                alt.setServClass(servTela);

                // Execução do método dentro do conectarBD
                alt.execute(15);

                txtValorServ.setText("");
                break;
        }
    }
}
