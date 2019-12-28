<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <c:if test="${empty sessionScope.lclassList }">
        <jsp:forward page=""></jsp:forward>
    </c:if>
</head>
<body>
<form action="">
    <c:set var="lclasses" value="${ }"></c:set>
    <select id="lclass_id">
        <option></option>
    </select>
</form>

</body>
</html>