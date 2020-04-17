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


    // I N S T A N C I A N D O  A  C L A S S E  C R I P T O G R A F I A
    criptografia cripto;

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
            conexao = DriverManager.getConnection("jdbc:mysql://172.25.74.35:3306/quickjobs", "root", "cthulhu");
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

        // Variável para contar os casos
        Boolean resp = null;

        op = integers[0];

        connect();

        // Estrutura de decisão
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
                    Toast.makeText(tela, "Cadastro efetuado com suceso!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Falha ao efetuar cadastro!", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                if (aBoolean == false)
                    Toast.makeText(tela, "Usuario não encontrado!", Toast.LENGTH_SHORT).show();
                break;

            case 2:
                if (aBoolean == false)
                    Toast.makeText(tela, "Procuador não encontrado!", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                if (aBoolean == false)
                    Toast.makeText(tela, "Falha ao excluír procurador", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Procuador excluido com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                if (aBoolean == false)
                    Toast.makeText(tela, "Falha ao alterar dados do procurador", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Procuador alterado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                if (aBoolean == false)
                    Toast.makeText(tela, "Trabalhador não cadastrado!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Trabalhador registrado com suceso!", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                if (aBoolean == false)
                    Toast.makeText(tela, "Falha ao excluír trabalhador", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Trabalhador excluido com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                if (aBoolean == false)
                    Toast.makeText(tela, "Falha ao alterar dados do trabalhador!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Trabalhador alterado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                if (aBoolean == false)
                    Toast.makeText(tela, "Administrador não Cadastrado!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Administrador registrado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 9:
                if (aBoolean == false)
                    Toast.makeText(tela, "Falha ao excluír administrador!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Administrador excluído com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 10:
                if (aBoolean == false)
                    Toast.makeText(tela, "Falha ao alterar dados do administrador!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Administrador alterado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 11:
                if (aBoolean == false)
                    Toast.makeText(tela, "Falha ao tentar registrar o trabalhador!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Trabalhador cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 12:
                if (aBoolean == false)
                    Toast.makeText(tela, "Falha ao tentar registrar o administrador!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Administrador cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 13:
                if (aBoolean == false)
                    Toast.makeText(tela, "Falha ao efetuar pesquisa!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Pesquisa realizada com sucesso!", Toast.LENGTH_SHORT).show();

                break;
            case 14:
                if (aBoolean == false)
                    Toast.makeText(tela, "Falha ao efetuar registro do serviço!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Serviço registrado com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            case 15:
                if (aBoolean == false)
                    Toast.makeText(tela, "Falha ao efetuar o pedido de serviço!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(tela, "Serviço enviado ao procurador!", Toast.LENGTH_SHORT).show();
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

            // CORPO DOS COMANDOS DE BUSCA NAS TABELAS
            String sql = "select * from procurador where email=? and senha=? and status = 1";

            String sql2 = "select * from administrador where email=? and senha=? and status= 1";

            String sql3 = "select * from trabalhador where email=? and senha=? and status = 1";

            // Comando de execução sql para cada tabela
            PreparedStatement comando = conexao.prepareStatement(sql);

            PreparedStatement comando2 = conexao.prepareStatement(sql2);

            PreparedStatement comando3 = conexao.prepareStatement(sql3);

            // Criptografando as informações recebidas para fazer a busca
            comando.setString(1, cripto.encrypt(getProcClass().getEmail().getBytes()).replace("\n",""));
            comando.setString(2, cripto.encrypt(getProcClass().getSenha().getBytes()).replace("\n",""));

            comando2.setString(1,cripto.encrypt(getAdmClass().getEmail().getBytes()).replace("\n",""));
            comando2.setString(2,cripto.encrypt(getAdmClass().getSenha().getBytes()).replace("\n",""));

            comando3.setString(1, cripto.encrypt(getTrabClass().getEmail().getBytes()).replace("\n",""));
            comando3.setString(1, cripto.encrypt(getTrabClass().getSenha().getBytes()).replace("\n",""));

            // Armazena o resultado da busca na tabela memória
            ResultSet tabelaMemoria = comando.executeQuery();
            ResultSet tabelaMemoria2 = comando2.executeQuery();
            ResultSet tabelaMemoria3 = comando3.executeQuery();

            // Caso sejam encontrados registros
            if (tabelaMemoria.next()) {
                // Recebe o número de identificação do procurador
                pesqProcPerfil.setId(tabelaMemoria.getInt("id_procurador"));

                loginProc = true;
                loginAdm = false;
                loginTrab = false;
                return true;
                // Caso o registro se refira a um administrador
            } else {

                if (tabelaMemoria2.next()) {

                    loginAdm = true;
                    loginTrab = false;
                    loginProc = false;
                    return true;
                    // Caso o registro refira-se a um trabalhador
                } else {
                    if (tabelaMemoria3.next()) {
                        // Recebe o número de identificação do trabalhador
                        PesqTrab.setId(tabelaMemoria3.getInt("id_trabalhador"));
                        loginTrab = true;
                        loginProc = false;
                        loginAdm = false;
                        return true;
                        // Caso não seja encontrado nenhum registro
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
            // Caso haja erro por parte do MySQL
        } catch (SQLException e) {
            // Exibição do erro
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

            // Corpo do comando INSERT na tabela serviço
            String sql = "insert into servico values(0,?,null,?,?,?,'','',?,?,?,default,?,?,?,?);";
            PreparedStatement comando = conexao.prepareStatement(sql);

            // Nesta parte ocorre a chamada da criptografia para receber as informações
            // E criptografá-las para serem registradas
            comando.setInt(1, pesqProcPerfil.getId());
            comando.setInt(2, servClass.getId_pagamento());
            comando.setInt(3, servClass.getId_tipo_trab());
            comando.setString(4, cripto.encrypt(servClass.getTitulo().getBytes()).replace("\n",""));
            comando.setString(5, cripto.encrypt(servClass.getDescricao().getBytes()).replace("\n",""));
            comando.setString(6, cripto.encrypt(String.valueOf(servClass.getValor()).getBytes()).replace("\n",""));
            comando.setString(7, cripto.encrypt(servClass.getObservacoes().getBytes()).replace("\n",""));
            comando.setString(8, cripto.encrypt(servClass.getCep().getBytes()).replace("\n",""));
            comando.setString(9, cripto.encrypt(String.valueOf(servClass.getNum_residencial()).getBytes()).replace("\n",""));
            comando.setString(10, cripto.encrypt(servClass.getComplemento().getBytes()).replace("\n",""));
            comando.setInt(11, servClass.getStatus());
            // Execução do comando
            comando.executeUpdate();

            return true;

            // Caso haja erro por parte do MySQL
        } catch (SQLException e) {
            // Exibição do erro
            e.printStackTrace();
            return false;
        }
    }

    // Método de registro de procurador (comando INSERT)
    public Boolean inserirProc() {
        try {
            // Formatação da data para o usuário
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataUtil = formato.parse(ProcClass.getData_nasc());

            java.sql.Date dataMySQL = new java.sql.Date(dataUtil.getTime());

            // Corpo do comando INSERT na tabela procurador
            String sql = "insert into procurador values(0,?,'foto',?,?,default,?,?,?,?,?,?,?,?,1" +
                    ");";
            // Comando de execução
            PreparedStatement comando = conexao.prepareStatement(sql);

            // Criptografa as informações recebidas para poder registr-á-las no banco
            comando.setString(1, cripto.encrypt(ProcClass.getNome().getBytes()).replace("\n",""));
            comando.setString(2, cripto.encrypt(ProcClass.getSexo().getBytes()).replace("\n",""));
            comando.setDate(3, dataMySQL);
            comando.setString(4, cripto.encrypt(ProcClass.getCpf().getBytes()).replace("\n",""));
            comando.setString(5, cripto.encrypt(ProcClass.getTel().getBytes()).replace("\n",""));
            comando.setString(6, cripto.encrypt(ProcClass.getEmail().getBytes()).replace("\n",""));
            comando.setString(7, cripto.encrypt(ProcClass.getSenha().getBytes()).replace("\n",""));
            comando.setString(8, cripto.encrypt(ProcClass.getCep().getBytes()).replace("\n",""));
            comando.setString(9, cripto.encrypt(ProcClass.getNum_residencial().getBytes()).replace("\n",""));
            comando.setString(10, cripto.encrypt(ProcClass.getComplemento().getBytes()).replace("\n",""));
            comando.setDouble(11, Double.parseDouble(ProcClass.getPontuacao()));
            // Exeução do comando
            comando.executeUpdate();

            return true;

            // Caso haja erro por parte do MySQL
        } catch (SQLException e) {
            // Exibição do erro
            e.printStackTrace();
            return false;
            // Caso não haja concordância das informações recebidas
        } catch (ParseException e) {
            // Exibição do erro
            e.printStackTrace();
            return false;
        }
    }

    // Método de busca de procurador (comando SELECT) com base no nome
    // e no status ativo (1)
    public Boolean pesquisarProc() {
        try {
            Date dataTMP;
            // Formatação da data para a o usuário
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataTMP1;
            SimpleDateFormat formatoData2 = new SimpleDateFormat("dd/MM/yyyy");

            // Corpo do comando SELECT da tabela procurador
            // Restringido aos procuradores ativos (status = 1)
            String sql = "select * from procurador where nome=? and status=1";

            // Comando de execução
            PreparedStatement comando = conexao.prepareStatement(sql);

            // Recebe o nome e criptografa ele para ser utilizado na busca das informações que também estão criptografadas
            // na tabela procurador
            comando.setString(1, cripto.encrypt(ProcClass.getNome().getBytes()).replace("\n",""));

            // Recebe as informações
            ResultSet tabelaMemoria = comando.executeQuery();

            // Caso sejam encontrados registros
            if (tabelaMemoria.next()) {

                // Os dados serão descriptografados e armazenados na tabela temporária
                // para ser carregado na tela
                ProcClass.setId_procurador(tabelaMemoria.getInt("id_procurador"));
                ProcClass.setNome(cripto.decrypt(tabelaMemoria.getString("nome")));
                ProcClass.setFoto(cripto.decrypt(tabelaMemoria.getString("foto")));
                ProcClass.setSexo(cripto.decrypt(tabelaMemoria.getString("sexo")));

                dataTMP = tabelaMemoria.getDate("data_nasc");
                ProcClass.setData_nasc(formatoData.format(dataTMP));

                dataTMP1 = tabelaMemoria.getDate("data_cadastro");
                ProcClass.setData_cadastro(formatoData2.format(dataTMP1));

                ProcClass.setCpf(cripto.decrypt(tabelaMemoria.getString("cpf")));
                ProcClass.setTel(cripto.decrypt(tabelaMemoria.getString("tel")));
                ProcClass.setEmail(cripto.decrypt(tabelaMemoria.getString("email")));
                ProcClass.setSenha(cripto.decrypt(tabelaMemoria.getString("senha")));
                ProcClass.setCep(cripto.decrypt(tabelaMemoria.getString("cep")));
                ProcClass.setNum_residencial(cripto.decrypt(tabelaMemoria.getString("num_residencial")));
                ProcClass.setComplemento(cripto.decrypt(tabelaMemoria.getString("complemento")));
                ProcClass.setPontuacao(String.valueOf(tabelaMemoria.getDouble("pontuacao")));

                ProcClass.setNome(tabelaMemoria.getString("nome"));
                ProcClass.setFoto(tabelaMemoria.getString("foto"));
                ProcClass.setSexo(tabelaMemoria.getString("sexo"));

                return true;
            } else {
                // Caso não sejam encontrados registros coerentes
                ProcClass = null;
                return false;
            }
            // Caso haja erro por parte do MySQL
        } catch (SQLException e) {
            // Exibição do erro
            e.printStackTrace();
            return false;
        }
    }

    // Método para carregar as informações pessoais do procurador em seu perfil (comando SELECT)
    // restringido aos usuários ativos (status = 1)
    public Boolean PerfilProc() {
        try {
            Date dataTMP;
            // Formatação da data para o usuário
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataTMP1;
            SimpleDateFormat formatoData2 = new SimpleDateFormat("dd/MM/yyyy");

            // Corpo do comando SELECT na tabela procurador que irá retornar todos
            // os registros dos procuradores ativos (stats = 1)
            String sql = "select * from procurador where id_procurador=? and status=1";

            // Declaração do comando de execução
            PreparedStatement comando = conexao.prepareStatement(sql);
           // Recebe o número de identificação do procurador
            comando.setInt(1, ProcClass.getId_procurador());
           // Recebe o resultado da busca
            ResultSet tabelaMemoria = comando.executeQuery();

            // Caso sejam encontrados registros
            if (tabelaMemoria.next()) {

                // As informações do banco referentes aquele procurador serão descriptografadas
                // e armazenadas na tabela temporária para ser mostradas na tela
                ProcClass.setId_procurador(tabelaMemoria.getInt("id_procurador"));
                ProcClass.setNome(cripto.decrypt(tabelaMemoria.getString("nome")));
                ProcClass.setFoto(cripto.decrypt(tabelaMemoria.getString("foto")));
                ProcClass.setSexo(cripto.decrypt(tabelaMemoria.getString("sexo")));

                dataTMP = tabelaMemoria.getDate("data_nasc");
                ProcClass.setData_nasc(formatoData.format(dataTMP));

                dataTMP1 = tabelaMemoria.getDate("data_cadastro");
                ProcClass.setData_cadastro(formatoData2.format(dataTMP1));

                ProcClass.setCpf(cripto.decrypt(tabelaMemoria.getString("cpf")));
                ProcClass.setTel(cripto.decrypt(tabelaMemoria.getString("tel")));
                ProcClass.setEmail(cripto.decrypt(tabelaMemoria.getString("email")));
                ProcClass.setSenha(cripto.decrypt(tabelaMemoria.getString("senha")));
                ProcClass.setCep(cripto.decrypt(tabelaMemoria.getString("cep")));
                ProcClass.setNum_residencial(cripto.decrypt(tabelaMemoria.getString("num_residencial")));
                ProcClass.setComplemento(cripto.decrypt(tabelaMemoria.getString("complemento")));
                ProcClass.setPontuacao(String.valueOf(tabelaMemoria.getDouble("pontuacao")));

                return true;
                // Caso não for possível encontrar o registro referente a busca
            } else {
                ProcClass = null;
                return false;
            }
            // Caso houver erro por parte do MySQL
        } catch (SQLException e) {
            // Exibição do erro
            e.printStackTrace();
            return false;
        }
    }

    // Método para desativação da conta de um procurador (comando UPDATE)
    // O comando alterará o status de ativo (1) para inativo (0)
    public Boolean excluirProc() {

        try {
            // Corpo do comando para desativar um procurador
            String sql = " update procurador set status=0 WHERE ID_procurador = ?";
            // Declaração do comando de execução
            PreparedStatement comando = conexao.prepareStatement(sql);

            // Recebe o número de identificação do procurador
            comando.setInt(1, ProcClass.getId_procurador());
            // Execução do comando
            comando.executeUpdate();

            return true;

            // Caso haja erro por parte do MySQL
        } catch (SQLException ex) {
            // Exibição do erro
            ex.printStackTrace();
            return false;
        }

    }

    // Método de alteração de dados de um procurado (comando UPDATE)
    public Boolean alterarProc() {
        try {
            // Formatação da data para o usuário
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataUtil = formatoData.parse(ProcClass.getData_nasc());

            java.sql.Date dataMyqsl = new java.sql.Date(dataUtil.getTime());

            // Corpo do comando de alteração de dados (UPDATE)
            String sql =
                    " UPDATE procurador SET nome=?, sexo=?, data_nasc=?, cpf=?, tel=?,email=?,senha=?,cep=?,num_residencial=?,complemento=?"
                            + " WHERE id_procurador=?";
            // Declaração do comando de execução
            PreparedStatement comando = conexao.prepareStatement(sql);

            // As informações inseridas na tela serão criptografadas para serem inseridas na tabela através do comando UPDATE
            comando.setString(1, cripto.encrypt(ProcClass.getNome().getBytes()).replace("\n",""));
            comando.setString(2, cripto.encrypt(ProcClass.getSexo().getBytes()).replace("\n",""));
            comando.setDate(3, dataMyqsl);
            comando.setString(4, cripto.encrypt(ProcClass.getCpf().getBytes()).replace("\n",""));
            comando.setString(5, cripto.encrypt(ProcClass.getTel().getBytes()).replace("\n",""));
            comando.setString(6, cripto.encrypt(ProcClass.getEmail().getBytes()).replace("\n",""));
            comando.setString(7, cripto.encrypt(ProcClass.getSenha().getBytes()).replace("\n",""));
            comando.setString(8, cripto.encrypt(ProcClass.getCep().getBytes()).replace("\n",""));
            comando.setString(9, cripto.encrypt(ProcClass.getNum_residencial().getBytes()).replace("\n",""));
            comando.setString(10, cripto.encrypt(ProcClass.getComplemento().getBytes()).replace("\n",""));
            comando.setInt(11, ProcClass.getId_procurador());

            // Execução do comando
            comando.executeUpdate();

            return true;
            // Caso não haja coerência das informações recebidas
        } catch (ParseException e) {
            // Exibição do erro
            e.printStackTrace();
            return false;
            // Caso haja erro por parte do MySQL
        } catch (SQLException e) {
            // Exibição do erro
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
            comando.setString(2, cripto.encrypt(trabClass.getNome().getBytes()).replace("\n",""));
            comando.setString(3, cripto.encrypt(trabClass.getCpf().getBytes()).replace("\n",""));
            comando.setDate(4, dataMySQL);
            comando.setString(5, cripto.encrypt(trabClass.getSexo().getBytes()).replace("\n",""));
            comando.setString(6, cripto.encrypt(trabClass.getTel().getBytes()).replace("\n",""));
            comando.setString(7, cripto.encrypt(trabClass.getEmail().getBytes()).replace("\n",""));
            comando.setString(8, cripto.encrypt(trabClass.getSenha().getBytes()).replace("\n",""));
            comando.setString(9, cripto.encrypt(trabClass.getCep().getBytes()).replace("\n",""));
            comando.setString(10, cripto.encrypt(trabClass.getNum_residencial().getBytes()).replace("\n",""));
            comando.setString(11, cripto.encrypt(trabClass.getComplemento().getBytes()).replace("\n",""));

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
            // Formatação da data para o usuário
            Date dataTMP;
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Date dataTMP1;
            SimpleDateFormat formatoData2 = new SimpleDateFormat("dd/MM/yyyy");

            String sql = "select * from trabalhador where nome=? and status=1";
            // Declaração do comando de execuação
            PreparedStatement comando = conexao.prepareStatement(sql);

            // Criptografa o nome para servir de busca nos registros da tabela
            comando.setString(1, cripto.encrypt(trabClass.getNome().getBytes()).replace("\n",""));

            // Recebe os resultados da pesquisa
            ResultSet tabelaMemoria = comando.executeQuery();

            // Caso hajam registros
            if (tabelaMemoria.next()) {

                // As informações serão descriptografadas e armazenadas na tabela temporária
                // para serem exibidas na tela
                trabClass.setId_trabalhador(tabelaMemoria.getInt("id_trabalhador"));
                trabClass.setId_tipo_pacote(tabelaMemoria.getInt("id_tipo_pacote"));
                trabClass.setNome(cripto.decrypt(tabelaMemoria.getString("nome")));
                trabClass.setFoto(cripto.decrypt(tabelaMemoria.getString("foto")));
                trabClass.setSexo(cripto.decrypt(tabelaMemoria.getString("sexo")));

                dataTMP = tabelaMemoria.getDate("data_nasc");
                trabClass.setData_nasc(formatoData.format(dataTMP));

                dataTMP1 = tabelaMemoria.getDate("data_registro");
                trabClass.setData_registro(formatoData2.format(dataTMP1));

                trabClass.setCpf(cripto.decrypt(tabelaMemoria.getString("cpf")));
                trabClass.setTel(cripto.decrypt(tabelaMemoria.getString("tel")));
                trabClass.setEmail(cripto.decrypt(tabelaMemoria.getString("email")));
                trabClass.setSenha(cripto.decrypt(tabelaMemoria.getString("senha")));
                trabClass.setCep(cripto.decrypt(tabelaMemoria.getString("cep")));
                trabClass.setNum_residencial(cripto.decrypt(tabelaMemoria.getString("num_residencial")));
                trabClass.setComplemento(cripto.decrypt(tabelaMemoria.getString("complemento")));

                return true;
                // Caso não seja possível encontrar registros coerentes com a busca
            } else {
                trabClass = null;
                return false;
            }
            // Caso haja erro por parte do MySQL
        } catch (SQLException e) {
            // Exibição do erro
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
            comando.setString(1, cripto.encrypt(trabClass.getNome().getBytes()).replace("\n",""));
            comando.setString(2, cripto.encrypt(trabClass.getSexo().getBytes()).replace("\n",""));
            comando.setDate(3, dataMyqsl);
            comando.setString(4, cripto.encrypt(trabClass.getCpf().getBytes()).replace("\n",""));
            comando.setString(5, cripto.encrypt(trabClass.getTel().getBytes()).replace("\n",""));
            comando.setString(6, cripto.encrypt(trabClass.getEmail().getBytes()).replace("\n",""));
            comando.setString(7, cripto.encrypt(trabClass.getSenha().getBytes()).replace("\n",""));
            comando.setString(8, cripto.encrypt(trabClass.getCep().getBytes()).replace("\n",""));
            comando.setString(9, cripto.encrypt(trabClass.getNum_residencial().getBytes()).replace("\n",""));
            comando.setString(10, cripto.encrypt(trabClass.getComplemento().getBytes()).replace("\n",""));
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
                            + " WHERE id_servico=?";

            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, cripto.encrypt(String.valueOf(servClass.getValor()).getBytes()).replace("\n",""));
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

    // Método de alteração de dados do serviço (comando UPDATE) pelo procurador
    public Boolean altServProc() {
        try {
            String sql = " UPDATE servico set status=2"
                    + " WHERE id_servico=?";

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

    // Método par alteração de status do contrato par
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

            comando.setString(1, cripto.encrypt(admClass.getNome().getBytes()).replace("\n",""));
            comando.setString(2, cripto.encrypt(admClass.getFoto().getBytes()).replace("\n",""));
            comando.setString(3, cripto.encrypt(admClass.getSexo().getBytes()).replace("\n",""));
            comando.setString(4, cripto.encrypt(admClass.getCpf().getBytes()).replace("\n",""));
            comando.setDate(5, dataMySQL);
            comando.setString(6, cripto.encrypt(admClass.getEmail().getBytes()).replace("\n",""));
            comando.setString(7, cripto.encrypt(admClass.getSenha().getBytes()).replace("\n",""));
            comando.setString(8, cripto.encrypt(admClass.getTel().getBytes()).replace("\n",""));
            comando.setString(9, cripto.encrypt(admClass.getCep().getBytes()).replace("\n",""));
            comando.setString(10, cripto.encrypt(String.valueOf(admClass.getNum_residencial()).getBytes()).replace("\n",""));
            comando.setString(11, cripto.encrypt(admClass.getComplemento().getBytes()).replace("\n",""));

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
            comando.setString(1, cripto.encrypt(admClass.getNome().getBytes()).replace("\n",""));
            ResultSet tabelaMemoria = comando.executeQuery();

            if (tabelaMemoria.next()) {

                admClass.setId_adm(tabelaMemoria.getInt("id_adm"));

                admClass.setNome(cripto.decrypt(tabelaMemoria.getString("nome")));
                admClass.setFoto(cripto.decrypt(tabelaMemoria.getString("foto")));
                admClass.setSexo(cripto.decrypt(tabelaMemoria.getString("sexo")));

                dataTMP = tabelaMemoria.getDate("data_nasc");
                admClass.setData_nasc(formatoData.format(dataTMP));

                dataTMP1 = tabelaMemoria.getDate("data_cadastro");
                admClass.setData_registro(formatoData2.format(dataTMP1));

                admClass.setCpf(cripto.decrypt(tabelaMemoria.getString("cpf")));
                admClass.setTel(cripto.decrypt(tabelaMemoria.getString("tel")));
                admClass.setEmail(cripto.decrypt(tabelaMemoria.getString("email")));
                admClass.setSenha(cripto.decrypt(tabelaMemoria.getString("senha")));
                admClass.setCep(cripto.decrypt(tabelaMemoria.getString("cep")));
                admClass.setNum_residencial(cripto.decrypt(tabelaMemoria.getString("num_residencial")));
                admClass.setComplemento(cripto.decrypt(tabelaMemoria.getString("complemento")));

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

            comando.setString(1, cripto.encrypt(admClass.getNome().getBytes()).replace("\n",""));
            comando.setString(2, cripto.encrypt(admClass.getSexo().getBytes()).replace("\n",""));
            comando.setDate(3,  dataMyqsl);
            comando.setString(4, cripto.encrypt(admClass.getCpf().getBytes()).replace("\n",""));
            comando.setString(5, cripto.encrypt(admClass.getTel().getBytes()).replace("\n",""));
            comando.setString(6, cripto.encrypt(admClass.getEmail().getBytes()).replace("\n",""));
            comando.setString(7, cripto.encrypt(admClass.getSenha().getBytes()).replace("\n",""));
            comando.setString(8, cripto.encrypt(admClass.getCep().getBytes()).replace("\n",""));
            comando.setString(9, cripto.encrypt(admClass.getNum_residencial().getBytes()).replace("\n",""));
            comando.setString(10, cripto.encrypt(admClass.getComplemento().getBytes()).replace("\n",""));
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
