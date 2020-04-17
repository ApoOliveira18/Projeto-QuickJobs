package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaContatoTrab extends AppCompatActivity implements View.OnClickListener {
    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnVoltarTelaTrab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contato_trab);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        btnVoltarTelaTrab = (Button) findViewById(R.id.btnVoltarTelaTrab);

        btnVoltarTelaTrab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnVoltarTelaTrab:
                Intent tela = new Intent(this, TelaTrab.class);
                startActivity(tela);
                break;
        }
    }
}
