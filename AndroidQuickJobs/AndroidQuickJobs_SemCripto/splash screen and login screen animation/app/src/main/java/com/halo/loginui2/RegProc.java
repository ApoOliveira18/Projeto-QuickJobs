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

public class RegProc extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnVoltarLogProc,btnRegProc;

    EditText txtNomeProcReg,txtDataNascProcReg,txtCPFProcReg,txtTelProcReg,txtEmailProcReg,txtSenhaProcReg,txtConfSenhaProcReg,txtCEPProcReg,txtNumResProcReg,txtComplProcReg;

    RadioButton rbnMascProcReg,rbnFemProcReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_proc);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        btnVoltarLogProc = (Button) findViewById(R.id.btnVoltarLogProc);
        btnRegProc = (Button) findViewById(R.id.btnRegProc);

        txtCEPProcReg = (EditText) findViewById(R.id.txtCEPProcReg);
        txtComplProcReg = (EditText) findViewById(R.id.txtComplProcReg);
        txtCPFProcReg = (EditText) findViewById(R.id.txtCPFProcReg);
        txtDataNascProcReg = (EditText) findViewById(R.id.txtDataNascProcReg);
        txtEmailProcReg = (EditText) findViewById(R.id.txtEmailProcReg);
        txtNomeProcReg = (EditText) findViewById(R.id.txtNomeProcReg);
        txtNumResProcReg = (EditText) findViewById(R.id.txtNumResProcReg);
        txtSenhaProcReg = (EditText) findViewById(R.id.txtSenhaProcReg);
        txtTelProcReg = (EditText) findViewById(R.id.txtTelProcReg);

        rbnFemProcReg = (RadioButton) findViewById(R.id.rbnFemProcReg) ;
        rbnMascProcReg = (RadioButton) findViewById(R.id.rbnMascProcReg) ;

        //evento click em no botão da tela
        btnVoltarLogProc.setOnClickListener(this);
        btnRegProc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnVoltarLogProc:
                Intent tela = new Intent(this, Activity_Login.class);
                startActivity(tela);
                break;
            case R.id.btnRegProc:

                // instancia as classes
                conectarBD cad = new conectarBD(this);

                procurador procTela = new procurador();

                procTela.setNome(txtNomeProcReg.getText().toString());
                procTela.setSenha(txtSenhaProcReg.getText().toString());
                procTela.setCep(txtCEPProcReg.getText().toString());
                procTela.setComplemento(txtComplProcReg.getText().toString());
                procTela.setCpf(txtCPFProcReg.getText().toString());
                procTela.setEmail(txtEmailProcReg.getText().toString());
                procTela.setData_nasc(txtDataNascProcReg.getText().toString());
                procTela.setNum_residencial(txtNumResProcReg.getText().toString());
                procTela.setTel(txtTelProcReg.getText().toString());
                procTela.setFoto("foto");
                procTela.setPontuacao("10");

                if (rbnFemProcReg.isChecked() == true)
                {
                    procTela.setSexo("Feminino");

                }

                else  {
                    procTela.setSexo("Masculino");
                }
                cad.setProcClass(procTela);
                cad.execute(0);

                txtTelProcReg.setText("");
                txtNumResProcReg.setText("");
                txtDataNascProcReg.setText("");
                txtEmailProcReg.setText("");
                txtCPFProcReg.setText("");
                txtComplProcReg.setText("");
                txtSenhaProcReg.setText("");
                txtNomeProcReg.setText("");
                txtCEPProcReg.setText("");
                rbnFemProcReg.setChecked(false);
                rbnMascProcReg.setChecked(false);

                Intent tela1 = new Intent(this, Activity_Login.class);
                startActivity(tela1);

                break;


        }
    }


}

