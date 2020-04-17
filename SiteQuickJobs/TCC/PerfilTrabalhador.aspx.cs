using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class PerfilTrabalhador : System.Web.UI.Page
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
            CarregarTela();
        }
    }

    private void CarregarTela() //metodo para buscar perfil
    {
        DataView dv;
        dv = (DataView)sqlTrab.Select(DataSourceSelectArguments.Empty);
        //Dando valor aos objetos para exibição
          lblNome.Text = cripto.Decrypt(dv.Table.Rows[0]["nome"].ToString());
        txtNome.Text = cripto.Decrypt(dv.Table.Rows[0]["nome"].ToString());
        DateTime dt = Convert.ToDateTime(dv.Table.Rows[0]["data_nasc"].ToString());
        txtData.Text = dt.ToShortDateString();
        Image1.ImageUrl = cripto.Decrypt(dv.Table.Rows[0]["foto"].ToString());
        ddlSexo.Text = cripto.Decrypt(dv.Table.Rows[0]["sexo"].ToString());
        txtCPF.Text = cripto.Decrypt(dv.Table.Rows[0]["cpf"].ToString());
        txtCelular.Text = cripto.Decrypt(dv.Table.Rows[0]["tel"].ToString());
        if (dv.Table.Rows[0]["pontuacao"].ToString() == "0") //Caso não tenha nenhuma pontuação
        {
            txtPontuacao.Text = "Nenhuma avaliação";
        }
        else
        {
            txtPontuacao.Text = dv.Table.Rows[0]["pontuacao"].ToString(); //
        }
        txt_CEP.Text = cripto.Decrypt(dv.Table.Rows[0]["cep"].ToString());
        txtNumero.Text = cripto.Decrypt(dv.Table.Rows[0]["num_residencial"].ToString());
        txtComplemento.Text = cripto.Decrypt(dv.Table.Rows[0]["complemento"].ToString());
        txtPacote.Text = cripto.Decrypt(dv.Table.Rows[0]["tipo_pacote"].ToString());

        Carregarcep();
    }
    private void Carregarcep()
    {
        DataView dv;
        dv = (DataView)sqlTrab.Select(DataSourceSelectArguments.Empty);
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

    

    protected void btnVoltar_Click(object sender, EventArgs e)
    {
        Response.Redirect("MenuTrabalhador.aspx");
    }

    protected void btnAltarar_Click(object sender, EventArgs e)
    {
        DateTime dt = Convert.ToDateTime(txtData.Text);
        String dataCorreta = dt.ToString("yyyy/MM/dd");
        sqlTrab.UpdateParameters["DATA"].DefaultValue = dataCorreta;
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

        sqlTrab.UpdateParameters["DATA"].DefaultValue = dataCorreta;
        sqlTrab.UpdateParameters["FOTO"].DefaultValue = cripto.Encrypt(urlFoto);
        sqlTrab.UpdateParameters["nome"].DefaultValue = cripto.Encrypt(txtNome.Text);
        sqlTrab.UpdateParameters["sexo"].DefaultValue = cripto.Encrypt(ddlSexo.Text);
        sqlTrab.UpdateParameters["tel"].DefaultValue = cripto.Encrypt(txtCelular.Text);
        sqlTrab.UpdateParameters["cep"].DefaultValue = cripto.Encrypt(txt_CEP.Text);
        sqlTrab.UpdateParameters["num"].DefaultValue = cripto.Encrypt(txtNumero.Text);
        sqlTrab.UpdateParameters["compl"].DefaultValue = cripto.Encrypt(txtComplemento.Text);
        sqlTrab.UpdateParameters["cpf"].DefaultValue = cripto.Encrypt(txtCPF.Text);


        sqlTrab.Update();

        lblErro.Text = "Atualizado com sucesso";
        CarregarTela();
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

    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }

    protected void btnCarregar_Click(object sender, EventArgs e)
    {
    
       
    }

    protected void btnPacote_Click(object sender, EventArgs e)
    {
        Response.Redirect("ComprarPacote.aspx");
    }

    protected void FileUpload1_DataBinding(object sender, EventArgs e)
    {
        String nomeFoto, urlFoto;
        nomeFoto = FileUpload1.FileName;
        if (nomeFoto != "")
        {
            FileUpload1.SaveAs(Server.MapPath("\\img\\Perfil\\" + nomeFoto));
            urlFoto = "~\\img\\Perfil\\" + nomeFoto;
            Image1.ImageUrl = urlFoto;
        }
        else
        {
            urlFoto = "~\\img\\Perfil\\sem-imagem.JPG";
        }
    }
}