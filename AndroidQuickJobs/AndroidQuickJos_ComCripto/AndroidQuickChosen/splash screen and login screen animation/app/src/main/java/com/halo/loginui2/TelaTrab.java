package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaTrab extends AppCompatActivity implements View.OnClickListener {

    Button btnTelaLista,btnServPend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_trab);

        btnTelaLista = (Button) findViewById(R.id.btnTelaLista);

        btnServPend = (Button) findViewById(R.id.btnServPend);

        btnTelaLista.setOnClickListener(this);

        btnServPend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnTelaLista:
                Intent tela = new Intent(this, ListaServTrab.class);
                startActivity(tela);
                break;


            case R.id.btnServPend:
                Intent tela1 = new Intent(this, ServPendTrab.class);
                startActivity(tela1);
                break;
        }
    }
}
