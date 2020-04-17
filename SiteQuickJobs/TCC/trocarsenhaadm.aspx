<%@ Page Language="C#" AutoEventWireup="true" CodeFile="trocarsenhaadm.aspx.cs" Inherits="trocarsenhaadm" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
 <meta charset="utf-8">
  <title>Quick Jobs</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
  <link href="img/Logo1.ico" rel="icon">
  <link href="img/Logo1.ico" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="lib/animate/animate.min.css" rel="stylesheet">
  <link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet">
  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="css/style.css" rel="stylesheet">
    <style type="text/css">
        div {
            font-family: ARial, Helvetica, sans-serif;
            color: #FFFFFF;
        }
        center {
            width: 98%;
            float:initial;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
         <header id="header" class="fixed-top">
    <div class="container"> 
        <a href="#intro" class="scrollto"> <asp:Image ID="Image2" runat="server" Height="48px" ImageUrl="~/img/agoravai.png" Width="153px" /></a>
      <nav class="main-nav float-right d-none d-lg-block">
        <ul>
             <li class="active"><a href="Default.aspx">Página inicial</a></li>                        
        </ul>
      </nav><!-- .main-nav -->
    &nbsp;</div>
  </header><!-- #header -->
          <!--==========================
    Intro Section
  ============================-->
  <section id="intro" class="clearfix">
    <div class="container">
        <h2>Este é seu primeiro acesso, troque sua senha!</h2>
        <br />
        Nova senha: <asp:TextBox ID="txtNSenha" runat="server" TextMode="Password"></asp:TextBox>
        &nbsp;<asp:LinkButton ID="lbnkSenha" runat="server" OnClick="lbnkSenha_Click" ForeColor="White" Font-Bold="True" Font-Strikeout="False" Font-Underline="True">Verifique sua senha</asp:LinkButton>
                       <asp:TextBox ID="txtTipoSenha" runat="server" BorderColor="#0066FF" Enabled="False" Height="16px" Width="16px"></asp:TextBox>
                       <asp:Label ID="lblTipoSenha" runat="server" ToolTip="Verifique se sua senha tem no mínimo 8 letras maiúsculas e minúsculas, números e caracteres especiais" BackColor="#0066FF" ForeColor="White"></asp:Label>
                       <br />
                       <asp:Label ID="lblErroSenha" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
              <br />
        <br />
        Confirmar Nova Senha: <asp:TextBox ID="txtCSenha" runat="server" TextMode="Password"></asp:TextBox>
        <asp:CompareValidator ID="CompararSenha" runat="server" ControlToCompare="txtNSenha" ControlToValidate="txtCSenha" ErrorMessage="Senha Incompativel"></asp:CompareValidator>
        <br />
        <br />
        <br />             
           <asp:Button ID="btnTrocar" runat="server"  Text="Trocar Senha" OnClick="btnTrocar_Click2" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" />
            <asp:Label ID="lblErro" runat="server"></asp:Label>
            <asp:SqlDataSource ID="sqlAdm" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT id_adm, nome, foto, sexo, cpf, data_nasc, data_cadastro, email, senha, tel, cep, num_residencial, complemento, pergunta, resposta, primeiro, status FROM administrador WHERE (id_adm = @id)" UpdateCommand="UPDATE administrador SET senha = @SENHA, primeiro = 0 WHERE (id_adm = @id)">
                <SelectParameters>
                    <asp:SessionParameter Name="id" SessionField="idAdm" />
                </SelectParameters>
                <UpdateParameters>
                    <asp:SessionParameter Name="id" SessionField="IdAdm" />
                    <asp:Parameter Name="SENHA" />
                </UpdateParameters>
            </asp:SqlDataSource>
        </div>
      </section>

    </form>
</body>
</html>
