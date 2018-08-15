<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 14.08.2018
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Task3-1</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/task3_2" method="post">
            <PRE>
                First Value: <input name="value1" type="text">
                Second Value: <input name="value2" type="text">
                <input type="submit" name="sumGetter" value="get sum">
            </PRE>
        </form>
    </body>
</html>
