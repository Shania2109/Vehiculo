<%-- 
    Document   : registrarVehiculo
    Created on : 14-jun-2021, 20:39:20
    Author     : USER
--%>

<%@page import="Modelo.CategoriaVO"%>
<%@page import="ModeloDAO.CategoriaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Sesiones.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Vehiculo</title>
    </head>
    <body>
    <center>
        <h1>Registrar Vehiculo</h1>
        <form method="post" action="Vehiculo1">
            <table>
                <tr>
                    <th>
                        PLACA<br>
                        <input type="text" name="textPlaca"><br><br>
                        DATOS<br>
                        <input type="text" name="textDatos"><br><br>
                        CATEGORIA<br>
                        <select name="textCategoria">
                            <option>Seleccione...</option>
                            <% 
                            CategoriaDAO catDAO = new CategoriaDAO();
                            for (CategoriaVO catVO : catDAO.listar()) {
                                %>
                                <option value="<%=catVO.getCatId()%>"><%=catVO.getCatTipo()%></option>
                                <%}%> 
                            
                        </select><br><br>
                        
                        MODELO<BR>
                        <input type="text" name="textModelo"><br><br>
                        MARCA<br>
                        <input type="text" name="textMarca"><br><br>
                        ESTADO<br>
                        <input type="text" name="textEstado"><br><br>
                        PRECIO<br>
                        <input type="text" name="textPrecio"><br><br>
                    </th>
                </tr>
            </table>
            <button>Registrar</button>
            <input type="hidden" value="1" name="opcion">    
        </form><br> <br>
         <%
               if (request.getAttribute("mensajeError") != null) { %>
            ${mensajeError} 

            <%}else{%>
            <div style="color: darkgreen">${mensajeExito} </div>

            <% }%>
        <center>
    </body>
</html>