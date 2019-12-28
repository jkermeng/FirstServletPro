<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="p_category">
    <h2>商品分类</h2>
    <c:set var="mclasses" value="${sessionScope.mclassList }"></c:set>
    <c:forEach items="${mclasses}" var="mces">
        <dl>
            <dt>
                <a href="category?cate=parent&hpcId=${mces.cid}">${mces.cname}</a>

            </dt>
            <c:set var="sclasses" value="${mces.setgoods}"></c:set>
            <c:forEach items="${sclasses }" var="subsclass">
                <dd>
                    <a href="${pageContext.request.contextPath}/user/goods?method=goodsView&searchName=${subsclass.gname }&searchId=${subsclass.gid }">${subsclass.gname }</a>
                        <%--product_view--%>
                </dd>
            </c:forEach>
        </dl>
    </c:forEach>


</div>

