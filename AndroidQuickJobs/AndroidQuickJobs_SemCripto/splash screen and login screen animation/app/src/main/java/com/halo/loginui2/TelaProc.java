package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaProc extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnPerfilProc,btnCadServProc,btnTelaServProc,btnTelaContProc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_proc);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        btnPerfilProc = (Button) findViewById(R.id.btnPerfilProc);

        btnCadServProc = (Button) findViewById(R.id.btnCadServProc);

        btnTelaServProc = (Button) findViewById(R.id.btnTelaServProc);

        //evento click em no botão da tela
        btnPerfilProc.setOnClickListener(this);

        btnCadServProc.setOnClickListener(this);

        btnTelaServProc.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnPerfilProc:
                //redireciona a tela de perfil
                Intent tela = new Intent(this, PerfilProc.class);
                startActivity(tela);
                break;
            case R.id.btnCadServProc:
                //redireciona a tela de cadastrar serviço
                Intent tela1 = new Intent(this, CadServico.class);
                startActivity(tela1);
                break;
            case R.id.btnTelaServProc:
                //redireciona a tela lista de serviços
                Intent tela2 = new Intent(this, ListaServProc.class);
                startActivity(tela2);
                break;

        }

    }
}
