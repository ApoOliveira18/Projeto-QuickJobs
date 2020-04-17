<%@ Page Language="C#" AutoEventWireup="true" CodeFile="DescServico.aspx.cs" Inherits="DescServico" %>
<!DOCTYPE html>
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
            <h2>
                <asp:Label ID="lblTitulo" runat="server"></asp:Label>
            </h2>
        </div>
        <div class="row">
           <div class ="col-lg-4">
                 <!-- Primeira div -->
            Procurador:
            <asp:Label ID="lblNomeProc" runat="server"></asp:Label>
            <br />
                 <br />
                 Tipo de trabalhador requisitado:
                 <asp:Label ID="lblTipoTrab" runat="server"></asp:Label>
                 <br />
            <br />
            Descrição:<br />
            <asp:TextBox ID="txtDesc" runat="server" Height="85px" TextMode="MultiLine" Width="236px" Enabled="False"></asp:TextBox>
            <br />
            <br />
            Observações:<br />
            <asp:TextBox ID="txtObs" runat="server" Height="85px" TextMode="MultiLine" Width="236px" Enabled="False"></asp:TextBox>
            <br />
            <br />
            Tipo de pagamento:
            <asp:Label ID="lblTipoPag" runat="server"></asp:Label>
            <br />
            <br />
            Valor: R$
            <asp:Label ID="lblValor" runat="server"> </asp:Label>
                 <br />
                 <br />
                 <asp:Button ID="btnAceitar" runat="server" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" OnClick="btnAceitar_Click" Text="Aceitar Serviço" />
                 &nbsp;
                 <asp:Button ID="btnVoltar" runat="server" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" OnClick="btnVoltar_Click" Text="Voltar" />
                 <br />
                 <asp:Label ID="lblAviso" runat="server"></asp:Label>
            </div>
           <div class ="col-lg-4">


               CEP:
               <asp:Label ID="lblCep" runat="server"></asp:Label>
               <br />
               <br />
               UF:&nbsp;
               <asp:Label ID="lblUF" runat="server"></asp:Label>
               <br />
               <br />
               Cidade:
               <asp:Label ID="lblCid" runat="server"></asp:Label>
               <br />
               <br />
               Bairro:
               <asp:Label ID="lblBairro" runat="server"></asp:Label>
               <br />
               <br />
               Endereço:
               <asp:Label ID="lblEnd" runat="server"></asp:Label>
               <br />
               <br />
               Numero Residencial:
               <asp:Label ID="lblNum" runat="server"></asp:Label>
               <br />
               <br />
               Complemento:<br />
            <asp:TextBox ID="txtCompl" runat="server" Height="85px" TextMode="MultiLine" Width="214px" Enabled="False"></asp:TextBox>


           </div>
            <div class ="col-lg-4">
                <h2>EXTRA</h2>
                Foto1:
                <asp:Image ID="img1" runat="server" Width="100px" />
                <br />
                <br />
                Foto2:
                <asp:Image ID="img2" runat="server" Width="100px" />
                </div>
        </div>

        </div>  
   
      <asp:SqlDataSource ID="sqlServico" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT servico.id_servico, servico.titulo, servico.foto1, servico.foto2, servico.descricao, servico.valor, servico.observacoes, servico.data_registro, servico.cep, servico.num_residencial, servico.complemento, servico.status, pagamento.forma, tipo_trabalhador.tipo, procurador.nome FROM servico INNER JOIN pagamento ON servico.id_pagamento = pagamento.id_pagamento INNER JOIN tipo_trabalhador ON servico.id_tipo_trab = tipo_trabalhador.id_tipo_trab INNER JOIN procurador ON servico.id_procurador = procurador.id_procurador WHERE (servico.id_servico = @IDservico) AND (servico.status = 0)" InsertCommand="INSERT INTO contrato(id_servico, data_registro) VALUES (@IdServico, DEFAULT)" UpdateCommand="UPDATE servico SET id_trabalhador = @idTrab WHERE (id_servico = @idServico)">
          <InsertParameters>
              <asp:SessionParameter Name="IdServico" SessionField="CodServico" />
          </InsertParameters>
          <SelectParameters>
              <asp:SessionParameter Name="IDservico" SessionField="CodServico" />
          </SelectParameters>
          <UpdateParameters>
              <asp:SessionParameter Name="idServico" SessionField="CodServico" />
              <asp:SessionParameter Name="@idTrab" SessionField="idTrabalhador" />
          </UpdateParameters>
      </asp:SqlDataSource>
   
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
