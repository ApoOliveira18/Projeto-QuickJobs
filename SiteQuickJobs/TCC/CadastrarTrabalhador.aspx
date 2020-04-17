<%@ Page Language="C#" AutoEventWireup="true" CodeFile="CadastrarTrabalhador.aspx.cs" Inherits="CadastrarTrabalhador" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
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
            color: #000000;
        }
        .auto-style2 {
            font-size: x-large;
            font-style: italic;
        }
    </style>
    <script src="Scripts/jquery-2.1.1.min.js"></script>
     <script src="Scripts/jquery.maskedinput.min.js"></script>
     <script type="text/javascript">
         jQuery(function ($) {
             $("#txtData").mask("99/99/9999");
             $("#txtCelular").mask("(99) 99999-9999");
             $("#txt_CEP").mask("99999-999");
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
           <li><a href="Login.aspx">Faça seu login</a></li>
        </ul>
      </nav><!-- .main-nav -->
    </div>
  </header><!-- #header -->
     <!--==========================
    Intro Section
  ============================-->
  <section id="intro" class="clearfix">
    <div class="container">
        <h2 class="text-white">Insira suas informações como Trabalhador</h2>   
  
      <div class="row">
   

       
        <div class ="col-lg-4"> 
                 <div class="intro-info">
                     <span class="auto-style2"><span class="text-white">Pessoais</span><br />
              </span>
            <br />
            <span class="text-white">Nome</span><br />
            <asp:TextBox ID="txtNome" runat="server" Width="300px"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="txtNome" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
            <br />
            <br />
            <span class="text-white">Sexo</span><br />
            <asp:DropDownList ID="ddlSexo" runat="server">
                <asp:ListItem>Masculino</asp:ListItem>
                <asp:ListItem>Feminino</asp:ListItem>
                <asp:ListItem>Outro</asp:ListItem>
            </asp:DropDownList>
            
            <asp:RequiredFieldValidator ID="RequiredFieldValidator17" runat="server" ControlToValidate="ddlSexo" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
            
            <br />
            <br />
            <span class="text-white">Data</span> <span class="text-white">de</span> <span class="text-white">nascimento</span><br />
            <asp:TextBox ID="txtData" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator18" runat="server" ControlToValidate="txtData" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
            <br />
                     <asp:Label ID="lblerrodata" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
            <br />
            <span class="text-white">Foto de perfil</span><br />
            <asp:Image ID="Image1" runat="server" Height="100px" Width="100px" />
            <br />
            <asp:FileUpload ID="FileUpload1" runat="server" ForeColor="White" />
                     <br />
            <br />
            <span class="text-white">CPF</span><br />
            <asp:TextBox ID="txtCPF" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator20" runat="server" ControlToValidate="txtCPF" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
                     <br />
                     <asp:Label ID="lblerrocpf" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
            <br />
            <span class="text-white">Celular</span><br />
            <asp:TextBox ID="txtCelular" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator24" runat="server" ControlToValidate="txtCelular" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
            <br />
            <br />
            <span class="text-white">Descrição</span> <span class="text-white">(Conte-nos um sobre você e suas experiências)</span><br />
            <asp:TextBox ID="txtDesc" runat="server" Height="76px" TextMode="MultiLine" Width="280px"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator32" runat="server" ControlToValidate="txtDesc" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
            <br />
            </div>
            </div>
          <div class ="col-lg-4">
                   <div class="intro-info">
             <span class="auto-style2"><span class="text-white">Localidade<br />
                       </span>
                  <br />
              </span><span class="text-white">CEP</span>
              <br />
              <asp:TextBox ID="txt_CEP" runat="server" OnTextChanged="txt_CEP_TextChanged" Width="89px"></asp:TextBox>
                  &nbsp;&nbsp;&nbsp;<br />
                       <br />
                 <asp:Button ID="btn_CEP" runat="server"  CausesValidation="False" OnClick="btn_CEP_Click" Text="Procurar CEP" Width="140px" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" />           
                            
            <br />
              <br />
              <span class="text-white">Estado</span><br />
              <asp:TextBox ID="txtEstado" runat="server" Width="50px" OnTextChanged="txtEstado_TextChanged" Enabled="False"></asp:TextBox>
              <asp:RequiredFieldValidator ID="RequiredFieldValidator25" runat="server" ControlToValidate="txtEstado" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
              <br />
              <span class="text-white">Cidade</span><br />
              <asp:TextBox ID="txtCidade" runat="server" Width="300px" Enabled="False"></asp:TextBox>
              <asp:RequiredFieldValidator ID="RequiredFieldValidator26" runat="server" ControlToValidate="txtCidade" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
              <br />
              <span class="text-white">Bairro</span><br />
              <asp:TextBox ID="txtBairro" runat="server" Width="300px" Enabled="False"></asp:TextBox>
              <asp:RequiredFieldValidator ID="RequiredFieldValidator27" runat="server" ControlToValidate="txtBairro" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
              <br />
              <span class="text-white">Endereço</span><br />
              <asp:TextBox ID="txtEndereco" runat="server" Width="300px" Enabled="False"></asp:TextBox>
              <asp:RequiredFieldValidator ID="RequiredFieldValidator28" runat="server" ControlToValidate="txtEndereco" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
              <br />
              <span class="text-white">Número residencial</span><br />
              <asp:TextBox ID="txtNumero" runat="server" Width="100px"></asp:TextBox>
              <asp:RequiredFieldValidator ID="RequiredFieldValidator29" runat="server" ControlToValidate="txtNumero" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
              <br />
              <span class="text-white">Complemento</span><br />
              <asp:TextBox ID="txtComplemento" runat="server" TextMode="MultiLine" Width="200px"></asp:TextBox>
              <br />
              <br />
                       </div>
              </div>   
      
          
          <div class="col-lg-4">
                   <div class="intro-info">
              <span class="auto-style2"><em style="color: #FFFFFF">Login</em></span><br />
              <br />
              <span class="text-white">Email</span><br />
              <asp:TextBox ID="txtEmail" runat="server" Width="300px"></asp:TextBox>
              <asp:RequiredFieldValidator ID="RequiredFieldValidator30" runat="server" ControlToValidate="txtEmail" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
                       <asp:Label ID="lblerroemail" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
              <br />
              <span class="text-white">Senha</span><br />
              <asp:TextBox ID="txtSenha" runat="server" TextMode="Password" Width="300px"></asp:TextBox>
              <asp:RequiredFieldValidator ID="RequiredFieldValidator31" runat="server" ControlToValidate="txtSenha" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
                       <asp:TextBox ID="txtTipoSenha" runat="server" BorderColor="#0066FF" Enabled="False" Height="16px" Width="16px"></asp:TextBox>
                       <asp:Label ID="lblTipoSenha" runat="server" ToolTip="Verifique se sua senha tem no mínimo 8 letras maiúsculas e minúsculas, números e caracteres especiais" BackColor="#0066FF" ForeColor="White"></asp:Label>
                       <asp:Label ID="lblErroSenha" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
              <br />
              <span class="text-white">Confirmar</span> <span class="text-white">senha</span><br />
              <asp:TextBox ID="txtConfirmarSenha" runat="server" TextMode="Password" Width="300px"></asp:TextBox>
              <asp:CompareValidator ID="CompareValidator1" runat="server" ControlToCompare="txtSenha" ControlToValidate="txtConfirmarSenha" ErrorMessage="Senha incorente"></asp:CompareValidator>
                       <br />
              <br />
              <br />
                       <div style="float:left">
            <asp:Button ID="btnCadastrar" runat="server"  OnClick="btnCadastrar_Click" Text="Cadastre-se" Width="300px" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" Height="50px" />

              <br />
                           <asp:Label ID="lblCadastrar" runat="server" style="color: #FFFFFF"></asp:Label>
                           <br />
              <br />
            <asp:Button ID="btnVoltar" runat="server"  OnClick="btnVoltar_Click" Text="Retornar ao Menu" Width="300px" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" Height="50px" CausesValidation="False" />

              <br />
                           </div>
              <asp:SqlDataSource ID="sqlCadastrarTrabalhador" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" InsertCommand="INSERT INTO trabalhador(id_tipo_pacote, nome, foto, cpf, data_nasc, data_registro, sexo, tel, email, senha, cep, num_residencial, complemento, status, pontuacao) VALUES (@idPac, @nome, @foto, @cpf, @data_nasc, DEFAULT, @sexo, @tel, @email, @senha, @cep, @num, @compl, 0, 0)" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM [trabalhador]">
                  <InsertParameters>
                      <asp:Parameter DefaultValue="1" Name="idPac" />
                      <asp:Parameter DefaultValue="" Name="nome" />
                      <asp:Parameter Name="foto" />
                      <asp:Parameter Name="cpf" />
                      <asp:Parameter Name="data_nasc" />
                      <asp:Parameter Name="sexo" />
                      <asp:Parameter Name="tel" />
                      <asp:Parameter Name="email" />
                      <asp:Parameter Name="senha" />
                      <asp:Parameter Name="cep" />
                      <asp:Parameter Name="num" />
                      <asp:Parameter Name="compl" />
                  </InsertParameters>
              </asp:SqlDataSource>
                       </div>
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