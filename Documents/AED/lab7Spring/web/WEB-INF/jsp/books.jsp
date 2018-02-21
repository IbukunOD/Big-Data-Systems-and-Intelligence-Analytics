
<jsp:directive.page import="Bean.Product" />
<jsp:directive.page import="java.util.ArrayList"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Available Books are:</title>
    </head>
    <body>
        <form method='post' action='books.htm'>
            <jsp:include page = "menu.jsp"></jsp:include>
            <h2> Results found </h2>
            <%
              ArrayList<Product> results = (ArrayList<Product>) request.getAttribute("searchResults");
        
              for(Product p : results){
                  if(p.getCategory().equals("Books")){
                  out.println("<input type='checkbox' name='id' value='" + p.getId()+ "' />" + p.getName() + " Price " + p.getPrice() + " [<a href='options.htm?action=add&id=" + p.getId()+ "'>Add product</a>]<br />");
                  }
                  }
            %>
            <input type='hidden' name='action' value='add'/>
            <input type='submit' value='Add selected items'>
        </form>
    </body>
</html>
