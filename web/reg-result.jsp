<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Shinning - Regist result</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<div id="register" class="wrap">
    <div class="shadow">
        <em class="corner lb"></em>
        <em class="corner rt"></em>
        <div class="box">
            <h1>Welcome to shinning</h1>
            <ul class="steps clearfix">
                <li class="finished"><em></em> Fill in the Registration information</li>
                <li class="last-current"><em></em>Registered successfully</li>
            </ul>
            <div class="msg">
                <p>Congratulation：successfully！</p>
                <p>Sign in...</p>
                <script type="text/javascript">
                    setTimeout("location.href='index.jsp'", 3000);
                </script>
            </div>
        </div>
    </div>
</div>
<div id="footer">
    Copyright &copy; Shinning company All Rights Reserved.
</div>
</body>
</html>
