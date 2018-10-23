<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Input options of command Delete</title>
        <h2>Input Options of Delete command</h2>
        <script type="text/javascript" src="options_input/validator.js"></script>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/delete.do" method="post">
            <table>
                <tr>
                    <td>id</td>
                    <td><input name="id" type="text" onchange="validateChain(validateNumber(name, value))" required></td>
                    <td><i id="id"></i></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="confirm" id="button1" disabled></td>
                </tr>
            </table>
            <a href="<%=request.getContextPath()%>/home">Home Page</a>
        </form>
    </body>
</html>
