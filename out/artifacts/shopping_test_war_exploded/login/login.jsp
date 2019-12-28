<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Login...</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/login/jq/jquery-3.3.1.js"></script>
    <script rel="script" type="application/javascript" src="login.js"></script>
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }

        body {
            background-image: url("image/loginbg.png");
            background-repeat: repeat;
        }

        #login {
            margin: 50px auto;
            height: 300px;
            width: 300px;
            box-shadow: #666666 12px 4px 10px, #666666 -2px -2px 10px;
            border-radius: 10px;
            text-align: center;
        }

        #line {
            background-image: url("image/line.png");
            background-repeat: repeat;
            height: 2px;
            box-shadow: #666666 -2px -2px 20px;
        }

        input {
            margin-left: 5px;
            margin-top: 15px;
            height: 22px;
            width: 200px;
        }

        button {
            margin: 30px 10px;
            line-height: 20px;
            width: 60px;
            height: 26px;
            background-color: #ff9710;
            border: none;
            border-radius: 2px;
            cursor: pointer;
        }

        #codeid {
            margin-left: 12px;
            margin-top: 15px;
            height: 22px;
            width: 140px;
        }

        #codeimage {
            margin: 15px 5px;
            height: 22px;
            width: 100px;
            cursor: pointer;
        }

        a {
            display: block;
            margin-left: 170px;
            margin-top: 100px;
            padding-top: 30px;
            padding-bottom: 0px;
        }
    </style>
</head>
<body onload="creat()">
<div id="login">
    <form action="/yamaxun/user/userServlet.do" method="post">
        <input type="hidden" name="methods" value="login">
        <a href="regi.jsp">注册</a><br>
        <span>用户名：</span> <input type="text" id="name" name="user_name">
        <span>密&nbsp;码：</span> <input type="password" id="pwd" name="regist_pwd">
        <span>验证码：</span><input type="text" id="codeid" name="code"><span id="codeimage" onclick="creat()"></span>
        <button type="submit">登&nbsp;&nbsp;录</button>
        <button type="reset">取&nbsp;&nbsp;消</button>
    </form>
</div>
<div id="line"></div>
</body>
<script>
    var code;

    function creat() {
        code = "";
        const codelength = 6;
        var codeid = document.getElementById("codeimage");
        var arrycode = ['a', 'b', 'c', 'B', '5', '9', 'o', 't', 'P', 'g', 'W', 'z'];
        for (var i = 0; i < codelength; i++) {
            var charnum = Math.floor(Math.random() * 12);
            code += arrycode[charnum];
        }
        if (codeid) {
            codeid.innerHTML = code;
        }
    }
</script>
<script type="text/javascript">
</script>
</html>