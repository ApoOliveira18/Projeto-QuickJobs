using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class PerfilProcurador : System.Web.UI.Page
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
            CarregarTela();
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
            txtCep.Text = "";
            txtCep.Text = ex.Message;


            txtBairro.Text = "";
            txtCidade.Text = "";
            txtEstado.Text = "";
        }
    }
   
    
   private void CarregarTela() //método para carregar as informações do usuário procurador
    {
        DataView dv;
        dv = (DataView)sqlProc.Select(DataSourceSelectArguments.Empty);
        //Pesquisar e Exibir informações nos objetos
        lblnome.Text = cripto.Decrypt(dv.Table.Rows[0]["nome"].ToString());
        txtCelular.Text = cripto.Decrypt(dv.Table.Rows[0]["tel"].ToString()); //      
        txtComplemento.Text = cripto.Decrypt(dv.Table.Rows[0]["complemento"].ToString());//
        txtCPF.Text = cripto.Decrypt(dv.Table.Rows[0]["cpf"].ToString()); //
        Image1.ImageUrl = cripto.Decrypt(dv.Table.Rows[0]["foto"].ToString()); //     
        txtCep.Text = cripto.Decrypt(dv.Table.Rows[0]["cep"].ToString()); //
        txtNome.Text = cripto.Decrypt(dv.Table.Rows[0]["nome"].ToString());//        
        txtNumero.Text = cripto.Decrypt(dv.Table.Rows[0]["num_residencial"].ToString()); //
        if (dv.Table.Rows[0]["pontuacao"].ToString() == "0")
        {
            txtPontuacao.Text = "Nenhuma avaliação";
        }
        else
        {
            txtPontuacao.Text = dv.Table.Rows[0]["pontuacao"].ToString(); //
        }
        ddlSexo.Text = cripto.Decrypt(dv.Table.Rows[0]["sexo"].ToString()); //         
        DateTime dt = Convert.ToDateTime(dv.Table.Rows[0]["data_nasc"].ToString());
        txtData.Text = dt.ToShortDateString();  // 
        Carregarcep();
    }

    protected void btnAlterar_Click(object sender, EventArgs e)
    {
        DateTime dt = Convert.ToDateTime(txtData.Text);
        String dataCorreta = dt.ToString("yyyy/MM/dd");
        sqlAlterar.UpdateParameters["data"].DefaultValue = dataCorreta;
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

        sqlAlterar.UpdateParameters["data"].DefaultValue = dataCorreta;
        sqlAlterar.UpdateParameters["FOTO"].DefaultValue = cripto.Encrypt(urlFoto);        
        sqlAlterar.UpdateParameters["nome"].DefaultValue = cripto.Encrypt(txtNome.Text);
        sqlAlterar.UpdateParameters["sexo"].DefaultValue = cripto.Encrypt(ddlSexo.Text);
        sqlAlterar.UpdateParameters["tel"].DefaultValue = cripto.Encrypt(txtCelular.Text);
        sqlAlterar.UpdateParameters["cep"].DefaultValue = cripto.Encrypt(txtCep.Text);
        sqlAlterar.UpdateParameters["num"].DefaultValue = cripto.Encrypt(txtNumero.Text);
        sqlAlterar.UpdateParameters["compl"].DefaultValue = cripto.Encrypt(txtComplemento.Text);
        sqlAlterar.UpdateParameters["cpf"].DefaultValue = cripto.Encrypt(txtCPF.Text);



        sqlAlterar.Update();

        lblErro.Text = "Atualizado com sucesso";

        CarregarTela();
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

    protected void btn_CEP_Click(object sender, EventArgs e)
    {
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

    protected void btnCarregar_Click(object sender, EventArgs e)
    {      
       
    }
}
