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

    // N O M E A Ç Ã O  D E  O B J E T O S //

    Button btnVoltarTrab, btnRegistrarTrab;

    EditText txtNomeTrab, txtDataNascTrab, txtCPFTrab, txtTelTrab, txtEmailTrab, txtSenhaTrab, txtConfSenhaTrab, txtCEPTrab, txtNumResTrab, txtComplTrab;

    RadioButton rbnMascTrab, rbnFemTrab;

    trabalhador trabTela;

    //////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_trab);

        //  A S S O C I A Ç Ã O  D E  O B J E T O S  //


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

        rbnFemTrab = (RadioButton) findViewById(R.id.rbnFemTrab);
        rbnMascTrab = (RadioButton) findViewById(R.id.rbnMascTrab);

        ////////////////////////////////////////////////

        // Evento dos botões acionáveis
        btnVoltarTrab.setOnClickListener(this);
        btnRegistrarTrab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Estrutura de decisão para os botões
        switch (v.getId()) {

            // Caso seja necessário retornar à tela anterior
            case R.id.btnVoltarTrab:
                Intent tela = new Intent(this, MenuReg.class);
                startActivity(tela);
                break;

            // Caso haja tentativa do registro de um novo trabalhador
            case R.id.btnRegistrarTrab:

                // Chamado do conectarBD
                conectarBD cad = new conectarBD(this);

                // Instância da classe trabalhador
                trabalhador trabTela = new trabalhador();

                // Associação dos dados da tela com os campos da classe
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

                // Caso o sexo seja definido no radiobutton como
                // Feminino
                if (rbnFemTrab.isChecked() == true) {
                    trabTela.setSexo("Feminino");

                }

                // Masculino
                else {
                    trabTela.setSexo("Masculino");
                }
                cad.setTrabClass(trabTela);
                // Execução da ação do conectarBD
                cad.execute(11);

                //  L I M P E Z A  D E  C A M P O S  //
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

                ////////////////////////////////////////
                break;

        }
    }
}
