<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Input options of command Delete</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/showCommand/delete" method="post">
            <table>
                <tr>
                    <td>id</td>
                    <td><input name="id" type="text"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="confirm"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
