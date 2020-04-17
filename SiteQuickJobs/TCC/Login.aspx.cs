using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Net;
using System.Web.Services;
using System.Net.Mail;


public partial class Login : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected static string ReCaptcha_Key = "6Ld0OLUUAAAAAIVu_g7ZM_mMYNIb5O9ZfBNiK3Pp";
    protected static string ReCaptcha_Secret = "6Ld0OLUUAAAAAFwqeY-oqiLgx-Rcsd0NU7LrfZ_i";

    public static bool ValidarEmail(String strEmail)//validaçao do email
    {
        String strModelo = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-.\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        if (System.Text.RegularExpressions.Regex.IsMatch(strEmail, strModelo))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    GoogleReCaptcha.GoogleReCaptcha ctrlGoogleReCaptcha = new GoogleReCaptcha.GoogleReCaptcha();
    protected override void CreateChildControls()
    {
        base.CreateChildControls();
        ctrlGoogleReCaptcha.PublicKey = "6Le_x2kUAAAAAL0sJaarasZVzVI7Ru_cXi7p-bLN";
        ctrlGoogleReCaptcha.PrivateKey = "6Le_x2kUAAAAACfhJI9m3r3doSiiaqaf0ESgc6I1";
        this.pnlEsconder.Controls.Add(ctrlGoogleReCaptcha);
    }

    [WebMethod]
    public static string VerifyCaptcha(string response)
    {
        string url = "https://www.google.com/recaptcha/api/siteverify?secret=" + ReCaptcha_Secret + "&response=" + response;
        return (new WebClient()).DownloadString(url);
    }

    public void verificar()
    {
        DataView Trab, Proc, Adm;

        sqlAdm.SelectParameters["email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        sqlAdm.SelectParameters["senha"].DefaultValue = cripto.Encrypt(txtSenha.Text);
        Adm = (DataView)sqlAdm.Select(DataSourceSelectArguments.Empty);

        sqlProcurador.SelectParameters["email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        sqlProcurador.SelectParameters["senha"].DefaultValue = cripto.Encrypt(txtSenha.Text);
        Proc = (DataView)sqlProcurador.Select(DataSourceSelectArguments.Empty);

        sqlTrabalhador.SelectParameters["senha"].DefaultValue = cripto.Encrypt(txtSenha.Text);
        sqlTrabalhador.SelectParameters["email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        Trab = (DataView)sqlTrabalhador.Select(DataSourceSelectArguments.Empty);

       if(Adm.Table.Rows.Count == 0)
        {
            if (ddlTipoUsu.SelectedIndex == 0) //Procurador
            {
                //da sim, eu tento aqui, só precisa arrumar a logica do login

                if (Proc.Table.Rows.Count == 0)
                {
                    lblErro1.Text = "Este Email não esta cadastrado";
                }
            }

            else //Trabalhador
            {

                if (Trab.Table.Rows.Count == 0)
                {
                    lblErro1.Text = "Este Email não esta cadastrado";
                }
            }
        }
        else
        {
           
           
        }
       
       

    }

    public void verificar2() //Login
    {
        #region
        DataView Trab, Proc, Adm;
        sqlAdm.SelectParameters["email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        sqlAdm.SelectParameters["senha"].DefaultValue = cripto.Encrypt(txtSenha.Text);
        Adm = (DataView)sqlAdm.Select(DataSourceSelectArguments.Empty);

        sqlProcurador.SelectParameters["email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        sqlProcurador.SelectParameters["senha"].DefaultValue = cripto.Encrypt(txtSenha.Text);
        Proc = (DataView)sqlProcurador.Select(DataSourceSelectArguments.Empty);

        sqlTrabalhador.SelectParameters["senha"].DefaultValue = cripto.Encrypt(txtSenha.Text);
        sqlTrabalhador.SelectParameters["email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        Trab = (DataView)sqlTrabalhador.Select(DataSourceSelectArguments.Empty);
        #endregion
        if (Adm.Table.Rows.Count > 0) //ADM encontrado
        {
            Session["nomeUsu"] = cripto.Decrypt(Adm.Table.Rows[0]["nome"].ToString());
            Session["idAdm"] = Adm.Table.Rows[0]["id_adm"].ToString();
            Session["pesq"] = cripto.Decrypt(Adm.Table.Rows[0]["pergunta"].ToString());
            double acesso = Convert.ToDouble(Adm.Table.Rows[0]["primeiro"].ToString());
            //se o usuário passa na validação do recaptcha
            if (acesso == 1) 
            {
                //Caso o administrador nunca tenha logado
                Session["tipoUsu"] = "TEMP-ADM";
                Response.Redirect("trocarsenhaadm.aspx");
            }
            else //Caso o adminstador ja tenha logado antes
            {
                Session["tipoUsu"] = "TEMP-ADM";
                Response.Redirect("3TentAdm.aspx");
            }
        }
        else
        {
            if (ddlTipoUsu.SelectedIndex == 1) //Trabalhador selecionado no DDL
            {
                if (Trab.Table.Rows.Count >= 1) //Conta encontrada 
                {
                    Session["tipoUsu"] = "TRAB";
                    Session["nomeUsu"] = cripto.Decrypt(Trab.Table.Rows[0]["nome"].ToString());
                    Session["idTrabalhador"] = Trab.Table.Rows[0]["id_trabalhador"].ToString();
                    Response.Redirect("MenuTrabalhador.aspx");
                }
                else //Conta não encontrada
                {
                    txtEmail.Text = "";      txtSenha.Text = "";
                    lblErro1.Text = "Usuario ou senha incorreto";
                }
            }
            else //Procurador selecionado no DDL
            {
                if (Proc.Table.Rows.Count >= 1) //Conta encontrada
                {
                    Session["tipoUsu"] = "PROC";
                    Session["nomeUsu"] = Proc.Table.Rows[0]["nome"].ToString();
                    Session["idProcurador"] = Proc.Table.Rows[0]["id_procurador"].ToString();
                    Response.Redirect("MenuProcurador.aspx");

                }
                else //Conta não encontrada
                {
                    txtEmail.Text = "";       txtSenha.Text = "";
                    lblErro1.Text = "Usuario ou senha incorreto";
                }
            }
        }
           
    }


    protected void Page_Load(object sender, EventArgs e)
    {     
        if (Session["tipoUsu"] == "ADM") //Verificar se algum dos Usuários ja estão logados
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
                if (Session["tipoUsu"] == "PROC")
                {
                    Response.Redirect("MenuProcurador.aspx");
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

    protected void btnAcessar_Click(object sender, EventArgs e)
    {
        try
        {
            if (ValidarEmail(txtEmail.Text) == false)
            {
                Label1.Text = "Digite um email válido.";
            }
            else
            {
                if (ctrlGoogleReCaptcha.Visible == false)//se a validação esta desabilitada 
                {
                    //se o usuário passa na validação do recaptcha

                    verificar();    //Ver se existe
                    verificar2();   //Aplicar login
                }
                else
                {
                    if (ctrlGoogleReCaptcha.Validate())//se houve click
                    {

                        verificar();  //Ver se existe
                        verificar2(); //Aplicar login

                    }
                    lblErro1.Text = "Tente novamente";
                }
            }                  
        }
        catch
        {
          
        }
    }

    protected void btnCadastrarProcurador_Click(object sender, EventArgs e)
    {
        Response.Redirect("CadastrarProcurador.aspx");

    }

    protected void btnEsqueciSenha_Click(object sender, EventArgs e)
    {
        Response.Redirect("EsqueciaSenha.aspx");
    }
}