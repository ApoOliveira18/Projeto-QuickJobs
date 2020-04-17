<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Login.aspx.cs" Inherits="Login" %>

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

 .g-recaptcha {
  width: 302px;
  margin: auto;
}
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
    </div>
  </header><!-- #header -->
     <!--==========================
    Intro Section
  ============================-->
  <section id="intro" class="clearfix">
    <div class="container">
        <h2 class="text-center">&nbsp;<em ">Por favor insira suas informações de login para acessar sua conta</em></h2>   
        <div style="text-align: center">

            Email:
            <asp:TextBox ID="txtEmail" runat="server" Width="300px" ></asp:TextBox>
            <asp:RequiredFieldValidator ID="rqfVEmail" runat="server" ToolTip="Obrigatório" ControlToValidate="txtEmail" ErrorMessage="*"></asp:RequiredFieldValidator>
            <br />
            <asp:Label ID="Label1" runat="server" ForeColor="Red"></asp:Label>
            <br />

            Senha:
            <asp:TextBox ID="txtSenha" runat="server" Width="300px" TextMode="Password" ></asp:TextBox>
            <asp:RequiredFieldValidator ID="rqfVSenha" runat="server" ToolTip="Obrigatório" ControlToValidate="txtSenha" ErrorMessage="*"></asp:RequiredFieldValidator>
            <br />
            <asp:Label ID="lblErro1" runat="server" ForeColor="Red"></asp:Label>
            <br />
            <br />
            <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
    async defer></script>
<script type="text/javascript">
    var onloadCallback = function () {
        grecaptcha.render('dvCaptcha', {
            'sitekey': '<%=ReCaptcha_Key %>',
            'callback': function (response) {
                $.ajax({
                    type: "POST",
                    url: "Default.aspx/VerifyCaptcha",
                    data: "{response: '" + response + "'}",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (r) {
                        var captchaResponse = jQuery.parseJSON(r.d);
                        if (captchaResponse.success) {
                            $("[id*=txtCaptcha]").val(captchaResponse.success);
                            $("[id*=rfvCaptcha]").hide();
                        } else {
                            $("[id*=txtCaptcha]").val("");
                            $("[id*=rfvCaptcha]").show();
                            var error = captchaResponse["error-codes"][0];
                            $("[id*=rfvCaptcha]").html("RECaptcha error. " + error);
                        }
                    }
                });
            }
        });
    };
</script>

            Insira o tipo da sua conta<br />
            <asp:DropDownList ID="ddlTipoUsu" runat="server" OnSelectedIndexChanged="ddlTipoUsu_SelectedIndexChanged">
                <asp:ListItem>Procurador</asp:ListItem>
                <asp:ListItem>Trabalhador</asp:ListItem>
            </asp:DropDownList>
            <br />
            <br />
          
             <div id="dvCaptcha">
                <asp:Panel ID="pnlEsconder" runat="server" Height="100px" Width="200px">
                </asp:Panel>
              
        </div>
        <asp:TextBox ID="txtCaptcha" runat="server" Style="display: none"/>
        <asp:RequiredFieldValidator ID="rfvCaptcha" runat="server" ControlToValidate="txtCaptcha" Display="Dynamic" Enabled="False" ErrorMessage="Captcha validation is required." ForeColor="Red" />

            <br />
            <br />

            <asp:Button ID="btnAcessar" runat="server" class="btn-get-started" OnClick="btnAcessar_Click" Text="Acessar Conta" Width="300px" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" />
            <br />
        <br />

            <asp:Button ID="btnEsqueciSenha" runat="server" class="btn-get-started" OnClick="btnEsqueciSenha_Click" Text="Esqueci minha senha" Width="300px" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" CausesValidation="False" />
            <br />
            <br />
            Ainda não possui uma conta? Crie uma é rápido e fácil !<br />
            <br />
            <asp:Button ID="btnCadastrarProcurador" runat="server" class="btn-get-started" OnClick="btnCadastrarProcurador_Click" Text="Cadastre-se como Procurador" Width="300px" CausesValidation="False" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" />
            <br />
            <br />
            <asp:Button ID="btnCadastrarTrabalhador" runat="server" class="btn-get-started" OnClick="btnCadastrarTrabalhador_Click" Text="Cadastre-se como Trabalhador" Width="300px" CausesValidation="False" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" />
            <br />
            <br />

     
   
            <asp:SqlDataSource ID="sqlTrabalhador" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" OnSelecting="sqlTrabalhador_Selecting" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT id_trabalhador, email, senha, nome FROM trabalhador WHERE (email = @email) AND (senha = @senha)">
                <SelectParameters>
                    <asp:Parameter Name="email" />
                    <asp:Parameter Name="senha" />
                </SelectParameters>
            </asp:SqlDataSource>
            <br />
            <asp:SqlDataSource ID="sqlProcurador" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT id_procurador, nome, senha, email FROM procurador WHERE (senha = @senha) AND (email = @email)">
                <SelectParameters>
                    <asp:Parameter Name="email" />
                    <asp:Parameter Name="senha" />
                </SelectParameters>
            </asp:SqlDataSource>
     
   
            <br />
    
   
        <asp:SqlDataSource ID="sqlAdm" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT id_adm, nome, foto, sexo, cpf, data_nasc, data_cadastro, email, senha, tel, cep, num_residencial, complemento, pergunta, resposta, primeiro, status FROM administrador WHERE (email = @email) AND (senha = @senha)">
            <SelectParameters>
                <asp:Parameter Name="senha" />
                <asp:Parameter Name="email" />
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
