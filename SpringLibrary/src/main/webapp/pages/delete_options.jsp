<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Input options of command Delete</title>
        <h2>Input Options of Delete command</h2>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/command/delete" method="post">
            <table>
                <tr>
                    <td>id</td>
                    <td><input name="id" value="<%if (request.getParameter("id") != null)%><%=request.getParameter("id")%>" type="text"></td>
                    <td><i id="id"></i></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="confirm" id="button1"></td>
                </tr>
            </table>
            ${message}
            <p>
            <a href="<%=request.getContextPath()%>/home">Home Page</a>
        </form>
    </body>
</html>
