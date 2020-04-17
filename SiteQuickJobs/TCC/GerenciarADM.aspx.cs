using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class GerenciarADM : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["tipoUsu"].ToString() != "ADM")
        {
            Response.Redirect("Login.aspx");
        }
    }
    private void CarregarAdmAtivos()
    {
        Session["grid"] = "ativo";
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaAdmDescripto = new DataTable();

        listaAdmDescripto.Columns.Add("id_adm", typeof(int));
        listaAdmDescripto.Columns.Add("nome", typeof(string));
        listaAdmDescripto.Columns.Add("foto", typeof(string));
        listaAdmDescripto.Columns.Add("sexo", typeof(string));
        listaAdmDescripto.Columns.Add("email", typeof(string));  

        DataView listaAdmCripto = (DataView)sqlAdmAtivos.Select(DataSourceSelectArguments.Empty);      
        if(listaAdmCripto.Table.Rows.Count > 0)
        {
            //dando valor as colunas criadas
            for (int i = 0; i < listaAdmCripto.Table.Rows.Count; i++)
            {
                DataRow linha = listaAdmDescripto.NewRow();
                linha["id_adm"] = listaAdmCripto.Table.Rows[i]["id_adm"].ToString();
                linha["nome"] = cripto.Decrypt(listaAdmCripto.Table.Rows[i]["nome"].ToString());
                linha["foto"] = cripto.Decrypt(listaAdmCripto.Table.Rows[i]["foto"].ToString());
                linha["email"] = cripto.Decrypt(listaAdmCripto.Table.Rows[i]["email"].ToString());

                listaAdmDescripto.Rows.Add(linha);

            }
            GridView1.DataSource = listaAdmDescripto;
            GridView1.DataBind();
            GridView1.Visible = true;
        }
        else
        {
            lblAviso.Text = "Nenhum administrador ativo encontrado";
        }
    }
    private void CarregarAdmInativos()
    {
        Session["grid"] = "inativo";
        //Criando uma tabela para o Grid View Trabalhador
        DataTable listaAdmDescripto = new DataTable();

        listaAdmDescripto.Columns.Add("id_adm", typeof(int));
        listaAdmDescripto.Columns.Add("nome", typeof(string));
        listaAdmDescripto.Columns.Add("foto", typeof(string));
        listaAdmDescripto.Columns.Add("sexo", typeof(string));
        listaAdmDescripto.Columns.Add("email", typeof(string));

        DataView listaAdmCripto = (DataView)sqlAdmInativos.Select(DataSourceSelectArguments.Empty);
        if (listaAdmCripto.Table.Rows.Count > 0)
        {
            //dando valor as colunas criadas
            for (int i = 0; i < listaAdmCripto.Table.Rows.Count; i++)
            {
                DataRow linha = listaAdmDescripto.NewRow();
                linha["id_adm"] = listaAdmCripto.Table.Rows[i]["id_adm"].ToString();
                linha["nome"] = cripto.Decrypt(listaAdmCripto.Table.Rows[i]["nome"].ToString());
                linha["foto"] = cripto.Decrypt(listaAdmCripto.Table.Rows[i]["foto"].ToString());
                linha["email"] = cripto.Decrypt(listaAdmCripto.Table.Rows[i]["email"].ToString());

                listaAdmDescripto.Rows.Add(linha);

            }
            GridView1.DataSource = listaAdmDescripto;
            GridView1.DataBind();
            GridView1.Visible = true;
        }
        else
        {
            lblAviso.Text = "Nenhum administrador inativo encontrado";
        }
    }

    protected void GridView1_SelectedIndexChanged(object sender, EventArgs e)
    {
        if (Session["grid"] == "ativo")
        {
            DataView dv = (DataView)sqlAdmAtivos.Select(DataSourceSelectArguments.Empty);

            //Selecionar a linha e buscar o ID do serviço correspondente, em seguida salvar em uma session
            Session["CodAdm"] = dv.Table.Rows[GridView1.SelectedIndex]["id_adm"].ToString();           
            sqlAdmAtivos.Update();
            CarregarAdmAtivos();

        }
        else
        {
            DataView dv = (DataView)sqlAdmInativos.Select(DataSourceSelectArguments.Empty);

            //Selecionar a linha e buscar o ID do serviço correspondente, em seguida salvar em uma session
            Session["CodAdm"] = dv.Table.Rows[GridView1.SelectedIndex]["id_adm"].ToString();          
            sqlAdmInativos.Update();
            CarregarAdmInativos();
        }
       
    }
    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }

    protected void btnPesq_Click(object sender, EventArgs e)
    {
        if(ddlTipoAdm.SelectedIndex == 0)
        {
            CarregarAdmAtivos();
            lblAviso.Text = "";
        }
        else
        {
            CarregarAdmInativos();
            lblAviso.Text = "";
        }
    }
}