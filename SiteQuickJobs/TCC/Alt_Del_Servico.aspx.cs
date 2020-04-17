using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class Alt_Del_Servico : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        //Verificar se o usuário está logado
          if (Session["tipoUsu"] != "PROC") 
          {
              Response.Redirect("Login.aspx");
          } 
        if (!IsPostBack)
        {
            CarregarTipoTrab();
            CarregarTipoPag();
            carregarTela();
        }

     
    }

    private void carregarTela()
    {
        double preco;
        DataView dv;
        //Pesquisar
        dv = (DataView)sqlServico.Select(DataSourceSelectArguments.Empty);
        //Exibir
        txtTitulo.Text = cripto.Decrypt(dv.Table.Rows[0]["titulo"].ToString());
        lblTIpoTrab.Text = cripto.Decrypt(dv.Table.Rows[0]["tipo"].ToString());
        txtCompl.Text = cripto.Decrypt(dv.Table.Rows[0]["complemento"].ToString());
        txtDesc.Text = cripto.Decrypt(dv.Table.Rows[0]["descricao"].ToString());
        txtObs.Text = cripto.Decrypt(dv.Table.Rows[0]["observacoes"].ToString());
        lblTipoPag.Text = cripto.Decrypt(dv.Table.Rows[0]["forma"].ToString());
        txtCep.Text = cripto.Decrypt(dv.Table.Rows[0]["cep"].ToString());
        preco = Convert.ToDouble(cripto.Decrypt(dv.Table.Rows[0]["valor"].ToString()).Replace('.', ','));
        txtValor.Text = preco.ToString("#0.00");
        txtNumRes.Text = cripto.Decrypt(dv.Table.Rows[0]["num_residencial"].ToString());
        img1.ImageUrl = cripto.Decrypt(dv.Table.Rows[0]["foto1"].ToString());
        imagem2.ImageUrl = cripto.Decrypt(dv.Table.Rows[0]["foto2"].ToString());
        //Pesquisar o Cep
        Carregarcep();
    }
    
    private void Carregarcep()
    {
        DataView dv;
        dv = (DataView)sqlServico.Select(DataSourceSelectArguments.Empty);
        try
        {
            var webService = new WSCorreios.AtendeClienteClient();
            var resposta = webService.consultaCEP(txtCep.Text);
            txtEndereco.Text = resposta.end;
            txtBairro.Text = resposta.bairro;
            txtCidade.Text = resposta.cidade;
            txtEstado.Text = resposta.uf;
        }
        catch (Exception ex)
        {
            txtCep.Text = "";
            txtCep.Text = ex.Message;


            txtBairro.Text = "";
            txtCidade.Text = "";
            txtEstado.Text = "";
        }
    }
    private void CarregarTipoTrab()
    {
        //Buscar a informação "tipo" da tabela Tipo_Trabalhador e inserir no ddl
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
        //Buscar a informação "forma" da tabela Pagamento e inserir no ddl
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

    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }

    protected void btnAlterar_Click(object sender, EventArgs e)
    {
        //Salvando novos valores
        sqlAlterar.UpdateParameters["NovoTitulo"].DefaultValue = cripto.Encrypt(txtTitulo.Text);
        sqlAlterar.UpdateParameters["NovoComplemento"].DefaultValue = cripto.Encrypt(txtCompl.Text);
        sqlAlterar.UpdateParameters["NovaDesc"].DefaultValue = cripto.Encrypt(txtDesc.Text);
        sqlAlterar.UpdateParameters["NovaObs"].DefaultValue = cripto.Encrypt(txtObs.Text);
        sqlAlterar.UpdateParameters["NovoValor"].DefaultValue = cripto.Encrypt(txtValor.Text);
        sqlAlterar.UpdateParameters["NovoNum"].DefaultValue = cripto.Encrypt(txtNumRes.Text);
        String nomeFoto1, urlFoto1;
        nomeFoto1 = foto1.FileName;
        if (nomeFoto1 != "")
        {
            foto1.SaveAs(Server.MapPath("\\img\\Perfil\\" + nomeFoto1));
            urlFoto1 = "~\\img\\Perfil\\" + nomeFoto1;
        }
        else
        {
            urlFoto1 = img1.ImageUrl;
        }
        String nomeFoto2, urlFoto2;
        nomeFoto2 = foto2.FileName;
        if (nomeFoto2 != "")
        {
            foto2.SaveAs(Server.MapPath("\\img\\Perfil\\" + nomeFoto2));
            urlFoto2 = "~\\img\\Perfil\\" + nomeFoto2;
        }
        else
        {
            urlFoto2 = imagem2.ImageUrl;
        }
        sqlAlterar.UpdateParameters["NovaFoto1"].DefaultValue = cripto.Encrypt(urlFoto1);
        sqlAlterar.UpdateParameters["NovaFoto2"].DefaultValue = cripto.Encrypt(urlFoto2);
        sqlAlterar.UpdateParameters["NovoCep"].DefaultValue = cripto.Encrypt(txtCep.Text);
        sqlAlterar.Update(); //Atualizando dados
        lblAviso.Text = "Alterado com sucesso";
        carregarTela(); //Atualizar telas
    }

    protected void btnDeletar_Click(object sender, EventArgs e)
    {
        sqlDeletar.Update();//Alterar status para INATIVO
        Response.Redirect("GerenciarServ.aspx");

    }

    protected void btnCEP_Click(object sender, EventArgs e)
    {
        Carregarcep();
    }

    protected void ddlTipoPag_SelectedIndexChanged(object sender, EventArgs e)
    {

    }
}