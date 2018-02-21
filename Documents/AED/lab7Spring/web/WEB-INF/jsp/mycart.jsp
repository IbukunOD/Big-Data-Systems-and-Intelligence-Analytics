<%@page import="java.util.HashSet"%>
<%@page import="Bean.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course View</title>
    </head>
    <body>
        <jsp:include page = "menu.jsp"></jsp:include>
        <form method='post' action='options.htm'>
        <h3> Products Added:</h3>
        <!--if there are no course in the set-->
        <%
            HashSet<Product> myProductSet = (HashSet<Product>) session.getAttribute("myProducts");
            if (myProductSet == null) {
                out.println("<div><p> No Course Added for you.</p></div>");
            } else {
                //else if there are courses, display every course in the set
                for (Product p : myProductSet) {
                    out.println("Product Name: " + p.getName() + "Price: " + p.getPrice() +"[<a href='options.htm?action=remove&id=" + p.getId()+ "'>Remove Course</a>]<br />");
                }
            }
        %>
        <input type="hidden" name="action" value="checkout">
        <br><input type="submit" value=Checkout>
        </form>
    </body>
</html>
