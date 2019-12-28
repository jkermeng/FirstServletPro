<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/12/18
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="onetomanyentity.Goods" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="product2">

    <c:if test="${requestScope.pageModel.totalPage==0}">
        <h4>找不到您搜索的商品！</h4>
    </c:if>

    <c:set value="${requestScope.pageModel.list}" var="products"></c:set>
    <c:forEach items="${products}" var="p">
        <li>
            <dl>
                <dt>
                    <a href="pview?pId=${p.hpId }" target="_self">
                        <img src="${p.hpFileName }"/></a>
                </dt>
                <dd class="title">
                    <a href="pview?pId=${p.hpId}" target="_self">${p.hpName }</a>
                </dd>
                <dd class="price">￥${p.hpPrice}</dd>
            </dl>
        </li>

    </c:forEach>


</ul>

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
                                                            id="hpcId" value="${requestScope.pageModel.hpcId}"/>
    <input type="hidden" id="totalPage" value="${requestScope.pageModel.totalPage}"/>
    <input type="hidden" id="currentPage" value="${requestScope.pageModel.currentPage}"/>
    <input type="hidden" id="queryName" value="${requestScope.pageModel.qname}"/>
</div>