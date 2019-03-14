<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Input options of command Delete</title>
        <h2>Input Options of Delete command</h2>
    </head>
    <body>
        <spring:form action="${pageContext.request.contextPath}/command/delete" commandName="options" method="post">
            <table>
                <tr>
                    <td>id</td>
                    <td><spring:input path="id"/></td>
                    <td><spring:errors path="id"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="confirm" id="button1"></td>
                </tr>
            </table>
            <p>
            <a href="<%=request.getContextPath()%>/home">Home Page</a>
        </spring:form>
    </body>
</html>
