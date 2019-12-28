<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>
<%
    session.invalidate();
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length != 0) {
        for (Cookie ck :
                cookies) {
            if (ck.getName().equals("login")) {
                ck.setMaxAge(0);
                ck.setValue(null);
                response.addCookie(ck);
            }
        }
    }
    response.sendRedirect("/shopping_test/index.jsp");
%>
<%--<jsp:forward page="/index.jsp"></jsp:forward>--%>
</body>
</html>