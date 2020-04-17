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


// Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnVoltarProc,btnRegistrarProc;

    EditText txtNomeProc,txtDataNascProc,txtCPFProc,txtTelProc,txtEmailProc,txtSenhaProc,txtConfSenhaProc,txtCEPProc,txtNumResProc,txtComplProc;

    RadioButton rbnMascProc,rbnFemProc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_proc);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

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

        //evento click em no botão da tela
        btnVoltarProc.setOnClickListener(this);
        btnRegistrarProc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnVoltarProc:
                // redireciona ao menu do ADM
                Intent tela = new Intent(this, MenuReg.class);
                startActivity(tela);
                break;
            case R.id.btnRegistrarProc:


                // instancia as classes
                    conectarBD cad = new conectarBD(this);

                    procurador procTela = new procurador();

                //guarda os valores da tela para executar um comando
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

                    if (rbnFemProc.isChecked() == true)
                    {
                        procTela.setSexo("Feminino");

                    }

                    else  {
                        procTela.setSexo("Masculino");
                    }
                    cad.setProcClass(procTela);

                    // executa um comando no banco de dados
                    cad.execute(0);

                    // limpa os objetos na tela
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
               // }

                //else {
                  //  Toast.makeText(getApplicationContext(),"As senhas não conferem", Toast.LENGTH_SHORT).show();

               // }


                break;


        }
    }



}
