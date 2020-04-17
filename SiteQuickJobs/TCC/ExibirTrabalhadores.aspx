<%@ Page Language="C#" AutoEventWireup="true" CodeFile="ExibirTrabalhadores.aspx.cs" Inherits="ExibirTrabalhadores" %>

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
        .auto-style1 {
            font-size: xx-large;
            text-align: center;
        }
        .auto-style2 {
            font-size: x-large;
        }
        .auto-style271 {
            color: #FFFFFF;
        }
    </style>

     <script src="Scripts/jquery-2.1.1.min.js"></script>
     <script src="Scripts/jquery.maskedinput.min.js"></script>
     <script type="text/javascript">
         jQuery(function ($) {
             $("#txtData").mask("99/99/9999");
             $("#txtCelular").mask("(99) 99999-9999");
             $("#txtCEP").mask("99999-999");
             $("#txtCPF").mask("999.999.999-99");
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
        <h2 class="auto-style1">Gerencie todos os trabalhadores por aqui</h2>   
        <div>
      <div class="row">
        
        <div class ="col-lg-6"> 
            
            <span class="auto-style2"><em>Pesquisa Personalizada</em></span><br />
            
            <br />
            Email:
            <asp:TextBox ID="txtEmail" runat="server" Width="300px"></asp:TextBox>
&nbsp;<asp:Button ID="btnBuscar" runat="server" Text="Buscar" OnClick="btnBuscar_Click" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" />
&nbsp;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <asp:Label ID="lblErro" runat="server" ForeColor="#CC0000"></asp:Label>
            <br />
            <asp:SqlDataSource ID="sqlTrab" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT id_trabalhador, id_tipo_pacote, nome, foto, cpf, data_nasc, data_registro, sexo, tel, email, senha, cep, num_residencial, complemento, pontuacao, status FROM trabalhador WHERE (email = @emailTrab) AND (status = 0)" UpdateCommand="UPDATE trabalhador SET id_tipo_pacote = @idPac, nome = @nome, foto = @FOTO, cpf = @cpf, data_nasc = @DATA, sexo = @sexo, tel = @cel, cep = @cep, num_residencial = @num, complemento = @compl WHERE (id_trabalhador = @id)">
                <SelectParameters>
                    <asp:Parameter Name="emailTrab" />
                </SelectParameters>
                <UpdateParameters>
                    <asp:ControlParameter ControlID="ddlPacote" Name="IdPac" PropertyName="SelectedValue" />
                    <asp:Parameter Name="nome" />
                    <asp:Parameter Name="FOTO" />
                    <asp:Parameter Name="cpf" />
                    <asp:Parameter Name="DATA" />
                    <asp:Parameter Name="sexo" />
                    <asp:Parameter Name="cel" />
                    <asp:Parameter Name="cep" />
                    <asp:Parameter Name="num" />
                    <asp:Parameter Name="compl" />
                    <asp:SessionParameter Name="id" SessionField="id_Trabalhador" />
                </UpdateParameters>
            </asp:SqlDataSource>
            <asp:SqlDataSource ID="sqlDel" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM [trabalhador]" UpdateCommand="UPDATE trabalhador SET status = 1 WHERE (id_trabalhador = @idDelTrab)">
                <UpdateParameters>
                    <asp:SessionParameter Name="idDelTrab" SessionField="id_Trabalhador" />
                </UpdateParameters>
            </asp:SqlDataSource>
            <br />
            <asp:Button ID="btnAlterar" runat="server" OnClick="btnAltarar_Click" Text="Alterar Dados" Width="300px" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" Height="50px" />
            <br />
            <br />
            <asp:Button ID="btnDeletar" runat="server" OnClick="btnDeletar_Click" Text="Apagar Dados" Width="300px" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" Height="50px" />
            <br />
            <br />
            <asp:Button ID="btnVoltar" runat="server" OnClick="btnVoltar_Click" Text="Retornar ao Administrativo" Width="300px" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" Height="50px" />
            <br />
            </div>
          <div class ="col-lg-6">
              <span class="auto-style2"><em>Informações Pessoais</em></span><br />
              <br />
              Nome<br />
              <asp:TextBox ID="txtNome" runat="server" Width="300px"></asp:TextBox>
              <br />
              <br />
              Sexo<br />
              <asp:DropDownList ID="ddlSexo" runat="server">
                  <asp:ListItem>Masculino</asp:ListItem>
                  <asp:ListItem>Feminino</asp:ListItem>
                  <asp:ListItem>Outro</asp:ListItem>
              </asp:DropDownList>
              <br />
              <br />
              Data de nascimento<br />
              <asp:TextBox ID="txtData" runat="server"></asp:TextBox>
              <br />
              <br />
              Foto de perfil<br />
              <asp:Image ID="img1" runat="server" Height="100px" Width="100px" />
              <br />
              <asp:FileUpload ID="foto1" runat="server" />
              <br />
              <br />
              CPF<br />
              <asp:TextBox ID="txtCPF" runat="server"></asp:TextBox>
              <br />
              <br />
              Celular<br />
              <asp:TextBox ID="txtCelular" runat="server"></asp:TextBox>
              <br />
              <br />
              Pontuação<br />
              <asp:TextBox ID="txtPontuacao" runat="server" Width="140px"></asp:TextBox>
              <br />
              <br />
              Pacote<br />
              <asp:DropDownList ID="ddlPacote" runat="server">
              </asp:DropDownList>
              <br />
              <br />
              <span class="auto-style2"><em class="auto-style271">Informações de Localidade</em></span><br />
              <br />
              CEP<br />
              <asp:TextBox ID="txtCEP" runat="server" Width="147px"></asp:TextBox>
              &nbsp;<asp:Button ID="btnCEP" runat="server" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" OnClick="btnCEP_Click" Text="Procurar CEP" />
              <br />
              <br />
              Estado<br />
              <asp:TextBox ID="txtEstado" runat="server" Width="50px" Enabled="False"></asp:TextBox>
              <br />
              <br />
              Cidade<br />
              <asp:TextBox ID="txtCidade" runat="server" Width="300px" Enabled="False"></asp:TextBox>
              <br />
              <br />
              Bairro<br />
              <asp:TextBox ID="txtBairro" runat="server" Width="300px" Enabled="False"></asp:TextBox>
              <br />
              <br />
              Endereço<br />
              <asp:TextBox ID="txtEndereço" runat="server" Width="300px" Enabled="False"></asp:TextBox>
              <br />
              <br />
              Número residencial<br />
              <asp:TextBox ID="txtNumero" runat="server" Width="100px"></asp:TextBox>
              <br />
              <br />
              Complemento<br />
              <asp:TextBox ID="txtComplemento" runat="server" TextMode="MultiLine" Width="200px"></asp:TextBox>
              <br />
              <asp:SqlDataSource ID="sqlPacote" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT id_tipo_pacote, tipo_pacote, valor, descricao, duracao FROM pacote"></asp:SqlDataSource>
              <br />
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
