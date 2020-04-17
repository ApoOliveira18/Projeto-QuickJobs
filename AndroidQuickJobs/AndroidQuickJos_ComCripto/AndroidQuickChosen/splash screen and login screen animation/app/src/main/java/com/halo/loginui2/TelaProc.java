package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaProc extends AppCompatActivity implements View.OnClickListener {

    Button btnPerfilProc,btnCadServProc,btnTelaServProc,btnTelaContProc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_proc);

        btnPerfilProc = (Button) findViewById(R.id.btnPerfilProc);

        btnCadServProc = (Button) findViewById(R.id.btnCadServProc);

        btnTelaServProc = (Button) findViewById(R.id.btnTelaServProc);

        btnPerfilProc.setOnClickListener(this);

        btnCadServProc.setOnClickListener(this);

        btnTelaServProc.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnPerfilProc:
                Intent tela = new Intent(this, PerfilProc.class);
                startActivity(tela);
                break;
            case R.id.btnCadServProc:
                Intent tela1 = new Intent(this, CadServico.class);
                startActivity(tela1);
                break;
            case R.id.btnTelaServProc:
                Intent tela2 = new Intent(this, ListaServProc.class);
                startActivity(tela2);
                break;

        }

    }
}
