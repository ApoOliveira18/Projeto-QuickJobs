using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class CadastroDeServico : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {

        if (Session["tipoUsu"] != "PROC")
        {
            Response.Redirect("Login.aspx");
        }
        

        if (!IsPostBack)
        {
            CarregarTipoTrab();
            CarregarTipoPag();
        }
    }

    private void CarregarTipoTrab()
    {
        DataView listTrab;
        listTrab = (DataView)sqlTipoTrab.Select(DataSourceSelectArguments.Empty);
        ddlTipoTrab.Items.Clear();
        for (int i = 0;
            i < listTrab.Table.Rows.Count;
            i++)
        {
            ddlTipoTrab.Items.Add(new ListItem(/*text*/cripto.Decrypt(listTrab.Table.Rows[i]["tipo"].ToString()),/*value*/listTrab.Table.Rows[i]["id_tipo_trab"].ToString()));
        }
    }
    private void CarregarTipoPag()
    {
        DataView listTipoPag;
        listTipoPag = (DataView)sqlTipoPag.Select(DataSourceSelectArguments.Empty);
        ddlTipoPag.Items.Clear();
        for (int i = 0;
            i < listTipoPag.Table.Rows.Count;
            i++)
        {
            ddlTipoPag.Items.Add(new ListItem(/*text*/cripto.Decrypt(listTipoPag.Table.Rows[i]["forma"].ToString()),/*value*/listTipoPag.Table.Rows[i]["id_pagamento"].ToString()));
        }
    }

    protected void btnCep_Click(object sender, EventArgs e)
    {
        try
        {
            var webService = new WSCorreios.AtendeClienteClient();
            var resposta = webService.consultaCEP(txtCEP.Text);
            txtEndereco.Text = resposta.end;

            txtBairro.Text = resposta.bairro;
            txtCidade.Text = resposta.cidade;
            txtUF.Text = resposta.uf;
        }
        catch (Exception ex)
        {
            txtCEP.Text = "";
            txtCEP.Text = ex.Message;


            txtBairro.Text = "";
            txtCidade.Text = "";
            txtUF.Text = "";
        }
    }
    private void limpar()
    {
        txtValDes.Text = "";
        txtUF.Text = "";
        txtTitulo.Text = "";
        txtObs.Text = "";
        txtNum.Text = "";
        txtEndereco.Text = "";
        txtDesc.Text = "";
        txtCompl.Text = "";
        txtCidade.Text = "";
        txtCEP.Text = "";
        txtBairro.Text = "";
        ddlTipoPag.SelectedIndex = 0;
        ddlTipoTrab.SelectedIndex = 0;

    }

    protected void TextBox3_TextChanged(object sender, EventArgs e)
    {

    }

    protected void btnPostar_Click(object sender, EventArgs e)
    {
        sqlCad.InsertParameters["titulo"].DefaultValue = cripto.Encrypt(txtTitulo.Text);
        sqlCad.InsertParameters["desc"].DefaultValue = cripto.Encrypt(txtDesc.Text);
        sqlCad.InsertParameters["valor"].DefaultValue = cripto.Encrypt(txtValDes.Text.ToString().Replace(".", ","));
        if(txtObs.Text == "") //Caso não tenha Observações
        {
            sqlCad.InsertParameters["obs"].DefaultValue = cripto.Encrypt("Sem observações");
        }
        else
        {
            sqlCad.InsertParameters["obs"].DefaultValue = cripto.Encrypt(txtObs.Text);
        }      
        sqlCad.InsertParameters["num"].DefaultValue = cripto.Encrypt(txtNum.Text);     
        sqlCad.InsertParameters["cep"].DefaultValue = cripto.Encrypt(txtCEP.Text);

        if (txtCompl.Text == "") //Caso não tenha complemento
        {
            sqlCad.InsertParameters["compl"].DefaultValue = cripto.Encrypt("Sem complemento");
        }
        else
        {
            sqlCad.InsertParameters["compl"].DefaultValue = cripto.Encrypt(txtCompl.Text);
        }    
        //Foto 1
        #region
        String nomeFoto1, urlFoto1;
        nomeFoto1 = img1.FileName;
        if (nomeFoto1 != "")
        {
            img1.SaveAs(Server.MapPath("\\img\\Perfil\\" + nomeFoto1));
            urlFoto1 = "~\\img\\Perfil\\" + nomeFoto1;
        }
        else
        {
            urlFoto1 = "~\\img\\Perfil\\sem-imagem.JPG";
        }

        #endregion
        sqlCad.InsertParameters["foto1"].DefaultValue = cripto.Encrypt(urlFoto1);
        //Foto 2
        #region
        String nomeFoto2, urlFoto2;
        nomeFoto2 = img2.FileName;
        if (nomeFoto2 != "")
        {
            img2.SaveAs(Server.MapPath("\\img\\Perfil\\" + nomeFoto2));
            urlFoto2 = "~\\img\\Perfil\\" + nomeFoto2;
        }
        else
        {
            urlFoto2 = "~\\img\\Perfil\\sem-imagem.JPG";
        }

        #endregion
        sqlCad.InsertParameters["foto2"].DefaultValue = cripto.Encrypt(urlFoto2);
        sqlCad.Insert(); //Cadastrar dados
        lblMsg.Text = "Postado com sucesso!";
        limpar(); //Limpar objetos
    }

    protected void btnCalcular_Click(object sender, EventArgs e)
    {

    }

    protected void txtValDes_TextChanged(object sender, EventArgs e)
    {

    }

    protected void btnVoltar_Click(object sender, EventArgs e)
    {
        Response.Redirect("MenuProcurador.aspx");
    }
    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }
}