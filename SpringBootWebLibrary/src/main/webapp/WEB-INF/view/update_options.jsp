<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Input options of command Update</title>
        <h2>Input Options of Update command</h2>
    </head>
    <body>
        <spring:form action="${pageContext.request.contextPath}/command/update" commandName="options" method="post">
            <table>
                <tr>
                    <td>id</td>
                    <td><spring:input path="id"/></td>
                    <td><spring:errors path="id"/></td>
                </tr>
                <tr>
                    <td>author</td>
                    <td><spring:input path="author"/></td>
                    <td><spring:errors path="author"/></td>
                </tr>
                <tr>
                    <td>title</td>
                    <td><spring:input path="title"/></td>
                    <td><spring:errors path="title"/></td>
                </tr>
                <tr>
                    <td>year</td>
                    <td><spring:input path="year"/></td>
                    <td><spring:errors path="year"/></td>
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
