<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="resources.messages"/>
<html>
<head>

    <link href="css/sign-in.css" rel="stylesheet" type="text/css"/>

        <%@ include file="head_styles_scripts.jsp" %>
        <link href="css/starter.css" rel="stylesheet" type="text/css"/>

</head>
<body class="text-center">
<jsp:include  page="header.jsp" />
<div class="h-100 d-flex flex-column justify-content-center align-items-center">
    <div class="w-25 mb-5">
<form class="form-signin justify-content-center align-items-center" action="controller" method="get">
    <input type="hidden" name = "command" value="login"/>
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="signIn"/></h1>
    <input type="text" name="name" id="inputEmail" class="form-control mb-1" placeholder=<fmt:message key="login"/> required autofocus>
    <label for="inputPassword" class="sr-only"><fmt:message key="login"/></label>
    <input type="password" name="password" id="inputPassword" class="form-control mb-1" placeholder=<fmt:message key="password"/> required>
    <button class="btn btn-lg btn-primary btn-block" type="submit" value="login"><fmt:message key="signIn"/></button>
</form>
    </div>
</div>
</body>
</html>
