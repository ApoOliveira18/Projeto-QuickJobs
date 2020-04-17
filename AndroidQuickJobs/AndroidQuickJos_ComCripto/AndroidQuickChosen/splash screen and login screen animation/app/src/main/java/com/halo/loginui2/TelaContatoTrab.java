package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaContatoTrab extends AppCompatActivity implements View.OnClickListener {

    Button btnVoltarTelaTrab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contato_trab);

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
