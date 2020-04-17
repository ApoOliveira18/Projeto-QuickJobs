<%@ Page Language="C#" AutoEventWireup="true" CodeFile="PesqContratoADM.aspx.cs" Inherits="PesqContratoADM" %>

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
        <h2>Lista de contratos</h2>
        <div>

            Filtrar por email do procurador:
            <asp:TextBox ID="txtPesqProc" runat="server" Width="240px"></asp:TextBox>
&nbsp;<asp:Button ID="btnPesqProc" runat="server" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" OnClick="btnPesqProc_Click" Text="Pesquisar" ValidationGroup="Proc" />
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="txtPesqProc" ErrorMessage="Preencha o campo" ValidationGroup="Proc"></asp:RequiredFieldValidator>
            <asp:Label ID="lblErroProc" runat="server"></asp:Label>
            <br />
            <br />
            Filtrar por email do trabalhador:
            <asp:TextBox ID="txtPesqTrab" runat="server" Width="240px"></asp:TextBox>
&nbsp;<asp:Button ID="btnPesqTrab" runat="server" BackColor="#0066FF" BorderColor="#0066FF" ForeColor="White" OnClick="btnPesqTrab_Click" Text="Pesquisar" ValidationGroup="Trab" />
            <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ControlToValidate="txtPesqTrab" ErrorMessage="Preencha o campo" ValidationGroup="Trab"></asp:RequiredFieldValidator>
            <asp:Label ID="lblErroTrab" runat="server"></asp:Label>
            <br />
            <br />
            <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:Button ID="btnPesqTodos" runat="server" BackColor="#0066FF" BorderColor="#0066FF" CausesValidation="False" Font-Bold="False" Font-Size="Large" ForeColor="White" OnClick="btnPesqTodos_Click" Text="Pesquisar todos" />
            <asp:Label ID="lblErro" runat="server"></asp:Label>
            <br />
            <br />
            <br />
            <asp:GridView ID="gvExibir" runat="server" AutoGenerateColumns="False" CellPadding="4" DataKeyNames="id_contrato" ForeColor="#333333" GridLines="None" OnSelectedIndexChanged="GridView1_SelectedIndexChanged" Width="1000px">
                <AlternatingRowStyle BackColor="White" />
                <Columns>
                    <asp:CommandField ShowSelectButton="True" />
                    <asp:TemplateField HeaderText="Título do Serviço" SortExpression="titulo">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox1" runat="server" Text='<%# Bind("titulo") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label1" runat="server" Text='<%# Bind("titulo") %>'></asp:Label>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="Nome do Procurador" SortExpression="nomeProc">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox2" runat="server" Text='<%# Bind("nomeProc") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label2" runat="server" Text='<%# Bind("nomeProc") %>'></asp:Label>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="Nome do Trabalhador" SortExpression="nomeTrab">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox3" runat="server" Text='<%# Bind("nomeTrab") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label3" runat="server" Text='<%# Bind("nomeTrab") %>'></asp:Label>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="Valor Cobrado" SortExpression="valor">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox4" runat="server" Text='<%# Bind("valor") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label4" runat="server" Text='<%# Bind("valor", "{0:c}") %>'></asp:Label>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="Forma de Pagamento" SortExpression="forma">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox5" runat="server" Text='<%# Bind("forma") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label5" runat="server" Text='<%# Bind("forma") %>'></asp:Label>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="Data de Registro" SortExpression="data_registro">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox6" runat="server" Text='<%# Bind("data_registro") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label6" runat="server" Text='<%# Bind("data_registro", "{0:d}") %>'></asp:Label>
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
            <asp:SqlDataSource ID="sqlContratoProc" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT contrato.id_contrato, servico.titulo, servico.valor, procurador.nome AS nomeProc, trabalhador.nome AS nomeTrab, pagamento.forma, contrato.data_registro FROM contrato INNER JOIN servico ON contrato.id_servico = servico.id_servico INNER JOIN procurador ON servico.id_procurador = procurador.id_procurador INNER JOIN trabalhador ON servico.id_trabalhador = trabalhador.id_trabalhador INNER JOIN pagamento ON servico.id_pagamento = pagamento.id_pagamento WHERE (procurador.email = @email)">
                <SelectParameters>
                    <asp:Parameter Name="email" />
                </SelectParameters>
            </asp:SqlDataSource>
            <asp:SqlDataSource ID="sqlContratoTrab" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT contrato.id_contrato, servico.titulo, servico.valor, procurador.nome AS nomeProc, trabalhador.nome AS nomeTrab, pagamento.forma, contrato.data_registro FROM contrato INNER JOIN servico ON contrato.id_servico = servico.id_servico INNER JOIN procurador ON servico.id_procurador = procurador.id_procurador INNER JOIN trabalhador ON servico.id_trabalhador = trabalhador.id_trabalhador INNER JOIN pagamento ON servico.id_pagamento = pagamento.id_pagamento WHERE (trabalhador.email = @email)">
                <SelectParameters>
                    <asp:Parameter Name="email" />
                </SelectParameters>
            </asp:SqlDataSource>
            <asp:SqlDataSource ID="sqlContrato" runat="server" ConnectionString="<%$ ConnectionStrings:quickjobsConnectionString %>" ProviderName="<%$ ConnectionStrings:quickjobsConnectionString.ProviderName %>" SelectCommand="SELECT contrato.id_contrato, servico.titulo, servico.valor, procurador.nome AS nomeProc, trabalhador.nome AS nomeTrab, pagamento.forma, contrato.data_registro FROM contrato INNER JOIN servico ON contrato.id_servico = servico.id_servico INNER JOIN procurador ON servico.id_procurador = procurador.id_procurador INNER JOIN trabalhador ON servico.id_trabalhador = trabalhador.id_trabalhador INNER JOIN pagamento ON servico.id_pagamento = pagamento.id_pagamento"></asp:SqlDataSource>

        </div>
        </div>

  </section>
    </form>
</body>
</html>
