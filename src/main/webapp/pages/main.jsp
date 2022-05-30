<%--
  Created by IntelliJ IDEA.
  User: super
  Date: 5/25/2022
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
Hello(forward) = ${user}
<hr/>
Hi (Redirect/Forward) = ${user_name}
<hr/>
${filter_attr}
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logOut">
</form>
</body>
</html>
