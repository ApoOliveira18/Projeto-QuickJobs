package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import dao.conectarBD;
import model.Usuario;

public class Registrar extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    EditText txtUsuarioReg, txtSenhaReg, txtSenhaConfirmar, txtEmailReg, txtDataNascReg ;
    Button btnRegistrar, btnVoltarReg;
    RadioButton rbnMasc, rbnFem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela
        txtDataNascReg = (EditText) findViewById(R.id.txtDataNascProc);
        txtEmailReg = (EditText) findViewById(R.id.txtEmailReg);
        txtSenhaConfirmar = (EditText) findViewById(R.id.txtSenhaConfirmar);
         txtSenhaReg= (EditText) findViewById(R.id.txtSenhaReg);
        txtUsuarioReg = (EditText) findViewById(R.id.txtUsuarioReg);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnVoltarReg = (Button) findViewById(R.id.btnVoltarReg);
        rbnFem = (RadioButton) findViewById(R.id.rbnFem);
        rbnMasc = (RadioButton) findViewById(R.id.rbnMasc);

        btnVoltarReg.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.btnVoltarReg:
                Intent tela = new Intent(this, Activity_Login.class);
                startActivity(tela);
                break;

            case R.id.btnRegistrar:

                conectarBD cad = new conectarBD(this);

                Usuario usuTela = new Usuario();

                usuTela.setNome_usu(txtUsuarioReg.getText().toString());
                usuTela.setSenha_usu(Integer.parseInt(txtSenhaReg.getText().toString()));
                usuTela.setEmail_usu((txtEmailReg.getText().toString()));
                usuTela.setDatanasc_usu(txtDataNascReg.getText().toString());

               if (rbnMasc.isChecked() == true ) {

                   usuTela.setSexo_usu("Masculino");

               }

               else {
                   usuTela.setSexo_usu("Feminino");
               }



                cad.execute(0);

                break;


        }


    }
}
