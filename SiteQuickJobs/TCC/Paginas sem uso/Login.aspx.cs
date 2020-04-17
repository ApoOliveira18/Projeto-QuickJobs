using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class Login : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["tipoUsu"] == "ADM")
        {
            Response.Redirect("MenuAdministrativo.aspx");
        }
        else
        {
            if (Session["tipoUsu"] == "TRAB")
            {
                Response.Redirect("MenuTrabalhador.aspx");
            }
            else
            {
                if(Session["tipoUsu"] == "PROC")
                {
                    Response.Redirect("MenuProcurador.aspx");
                }
            }
        }
    }

    protected void btnCadastrarProcurador_Click(object sender, EventArgs e)
    {
        Response.Redirect("CadastrarProcurador.aspx");
    }

    protected void btnAcessar_Click(object sender, EventArgs e)
    {
        //ADM
        sqlAdm.SelectParameters["email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        sqlAdm.SelectParameters["senha"].DefaultValue = cripto.Encrypt(txtSenha.Text);
        //TRAB
        sqlTrabalhador.SelectParameters["email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        sqlTrabalhador.SelectParameters["senha"].DefaultValue = cripto.Encrypt(txtSenha.Text);
        //PROC
        sqlProcurador.SelectParameters["email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        sqlProcurador.SelectParameters["senha"].DefaultValue = cripto.Encrypt(txtSenha.Text);

        DataView usuLogadoPROC;
        usuLogadoPROC = (DataView)sqlProcurador.Select(DataSourceSelectArguments.Empty);

        DataView usuLogadoTRAB;
        usuLogadoTRAB = (DataView)sqlTrabalhador.Select(DataSourceSelectArguments.Empty);

        DataView usuLogadoADM;
        usuLogadoADM = (DataView)sqlAdm.Select(DataSourceSelectArguments.Empty);


        if (usuLogadoADM.Table.Rows.Count >= 1)
        {      
            Session["tipoUsu"] = "ADM";          
            Session["nomeUsu"] = usuLogadoADM.Table.Rows[0]["nome"].ToString();
            Session["idAdm"] = usuLogadoADM.Table.Rows[0]["id_adm"].ToString();
            Response.Redirect("MenuAdministrativo.aspx");
           
        }
        else
        {
            if (ddlTipoUsu.SelectedIndex == 1)
            {
                if (usuLogadoTRAB.Table.Rows.Count >= 1)
                {               
                    Session["tipoUsu"] = "TRAB";               
                    Session["nomeUsu"] = usuLogadoTRAB.Table.Rows[0]["nome"].ToString();
                    Session["idTrabalhador"] = usuLogadoTRAB.Table.Rows[0]["id_trabalhador"].ToString();
                    Response.Redirect("MenuTrabalhador.aspx");
                    
                }
                else
                {
                    txtEmail.Text = "";
                    txtSenha.Text = "";
                    lblErro1.Text = "Usuario ou senha incorreto";
                }
            }
            else
            {
                if (usuLogadoPROC.Table.Rows.Count >= 1)
                {                   
                    Session["tipoUsu"] = "PROC";                  
                    Session["nomeUsu"] = usuLogadoPROC.Table.Rows[0]["nome"].ToString();
                    Session["idProcurador"] = usuLogadoPROC.Table.Rows[0]["id_procurador"].ToString();
                    Response.Redirect("MenuProcurador.aspx");
                  
                }
                else
                {
                    txtEmail.Text = "";
                    txtSenha.Text = "";
                    lblErro1.Text = "Usuario ou senha incorreto";
                }
            }
        }
    }

    protected void btnCadastrarTrabalhador_Click(object sender, EventArgs e)
    {
        Response.Redirect("CadastrarTrabalhador.aspx");
    }

    protected void btnRetornar_Click(object sender, EventArgs e)
    {
        
    }

    protected void sqlTrabalhador_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
    {

    }

    protected void ddlTipoUsu_SelectedIndexChanged(object sender, EventArgs e)
    {

    }
}