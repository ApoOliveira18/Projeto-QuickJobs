using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data; 

public partial class MenuProcurador : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["tipoUsu"] != "PROC")
        {
            Response.Redirect("Login.aspx");
        }
        DataView dv = (DataView)sqlProc.Select(DataSourceSelectArguments.Empty);
        lblNome.Text = cripto.Decrypt(dv.Table.Rows[0]["nome"].ToString());
    }

    protected void btnPerfil_Click(object sender, EventArgs e)
    {
        Response.Redirect("PerfilProcurador.aspx");
    }
    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }

    protected void btnServicos_Click(object sender, EventArgs e)
    {
        Response.Redirect("CadastroDeServico.aspx");
    }

    protected void btnVoltar_Click(object sender, EventArgs e)
    {

    }

    protected void btnGerenciar_Click(object sender, EventArgs e)
    {
        Response.Redirect("ExibirContratos.aspx");
    }

    protected void btnGerenciar_Click1(object sender, EventArgs e)
    {
        
    }

    protected void btnGerenciarServ_Click(object sender, EventArgs e)
    {
        Response.Redirect("GerenciarServ.aspx");
    }
}