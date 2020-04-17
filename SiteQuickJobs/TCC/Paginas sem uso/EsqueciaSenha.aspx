<%@ Page Language="C#" AutoEventWireup="true" CodeFile="EsqueciaSenha.aspx.cs" Inherits="EsqueciaSenha" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head runat="server" >
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
            Nome:
            <asp:TextBox ID="txtNome" runat="server" Width="216px"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="txtNome" ErrorMessage="*"></asp:RequiredFieldValidator>
            <br />
            <br />
            Email:
            <asp:TextBox ID="txtEmail" runat="server" ToolTip="Digite seu email" OnTextChanged="txtEmail_TextChanged" Width="218px"></asp:TextBox>           
            <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ControlToValidate="txtEmail" ErrorMessage="*"></asp:RequiredFieldValidator>
            <br />
            <br />
            <asp:Button ID="btnEnviar" runat="server" Text="Enviar" OnClick="btnEnviar_Click" CausesValidation="False" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:Button ID="btnVoltar" runat="server" OnClick="btnVoltar_Click" Text="Voltar" CausesValidation="False" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" />
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
        <%@ Register Assembly="GoogleReCaptcha" Namespace="GoogleReCaptcha" TagPrefix="cc1" %>
        <script src='https://www.google.com/recaptcha/api.js?hl=pt-BR'></script>

        <asp:Panel ID="Panel1" runat="server" CssClass="panel"  Visible="false">

        </asp:Panel>
            <br />
            <asp:Label ID="lblErro" runat="server" ></asp:Label>
            <br />
            <asp:SqlDataSource ID="sqlProcurador" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT nome, email, senha FROM procurador WHERE (nome = @Nome) AND (email = @Email)">
                <SelectParameters>
                    <asp:Parameter Name="Nome" />
                    <asp:Parameter Name="Email" />
                </SelectParameters>
            </asp:SqlDataSource>
            <asp:SqlDataSource ID="sqlTrabalhador" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT email, senha, nome FROM trabalhador WHERE (email = @Email) AND (nome = @Nome)">
                <SelectParameters>
                    <asp:Parameter Name="Nome" />
                    <asp:Parameter Name="Email" />
                </SelectParameters>
            </asp:SqlDataSource>
        </div>
                  </div>
            </section>
    </form>
    
</body>
</html>
