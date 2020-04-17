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

    // N O M E A Ç Ã O  D E  O B J E T O S //

    Button btnVoltarServ,btnCadServ;

    Spinner spnPagto, spnTipoTrab;

    EditText txtTituloServCad,txtDescServCad,txtValorServCad,txtObsServCad,txtCepServCad,txtNumResServCad,txtComplServCad;

    // Associação de componentes do spinner
    String[] textoPagto = {"Dinheiro","Cartão de Crédito","Cartão de Débito","Boleto Bancário"};

    // Associação de componentes do spinner
    String[] textoTipoTrab = {"Pintor","Eletricista","Encanador"};

    ArrayAdapter<String> listaPagto;

    ArrayAdapter<String> listaTipoTrab;

    servico servTela = new servico();

    //////////////////////////////////////////
    @Override

    //  A S S O C I A Ç Ã O  D E  O B J E T O S  //
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_servico);

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

        listaPagto = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,textoPagto);

        spnPagto.setAdapter(listaPagto);

        listaTipoTrab = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,textoTipoTrab);

        spnTipoTrab.setAdapter(listaTipoTrab);

        btnVoltarServ.setOnClickListener(this);
        btnCadServ.setOnClickListener(this);
        /////////////////////////////////////////////////

        // Escolha do item do spinner
        spnTipoTrab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (1) {
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

        // Escolha do item do spinner
        spnPagto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (1) {
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
        // Estrutura de decisão para os botões
        switch (v.getId()) {

            // Caso seja necessário voltar à tela anterior
            case R.id.btnVoltarServ:
                Intent tela = new Intent(this, TelaProc.class);
                startActivity(tela);
                break;

            // Caso haja tentativa de registro do serviço
            case R.id.btnCadServ:
                // Instância do conectarBD
                conectarBD cad = new conectarBD(this);

                // Associação dos dados com os campos da classe
                servTela.setTitulo(txtTituloServCad.getText().toString());
                servTela.setCep(txtCepServCad.getText().toString());
                servTela.setComplemento(txtComplServCad.getText().toString());
                servTela.setDescricao(txtDescServCad.getText().toString());
                servTela.setNum_residencial(txtNumResServCad.getText().toString());
                servTela.setObservacoes(txtObsServCad.getText().toString());
                servTela.setStatus(0);
                servTela.setValor("");
                servTela.setData_registro("default");

                // Instância da classe Serviço
                cad.setServClass(servTela);
                cad.execute(14);

                //  L I M P E Z A  D O S  C A M P O S  //

                txtCepServCad.setText("");
                txtComplServCad.setText("");
                txtDescServCad.setText("");
                txtNumResServCad.setText("");
                txtObsServCad.setText("");
                txtTituloServCad.setText("");

                /////////////////////////////////////////
                break;
        }
    }
}
