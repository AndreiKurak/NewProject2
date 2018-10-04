<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Test</title>
    </head>
    <body>
    <form action="${pageContext.request.contextPath}/librarian_request" method="post">
            <pre>
                <%
                    String values = request.getParameterValues("selection")[0];
                    String command = values.substring(0, values.indexOf(" "));
                    String[] options = values.replace(command, "").trim().split(" ");
                %>
                command <input name="<%=command%>" value="<%=command%>" type="text" readonly>
                <%
                    for (String option : options) {
                %>
                <%=option%>  <input name="<%=option%>" type="text">
                <%
                    }
                %>
                <input type="submit" value="confirm">
            </pre>
        </form>
    </body>
</html>
