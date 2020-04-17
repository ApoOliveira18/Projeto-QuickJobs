using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class CadastrarProcurador : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        txtTipoSenha.Visible = false;
        lblTipoSenha.Visible = false;
        lblErroSenha.Visible = false;

    }
    public void cadastrar()
    {
        txtTipoSenha.Visible = true;
        txtTipoSenha.BackColor = System.Drawing.Color.Green;
        btnCadastrar.Enabled = true;

        DateTime dt = Convert.ToDateTime(txtData.Text);
        String dataCorreta = dt.ToString("yyyy/MM/dd");
        sqlCadastrarProcurador.InsertParameters["DATA"].DefaultValue = dataCorreta;  

        String nomeFoto, urlFoto;
        nomeFoto = FileUpload1.FileName;
        if (nomeFoto != "")
        {
            FileUpload1.SaveAs(Server.MapPath("\\img\\Perfil\\" + nomeFoto));
            urlFoto = "~\\img\\Perfil\\" + nomeFoto;
        }
        else
        {
            urlFoto = "~\\img\\Perfil\\sem-imagem.JPG";
        }
        sqlCadastrarProcurador.InsertParameters["FOTO"].DefaultValue = cripto.Encrypt(urlFoto);
        sqlCadastrarProcurador.InsertParameters["NOME"].DefaultValue = cripto.Encrypt(txtNome.Text);
        sqlCadastrarProcurador.InsertParameters["SEXO"].DefaultValue = cripto.Encrypt(ddlSexo.Text);
        sqlCadastrarProcurador.InsertParameters["CPF"].DefaultValue = cripto.Encrypt(txtCPF.Text);
        sqlCadastrarProcurador.InsertParameters["NUM"].DefaultValue = cripto.Encrypt(txtNumero.Text);
        sqlCadastrarProcurador.InsertParameters["COMPL"].DefaultValue = cripto.Encrypt(txtComplemento.Text);
        sqlCadastrarProcurador.InsertParameters["CEL"].DefaultValue = cripto.Encrypt(txtCelular.Text);
        sqlCadastrarProcurador.InsertParameters["EMAIL"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        sqlCadastrarProcurador.InsertParameters["SENHA"].DefaultValue = cripto.Encrypt(txtSenha.Text);
        sqlCadastrarProcurador.InsertParameters["CEP"].DefaultValue = cripto.Encrypt(txt_CEP.Text);        
        sqlCadastrarProcurador.Insert();
        lblCad.Text = "Cadastrado concluido!";
        limpar();
    }

    protected void btnCadastrar_Click(object sender, EventArgs e)
    {
        int qtdLetras;
        int qtdLetMai = 0, qtdLetMin = 0, qtdNum = 0, qtdCar = 0;

        qtdLetras = txtSenha.Text.Length;

        if (qtdLetras < 8)
        {


            lblErroSenha.Visible = true;
            lblErroSenha.Text = "Favor digitar outra senha com 8 caracteres no mínimo";
            txtSenha.Text = "";
        }
        else
        {
            txtTipoSenha.Visible = true;
            lblTipoSenha.Visible = true;
            txtSenha.Attributes.Add("value", txtSenha.Text);
            lblTipoSenha.Text = "Senha Fraca";


            txtTipoSenha.BackColor = System.Drawing.Color.Red;

            for (int i = 0; i < qtdLetras; i++)
            {
                if (char.IsLower(txtSenha.Text[i]))
                {
                    qtdLetMin++;
                }
                else
                {
                    if (char.IsUpper(txtSenha.Text[i]))
                    {
                        qtdLetMai = qtdLetMai + 1;
                    }
                    else
                    {
                        if (char.IsNumber(txtSenha.Text[i]))
                        {
                            qtdNum++;
                        }
                        else
                        {
                            if (!char.IsWhiteSpace(txtSenha.Text[i]))
                            {
                                qtdCar++;
                            }
                        }
                    }
                }
            }

            if (qtdCar != 0 &&
                qtdLetMai != 0 &&
                qtdLetMin != 0 &&
                qtdNum != 0)
            {
                txtTipoSenha.Visible = true;
                lblTipoSenha.Visible = true;
                lblTipoSenha.Text = "Senha Forte";
                txtTipoSenha.BackColor = System.Drawing.Color.Green;
                cadastrar();
            }
            else
            {
                if (qtdCar != 0)
                {
                    if (qtdLetMai != 0 && qtdLetMin != 0)
                    {
                        txtTipoSenha.Visible = true;
                        lblTipoSenha.Visible = true;
                        lblTipoSenha.Text = "Senha Média";
                        txtTipoSenha.BackColor = System.Drawing.Color.Yellow;
                        cadastrar();
                    }
                    if (qtdLetMai != 0 && qtdNum != 0)
                    {
                        txtTipoSenha.Visible = true;
                        lblTipoSenha.Visible = true;
                        lblTipoSenha.Text = "Senha Média";
                        txtTipoSenha.BackColor = System.Drawing.Color.Yellow;
                        cadastrar();
                    }
                    if (qtdLetMin != 0 && qtdNum != 0)
                    {
                        txtTipoSenha.Visible = true;
                        lblTipoSenha.Visible = true;
                        lblTipoSenha.Text = "Senha Média";
                        txtTipoSenha.BackColor = System.Drawing.Color.Yellow;
                        cadastrar();
                    }
                }
                else
                {
                    if (qtdLetMai != 0)
                    {
                        if (qtdLetMin != 0 && qtdNum != 0)
                        {
                            txtTipoSenha.Visible = true;
                            lblTipoSenha.Visible = true;
                            lblTipoSenha.Text = "Senha Média";
                            txtTipoSenha.BackColor = System.Drawing.Color.Yellow;
                            cadastrar();
                        }
                    }
                }
            }

        }
    }

    protected void btnVoltar_Click(object sender, EventArgs e)
    {
        Response.Redirect("Default.aspx");
    }

    public void limpar()
    {
        txtBairro.Text = "";
        txtCelular.Text = "";
        txtCidade.Text = "";
        txtComplemento.Text = "";
        txtConfirmarSenha.Text = "";
        txtCPF.Text = "";
        txt_CEP.Text = "";
        txtData.Text = "";
        txtEmail.Text = "";
        txtEndereco.Text = "";
        txtEstado.Text = "";
        txtNome.Text = "";
        txtNumero.Text = "";       
        txtSenha.Text = "";
      
    }

    protected void btn_CEP_Click(object sender, EventArgs e)
    {
        try
        {
            var webService = new WSCorreios.AtendeClienteClient();
            var resposta = webService.consultaCEP(txt_CEP.Text);
            txtEndereco.Text = resposta.end;
            
            txtBairro.Text = resposta.bairro;
            txtCidade.Text = resposta.cidade;
            txtEstado.Text = resposta.uf;
        }
        catch (Exception ex)
        {
            txt_CEP.Text = "";
            txt_CEP.Text = ex.Message;


            txtBairro.Text = "";
            txtCidade.Text = "";
            txtEstado.Text = "";
        }
    }

    protected void sqlCadastrarProcurador_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
    {

    }

    protected void txtNome_TextChanged(object sender, EventArgs e)
    {
        lblCad.Text = "";
    }
}

