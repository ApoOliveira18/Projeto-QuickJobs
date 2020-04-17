using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class DescContrato : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["tipoUsu"] == "TRAB" || Session["tipoUsu"] == "PROC" || Session["tipoUsu"] == "ADM")
        {
            double preco;
            DataView dv;
            dv = (DataView)sqlContrato.Select(DataSourceSelectArguments.Empty);

            lblTitulo.Text = cripto.Decrypt(dv.Table.Rows[0]["titulo"].ToString());
            lblTipoTrab.Text = cripto.Decrypt(dv.Table.Rows[0]["tipo"].ToString());
            txtCompl.Text = cripto.Decrypt(dv.Table.Rows[0]["complemento"].ToString());
            txtDesc.Text = cripto.Decrypt(dv.Table.Rows[0]["descricao"].ToString());
            txtObs.Text = cripto.Decrypt(dv.Table.Rows[0]["observacoes"].ToString());
            lblTipoPag.Text = cripto.Decrypt(dv.Table.Rows[0]["forma"].ToString());
            lblCep.Text = cripto.Decrypt(dv.Table.Rows[0]["cep"].ToString());

            preco = Convert.ToDouble(cripto.Decrypt(dv.Table.Rows[0]["valor"].ToString()).Replace('.', ','));
            lblValor.Text = preco.ToString("#0.00");

            lblNomeProc.Text = cripto.Decrypt(dv.Table.Rows[0]["nomeProc"].ToString());
            lblNum.Text = cripto.Decrypt(dv.Table.Rows[0]["num_residencial"].ToString());
            img1.ImageUrl = cripto.Decrypt(dv.Table.Rows[0]["foto1"].ToString());
            img2.ImageUrl = cripto.Decrypt(dv.Table.Rows[0]["foto2"].ToString());
            lblNomeTrab.Text = cripto.Decrypt(dv.Table.Rows[0]["nomeTrab"].ToString());
            lblDataReg.Text = dv.Table.Rows[0]["data_registro"].ToString();
            Carregarcep();

        }
        else
        {
            Response.Redirect("Login.aspx");
        }
      
    }

    private void Carregarcep()
    {
        DataView dv;
        dv = (DataView)sqlContrato.Select(DataSourceSelectArguments.Empty);
        try
        {
            var webService = new WSCorreios.AtendeClienteClient();
            var resposta = webService.consultaCEP(cripto.Decrypt(dv.Table.Rows[0]["cep"].ToString()));
            lblEnd.Text = resposta.end;
            lblBairro.Text = resposta.bairro;
            lblCid.Text = resposta.cidade;
            lblUF.Text = resposta.uf;
        }
        catch (Exception ex)
        {
            lblCep.Text = "";
            lblCep.Text = ex.Message;


            lblBairro.Text = "";
            lblCid.Text = "";
            lblUF.Text = "";
        }

      
    }
    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }
}