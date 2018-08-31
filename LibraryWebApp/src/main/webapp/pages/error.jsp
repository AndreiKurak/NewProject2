<%@ page import="framework_web.WebPageOutput" %>
<%@ page import="common.ViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 29.08.2018
  Time: 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error</title>
        <H1>Error during performance</H1>
    </head>
    <body>
        <%= request.getAttribute("view") %>
    </body>
</html>
