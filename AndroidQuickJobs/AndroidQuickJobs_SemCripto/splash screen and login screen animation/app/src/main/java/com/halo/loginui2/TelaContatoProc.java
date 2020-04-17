package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaContatoProc extends AppCompatActivity implements View.OnClickListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnVoltarTelaProc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contato_proc);

        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        btnVoltarTelaProc = (Button) findViewById(R.id.btnVoltarTelaProc);

        btnVoltarTelaProc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnVoltarTelaProc:
                Intent tela = new Intent(this, TelaProc.class);
                startActivity(tela);
                break;
        }
    }
}
