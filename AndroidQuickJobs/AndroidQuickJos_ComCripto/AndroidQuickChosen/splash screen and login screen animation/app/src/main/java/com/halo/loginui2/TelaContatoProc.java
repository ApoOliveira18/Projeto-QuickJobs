package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaContatoProc extends AppCompatActivity implements View.OnClickListener {

    Button btnVoltarTelaProc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contato_proc);

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
