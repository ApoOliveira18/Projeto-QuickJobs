package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import dao.conectarBD;
import model.procurador;
import model.trabalhador;

public class CadTrab extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnVoltarTrab,btnRegistrarTrab;

    EditText txtNomeTrab,txtDataNascTrab,txtCPFTrab,txtTelTrab,txtEmailTrab,txtSenhaTrab,txtConfSenhaTrab,txtCEPTrab,txtNumResTrab,txtComplTrab;

    RadioButton rbnMascTrab,rbnFemTrab;

    trabalhador trabTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_trab);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela


        btnVoltarTrab = (Button) findViewById(R.id.btnVoltarTrab);
        btnRegistrarTrab = (Button) findViewById(R.id.btnRegistrarTrab);

        txtCEPTrab = (EditText) findViewById(R.id.txtCEPTrab);
        txtComplTrab = (EditText) findViewById(R.id.txtComplTrab);
        txtCPFTrab = (EditText) findViewById(R.id.txtCPFTrab);
        txtDataNascTrab = (EditText) findViewById(R.id.txtDataNascTrab);
        txtEmailTrab = (EditText) findViewById(R.id.txtEmailTrab);
        txtNomeTrab = (EditText) findViewById(R.id.txtNomeTrab);
        txtNumResTrab = (EditText) findViewById(R.id.txtNumResTrab);
        txtSenhaTrab = (EditText) findViewById(R.id.txtSenhaTrab);
        txtTelTrab = (EditText) findViewById(R.id.txtTelTrab);

        rbnFemTrab = (RadioButton) findViewById(R.id.rbnFemTrab) ;
        rbnMascTrab = (RadioButton) findViewById(R.id.rbnMascTrab) ;

        //evento click em no botão da tela
        btnVoltarTrab.setOnClickListener(this);
        btnRegistrarTrab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnVoltarTrab:
                // redireciona ao menu do ADM
                Intent tela = new Intent(this, MenuReg.class);
                startActivity(tela);
                break;
            case R.id.btnRegistrarTrab:

                // instancia as classes
                conectarBD cad = new conectarBD(this);

                trabalhador trabTela = new trabalhador();

                trabTela.setNome(txtNomeTrab.getText().toString());
                trabTela.setSenha(txtSenhaTrab.getText().toString());
                trabTela.setCep(txtCEPTrab.getText().toString());
                trabTela.setComplemento(txtComplTrab.getText().toString());
                trabTela.setCpf(txtCPFTrab.getText().toString());
                trabTela.setEmail(txtEmailTrab.getText().toString());
                trabTela.setData_nasc(txtDataNascTrab.getText().toString());
                trabTela.setNum_residencial(txtNumResTrab.getText().toString());
                trabTela.setTel(txtTelTrab.getText().toString());
                trabTela.setId_tipo_pacote(1);

                if (rbnFemTrab.isChecked() == true)
                {
                    trabTela.setSexo("Feminino");

                }

                else  {
                    trabTela.setSexo("Masculino");
                }
                cad.setTrabClass(trabTela);
                cad.execute(11);

                txtNomeTrab.setText("");
                txtSenhaTrab.setText("");
                txtCEPTrab.setText("");
                txtComplTrab.setText("");
                txtCPFTrab.setText("");
                txtEmailTrab.setText("");
                txtDataNascTrab.setText("");
                txtNumResTrab.setText("");
                txtTelTrab.setText("");
                rbnFemTrab.setChecked(false);
                rbnMascTrab.setChecked(false);

                break;


        }
    }
    }

