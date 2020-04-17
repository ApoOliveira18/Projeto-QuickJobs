package dao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.adm;
import model.contrato;
import model.procurador;
import model.servico;
import model.trabalhador;
import utils.PesqServ;
import utils.PesqTrab;
import utils.criptografia;
import utils.pesqProcPerfil;


public class conectarBD extends AsyncTask<Integer, Object, Boolean> {

    Connection conexao;

    Context tela;

    ProgressDialog dialogo;

    int op;

    // I N S T A N C I A N D O  A  C L A S S E  C R I P T O G R A F I A

    criptografia cripto;

    private procurador ProcClass;

    private contrato contratoClass;

    private adm admClass;

    private trabalhador trabClass;

    private servico servClass;

    public trabalhador getTrabClass() {
        return trabClass;
    }

    public void setTrabClass(trabalhador trabClass) {
        this.trabClass = trabClass;
    }

    public adm getAdmClass() {
        return admClass;
    }

    public void setAdmClass(adm admClass) {
        this.admClass = admClass;
    }

    public procurador getProcClass() {
        return ProcClass;
    }

    public void setProcClass(procurador procClass) {
        ProcClass = procClass;
    }

    private Boolean loginAdm;

    public Boolean getLoginAdm() {
        return loginAdm;
    }

    public void setLoginAdm(Boolean loginAdm) {
        this.loginAdm = loginAdm;
    }

    private Boolean loginProc;

    public Boolean getLoginProc() {
        return loginProc;
    }

    public void setLoginProc(Boolean loginProc) {
        this.loginProc = loginProc;
    }

    private Boolean loginTrab;

    public Boolean getLoginTrab() {
        return loginTrab;
    }

    public void setLoginTrab(Boolean loginTrab) {
        this.loginTrab = loginTrab;
    }


    public conectarBD(Context tela) {
        super();
        this.tela = tela;

    }
    // MÉTODO DE CONEXÃO COM O BANCO DE DADOS
    // UTILIZAMOS O ENDEREÇO DE IPV4, A PORTA DE CONEXÃO, NOME DO BANCO, USUÁRIO E SENHA RESPECTIVA
    // ANTENTE-SE AO IPV4, MUDANDO DE REDE ESTE TAMBÉM MUDARÁ

    public Boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexao = DriverManager.getConnection("jdbc:mysql://172.25.74.35:3306/quickjobs", "root", "pedro123");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Executor de ações
    // Aqui os métodos criados serão adicionados para serem chamados nas telas
    @Override
    protected Boolean doInBackground(Integer... integers) {


        Boolean resp = null;

        op = integers[0];

        connect();

        switch (op) {
            case 0:
                resp = inserirProc();
                break;
            case 1:
                resp = logar();
                break;
            case 2:
                resp = pesquisarProc();
                break;
            case 3:
                resp = excluirProc();
                break;
            case 4:
                resp = alterarProc();
                break;
            case 5:
                resp = pesquisarTrab();
                break;
            case 6:
                resp = excluirTrab();
                break;
            case 7:
                resp = alterarTrab();
                break;
            case 8:
                resp = pesquisarAdm();
                break;
            case 9:
                resp = excluirAdm();
                break;
            case 10:
                resp = alterarAdm();
                break;
            case 11:
                resp = inserirTrab();
                break;
            case 12:
                resp = inserirAdm();
                break;
            case 13:
                resp = PerfilProc();
                break;
            case 14:
                resp = inserirServ();
                break;
            case 15:
                resp = altServTrab();
                break;
            case 16:
                resp = altServProc();
                break;
            case 17:
                resp = UpdateContrato();
                break;

        }
        return resp;
    }

    // Servirá para exibir uma mensagem enquanto o App conecta-se com a base de dados
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialogo = new ProgressDialog(tela);
        dialogo.setMessage("Aguarde conectando.....");
        dialogo.show();
    }
    // Servirá para armazenar as mensagens de exibição para os casos dentro de uma estrutura de decisão
    // Cada conjunto consiste em uma mensagem para caso a ação seja realizada com sucesso e outra para caso não seja possível efetuar o ato

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        switch (op) {
            case 0:
                if (aBoolean == true)
                    Toast.makeText(tela, "Cadastro OK", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "ERRO!!!", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                if (aBoolean == false)
                    Toast.makeText(tela, "Usuario não cadastrado!", Toast.LENGTH_SHORT).show();
                break;

            case 2:
                if (aBoolean == false)
                    Toast.makeText(tela, "Procuador não cadastrado!", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                if (aBoolean == false)
                    Toast.makeText(tela, "ERRO!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Procuador excluido com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                if (aBoolean == false)
                    Toast.makeText(tela, "ERRO!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Procuador alterado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                if (aBoolean == false)
                    Toast.makeText(tela, "Trabalhador não cadastrado!!", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                if (aBoolean == false)
                    Toast.makeText(tela, "ERRO NA EXCLUSÃO!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Trabalhador excluido com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                if (aBoolean == false)
                    Toast.makeText(tela, "ERRO NA ALTERAÇÃO!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Trabalhador alterado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                if (aBoolean == false)
                    Toast.makeText(tela, "Administrador não Cadastrado!!", Toast.LENGTH_SHORT).show();

                break;
            case 9:
                if (aBoolean == false)
                    Toast.makeText(tela, "ERRO NA EXCLUSÃO!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Administrador excluído com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 10:
                if (aBoolean == false)
                    Toast.makeText(tela, "ERRO NA ALTERAÇÃO!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Administrador alterado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 11:
                if (aBoolean == false)
                    Toast.makeText(tela, "ERRO NO CADASTRO!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Trabalhador cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 12:
                if (aBoolean == false)
                    Toast.makeText(tela, "ERRO NO CADASTRO!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Administrador cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 13:
                if (aBoolean == false)
                    Toast.makeText(tela, "ERRO NA PESQUISA!", Toast.LENGTH_SHORT).show();

                break;
            case 14:
                if (aBoolean == false)
                    Toast.makeText(tela, "ERRO NO CADASTRO!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Serviço Cadastrado!", Toast.LENGTH_SHORT).show();
                break;
            case 15:
                if (aBoolean == false)
                    Toast.makeText(tela, "Erro ao finalizar!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Serviço Enviado ao Procurador!", Toast.LENGTH_SHORT).show();
                break;
            case 16:
                if (aBoolean == false)
                    Toast.makeText(tela, "Erro ao finalizar serviço!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Serviço Enviado ao Trabalhador!", Toast.LENGTH_SHORT).show();
                break;
            case 17:
                if (aBoolean == false)
                    Toast.makeText(tela, "Erro ao finalizar o serviço!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Serviço Finalizado!", Toast.LENGTH_SHORT).show();
                break;
        }
        dialogo.dismiss();

    }

    // Método para realizar uma busca nos usuários das tabelas Administrador, Procurador e Trabalhador filtrando as informações
    // de email (login) e senha
    public Boolean logar() {
        try {
            Date dataNasc;
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataCad;
            SimpleDateFormat formatoData1 = new SimpleDateFormat("dd/MM/yyyy");

            String sql = "select * from procurador where email=? and senha=? and status = 1";

            String sql2 = "select * from administrador where email=? and senha=? and status= 1";

            String sql3 = "select * from trabalhador where email=? and senha=? and status = 1";


            PreparedStatement comando = conexao.prepareStatement(sql);

            PreparedStatement comando2 = conexao.prepareStatement(sql2);

            PreparedStatement comando3 = conexao.prepareStatement(sql3);

            comando.setString(1, getProcClass().getEmail());
            comando.setString(2, getProcClass().getSenha());

            comando2.setString(1, getAdmClass().getEmail());
            comando2.setString(2, getAdmClass().getSenha());

            comando3.setString(1, getTrabClass().getEmail());
            comando3.setString(2, getTrabClass().getSenha());

            ResultSet tabelaMemoria = comando.executeQuery();
            ResultSet tabelaMemoria2 = comando2.executeQuery();
            ResultSet tabelaMemoria3 = comando3.executeQuery();

            if (tabelaMemoria.next()) {

                pesqProcPerfil.setId(tabelaMemoria.getInt("id_procurador"));

                loginProc = true;
                loginAdm = false;
                loginTrab = false;
                return true;
            } else {

                if (tabelaMemoria2.next()) {

                    loginAdm = true;
                    loginTrab = false;
                    loginProc = false;
                    return true;
                } else {
                    if (tabelaMemoria3.next()) {

                        PesqTrab.setId(tabelaMemoria3.getInt("id_trabalhador"));
                        loginTrab = true;
                        loginProc = false;
                        loginAdm = false;
                        return true;
                    } else {
                        loginTrab = false;
                        loginAdm = false;
                        loginProc = false;
                        setProcClass(null);
                        setAdmClass(null);
                        setTrabClass(null);
                        return false;
                    }
                }


            }


        } catch (SQLException e) {
            e.printStackTrace();
            loginProc = false;
            loginTrab = false;
            loginAdm = false;
            return false;
        }


    }
    // Método de registro de serviço (comando INSERT)

    public Boolean inserirServ() {
        try {


            String sql = "insert into servico values(0,?,null,?,?,?,'','',?,?,?,default,?,?,?,?);";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, pesqProcPerfil.getId());
            comando.setInt(2, servClass.getId_pagamento());
            comando.setInt(3, servClass.getId_tipo_trab());
            comando.setString(4, servClass.getTitulo());
            comando.setString(5, servClass.getDescricao());
            comando.setString(6, servClass.getValor());
            comando.setString(7, servClass.getObservacoes());
            comando.setString(8, servClass.getCep());
            comando.setString(9, servClass.getNum_residencial());
            comando.setString(10, servClass.getComplemento());
            comando.setInt(11, servClass.getStatus());
            comando.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Método de registro de procurador (comando INSERT)
    public Boolean inserirProc() {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataUtil = formato.parse(ProcClass.getData_nasc());

            java.sql.Date dataMySQL = new java.sql.Date(dataUtil.getTime());


            String sql = "insert into procurador values(0,?,'foto',?,?,default,?,?,?,?,?,?,?,?,1" +
                    ");";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, (ProcClass.getNome()));
            comando.setString(2, ProcClass.getSexo());
            comando.setDate(3, dataMySQL);
            comando.setString(4, ProcClass.getCpf());
            comando.setString(5, ProcClass.getTel());
            comando.setString(6, ProcClass.getEmail());
            comando.setString(7, ProcClass.getSenha());
            comando.setString(8, ProcClass.getCep());
            comando.setString(9, ProcClass.getNum_residencial());
            comando.setString(10, ProcClass.getComplemento());
            comando.setString(11, ProcClass.getPontuacao());
            comando.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método de busca de procurador (comando SELECT) com base no nome
    // e no status ativo (1)
    public Boolean pesquisarProc() {
        try {
            Date dataTMP;
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataTMP1;
            SimpleDateFormat formatoData2 = new SimpleDateFormat("dd/MM/yyyy");

            String sql = "select * from procurador where nome=? and status=1";


            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, ProcClass.getNome());
            ResultSet tabelaMemoria = comando.executeQuery();

            if (tabelaMemoria.next()) {

                ProcClass.setId_procurador(tabelaMemoria.getInt("id_procurador"));
                ProcClass.setNome(tabelaMemoria.getString("nome"));
                ProcClass.setFoto(tabelaMemoria.getString("foto"));
                ProcClass.setSexo(tabelaMemoria.getString("sexo"));

                dataTMP = tabelaMemoria.getDate("data_nasc");
                ProcClass.setData_nasc(formatoData.format(dataTMP));

                dataTMP1 = tabelaMemoria.getDate("data_cadastro");
                ProcClass.setData_cadastro(formatoData2.format(dataTMP1));

                ProcClass.setCpf(tabelaMemoria.getString("cpf"));
                ProcClass.setTel(tabelaMemoria.getString("tel"));
                ProcClass.setEmail(tabelaMemoria.getString("email"));
                ProcClass.setSenha(tabelaMemoria.getString("senha"));
                ProcClass.setCep(tabelaMemoria.getString("cep"));
                ProcClass.setNum_residencial(tabelaMemoria.getString("num_residencial"));
                ProcClass.setComplemento(tabelaMemoria.getString("complemento"));
                ProcClass.setPontuacao(tabelaMemoria.getString("pontuacao"));


                return true;
            } else {

                ProcClass = null;
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }


    }
    // Método para carregar as informações pessoais do procurador em seu perfil (comando SELECT)
    // restringido aos usuários ativos (status = 1)

    public Boolean PerfilProc() {
        try {
            Date dataTMP;
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataTMP1;
            SimpleDateFormat formatoData2 = new SimpleDateFormat("dd/MM/yyyy");

            String sql = "select * from procurador where id_procurador=? and status=1";


            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, ProcClass.getId_procurador());
            ResultSet tabelaMemoria = comando.executeQuery();

            if (tabelaMemoria.next()) {

                ProcClass.setId_procurador(tabelaMemoria.getInt("id_procurador"));
                ProcClass.setNome(tabelaMemoria.getString("nome"));
                ProcClass.setFoto(tabelaMemoria.getString("foto"));
                ProcClass.setSexo(tabelaMemoria.getString("sexo"));

                dataTMP = tabelaMemoria.getDate("data_nasc");
                ProcClass.setData_nasc(formatoData.format(dataTMP));

                dataTMP1 = tabelaMemoria.getDate("data_cadastro");
                ProcClass.setData_cadastro(formatoData2.format(dataTMP1));

                ProcClass.setCpf(tabelaMemoria.getString("cpf"));
                ProcClass.setTel(tabelaMemoria.getString("tel"));
                ProcClass.setEmail(tabelaMemoria.getString("email"));
                ProcClass.setSenha(tabelaMemoria.getString("senha"));
                ProcClass.setCep(tabelaMemoria.getString("cep"));
                ProcClass.setNum_residencial(tabelaMemoria.getString("num_residencial"));
                ProcClass.setComplemento(tabelaMemoria.getString("complemento"));
                ProcClass.setPontuacao(tabelaMemoria.getString("pontuacao"));


                return true;
            } else {

                ProcClass = null;
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }


    }

    // Método para desativação da conta de um procurador (comando UPDATE)
    // O comando alterará o status de ativo (1) para inativo (0)
    public Boolean excluirProc() {

        try {

            String sql = " update procurador set status=0 WHERE ID_procurador = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setInt(1, ProcClass.getId_procurador());
            comando.executeUpdate();

            return true;


        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    // Método de alteração de dados de um procurador (comando UPDATE)
    public Boolean alterarProc() {
        try {

            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataUtil = formatoData.parse(ProcClass.getData_nasc());

            java.sql.Date dataMyqsl = new java.sql.Date(dataUtil.getTime());

            String sql =
                    " UPDATE procurador SET nome=?, sexo=?, data_nasc=?, cpf=?, tel=?,email=?,senha=?,cep=?,num_residencial=?,complemento=?"
                            + " WHERE id_procurador=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, ProcClass.getNome());
            comando.setString(2, ProcClass.getSexo());
            comando.setDate(3, dataMyqsl);
            comando.setString(4, ProcClass.getCpf());
            comando.setString(5, ProcClass.getTel());
            comando.setString(6, ProcClass.getEmail());
            comando.setString(7, ProcClass.getSenha());
            comando.setString(8, ProcClass.getCep());
            comando.setString(9, ProcClass.getNum_residencial());
            comando.setString(10, ProcClass.getComplemento());
            comando.setInt(11, ProcClass.getId_procurador());
            comando.executeUpdate();

            return true;
        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    // Método de registro de um novo trabalhador (comando INSERT)

    public Boolean inserirTrab() {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataUtil = formato.parse(trabClass.getData_nasc());

            java.sql.Date dataMySQL = new java.sql.Date(dataUtil.getTime());


            String sql = "insert into trabalhador values(0,?,?,'',?,?,default,?,?,?,?,?,?,?,10,1);";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, trabClass.getId_tipo_pacote());
            comando.setString(2, trabClass.getNome());
            comando.setString(3, trabClass.getCpf());
            comando.setDate(4, dataMySQL);
            comando.setString(5, trabClass.getSexo());
            comando.setString(6, trabClass.getTel());
            comando.setString(7, trabClass.getEmail());
            comando.setString(8, trabClass.getSenha());
            comando.setString(9, trabClass.getCep());
            comando.setString(10, trabClass.getNum_residencial());
            comando.setString(11, trabClass.getComplemento());
            comando.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Método de pesquisa de um trabalhador (comando SELECT) com base no nome
    // Restringido aos que possuem status ativo (1)
    public Boolean pesquisarTrab() {
        try {
            Date dataTMP;
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataTMP1;
            SimpleDateFormat formatoData2 = new SimpleDateFormat("dd/MM/yyyy");

            String sql = "select * from trabalhador where nome=? and status=1";


            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, trabClass.getNome());
            ResultSet tabelaMemoria = comando.executeQuery();

            if (tabelaMemoria.next()) {

                trabClass.setId_trabalhador(tabelaMemoria.getInt("id_trabalhador"));
                trabClass.setId_tipo_pacote(tabelaMemoria.getInt("id_tipo_pacote"));
                trabClass.setNome(tabelaMemoria.getString("nome"));
                trabClass.setFoto(tabelaMemoria.getString("foto"));
                trabClass.setSexo(tabelaMemoria.getString("sexo"));

                dataTMP = tabelaMemoria.getDate("data_nasc");
                trabClass.setData_nasc(formatoData.format(dataTMP));

                dataTMP1 = tabelaMemoria.getDate("data_registro");
                trabClass.setData_registro(formatoData2.format(dataTMP1));

                trabClass.setCpf(tabelaMemoria.getString("cpf"));
                trabClass.setTel(tabelaMemoria.getString("tel"));
                trabClass.setEmail(tabelaMemoria.getString("email"));
                trabClass.setSenha(tabelaMemoria.getString("senha"));
                trabClass.setCep(tabelaMemoria.getString("cep"));
                trabClass.setNum_residencial(tabelaMemoria.getString("num_residencial"));
                trabClass.setComplemento(tabelaMemoria.getString("complemento"));


                return true;
            } else {

                trabClass = null;
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }


    }

    // Método para desativar um trabalhador (comando UPDATE)
    // Assim como os demais comandos "delete" alterará o status de ativo(1) para inativo(0)

    public Boolean excluirTrab() {

        try {

            String sql = " update trabalhador set status=0 WHERE id_trabalhador = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setInt(1, trabClass.getId_trabalhador());
            comando.executeUpdate();

            return true;


        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    // Método para alteração de dados do trabalhdor (comando UPDATE)

    public Boolean alterarTrab() {
        try {

            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataUtil = formatoData.parse(trabClass.getData_nasc());

            java.sql.Date dataMyqsl = new java.sql.Date(dataUtil.getTime());

            String sql =
                    " UPDATE trabalhador SET nome=?, sexo=?, data_nasc=?, cpf=?, tel=?,email=?,senha=?,cep=?,num_residencial=?,complemento=?"
                            + " WHERE id_trabalhador=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, trabClass.getNome());
            comando.setString(2, trabClass.getSexo());
            comando.setDate(3, dataMyqsl);
            comando.setString(4, trabClass.getCpf());
            comando.setString(5, trabClass.getTel());
            comando.setString(6, trabClass.getEmail());
            comando.setString(7, trabClass.getSenha());
            comando.setString(8, trabClass.getCep());
            comando.setString(9, trabClass.getNum_residencial());
            comando.setString(10, trabClass.getComplemento());
            comando.setInt(11, trabClass.getId_trabalhador());
            comando.executeUpdate();

            return true;
        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }



    // Método para envio de solicitação de serviço por parte do Trabalhador para o Procurador
    // Caso seja efetuado com sucesso, alterará o número de identificação do trabalhador presente na tabela serviço
    // para o seu respectivo e com o valor acordado
    public Boolean altServTrab() {
        try {


            String sql =
                    " UPDATE servico set valor =?,id_trabalhador=?,status=?"
                            + " WHERE id_servico=?;";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, servClass.getValor());
            comando.setInt(2, PesqTrab.getId());
            comando.setInt(3, servClass.getStatus());
            comando.setInt(4, PesqServ.getId());
            comando.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    // Método para alteração de servico. Quando o servico com status=1 (Esperando confirmação do procurador) é enviado to trabalhador para o procurador para que o mesmo aceite e confirme os termos do servico
    public Boolean altServProc() {
        try {


            String sql = " UPDATE servico set status=2"
                    + " WHERE id_servico=?;";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, PesqServ.getId());

            comando.executeUpdate();

            String sql1 = "insert into contrato values(0,?,default)";

            PreparedStatement comando1 = conexao.prepareStatement(sql1);
            comando1.setInt(1, PesqServ.getId());

            comando1.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    // Método par alteração de status do contrato para 3(concluido)
    public Boolean UpdateContrato() {
        try {


            String sql = " UPDATE servico set status=3"
                    + " WHERE id_servico=?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, PesqServ.getId());

            comando.executeUpdate();


            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    // Método para registro de um administrador (comando INSERT)
    public Boolean inserirAdm() {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataUtil = formato.parse(admClass.getData_nasc());

            java.sql.Date dataMySQL = new java.sql.Date(dataUtil.getTime());


            String sql = "insert into administrador values(0,?,?,?,?,?,default,?,?,?,?,?,?,'pergunta','resposta',0,1" +
                    ");";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, admClass.getNome());
            comando.setString(2, admClass.getFoto());
            comando.setString(3, admClass.getSexo());
            comando.setString(4, admClass.getCpf());
            comando.setDate(5, dataMySQL);
            comando.setString(6, admClass.getEmail());
            comando.setString(7, admClass.getSenha());
            comando.setString(8, admClass.getTel());
            comando.setString(9, admClass.getCep());
            comando.setString(10, admClass.getNum_residencial());
            comando.setString(11, admClass.getComplemento());
            comando.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para busca de um administrador (comando SELECT) com base no nome
    // e restringido aos administradores ativos no sistema(status = 1)
    public Boolean pesquisarAdm() {
        try {
            Date dataTMP;
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataTMP1;
            SimpleDateFormat formatoData2 = new SimpleDateFormat("dd/MM/yyyy");

            String sql = "select * from administrador where nome=? and status=1";


            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, admClass.getNome());
            ResultSet tabelaMemoria = comando.executeQuery();

            if (tabelaMemoria.next()) {

                admClass.setId_adm(tabelaMemoria.getInt("id_adm"));
                admClass.setNome(tabelaMemoria.getString("nome"));
                admClass.setFoto(tabelaMemoria.getString("foto"));
                admClass.setSexo(tabelaMemoria.getString("sexo"));

                dataTMP = tabelaMemoria.getDate("data_nasc");
                admClass.setData_nasc(formatoData.format(dataTMP));

                dataTMP1 = tabelaMemoria.getDate("data_cadastro");
                admClass.setData_registro(formatoData2.format(dataTMP1));

                admClass.setCpf(tabelaMemoria.getString("cpf"));
                admClass.setTel(tabelaMemoria.getString("tel"));
                admClass.setEmail(tabelaMemoria.getString("email"));
                admClass.setSenha(tabelaMemoria.getString("senha"));
                admClass.setCep(tabelaMemoria.getString("cep"));
                admClass.setNum_residencial(tabelaMemoria.getString("num_residencial"));
                admClass.setComplemento(tabelaMemoria.getString("complemento"));


                return true;
            } else {

                admClass = null;
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }


    }

    // Método para desativar administradores (comando UPDATE)
    public Boolean excluirAdm() {

        try {

                String sql = "update administrador set status=0 WHERE id_adm = ? and id_adm != 1";
                PreparedStatement comando = conexao.prepareStatement(sql);

                comando.setInt(1, admClass.getId_adm());
                comando.executeUpdate();

                return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }



    // Método para alteração de dados do administrador (comando UPDATE)

    public Boolean alterarAdm() {
        try {

            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataUtil = formatoData.parse(admClass.getData_nasc());

            java.sql.Date dataMyqsl = new java.sql.Date(dataUtil.getTime());

            String sql =
                    " UPDATE administrador SET nome=?, sexo=?, data_nasc=?, cpf=?, tel=?,email=?,senha=?,cep=?,num_residencial=?,complemento=?"
                            + " WHERE id_adm=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, admClass.getNome());
            comando.setString(2, admClass.getSexo());
            comando.setDate(3,  dataMyqsl);
            comando.setString(4,admClass.getCpf());
            comando.setString(5, admClass.getTel());
            comando.setString(6, admClass.getEmail());
            comando.setString(7, admClass.getSenha());
            comando.setString(8, admClass.getCep());
            comando.setString(9, admClass.getNum_residencial());
            comando.setString(10, admClass.getComplemento());
            comando.setInt(11, admClass.getId_adm());
            comando.executeUpdate();

            return true;
        }



        catch (ParseException e ) {

            e.printStackTrace();
            return false;
        }
        catch (SQLException e ) {
            e.printStackTrace();
            return false;
        }

    }


    public servico getServClass() {
        return servClass;
    }

    public void setServClass(servico servClass) {
        this.servClass = servClass;
    }

    public contrato getContratoClass() {
        return contratoClass;
    }

    public void setContratoClass(contrato contratoClass) {
        this.contratoClass = contratoClass;
    }
}
