<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Shinning - Product List</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
    <script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<%@ include file="index_top.jsp" %>
<div id="position" class="wrap">
    您现在的位置：<a href="${pageContext.request.contextPath}/index.jsp">Shinning</a> &gt; <a
        href="product-list.jsp">商品列表页</a>
</div>
<div id="main" class="wrap">
    <div class="lefter">
        <%@ include file="index_product_sort.jsp" %>
        <div class="spacer"></div>
        <div class="last-view">
            <h2>最近浏览</h2>
            <dl class="clearfix">

                <dt>
                    <img style="width: 54px; height: 54px;" src="images/product/0.jpg"/>
                </dt>
                <dd>
                    <a href="#">商品名称</a>
                </dd>
                <c:forEach items="${sessionScope.viewedProduct }" var="p" end="3">
                <dt>
                    <img style="width: 54px; height: 54px;" src="${p.gp.purl }"/>
                </dt>
                <dd>
                    <a href="pview?pId=${p.gid }">${p.gname }</a>
                </dd>
                </c:forEach>

        </div>
    </div>
    <div class="main">
        <div class="product-list">
            <h2>所有商品</h2>
            <div class="clear"></div>
            <ul class="product clearfix">
                <c:set var="goodlist" value="${sessionScope.goods }"></c:set>
                <c:forEach items="${goodlist }" var="goods">
                    <li>
                        <dl>
                            <dt>
                                <a href="#" target="_self"><img src="${goods.gp.purl }" alt="No Picture"/></a>
                                <!-- 当图片路径出错时 显示另一图片路径onerror="this.src='images/product/0.jpg';this.onerror=null" -->
                            </dt>
                            <dd class="title">
                                <a href="${pageContext.request.contextPath}/user/goods?method=uniqueGoodsView&goodid=${goods.gid}"
                                   target="_self">${goods.gname }&nbsp;&nbsp;￥${goods.gprice }</a>
                            </dd>
                        </dl>
                    </li>

                </c:forEach>

            </ul>
            <div class="clear"></div>
            <div class="pager">
                <ul>
                    <ul>
                        <li><a href="javascript:lastPage()" id="lastPage">上一页</a></li>
                        <c:forEach items="${requestScope.pageModel.pageList}" var="pl">
                            <li>
                                <a href="ref?src=${requestScope.pageModel.source}&page=${pl }&hpcId=${requestScope.pageModel.hpcId}">${pl }</a>
                            </li>
                        </c:forEach>
                        <li><a href="javascript:nextPage()" id="nextPage">下一页</a></li>
                    </ul>
                </ul>
                <input type="hidden" id="source"
                       value="${requestScope.pageModel.source}"/><input type="hidden"
                                                                        id="hpcId"
                                                                        value="${requestScope.pageModel.hpcId}"/>
                <input
                        type="hidden" id="totalPage"
                        value="${requestScope.pageModel.totalPage}"/> <input
                    type="hidden" id="currentPage"
                    value="${requestScope.pageModel.currentPage}"/> <input
                    type="hidden" id="queryName"
                    value="${requestScope.pageModel.qname}"/>
            </div>
        </div>
        <%--            <div class="pager">--%>
        <%--                <ul class="clearfix">--%>
        <%--                    <li><a href="#">上一页</a></li>--%>
        <%--                    <li><a href="#">2</a></li>--%>
        <%--                    <li><a href="#">3</a></li>--%>
        <%--                    <li><a href="#">下一页</a></li>--%>
        <%--                </ul>--%>
        <%--            </div>--%>

    </div>
</div>
<div class="clear"></div>
</div>
<div id="footer">Copyright &copy; Shinning Company All Rights Reserved.
</div>
</body>
</html>

