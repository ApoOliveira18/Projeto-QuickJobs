using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class MenuTrabalhador : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["tipoUsu"] != "TRAB")
        {
            Response.Redirect("Login.aspx");
        }
        DataView dv = (DataView)sqlTrab.Select(DataSourceSelectArguments.Empty);
        lblNome.Text = cripto.Decrypt(dv.Table.Rows[0]["nome"].ToString());
       
    }

    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }

    protected void btnServicos_Click(object sender, EventArgs e)
    {
        Response.Redirect("PesqServico.aspx");
    }

    protected void btnPerfil_Click(object sender, EventArgs e)
    {
        Response.Redirect("PerfilTrabalhador.aspx");
    }

    protected void btnContratos_Click(object sender, EventArgs e)
    {
        Response.Redirect("ExibirContratos.aspx");
    }
}