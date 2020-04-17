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

public partial class EsqueciaSenha : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected static string ReCaptcha_Key = "6Ld0OLUUAAAAAIVu_g7ZM_mMYNIb5O9ZfBNiK3Pp";
    protected static string ReCaptcha_Secret = "6Ld0OLUUAAAAAFwqeY-oqiLgx-Rcsd0NU7LrfZ_i";


    protected void Page_Load(object sender, EventArgs e)
    {

    }

    public int Contador
    {
        get
        {
            if (Session["Contador"] != null)
                return Convert.ToInt32(Session["Contador"]);

            return 0;

        }
        set
        {
            Session["Contador"] = value;
        }
    }
    [WebMethod]
    public static string VerifyCaptcha(string response)
    {
        string url = "https://www.google.com/recaptcha/api/siteverify?secret=" + ReCaptcha_Secret + "&response=" + response;
        return (new WebClient()).DownloadString(url);
    }
    GoogleReCaptcha.GoogleReCaptcha ctrlGoogleReCaptcha = new GoogleReCaptcha.GoogleReCaptcha();
    protected override void CreateChildControls()
    {
        base.CreateChildControls();
        ctrlGoogleReCaptcha.PublicKey = "6Le_x2kUAAAAAL0sJaarasZVzVI7Ru_cXi7p-bLN";
        ctrlGoogleReCaptcha.PrivateKey = "6Le_x2kUAAAAACfhJI9m3r3doSiiaqaf0ESgc6I1";
        this.Panel1.Controls.Add(ctrlGoogleReCaptcha);
    }
    public void verificar()
    {

        DataView TentativaTrab, TentativaProc;
        string EmailTrab, EmailProc;

        sqlTrabalhador.SelectParameters["Nome"].DefaultValue = cripto.Encrypt(txtNome.Text);
        sqlTrabalhador.SelectParameters["Email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        TentativaTrab = (DataView)sqlTrabalhador.Select(DataSourceSelectArguments.Empty);

        sqlProcurador.SelectParameters["Nome"].DefaultValue = cripto.Encrypt(txtNome.Text);
        sqlProcurador.SelectParameters["Email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        TentativaProc = (DataView)sqlProcurador.Select(DataSourceSelectArguments.Empty);

        if (TentativaTrab.Table.Rows.Count == 0 && TentativaProc.Table.Rows.Count == 0)
        {

            lblErro.Text = "Este Email não esta cadastrado";
            Contador++;

        }
       
    }
    public void verificar2()
    {
        
        DataView TentativaTrab, TentativaProc;
        string EmailTrab, EmailProc;

        sqlTrabalhador.SelectParameters["Nome"].DefaultValue = cripto.Encrypt(txtNome.Text);
        sqlTrabalhador.SelectParameters["Email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        TentativaTrab = (DataView)sqlTrabalhador.Select(DataSourceSelectArguments.Empty);

        sqlProcurador.SelectParameters["Nome"].DefaultValue = cripto.Encrypt(txtNome.Text);
        sqlProcurador.SelectParameters["Email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        TentativaProc = (DataView)sqlProcurador.Select(DataSourceSelectArguments.Empty);
        if (TentativaTrab.Table.Rows.Count > 0)
        {
            EmailTrab = txtEmail.Text;
            Session["senhaTrab"] = cripto.Decrypt(TentativaTrab.Table.Rows[0]["senha"].ToString());

            SmtpClient cliente = new SmtpClient("smtp.gmail.com");
            cliente.EnableSsl = true;
            cliente.Port = 587;
            cliente.UseDefaultCredentials = false;

            MailAddress remetente = new MailAddress("TCC5IA2K19@gmail.com");

            MailAddress destinatario = new MailAddress(EmailTrab);

            MailMessage mensagem = new MailMessage(remetente, destinatario);

            mensagem.Body = "Sua senha do site QuickJOBS é " + Session["senhaTrab"];
            mensagem.Subject = "Esqueci minha senha do site QuickJOBS";

            NetworkCredential credenciais = new NetworkCredential("TCC5IA2K19@gmail.com", "ejmlr2019", "");
            cliente.Credentials = credenciais;


            cliente.Send(mensagem);
            lblErro.Text = "Seu email foi enviado com sucesso. Aguarde contato.";
            txtEmail.Text = "";
            txtNome.Text = "";

        }

        else
        {

            if (TentativaProc.Table.Rows.Count > 0)
            {
                EmailProc = txtEmail.Text;
                Session["senhaProc"] = cripto.Decrypt(TentativaProc.Table.Rows[0]["senha"].ToString());

                SmtpClient cliente = new SmtpClient("smtp.gmail.com");
                cliente.EnableSsl = true;
                cliente.Port = 587;
                cliente.UseDefaultCredentials = false;

                MailAddress remetente = new MailAddress("TCC5IA2K19@gmail.com");

                MailAddress destinatario = new MailAddress(EmailProc);

                MailMessage mensagem = new MailMessage(remetente, destinatario);

                mensagem.Body = "Sua senha do site QuickJOBS é " + Session["senhaProc"];
                mensagem.Subject = "Esqueci minha senha do site QuickJOBS";

                NetworkCredential credenciais = new NetworkCredential("TCC5IA2K19@gmail.com", "ejmlr2019", "");
                cliente.Credentials = credenciais;


                cliente.Send(mensagem);
                lblErro.Text = "Seu email foi enviado com sucesso. Aguarde contato.";
                txtEmail.Text = "";
                txtNome.Text = "";

            }

        }
    }
    protected void btnEnviar_Click(object sender, EventArgs e)
    {           
        try
        {
            if (ctrlGoogleReCaptcha.Visible == false)
            {
                //se o usuário passa na validação do recaptcha
                verificar();
                
            }
            else
            {
                if (ctrlGoogleReCaptcha.Validate())
                {

                    verificar();
                    verificar2();

                }
                verificar2();

            }
         
            this.Contador++;

            if (Contador >= 3)
            {
                ctrlGoogleReCaptcha.Visible = true;
                Panel1.Visible = true;
                Contador = Contador - Contador;
            }

           
           
          
        }
        catch
        {
            verificar();

            Contador = Contador - Contador;
        }
      

        //else
        //{  
        //    if (TentativaTrab.Table.Rows.Count > 0)
        //    {
        //        EmailTrab = txtEmail.Text;
        //        Session["senhaTrab"] = cripto.Decrypt(TentativaTrab.Table.Rows[0]["senha"].ToString());

        //        SmtpClient cliente = new SmtpClient("smtp.gmail.com");
        //        cliente.EnableSsl = true;
        //        cliente.Port = 587;
        //        cliente.UseDefaultCredentials = false;

        //        MailAddress remetente = new MailAddress("TCC5IA2K19@gmail.com");

        //        MailAddress destinatario = new MailAddress(EmailTrab);

        //        MailMessage mensagem = new MailMessage(remetente, destinatario);

        //        mensagem.Body = "Sua senha do site QuickJOBS é " + Session["senhaTrab"];
        //        mensagem.Subject = "Esqueci minha senha do site QuickJOBS";
                
        //        NetworkCredential credenciais = new NetworkCredential("TCC5IA2K19@gmail.com", "ejmlr2019","");
        //        cliente.Credentials = credenciais;


        //        cliente.Send(mensagem);
        //        lblErro.Text = "Seu email foi enviado com sucesso. Aguarde contato.";
        //        txtEmail.Text = "";
        //        txtNome.Text = "";

        //    }

        //    else
        //    {
               
        //        if (TentativaProc.Table.Rows.Count > 0)
        //        {
        //            EmailProc = txtEmail.Text;
        //            Session["senhaProc"] = cripto.Decrypt(TentativaProc.Table.Rows[0]["senha"].ToString());

        //            SmtpClient cliente = new SmtpClient("smtp.gmail.com");
        //            cliente.EnableSsl = true;
        //            cliente.Port = 587;
        //            cliente.UseDefaultCredentials = false;

        //            MailAddress remetente = new MailAddress("TCC5IA2K19@gmail.com");

        //            MailAddress destinatario = new MailAddress(EmailProc);

        //            MailMessage mensagem = new MailMessage(remetente, destinatario);

        //            mensagem.Body = "Sua senha do site QuickJOBS é " + Session["senhaProc"];
        //            mensagem.Subject = "Esqueci minha senha do site QuickJOBS";

        //            NetworkCredential credenciais = new NetworkCredential("TCC5IA2K19@gmail.com", "ejmlr2019", "");
        //            cliente.Credentials = credenciais;


        //            cliente.Send(mensagem);
        //            lblErro.Text = "Seu email foi enviado com sucesso. Aguarde contato.";
        //            txtEmail.Text = "";
        //            txtNome.Text = "";

        //        }

        //    }
            }

    protected void btnVoltar_Click(object sender, EventArgs e)
    {
        Response.Redirect("Login.aspx");
    }

    protected void txtEmail_TextChanged(object sender, EventArgs e)
    {

    }
}