package com.halo.loginui2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.Usuario;
import model.adm;
import model.procurador;
import model.trabalhador;


public class Activity_Login extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Dialog myDialog;

    RelativeLayout rellay1, rellay2;
    Button btnRegistrarTela, btnEsqueciSenha, btnLogar;
    TextView txtUsuLog, txtSenha;

    adm admTela;
    procurador procTela;
    trabalhador trabTela;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        myDialog = new Dialog(this);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtUsuLog = (EditText) findViewById(R.id.txtUsuLog);

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash

        btnRegistrarTela = (Button) findViewById(R.id.btnRegistrarTela);
        btnLogar = (Button) findViewById(R.id.btnLogar);


        // instancia as classes
        trabTela = new trabalhador();
        procTela = new procurador();
        admTela = new adm();

        //evento click em no botão da tela
        btnRegistrarTela.setOnClickListener(this);
        btnLogar.setOnClickListener(this);

        TextView txtclose;
        Button  btnTelaRegProc, btnTelaRegTrab;
        myDialog.setContentView(R.layout.activity_pop_up);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        btnTelaRegProc = (Button) myDialog.findViewById(R.id.btnTelaRegProc);
        btnTelaRegTrab = (Button) myDialog.findViewById(R.id.btnTelaRegTrab);
        txtclose.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        btnTelaRegProc.setOnClickListener(this);
        btnTelaRegTrab.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.btnRegistrarTela:

                // mostra um pop-up com a opcão de registrar como trabalhador ou procyrador
                myDialog.show();
                break;
            case R.id.btnTelaRegProc:

                // redireciona para a tela registrar procurador
                Intent tela = new Intent(this, RegProc.class);
                startActivity(tela);
                break;
            case R.id.btnTelaRegTrab:

                // redireciona para a tela registrar trabalhador
                Intent tela1 = new Intent(this, RegTrab.class);
                startActivity(tela1);
                break;

            case R.id.btnLogar:
                try {
                    conectarBD logar = new conectarBD(this);

                    // guarda os valores recebidos na classe para efetuar um comando
                    admTela.setEmail(txtUsuLog.getText().toString());
                    admTela.setSenha((txtSenha.getText().toString()));

                    trabTela.setEmail(txtUsuLog.getText().toString());
                    trabTela.setSenha((txtSenha.getText().toString()));


                    procTela.setEmail(txtUsuLog.getText().toString());
                    procTela.setSenha((txtSenha.getText().toString()));


                    logar.setProcClass(procTela);
                    logar.setAdmClass(admTela);
                    logar.setTrabClass(trabTela);

                    // executa o comando no conectarBD no banco de dados
                    logar.execute(1).get();

                    // se o login de ADM for true ele redireciona para o menu do ADM
                    if (logar.getLoginAdm() == true  && logar.getLoginProc() == false && logar.getLoginTrab() == false) {
                        Intent telaMenu = new Intent(this, MenuReg.class);
                        startActivity(telaMenu);
                    }

                    else {

                        // se o login do trabalhador for true ele redireciona para a tela do trabalhador
                        if (logar.getLoginTrab() == true && logar.getLoginProc() == false && logar.getLoginAdm() == false) {
                            Intent telaMenu1 = new Intent(this, TelaTrab.class);
                            startActivity(telaMenu1);
                        }

                        // se o login do procurador for true ele redireciona para a tela do procurador
                        else {
                            if (logar.getLoginProc() == true && logar.getLoginTrab() == false && logar.getLoginAdm() == false) {
                                Intent telaMenu2 = new Intent(this, TelaProc.class);
                                startActivity(telaMenu2);
                            }

                            else {
                                // instancia as classes caso nenhum dos logins exista
                                procTela = new procurador();
                                admTela = new adm();
                                trabTela = new trabalhador();
                            }
                        }


                    }
                }
                catch (ExecutionException e) {
                    e.printStackTrace();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

        }



    }
}
