<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>List</title>
        <H2>List of books</H2>
    </head>
    <body>
        <%= request.getAttribute("view") %>
        <p>
            <a href=<%=request.getContextPath()%>>Start Page</a>
        </p>
    </body>
</html>
