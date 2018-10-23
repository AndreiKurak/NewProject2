<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Input options of command Search</title>
        <h2>Input Options of Search command</h2>
        <script type="text/javascript" src="options_input/validator.js"></script>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/search.do" method="post">
            <table>
                <tr>
                    <td>author</td>
                    <td><input name="author" type="text" onchange="validateChain(validateString(name, value))"></td>
                    <td><i id="author"></i></td>
                </tr>
                <tr>
                    <td>title</td>
                    <td><input name="title" type="text" onchange="validateChain(validateString(name, value))"></td>
                    <td><i id="title"></i></td>
                </tr>
                <tr>
                    <td>year</td>
                    <td><input name="year" type="text"
                               onchange="validateChain(validateDate(name, value), validateNumber(name, value))"></td>
                    <td><i id="year"></i></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="confirm" id="button1" disabled></td>
                </tr>
                <tr>
                    <td><a href="<%=request.getContextPath()%>/home">Home Page</a></td>
                </tr>
            </table>
        </form>
    </body>
</html>
