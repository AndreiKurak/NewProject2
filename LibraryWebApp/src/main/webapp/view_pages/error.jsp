<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error</title>
        <H2>Error during performance</H2>
    </head>
    <body>
        <%= request.getAttribute("view") %>
        <p>
            <a href="/library/pages/start_page.jsp">Start Page</a>
        </p>
    </body>
</html>
