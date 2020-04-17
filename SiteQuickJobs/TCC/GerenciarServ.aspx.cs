using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class GerenciarServ : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["tipoUsu"] != "PROC")
        {
            Response.Redirect("Login.aspx");
        }
        carregarGridServ();
    }
    private void carregarGridServ()
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

        DataView listaServCripto = (DataView)sqlServicos.Select(DataSourceSelectArguments.Empty);
        if(listaServCripto.Table.Rows.Count > 0)
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
            lblAviso.Text = "Nenhum serviço requisitado";
        }
       

    }

    protected void gvExibir_SelectedIndexChanged(object sender, EventArgs e)
    {
        DataView dv = (DataView)sqlServicos.Select(DataSourceSelectArguments.Empty);

        //Selecionar a linha e buscar o ID do serviço correspondente, em seguida salvar em uma session
        Session["CodServicoProc"] = dv.Table.Rows[gvExibir.SelectedIndex]["id_servico"].ToString();
        //Redirecionar a uma tela descritiva
        Response.Redirect("Alt_Del_Servico.aspx");
    }
    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }
}