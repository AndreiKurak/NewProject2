<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error</title>
        <H2>Error during performance</H2>
    </head>
    <body>
        <%= request.getSession().getAttribute("view") %>
        <%request.getSession().removeAttribute("view");%>
        <p>
            <a href="<%=request.getContextPath()%>/home">Home Page</a>
        </p>
    </body>
</html>
