<%-- 
    Document   : Sesiones
    Created on : 15-jun-2021, 23:06:56
    Author     : USER
--%>

<%@page import="Modelo.UsuarioVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    
  response.setHeader("Pragma", "No-cache");
  response.setHeader("Cache-control", "no-cache,no-store,must-revalidate"); 
  response.setDateHeader("Expires", 0);
    
%>

<%
HttpSession buscarSesion = (HttpSession)request.getSession();
String usuario="";

if (buscarSesion.getAttribute("datosUsuario")==null) {
    request.getRequestDispatcher("index.jsp").forward(request, response);
        
    }else{
    UsuarioVO usuVO = (UsuarioVO)buscarSesion.getAttribute("datosUsuario");
    usuario = usuVO.getUsuLogin();
}
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sesiones</title>
    </head>
    <body>
        <div style="float: right;">
        <h1 style="color: red; ">Bienvenido :<%=usuario%></h1>
        <form method="post" action="Sesiones1">
            <input type="submit" value="Cerrar Sesion">
            
        </form>
        </div><br><br>
        <div></div><br><br>
        <div></div><br><br>
        
    </body>
</html>
