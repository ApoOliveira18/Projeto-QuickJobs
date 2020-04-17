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
import model.adm;
import model.trabalhador;

public class PesqAdm extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnVoltarPesqAdm, btnPesqAdm, btnExcluirAdm, btnAlterarAdm;

    EditText txtNomePesqAdm, txtDataNascPesqAdm, txtCPFPesqAdm,
            txtTelPesqAdm, txtEmailPesqAdm, txtSenhaPesqAdm, txtCEPPesqAdm,
            txtNumResPesqAdm, txtComplPesqAdm;

    RadioButton rbnMascPesqAdm, rbnFemPesqAdm;

    adm admTela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesq_adm);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        btnVoltarPesqAdm = (Button) findViewById(R.id.btnVoltarPesqAdm);

        btnPesqAdm = (Button) findViewById(R.id.btnPesqAdm);

        btnExcluirAdm = (Button) findViewById(R.id.btnExcluirAdm);

        btnAlterarAdm = (Button) findViewById(R.id.btnAlterarAdm);

        txtCPFPesqAdm = (EditText) findViewById(R.id.txtCPFPesqAdm);
        txtComplPesqAdm = (EditText) findViewById(R.id.txtComplPesqAdm);
        txtCEPPesqAdm = (EditText) findViewById(R.id.txtCEPPesqAdm);
        txtDataNascPesqAdm = (EditText) findViewById(R.id.txtDataNascPesqAdm);
        txtEmailPesqAdm = (EditText) findViewById(R.id.txtEmailPesqAdm);
        txtNomePesqAdm = (EditText) findViewById(R.id.txtNomePesqAdm);
        txtNumResPesqAdm = (EditText) findViewById(R.id.txtNumResPesqAdm);
        txtSenhaPesqAdm = (EditText) findViewById(R.id.txtSenhaPesqAdm);
        txtTelPesqAdm = (EditText) findViewById(R.id.txtTelPesqAdm);

        rbnFemPesqAdm = (RadioButton) findViewById(R.id.rbnFemPesqAdm);
        rbnMascPesqAdm = (RadioButton) findViewById(R.id.rbnMascPesqAdm);


        // instancia a classe
       admTela = new adm();


        //evento click em no botão da tela
        btnPesqAdm.setOnClickListener(this);
        btnAlterarAdm.setOnClickListener(this);
        btnExcluirAdm.setOnClickListener(this);
        btnVoltarPesqAdm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPesqAdm:

                try {
                    // instancia as classes
                    conectarBD pesqBD = new conectarBD(this);

                    admTela.setNome(txtNomePesqAdm.getText().toString());

                    pesqBD.getAdmClass();

                    pesqBD.setAdmClass(admTela);


                    pesqBD.execute(8).get();

                    admTela = pesqBD.getAdmClass();

                    if (admTela != null) {
                        txtCPFPesqAdm.setText(admTela.getCpf());
                        txtComplPesqAdm.setText(admTela.getComplemento());
                        txtCEPPesqAdm.setText(admTela.getCep());
                        txtDataNascPesqAdm.setText(admTela.getData_nasc());
                        txtEmailPesqAdm.setText(admTela.getEmail());
                        txtNumResPesqAdm.setText(admTela.getNum_residencial());
                        txtSenhaPesqAdm.setText(admTela.getSenha());
                        txtTelPesqAdm.setText(admTela.getTel());



                        if (admTela.getSexo().equals("Masculino")) {
                            rbnMascPesqAdm.setChecked(true);
                        }


                        else  {
                            rbnFemPesqAdm.setChecked(true);
                        }


                    } else {
                        txtNomePesqAdm.setText("");
                        txtTelPesqAdm.setText("");
                        txtSenhaPesqAdm.setText("");
                        txtNumResPesqAdm.setText("");
                        txtEmailPesqAdm.setText("");
                        txtDataNascPesqAdm.setText("");
                        txtCEPPesqAdm.setText("");
                        txtComplPesqAdm.setText("");
                        txtCPFPesqAdm.setText("");
                        rbnMascPesqAdm.setChecked(false);
                        rbnFemPesqAdm.setChecked(false);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btnVoltarPesqAdm:
                Intent tela3 = new Intent(this, MenuReg.class);
                startActivity(tela3);
                break;
            case R.id.btnExcluirAdm:

                conectarBD excluir = new conectarBD(this);
                excluir.setAdmClass(admTela);
                excluir.execute(9);

                txtNomePesqAdm.setText("");
                txtTelPesqAdm.setText("");
                txtSenhaPesqAdm.setText("");
                txtNumResPesqAdm.setText("");
                txtEmailPesqAdm.setText("");
                txtDataNascPesqAdm.setText("");
                txtCEPPesqAdm.setText("");
                txtComplPesqAdm.setText("");
                txtCPFPesqAdm.setText("");
                rbnMascPesqAdm.setChecked(false);
                rbnFemPesqAdm.setChecked(false);
                break;

            case R.id.btnAlterarAdm:

                conectarBD alt = new conectarBD(this);



                admTela.setNome(txtNomePesqAdm.getText().toString());
                admTela.setSenha(txtSenhaPesqAdm.getText().toString());
                admTela.setCep(txtCEPPesqAdm.getText().toString());
                admTela.setComplemento(txtComplPesqAdm.getText().toString());
                admTela.setCpf(txtCPFPesqAdm.getText().toString());
                admTela.setEmail(txtEmailPesqAdm.getText().toString());
                admTela.setData_nasc(txtDataNascPesqAdm.getText().toString());
                admTela.setNum_residencial(txtNumResPesqAdm.getText().toString());
                admTela.setTel(txtTelPesqAdm.getText().toString());

                if (rbnFemPesqAdm.isChecked() == true)
                {
                    admTela.setSexo("Feminino");

                }

                if (rbnMascPesqAdm.isChecked() == true) {
                    admTela.setSexo("Masculino");
                }
                alt.setAdmClass(admTela);

                alt.execute(10);

                txtNomePesqAdm.setText("");
                txtTelPesqAdm.setText("");
                txtSenhaPesqAdm.setText("");
                txtNumResPesqAdm.setText("");
                txtEmailPesqAdm.setText("");
                txtDataNascPesqAdm.setText("");
                txtCEPPesqAdm.setText("");
                txtComplPesqAdm.setText("");
                txtCPFPesqAdm.setText("");
                rbnMascPesqAdm.setChecked(false);
                rbnFemPesqAdm.setChecked(false);



                break;
        }
    }
}
