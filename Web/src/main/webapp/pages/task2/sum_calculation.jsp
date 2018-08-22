<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 14.08.2018
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Task2</title>
    </head>
        <body>
            Sum of two numbers:
            <%
                int value1 = Integer.valueOf(request.getParameter("value1"));
                int value2 = Integer.valueOf(request.getParameter("value2"));
            %>
            <%= value1 + value2 %>
        </body>
</html>
