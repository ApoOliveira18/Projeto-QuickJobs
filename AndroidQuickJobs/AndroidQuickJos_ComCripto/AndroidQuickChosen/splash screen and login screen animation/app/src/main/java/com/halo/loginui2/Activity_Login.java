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

    // Servirá para chamar o POP UP do registro no menu
    Dialog myDialog;

    // N O M E A Ç Ã O  D E  O B J E T O S //

    RelativeLayout rellay1, rellay2;
    Button btnRegistrarTela, btnLogar;
    TextView txtUsuLog, txtSenha;

    // Declaração das classes presentes
    adm admTela;
    procurador procTela;
    trabalhador trabTela;

    // Contador de tempo para transição da splash para a tela
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
        // Criação do pop up de registro (Procurador ou Trabalhador)
        myDialog = new Dialog(this);

        //       A S S O C I A Ç Ã O  D O S  O B J E T O S         //
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtUsuLog = (EditText) findViewById(R.id.txtUsuLog);

        // Tempo de duraação da splash (2 segundos)
        handler.postDelayed(runnable, 2000);

        btnRegistrarTela = (Button) findViewById(R.id.btnRegistrarTela);
        btnLogar = (Button) findViewById(R.id.btnLogar);

        // Instanciando classes
        trabTela = new trabalhador();
        procTela = new procurador();
        admTela = new adm();

        // Eventos dos botões ativáveis
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

        // Estrutura de decisão para os botões
        switch (view.getId()) {

            // Este irá chamar o pop up com as decisões de registro
            case R.id.btnRegistrarTela:
                myDialog.show();
                break;

            // Caso o registro de procuradores seja escolhido
            // O usuário será redirecionado para a tela de Cadastro de Procuradores
            case R.id.btnTelaRegProc:
                Intent tela = new Intent(this, RegProc.class);
                startActivity(tela);
                break;

            // Caso o registro de trabalhadores seja escolhido
            // O usuário será redirecionado para a tela de Cadastro de Trabalhadores
            case R.id.btnTelaRegTrab:
                Intent tela1 = new Intent(this, RegTrab.class);
                startActivity(tela1);
                break;

            // Caso o usuário tente realizar o login de sua conta
            // O conectarBD será chamado para executar a ação de busca de usuários
            case R.id.btnLogar:
                try {
                    conectarBD logar = new conectarBD(this);

                    admTela.setEmail(txtUsuLog.getText().toString());
                    admTela.setSenha((txtSenha.getText().toString()));

                    trabTela.setEmail(txtUsuLog.getText().toString());
                    trabTela.setSenha((txtSenha.getText().toString()));

                    procTela.setEmail(txtUsuLog.getText().toString());
                    procTela.setSenha((txtSenha.getText().toString()));

                    logar.setProcClass(procTela);
                    logar.setAdmClass(admTela);
                    logar.setTrabClass(trabTela);

                    logar.execute(1).get();

                    // Caso o email e senha refira-se a um administrador
                    // O usuário será redirecionado para a tela de Menu Administrativo
                    if (logar.getLoginAdm() == true  && logar.getLoginProc() == false && logar.getLoginTrab() == false) {
                        Intent telaMenu = new Intent(this, MenuReg.class);
                        startActivity(telaMenu);
                    }
                    else {
                        // Caso o email e senha refira-se a um trabalhador
                        // O usuário será redirecionado para a tela de Menu do Trabalhador
                        if (logar.getLoginTrab() == true && logar.getLoginProc() == false && logar.getLoginAdm() == false) {
                            Intent telaMenu1 = new Intent(this, TelaTrab.class);
                            startActivity(telaMenu1);
                        }
                        else {
                            // Caso o email e senha refira-se a um procurador
                            // O usuário será redirecionado para a tela de Menu do Procurador
                            if (logar.getLoginProc() == true && logar.getLoginTrab() == false && logar.getLoginAdm() == false) {
                                Intent telaMenu2 = new Intent(this, TelaProc.class);
                                startActivity(telaMenu2);
                            }
                            else {
                                procTela = new procurador();
                                admTela = new adm();
                                trabTela = new trabalhador();
                            }
                        }
                    }
                }
                // Caso haja erro de textos ou manuseio de dados
                catch (ExecutionException e) {
                    // Exibição do erro
                    e.printStackTrace();
                }
                // Caso haja erro por parte do MySQL
                catch (InterruptedException e) {
                    // Exibição do erro
                    e.printStackTrace();
                }
                break;
        }
    }
}
