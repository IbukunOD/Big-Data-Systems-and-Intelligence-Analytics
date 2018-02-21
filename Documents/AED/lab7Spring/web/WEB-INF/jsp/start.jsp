<!--By default this is true-->
<!--Even if this line is removed, by default the session will continue.-->
<!--Session is an implicit object in JSP-->

<!-- // jsp can use session objects directly But if u set it to false, 
then create the session object first and then only u can access all the session methods-->
<%@ page session="true" %> 
<% 
String user = (String) session.getAttribute("username");
if(user != null){
    response.sendRedirect("searchCourse.jsp");
}
%>
<!-- add cookie code here.. after logout, if you remove it from the cookie, the user will always stay logged in. -->

<!DOCTYPE html>
<html>
    <head>
        <title>NEU Courses</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
    </head>
    <body>
        <div>
            <h1>Start Shopping here!</h1>
            <form name='myForm' method="post" action="">
                <jsp:include page = "menu.jsp"></jsp:include>
                <br />
                <br />                 
                
            </form>
        </div>
    </body>
</html>
