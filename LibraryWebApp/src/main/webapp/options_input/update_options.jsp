<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Input options of command Update</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/librarian_request" method="post">
            <input name="command" value="<%=request.getParameter("command")%>" type="text" hidden>
            <table>
                <tr>
                    <td>id</td>
                    <td><input name="id" type="text"></td>
                </tr>
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
        </form>
    </body>
</html>
