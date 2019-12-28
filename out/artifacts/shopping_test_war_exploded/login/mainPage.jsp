<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        * {
            margin: 0px;
            padding: 0px;
        }

        body {
            border-image-repeat: repeat;
            background-image: url("image/mainbg1.png");
        }

        #out {
            width: 800px;
            border: 1px solid black;
            margin: 0px auto;
            background-image: url("image/mainbg.png");
        }

        #in1 {
            margin-top: 20px;
            width: 100%;
            height: 50px;
            background-color: #666666;
            background-image: url("image/mainbg.png");
        }

        #in2 {
            width: 100%;
            height: 45px;
            background-color: #666666;
            text-align: right;
            border-bottom: 2px solid white;
            background-image: url("image/mainbg.png");
        }

        #in2 a {
            line-height: 33px;
            margin: 0px 10px;
        }

        ul div {
            border-image-repeat: repeat;
            text-align: right;
            width: 640px;
            float: left;
            margin: 0px;
            line-height: 33px;
        }

        ul li {
            width: 110px;
            float: left;
            margin: 0px;
            line-height: 33px;
        }

        ul {
            padding-top: 8px;
            background-image: url("image/mainbg.png");
            list-style-image: url("image/open.png");
            list-style-position: inside;
            height: 35px;
            border-bottom: 1px solid white;
        }

        li a {
            display: block;
            float: right;
        }

        ul div a {
            margin-left: 20px;
        }
    </style>
</head>
<body>
<div id="out">
    <div id="in1"><img src="image/mymes.png"></div>
    <div id="in2"><a>当前用户：</a><a>登录名</a><a href="#">发短信息</a><a href="#">退出</a></div>
    <ul>
        <li><a>aaaaaa</a></li>
        <div><a>删除</a><a>回信</a><a>日期</a></div>
    </ul>
    <ul>
        <li><a>aaaaaa</a></li>
        <div><a>删除</a><a>回信</a><a>日期</a></div>
    </ul>
    <ul>
        <li><a>aaaaaa</a></li>
        <div><a>删除</a><a>回信</a><a>日期</a></div>
    </ul>
    <ul>
        <li><a>aaaaaa</a></li>
        <div><a>删除</a><a>回信</a><a>日期</a></div>
    </ul>
</div>
</body>
</html>