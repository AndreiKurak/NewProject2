<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="common.ViewModel" %>
<%@ page import="framework_web.WebPageOutput" %><%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 29.08.2018
  Time: 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>List</title>
        <H1>List of books</H1>
    </head>
    <body>
        <%= request.getAttribute("view") %>
    </body>
</html>
