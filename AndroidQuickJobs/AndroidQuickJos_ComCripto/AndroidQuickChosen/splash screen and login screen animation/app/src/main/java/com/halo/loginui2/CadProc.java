package com.halo.loginui2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.Usuario;
import model.procurador;

public class CadProc extends AppCompatActivity implements View.OnClickListener {




    // N O M E A Ç Ã O  D E  O B J E T O S //

    Button btnVoltarProc,btnRegistrarProc;

    EditText txtNomeProc,txtDataNascProc,txtCPFProc,txtTelProc,txtEmailProc,txtSenhaProc,txtConfSenhaProc,txtCEPProc,txtNumResProc,txtComplProc;

    RadioButton rbnMascProc,rbnFemProc;

    //////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_proc);


        //  A S S O C I A Ç Ã O  D E  O B J E T O S  //

        btnVoltarProc = (Button) findViewById(R.id.btnVoltarProc);
        btnRegistrarProc = (Button) findViewById(R.id.btnRegistrarProc);

        txtCEPProc = (EditText) findViewById(R.id.txtCEPProc);
        txtComplProc = (EditText) findViewById(R.id.txtComplProc);
        txtCPFProc = (EditText) findViewById(R.id.txtCPFProc);
        txtDataNascProc = (EditText) findViewById(R.id.txtDataNascProc);
        txtEmailProc = (EditText) findViewById(R.id.txtEmailProc);
        txtNomeProc = (EditText) findViewById(R.id.txtNomeProc);
        txtNumResProc = (EditText) findViewById(R.id.txtNumResProc);
        txtSenhaProc = (EditText) findViewById(R.id.txtSenhaProc);
        txtTelProc = (EditText) findViewById(R.id.txtTelProc);

        rbnFemProc = (RadioButton) findViewById(R.id.rbnFemProc) ;
        rbnMascProc = (RadioButton) findViewById(R.id.rbnMascProc) ;

        /////////////////////////////////////////////////

        // Eventos dos botões ativáveis
        btnVoltarProc.setOnClickListener(this);
        btnRegistrarProc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Estrutura de decisão para os botões
        switch (v.getId()) {

            // Caso seja necessário retornar à tela anterior
            case R.id.btnVoltarProc:
                Intent tela = new Intent(this, MenuReg.class);
                startActivity(tela);
                break;

            // Caso haja tentativa de registro do procurador
            case R.id.btnRegistrarProc:

                // Chamado do conectarBD
                conectarBD cad = new conectarBD(this);

                // Instância da classe procurador
                procurador procTela = new procurador();

                // Associação dos caracteres inserido na tela com os campos da classe
                procTela.setNome(txtNomeProc.getText().toString());
                procTela.setSenha(txtSenhaProc.getText().toString());
                procTela.setCep(txtCEPProc.getText().toString());
                procTela.setComplemento(txtComplProc.getText().toString());
                procTela.setCpf(txtCPFProc.getText().toString());
                procTela.setEmail(txtEmailProc.getText().toString());
                procTela.setData_nasc(txtDataNascProc.getText().toString());
                procTela.setNum_residencial(txtNumResProc.getText().toString());
                procTela.setTel(txtTelProc.getText().toString());
                procTela.setFoto("foto");
                procTela.setPontuacao("10");

                // Caso o sexo no radiobutton seja definido como
                // Feminino
                if (rbnFemProc.isChecked() == true)
                {
                    procTela.setSexo("Feminino");

                }

                // Masculino
                else  {
                    procTela.setSexo("Masculino");
                }
                cad.setProcClass(procTela);
                cad.execute(0);

                //  L I M P E Z A  D E  C A M P O S  //
                txtNomeProc.setText("");
                txtSenhaProc.setText("");
                txtComplProc.setText("");
                txtCEPProc.setText("");
                txtCPFProc.setText("");
                txtEmailProc.setText("");
                txtDataNascProc.setText("");
                txtNumResProc.setText("");
                txtTelProc.setText("");
                rbnFemProc.setChecked(false);
                rbnMascProc.setChecked(false);

                ///////////////////////////////////////
                break;
        }
    }
}
