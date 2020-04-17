﻿<%@ Page Language="C#" AutoEventWireup="true" CodeFile="MenuAdministrativo.aspx.cs" Inherits="MenuAdministrativo" %>

<!DOCTYPE html>
<script runat="server">


</script>
<html lang="en">
<head>
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

  <!-- =======================================================
    Theme Name: NewBiz
    Theme URL: https://bootstrapmade.com/newbiz-bootstrap-business-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
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
             <li class="active"><a href="MenuAdministrativo.aspx">Menu</a></li>   
          <li>   <asp:LinkButton ID="btnLogoff" runat="server" CssClass="auto-style4" OnClick="btnLogoff_Click">Sair</asp:LinkButton> </li>
        </ul>
      </nav><!-- .main-nav -->
    </div>
  </header><!-- #header -->
     <!--==========================
    Intro Section   
  ============================-->
  <section id="intro" class="clearfix">
    <div class="container">
        <h2>Seja bem vindo(a) <asp:Label ID="lblNome" runat="server"></asp:Label></h2>   
        <div class="intro-info">
            <div class="row">
        
        <div class ="col-lg-6"> 
            <br />
             <asp:Button ID="btnExibirProcuradores" runat="server" Text="Gerenciar Procuradores" class="btn-get-started" OnClick="btnExibirProcuradores_Click" Width="300px" Height="50px" />   
            <br />
            <br />
             <asp:Button ID="btnExibirTrabalhadores" runat="server" Text="Gerenciar Trabalhadores" class="btn-get-started" OnClick="btnExibirTrabalhadores_Click" Width="300px" Height="50px" />   
            <br />
            <br />
             <asp:Button ID="btnPesqContasInativas" runat="server" Text="Gerenciar contas Inativas" class="btn-get-started" OnClick="btnPesqContasInativas_Click" Width="300px" Height="50px" />   
            <br />
         
          
     </div>
                <div class="col-lg-6">
                       <br />
                       <asp:Button ID="btnPesqContratos" runat="server" Text="Pesquisar Contratos" class="btn-get-started" OnClick="btnPesqContratos_Click" Width="300px" Height="50px" />   
            <br />
            <br />
             <asp:Button ID="btnCadAdm" runat="server" Text="Cadastrar novo Administrador" class="btn-get-started" OnClick="btnCadAdm_Click" Width="300px" Height="50px" />   
            <br />
                       <br />
                <asp:Button ID="btnGerenciarADM" runat="server" Text="Gerenciar Administradores" class="btn-get-started" OnClick="btnGerenciarADM_Click" Width="300px" Height="50px" />   
                    </div>
                </div>
   
            <asp:SqlDataSource ID="sqlAdm" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT nome FROM administrador WHERE (id_adm = @id)">
                <SelectParameters>
                    <asp:SessionParameter Name="id" SessionField="idAdm" />
                </SelectParameters>
            </asp:SqlDataSource>
     
   
    </div>
        </div>
  
  </section><!-- #intro -->
  
  <main id="main">

   

  </main>




  <!-- JavaScript Libraries -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/jquery/jquery-migrate.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="lib/easing/easing.min.js"></script>
  <script src="lib/mobile-nav/mobile-nav.js"></script>
  <script src="lib/wow/wow.min.js"></script>
  <script src="lib/waypoints/waypoints.min.js"></script>
  <script src="lib/counterup/counterup.min.js"></script>
  <script src="lib/owlcarousel/owl.carousel.min.js"></script>
  <script src="lib/isotope/isotope.pkgd.min.js"></script>
  <script src="lib/lightbox/js/lightbox.min.js"></script>
  <!-- Contact Form JavaScript File -->
  <script src="contactform/contactform.js"></script>

  <!-- Template Main Javascript File -->
  <script src="js/main.js"></script>

    </form>

</body>
</html>
