package com.halo.loginui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import dao.perfilProc;
import model.procurador;

public class PerfilProc extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    // Inicia as variáveis para receber os objetos na tela e as classes para executar uma programação

    Button btnVoltarProcPerfil, btnAlterarProcPerfil;

    EditText txtNomeProcPerfil, txtDataNascProcPerfil, txtCPFProcPerfil,
            txtTelProcPerfil, txtEmailProcPerfil, txtSenhaProcPerfil, txtCEPProcPerfil,
            txtNumResPerfil, txtComplProcPerfil;
    TextView lblNome;

    Spinner spnSexoProcPerfil;
    private static final String[] textosSpinner = {"Masculino", "Feminino"};
    ArrayAdapter<String> listaSexo;

    procurador procTela;
    perfilProc perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_proc);


        // Faz os as variaveis criadas acima receberem os objetos nas respectiva tela

        try {

            btnVoltarProcPerfil = (Button) findViewById(R.id.btnVoltarProcPerfil);

            btnAlterarProcPerfil = (Button) findViewById(R.id.btnAlterarProcPerfil);

            txtCEPProcPerfil = (EditText) findViewById(R.id.txtCEPProcPerfil);
            txtComplProcPerfil = (EditText) findViewById(R.id.txtComplProcPerfil);
            txtCPFProcPerfil = (EditText) findViewById(R.id.txtCPFProcPerfil);
            txtDataNascProcPerfil = (EditText) findViewById(R.id.txtDataNascProcPerfil);
            txtEmailProcPerfil = (EditText) findViewById(R.id.txtEmailProcPerfil);
            txtNomeProcPerfil = (EditText) findViewById(R.id.txtNomeProcPerfil);
            txtNumResPerfil = (EditText) findViewById(R.id.txtNumResPerfil);
            txtSenhaProcPerfil = (EditText) findViewById(R.id.txtSenhaProcPerfil);
            txtTelProcPerfil = (EditText) findViewById(R.id.txtTelProcPerfil);
            lblNome = (TextView) findViewById(R.id.lblNome);


            spnSexoProcPerfil = (Spinner) findViewById(R.id.spnSexoProcPerfil);

            listaSexo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,textosSpinner);
            spnSexoProcPerfil.setAdapter(listaSexo);

            // instancia as classes
            perfil = new perfilProc(PerfilProc.this);
            procTela = new procurador();

            perfil.execute().get();

            procTela = perfil.getProcClass();

            if(procTela.getSexo().equals("Masculino"))
                spnSexoProcPerfil.setSelection(0);
            else
                spnSexoProcPerfil.setSelection(1);


            txtNomeProcPerfil.setText(procTela.getNome());
            txtCEPProcPerfil.setText(procTela.getCep());
            txtComplProcPerfil.setText(procTela.getComplemento());
            txtDataNascProcPerfil.setText(procTela.getData_nasc());
            txtCPFProcPerfil.setText(procTela.getCpf());
            txtEmailProcPerfil.setText(procTela.getEmail());
            txtNumResPerfil.setText(procTela.getNum_residencial());
            txtSenhaProcPerfil.setText(procTela.getSenha());
            txtTelProcPerfil.setText(procTela.getTel());
            lblNome.setText(procTela.getNome());


        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //evento click em no botão da tela
        btnVoltarProcPerfil.setOnClickListener(this);
        btnAlterarProcPerfil.setOnClickListener(this);

        spnSexoProcPerfil.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAlterarProcPerfil:


                    conectarBD alt = new conectarBD(this);


                    procTela.setNome(txtNomeProcPerfil.getText().toString());
                    procTela.setSenha(txtSenhaProcPerfil.getText().toString());
                    procTela.setCep(txtCEPProcPerfil.getText().toString());
                    procTela.setComplemento(txtComplProcPerfil.getText().toString());
                    procTela.setCpf(txtCPFProcPerfil.getText().toString());
                    procTela.setEmail(txtEmailProcPerfil.getText().toString());
                    procTela.setData_nasc(txtDataNascProcPerfil.getText().toString());
                    procTela.setNum_residencial(txtNumResPerfil.getText().toString());
                    procTela.setTel(txtTelProcPerfil.getText().toString());

                    alt.setProcClass(procTela);

                    alt.execute(4);






                    break;

            case R.id.btnVoltarProcPerfil:
                Intent tela3 = new Intent(this, TelaProc.class);
                startActivity(tela3);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (1) {
            case 0:
              procTela.setSexo("Masculino");
                break;
            case 1:
                procTela.setSexo("Feminino");
                break;

        }


    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
