<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head_styles_scripts.jsp" %>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="resources.messages"/>
<html>
<head>
        <link href="static/css/register.css" rel="stylesheet" type="text/css"/>
</head>
<body class="text-center">
<div class="h-100 d-flex flex-column justify-content-center align-items-center">
    <div class="w-25 mb-5">
<form class="form-register" action="controller" method="get">
    <input type="hidden" name = "command" value="registration"/>
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="credentials"/></h1>
    <label for="inputNick" class="sr-only"><fmt:message key="nickname"/></label>
    <input type="text" name="nickname" id="inputNick" class="form-control mb-1" placeholder="nickname" required autofocus>
    <label for="inputEmail" class="sr-only"><fmt:message key="email"/></label>
    <input type="text" name="email" id="inputEmail" class="form-control mb-1" placeholder="e-mail" required autofocus>
    <label for="inputPassword" class="sr-only"><fmt:message key="password"/></label>
    <input type="password" name="reg_password" id="inputPassword" class="form-control mb-1" placeholder="<fmt:message key="password"/>" required>
    <label for="inputRePassword" class="sr-only"><fmt:message key="re_password"/></label>
    <input type="password" name="reg_password_repeat" id="inputRePassword" class="form-control mb-1" placeholder="<fmt:message key="password"/>" required>
    <c:if test="${notEqualsPass}">
        <label  class="sr-only"><fmt:message key="passwords_dont_match"/></label>
    </c:if>
    <br/><button class="btn btn-lg btn-primary btn-block" type="submit" value="register"><fmt:message key="register"/></button>
</form>
    </div>
</div>
</body>
</html>
