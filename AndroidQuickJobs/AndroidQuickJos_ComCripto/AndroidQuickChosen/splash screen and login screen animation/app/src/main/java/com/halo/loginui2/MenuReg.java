package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.Usuario;

public class MenuReg extends AppCompatActivity implements View.OnClickListener {


    Button btnRegProc, btnRegTrab,btnVoltarReg,btnPesqProc,btnPesTrab,btnPesqAdmTela,btnRegAdmTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reg);

        btnRegProc = (Button) findViewById(R.id.btnRegProc);

        btnPesqProc= (Button) findViewById(R.id.btnPesqProc);

        btnRegTrab = (Button) findViewById(R.id.btnRegTrab);

        btnPesTrab= (Button) findViewById(R.id.btnPesTrab);

        btnPesqAdmTela= (Button) findViewById(R.id.btnPesqAdmTela);

        btnRegAdmTela= (Button) findViewById(R.id.btnRegAdmTela);

        btnVoltarReg= (Button) findViewById(R.id.btnVoltarReg);











        btnRegProc.setOnClickListener(this);
        btnRegTrab.setOnClickListener(this);
        btnVoltarReg.setOnClickListener(this);
        btnPesqProc.setOnClickListener(this);
        btnPesTrab.setOnClickListener(this);
        btnPesqAdmTela.setOnClickListener(this);
        btnRegAdmTela.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.btnRegProc:
                Intent tela = new Intent(this, CadProc.class);
                startActivity(tela);
                break;


            case R.id.btnRegTrab:

                Intent tela1 = new Intent(this, CadTrab.class);
                startActivity(tela1);

                break;

            case R.id.btnVoltarReg:

                Intent tela2 = new Intent(this, Activity_Login.class);
                startActivity(tela2);

                break;
            case R.id.btnPesqProc:

                Intent tela4 = new Intent(this, PesqProc.class);
                startActivity(tela4);

                break;
            case R.id.btnPesTrab:

            Intent tela5 = new Intent(this, PesqTrab.class);
            startActivity(tela5);

            break;

            case R.id.btnPesqAdmTela:

                Intent tela6 = new Intent(this, PesqAdm.class);
                startActivity(tela6);

                break;


            case R.id.btnRegAdmTela:

                Intent tela7 = new Intent(this, CadAdm.class);
                startActivity(tela7);

                break;





        }


    }
}

