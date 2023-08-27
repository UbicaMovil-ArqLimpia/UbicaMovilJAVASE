<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ubicamovil.appweb.utils.*"%>
<!--Texto en nav en pantalla de escritorio-->
<nav class="red">
    <div class="nav-wrapper container">
        <a href="Home" class="brand-logo">UbicaMovil</a>
        <a href="Anuncio" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>       
        <ul class="right hide-on-med-and-down"> 
            <li><a href="Home">Inicio</a></li>
            <li><a href="Empresa">Empresas</a></li>
            <li><a href="Categoria">Categorias</a></li>
            <li><a href="Anuncio">Anuncios</a></li>
        </ul>
    </div>
</nav>
<!--Texto en el nav pantalla movil-->
<ul class="sidenav red" id="mobile-demo">
    <li><a href="Home">Inicio</a></li>
    <li><a href="Empresa">Empresas</a></li>
    <li><a href="Categoria">Categorias</a></li>
    <li><a href="Anuncio">Anuncios</a></li>
</ul>