package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import dao.conectarBD;
import model.trabalhador;

public class RegTrab extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnVoltarLogTrab,btnRegTrab;

    EditText txtNomeTrabReg,txtDataNascTrabReg,txtCPFTrabReg,txtTelTrabReg,txtEmailTrabReg,txtSenhaTrabReg,txtCEPTrabReg,txtNumResTrabReg,txtComplTrabReg;

    RadioButton rbnMascTrabReg,rbnFemTrabReg;

    trabalhador trabTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_trab);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        btnVoltarLogTrab = (Button) findViewById(R.id.btnVoltarLogTrab);
        btnRegTrab = (Button) findViewById(R.id.btnRegTrab);

        txtCEPTrabReg = (EditText) findViewById(R.id.txtCEPTrabReg);
        txtComplTrabReg = (EditText) findViewById(R.id.txtComplTrabReg);
        txtCPFTrabReg = (EditText) findViewById(R.id.txtCPFTrabReg);
        txtDataNascTrabReg = (EditText) findViewById(R.id.txtDataNascTrabReg);
        txtEmailTrabReg= (EditText) findViewById(R.id.txtEmailTrabReg);
        txtNomeTrabReg = (EditText) findViewById(R.id.txtNomeTrabReg);
        txtNumResTrabReg = (EditText) findViewById(R.id.txtNumResTrabReg);
        txtSenhaTrabReg = (EditText) findViewById(R.id.txtSenhaTrabReg);
        txtTelTrabReg = (EditText) findViewById(R.id.txtTelTrabReg);

        rbnFemTrabReg = (RadioButton) findViewById(R.id.rbnFemTrabReg) ;
        rbnMascTrabReg = (RadioButton) findViewById(R.id.rbnMascTrabReg) ;

        //evento click em no botão da tela
        btnVoltarLogTrab.setOnClickListener(this);
        btnRegTrab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnVoltarLogTrab:
                Intent tela = new Intent(this, Activity_Login.class);
                startActivity(tela);
                break;
            case R.id.btnRegTrab:
                // instancia as classes
                conectarBD cad = new conectarBD(this);

                trabalhador trabTela = new trabalhador();

                trabTela.setNome(txtNomeTrabReg.getText().toString());
                trabTela.setSenha(txtSenhaTrabReg.getText().toString());
                trabTela.setCep(txtCEPTrabReg.getText().toString());
                trabTela.setComplemento(txtComplTrabReg.getText().toString());
                trabTela.setCpf(txtCPFTrabReg.getText().toString());
                trabTela.setEmail(txtEmailTrabReg.getText().toString());
                trabTela.setData_nasc(txtDataNascTrabReg.getText().toString());
                trabTela.setNum_residencial(txtNumResTrabReg.getText().toString());
                trabTela.setTel(txtTelTrabReg.getText().toString());
                trabTela.setId_tipo_pacote(1);

                if (rbnFemTrabReg.isChecked() == true)
                {
                    trabTela.setSexo("Feminino");

                }

                else  {
                    trabTela.setSexo("Masculino");
                }
                cad.setTrabClass(trabTela);
                cad.execute(11);

                txtTelTrabReg.setText("");
                txtNumResTrabReg.setText("");
                txtDataNascTrabReg.setText("");
                txtEmailTrabReg.setText("");
                txtCPFTrabReg.setText("");
                txtComplTrabReg.setText("");
                txtCEPTrabReg.setText("");
                txtSenhaTrabReg.setText("");
                txtNomeTrabReg.setText("");
                txtCEPTrabReg.setText("");
                rbnFemTrabReg.setChecked(false);
                rbnMascTrabReg.setChecked(false);

                Intent tela1 = new Intent(this, Activity_Login.class);
                startActivity(tela1);



                break;


        }
    }

}
