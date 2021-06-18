<%-- 
    Document   : RegistrarUsuario
    Created on : 11-may-2021, 21:28:35
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Usuario</title>
    </head>
    <body>
    <center>
        <h1>Registrar Usuario</h1>
        <form method="post" action="Usuario">
            <table>
                <tr>
                    Usuario <br>
                <input type="text" name="textUsuario"><br>
                Contraseña <br>
                <input type="password" name="textClave"><br>
                </tr>
            </table><br>
            <button>Registrar</button>
            <input type="hidden" value="1" name="opcion">
        </form>

        <div style="color: red;">

            <%
               if (request.getAttribute("mensajeError") != null) { %>
            ${mensajeError} 

            <%}else{%>
            <div style="color: darkgreen">${mensajeExito} </div>

            <%}%>
        </div>

    </center>
</body>
</html>