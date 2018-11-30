<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>

</head>
<body>
<form method="post" action="/login.do">
    <p>user:<input type="text" name ="user" value="${ userInfo.name }" /></p>
    <p>password:<input type="password" name="password" />${ userInfo.password }</p>
    <input type="submit" value="login"/>
</form>
</body>
</html>