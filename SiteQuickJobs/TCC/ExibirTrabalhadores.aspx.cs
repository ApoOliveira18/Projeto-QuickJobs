using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

public partial class ExibirTrabalhadores : System.Web.UI.Page
{
    Criptografia cripto = new Criptografia("ETEP");
    protected void Page_Load(object sender, EventArgs e)
    {
          if (Session["tipoUsu"] != "ADM")
          {
              Response.Redirect("Login.aspx");
          }
        if (!IsPostBack)
        {
            CarregarTipoTrab();
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

    private void Carregarcep()
    {
        DataView dv;
        dv = (DataView)sqlTrab.Select(DataSourceSelectArguments.Empty);
        try
        {
            var webService = new WSCorreios.AtendeClienteClient();
            var resposta = webService.consultaCEP(txtCEP.Text);
            txtEndereço.Text = resposta.end;
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

    protected void btnAltarar_Click(object sender, EventArgs e)
    {
        DateTime dt = Convert.ToDateTime(txtData.Text);
        String dataCorreta = dt.ToString("yyyy/MM/dd");
        sqlTrab.UpdateParameters["DATA"].DefaultValue = dataCorreta;
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

        sqlTrab.UpdateParameters["FOTO"].DefaultValue = cripto.Encrypt(urlFoto1);
        sqlTrab.UpdateParameters["nome"].DefaultValue = cripto.Encrypt(txtNome.Text);
        sqlTrab.UpdateParameters["cpf"].DefaultValue = cripto.Encrypt(txtCPF.Text);
        sqlTrab.UpdateParameters["sexo"].DefaultValue = cripto.Encrypt(ddlSexo.Text);
        sqlTrab.UpdateParameters["cel"].DefaultValue = cripto.Encrypt(txtCelular.Text); 
        sqlTrab.UpdateParameters["cep"].DefaultValue = cripto.Encrypt(txtCEP.Text);
        sqlTrab.UpdateParameters["num"].DefaultValue = cripto.Encrypt(txtNumero.Text);
        sqlTrab.UpdateParameters["compl"].DefaultValue = cripto.Encrypt(txtComplemento.Text);
      
        

        sqlTrab.Update();

        lblErro.Text = "Atualizado com sucesso";
        txtEmail.Text = "";
        txtBairro.Text = "";
        txtCelular.Text = "";
        txtCidade.Text = "";
        txtComplemento.Text = "";
        txtCPF.Text = "";
        img1.ImageUrl = "";
        txtEndereço.Text = "";
        txtEstado.Text = "";
        txtNome.Text = "";
        txtNumero.Text = "";
        txtPontuacao.Text = "";
        txtCEP.Text = "";        
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
        img1.ImageUrl = "";
        txtEndereço.Text = "";
        txtEstado.Text = "";
        txtNome.Text = "";
        txtNumero.Text = "";
        txtPontuacao.Text = "";
        txtCEP.Text = "";
        txtData.Text = "";

    }

    protected void btnVoltar_Click(object sender, EventArgs e)
    {
        Response.Redirect("MenuAdministrativo.aspx");
    }

    protected void btnBuscar_Click(object sender, EventArgs e)
    {
        //Salvar o email digitado para pesquisa
        sqlTrab.SelectParameters["emailTrab"].DefaultValue = cripto.Encrypt(txtEmail.Text);
        DataView dv;
        dv = (DataView)sqlTrab.Select(DataSourceSelectArguments.Empty);
        //Preencher campos com os resultados da pesquisa
        if (dv.Table.Rows.Count > 0)
            {
                Session["id_Trabalhador"] = dv.Table.Rows[0]["id_trabalhador"].ToString();
                lblErro.Text = "";

                txtNome.Text = cripto.Decrypt(dv.Table.Rows[0]["nome"].ToString()); //
                DateTime dt = Convert.ToDateTime(dv.Table.Rows[0]["data_nasc"].ToString()); // 
                txtData.Text = dt.ToShortDateString();
                img1.ImageUrl = cripto.Decrypt(dv.Table.Rows[0]["foto"].ToString());  //
                ddlSexo.Text = cripto.Decrypt(dv.Table.Rows[0]["sexo"].ToString());
                txtCPF.Text = cripto.Decrypt(dv.Table.Rows[0]["cpf"].ToString());   //
                txtCelular.Text = cripto.Decrypt(dv.Table.Rows[0]["tel"].ToString()); //
            if (dv.Table.Rows[0]["pontuacao"].ToString() == "0")
            {
                txtPontuacao.Text = "Sem avaliação";
                Session["pontProc"] = "0";
            }
            else
            {
                txtPontuacao.Text = dv.Table.Rows[0]["pontuacao"].ToString();
            }
            txtCEP.Text = cripto.Decrypt(dv.Table.Rows[0]["cep"].ToString()); // 
                txtNumero.Text = cripto.Decrypt(dv.Table.Rows[0]["num_residencial"].ToString()); // 
                txtComplemento.Text = cripto.Decrypt(dv.Table.Rows[0]["complemento"].ToString()); // 
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
            img1.ImageUrl = "";
            txtEndereço.Text = "";
            txtEstado.Text = "";
            txtNome.Text = "";
            txtNumero.Text = "";
            txtPontuacao.Text = "";
            txtCEP.Text = "";
            txtData.Text = "";


        }



    }

    protected void btnLogoff_Click(object sender, EventArgs e)
    {
        Session["tipoUsu"] = "";
        Response.Redirect("Default.aspx");
    }


    protected void btnCEP_Click(object sender, EventArgs e)
    {
        DataView dv;
        dv = (DataView)sqlTrab.Select(DataSourceSelectArguments.Empty);
        try
        {
            var webService = new WSCorreios.AtendeClienteClient();
            var resposta = webService.consultaCEP(txtCEP.Text);
            txtEndereço.Text = resposta.end;
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
            txtEndereço.Text = "";
        }
    }

}
