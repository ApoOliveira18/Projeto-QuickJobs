<%@ Page Language="C#" AutoEventWireup="true" CodeFile="CadastrarProcurador.aspx.cs" Inherits="CadastrarProcurador" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <meta charset="utf-8"/> 
 <title>Quick Jobs</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
  <meta content="" name="keywords"/>
  <meta content="" name="description"/>

  <link href="img/Logo1.ico" rel="icon"/>
  <link href="img/Logo1.ico" rel="apple-touch-icon"/>

  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700" rel="stylesheet"/>

  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>

  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
  <link href="lib/animate/animate.min.css" rel="stylesheet"/>
  <link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet"/>
  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet"/>
  <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet"/>
 
  <link href="css/style.css" rel="stylesheet"/>

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

     <style type="text/css">
         .auto-style1 {
             font-size: x-large;
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
        <h2 class="text-white">Insira suas informações como Procurador</h2>
   
      <div class="row">
        
        <div class ="col-lg-4"> 
                 <div class="intro-info">
                     <span class="auto-style1"><em>Pessoais</em></span><br />
            <br />
            <span class="text-white">Nome</span><br />
            <asp:TextBox ID="txtNome" runat="server" Width="300px" OnTextChanged="txtNome_TextChanged"></asp:TextBox>
            <asp:RequiredFieldValidator ID="rqfvNome" runat="server" ControlToValidate="txtNome" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
            <br />
            <br />
            <span class="text-white">Sexo</span><br />
            <asp:DropDownList ID="ddlSexo" runat="server" OnSelectedIndexChanged="ddlSexo_SelectedIndexChanged">
                <asp:ListItem>Masculino</asp:ListItem>
                <asp:ListItem>Feminino</asp:ListItem>
                <asp:ListItem>Outro</asp:ListItem>
            </asp:DropDownList>
            <asp:RequiredFieldValidator ID="rqfvSexo" runat="server" ControlToValidate="ddlSexo" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
            <br />
            <br />
            <span class="text-white">Data de nascimento</span><br />
            <asp:TextBox ID="txtData" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="rqfvData" runat="server" ControlToValidate="txtData" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>

            <br />
                     <asp:Label ID="lblerrodata" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>

            <br />
            <span class="text-white">Foto de Perfil</span><br />
            <asp:Image ID="Image1" runat="server" Height="100px" Width="100px" />
            <br />
            <asp:FileUpload ID="FileUpload1" runat="server" ForeColor="White" />
            <br />
            <br />
            <br />
            <span class="text-white">CPF</span><br />
            <asp:TextBox ID="txtCPF" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" ControlToValidate="txtCPF" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
                     <br />
                     <asp:Label ID="lblerrocpf" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
            <br />
            <span class="text-white">Celular</span><br />
            <asp:TextBox ID="txtCelular" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="rqfvCel" runat="server" ControlToValidate="txtCelular" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
            <br />
            
            <br />
                     </div>
            </div>
          <div class ="col-lg-4">
                   <div class="intro-info">
                       <span class="auto-style1"><em>Localidade</em></span><br />
                       <br />
              <span class="text-white">CEP</span><br />
              <asp:TextBox ID="txt_CEP" runat="server" Width="89px"></asp:TextBox>
                       <br />
<br />
              <asp:Button ID="btn_CEP" runat="server"  CausesValidation="False" OnClick="btn_CEP_Click" Text="Procurar CEP" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" Width="140px"/>
              <br />
              <br />
              <span class="text-white">Estado</span><br />
              <asp:TextBox ID="txtEstado" runat="server" Width="50px" Enabled="False"></asp:TextBox>
              <asp:RequiredFieldValidator ID="rqfvEstado" runat="server" ControlToValidate="txtEstado" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
              <br />
              <span class="text-white">Cidade</span><br />
              <asp:TextBox ID="txtCidade" runat="server" Width="300px" Enabled="False"></asp:TextBox>
              <asp:RequiredFieldValidator ID="rqfvCidade" runat="server" ControlToValidate="txtCidade" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
              <br />
              <span class="text-white">Bairro</span><br />
              <asp:TextBox ID="txtBairro" runat="server" Width="300px" Enabled="False"></asp:TextBox>
              <asp:RequiredFieldValidator ID="rqfvBairro" runat="server" ControlToValidate="txtBairro" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
              <br />
              <span class="text-white">Endereço</span><br />
              <asp:TextBox ID="txtEndereco" runat="server" Width="300px" Enabled="False"></asp:TextBox>
              <asp:RequiredFieldValidator ID="rqfvEndereco" runat="server" ControlToValidate="txtEndereco" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
              <br />
              <span class="text-white">Número</span> <span class="text-white">residencial</span><br />
              <asp:TextBox ID="txtNumero" runat="server" Width="100px"></asp:TextBox>
              <asp:RequiredFieldValidator ID="rqfvNum" runat="server" ControlToValidate="txtNumero" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
              <br />
              <br />
              <span class="text-white">Complemento</span><br />
              <asp:TextBox ID="txtComplemento" runat="server" TextMode="MultiLine" Width="300px" Height="67px"></asp:TextBox>
              <br />
                       </div>
             </div>     
          
          <div class="col-lg-4">
                   <div class="intro-info">
                   <em><span class="auto-style1">Login</span></em><br />
                   <br />
                   <span class="text-white">Email</span><br />
                   <asp:TextBox ID="txtEmail" runat="server" Width="300px"></asp:TextBox>
                  
                       <asp:RequiredFieldValidator ID="rqfvEmail" runat="server" ControlToValidate="txtEmail" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
                       <br />
                  <asp:Label ID="lblerroemail" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
                   <br />
                   <span class="text-white">Senha</span><br />
                   <asp:TextBox ID="txtSenha" runat="server" TextMode="Password" Width="300px"></asp:TextBox>
                   <asp:RequiredFieldValidator ID="rqfvSenha" runat="server" ControlToValidate="txtSenha" ErrorMessage="Preencha o campo">*</asp:RequiredFieldValidator>
                   <br />
                       <asp:TextBox ID="txtTipoSenha" runat="server" BorderColor="#0066FF" Enabled="False" Height="16px" Width="16px"></asp:TextBox>
                       <asp:Label ID="lblTipoSenha" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
                       <asp:Label ID="lblErroSenha" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
                   <br />
                   <span class="text-white">Confirmar</span> <span class="text-white">senha</span></span><br />
                   <asp:TextBox ID="txtConfirmarSenha" runat="server" TextMode="Password" Width="300px"></asp:TextBox>
                   <asp:CompareValidator ID="compvSenha" runat="server" ControlToCompare="txtSenha" ControlToValidate="txtConfirmarSenha" ErrorMessage="Senha incoerente"></asp:CompareValidator>
                   <br />
                   <br />
                   <br />
                   <br />
            <asp:Button ID="btnCadastrar" runat="server"  OnClick="btnCadastrar_Click" Text="Cadastrar" Width="300px" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" Height="50px" />
                       
                   <br />
                       <asp:Label ID="lblCad" runat="server" style="color: #FFFFFF"></asp:Label>
                       <br />
                   <br />
            <asp:Button ID="btnVoltar" runat="server"  OnClick="btnVoltar_Click" Text="Retornar ao Menu" Width="300px" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" Height="50px" CausesValidation="False" />
                   <br />
                   <br />
                       </div>
              </div>
      </div>
   
            <br />
            <asp:SqlDataSource ID="sqlCadastrarProcurador" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" InsertCommand="INSERT INTO procurador(foto, sexo, data_nasc, data_cadastro, cpf, tel, email, senha, cep, num_residencial, complemento, pontuacao, nome, status) VALUES (@FOTO, @SEXO, @DATA, DEFAULT, @CPF, @CEL, @EMAIL, @SENHA, @CEP, @NUM, @COMPL, 0, @NOME, 0)" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM procurador">
                <InsertParameters>
                    <asp:Parameter Name="FOTO" />
                    <asp:Parameter Name="SEXO" />
                    <asp:Parameter Name="DATA" />
                    <asp:Parameter Name="CPF" />
                    <asp:Parameter Name="EMAIL" />
                    <asp:Parameter Name="SENHA" />
                    <asp:Parameter Name="CEP" />
                    <asp:Parameter Name="NUM" />
                    <asp:Parameter Name="COMPL" />
                    <asp:Parameter Name="NOME" />
                    <asp:Parameter Name="CEL" />
                </InsertParameters>
        </asp:SqlDataSource>
            <br />
     
   
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
