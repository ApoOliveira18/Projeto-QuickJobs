using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Security.Cryptography;//necessario 
using System.Text;//necessario
using System.Web.Services;//necessario
using System.Net.Mail;//necessario
using System.Net;//necessario


public partial class adiministrador : System.Web.UI.Page
{

    Criptografia cripto = new Criptografia("ETEP");
    int TamanhoDaSenha = 8;
    private void ValidaTamanhoSenha()
    {

        if (TamanhoDaSenha < 8)
        {
            TamanhoDaSenha = 8;

        }

    }
    public static bool IsCpf(string cpf)//validação do cpf
    {
        int[] multiplicador1 = new int[9] { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
        int[] multiplicador2 = new int[10] { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
        string tempCpf;
        string digito;
        int soma;
        int resto;
        cpf = cpf.Trim();
        cpf = cpf.Replace(".", "").Replace("-", "");
        if (cpf.Length != 11)
            return false;
        tempCpf = cpf.Substring(0, 9);
        soma = 0;

        for (int i = 0; i < 9; i++)
            soma += int.Parse(tempCpf[i].ToString()) * multiplicador1[i];
        resto = soma % 11;
        if (resto < 2)
            resto = 0;
        else
            resto = 11 - resto;
        digito = resto.ToString();
        tempCpf = tempCpf + digito;
        soma = 0;
        for (int i = 0; i < 10; i++)
            soma += int.Parse(tempCpf[i].ToString()) * multiplicador2[i];
        resto = soma % 11;
        if (resto < 2)
            resto = 0;
        else
            resto = 11 - resto;
        digito = digito + resto.ToString();
        return cpf.EndsWith(digito);
    }
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

    public bool validaData(string sData)//validaçao da data
    {
        try
        {
            DateTime.Parse(sData);
            return true;
        }
        catch
        {
            return false;
        }
    }


    public void verificar2()
    {
        string Email;

        Email = txtEmail.Text;


        SmtpClient cliente = new SmtpClient("smtp.gmail.com");
        cliente.EnableSsl = true;
        cliente.Port = 587;
        cliente.UseDefaultCredentials = false;

        MailAddress remetente = new MailAddress("TCC5IA2K19@gmail.com");

        MailAddress destinatario = new MailAddress(Email);

        MailMessage mensagem = new MailMessage(remetente, destinatario);

        mensagem.Body = "Sua senha do site QuickJOBS é " + Session["senhaAle"];
        mensagem.Subject = "Sua senha de ADMINISTRADOR do site QuickJOBS";

        NetworkCredential credenciais = new NetworkCredential("TCC5IA2K19@gmail.com", "ejmlr2019", "");
        cliente.Credentials = credenciais;


        cliente.Send(mensagem);
        lblErrei.Text = "Cadastro feito com sucesso, sua senha foi enviada a seu email.";
        txtCEP.Text = "";
        txtCompl.Text = "";
        txtCPF.Text = "";
        txtData.Text = "";
        txtEmail.Text = "";
        txtNome.Text = "";
        txtNum.Text = "";

        ddlSexo.Text = "";
        txtTel.Text = "";




    }

    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void btnCad_Click(object sender, EventArgs e)
    {
        if (IsCpf(txtCPF.Text) == false || ValidarEmail(txtEmail.Text) == false || validaData(txtData.Text) == false)
        {
            if (IsCpf(txtCPF.Text) == false)
            {
                lblerrocpf.Text = "Favor digitar um CPF válido!";
            }
            if (ValidarEmail(txtEmail.Text) == false)
            {
                lblerroemail.Text = "Favor digitar um email válido!";
            }

            if (validaData(txtData.Text) == false)
            {
                lblerrodata.Text = "Favor digitar uma data válida!";
            }

        }
        else //cadastrar
        {
            ValidaTamanhoSenha();
            string codigoSenha = DateTime.Now.Ticks.ToString();
            try
            {
                string senha = BitConverter.ToString(new System.Security.Cryptography.SHA512CryptoServiceProvider().ComputeHash(Encoding.Default.GetBytes(codigoSenha))).Replace("-", String.Empty);
                Session["senhaAle"] = senha.Substring(0, TamanhoDaSenha);

            }
            catch
            {
                lblerro.Text = "Erro";
            }
            DateTime dt = Convert.ToDateTime(txtData.Text);
            String dataCorreta = dt.ToString("yyyy/MM/dd");
            sqlCadAdm.InsertParameters["DATANASC"].DefaultValue = dataCorreta;
            sqlCadAdm.InsertParameters["NOME"].DefaultValue = cripto.Encrypt(txtNome.Text);
            sqlCadAdm.InsertParameters["SEXO"].DefaultValue = cripto.Encrypt(ddlSexo.Text);
            sqlCadAdm.InsertParameters["CPF"].DefaultValue = cripto.Encrypt(txtCPF.Text);
            sqlCadAdm.InsertParameters["EMAIL"].DefaultValue = cripto.Encrypt(txtEmail.Text);
            sqlCadAdm.InsertParameters["TEL"].DefaultValue = cripto.Encrypt(txtTel.Text);
            sqlCadAdm.InsertParameters["CEP"].DefaultValue = cripto.Encrypt(txtCEP.Text);
            sqlCadAdm.InsertParameters["NUMRES"].DefaultValue = cripto.Encrypt(txtNum.Text);
            sqlCadAdm.InsertParameters["COMP"].DefaultValue = cripto.Encrypt(txtCompl.Text);
            sqlCadAdm.InsertParameters["PERGUNTA"].DefaultValue = cripto.Encrypt(txtPergunta.Text);
            sqlCadAdm.InsertParameters["RESPOSTA"].DefaultValue = cripto.Encrypt(txtResposta.Text);
            sqlCadAdm.InsertParameters["SENHA"].DefaultValue = cripto.Encrypt(Session["senhaAle"].ToString());
            String foto, fotoBD;
            foto = Imagem.FileName;
            if (foto != "")
            {
                Imagem.SaveAs(Server.MapPath("~\\IMG\\" + foto));
                fotoBD = "~\\IMG\\" + foto;
            }
            else
            {
                fotoBD = "~\\IMG\\sem-imagem.jpg";
                sqlCadAdm.InsertParameters["FOTO"].DefaultValue =
                    cripto.Encrypt(fotoBD);
                ////////
                sqlCadAdm.Insert();

                verificar2();
                txtCEP.Text = "";
                txtCompl.Text = "";
                txtCPF.Text = "";
                txtData.Text = "";
                txtEmail.Text = "";
                txtNome.Text = "";
                txtNum.Text = "";


                ddlSexo.Text = "";
                txtTel.Text = "";
                txtResposta.Text = "";
                txtPergunta.Text = "";

            }
        }

       
    }
    protected void sqlAdministrador_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
    {

    }

    protected void SqlDataSource1_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
    {

    }
    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }

    protected void btnCEP_Click(object sender, EventArgs e)
    {
        try
        {
            var webService = new WSCorreios.AtendeClienteClient();
            var resposta = webService.consultaCEP(txtCEP.Text);
            lblCep.Text = "CEP válido";

        }
        catch (Exception ex)
        {
            lblCep.Text = "CEP inválido";
            txtCEP.Text = "";
        }
    }
}