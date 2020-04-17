<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Alt_Del_Servico.aspx.cs" Inherits="Alt_Del_Servico" %>
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
       
        <div>
            <h2>
                Titulo:
                <asp:TextBox ID="txtTitulo" runat="server" Width="509px"></asp:TextBox>
            </h2>
        </div>
        <div class="row">
           <div class ="col-lg-4">
                 <br />
                 Tipo de trabalhador requisitado:
                 <asp:DropDownList ID="ddlTipoTrab" runat="server">
                 </asp:DropDownList>
                 <br />
                 Atual:
                 <asp:Label ID="lblTIpoTrab" runat="server"></asp:Label>
&nbsp;<asp:SqlDataSource ID="sqlTipoTrab" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM tipo_trabalhador"></asp:SqlDataSource>
                 <br />
            <br />
            Descrição:<br />
            <asp:TextBox ID="txtDesc" runat="server" Height="85px" TextMode="MultiLine" Width="236px"></asp:TextBox>
            <br />
            <br />
            Observações:<br />
            <asp:TextBox ID="txtObs" runat="server" Height="85px" TextMode="MultiLine" Width="236px"></asp:TextBox>
            <br />
            <br />
            Tipo de pagamento:
                 <asp:DropDownList ID="ddlTipoPag" runat="server" OnSelectedIndexChanged="ddlTipoPag_SelectedIndexChanged">
                 </asp:DropDownList>
                  <br />
                 Atual:
                 <asp:Label ID="lblTipoPag" runat="server"></asp:Label>
                  <asp:SqlDataSource ID="sqlTipoPag" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM pagamento"></asp:SqlDataSource>
            <br />
            <br />
            Valor: R$
                 <asp:TextBox ID="txtValor" runat="server"></asp:TextBox>
                 <br />
                 <br />
                 <asp:Button ID="btnAlterar" runat="server" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" OnClick="btnAlterar_Click" Text="Alterar Dados" />
                 <br />
                 <br />
                 <asp:Button ID="btnExcluir" runat="server" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" OnClick="btnDeletar_Click" Text="Excluir Serviço" />
                 <br />
                 <asp:Label ID="lblAviso" runat="server"></asp:Label>
            </div>
           <div class ="col-lg-4">


               <br />


               CEP:
               <asp:TextBox ID="txtCep" runat="server"></asp:TextBox>
&nbsp;<asp:Button ID="btnCEP" runat="server" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" OnClick="btnCEP_Click" Text="Procurar CEP" />
               <br />
               <br />
               UF:&nbsp;
               <asp:TextBox ID="txtEstado" runat="server" Enabled="False" Width="40px"></asp:TextBox>
               <br />
               <br />
               Cidade:
               <asp:TextBox ID="txtCidade" runat="server" Enabled="False"></asp:TextBox>
               <br />
               <br />
               Bairro:
               <asp:TextBox ID="txtBairro" runat="server" Enabled="False"></asp:TextBox>
               <br />
               <br />
               Endereço:
               <asp:TextBox ID="txtEndereco" runat="server" Enabled="False"></asp:TextBox>
               <br />
               <br />
               Numero Residencial:
               <asp:TextBox ID="txtNumRes" runat="server"></asp:TextBox>
               <br />
               <br />
               Complemento:<br />
            <asp:TextBox ID="txtCompl" runat="server" Height="85px" TextMode="MultiLine" Width="214px"></asp:TextBox>


           </div>
            <div class ="col-lg-4">
                <h2>&nbsp;</h2>
                <h2>EXTRA</h2>
                Foto1:
                <asp:Image ID="img1" runat="server" Width="100px" />
                <br />
                <asp:FileUpload ID="foto1" runat="server" />
                <br />
                <br />
                Foto2:
                <asp:Image ID="imagem2" runat="server" Width="100px" />
                <br />
                <asp:FileUpload ID="foto2" runat="server" />
                </div>
        </div>

        </div>  
   
      <asp:SqlDataSource ID="sqlServico" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT servico.id_servico, servico.titulo, servico.foto1, servico.foto2, servico.descricao, servico.valor, servico.observacoes, servico.data_registro, servico.cep, servico.num_residencial, servico.complemento, servico.status, pagamento.forma, tipo_trabalhador.tipo, procurador.nome FROM servico INNER JOIN pagamento ON servico.id_pagamento = pagamento.id_pagamento INNER JOIN tipo_trabalhador ON servico.id_tipo_trab = tipo_trabalhador.id_tipo_trab INNER JOIN procurador ON servico.id_procurador = procurador.id_procurador WHERE (servico.id_servico = @IDservico) AND (servico.status = 0)" InsertCommand="INSERT INTO contrato(id_servico, data_registro) VALUES (@IdServico, DEFAULT)" UpdateCommand="UPDATE servico SET id_trabalhador = @idTrab WHERE (id_servico = @idServico)">
          <InsertParameters>
              <asp:SessionParameter Name="IdServico" SessionField="CodServico" />
          </InsertParameters>
          <SelectParameters>
              <asp:SessionParameter Name="IDservico" SessionField="CodServicoProc" />
          </SelectParameters>
          <UpdateParameters>
              <asp:SessionParameter Name="idServico" SessionField="CodServico" />
              <asp:SessionParameter Name="@idTrab" SessionField="idTrabalhador" />
          </UpdateParameters>
      </asp:SqlDataSource>
   
      <asp:SqlDataSource ID="sqlDeletar" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM [servico]" UpdateCommand="UPDATE servico SET status = 1 WHERE (id_servico = @id) ">
          <UpdateParameters>
              <asp:SessionParameter Name="id" SessionField="CodServicoProc" />
          </UpdateParameters>
      </asp:SqlDataSource>
      <asp:SqlDataSource ID="sqlAlterar" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT * FROM [servico]" UpdateCommand="UPDATE servico SET titulo = @NovoTitulo, foto1 = @NovaFoto1, foto2 = @NovaFoto2, descricao = @NovaDesc, valor = @NovoValor, observacoes = @NovaObs, cep = @NovoCep, num_residencial = @NovoNum, complemento = @NovoComplemento, id_pagamento = @NovoPag, id_tipo_trab = @NovoTipo WHERE (id_servico = @idServico)">
          <UpdateParameters>
              <asp:Parameter Name="NovoTitulo" />
              <asp:ControlParameter ControlID="ddlTipoTrab" Name="NovoTipo" PropertyName="SelectedValue" />
              <asp:Parameter Name="NovoComplemento" />
              <asp:Parameter Name="NovaDesc" />
              <asp:Parameter Name="NovaObs" />
              <asp:ControlParameter ControlID="ddlTipoPag" Name="NovoPag" PropertyName="SelectedValue" />
              <asp:Parameter Name="NovoValor" />
              <asp:Parameter Name="NovoNum" />
              <asp:Parameter Name="NovaFoto1" />
              <asp:Parameter Name="NovaFoto2" />
              <asp:Parameter Name="NovoCep" />
              <asp:SessionParameter Name="idServico" SessionField="CodServicoProc" />
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