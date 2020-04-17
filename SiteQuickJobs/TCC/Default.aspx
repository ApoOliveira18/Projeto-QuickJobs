<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head >
     <meta charset="utf-8">
 <title>Quick Jobs</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <link href="img/Logo1.ico" rel="icon">
  <link href="img/Logo1.ico" rel="apple-touch-icon">

  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700" rel="stylesheet">

  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="lib/animate/animate.min.css" rel="stylesheet">
  <link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet">
  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
 
  <link href="css/style.css" rel="stylesheet">

</head>
<body>
    <form id="form1" runat="server">
  <!--==========================
  Header
  ============================-->
  <header id="header" class="fixed-top">
    <div class="container"> 
        <a href="#intro" class="scrollto"> <asp:Image ID="Image1" runat="server" Height="48px" ImageUrl="~/img/agoravai.png" Width="153px" /></a>
      <nav class="main-nav float-right d-none d-lg-block">
        <ul>
             <li class="active"><a href="Default.aspx">Página inicial</a></li>       
          <li class="drop-down"><a>Guia</a>
           <ul>
                  <li><a href="#about">Sobre nós</a></li>
                  <li><a href="#services">Serviços</a></li>
                  <li><a href="#clients">Pacotes</a></li>
                  
             </ul>
          </li>
           <li><a href="Login.aspx">Acessar</a></li>
        </ul>
      </nav><!-- .main-nav -->
    </div>
  </header><!-- #header -->

  <!--==========================
    Intro Section
  ============================-->
  <section id="intro" class="clearfix">
    <div class="container">

      <div class="intro-img">
        <img src="img/intro-img.svg" alt="" class="img-fluid"/>
      </div>

      <div class="intro-info">
           <h2 >Nós providenciamos<br><span>Soluções</span><br>Para seus negócios!</h2>    
          <div> 
           <asp:Button ID="btnCadastrarUsu" runat="server" Text="Quer ANUNCIAR seus serviços?" class="btn-get-started" OnClick="btnCadastrarUsu_Click" Height="50px" Width="300px"/>
            <br />    
              
            <asp:Button ID="btnCadastrarAnu" runat="server" Text="Precisa CONTRATAR serviços?" class="btn-get-started" OnClick="btnCadastrarTrab_Click" Width="300px" Height="50px"/>  
            <br />
            <asp:Button ID="Button1" runat="server" Text="Faça seu login" class="btn-get-started" OnClick="btnLogar_Click" Height="50px" Width="300px"/>
            </div> 
        </div>
      </div>

  </section><!-- #intro -->

  <main id="main">

    <!--==========================
      About Us Section
    ============================-->
    <section id="about">
      <div class="container">

        <header class="section-header">
     
             <h3 class="auto-style5">Sobre nós</h3>
          <p>Aqui está ume breve informação sobre nossa equipe e nosso aplicativo!</p>
        </header>

        <div class="row about-container">
              <div class="col-lg-6 content order-lg-1 order-2">
            
            <div class="icon-box wow fadeInUp">
              <div class="icon"><i class="fa fa-photo"></i></div>
              <h4 class="title">Nosso projeto</h4>
              <p class="description" style="color:black;">O QuickJobs foi desenvolvido com o propósito de facilitar a vida de pessoas que estão procurando fazer ou pedir serciços, sem ter que fazer diversos cadastros ou buscas em diversos sites e estabelecimentos.</p>
            </div>

                  <div class="icon-box wow fadeInUp" data-wow-delay="0.2s">
              <div class="icon"><i class="fa fa-shopping-bag"></i></div>
              <h4 class="title">Como funciona</h4>
              <p class="description" style="color:black;">É simples! Após cadastrar-se pelo site ou aplicativo, você poderá acessar o mapa ou uma barra de pesquisa em busca de serviço ou cadastra-los para que outros usuários interessados possam vê-los e requisitarem.</p>
            </div>


            <div class="icon-box wow fadeInUp" data-wow-delay="0.4s">
              <div class="icon"><i class="fa fa-bar-chart"></i></div>
              <h4 class="title">Serviços</h4>
              <p class="description" style="color:black;">Ao encontrar o serviço desejado, será liberado um chato com o perfil que postou, a partir de la será feito um acordo entre os dois para estabelecer um preço justo para o serviço (os serviços podem ter preço mínimo apresentado na proposta)</p>
            </div>
          </div>
          <div class="col-lg-6 background order-lg-2 order-1 wow fadeInUp">
            <img src="img/about-img.svg" class="img-fluid" alt="">
          </div>
      </div>
          </div>
    </section><!-- #about -->

    <!--==========================
      Services Section
    ============================-->

       <section id="services" class="section-bg">
      <div class="container">

        <header class="section-header">
          <h3>Serviços</h3>
          <p>Aqui estão alguns dos benefícios que nossos serviços trazem a você</p>
        </header>

        <div class="row">

          <div class="col-md-6 col-lg-5 offset-lg-1 wow bounceInUp" data-wow-duration="1.4s">
            <div class="box">
              <div class="icon"><i class="ion-ios-analytics-outline" style="color: #ff689b;"></i></div>
              <h4 class="title">Facilidade</h4>
              <p class="description" style="color:black;">Você sempre será notificado caso algum outro usuário solicitar um ou mais serviços seu.</p>
            </div>
          </div>
          <div class="col-md-6 col-lg-5 wow bounceInUp" data-wow-duration="1.4s">
            <div class="box">
              <div class="icon"><i class="ion-ios-bookmarks-outline" style="color: #e9bf06;"></i></div>
              <h4 class="title">Avaliações</h4>
              <p class="description" style="color:black;">Todo final de serviço, ambos terão que avaliar a qualidade do serviço ou a hospitalidade do cliente</p>
            </div>
          </div>     

          <div class="col-md-6 col-lg-5 offset-lg-1 wow bounceInUp" data-wow-delay="0.2s" data-wow-duration="1.4s">
            <div class="box">
              <div class="icon"><i class="ion-ios-world-outline" style="color: #d6ff22;"></i></div>
              <h4 class="title">Area de serviço</h4>
              <p class="description" style="color:black;">Para os clientes que desejarem um serviço, será apresentado como recomendado so mais próximos dele, caso não encontre o que deseja, poderá usar a barra de pesquisa</p>
            </div>
          </div>
          <div class="col-md-6 col-lg-5 wow bounceInUp" data-wow-delay="0.2s" data-wow-duration="1.4s">
            <div class="box">
              <div class="icon"><i class="ion-ios-clock-outline" style="color: #4680ff;"></i></div>
              <h4 class="title">Rápido</h4>
              <p class="description" style="color:black;">Encontrar ou publicar um serviço é muito simples e rápido, após encontrar um serviço, caso ambos estejam de acordo, poderão agendar ou começar o serviço desejado</p>
            </div>
          </div>

        </div>

      </div>
    </section><!-- #services -->
      
   
    <!--==========================
      Package Section
    ============================-->

       <section id="clients" class="section-bg">

      <div class="container">

        <div class="section-header">
          <h3>Pacotes!</h3>
          <p>Escolha dentre os 4 pacotes que oferemos!</p>
            <p>Sinta-se livre para escolher o que for se encaixar mais em seu cotidiano!</p>
        </div>

        <div class="row no-gutters clients-wrap clearfix wow fadeInUp">

          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo">
              <img src="img/pacotes/basico.jpg" class="img-fluid" alt="" title="Melhor pacote para novos usuários, confere ao trabalhador poder realizar até 2 serviços por vez"/>
            </div>
          </div>
          
          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo">
              <img src="img/pacotes/iniciante.jpg" class="img-fluid" alt="" title="Pacote ideal para usuários que possuem trabalhos intermitentes, confere ao mesmo poder realizar até 4 serviços por vez"/>
            </div>
          </div>
        
          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo">
              <img src="img/pacotes/veterano.jpg" class="img-fluid" alt=""  title="Um dos melhores planos certos para quem quer alavancar seus negócios independentes, dá ao usuário um limite de 6 serviços por vez"/>
            </div>
          </div>
          
          <div class="col-lg-3 col-md-4 col-xs-6">
            <div class="client-logo" >
              <img src="img/pacotes/mestre.jpg" class="img-fluid" alt="" title="Para os melhores trabalhadores, estes que dedicam suas vidas aos seus trabalhos, tenha um número ilimitado de serviços"/>
            </div>
          </div>             

     
        </div>

      </div>

    </section> <!-- Package -->

  <!--==========================
    Footer
  ============================-->
  
        <footer id="footer">
    <div class="footer-top">
      <div class="container">
        <div class="row">

          <div class="col-lg-6 col-md-6 footer-info">
            <h3>QuickJobs</h3>
            <p style="color:white">O QuickJobs é um aplicativo para pessoas com dificuldade de encontrar serviços simples para soluções simples e também para pessoas que querem propor seus serviços sem ter que gastar dinheiro com panfletos e perdendo tempo se cadastrando em diversos sites diferentes
                sem ter que preocupar em perder ligações ou olhar seu e-mail o tempo todo, a solução estará na palma dua sua mão.
            </p>
          </div>

         

          <div class="col-lg-6 col-md-6 footer-contact">
            <h4>Contato</h4>
            <p style="color:white">
             ETEP, Av. Barão do Rio Branco, 882
             <br />
             Jardim Esplanada, São José dos Campos, São Paulo
              
              <strong style="color:white">Telefone:</strong> +55 (12)98211-9313<br>
              <strong style="color:white">Email:</strong> QuickJobsContato@gmail.com<br>
            </p>
             
            <div class="social-links">
              <a href="#" class="twitter"><i class="fa fa-twitter"></i></a>
              <a href="#" class="facebook"><i class="fa fa-facebook"></i></a>
              <a href="#" class="instagram"><i class="fa fa-instagram"></i></a>
              <a href="#" class="google-plus"><i class="fa fa-google-plus"></i></a>
              <a href="#" class="linkedin"><i class="fa fa-linkedin"></i></a>
            </div>

          </div>

        

        </div>
          </div>
        </div>
  

    
  </footer><!-- #footer -->




  <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
  <!-- Uncomment below i you want to use a preloader -->
  <!-- <div id="preloader"></div> -->

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
