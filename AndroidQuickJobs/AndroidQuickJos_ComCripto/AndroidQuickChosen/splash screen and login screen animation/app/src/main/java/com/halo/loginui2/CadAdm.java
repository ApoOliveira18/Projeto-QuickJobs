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

    // N O M E A Ç Ã O  D E  O B J E T O S //

    Button btnVoltarAdm,btnRegAdm;

    EditText txtNomeAdm,txtDataNascAdm,txtCPFAdm,txtTelAdm,txtEmailAdm,txtSenhaAdm,txtConfSenhaAdm,txtCEPAdm,txtNumResAdm,txtComplAdm;

    RadioButton rbnMascAdm,rbnFemAdm;

    //////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_adm);

        //  A S S O C I A Ç Ã O  D E  O B J E T O S  //
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

        ////////////////////////////////////////////////////

        // Evento dos botões ativáveis
        btnVoltarAdm.setOnClickListener(this);
        btnRegAdm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // Estrutura de decisão
        switch (v.getId()) {
            // Caso seja necessário voltar para a tela anterior
            case R.id.btnVoltarAdm:
                Intent tela = new Intent(this, MenuReg.class);
                startActivity(tela);
                break;

            // Caso o registro do administrador queira ser efetuado
            case R.id.btnRegAdm:

                // Chamado da classe conectarBD
                conectarBD cad = new conectarBD(this);

                // Instância da classe administrador
                adm admTela = new adm();

                // Associação dos campos da tela com as informações da classe
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


                // Caso o sexo seja definido nos radiobuttons como
                //Feminino
                if (rbnFemAdm.isChecked() == true)
                {
                    admTela.setSexo("Feminino");

                }
                // Masculino
                else  {
                    admTela.setSexo("Masculino");
                }
                // Execução do registro
                cad.setAdmClass(admTela);
                cad.execute(12);

                //  L I M P E Z A  D E  C A M P O S  //
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
                ///////////////////////////////////////
                break;
        }
    }
}
