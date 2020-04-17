using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Net.Mail;
using System.Net;
using System.Web.Services;

public partial class trocarsenhaadm : System.Web.UI.Page
{
    int qtd_letras, cont, maiuscula, minuscula, numeros, especial, i;
    Criptografia cripto = new Criptografia("ETEP");

    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["tipoUsu"].ToString() != "TEMP-ADM")
        {
            Response.Redirect("Login.aspx"); 
        }

        txtTipoSenha.Visible = false;
        lblTipoSenha.Visible = false;
        lblErroSenha.Visible = false;
    }
    

    protected void btnTrocar_Click2(object sender, EventArgs e)
    {
        if(lblTipoSenha.Text != "Senha Forte")
        {
            lblErroSenha.Text = "Verifique a força da senha";
        }
        else  //Senha forte e trocada com sucesso   
        {
           
            sqlAdm.UpdateParameters["SENHA"].DefaultValue = cripto.Encrypt(txtNSenha.Text);
            sqlAdm.Update();
            lblErro.Text = "Atualizado com sucesso";
            Session["tipoUsu"] = "";
            Response.Redirect("Login.aspx");
        }
        
    }

    protected void lbnkSenha_Click(object sender, EventArgs e)
    {
        int qtdLetras;
        int qtdLetMai = 0, qtdLetMin = 0, qtdNum = 0, qtdCar = 0;

        qtdLetras = txtNSenha.Text.Length;
        try
        {
            if (qtdLetras < 8)// se tiver menos que 8 caracteres
            {


                lblErroSenha.Visible = true;
                lblErroSenha.Text = "Favor digitar outra senha com 8 caracteres no mínimo";
                txtNSenha.Text = "";
            }
            else
            {
                txtTipoSenha.Visible = true;
                lblTipoSenha.Visible = true;
                txtNSenha.Attributes.Add("value", txtNSenha.Text);
                lblTipoSenha.Text = "Senha Fraca";


                txtTipoSenha.BackColor = System.Drawing.Color.Red;

                for (int i = 0; i < qtdLetras; i++)
                {
                    if (char.IsLower(txtNSenha.Text[i]))// se tem letras minusculas
                    {
                        qtdLetMin++;
                    }
                    else
                    {
                        if (char.IsUpper(txtNSenha.Text[i]))// se tem letras maiusculas
                        {
                            qtdLetMai = qtdLetMai + 1;
                        }
                        else
                        {
                            if (char.IsNumber(txtNSenha.Text[i]))// se tem números
                            {
                                qtdNum++;
                            }
                            else
                            {
                                if (!char.IsWhiteSpace(txtNSenha.Text[i]))// se tem caracteres especiais
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

                        }
                        if (qtdLetMai != 0 && qtdNum != 0)
                        {
                            txtTipoSenha.Visible = true;
                            lblTipoSenha.Visible = true;
                            lblTipoSenha.Text = "Senha Média";
                            txtTipoSenha.BackColor = System.Drawing.Color.Yellow;

                        }
                        if (qtdLetMin != 0 && qtdNum != 0)
                        {
                            txtTipoSenha.Visible = true;
                            lblTipoSenha.Visible = true;
                            lblTipoSenha.Text = "Senha Média";
                            txtTipoSenha.BackColor = System.Drawing.Color.Yellow;

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
        }
        catch
        {

        }
    }
}