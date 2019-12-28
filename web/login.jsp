<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Shinning - 登录页面</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jq/jquery-3.3.1.js"></script>
</head>
<body>
<%@ include file="index_top.jsp" %>
<div id="register" class="wrap">
    <div class="shadow">
        <em class="corner lb"></em>
        <em class="corner rt"></em>
        <div class="box">
            <h1>欢迎登录 Shinning</h1>
            <form id="loginForm" action="${pageContext.request.contextPath }/user/loginregist.do"
                  onsubmit="return loginCheck()">
                <input type="hidden" name="method" value="login"/>
                <table>
                    <tr>
                        <td class="field">用户名：</td>
                        <td><input class="text" type="text" name="userName" onfocus="FocusItem(this)"
                                   onblur="CheckItem(this);"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">密&nbsp;码：</td>
                        <td><input class="text" type="password" id="passWord" name="passWord" onfocus="FocusItem(this)"
                                   onblur="CheckItem(this);"/><%--<a href="retrieve_password.jsp">忘记密码</a></td>--%>
                    </tr>
                    <tr>
                        <td class="field">验证码：</td>
                        <td><input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)"
                                   onblur="CheckItem(this);" maxlength="4"/><img id="veryCode" src="code.jsp"/><span
                                id="Code"></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-green"><input type="submit" name="submit" value="确&nbsp;定"/></label></td>
                        <td><input type="checkbox" id="remenber" name="remenber"/>
                            <p style="position: absolute;left: 310px;top: 230px">记住密码</p>
                        </td>
                    </tr>
                </table>
            </form>
            <a href="retrieve_password.jsp">找回密码?</a>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    Copyright &copy; Shinning Company All Rights Reserved.
</div>
</body>
<script>
    $("#remenber").blur(function () {
        var pass = $("#remenber").prop("checked");
        if (pass) {
            console.log(123);
            $("#remenber").attr("value", "1");
        } else {
            console.log(456);
            $("#remenber").attr("value", "0");
        }
    })
</script>
</html>
