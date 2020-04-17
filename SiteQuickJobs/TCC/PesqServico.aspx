<%@ Page Language="C#" AutoEventWireup="true" CodeFile="PesqServico.aspx.cs" Inherits="PesqServico" %>
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
           <li>   <asp:LinkButton ID="btnLogoff" runat="server" CssClass="auto-style4" OnClick="btnLogoff_Click" CausesValidation="False">Sair</asp:LinkButton> </li>
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
            <h2>Procure um serviço!</h2>
        </div>
        <div>

               <asp:Label ID="Label3" runat="server" Text="Pesquisar por nome de serviço:"></asp:Label>
&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:TextBox ID="txtPesqServ" runat="server" Width="260px" Height="34px"></asp:TextBox>
&nbsp; <asp:Button ID="btnPesqSerc" runat="server" OnClick="btnPesqSerc_Click" Text="Pesquisar" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" />
               <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="txtPesqServ" ErrorMessage="Preencha o campo"></asp:RequiredFieldValidator>
               <br />
            <br />
        </div>
        <asp:Label ID="Label2" runat="server" Text="Pesquisar por tipo de trabalhador:"></asp:Label>
&nbsp;<asp:DropDownList ID="ddlTrab" runat="server" CssClass="auto-style2" Height="31px" Width="265px">
        </asp:DropDownList>
&nbsp;
        <asp:Button ID="btnPesqTrab" runat="server" OnClick="btnPesqTrab_Click" Text="Pesquisar" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" CausesValidation="False" />
        <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Label ID="lblAviso" runat="server"></asp:Label>
        <br />
        <br />
        <br />
        <br />
        <asp:GridView ID="gvExibir" runat="server" AutoGenerateColumns="False" CellPadding="4" CssClass="auto-style3" DataKeyNames="id_servico" ForeColor="#333333" GridLines="None" Height="198px" OnSelectedIndexChanged="gvExibir_SelectedIndexChanged" Width="1139px">
            <AlternatingRowStyle BackColor="White" />
            <Columns>
                <asp:TemplateField ShowHeader="False">
                    <ItemTemplate>
                        <asp:LinkButton ID="LinkButton1" runat="server" CausesValidation="False" CommandName="Select" Text="Escolher"></asp:LinkButton>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Serviço" SortExpression="titulo">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox1" runat="server" Text='<%# Bind("titulo") %>'></asp:TextBox>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label1" runat="server" Text='<%# Bind("titulo") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField> 
                <asp:TemplateField HeaderText="Foto 1" SortExpression="foto1">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox2" runat="server" Text='<%# Bind("foto1") %>'></asp:TextBox>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Image ID="Image1" runat="server" Height="100px" ImageUrl='<%# Eval("foto1","{0}") %>' Width="100px" />
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Foto 2" SortExpression="foto2">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox3" runat="server" Text='<%# Bind("foto2") %>'></asp:TextBox>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Image ID="Image2" runat="server" Height="100px" ImageUrl='<%# Eval("foto2","{0}") %>' Width="100px" />
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Valor" SortExpression="valor">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox7" runat="server" Text='<%# Bind("valor") %>'></asp:TextBox>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label7" runat="server" Text='<%# Bind("valor", "{0:c}") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Pagamento" SortExpression="forma">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox8" runat="server" Text='<%# Bind("forma") %>'></asp:TextBox>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label2" runat="server" Text='<%# Bind("forma") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Data de registro" SortExpression="data_registro">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox9" runat="server" Text='<%# Bind("data_registro") %>'></asp:TextBox>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label3" runat="server" Text='<%# Bind("data_registro", "{0:d}") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="CEP" SortExpression="cep">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox10" runat="server" Text='<%# Bind("cep") %>'></asp:TextBox>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label4" runat="server" Text='<%# Bind("cep") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Tipo de Trabalhador" SortExpression="tipo">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox11" runat="server" Text='<%# Bind("tipo") %>'></asp:TextBox>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label5" runat="server" Text='<%# Bind("tipo") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
            <EditRowStyle BackColor="#2461BF" />
            <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
            <HeaderStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
            <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
            <RowStyle BackColor="#EFF3FB" />
            <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
            <SortedAscendingCellStyle BackColor="#F5F7FB" />
            <SortedAscendingHeaderStyle BackColor="#6D95E1" />
            <SortedDescendingCellStyle BackColor="#E9EBEF" />
            <SortedDescendingHeaderStyle BackColor="#4870BE" />
        </asp:GridView>
        <asp:SqlDataSource ID="sqlTipoTrab" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM tipo_trabalhador"></asp:SqlDataSource>
        <asp:SqlDataSource ID="sqlServicoNome" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT servico.titulo, servico.foto1, servico.foto2, servico.valor, servico.data_registro, servico.cep, pagamento.forma, servico.id_servico, tipo_trabalhador.tipo, servico.complemento, servico.num_residencial, servico.id_trabalhador FROM servico INNER JOIN pagamento ON servico.id_pagamento = pagamento.id_pagamento INNER JOIN tipo_trabalhador ON servico.id_tipo_trab = tipo_trabalhador.id_tipo_trab WHERE (servico.status = 0) AND (servico.id_trabalhador IS NULL)"></asp:SqlDataSource>
        <asp:SqlDataSource ID="sqlServicoTrab" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT servico.id_servico, servico.titulo, servico.foto1, servico.foto2, servico.valor, servico.data_registro, servico.cep, pagamento.forma, tipo_trabalhador.tipo, servico.id_tipo_trab, servico.id_trabalhador FROM servico INNER JOIN pagamento ON servico.id_pagamento = pagamento.id_pagamento INNER JOIN tipo_trabalhador ON servico.id_tipo_trab = tipo_trabalhador.id_tipo_trab WHERE (servico.status = 0) AND (servico.id_tipo_trab = @tipoTrab) AND (servico.id_trabalhador IS NULL)" OnSelecting="sqlServicoTrab_Selecting">
            <SelectParameters>
                <asp:ControlParameter ControlID="ddlTrab" Name="TipoTrab" PropertyName="SelectedValue" />
            </SelectParameters>
        </asp:SqlDataSource>
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
