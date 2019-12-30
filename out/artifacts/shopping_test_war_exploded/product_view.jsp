<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Shinning - Products Show</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
    <script type="text/javascript" src="scripts/product_view.js"></script>


</head>
<body>
<%@ include file="index_top.jsp" %>
<div id="position" class="wrap">
    <c:set value="${requestScope.categoryForProduct}" var="s"></c:set>
    您现在的位置：<a href="index.jsp">Shinning</a> &gt; <a
        href="category?cate=parent&hpcId=${s[0] }">${s[1] }</a> &gt; <a
        href="category?cate=child&hpcId=${s[2] }">${s[3] }</a>
</div>
<div id="main" class="wrap">
    <div class="lefter">
        <%@ include file="index_product_sort.jsp" %>
    </div>
    <div id="product" class="main">
        <c:set var="good" value="${sessionScope.good}"></c:set>

    <h1>商品名称：${good.gname }</h1>
        <div class="infos">
            <div class="thumb">
                <img style="width: 100px; height: 100px;" src="${good.gp.purl }" alt="暂无产品图片"/>
            </div>
            <div class="buy">
                <p>
                    商品单价：<span class="price">￥${good.gprice }</span>
                </p>
                <c:choose>
                <c:when test="${good.gstock>0 }">
                    <p>
                        库存：<span id="stock">${good.gstock }</span>(有货)
                    </p>
                </c:when>
                <c:otherwise>
                <p>库存：无货
                    </c:otherwise>
                    </c:choose>
                    <input type="button" id="minus" value=" - " width="3px"
                           onclick="minus();">
                    <input type="text" id="count" name="count" onblur="checkStock()" value="1" maxlength="5" size="1"
                           style="text-align: center; vertical-align: middle">
                    <input type="button" id="add" value=" + " width="2px"
                           onclick="add();">
                    <c:choose>
                    <c:when test="${sessionScope.user==null }">
                <div class="button">
                    <input type="button" name="button" value="" onclick="remaind();"
                           style="background: url(images/buyNow.png)"/> <input type="image"
                                                                               name="imageField"
                                                                               src="images/cartbutton.png"
                                                                               onclick="remaind()"/>
                </div>
                </c:when>
                <c:otherwise>
                    <div class="button">
                        <input type="button" name="button" value="" onclick="goingToBuy(${good.gid },${good.gprice});"
                               style="background: url(images/buyNow.png)"/>
                        <input type="image" name="imageField" src="images/cartbutton.png"
                               onclick="addToCart(${good.gid });"/>
                    </div>
                </c:otherwise>
                </c:choose>

            </div>
            <div class="clear"></div>
        </div>
        <div class="introduce">
            <h2>
                <strong>商品详情</strong>
            </h2>
            <div class="text">
                商品名称：${good.gname }<br/>
                商品单价：￥${good.gprice }<br/> 库存：${good.gstock }<br/>
                <dl>
                    <dt>商品描述：</dt>
                    <%--                    <c:set var="attributes" value="${good.att }"></c:set>--%>
                    <%--                    <c:forEach items="${attributes }" var="attribute">--%>
                    <%--                        <label>${attribute.name }</label>&nbsp;&nbsp;--%>
                    <%--                    </c:forEach>--%>
                </dl>
            </div>

        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">Copyright &copy; Shinning Company All Rights Reserved.
</div>
</body>
</html>

