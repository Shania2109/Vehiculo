<%-- 
    Document   : menu
    Created on : 13-jun-2021, 0:32:12
    Author     : USER
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Sesiones.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menú</title>
        <script src="Validaciones.js" type="text/javascript"></script>
    </head>
    
    <form>
        
 ROLES: 
        <select onchange="redireccion(value)">
            <option>Seleccione...</option>
            <%
                
            UsuarioVO usuVO = new UsuarioVO();
            ArrayList<UsuarioVO> listaRoles = (ArrayList<UsuarioVO>) buscarSesion.getAttribute("rol");
            for (int i = 0; i < listaRoles.size(); i++) {
                
                 usuVO = listaRoles.get(i);
            }
            if(usuVO.getRol().equals("Comprador")){
            buscarSesion.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response);
            

            %>
            
            <option value="<%=usuVO.getRol()%>"><%=usuVO.getRol()%></option>
            
            <%}%>
            
     
            
        </select>
        
    </form>
    
    <body><center>
        <h1>Menú</h1>
        </center>
    </body>
</html>
