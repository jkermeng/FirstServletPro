<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="/DwebPro/login/jq/jquery-3.3.1.js"></script>
    <script rel="script" type="application/javascript" src="login.js"></script>
</head>
<body>
<div>
    <form>
        <input id="hid" type="hidden" name="methods" value="regist">
        <label>UserName：</label><input id="nameinput" type="text" name="user_name"><span id="name_msg"></span><br>
        <label>Gender：</label><input type="radio" name="gender" value="male" checked="checked"><label>Male</label>
        <input type="radio" name="gender" value="female"><label>Female</label><br>
        <label>Regist_pwd：</label><input id="regist_pwdinput" type="password" name="regist_pwd"><span
            id="regist_pwd_msg"></span><br>
        <label>Confirm_pwd：</label> <input id="confirm_pwdinput" type="password" name="confirm_pwd"><span
            id="confirm_pwd_msg"></span><br>
        <select name="sheng" id="crownland" onchange="setcity(this,document.getElementById('city'))">
            <option value=0 selected>Province</option>
            <option value=台湾>台湾</option>
            <option value=北京>北京</option>
            <option value=上海>上海</option>
            <option value=天津>天津</option>
            <option value=重庆>重庆</option>
            <option value=河北省>河北省</option>
            <option value=山西省>山西省</option>
            <option value=辽宁省>辽宁省</option>
            <option value=吉林省>吉林省</option>
            <option value=黑龙江省>黑龙江省</option>
            <option value=江苏省>江苏省</option>
            <option value=浙江省>浙江省</option>
            <option value=安徽省>安徽省</option>
            <option value=福建省>福建省</option>
            <option value=江西省>江西省</option>
            <option value=山东省>山东省</option>
            <option value=河南省>河南省</option>
            <option value=湖北省>湖北省</option>
            <option value=湖南省>湖南省</option>
            <option value=广东省>广东省</option>
            <option value=海南省>海南省</option>
            <option value=四川省>四川省</option>
            <option value=贵州省>贵州省</option>
            <option value=云南省>云南省</option>
            <option value=陕西省>陕西省</option>
            <option value=甘肃省>甘肃省</option>
            <option value=青海省>青海省</option>
            <option value=内蒙古>内蒙古</option>
            <option value=广西>广西</option>
            <option value=西藏>西藏</option>
            <option value=宁夏>宁夏</option>
            <option value=新疆>新疆</option>
            <option value=香港>香港</option>
            <option value=澳门>澳门</option>
        </select>
        <select id="city">
            <option value=0 selected>City</option>
        </select><br>
        <label>Phone：</label><input id="phoneinput" type="text" id="regiphone"
                                    onkeyup="this.value=this.value.replace(/[^\d]/ig,'')">
        <label>E_mail：</label><input id="emailinput" type="text" id="regiemail">
        <button type="button" onclick="getvalue()">Submit</button>
        <button type="reset">Reset</button>
    </form>
</div>
<script type="text/javascript">

    function getvalue() {
        var methods = $("#hid").val();
        var user_name = $("#nameinput").val();
        var gender = $('input[name="gender"]:checked').val();
        var regist_pwd = $("#regist_pwdinput").val();
        var confirm_pwd = $("#confirm_pwdinput").val();
        var phone = $("#phoneinput").val();
        var email = $("#emailinput").val();
        var province = $("#crownland option:selected").val();
        var city = $("#city option:selected").val();

        var user = {
            "methods": methods,
            "user_name": user_name,
            "gender": gender,
            "regist_pwd": regist_pwd,
            "confirm_pwd": confirm_pwd,
            "phone": phone,
            "email": email,
            "province": province,
            "city": city
        }

        $.post("/DwebPro/user/userServlet.do", user, function (data) {
            if (data == 1) {
                window.location.href = "/DwebPro/login/newalert.html";
            }
        })

    }

</script>
</body>

</html>