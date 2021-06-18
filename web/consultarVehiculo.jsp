<%-- 
    Document   : consultarVehiculo
    Created on : 14-jun-2021, 22:16:29
    Author     : USER
--%>

<%@page import="ModeloDAO.VehiculoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.VehiculoVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Sesiones.jsp" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Vehiculo</title>
    </head>
    <body>
    <center>
        <h1>Vehiculos</h1>

        <form method="post" action="Vehiculo1">

            <table>
                <tr>
                    <th>
                        PLACA:
                        <input type="text" name="textPlaca"> <button>Consultar</button>
                    </th>
                </tr>
            </table><br>

            <input type="hidden" value="3" name="opcion">
        </form><br>
        <div style="color: red;">
            <%if (request.getAttribute("mensajeError") != null) {%>
            ${mensajeError}
            <%} else {%>
            <div style="color: darkgreen;">${mensajeExito}</div>
            <%}%>
        </div><br>
        <form>
            <table border="1">
                <tr>
                    <th>PLACA</th>
                    <th>DATOS</th>
                    <th>CATEGORIA</th>
                    <th>MODELO</th>
                    <th>MARCA</th>
                    <th>ESTADO</th>
                    <th>PRECIO</th>
                </tr>

                <%
                    VehiculoVO vehVO = new VehiculoVO();
                    VehiculoDAO vehDAO = new VehiculoDAO();
                    
                    ArrayList<VehiculoVO> listaVehiculos = vehDAO.listar();
                    for (int i = 0; i < listaVehiculos.size(); i++) {

                        vehVO = listaVehiculos.get(i);
                %>

                <tr>
                    <td><%=vehVO.getVehPlaca()%></td>
                    <td><%=vehVO.getDatId()%></td>
                    <td><%=vehVO.getCatId()%></td>
                    <td><%=vehVO.getVehModelo()%></td>
                    <td><%=vehVO.getVehMarca()%></td>
                    <td><%=vehVO.getVehEstado()%></td>
                    <td><%=vehVO.getVehPrecio()%></td>

                </tr>
                
                <%
                }
                %>
            </table>
        </form>


    </center>
</body>
</html>

