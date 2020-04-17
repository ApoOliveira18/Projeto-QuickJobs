<%@ Page Language="C#" AutoEventWireup="true" CodeFile="CadastrarADM.aspx.cs" Inherits="adiministrador" %>

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

      <script src="Scripts/jquery-2.1.1.min.js"></script>
     <script src="Scripts/jquery.maskedinput.min.js"></script>
     <script type="text/javascript">
         jQuery(function ($) {
             $("#txtData").mask("99/99/9999");
             $("#txtTel").mask("(99) 99999-9999");
             $("#txtCEP").mask("99999-999");
             $("#txtCPF").mask("999.999.999-99");
         });
    </script>  
 
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
         <h2>Cadastre um novo Administrador</h2>
        <div class="row">            
        <div class="col-6">
            NOME: <asp:TextBox ID="txtNome" runat="server"></asp:TextBox>
            <br />
            <br />
            FOTO: <asp:FileUpload ID="Imagem" runat="server" />
            <br />
            <br />
            SEXO:
            <asp:DropDownList ID="ddlSexo" runat="server">
                <asp:ListItem>Masculino</asp:ListItem>
                <asp:ListItem>Feminino</asp:ListItem>
                <asp:ListItem>Outro</asp:ListItem>
            </asp:DropDownList>
            <br />
            <br />
            DATA NASCIMENTO: <asp:TextBox ID="txtData" runat="server"></asp:TextBox>
            <asp:Label ID="lblerrodata" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
            <br />
            <br />
            CPF: <asp:TextBox ID="txtCPF" runat="server"></asp:TextBox>
            <asp:Label ID="lblerrocpf" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
            <br />
            <br />
            TEL: <asp:TextBox ID="txtTel" runat="server"></asp:TextBox>
            <br />
            <br />
            EMAIL: <asp:TextBox ID="txtEmail" runat="server"></asp:TextBox>
            <asp:Label ID="lblerroemail" runat="server" BackColor="#0066FF" ForeColor="White"></asp:Label>
            <br />
            <br />
            SENHA: <asp:Label ID="lblerro" runat="server" Font-Bold="True">Gerado Aleatóriamente</asp:Label>
            <br />
            <br />
            </div>
            <div class="col-6">
            CEP: <asp:TextBox ID="txtCEP" runat="server"></asp:TextBox>
                &nbsp;<asp:Button ID="btnCEP" runat="server" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" OnClick="btnCEP_Click" Text="Validar" />
&nbsp;<asp:Label ID="lblCep" runat="server"></asp:Label>
                <br />
            <br />
            NUM RESIDENCIAL: <asp:TextBox ID="txtNum" runat="server"></asp:TextBox>
                <br />
            <br />
            COMPLEMENTO:<br />
                <asp:TextBox ID="txtCompl" runat="server" Height="77px" TextMode="MultiLine" Width="282px"></asp:TextBox>
                <br />
            <br />
            PERGUNTA:<asp:TextBox ID="txtPergunta" runat="server" Width="281px"></asp:TextBox>
                <br />
            <br />
            RESPOSTA: <asp:TextBox ID="txtResposta" runat="server" Width="279px"></asp:TextBox>
                <br />
            <br />
        </div>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Button ID="btnCad" runat="server" OnClick="btnCad_Click" Text="Cadastrar" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" Width="231px" />
        <br />
        <asp:SqlDataSource ID="sqlCadAdm" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" InsertCommand="INSERT INTO administrador(nome, foto, sexo, cpf, data_nasc, data_cadastro, email, senha, tel, cep, num_residencial, complemento, pergunta, resposta, primeiro, status) VALUES (@NOME, @FOTO, @SEXO, @CPF, @DATANASC, DEFAULT, @EMAIL, @SENHA, @TEL, @CEP, @NUMRES, @COMP, @PERGUNTA, @RESPOSTA, 1, 0)" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT id_adm, nome, foto, sexo, cpf, data_nasc, data_cadastro, email, senha, tel, cep, num_residencial, complemento, pergunta, resposta, primeiro, status FROM administrador WHERE (email = @EMAIL)">
            <InsertParameters>
                <asp:Parameter Name="NOME" />
                <asp:Parameter Name="FOTO" />
                <asp:Parameter Name="SEXO" />
                <asp:Parameter Name="DATANASC" />
                <asp:Parameter Name="CPF" />
                <asp:Parameter Name="TEL" />
                <asp:Parameter Name="EMAIL" />
                <asp:Parameter Name="CEP" />
                <asp:Parameter Name="NUMRES" />
                <asp:Parameter Name="COMP" />
                <asp:Parameter Name="PERGUNTA" />
                <asp:Parameter Name="RESPOSTA" />
                <asp:Parameter Name="SENHA" />
            </InsertParameters>
            <SelectParameters>
                <asp:Parameter Name="EMAIL" />
                <asp:SessionParameter Name="SENHA" SessionField="senhaAle" />
            </SelectParameters>
        </asp:SqlDataSource>
        <asp:Label ID="lblErrei" runat="server"></asp:Label>
            </div>
            </div>
       
      </section>
    </form>
</body>
</html>

