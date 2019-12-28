<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Shinning - 注册</title>
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
            <h1>欢迎注册 Shinning</h1>
            <form>
                <table>
                    <tr>
                        <td class="field">用户名：</td>
                        <td><input id="userName" class="text" type="text" name="userName"/><span id="uName"></span></td>
                    </tr>
                    <tr>
                        <td class="field">用户真实名：</td>
                        <td><input id="rName" class="text" type="text" name="rName"/><span id="rlName"></span></td>
                    </tr>
                    <tr>
                        <td class="field">密&nbsp;码：</td>
                        <td><input id="passWord" class="text" type="password" name="passWord"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">确认密码：</td>
                        <td><input id="rePassWord" class="text" type="password" name="rePassWord"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">性&nbsp;别：</td>
                        <td><input type="radio" name="sex" style="border:0px;" checked="checked" value="m"/>男&nbsp;&nbsp;&nbsp;&nbsp;<input
                                type="radio" name="sex" style="border:0px;" value="fm"/>女<span></span></td>
                    </tr>


                    <tr>
                        <td class="field">邮&nbsp;箱：</td>
                        <td><input id="emailinput" class="text" type="text" name="email"
                                   onblur="CheckItem(this);"/><span id="uemail"></span></td>
                    </tr>
                    <tr>
                        <td class="field">联系电话：</td>
                        <td><input id="phoneinput" class="text" type="text" name="mobile"
                                   onblur="CheckItem(this);"/><span id="uphone"></span></td>
                    </tr>

                    <tr>
                        <td class="field">验证码：</td>
                        <td><input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)"
                                   onblur="CheckItem(this);"/><img id="veryCode"
                                                                   src="code.jsp"/><span
                                id="Code"></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-green"><input type="button" name="submit" value="提&nbsp;交"
                                                           onclick="getvalue()"/></label>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    Copyright &copy; Shinning Company All Rights Reserved.
</div>
<script type="text/javascript">

    function getvalue() {
        var user_name = $("#userName").val();
        var rname = $("#rName").val();

        var gender = $('input[name="sex"]:checked').val();
        var regist_pwd = $("#passWord").val();
        var confirm_pwd = $("#rePassWord").val();
        var phone = $("#phoneinput").val();
        var email = $("#emailinput").val();

        $.post("/shopping_test/yamaxun/user/loginregist.do",
            {
                "method": "regist",
                "user_name": user_name,
                "rName": rname,
                "gender": gender,
                "regist_pwd": regist_pwd,
                "confirm_pwd": confirm_pwd,
                "phone": phone,
                "email": email

            }, function (data) {
                if (data == 1) {
                    window.location.href = "/shopping_test/reg-result.jsp";
                } else {
                    alert("操作失败！！！");
                    window.location.href = "/shopping_test/register.jsp";
                }
            })

    }

</script>
</body>
</html>

