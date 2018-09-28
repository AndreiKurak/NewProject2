<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>InputLibrarianRequestPage</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/librarian_request" method="post">
            <PRE>
                action: <select name="selection">
                            <option selected>add</option>
                            <option>delete</option>
                            <option>update</option>
                            <option>list</option>
                            <option>search</option>
                        </select>
                author <input name="author" type="text">
                title  <input name="title" type="text">
                year   <input name="year" type="text">
                <input type="submit" value="confirm">
            </PRE>
        </form>
    </body>
</html>
