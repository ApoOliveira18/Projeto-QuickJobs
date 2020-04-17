using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class _3TentAdm : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
       if (Session["tipoUsu"].ToString() != "TEMP-ADM")
        {
            Response.Redirect("Login.aspx");
        }
       //Carregar a pergunta
        DataView dv = (DataView)sqlAdm.Select(DataSourceSelectArguments.Empty);
        lblPerg.Text = cripto.Decrypt(dv.Table.Rows[0]["pergunta"].ToString());

    }

    protected void btnContinuar_Click(object sender, EventArgs e)
    {
        //Vericiar se a reposta esta correta
        sqlPergAdm.SelectParameters["resp"].DefaultValue = cripto.Encrypt(txtPerg.Text);
        sqlPergAdm.SelectParameters["perg"].DefaultValue = cripto.Encrypt(lblPerg.Text);
        DataView dv = (DataView)sqlPergAdm.Select(DataSourceSelectArguments.Empty);
        if (dv.Table.Rows.Count > 0)
        {
            //Prosseguir
            Session["tipoUsu"] = "ADM";
            Response.Redirect("MenuAdministrativo.aspx");
        }
        else
        {
            lblAviso.Text = "Resposta Incorreta";
        }

    }

    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }


    protected void sqlAdm_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
    {

    }

    protected void sqlPergAdm_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
    {

    }
}