<%@ page language="java" contentType="text/html; charset=utf-8" %>
<@% page import="" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>

</head>
<body>
<form method="post" action="/login.do">
    <p>user:<input type="text" name="user" value="${ userInfo.name }"/>
    <p>password:<input type="password" name="password"/>${ userInfo.password }
    <input type="submit" value="login"/>
</form>
</body>
</html>