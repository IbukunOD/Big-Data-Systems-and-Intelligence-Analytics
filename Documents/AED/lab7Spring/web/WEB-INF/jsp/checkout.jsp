<%-- 
    Document   : checkout
    Created on : Feb 5, 2018, 3:45:26 PM
    Author     : shard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut</title>
    </head>
    <body>
        <jsp:include page = "menu.jsp"></jsp:include>
        <%
           float total = (float)request.getAttribute("total");
        %>
        <h1>sum:  $<%=total%></h1>
    </body>
</html>
