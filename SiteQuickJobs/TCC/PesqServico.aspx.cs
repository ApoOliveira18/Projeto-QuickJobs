using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class PesqServico : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["tipoUsu"] != "TRAB")
        {
            Response.Redirect("Login.aspx");
        }

        if (!IsPostBack)
        {
            CarregarTipoTrab();
        }
    }
    private void carregarGridNome()
    {
        //Criar tabela
        DataTable listaServDescripto = new DataTable();

        listaServDescripto.Columns.Add("id_servico", typeof(int));
        listaServDescripto.Columns.Add("titulo", typeof(string));
        listaServDescripto.Columns.Add("foto1", typeof(string));
        listaServDescripto.Columns.Add("foto2", typeof(string));       
        listaServDescripto.Columns.Add("valor", typeof(double));
        listaServDescripto.Columns.Add("forma", typeof(string));
        listaServDescripto.Columns.Add("data_registro", typeof(DateTime));
        listaServDescripto.Columns.Add("cep", typeof(string));
        listaServDescripto.Columns.Add("tipo", typeof(string));

        DataView listaServCripto = (DataView)sqlServicoNome.Select(DataSourceSelectArguments.Empty);

        listaServDescripto.DefaultView.RowFilter = "titulo like'" + txtPesqServ.Text + "%'";

        if (listaServCripto.Table.Rows.Count > 0)
        {

            for (int i = 0; i < listaServCripto.Table.Rows.Count; i++)
            {
                //Dar valor aos campos da tabela
                DataRow linha = listaServDescripto.NewRow();
                linha["titulo"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["titulo"].ToString());
                linha["foto1"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["foto1"].ToString());
                linha["foto2"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["foto2"].ToString());
                linha["valor"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["valor"].ToString()).Replace('.', ',');
                linha["forma"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["forma"].ToString());
                linha["data_registro"] = listaServCripto.Table.Rows[i]["data_registro"].ToString();
                linha["cep"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["cep"].ToString());
                linha["tipo"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["tipo"].ToString());

                listaServDescripto.Rows.Add(linha);

            }
            gvExibir.DataSource = listaServDescripto;
            gvExibir.DataBind();
            lblAviso.Text = "";
        }
        //Caso não haja serviço:
        lblAviso.Text = "Nenhum serviço encontrado";
    }

    private void carregarGridTrab()
    {
        DataTable listaServDescripto = new DataTable();

        listaServDescripto.Columns.Add("id_servico", typeof(int));
        listaServDescripto.Columns.Add("titulo", typeof(string));
        listaServDescripto.Columns.Add("foto1", typeof(string));
        listaServDescripto.Columns.Add("foto2", typeof(string));      
        listaServDescripto.Columns.Add("valor", typeof(double));
        listaServDescripto.Columns.Add("forma", typeof(string));
        listaServDescripto.Columns.Add("data_registro", typeof(DateTime));
        listaServDescripto.Columns.Add("cep", typeof(string));
        listaServDescripto.Columns.Add("tipo", typeof(string));

        DataView listaServCripto = (DataView)sqlServicoTrab.Select(DataSourceSelectArguments.Empty);
        if (listaServCripto.Table.Rows.Count > 0)
        {
            for (int i = 0; i < listaServCripto.Table.Rows.Count; i++)
            {
                DataRow linha = listaServDescripto.NewRow();
                linha["titulo"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["titulo"].ToString());
                linha["foto1"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["foto1"].ToString());
                linha["foto2"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["foto2"].ToString());
                linha["valor"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["valor"].ToString()).Replace('.', ',');
                linha["forma"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["forma"].ToString());
                linha["data_registro"] = listaServCripto.Table.Rows[i]["data_registro"].ToString();
                linha["cep"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["cep"].ToString());
                linha["tipo"] = cripto.Decrypt(listaServCripto.Table.Rows[i]["tipo"].ToString());

                listaServDescripto.Rows.Add(linha);

            }
            gvExibir.DataSource = listaServDescripto;
            gvExibir.DataBind();
            lblAviso.Text = "";
        }
        else
        {
            lblAviso.Text = "Nenhum serviço encontrado";
        }
    }

    private void CarregarTipoTrab()
    {
        DataView listTrab;
        listTrab = (DataView)sqlTipoTrab.Select(DataSourceSelectArguments.Empty);
        ddlTrab.Items.Clear();
        for (int i = 0;
            i < listTrab.Table.Rows.Count;
            i++)
        {
            ddlTrab.Items.Add(new ListItem(/*text*/cripto.Decrypt(listTrab.Table.Rows[i]["tipo"].ToString()),/*value*/listTrab.Table.Rows[i]["id_tipo_trab"].ToString()));
        }
    }
  
    protected void gvExibir_SelectedIndexChanged(object sender, EventArgs e)
    {
        DataView dv = (DataView)sqlServicoTrab.Select(DataSourceSelectArguments.Empty);  

        //Selecionar a linha e buscar o ID do serviço correspondente, em seguida salvar em uma session
        Session["CodServico"] = dv.Table.Rows[gvExibir.SelectedIndex]["id_servico"].ToString();
        //Levando para descrição de serviço
        Response.Redirect("DescServico.aspx");
    }

  

   

    protected void btnPesqSerc_Click(object sender, EventArgs e)
    {
        carregarGridNome();
    }

    protected void btnPesqTrab_Click(object sender, EventArgs e)
    {
        carregarGridTrab();
    }
    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }





    protected void sqlServicoTrab_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
    {

    }
}