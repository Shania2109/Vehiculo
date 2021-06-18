<%-- 
    Document   : actualizarVehiculo
    Created on : 15-jun-2021, 16:02:35
    Author     : USER
--%>

<%@page import="Modelo.VehiculoVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Sesiones.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>Actualizar Vehiculo</h1>

        <%
            VehiculoVO vehVO = (VehiculoVO) request.getAttribute("vehiculoConsultado");
            if (vehVO != null) {
        %>
        <form method="post" action="Vehiculo1">
            <table>
                <tr>
                    <th>
                        PLACA<br>
                        <input type="text" name="textPlaca" value="<%=vehVO.getVehPlaca()%>"><br><br>
                        DATOS<br>
                        <input type="text" name="textDatos" value="<%=vehVO.getDatId()%>"><br><br>
                        CATEGORIA<br>
                        <input type="text" name="textCategoria" value="<%=vehVO.getCatId()%>"><br><br>
                        MODELO<BR>
                        <input type="text" name="textModelo" value="<%=vehVO.getVehModelo()%>"><br><br>
                        MARCA<br>
                        <input type="text" name="textMarca" value="<%=vehVO.getVehMarca()%>"><br><br>
                        ESTADO<br>
                        <input type="text" name="textEstado" value="<%=vehVO.getVehEstado()%>"><br><br>
                        PRECIO<br>
                        <input type="text" name="textPrecio" value="<%=vehVO.getVehPrecio()%>"><br><br>
                    </th>
                </tr>
            </table>
            <button>Actualizar</button>
            <input type="hidden" value="2" name="opcion">    
        </form><br><br>

        
        <%}%>
        <a href="consultarVehiculo.jsp">Volver</a>
        <div style="color: red;">
            <% if (request.getAttribute("mensajeError") != null) {%>
            ${mensajeError}
            <%} else {%>
            <div style="color: darkgreen;">${mensajeExito}</div>
            <%}%>
            
        </div>

    </center>
</body>
</html>
