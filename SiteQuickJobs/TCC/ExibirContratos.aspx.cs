using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class ExibirContratos : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        //Verificar se o usuário está logado
        if (Session["tipoUsu"] == "TRAB" || Session["tipoUsu"] == "PROC")
        {
            CarregarContrato();
        }
        else
        {
            Response.Redirect("Login.aspx");
        }
       
    }
    private void CarregarContrato()
    {
        //Verificando se o usuário é do tipo Procurador
        if (Session["tipoUsu"].ToString() == "PROC")
        {
            //Criando uma tabela para o Grid View
            DataTable listaContratoDecripto = new DataTable();

            listaContratoDecripto.Columns.Add("id_contrato", typeof(int));
            listaContratoDecripto.Columns.Add("titulo", typeof(string));
            listaContratoDecripto.Columns.Add("nomeProc", typeof(string));
            listaContratoDecripto.Columns.Add("nomeTrab", typeof(string));
            listaContratoDecripto.Columns.Add("data_registro", typeof(DateTime));
            //fazendo a pesquisa no banco de dados
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
                    linha["data_registro"] = listaContratoCripto.Table.Rows[i]["data_registro"].ToString();


                    listaContratoDecripto.Rows.Add(linha);

                }
                gvContrato.DataSource = listaContratoDecripto;
                gvContrato.DataBind();
                gvContrato.Visible = true;
            }
            else
            {
                //Caso o usuário não tenha nenhum contrato
                lblErro.Text = "Você não possui nenhum contrato efetualizado";
            }
        }
        else //Executando a mesma ação caso seja o tipo de Trabalhador
        {
            //Criando uma tabela para o Grid View
            DataTable listaContratoDecripto = new DataTable();

            listaContratoDecripto.Columns.Add("id_contrato", typeof(int));
            listaContratoDecripto.Columns.Add("titulo", typeof(string));
            listaContratoDecripto.Columns.Add("nomeProc", typeof(string));
            listaContratoDecripto.Columns.Add("nomeTrab", typeof(string));
            listaContratoDecripto.Columns.Add("data_registro", typeof(DateTime));
            //fazendo a pesquisa no banco de dados
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
                    linha["data_registro"] = listaContratoCripto.Table.Rows[i]["data_registro"].ToString();
                    listaContratoDecripto.Rows.Add(linha);

                }
                gvContrato.DataSource = listaContratoDecripto;
                gvContrato.DataBind();
                gvContrato.Visible = true;
            }
            else
            {
                //Caso o usuário não tenha nenhum contrato
                lblErro.Text = "Você não possui nenhum contrato efetualizado";
            }
        }

    }

    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }

    protected void gvContrato_SelectedIndexChanged(object sender, EventArgs e)
    {
        //Verificando se o usuário é do tipo Procurador
        if (Session["tipoUsu"].ToString() == "PROC")
        {
            DataView dv = (DataView)sqlContratoProc.Select(DataSourceSelectArguments.Empty);

            //Selecionar a linha e buscar o ID do serviço correspondente, em seguida salvar em uma session
            Session["idContrato"] = dv.Table.Rows[gvContrato.SelectedIndex]["id_contrato"].ToString();
            Response.Redirect("DescContrato.aspx");
        }
        else
        {
            DataView dv = (DataView)sqlContratoTrab.Select(DataSourceSelectArguments.Empty);

            //Selecionar a linha e buscar o ID do serviço correspondente, em seguida salvar em uma session
            Session["idContrato"] = dv.Table.Rows[gvContrato.SelectedIndex]["id_contrato"].ToString();
            Response.Redirect("DescContrato.aspx");
        }
        
    }

    protected void sqlContratoProc_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
    {

    }
}