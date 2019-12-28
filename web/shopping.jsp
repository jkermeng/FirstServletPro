<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Shinning-购物车</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
    <script type="text/javascript" src="scripts/shopping.js"></script>

</head>
<body>
<%@ include file="index_top.jsp" %>

<div id="position" class="wrap">
    您的位置：<a href="index.jsp">Shinning</a> &gt; <a href="index.jsp">购物车列表</a>
</div>
<div class="wrap">
    <div id="shopping">
        <form action="doBuy" method="post">
            <table>
                <tr>
                    <th>选&nbsp;择</th>
                    <th>商品名称</th>
                    <th>商品单价</th>
                    <th>购买数量</th>
                    <th>处&nbsp;理</th>
                </tr>


                <!-- 根据用户购物车生成列表 -->
                <c:forEach items="${sessionScope.carts }" var="shopping">
                    <tr id="product_id_1">
                        <td style=" text-align: center;"><input type="checkbox" id="choose${shopping.g.gid}"
                                                                name="gid" value="${shopping.g.gid}"/>
                        </td>

                        <td class="thumb"><img style="width: 100px; height: 100px;"
                                               src="${shopping.g.gp.purl }"
                                               alt="No Picture"/><a
                                href="${pageContext.request.contextPath}/user/goods?method=uniqueGoodsView&goodid=${shopping.g.gid}">${shopping.g.gname }</a>
                        </td>
                        <td class="price" id="price_id_1">
                            ￥<span id="span_1">${shopping.g.gprice }</span>
                            <input type="text" id="subPrice"
                                   value="${shopping.g.gprice*shopping.gnumber }"
                                   name="sumPrice"/>
                            <input type="text" value="${shopping.g.gid }" name="pId"/>
                            <input type="text" value="${shopping.g.gstock }" name="hpStock"
                                   id="hpStock${shopping.cid }"/>

                        </td>
                        <td class="number">

                            <c:set var="hcid" value="${shopping.cid}"></c:set>
                            <input type="button" id="minus" value=" - " width="3px"
                                   onclick=" reduce(${hcid })"
                                   name="minusButton">

                            <input id="${hcid }" type="text" name="number${shopping.g.gid}"
                                   value="${shopping.gnumber }"
                                   maxlength="5"
                                   size="1" style="text-align:center; vertical-align:middle"
                                   onblur="checkStock(${hcid })"/>

                            <input type="button" id="add" value=" + " width="2px"
                                   onclick=" increase(${hcid })"
                                   name="addButton">

                        </td>
                        <input type="hidden" id="contextPath" value="${pageContext.request.contextPath }">

                        <td class="delete"><a
                                href="${pageContext.request.contextPath }/user/cart?cid=${hcid }&gid=${shopping.g.gid}&method=deleteCart">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="button"><input type="submit" onclick="buttonTest(${sessionScope.user.uid})"/>
            </div>
        </form>
    </div>
</div>
<div id="footer">
    Copyright &copy; Shinning Company All Rights Reserved.
</div>
</body>
</html>

