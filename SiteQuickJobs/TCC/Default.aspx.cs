using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        
    }

    protected void btnCadastrarUsu_Click(object sender, EventArgs e)
    {
        Response.Redirect("CadastrarProcurador.aspx");
    }

    protected void btnCadastrarTrab_Click(object sender, EventArgs e)
    {
        Response.Redirect("CadastrarTrabalhador.aspx");
    }

    protected void btnLogar_Click(object sender, EventArgs e)
    {
        Response.Redirect("Login.aspx");
    }
}