using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class PesqContratoADM : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["tipoUsu"] != "ADM")
        {
            Response.Redirect("Login.aspx");
        }
    }

    private void CarregarProc()
    {
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaContratoDecripto = new DataTable();

        listaContratoDecripto.Columns.Add("id_contrato", typeof(int));
        listaContratoDecripto.Columns.Add("titulo", typeof(string));
        listaContratoDecripto.Columns.Add("nomeProc", typeof(string));
        listaContratoDecripto.Columns.Add("nomeTrab", typeof(string));
        listaContratoDecripto.Columns.Add("valor", typeof(string));
        listaContratoDecripto.Columns.Add("forma", typeof(string));
        listaContratoDecripto.Columns.Add("data_registro", typeof(DateTime));
        //fazendo a pesquisa no banco de dados
        sqlContratoProc.SelectParameters["email"].DefaultValue = cripto.Encrypt(txtPesqProc.Text);
        DataView listaContratoCripto = (DataView)sqlContratoProc.Select(DataSourceSelectArguments.Empty);

        if (listaContratoCripto.Table.Rows.Count > 0)
        {
            //dando valor as colunas criadas
            for (int i = 0; i < listaContratoCripto.Table.Rows.Count; i++)
            {
                DataRow linha = listaContratoDecripto.NewRow();
                linha["titulo"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["titulo"].ToString());
                linha["nomeProc"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["nomeProc"].ToString());
                linha["nomeTrab"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["nomeTrab"].ToString());
                linha["valor"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["valor"].ToString());
                linha["forma"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["forma"].ToString());
                linha["data_registro"] = listaContratoCripto.Table.Rows[i]["data_registro"].ToString();


                listaContratoDecripto.Rows.Add(linha);

            }
            gvExibir.DataSource = listaContratoDecripto;
            gvExibir.DataBind();
            gvExibir.Visible = true;
            //Anotando qual tipo de pesquisa foi feita
            Session["TipoPesq"] = "Proc";
        }
        else
        {
            //Caso o usuário não tenha nenhum contrato
            lblErroProc.Text = "Nenhum contrato encontrado";
            lblErro.Text = "";
            lblErroTrab.Text = "";
        }
    }

    private void CarregarTrab()
    {
        try
        {
            //Criando uma tabela para o Grid View Trabalhador
            DataTable listaContratoDecripto = new DataTable();

            listaContratoDecripto.Columns.Add("id_contrato", typeof(int));
            listaContratoDecripto.Columns.Add("titulo", typeof(string));
            listaContratoDecripto.Columns.Add("nomeProc", typeof(string));
            listaContratoDecripto.Columns.Add("nomeTrab", typeof(string));
            listaContratoDecripto.Columns.Add("valor", typeof(string));
            listaContratoDecripto.Columns.Add("forma", typeof(string));
            listaContratoDecripto.Columns.Add("data_registro", typeof(DateTime));
            //fazendo a pesquisa no banco de dados
            sqlContratoProc.SelectParameters["email"].DefaultValue = cripto.Encrypt(txtPesqTrab.Text);
            DataView listaContratoCripto = (DataView)sqlContratoTrab.Select(DataSourceSelectArguments.Empty);

            if (listaContratoCripto.Table.Rows.Count > 0)
            {
                //dando valor as colunas criadas
                for (int i = 0; i < listaContratoCripto.Table.Rows.Count; i++)
                {
                    DataRow linha = listaContratoDecripto.NewRow();
                    linha["titulo"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["titulo"].ToString());
                    linha["nomeProc"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["nomeProc"].ToString());
                    linha["nomeTrab"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["nomeTrab"].ToString());
                    linha["valor"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["valor"].ToString());
                    linha["forma"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["forma"].ToString());
                    linha["data_registro"] = listaContratoCripto.Table.Rows[i]["data_registro"].ToString();


                    listaContratoDecripto.Rows.Add(linha);

                }
                gvExibir.DataSource = listaContratoDecripto;
                gvExibir.DataBind();
                gvExibir.Visible = true;
                //Anotando qual tipo de pesquisa foi feita
                Session["TipoPesq"] = "Trab";
            }
            else
            {
                //Caso o usuário não tenha nenhum contrato
                lblErroTrab.Text = "Nenhum contrato encontrado";
                lblErro.Text = "";
                lblErroProc.Text = "";
            }
        }
        catch
        {

        }
    }

    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }

    private void CarregarTodos()
    {

        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaContratoDecripto = new DataTable();

        listaContratoDecripto.Columns.Add("id_contrato", typeof(int));
        listaContratoDecripto.Columns.Add("titulo", typeof(string));
        listaContratoDecripto.Columns.Add("nomeProc", typeof(string));
        listaContratoDecripto.Columns.Add("nomeTrab", typeof(string));
        listaContratoDecripto.Columns.Add("valor", typeof(string));
        listaContratoDecripto.Columns.Add("forma", typeof(string));
        listaContratoDecripto.Columns.Add("data_registro", typeof(DateTime));
        //fazendo a pesquisa no banco de dados
        DataView listaContratoCripto = (DataView)sqlContrato.Select(DataSourceSelectArguments.Empty);

        if (listaContratoCripto.Table.Rows.Count > 0)
        {
            //dando valor as colunas criadas
            for (int i = 0; i < listaContratoCripto.Table.Rows.Count; i++)
            {
                DataRow linha = listaContratoDecripto.NewRow();
                linha["titulo"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["titulo"].ToString());
                linha["nomeProc"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["nomeProc"].ToString());
                linha["nomeTrab"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["nomeTrab"].ToString());
                linha["valor"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["valor"].ToString());
                linha["forma"] = cripto.Decrypt(listaContratoCripto.Table.Rows[i]["forma"].ToString());
                linha["data_registro"] = listaContratoCripto.Table.Rows[i]["data_registro"].ToString();


                listaContratoDecripto.Rows.Add(linha);

            }
            gvExibir.DataSource = listaContratoDecripto;
            gvExibir.DataBind();
            gvExibir.Visible = true;
            //Anotando qual tipo de pesquisa foi feita
            Session["TipoPesq"] = "Todos";
        }
        else
        {
            //Caso o usuário não tenha nenhum contrato
            lblErro.Text = "Nenhum contrato encontrado";
            lblErroTrab.Text = "";
            lblErroProc.Text = "";
        }
    }

    protected void btnPesqProc_Click(object sender, EventArgs e)
    {
        CarregarProc();
    }

    protected void btnPesqTrab_Click(object sender, EventArgs e)
    {
        CarregarTrab();
    }

    protected void btnPesqTodos_Click(object sender, EventArgs e)
    {
        CarregarTodos();
    }

    protected void GridView1_SelectedIndexChanged(object sender, EventArgs e)
    {
        if (Session["TipoPesq"] == "Trab") //Caso filtro seja por Trabalhador
        {
            DataView dv = (DataView)sqlContratoTrab.Select(DataSourceSelectArguments.Empty);
            //Selecionar a linha e buscar o ID do serviço correspondente, em seguida salvar em uma session
            Session["idContrato"] = dv.Table.Rows[gvExibir.SelectedIndex]["id_contrato"].ToString();
            Response.Redirect("DescContrato.aspx");
        }
        else
        {
            if (Session["TipoPesq"] == "Proc") //Caso filtro seja por Procurador
            {
                DataView dv = (DataView)sqlContratoProc.Select(DataSourceSelectArguments.Empty);
                //Selecionar a linha e buscar o ID do serviço correspondente, em seguida salvar em uma session
                Session["idContrato"] = dv.Table.Rows[gvExibir.SelectedIndex]["id_contrato"].ToString();
                Response.Redirect("DescContrato.aspx");
            }
            else //Caso não haja filtro
            {
                DataView dv = (DataView)sqlContrato.Select(DataSourceSelectArguments.Empty);
                //Selecionar a linha e buscar o ID do serviço correspondente, em seguida salvar em uma session
                Session["idContrato"] = dv.Table.Rows[gvExibir.SelectedIndex]["id_contrato"].ToString();
                Response.Redirect("DescContrato.aspx");
            }
        }
    }
}