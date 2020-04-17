using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class ComprarPacote : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");

    protected void Page_Load(object sender, EventArgs e)
    {
          if (Session["tipoUsu"] != "TRAB")
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
        listTrab = (DataView)sqlPacote.Select(DataSourceSelectArguments.Empty);
        ddlPacote.Items.Clear();
        for (int i = 0;
            i < listTrab.Table.Rows.Count;
            i++)
        {
            ddlPacote.Items.Add(new ListItem(/*text*/cripto.Decrypt(listTrab.Table.Rows[i]["tipo_pacote"].ToString()),/*value*/listTrab.Table.Rows[i]["id_tipo_pacote"].ToString()));
        }
    }
    private void CarregarTipoPag()
    {
        DataView listTipoPag;
        listTipoPag = (DataView)sqlPagamento.Select(DataSourceSelectArguments.Empty);
        ddlPagamento.Items.Clear();
        for (int i = 0;
            i < listTipoPag.Table.Rows.Count;
            i++)
        {
            ddlPagamento.Items.Add(new ListItem(/*text*/cripto.Decrypt(listTipoPag.Table.Rows[i]["forma"].ToString()),/*value*/listTipoPag.Table.Rows[i]["id_pagamento"].ToString()));
        }
    }

    protected void ddlPacotes_SelectedIndexChanged(object sender, EventArgs e)
    {
        DataView prodEscolhido;

        prodEscolhido = (DataView)sqlPacoteEscolhido.Select(DataSourceSelectArguments.Empty);
        txtDesc.Text = cripto.Decrypt(prodEscolhido.Table.Rows[0]["descricao"].ToString());
        lblVal.Text = cripto.Decrypt(prodEscolhido.Table.Rows[0]["valor"].ToString());
        lblDuracao.Text = cripto.Decrypt(prodEscolhido.Table.Rows[0]["duracao"].ToString());
  
        if(ddlPacote.SelectedIndex == 0)
        {
            imgLogo.ImageUrl = "~\\img\\pacotes\\gratis.jpg";
        }
        else
        {
            if(ddlPacote.SelectedIndex == 1)
            {
                imgLogo.ImageUrl = "~\\img\\pacotes\\basico.jpg";
            }
            else
            {
                if(ddlPacote.SelectedIndex == 2)
                {
                    imgLogo.ImageUrl = "~\\img\\pacotes\\iniciante.jpg";
                }
                else
                {
                    if(ddlPacote.SelectedIndex == 3)
                    {
                        imgLogo.ImageUrl = "~\\img\\pacotes\\veterano.jpg";
                    }
                    else
                    {
                        imgLogo.ImageUrl = "~\\img\\pacotes\\mestre.jpg";
                    }
                }
            }
        }
    }

    protected void btnComprar_Click(object sender, EventArgs e)
    {
        DateTime dtCompra = Convert.ToDateTime(DateTime.Now.ToString());
        String dataCompraCorreta = dtCompra.ToString("yyyy/MM/dd"); //Salvando data atual
        sqlAlterar.Update();//Atualizando pacote
        sqlComprar.Insert();//Salvando compra na tabela Compra_pacote
        lblMensagem.Text = "Comprado com sucesso";
    }
    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }
}