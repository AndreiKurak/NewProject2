<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 26.08.2018
  Time: 3:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>InputLibrarianRequestPage</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/librarian_request" method="post">
            input your request: <input name="inputQuery" type="text">
            <input type="submit" name="submitPressed" value="confirm">
        </form>
    </body>
</html>
