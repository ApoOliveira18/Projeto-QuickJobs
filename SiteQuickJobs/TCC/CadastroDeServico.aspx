<%@ Page Language="C#" AutoEventWireup="true" CodeFile="CadastroDeServico.aspx.cs" Inherits="CadastroDeServico" %>

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
        .auto-style1 {
            font-size: xx-large;
        }
    </style>

     <script src="Scripts/jquery-2.1.1.min.js"></script>
     <script src="Scripts/jquery.maskedinput.min.js"></script>
     <script type="text/javascript">
         jQuery(function ($) {                 
             $("#txtCEP").mask("99999-999");          
         });
    </script>  
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
             <li class="active"><a href="MenuProcurador.aspx">Menu</a></li>   
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
        <div class ="row">

     
  
         
    <div class ="col-lg-4"> 
           <h2 class="text-white">Solicite seu pedido!</h2>
           <div class="intro-info">
             
            Titulo do serviço:
               <br />
            <asp:TextBox ID="txtTitulo" runat="server" Width="278px"></asp:TextBox>
               <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="txtTitulo" ErrorMessage="*"></asp:RequiredFieldValidator>
            <br />
            <br />
            Tipo de Competência
            <asp:DropDownList ID="ddlTipoTrab" runat="server">
            </asp:DropDownList>
            <br />
            <asp:SqlDataSource ID="sqlTipoTrab" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM tipo_trabalhador"></asp:SqlDataSource>
            <br />
            Descrição:<br />
            <asp:TextBox ID="txtDesc" runat="server" Height="66px" TextMode="MultiLine" Width="211px"></asp:TextBox>
               <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ControlToValidate="txtDesc" ErrorMessage="*"></asp:RequiredFieldValidator>
            <br />
            <br />
            Observações:<br />
            <asp:TextBox ID="txtObs" runat="server" Height="66px" TextMode="MultiLine" Width="211px"></asp:TextBox>
                  <asp:SqlDataSource ID="sqlTipoPag" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM pagamento"></asp:SqlDataSource>
            <br />
            Tipo de pagamento:<br />
            <asp:DropDownList ID="ddlTipoPag" runat="server">
            </asp:DropDownList>

            <br />
          <br />

            Valor desejado:<br />
            <asp:TextBox ID="txtValDes" runat="server" OnTextChanged="txtValDes_TextChanged"></asp:TextBox>
               <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" ControlToValidate="txtValDes" ErrorMessage="*"></asp:RequiredFieldValidator>
            <br />
            <br />
            <asp:Button ID="btnPostar" runat="server" OnClick="btnPostar_Click" Text="Postar" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" Width="107px" />
            <br />
            <asp:Label ID="lblMsg" runat="server"></asp:Label>
            <br />
            <br />
            <asp:Button ID="btnVoltar" runat="server" OnClick="btnVoltar_Click" Text="Voltar" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" Width="103px" CausesValidation="False" />
            <br />
            <br />
            <asp:SqlDataSource ID="sqlCad" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" InsertCommand="INSERT INTO servico(id_procurador, id_pagamento, id_tipo_trab, titulo, foto1, foto2, descricao, valor, observacoes, cep, num_residencial, complemento, status, data_registro) VALUES (@idProc, @idPag, @TipoTrab, @titulo, @foto1, @foto2, @desc , @valor, @obs, @cep, @num, @compl, @status, DEFAULT)" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM [servico]">
                <InsertParameters>
                    <asp:SessionParameter Name="idProc" SessionField="idProcurador" />
                    <asp:Parameter DefaultValue="" Name="foto1" />
                    <asp:Parameter Name="foto2" />
                    <asp:Parameter Name="desc" />
                    <asp:Parameter Name="valor" />
                    <asp:Parameter Name="obs" />
                    <asp:Parameter Name="data_reg" />
                    <asp:Parameter Name="cep" />
                    <asp:Parameter Name="num" />
                    <asp:Parameter Name="compl" />
                    <asp:Parameter DefaultValue="0" Name="status" />
                    <asp:ControlParameter ControlID="ddlTipoTrab" Name="TipoTrab" PropertyName="SelectedValue" />
                    <asp:ControlParameter ControlID="ddlTipoPag" Name="idPag" PropertyName="SelectedValue" />
                    <asp:Parameter Name="titulo" />
                </InsertParameters>
            </asp:SqlDataSource>
      
      
      </div>
    </div>

   <div class ="col-lg-4">
      <div class="intro-info">
          <span class="auto-style2"><span class="text-white">
          <br />
          <br /></span> </span>
           CEP do local onde será feito o serviço:
            <asp:TextBox ID="txtCEP" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" ControlToValidate="txtCEP" ErrorMessage="*"></asp:RequiredFieldValidator>
&nbsp;<asp:Button ID="btnCep" runat="server" OnClick="btnCep_Click" Text="Procurar CEP" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" CausesValidation="False" />
            <br />
            <br />
            UF:
            <asp:TextBox ID="txtUF" runat="server" CssClass="auto-style2" Enabled="False" Width="35px"></asp:TextBox>
            <br />
            <br />
            Cidade:
            <asp:TextBox ID="txtCidade" runat="server" Enabled="False"></asp:TextBox>
            <br />
            <br />
            Bairro:
            <asp:TextBox ID="txtBairro" runat="server" Enabled="False" OnTextChanged="TextBox3_TextChanged"></asp:TextBox>
            <br />
            <br />
            Endereço:
            <asp:TextBox ID="txtEndereco" runat="server" Enabled="False"></asp:TextBox>
            <br />
            <br />
            Numero residencial: <asp:TextBox ID="txtNum" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ControlToValidate="txtNum" ErrorMessage="*"></asp:RequiredFieldValidator>
            <br />
            <br />
            Complemento:<br />
            <asp:TextBox ID="txtCompl" runat="server" Height="66px" TextMode="MultiLine" Width="211px"></asp:TextBox>
      </div>
  </div>     
        
    <div class="col-lg-4">
       <div class="intro-info">
              <span class="auto-style2"><em style="color: #FFFFFF"><span class="auto-style1">Extra</span></em></span><br /><br />
           Foto 1<br />
            <asp:FileUpload ID="img1" runat="server" />
            <br />
            <br />
            Foto 2<br />
            <asp:FileUpload ID="img2" runat="server" />
       </div>
    </div>
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
