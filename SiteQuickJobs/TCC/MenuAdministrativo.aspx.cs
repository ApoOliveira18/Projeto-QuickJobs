using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class MenuAdministrativo : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["tipoUsu"].ToString() != "ADM")
        {
            Response.Redirect("Login.aspx");
        }
        DataView dv = (DataView)sqlAdm.Select(DataSourceSelectArguments.Empty);
        lblNome.Text = cripto.Decrypt(dv.Table.Rows[0]["nome"].ToString());
    }

    protected void btnExibirProcuradores_Click(object sender, EventArgs e)
    {
        Response.Redirect("ExibirProcuradores.aspx");
    }

    protected void btnExibirTrabalhadores_Click(object sender, EventArgs e)
    {
        Response.Redirect("ExibirTrabalhadores.aspx");
    }

    protected void btnMeuPerfil_Click(object sender, EventArgs e)
    {

    }

    protected void btnSair_Click(object sender, EventArgs e)
    {
      
    }

    protected void btnEncerrar_Click(object sender, EventArgs e)
    {
        Response.Redirect("Default.aspx");
    }
    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }

    protected void btnPesqContasInativas_Click(object sender, EventArgs e)
    {
        Response.Redirect("PesqContas.aspx");
    }

    protected void btnPesqContratos_Click(object sender, EventArgs e)
    {
        Response.Redirect("PesqContratoADM.aspx");
    }

    protected void btnCadAdm_Click(object sender, EventArgs e)
    {
        Response.Redirect("CadastrarADM.aspx");
    }

    protected void btnGerenciarADM_Click(object sender, EventArgs e)
    {
        Response.Redirect("GerenciarADM.aspx");
    }
}