package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import dao.conectarBD;
import model.adm;
import model.procurador;

public class CadAdm extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnVoltarAdm,btnRegAdm;

    EditText txtNomeAdm,txtDataNascAdm,txtCPFAdm,txtTelAdm,txtEmailAdm,txtSenhaAdm,txtConfSenhaAdm,txtCEPAdm,txtNumResAdm,txtComplAdm;

    RadioButton rbnMascAdm,rbnFemAdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_adm);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        btnVoltarAdm = (Button) findViewById(R.id.btnVoltarAdm);
        btnRegAdm = (Button) findViewById(R.id.btnRegAdm);

        txtCEPAdm = (EditText) findViewById(R.id.txtCEPAdm);
        txtComplAdm = (EditText) findViewById(R.id.txtComplAdm);
        txtCPFAdm = (EditText) findViewById(R.id.txtCPFAdm);
        txtDataNascAdm = (EditText) findViewById(R.id.txtDataNascAdm);
        txtEmailAdm = (EditText) findViewById(R.id.txtEmailAdm);
        txtNomeAdm = (EditText) findViewById(R.id.txtNomeAdm);
        txtNumResAdm = (EditText) findViewById(R.id.txtNumResAdm);
        txtSenhaAdm = (EditText) findViewById(R.id.txtSenhaAdm);
        txtTelAdm = (EditText) findViewById(R.id.txtTelAdm);

        rbnFemAdm = (RadioButton) findViewById(R.id.rbnFemAdm) ;
        rbnMascAdm = (RadioButton) findViewById(R.id.rbnMascAdm) ;

        //evento click em no botão da tela
        btnVoltarAdm.setOnClickListener(this);
        btnRegAdm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            // redireciona para o menu do ADM
            case R.id.btnVoltarAdm:
                Intent tela = new Intent(this, MenuReg.class);
                startActivity(tela);
                break;
            case R.id.btnRegAdm:

                // instancia as classes

                conectarBD cad = new conectarBD(this);

                adm admTela = new adm();

                //guarda os valores da tela para executar um comando
                admTela.setNome(txtNomeAdm.getText().toString());
                admTela.setSenha(txtSenhaAdm.getText().toString());
                admTela.setCep(txtCEPAdm.getText().toString());
                admTela.setComplemento(txtComplAdm.getText().toString());
                admTela.setCpf(txtCPFAdm.getText().toString());
                admTela.setEmail(txtEmailAdm.getText().toString());
                admTela.setData_nasc(txtDataNascAdm.getText().toString());
                admTela.setNum_residencial(txtNumResAdm.getText().toString());
                admTela.setTel(txtTelAdm.getText().toString());
                admTela.setData_registro("default");


                if (rbnFemAdm.isChecked() == true)
                {
                    admTela.setSexo("Feminino");

                }

                else  {
                    admTela.setSexo("Masculino");
                }
                cad.setAdmClass(admTela);
                // executa um comando do conectarBD no banco de dados
                cad.execute(12);

                // limpa os objetos na tela
                txtNomeAdm.setText("");
                txtSenhaAdm.setText("");
                txtCPFAdm.setText("");
                txtComplAdm.setText("");
                txtCEPAdm.setText("");
                txtEmailAdm.setText("");
                txtDataNascAdm.setText("");
                txtNumResAdm.setText("");
                txtTelAdm.setText("");
                rbnFemAdm.setChecked(false);
                rbnMascAdm.setChecked(false);
                // }

                //else {
                //  Toast.makeText(getApplicationContext(),"As senhas não conferem", Toast.LENGTH_SHORT).show();

                // }


                break;


        }
    }


}
