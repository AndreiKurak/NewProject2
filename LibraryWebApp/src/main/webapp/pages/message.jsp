<%@ page import="common.ViewModel" %>
<%@ page import="framework_web.WebPageOutput" %><%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 28.08.2018
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Message</title>
        <H1>Message about operation execution</H1>
    </head>
    <body>
        <%= request.getAttribute("view") %>
    </body>
</html>
