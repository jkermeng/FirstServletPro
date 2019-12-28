<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Shinning - Order Page</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
    <script type="text/javascript" src="scripts/shopping.js"></script>
    <style type="text/css">#oderview {
        font-size: 14px
    }</style>

</head>
<body>
<%@ include file="index_top.jsp" %>

<div id="position" class="wrap">
    您现在的位置：<a href="index.jsp">首页</a> &gt; 订单页
</div>
<div class="wrap">
    <div id="shopping">
        <form action="doBuy" method="post">
            <table id="oderview">
                <c:set value="${sessionScope.orderView }" var="view"></c:set>
                <%-- <c:set value="${view.oder }" var="order"></c:set> --%>
                <tr>
                    <th>订单编号</th>
                    <th>订单合计金额(单位：元)</th>
                    <th>生成订单时间</th>
                    <th>
                    <th>

                </tr>
                <!-- 根据用户购物车生成列表 -->
                <c:forEach items="${view }" var="shopping">
                    <tr id="product_id_1">
                        <td class="number">${shopping.oid }</td>
                        <td class="price"><span>￥${shopping.ototal }</span></td>
                        <td class="thumb">${shopping.ostart_time }</td>
                        <td><a href="#">点击查看</a></td>
                        <td class="delete"><a>派送中...</a></td>
                    </tr>
                </c:forEach>
            </table>

        </form>
    </div>
</div>
<div id="footer">
    Copyright &copy; Shinning Company All Rights Reserved.
</div>
</body>
</html>

