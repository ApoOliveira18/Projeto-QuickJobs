<%@ Page Language="C#" AutoEventWireup="true" CodeFile="3TentAdm.aspx.cs" Inherits="_3TentAdm" %>

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
        </style>
</head>
<body>
    <form id="form1" runat="server">
         <!--==========================
  Header
  ============================-->
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
        <h2>Responda a pergunta de segurança para prosseguir&nbsp;</h2>
        <div>         
            <br />
            PERGUNTA:
            <asp:Label ID="lblPerg" runat="server" CssClass="text-white"></asp:Label>
            <br />
            <br />
            RESPOSTA:<asp:TextBox ID="txtPerg" runat="server"></asp:TextBox>
            <br />
            <br />
        </div>
        <p>
            <asp:Button ID="btnContinuar" runat="server" OnClick="btnContinuar_Click" Text="Continuar" BackColor="#0066FF" BorderColor="#0066FF" CssClass="text-white" ForeColor="White" />
            <asp:Label ID="lblAviso" runat="server" CssClass="text-white"></asp:Label>
            <asp:SqlDataSource ID="sqlAdm" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT id_adm, pergunta, resposta, status, primeiro FROM administrador WHERE (id_adm = @id)" OnSelecting="sqlAdm_Selecting">
                <SelectParameters>
                    <asp:SessionParameter Name="id" SessionField="idAdm" />
                </SelectParameters>
            </asp:SqlDataSource>
            <asp:SqlDataSource ID="sqlPergAdm" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT id_adm, pergunta, resposta FROM administrador WHERE (id_adm = @id) AND (pergunta = @perg) AND (resposta = @resp)" OnSelecting="sqlPergAdm_Selecting">
                <SelectParameters>
                    <asp:Parameter Name="perg" />
                    <asp:Parameter Name="resp" />
                    <asp:SessionParameter Name="id" SessionField="idAdm" />
                </SelectParameters>
            </asp:SqlDataSource>
        </p>
        <p>
            &nbsp;</p>
        </div>
      </section>
    </form>
</body>
</html>
