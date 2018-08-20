<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 14.08.2018
  Time: 4:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>StartPage</title>
        <H1>Welcome</H1>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/authorization" method="post">
            <PRE>
                login:    <input name="login" type="text">
                password: <input name="password" type="text">
                <input type="submit" name="submitPressed" value="confirm">
            </PRE>
        </form>
    </body>
</html>
