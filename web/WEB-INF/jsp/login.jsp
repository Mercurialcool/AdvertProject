<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resources.messages"/>
<html>
<head>

    <link href="css/sign-in.css" rel="stylesheet" type="text/css"/>

        <%@ include file="head_styles_scripts.jsp" %>
        <link href="css/starter.css" rel="stylesheet" type="text/css"/>

</head>
<body class="text-center">
<jsp:include  page="header.jsp" />
<form class="form-signin" action="controller" method="get">
    <input type="hidden" name = "command" value="login"/>
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="signIn"/></h1>
    <input type="text" name="name" id="inputEmail" class="form-control" placeholder=<fmt:message key="login"/> required autofocus>
    <label for="inputPassword" class="sr-only"><fmt:message key="login"/></label>
    <input type="password" name="password" id="inputPassword" class="form-control" placeholder=<fmt:message key="password"/> required>
    <button class="btn btn-lg btn-primary btn-block" type="submit" value="login"><fmt:message key="signIn"/></button>
</form>
</body>
</html>
