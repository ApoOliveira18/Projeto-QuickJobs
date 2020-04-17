package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.procurador;
import model.trabalhador;

public class PesqTrab extends AppCompatActivity implements View.OnClickListener {

    Button btnVoltarPesqTrab, btnPesqTrab, btnExcluirTrab, btnAlterarTrab;

    EditText txtNomePesqTrab, txtDataNascPesqTrab, txtCPFPesqTrab,
            txtTelPesqTrab, txtEmailPesqTrab, txtSenhaPesqTrab, txtCEPPesqTrab,
            txtNumResPesqTrab, txtComplPesqTrab;

    RadioButton rbnMascPesqTrab, rbnFemPesqTrab;

    trabalhador trabTela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesq_trab);

        btnVoltarPesqTrab = (Button) findViewById(R.id.btnVoltarPesqTrab);

        btnPesqTrab = (Button) findViewById(R.id.btnPesqTrab);

        btnExcluirTrab = (Button) findViewById(R.id.btnExcluirTrab);

        btnAlterarTrab = (Button) findViewById(R.id.btnAlterarTrab);

        txtCPFPesqTrab = (EditText) findViewById(R.id.txtCPFPesqTrab);
        txtComplPesqTrab = (EditText) findViewById(R.id.txtComplPesqTrab);
        txtCEPPesqTrab = (EditText) findViewById(R.id.txtCEPPesqTrab);
        txtDataNascPesqTrab = (EditText) findViewById(R.id.txtDataNascPesqTrab);
        txtEmailPesqTrab = (EditText) findViewById(R.id.txtEmailPesqTrab);
        txtNomePesqTrab = (EditText) findViewById(R.id.txtNomePesqTrab);
        txtNumResPesqTrab = (EditText) findViewById(R.id.txtNumResPesqTrab);
        txtSenhaPesqTrab = (EditText) findViewById(R.id.txtSenhaPesqTrab);
        txtTelPesqTrab = (EditText) findViewById(R.id.txtTelPesqTrab);

        rbnFemPesqTrab = (RadioButton) findViewById(R.id.rbnFemPesqTrab);
        rbnMascPesqTrab = (RadioButton) findViewById(R.id.rbnMascPesqTrab);

        trabTela = new trabalhador();


        btnPesqTrab.setOnClickListener(this);
        btnAlterarTrab.setOnClickListener(this);
        btnExcluirTrab.setOnClickListener(this);
        btnVoltarPesqTrab.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnPesqTrab:

                try {
                    conectarBD pesqBD = new conectarBD(this);

                    trabTela.setNome(txtNomePesqTrab.getText().toString());

                    pesqBD.getTrabClass();

                    pesqBD.setTrabClass(trabTela);


                    pesqBD.execute(5).get();

                    trabTela = pesqBD.getTrabClass();

                    if (trabTela != null) {
                        txtCPFPesqTrab.setText(trabTela.getCpf());
                        txtComplPesqTrab.setText(trabTela.getComplemento());
                        txtCEPPesqTrab.setText(trabTela.getCep());
                        txtDataNascPesqTrab.setText(trabTela.getData_nasc());
                        txtEmailPesqTrab.setText(trabTela.getEmail());
                        txtNumResPesqTrab.setText(trabTela.getNum_residencial());
                        txtSenhaPesqTrab.setText(trabTela.getSenha());
                        txtTelPesqTrab.setText(trabTela.getTel());




                        if (trabTela.getSexo().equals("Masculino")) {
                            rbnMascPesqTrab.setChecked(true);
                        }


                        else  {
                            rbnFemPesqTrab.setChecked(true);
                        }


                    } else {
                        txtNomePesqTrab.setText("");
                        txtTelPesqTrab.setText("");
                        txtSenhaPesqTrab.setText("");
                        txtNumResPesqTrab.setText("");
                        txtEmailPesqTrab.setText("");
                        txtDataNascPesqTrab.setText("");
                        txtCEPPesqTrab.setText("");
                        txtComplPesqTrab.setText("");
                        txtCPFPesqTrab.setText("");
                        rbnMascPesqTrab.setChecked(false);
                        rbnFemPesqTrab.setChecked(false);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btnVoltarPesqTrab:
                Intent tela3 = new Intent(this, MenuReg.class);
                startActivity(tela3);
                break;
            case R.id.btnExcluirTrab:

                conectarBD excluir = new conectarBD(this);
                excluir.setTrabClass(trabTela);
                excluir.execute(6);

                txtNomePesqTrab.setText("");
                txtTelPesqTrab.setText("");
                txtSenhaPesqTrab.setText("");
                txtNumResPesqTrab.setText("");
                txtEmailPesqTrab.setText("");
                txtDataNascPesqTrab.setText("");
                txtCEPPesqTrab.setText("");
                txtComplPesqTrab.setText("");
                txtCPFPesqTrab.setText("");
                rbnMascPesqTrab.setChecked(false);
                rbnFemPesqTrab.setChecked(false);
                break;

            case R.id.btnAlterarTrab:

                conectarBD alt = new conectarBD(this);



                trabTela.setNome(txtNomePesqTrab.getText().toString());
                trabTela.setSenha(txtSenhaPesqTrab.getText().toString());
                trabTela.setCep(txtCEPPesqTrab.getText().toString());
                trabTela.setComplemento(txtComplPesqTrab.getText().toString());
                trabTela.setCpf(txtCPFPesqTrab.getText().toString());
                trabTela.setEmail(txtEmailPesqTrab.getText().toString());
                trabTela.setData_nasc(txtDataNascPesqTrab.getText().toString());
                trabTela.setNum_residencial(txtNumResPesqTrab.getText().toString());
                trabTela.setTel(txtTelPesqTrab.getText().toString());

                if (rbnFemPesqTrab.isChecked() == true)
                {
                    trabTela.setSexo("Feminino");

                }

                if (rbnMascPesqTrab.isChecked() == true) {
                    trabTela.setSexo("Masculino");
                }
                alt.setTrabClass(trabTela);

                alt.execute(7);

                txtNomePesqTrab.setText("");
                txtTelPesqTrab.setText("");
                txtSenhaPesqTrab.setText("");
                txtNumResPesqTrab.setText("");
                txtEmailPesqTrab.setText("");
                txtDataNascPesqTrab.setText("");
                txtCEPPesqTrab.setText("");
                txtComplPesqTrab.setText("");
                txtCPFPesqTrab.setText("");
                rbnMascPesqTrab.setChecked(false);
                rbnFemPesqTrab.setChecked(false);



                break;

        }
    }
}
