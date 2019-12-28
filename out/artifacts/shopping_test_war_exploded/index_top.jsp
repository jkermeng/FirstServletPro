<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    double num = Math.random();
%>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jq/jquery-3.3.1.js"></script>
<div id="header">
    <div class="login_menu">
        <div class="login_container">
            <c:set value="${sessionScope.user }" var="user"></c:set>
            <ul class="m_left">
                <c:choose>
                    <c:when test="${user!=null }">
                        <li>欢迎&nbsp;&nbsp;&nbsp;<a href="#" class="c_red">${user.user_name }</a>&nbsp;&nbsp;&nbsp;</li>
                        <li><a href="exit.jsp">退出</a>&nbsp;&nbsp;&nbsp;</li>
                        <li><a href="register.jsp">注册</a></li>
                    </c:when>

                    <c:otherwise>
                        <li><a href="login.jsp" class="c_red">登录</a>&nbsp;&nbsp;&nbsp;</li>
                        <li><a href="register.jsp">注册</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>

            <ul class="m_right">
                <c:choose>
                    <c:when test="${user!=null }">
                        <li><img src="images/icon_3.png"><a
                                href="${pageContext.request.contextPath }/user/cart?method=showCart"
                                class="c_red">购物车</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><img src="images/icon_3.png"><a
                                href="javascript:tips()" class="c_red">购物车</a></li>
                    </c:otherwise>
                </c:choose>
                <li><img src="images/icon_4.png"><a
                        href="javascript:AddFavorite('我的网站',location.href)">收藏</a></li>
                <li><img src="images/icon_2.png"><a href="guestbook.jsp">留言</a></li>
                <li><img src="images/icon_1.png"><a href="index.jsp">主页</a></li>
            </ul>
        </div>
    </div>
    <div class="logo_search">
        <div class="logo">
            <img src="images/LOGO.png" alt="SHINNING WEB">
        </div>
        <div class="search">
            <input type="hidden" id="contextPath" value="${pageContext.request.contextPath }">
            <input type="text" placeholder="商品名称" id="qname"/>
            <button class="query_button" onclick="queryProducts()">搜&nbsp;&nbsp;索</button>
        </div>
    </div>
    <div class="nav_bar">
        <div class="nav_bar_container">
            <ul>
                <li><a href="${pageContext.request.contextPath}/user/goods?method=goodsView">商品名称</a></li>
                <c:if test="${requestScope.carts!=null }">
                    <c:forEach items="${requestScope.carts }" var="p" end="2">
                        <li>|</li>
                        <li><a href="pview?pId=${p.cid }" target="_self">${p.g.gname }</a></li>
                    </c:forEach>
                </c:if>

                <c:set var="categoryInfo" value="${requestScope.categoryInfo }"></c:set>
                <h1>${categoryInfo}</h1>
                <c:forEach items="${categoryInfo }" var="c" begin="3" end="4">
                    <c:forEach items="${c[1] }" var="category" end="4">
                        <li>|</li>
                        <li><a href="category?cate=child&hpcId=${category.hpcId }">${category.hpcName }</a></li>
                    </c:forEach>
                </c:forEach>
            </ul>
        </div>
    </div>

    <script type="text/javascript">
        function tips() {
            alert("请先登录!");
            window.location.href = "login.jsp";
        }

        function AddFavorite(title, url) {
            try {
                window.external.addFavorite(url, title);
            } catch (e) {
                try {
                    window.sidebar.addPanel(title, url, "");
                } catch (e) {
                    alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
                }
            }
        }
    </script>
</div>
