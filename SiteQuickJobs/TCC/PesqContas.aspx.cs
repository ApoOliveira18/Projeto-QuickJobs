using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class PesqContasInativas : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
         if (Session["tipoUsu"].ToString() != "ADM")
         {
             Response.Redirect("Login.aspx");
         }
        
    }
    #region
    private void CarregarTrabNome()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaTrabDescripto = new DataTable();

        listaTrabDescripto.Columns.Add("id_trabalhador", typeof(int));
        listaTrabDescripto.Columns.Add("nome", typeof(string));
        listaTrabDescripto.Columns.Add("foto", typeof(string));
        listaTrabDescripto.Columns.Add("sexo", typeof(string));
        listaTrabDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaTrabDescripto.Columns.Add("data_registro", typeof(DateTime));
        listaTrabDescripto.Columns.Add("tel", typeof(string));
        listaTrabDescripto.Columns.Add("email", typeof(string));
        listaTrabDescripto.Columns.Add("cpf", typeof(string));
        listaTrabDescripto.Columns.Add("cep", typeof(string));
        listaTrabDescripto.Columns.Add("num_residencial", typeof(string));
        listaTrabDescripto.Columns.Add("pontuacao", typeof(double));
        listaTrabDescripto.Columns.Add("tipo_pacote", typeof(string));

        DataView listaTrabCripto = (DataView)sqlTrab.Select(DataSourceSelectArguments.Empty);
        //Pesquisando com filtro
        listaTrabDescripto.DefaultView.RowFilter = "nome like'" + txtNome.Text + "%'";

        //dando valor as colunas criadas
        for (int i = 0; i < listaTrabCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaTrabDescripto.NewRow();          
            linha["nome"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaTrabCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_registro"] = listaTrabCripto.Table.Rows[i]["data_registro"].ToString();
            linha["tel"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["num_residencial"].ToString());
            linha["pontuacao"] = listaTrabCripto.Table.Rows[i]["pontuacao"].ToString();
            linha["tipo_pacote"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["tipo_pacote"].ToString());

            listaTrabDescripto.Rows.Add(linha);

        }
        gvExibirTrabalhador.DataSource = listaTrabDescripto;
        gvExibirTrabalhador.DataBind();
        gvExibirTrabalhador.Visible = true;

        gvExibirProc.Visible = false;
        gvExibir.Visible = false;
    }

    private void CarregarProcNome()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaProcDescripto = new DataTable();

        listaProcDescripto.Columns.Add("id_procurador", typeof(int));
        listaProcDescripto.Columns.Add("nome", typeof(string));
        listaProcDescripto.Columns.Add("foto", typeof(string));
        listaProcDescripto.Columns.Add("sexo", typeof(string));
        listaProcDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaProcDescripto.Columns.Add("data_cadastro", typeof(DateTime));
        listaProcDescripto.Columns.Add("tel", typeof(string));
        listaProcDescripto.Columns.Add("email", typeof(string));
        listaProcDescripto.Columns.Add("cpf", typeof(string));
        listaProcDescripto.Columns.Add("cep", typeof(string));
        listaProcDescripto.Columns.Add("num_residencial", typeof(string));
        listaProcDescripto.Columns.Add("pontuacao", typeof(double));


        DataView listaProcCripto = (DataView)sqlProc.Select(DataSourceSelectArguments.Empty);
        //Pesquisando com filtro
        listaProcDescripto.DefaultView.RowFilter = "nome like'" + txtNome.Text + "%'";

        //dando valor as colunas criadas
        for (int i = 0; i < listaProcCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaProcDescripto.NewRow();
            linha["nome"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaProcCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_cadastro"] = listaProcCripto.Table.Rows[i]["data_cadastro"].ToString();
            linha["tel"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["num_residencial"].ToString());
            linha["pontuacao"] = listaProcCripto.Table.Rows[i]["pontuacao"].ToString();


            listaProcDescripto.Rows.Add(linha);

        }
        gvExibirProc.DataSource = listaProcDescripto;
        gvExibirProc.DataBind();
        gvExibirProc.Visible = true;

        gvExibirTrabalhador.Visible = false;
        gvExibir.Visible = false;
    }

    private void CarregarADMNome()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaADMDescripto = new DataTable();

        listaADMDescripto.Columns.Add("id_adm", typeof(int));
        listaADMDescripto.Columns.Add("nome", typeof(string));
        listaADMDescripto.Columns.Add("foto", typeof(string));
        listaADMDescripto.Columns.Add("sexo", typeof(string));
        listaADMDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaADMDescripto.Columns.Add("data_cadastro", typeof(DateTime));
        listaADMDescripto.Columns.Add("tel", typeof(string));
        listaADMDescripto.Columns.Add("email", typeof(string));
        listaADMDescripto.Columns.Add("cpf", typeof(string));
        listaADMDescripto.Columns.Add("cep", typeof(string));
        listaADMDescripto.Columns.Add("num_residencial", typeof(string));



        DataView listaADMCripto = (DataView)sqlPesqADM.Select(DataSourceSelectArguments.Empty);
        //Pesquisando com filtro
        listaADMDescripto.DefaultView.RowFilter = "nome like'" + txtNome.Text + "%'";

        //dando valor as colunas criadas
        for (int i = 0; i < listaADMCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaADMDescripto.NewRow();
            

            linha["nome"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaADMCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_cadastro"] = listaADMCripto.Table.Rows[i]["data_cadastro"].ToString();
            linha["tel"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["num_residencial"].ToString());



            listaADMDescripto.Rows.Add(linha);

        }
        gvExibir.DataSource = listaADMDescripto;
        gvExibir.DataBind();
        gvExibir.Visible = true;

        gvExibirProc.Visible = false;
        gvExibirTrabalhador.Visible = false;

    }

    private void CarregarTrabTodos()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaTrabDescripto = new DataTable();
        listaTrabDescripto.Columns.Add("id_trabalhador", typeof(int));
        listaTrabDescripto.Columns.Add("nome", typeof(string));
        listaTrabDescripto.Columns.Add("foto", typeof(string));
        listaTrabDescripto.Columns.Add("sexo", typeof(string));
        listaTrabDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaTrabDescripto.Columns.Add("data_registro", typeof(DateTime));
        listaTrabDescripto.Columns.Add("tel", typeof(string));
        listaTrabDescripto.Columns.Add("email", typeof(string));
        listaTrabDescripto.Columns.Add("cpf", typeof(string));
        listaTrabDescripto.Columns.Add("cep", typeof(string));
        listaTrabDescripto.Columns.Add("num_residencial", typeof(string));
        listaTrabDescripto.Columns.Add("pontuacao", typeof(double));
        listaTrabDescripto.Columns.Add("tipo_pacote", typeof(string));

        DataView listaTrabCripto = (DataView)sqlTrab.Select(DataSourceSelectArguments.Empty);


        //dando valor as colunas criadas
        for (int i = 0; i < listaTrabCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaTrabDescripto.NewRow();
            linha["nome"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaTrabCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_registro"] = listaTrabCripto.Table.Rows[i]["data_registro"].ToString();
            linha["tel"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["num_residencial"].ToString());
            linha["pontuacao"] = listaTrabCripto.Table.Rows[i]["pontuacao"].ToString();
            linha["tipo_pacote"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["tipo_pacote"].ToString());

            listaTrabDescripto.Rows.Add(linha);

        }
        //Exibindo no Grid
        gvExibirTrabalhador.DataSource = listaTrabDescripto;
        gvExibirTrabalhador.DataBind();
        gvExibirTrabalhador.Visible = true;
        //Escondendo outros Grids
        gvExibirProc.Visible = false;
        gvExibir.Visible = false;
    }

    private void CarregarProcTodos()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaProcDescripto = new DataTable();

        listaProcDescripto.Columns.Add("id_procurador", typeof(int));
        listaProcDescripto.Columns.Add("nome", typeof(string));
        listaProcDescripto.Columns.Add("foto", typeof(string));
        listaProcDescripto.Columns.Add("sexo", typeof(string));
        listaProcDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaProcDescripto.Columns.Add("data_cadastro", typeof(DateTime));
        listaProcDescripto.Columns.Add("tel", typeof(string));
        listaProcDescripto.Columns.Add("email", typeof(string));
        listaProcDescripto.Columns.Add("cpf", typeof(string));
        listaProcDescripto.Columns.Add("cep", typeof(string));
        listaProcDescripto.Columns.Add("num_residencial", typeof(string));
        listaProcDescripto.Columns.Add("pontuacao", typeof(double));


        DataView listaProcCripto = (DataView)sqlProc.Select(DataSourceSelectArguments.Empty);

        //dando valor as colunas criadas
        for (int i = 0; i < listaProcCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaProcDescripto.NewRow();
            linha["nome"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaProcCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_cadastro"] = listaProcCripto.Table.Rows[i]["data_cadastro"].ToString();
            linha["tel"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["num_residencial"].ToString());
            linha["pontuacao"] = listaProcCripto.Table.Rows[i]["pontuacao"].ToString();


            listaProcDescripto.Rows.Add(linha);

        }
        gvExibirProc.DataSource = listaProcDescripto;
        gvExibirProc.DataBind();
        gvExibirProc.Visible = true;

        gvExibirTrabalhador.Visible = false;
        gvExibir.Visible = false;
    }

    private void CarregarADMTodos()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaADMDescripto = new DataTable();

        listaADMDescripto.Columns.Add("id_adm", typeof(int));
        listaADMDescripto.Columns.Add("nome", typeof(string));
        listaADMDescripto.Columns.Add("foto", typeof(string));
        listaADMDescripto.Columns.Add("sexo", typeof(string));
        listaADMDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaADMDescripto.Columns.Add("data_cadastro", typeof(DateTime));
        listaADMDescripto.Columns.Add("tel", typeof(string));
        listaADMDescripto.Columns.Add("email", typeof(string));
        listaADMDescripto.Columns.Add("cpf", typeof(string));
        listaADMDescripto.Columns.Add("cep", typeof(string));
        listaADMDescripto.Columns.Add("num_residencial", typeof(string));



        DataView listaADMCripto = (DataView)sqlPesqADM.Select(DataSourceSelectArguments.Empty);
        //Pesquisando com filtro
        listaADMDescripto.DefaultView.RowFilter = "nome like'" + txtNome.Text + "%'";

        //dando valor as colunas criadas
        for (int i = 0; i < listaADMCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaADMDescripto.NewRow();
            linha["nome"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaADMCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_cadastro"] = listaADMCripto.Table.Rows[i]["data_cadastro"].ToString();
            linha["tel"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["num_residencial"].ToString());



            listaADMDescripto.Rows.Add(linha);

        }
        gvExibir.DataSource = listaADMDescripto;
        gvExibir.DataBind();
        gvExibir.Visible = true;

        gvExibirProc.Visible = false;
        gvExibirTrabalhador.Visible = false;
    }
    #endregion

    #region
    private void CarregarTrabNomeDesativar()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaTrabDescripto = new DataTable();

        listaTrabDescripto.Columns.Add("id_trabalhador", typeof(int));
        listaTrabDescripto.Columns.Add("nome", typeof(string));
        listaTrabDescripto.Columns.Add("foto", typeof(string));
        listaTrabDescripto.Columns.Add("sexo", typeof(string));
        listaTrabDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaTrabDescripto.Columns.Add("data_registro", typeof(DateTime));
        listaTrabDescripto.Columns.Add("tel", typeof(string));
        listaTrabDescripto.Columns.Add("email", typeof(string));
        listaTrabDescripto.Columns.Add("cpf", typeof(string));
        listaTrabDescripto.Columns.Add("cep", typeof(string));
        listaTrabDescripto.Columns.Add("num_residencial", typeof(string));
        listaTrabDescripto.Columns.Add("pontuacao", typeof(double));
        listaTrabDescripto.Columns.Add("tipo_pacote", typeof(string));

        DataView listaTrabCripto = (DataView)sqlTrabDesativar.Select(DataSourceSelectArguments.Empty);
        //Pesquisando com filtro
        listaTrabDescripto.DefaultView.RowFilter = "nome like'" + txtNome.Text + "%'";

        //dando valor as colunas criadas
        for (int i = 0; i < listaTrabCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaTrabDescripto.NewRow();
            linha["nome"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaTrabCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_registro"] = listaTrabCripto.Table.Rows[i]["data_registro"].ToString();
            linha["tel"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["num_residencial"].ToString());
            linha["pontuacao"] = listaTrabCripto.Table.Rows[i]["pontuacao"].ToString();
            linha["tipo_pacote"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["tipo_pacote"].ToString());

            listaTrabDescripto.Rows.Add(linha);

        }
        gvExibirTrabalhador.DataSource = listaTrabDescripto;
        gvExibirTrabalhador.DataBind();
        gvExibirTrabalhador.Visible = true;

        gvExibirProc.Visible = false;
        gvExibir.Visible = false;
    }

    private void CarregarProcNomeDesativar()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaProcDescripto = new DataTable();

        listaProcDescripto.Columns.Add("id_procurador", typeof(int));
        listaProcDescripto.Columns.Add("nome", typeof(string));
        listaProcDescripto.Columns.Add("foto", typeof(string));
        listaProcDescripto.Columns.Add("sexo", typeof(string));
        listaProcDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaProcDescripto.Columns.Add("data_cadastro", typeof(DateTime));
        listaProcDescripto.Columns.Add("tel", typeof(string));
        listaProcDescripto.Columns.Add("email", typeof(string));
        listaProcDescripto.Columns.Add("cpf", typeof(string));
        listaProcDescripto.Columns.Add("cep", typeof(string));
        listaProcDescripto.Columns.Add("num_residencial", typeof(string));
        listaProcDescripto.Columns.Add("pontuacao", typeof(double));


        DataView listaProcCripto = (DataView)sqlProcDesativar
.Select(DataSourceSelectArguments.Empty);
        //Pesquisando com filtro
        listaProcDescripto.DefaultView.RowFilter = "nome like'" + txtNome.Text + "%'";

        //dando valor as colunas criadas
        for (int i = 0; i < listaProcCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaProcDescripto.NewRow();
            linha["nome"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaProcCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_cadastro"] = listaProcCripto.Table.Rows[i]["data_cadastro"].ToString();
            linha["tel"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["num_residencial"].ToString());
            linha["pontuacao"] = listaProcCripto.Table.Rows[i]["pontuacao"].ToString();


            listaProcDescripto.Rows.Add(linha);

        }
        gvExibirProc.DataSource = listaProcDescripto;
        gvExibirProc.DataBind();
        gvExibirProc.Visible = true;

        gvExibirTrabalhador.Visible = false;
        gvExibir.Visible = false;
    }

    private void CarregarADMNomeDesativar()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaADMDescripto = new DataTable();

        listaADMDescripto.Columns.Add("id_adm", typeof(int));
        listaADMDescripto.Columns.Add("nome", typeof(string));
        listaADMDescripto.Columns.Add("foto", typeof(string));
        listaADMDescripto.Columns.Add("sexo", typeof(string));
        listaADMDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaADMDescripto.Columns.Add("data_cadastro", typeof(DateTime));
        listaADMDescripto.Columns.Add("tel", typeof(string));
        listaADMDescripto.Columns.Add("email", typeof(string));
        listaADMDescripto.Columns.Add("cpf", typeof(string));
        listaADMDescripto.Columns.Add("cep", typeof(string));
        listaADMDescripto.Columns.Add("num_residencial", typeof(string));



        DataView listaADMCripto = (DataView)sqlADMDesativar.Select(DataSourceSelectArguments.Empty);
        //Pesquisando com filtro
        listaADMDescripto.DefaultView.RowFilter = "nome like'" + txtNome.Text + "%'";

        //dando valor as colunas criadas
        for (int i = 0; i < listaADMCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaADMDescripto.NewRow();


            linha["nome"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaADMCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_cadastro"] = listaADMCripto.Table.Rows[i]["data_cadastro"].ToString();
            linha["tel"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["num_residencial"].ToString());



            listaADMDescripto.Rows.Add(linha);

        }
        gvExibir.DataSource = listaADMDescripto;
        gvExibir.DataBind();
        gvExibir.Visible = true;

        gvExibirProc.Visible = false;
        gvExibirTrabalhador.Visible = false;

    }

    private void CarregarTrabDesativarTodos()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaTrabDescripto = new DataTable();
        listaTrabDescripto.Columns.Add("id_trabalhador", typeof(int));
        listaTrabDescripto.Columns.Add("nome", typeof(string));
        listaTrabDescripto.Columns.Add("foto", typeof(string));
        listaTrabDescripto.Columns.Add("sexo", typeof(string));
        listaTrabDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaTrabDescripto.Columns.Add("data_registro", typeof(DateTime));
        listaTrabDescripto.Columns.Add("tel", typeof(string));
        listaTrabDescripto.Columns.Add("email", typeof(string));
        listaTrabDescripto.Columns.Add("cpf", typeof(string));
        listaTrabDescripto.Columns.Add("cep", typeof(string));
        listaTrabDescripto.Columns.Add("num_residencial", typeof(string));
        listaTrabDescripto.Columns.Add("pontuacao", typeof(double));
        listaTrabDescripto.Columns.Add("tipo_pacote", typeof(string));

        DataView listaTrabCripto = (DataView)sqlTrabDesativar.Select(DataSourceSelectArguments.Empty);


        //dando valor as colunas criadas
        for (int i = 0; i < listaTrabCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaTrabDescripto.NewRow();
            linha["nome"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaTrabCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_registro"] = listaTrabCripto.Table.Rows[i]["data_registro"].ToString();
            linha["tel"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["num_residencial"].ToString());
            linha["pontuacao"] = listaTrabCripto.Table.Rows[i]["pontuacao"].ToString();
            linha["tipo_pacote"] = cripto.Decrypt(listaTrabCripto.Table.Rows[i]["tipo_pacote"].ToString());

            listaTrabDescripto.Rows.Add(linha);

        }
        gvExibirTrabalhador.DataSource = listaTrabDescripto;
        gvExibirTrabalhador.DataBind();
        gvExibirTrabalhador.Visible = true;

        gvExibirProc.Visible = false;
        gvExibir.Visible = false;
    }

    private void CarregarProcDesativarTodos()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaProcDescripto = new DataTable();

        listaProcDescripto.Columns.Add("id_procurador", typeof(int));
        listaProcDescripto.Columns.Add("nome", typeof(string));
        listaProcDescripto.Columns.Add("foto", typeof(string));
        listaProcDescripto.Columns.Add("sexo", typeof(string));
        listaProcDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaProcDescripto.Columns.Add("data_cadastro", typeof(DateTime));
        listaProcDescripto.Columns.Add("tel", typeof(string));
        listaProcDescripto.Columns.Add("email", typeof(string));
        listaProcDescripto.Columns.Add("cpf", typeof(string));
        listaProcDescripto.Columns.Add("cep", typeof(string));
        listaProcDescripto.Columns.Add("num_residencial", typeof(string));
        listaProcDescripto.Columns.Add("pontuacao", typeof(double));


        DataView listaProcCripto = (DataView)sqlProcDesativar.Select(DataSourceSelectArguments.Empty);

        //dando valor as colunas criadas
        for (int i = 0; i < listaProcCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaProcDescripto.NewRow();
            linha["nome"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaProcCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_cadastro"] = listaProcCripto.Table.Rows[i]["data_cadastro"].ToString();
            linha["tel"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaProcCripto.Table.Rows[i]["num_residencial"].ToString());
            linha["pontuacao"] = listaProcCripto.Table.Rows[i]["pontuacao"].ToString();


            listaProcDescripto.Rows.Add(linha);

        }
        gvExibirProc.DataSource = listaProcDescripto;
        gvExibirProc.DataBind();
        gvExibirProc.Visible = true;

        gvExibirTrabalhador.Visible = false;
        gvExibir.Visible = false;
    }

    private void CarregarADM_DesativarTodos()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaADMDescripto = new DataTable();

        listaADMDescripto.Columns.Add("id_adm", typeof(int));
        listaADMDescripto.Columns.Add("nome", typeof(string));
        listaADMDescripto.Columns.Add("foto", typeof(string));
        listaADMDescripto.Columns.Add("sexo", typeof(string));
        listaADMDescripto.Columns.Add("data_nasc", typeof(DateTime));
        listaADMDescripto.Columns.Add("data_cadastro", typeof(DateTime));
        listaADMDescripto.Columns.Add("tel", typeof(string));
        listaADMDescripto.Columns.Add("email", typeof(string));
        listaADMDescripto.Columns.Add("cpf", typeof(string));
        listaADMDescripto.Columns.Add("cep", typeof(string));
        listaADMDescripto.Columns.Add("num_residencial", typeof(string));



        DataView listaADMCripto = (DataView)sqlADMDesativar.Select(DataSourceSelectArguments.Empty);
        //Pesquisando com filtro
        listaADMDescripto.DefaultView.RowFilter = "nome like'" + txtNome.Text + "%'";

        //dando valor as colunas criadas
        for (int i = 0; i < listaADMCripto.Table.Rows.Count; i++)
        {
            DataRow linha = listaADMDescripto.NewRow();
            linha["nome"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["nome"].ToString());
            linha["foto"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["foto"].ToString());
            linha["sexo"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["sexo"].ToString());
            linha["data_nasc"] = listaADMCripto.Table.Rows[i]["data_nasc"].ToString();
            linha["data_cadastro"] = listaADMCripto.Table.Rows[i]["data_cadastro"].ToString();
            linha["tel"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["tel"].ToString());
            linha["email"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["email"].ToString());
            linha["cpf"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["cpf"].ToString());
            linha["cep"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["cep"].ToString());
            linha["num_residencial"] = cripto.Decrypt(listaADMCripto.Table.Rows[i]["num_residencial"].ToString());



            listaADMDescripto.Rows.Add(linha);

        }
        gvExibir.DataSource = listaADMDescripto;
        gvExibir.DataBind();
        gvExibir.Visible = true;

        gvExibirProc.Visible = false;
        gvExibirTrabalhador.Visible = false;
    }
    #endregion

    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }

    protected void btnPesq_Click(object sender, EventArgs e)
    {
        if (ddlTipoAcao.SelectedIndex == 0) //Pesquisar contas Destivas
        {
            if (ddlTipoUsu.SelectedIndex == 0) //ADM
            {
                CarregarADMNome();
            }
            else
            {
                if (ddlTipoUsu.SelectedIndex == 1) // PROC
                {
                    CarregarProcNome();
                }
                else //TRAB
                {
                    CarregarTrabNome();
                }
            }
        }
        else //Pesquisar contas Ativas
        {
            if (ddlTipoUsu.SelectedIndex == 0) //ADM
            {
                CarregarADMNomeDesativar();
            }
            else
            {
                if (ddlTipoUsu.SelectedIndex == 1) // PROC
                {
                    CarregarProcNomeDesativar();
                }
                else //TRAB
                {
                    CarregarTrabNomeDesativar();
                }
            }
        }
    }


    protected void btnPesqTodos_Click(object sender, EventArgs e)
    {
        if (ddlTipoAcao.SelectedIndex == 0) //Pesquisar contas Destivas
        {
            if (ddlTipoUsu.SelectedIndex == 0) //ADM
            {
                CarregarADMTodos();
            }
            else
            {
                if (ddlTipoUsu.SelectedIndex == 1) // PROC
                {
                    CarregarProcTodos();
                }
                else //TRAB
                {
                    CarregarTrabTodos();
                }
            }
        }
        else //Pesquisar contas Ativas
        {
            if (ddlTipoUsu.SelectedIndex == 0) //ADM
            {
                CarregarADM_DesativarTodos();
            }
            else
            {
                if (ddlTipoUsu.SelectedIndex == 1) // PROC
                {
                    CarregarProcDesativarTodos();
                }
                else //TRAB
                {
                    CarregarTrabDesativarTodos();                   
                }
            }
        }
    }

   
    protected void btnAtivar_Click(object sender, EventArgs e)
    {
        if (ddlTipoAcao.SelectedIndex == 0) //Ativar conta
        {
            if (ddlTipoUsu.SelectedIndex == 0) //ADM
            {
                foreach (GridViewRow linha in gvExibir.Rows)
                {
                    CheckBox chkAtivar;
                    chkAtivar = (CheckBox)linha.FindControl("chkAtivarADM");
                    if (chkAtivar.Checked == true)
                    {
                        DataView dv = (DataView)sqlPesqADM.Select(DataSourceSelectArguments.Empty);
                        string emailADM;
                        //Selecionando o email do administrador que terá sua conta ativada
                        Label email = (Label)gvExibir.Rows[linha.RowIndex].FindControl("Label7");
                        emailADM = email.Text;
                        sqlPesqADM.UpdateParameters["email"].DefaultValue = cripto.Encrypt(emailADM);
                        //Ativando a conta do administrador
                        sqlPesqADM.Update();
                        lblAviso.Text = "Ativado com sucesso";
                        CarregarADMTodos();
                        lblAviso.Text = "";
                    }
                }
            }
            else
            {
                if (ddlTipoUsu.SelectedIndex == 1) //PROC
                {
                    foreach (GridViewRow linha in gvExibirProc.Rows)
                    {
                        CheckBox chkAtivar;
                        chkAtivar = (CheckBox)linha.FindControl("chkAtivarProc");
                        if (chkAtivar.Checked == true)
                        {

                            DataView dv = (DataView)sqlProc.Select(DataSourceSelectArguments.Empty);
                            string emailProc;
                            //Selecionando o email do procurador que terá sua conta ativada
                            Label email = (Label)gvExibirProc.Rows[linha.RowIndex].FindControl("Label8");
                            emailProc = email.Text;
                            sqlProc.UpdateParameters["email"].DefaultValue = cripto.Encrypt(emailProc);
                            //Ativando a conta do procurador
                            sqlProc.Update();
                            lblAviso.Text = "Ativado com sucesso";
                            CarregarProcTodos();
                            lblAviso.Text = "";
                        }
                    }
                }
                else //TRAB
                {
                    foreach (GridViewRow linha in gvExibirTrabalhador.Rows)
                    {
                        CheckBox chkAtivar;
                        chkAtivar = (CheckBox)linha.FindControl("chkAtivarTrab");
                        if (chkAtivar.Checked == true)
                        {
                            DataView dv = (DataView)sqlTrab.Select(DataSourceSelectArguments.Empty);
                            string emailTrab;
                            //Selecionando o id do trabalhador que terá sua conta ativada
                            Label email = (Label)gvExibirTrabalhador.Rows[linha.RowIndex].FindControl("Label7");
                            emailTrab = email.Text;
                            sqlTrab.UpdateParameters["email"].DefaultValue = cripto.Encrypt(emailTrab);
                            //Ativando a conta do trabalhador
                            sqlTrab.Update();
                            lblAviso.Text = "Ativado com sucesso";
                            CarregarTrabTodos();
                            lblAviso.Text = "";
                        }
                    }
                }
            }       
        }
        else //Desativar Conta          
        {
            if (ddlTipoUsu.SelectedIndex == 0) //Adm
            {
                foreach (GridViewRow linha in gvExibir.Rows)
                {
                    CheckBox chkAtivar;
                    chkAtivar = (CheckBox)linha.FindControl("chkAtivarADM");
                    if (chkAtivar.Checked == true)
                    {
                        DataView dv = (DataView)sqlADMDesativar.Select(DataSourceSelectArguments.Empty);
                        string emailADM;
                        //Selecionando o email do administrador que terá sua conta ativada
                        Label email = (Label)gvExibir.Rows[linha.RowIndex].FindControl("Label7");
                        emailADM = email.Text;
                        sqlADMDesativar.UpdateParameters["email"].DefaultValue = cripto.Encrypt(emailADM);
                        //Ativando a conta do administrador
                        sqlADMDesativar.Update();
                        lblAviso.Text = "Desativado com sucesso";
                        CarregarADM_DesativarTodos();
                        lblAviso.Text = "";
                    }
                }
            }
            else
            {
                if (ddlTipoUsu.SelectedIndex == 1) //PROC
                {
                    foreach (GridViewRow linha in gvExibirProc.Rows)
                    {
                        CheckBox chkAtivar;
                        chkAtivar = (CheckBox)linha.FindControl("chkAtivarProc");
                        if (chkAtivar.Checked == true)
                        {

                            DataView dv = (DataView)sqlProcDesativar.Select(DataSourceSelectArguments.Empty);
                            string emailProc;
                            //Selecionando o email do procurador que terá sua conta ativada
                            Label email = (Label)gvExibirProc.Rows[linha.RowIndex].FindControl("Label8");
                            emailProc = email.Text;
                            sqlProcDesativar.UpdateParameters["email"].DefaultValue = cripto.Encrypt(emailProc);
                            //Ativando a conta do procurador
                            sqlProcDesativar.Update();
                            lblAviso.Text = "Desativado com sucesso";
                            CarregarProcDesativarTodos();
                            lblAviso.Text = "";
                        }
                    }
                }
                else //TRAB
                {
                    foreach (GridViewRow linha in gvExibirTrabalhador.Rows)
                    {
                        CheckBox chkAtivar;
                        chkAtivar = (CheckBox)linha.FindControl("chkAtivarTrab");
                        if (chkAtivar.Checked == true)
                        {
                            DataView dv = (DataView)sqlTrabDesativar.Select(DataSourceSelectArguments.Empty);
                            string emailTrab;
                            //Selecionando o id do trabalhador que terá sua conta ativada
                            Label email = (Label)gvExibirTrabalhador.Rows[linha.RowIndex].FindControl("Label7");
                            emailTrab = email.Text;
                            sqlTrabDesativar.UpdateParameters["email"].DefaultValue = cripto.Encrypt(emailTrab);
                            //Ativando a conta do trabalhador
                            sqlTrabDesativar.Update();
                            lblAviso.Text = "Desativado com sucesso";
                            CarregarTrabDesativarTodos();
                            lblAviso.Text = "";
                        }
                    }
                }
            }
        }
    }

    protected void ddlTipoAcao_SelectedIndexChanged(object sender, EventArgs e)
    {
      
    }

    protected void ddlTipoUsu_SelectedIndexChanged(object sender, EventArgs e)
    {
       
    }

    protected void gvExibirTrabalhador_SelectedIndexChanged(object sender, EventArgs e)
    {

    }
}
