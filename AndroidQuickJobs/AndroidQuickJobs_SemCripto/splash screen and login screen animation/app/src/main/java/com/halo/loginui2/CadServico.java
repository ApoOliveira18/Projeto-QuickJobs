package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import dao.conectarBD;
import model.servico;

public class CadServico extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnVoltarServ,btnCadServ;

    Spinner spnPagto, spnTipoTrab;

    EditText txtTituloServCad,txtDescServCad,txtValorServCad,txtObsServCad,txtCepServCad,txtNumResServCad,txtComplServCad;

     String[] textoPagto = {"Dinheiro","Cartão de Crédito","Cartão de Débito","Boleto Bancário"};

    String[] textoTipoTrab = {"Pintor","Eletricista","Encanador"};

    ArrayAdapter<String> listaPagto;

    ArrayAdapter<String> listaTipoTrab;

    servico servTela = new servico();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_servico);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        btnCadServ = (Button) findViewById(R.id.btnCadServ);
        btnVoltarServ = (Button) findViewById(R.id.btnVoltarServ);

        txtCepServCad = (EditText) findViewById(R.id.txtCepServCad);
        txtComplServCad = (EditText) findViewById(R.id.txtComplServCad);
        txtDescServCad = (EditText) findViewById(R.id.txtDescServCad);
        txtNumResServCad = (EditText) findViewById(R.id.txtNumResServCad);
        txtObsServCad = (EditText) findViewById(R.id.txtObsServCad);
        txtTituloServCad = (EditText) findViewById(R.id.txtTituloServCad);

        spnPagto = (Spinner) findViewById(R.id.spnPagto) ;
        spnTipoTrab = (Spinner) findViewById(R.id.spnTipoTrab) ;

        // Coloca os campos e valores no spinner
        listaPagto = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,textoPagto);

        spnPagto.setAdapter(listaPagto);

        listaTipoTrab = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,textoTipoTrab);

        spnTipoTrab.setAdapter(listaTipoTrab);

        //evento click em no botão da tela
        btnVoltarServ.setOnClickListener(this);
        btnCadServ.setOnClickListener(this);

        spnTipoTrab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (1) {
                    // quando for executar um insert no banco de dados coloca o respectivo id
                    case 0:
                        servTela.setId_tipo_trab(11);
                        break;
                    case 1:
                        servTela.setId_tipo_trab(12);
                        break;
                    case 2:
                        servTela.setId_tipo_trab(13);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnPagto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (1) {
                    // quando for executar um insert no banco de dados coloca o respectivo id
                    case 0:
                        servTela.setId_pagamento(5);
                        break;
                    case 1:
                        servTela.setId_pagamento(6);
                        break;
                    case 2:
                        servTela.setId_pagamento(7);
                        break;
                    case 3:
                        servTela.setId_pagamento(8);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.btnVoltarServ:
                // retorna a tela do procurador
                Intent tela = new Intent(this, TelaProc.class);
                startActivity(tela);
                break;
            case R.id.btnCadServ:


                // instancia a classe
                conectarBD cad = new conectarBD(this);

                // guarda os valores recebidos na tela para executar um comando
                servTela.setTitulo(txtTituloServCad.getText().toString());
                servTela.setCep(txtCepServCad.getText().toString());
                servTela.setComplemento(txtComplServCad.getText().toString());
                servTela.setDescricao(txtDescServCad.getText().toString());
                servTela.setNum_residencial(txtNumResServCad.getText().toString());
                servTela.setObservacoes(txtObsServCad.getText().toString());
                servTela.setStatus(0);
                servTela.setValor("");
                servTela.setData_registro("default");

                cad.setServClass(servTela);
                // executa um comando no conectarBD no banco de dados
                cad.execute(14);

                //limpa os objetos da tela
                txtCepServCad.setText("");
                txtComplServCad.setText("");
                txtDescServCad.setText("");
                txtNumResServCad.setText("");
                txtObsServCad.setText("");
                txtTituloServCad.setText("");


                break;


        }
    }
}
