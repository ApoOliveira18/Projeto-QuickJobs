using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class CadastrarTrabalhador : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        //para começar os lbl invisiveis 
        txtTipoSenha.Visible = false;
        lblTipoSenha.Visible = false;
        lblErroSenha.Visible = false;
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
    protected void btnVoltar_Click(object sender, EventArgs e)
    {
        Response.Redirect("Default.aspx");
    }
    public void cadastrar()//metodo para simplificar no botao cadastrar
    {
        DateTime dt = Convert.ToDateTime(txtData.Text);//fazendo a converção da data de nascimento
        String dataCorreta = dt.ToString("yyyy/MM/dd");
        sqlCadastrarTrabalhador.InsertParameters["data_nasc"].DefaultValue = dataCorreta;

    
        //para cadastrar a foto 
        String nomeFoto, urlFoto;
        nomeFoto = FileUpload1.FileName;
        if (nomeFoto != "")
        {
            FileUpload1.SaveAs(Server.MapPath("\\img\\Perfil\\" + nomeFoto));
            urlFoto = "~\\img\\Perfil\\" + nomeFoto;
        }
        else
        {
            urlFoto = "~\\img\\Perfil\\sem-imagem.jpg";
        }
        //inserindo os dados para serem criptogrados
        sqlCadastrarTrabalhador.InsertParameters["foto"].DefaultValue = cripto.Encrypt(urlFoto);

        sqlCadastrarTrabalhador.InsertParameters["nome"].DefaultValue = cripto.Encrypt(txtNome.Text);
        sqlCadastrarTrabalhador.InsertParameters["sexo"].DefaultValue = cripto.Encrypt(ddlSexo.Text);
        sqlCadastrarTrabalhador.InsertParameters["cpf"].DefaultValue = cripto.Encrypt(txtCPF.Text);
        sqlCadastrarTrabalhador.InsertParameters["num"].DefaultValue = cripto.Encrypt(txtNumero.Text);
        sqlCadastrarTrabalhador.InsertParameters["compl"].DefaultValue = cripto.Encrypt(txtComplemento.Text);
        sqlCadastrarTrabalhador.InsertParameters["tel"].DefaultValue = cripto.Encrypt(txtCelular.Text);
        sqlCadastrarTrabalhador.InsertParameters["email"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        sqlCadastrarTrabalhador.InsertParameters["senha"].DefaultValue = cripto.Encrypt(txtSenha.Text);
        sqlCadastrarTrabalhador.InsertParameters["cep"].DefaultValue = cripto.Encrypt(txt_CEP.Text);
        
        sqlCadastrarTrabalhador.Insert(); // comando para inserir
        limpar(); // metodo para limpar os campos

        lblCadastrar.Text = "Cadastro concluido!";
    }


    protected void btnCadastrar_Click(object sender, EventArgs e)
    {
      

        //Para fazer a verificação antes de cadastrar
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
        else
        {
            try
            {
                //para fazer a senha segura
                int qtdLetras;
                int qtdLetMai = 0, qtdLetMin = 0, qtdNum = 0, qtdCar = 0;

                qtdLetras = txtSenha.Text.Length;

                if (qtdLetras < 8)// se tiver menos que 8 caracteres
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
                        if (char.IsLower(txtSenha.Text[i]))// se tem letras minusculas
                        {
                            qtdLetMin++;
                        }
                        else
                        {
                            if (char.IsUpper(txtSenha.Text[i]))// se tem letras maiusculas
                            {
                                qtdLetMai = qtdLetMai + 1;
                            }
                            else
                            {
                                if (char.IsNumber(txtSenha.Text[i]))// se tem números
                                {
                                    qtdNum++;
                                }
                                else
                                {
                                    if (!char.IsWhiteSpace(txtSenha.Text[i]))// se tem caracteres especiais
                                    {
                                        qtdCar++;
                                    }
                                }
                            }
                        }
                    }
                    //fazendo as cominações de senhas 
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
                                    txtSenha.Text = "";
                                }
                            }
                            else
                            {
                                txtTipoSenha.Visible = true;
                                lblTipoSenha.Visible = true;
                                lblTipoSenha.Text = "Senha Fraca";
                                txtTipoSenha.BackColor = System.Drawing.Color.Red;
                            }

                        }
                    }
                }
                lblerrodata.Text = "";
                lblerroemail.Text = "";
                lblerrocpf.Text= "";
            }

            catch 

            { }

        }
                   
          
    }

       
    

    public void limpar()//metodo para limpar
    {
        txtBairro.Text = "";
        txtCelular.Text = "";
        txtCidade.Text = "";
        txtComplemento.Text = "";
        txtConfirmarSenha.Text = "";
        txtCPF.Text = "";
        txtData.Text = "";
        txtEmail.Text = "";
        txtEndereco.Text = "";
        txtEstado.Text = "";
        txtNome.Text = "";
        txtNumero.Text = "";
        txtSenha.Text = "";
        txtDesc.Text = "";
        txt_CEP.Text = "";
    }

    protected void btn_CEP_Click(object sender, EventArgs e)
    {
        //sistemas do correios
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

    protected void txtEmail_TextChanged(object sender, EventArgs e)
    {

    }

    protected void ddlPacote_SelectedIndexChanged(object sender, EventArgs e)
    {
      
    }

    protected void btnOK_Click(object sender, EventArgs e)
    {
    }

    protected void txtEstado_TextChanged(object sender, EventArgs e)
    {

    }

    protected void txt_CEP_TextChanged(object sender, EventArgs e)
    {

    }
}