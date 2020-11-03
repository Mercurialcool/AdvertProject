<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="resources.messages"/>
<%@ include file="head_styles_scripts.jsp" %>
<html>
<head>
    <title>New Advertisement</title>
</head>
<body>

<form class="" action="controller" method="get">
    <input type="hidden" name = "command" value="Add_Advertisement"/>
    <input type="text" name="advert_title" class="form-control" placeholder=<fmt:message key="advert_title"/> required autofocus>
    <input type="text" name="advert_plot" class="form-control" placeholder=<fmt:message key="advert_plot"/> required autofocus>
    <button class="btn btn-lg btn-primary btn-block" type="submit" value="login"><fmt:message key="confirm"/></button>
</form>

</body>
</html>
