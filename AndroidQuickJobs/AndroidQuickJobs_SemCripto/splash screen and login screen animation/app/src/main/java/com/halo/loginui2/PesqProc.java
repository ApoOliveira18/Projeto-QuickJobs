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

public class PesqProc extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnVoltarProcPesq,btnPesqProc, btnExcluirProc,btnAlterarProc;

    EditText txtPesqNomeProc,txtDataPesqNascProc,txtPesqCPFProc,
            txtPesqTelProc,txtPesqEmailProc,txtPesqSenhaProc,txtPesqCEPProc,
            txtPesqNumResProc,txtPesqComplProc;

    RadioButton rbnPesqMascProc,rbnPesqFemProc;



    procurador procTela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesq_proc);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela
        btnVoltarProcPesq = (Button) findViewById(R.id.btnVoltarProcPesq);

        btnPesqProc = (Button) findViewById(R.id.btnPesqProc);

        btnExcluirProc= (Button) findViewById(R.id.btnExcluirProc);

        btnAlterarProc = (Button) findViewById(R.id.btnAlterarProc);

        txtPesqCEPProc = (EditText) findViewById(R.id.txtPesqCEPProc);
        txtPesqComplProc = (EditText) findViewById(R.id.txtPesqComplProc);
        txtPesqCPFProc = (EditText) findViewById(R.id.txtPesqCPFProc);
        txtDataPesqNascProc = (EditText) findViewById(R.id.txtDataPesqNascProc);
        txtPesqEmailProc = (EditText) findViewById(R.id.txtPesqEmailProc);
        txtPesqNomeProc = (EditText) findViewById(R.id.txtPesqNomeProc);
        txtPesqNumResProc = (EditText) findViewById(R.id.txtPesqNumResProc);
        txtPesqSenhaProc = (EditText) findViewById(R.id.txtPesqSenhaProc);
        txtPesqTelProc = (EditText) findViewById(R.id.txtPesqTelProc);

        rbnPesqFemProc = (RadioButton) findViewById(R.id.rbnPesqFemProc) ;
        rbnPesqMascProc = (RadioButton) findViewById(R.id.rbnPesqMascProc) ;


        // instancia as classes
        procTela = new procurador();




        //evento click em no botão da tela
        btnPesqProc.setOnClickListener(this);
        btnAlterarProc.setOnClickListener(this);
        btnExcluirProc.setOnClickListener(this);
        btnVoltarProcPesq.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnPesqProc:

                try {
                    // instancia as classes
                    conectarBD pesqBD = new conectarBD(this);

                    procTela.setNome(txtPesqNomeProc.getText().toString());

                    pesqBD.getProcClass();

                    pesqBD.setProcClass(procTela);


                    pesqBD.execute(2).get();

                    procTela = pesqBD.getProcClass();

                    if (procTela != null) {
                        txtPesqCEPProc.setText(procTela.getCep());
                        txtPesqComplProc.setText(procTela.getComplemento());
                        txtDataPesqNascProc.setText(procTela.getData_nasc());
                        txtPesqCPFProc.setText(procTela.getCpf());
                        txtPesqEmailProc.setText(procTela.getEmail());
                        txtPesqNumResProc.setText(procTela.getNum_residencial());
                        txtPesqSenhaProc.setText(procTela.getSenha());
                        txtPesqTelProc.setText(procTela.getTel());


                        if (procTela.getSexo().equals("Masculino") ) {
                            rbnPesqMascProc.setChecked(true);
                        }

                        else {
                            rbnPesqFemProc.setChecked(true);
                        }




                    } else {
                        txtPesqSenhaProc.setText("");
                        txtPesqNumResProc.setText("");
                        txtPesqEmailProc.setText("");
                        txtPesqCPFProc.setText("");
                        txtDataPesqNascProc.setText("");
                        txtPesqComplProc.setText("");
                        txtPesqCEPProc.setText("");
                        txtPesqTelProc.setText("");
                        txtPesqNomeProc.setText("");
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btnVoltarProcPesq:
                Intent tela3 = new Intent(this, MenuReg.class);
                startActivity(tela3);
                break;
            case R.id.btnExcluirProc:

                conectarBD excluir = new conectarBD(this);
                excluir.setProcClass(procTela);
                excluir.execute(3);

                txtPesqSenhaProc.setText("");
                txtPesqNumResProc.setText("");
                txtPesqEmailProc.setText("");
                txtPesqCPFProc.setText("");
                txtDataPesqNascProc.setText("");
                txtPesqComplProc.setText("");
                txtPesqCEPProc.setText("");
                txtPesqTelProc.setText("");
                txtPesqNomeProc.setText("");



                break;
            case R.id.btnAlterarProc:

                conectarBD alt = new conectarBD(this);


                procTela.setNome(txtPesqNomeProc.getText().toString());
                procTela.setSenha(txtPesqSenhaProc.getText().toString());
                procTela.setCep(txtPesqCEPProc.getText().toString());
                procTela.setComplemento(txtPesqComplProc.getText().toString());
                procTela.setCpf(txtPesqCPFProc.getText().toString());
                procTela.setEmail(txtPesqEmailProc.getText().toString());
                procTela.setData_nasc(txtDataPesqNascProc.getText().toString());
                procTela.setNum_residencial(txtPesqNumResProc.getText().toString());
                procTela.setTel(txtPesqTelProc.getText().toString());

                if (rbnPesqFemProc.isChecked() == true)
                {
                    procTela.setSexo("Feminino");

                }

                if (rbnPesqMascProc.isChecked() == true) {
                    procTela.setSexo("Masculino");
                }
                alt.setProcClass(procTela);

                alt.execute(4);

                txtPesqSenhaProc.setText("");
                txtPesqNumResProc.setText("");
                txtPesqEmailProc.setText("");
                txtPesqCPFProc.setText("");
                txtDataPesqNascProc.setText("");
                txtPesqComplProc.setText("");
                txtPesqCEPProc.setText("");
                txtPesqTelProc.setText("");
                txtPesqNomeProc.setText("");
                rbnPesqFemProc.setChecked(false);
                rbnPesqMascProc.setChecked(false);



                break;

        }

    }
}
