<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <%
        String url = request.getContextPath();
    %>

    <a href="<%=url%>/options/add">add</a>
    <a href="<%=url%>/options/delete">delete</a>
    <a href="<%=url%>/options/search">search</a>
    <a href="<%=url%>/options/update">update</a>
    <a href="<%=url%>/command/list">list</a>

    ${message}
</body>
</html>
