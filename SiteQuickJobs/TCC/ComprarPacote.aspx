<%@ Page Language="C#" AutoEventWireup="true" CodeFile="ComprarPacote.aspx.cs" Inherits="ComprarPacote" %>

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

    <!--==========================
  Header
  ============================-->
  <header id="header" class="fixed-top">
    <div class="container"> 
        <a href="#intro" class="scrollto"> <asp:Image ID="Image2" runat="server" Height="48px" ImageUrl="~/img/agoravai.png" Width="153px" /></a>
      <nav class="main-nav float-right d-none d-lg-block">
        <ul>
             <li class="active"><a href="Default.aspx">Página inicial</a></li>  
              <li class="active"><a href="MenuTrabalhador.aspx">Menu</a></li>   
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
       
        <div>
            <h2>Adquira o pacote que mais te agradar!</h2>
        </div>
        <p>
        Escolha dentre os serviços:
        <asp:DropDownList ID="ddlPacote" runat="server" OnSelectedIndexChanged="ddlPacotes_SelectedIndexChanged" AutoPostBack="True">
        </asp:DropDownList>
&nbsp; </p>
        <p>
            <asp:Image ID="imgLogo" runat="server" Height="207px" Width="198px" />
        </p>
       <div>
             Descrição:
       
             <br />
       
            <asp:TextBox ID="txtDesc" runat="server" Enabled="False" Height="104px" TextMode="MultiLine" Width="299px"></asp:TextBox>
             <br />
       </div>
          
        &nbsp;<p>
            Valor:
            R$
            <asp:Label ID="lblVal" runat="server"></asp:Label>
        </p>
        <p>
            Duração:
            <asp:Label ID="lblDuracao" runat="server"></asp:Label>
        </p>
        <p>
            Selecione o tipo de pagamento:
            <asp:DropDownList ID="ddlPagamento" runat="server" OnSelectedIndexChanged="ddlPacotes_SelectedIndexChanged">
            </asp:DropDownList>
        </p>
        <p>
            <asp:Button ID="btnComprar" runat="server" OnClick="btnComprar_Click" Text="Comprar" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" />
        </p>
        <p>
            <asp:Label ID="lblMensagem" runat="server"></asp:Label>
        </p>
        <p>
            <asp:SqlDataSource ID="sqlPacote" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM pacote"></asp:SqlDataSource>
            <asp:SqlDataSource ID="sqlAlterar" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM [trabalhador]" UpdateCommand="UPDATE trabalhador SET id_tipo_pacote = @pacote WHERE (id_trabalhador = @id)">
                <UpdateParameters>
                    <asp:ControlParameter ControlID="ddlPacote" Name="pacote" PropertyName="SelectedValue" />
                    <asp:SessionParameter Name="id" SessionField="idTrabalhador" />
                </UpdateParameters>
            </asp:SqlDataSource>
        </p>
   
    

            <asp:SqlDataSource ID="sqlPacoteEscolhido" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT id_tipo_pacote, tipo_pacote, valor, descricao, duracao FROM pacote WHERE (id_tipo_pacote = @id)">
                <SelectParameters>
                    <asp:ControlParameter ControlID="ddlPacote" Name="id" PropertyName="SelectedValue" />
                </SelectParameters>
        </asp:SqlDataSource>
        <asp:SqlDataSource ID="sqlComprar" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" InsertCommand="INSERT INTO compra_pacote(id_trabalhador, id_pagamento, id_tipo_pacote, data_compra) VALUES (@idTrab, @idPag, @idTipoPac, @Data)" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM compra_pacote">
            <InsertParameters>
                <asp:SessionParameter Name="idTrab" SessionField="idTrabalhador" />
                <asp:ControlParameter ControlID="ddlPagamento" Name="idPag" PropertyName="SelectedValue" />
                <asp:ControlParameter ControlID="ddlPacote" Name="idTipoPac" PropertyName="SelectedValue" />
                <asp:Parameter Name="Data" />
            </InsertParameters>
        </asp:SqlDataSource>
        <asp:SqlDataSource ID="sqlPagamento" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM pagamento"></asp:SqlDataSource>
             </div>

      
      </section><!-- #intro -->
  
        
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
