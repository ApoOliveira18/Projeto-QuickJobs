using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class ExibirProcuradores : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["tipoUsu"] != "ADM")
        {
            Response.Redirect("Login.aspx");
        }
    }

    private void Carregarcep()
    {
        DataView dv;
        dv = (DataView)sqlProc.Select(DataSourceSelectArguments.Empty);
        try
        {
            var webService = new WSCorreios.AtendeClienteClient();
            var resposta = webService.consultaCEP(cripto.Decrypt(dv.Table.Rows[0]["cep"].ToString()));
            txtEndereco.Text = resposta.end;
            txtBairro.Text = resposta.bairro;
            txtCidade.Text = resposta.cidade;
            txtEstado.Text = resposta.uf;
        }
        catch (Exception ex)
        {
            txtCEP.Text = "";
            txtCEP.Text = ex.Message;


            txtBairro.Text = "";
            txtCidade.Text = "";
            txtEstado.Text = "";
        }
    }
    protected void btnVoltar_Click(object sender, EventArgs e)
    {
        Response.Redirect("MenuAdministrativo.aspx");
    }

    protected void btnAlterar_Click(object sender, EventArgs e)
    {
        DateTime dt = Convert.ToDateTime(txtData.Text);
        String dataCorreta = dt.ToString("yyyy/MM/dd");
        sqlAlterar.UpdateParameters["DATA"].DefaultValue = dataCorreta;
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

        
        sqlAlterar.UpdateParameters["FOTO"].DefaultValue = cripto.Encrypt(urlFoto);
        sqlAlterar.UpdateParameters["nome"].DefaultValue = cripto.Encrypt(txtNome.Text);
        sqlAlterar.UpdateParameters["sexo"].DefaultValue = cripto.Encrypt(ddlSexo.Text);
        sqlAlterar.UpdateParameters["cpf"].DefaultValue = cripto.Encrypt(txtCPF.Text);
        sqlAlterar.UpdateParameters["tel"].DefaultValue = cripto.Encrypt(txtCelular.Text);
        sqlAlterar.UpdateParameters["cep"].DefaultValue = cripto.Encrypt(txtCEP.Text);
        sqlAlterar.UpdateParameters["num"].DefaultValue = cripto.Encrypt(txtNumero.Text);
        sqlAlterar.UpdateParameters["compl"].DefaultValue = cripto.Encrypt(txtComplemento.Text);
        sqlAlterar.UpdateParameters["pont"].DefaultValue = cripto.Encrypt(txtPontuacao.Text);
        sqlAlterar.Update();

        lblErro.Text = "Atualizado com sucesso";
        txtEmail.Text = "";
        txtBairro.Text = "";
        txtCelular.Text = "";
        txtCidade.Text = "";
        txtComplemento.Text = "";
        txtCPF.Text = "";
        Image1.ImageUrl = "";
        txtEndereco.Text = "";
        txtEstado.Text = "";
        txtNome.Text = "";
        txtNumero.Text = "";
        txtPontuacao.Text = "";
        txtCEP.Text = "";

        ddlSexo.Text = "";
        txtData.Text = "";
    }

    protected void btnDeletar_Click(object sender, EventArgs e)
    {
        sqlDel.Update();
        lblErro.Text = "Deletado com sucesso";
        txtEmail.Text = "";
        txtBairro.Text = "";
        txtCelular.Text = "";
        txtCidade.Text = "";
        txtComplemento.Text = "";
        txtCPF.Text = "";
        Image1.ImageUrl = "";
        txtEndereco.Text = "";
        txtEstado.Text = "";
        txtNome.Text = "";
        txtNumero.Text = "";
        txtPontuacao.Text = "";
        txtCEP.Text = "";
        ddlSexo.Text = "";
        txtData.Text = "";
    }

    protected void btnProcurar_Click(object sender, EventArgs e)
    {
        //Salvar o email digitado para pesquisa
        sqlProc.SelectParameters["emailProc"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        DataView dv;
        dv = (DataView)sqlProc.Select(DataSourceSelectArguments.Empty);
        //Preencher campos com os resultados da pesquisa
        if (dv.Table.Rows.Count > 0)
        {
            Session["idProcADM"] = dv.Table.Rows[0]["id_procurador"].ToString();
            lblErro.Text = "";
            txtNome.Text = cripto.Decrypt(dv.Table.Rows[0]["nome"].ToString());
            DateTime dt = Convert.ToDateTime(dv.Table.Rows[0]["data_nasc"].ToString());
            txtData.Text = dt.ToShortDateString();
            Image1.ImageUrl = cripto.Decrypt(dv.Table.Rows[0]["foto"].ToString());
            ddlSexo.Text = cripto.Decrypt(dv.Table.Rows[0]["sexo"].ToString());
            txtCPF.Text = cripto.Decrypt(dv.Table.Rows[0]["cpf"].ToString());
            txtCelular.Text = cripto.Decrypt(dv.Table.Rows[0]["tel"].ToString());
            if (dv.Table.Rows[0]["pontuacao"].ToString() == "0")
            {
                txtPontuacao.Text = "Sem avaliação";
                Session["pontProc"] = "0";
            }
            else
            {
                txtPontuacao.Text = dv.Table.Rows[0]["pontuacao"].ToString();
            }
            txtCEP.Text = cripto.Decrypt(dv.Table.Rows[0]["cep"].ToString());
            txtNumero.Text = cripto.Decrypt(dv.Table.Rows[0]["num_residencial"].ToString());
            txtComplemento.Text = cripto.Decrypt(dv.Table.Rows[0]["complemento"].ToString());
            Carregarcep();
            
        }
        else  
        {
            lblErro.Text = "Usuário não encontrado";
            txtEmail.Text = "";
            txtBairro.Text = "";
            txtCelular.Text = "";
            txtCidade.Text = "";
            txtComplemento.Text = "";
            txtCPF.Text = "";
            txtCEP.Text = "";
            Image1.ImageUrl = "";
            txtEndereco.Text = "";
            txtEstado.Text = "";
            txtNome.Text = "";
            txtNumero.Text = "";
            txtPontuacao.Text = "";
            txtCEP.Text = "";
            ddlSexo.Text = "";
            txtData.Text = "";


        }
    }

    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }

    protected void GridView1_SelectedIndexChanged(object sender, EventArgs e)
    {

    }

    protected void txtCidade_TextChanged(object sender, EventArgs e)
    {

    }

    protected void btnCEP_Click(object sender, EventArgs e)
    {
        DataView dv;
        dv = (DataView)sqlProc.Select(DataSourceSelectArguments.Empty);
        try
        {
            var webService = new WSCorreios.AtendeClienteClient();
            var resposta = webService.consultaCEP(txtCEP.Text);
            txtEndereco.Text = resposta.end;
            txtBairro.Text = resposta.bairro;
            txtCidade.Text = resposta.cidade;
            txtEstado.Text = resposta.uf;
        }
        catch (Exception ex)
        {
            txtCEP.Text = "";
            txtCEP.Text = ex.Message;


            txtBairro.Text = "";
            txtCidade.Text = "";
            txtEstado.Text = "";
        }
    }
}