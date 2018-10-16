<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Input options of command Add</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/showCommand/add" method="post">
            <table>
                <tr>
                    <td>author</td>
                    <td><input name="author" type="text"></td>
                </tr>
                <tr>
                    <td>title</td>
                    <td><input name="title" type="text"></td>
                </tr>
                <tr>
                    <td>year</td>
                    <td><input name="year" type="text"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="confirm"></td>
                </tr>
            </table>
            <%
                if (request.getAttribute("view") != null) {
            %>
                    <%=request.getAttribute("view")%>
            <%
                }
            %>
        </form>
    </body>
</html>
