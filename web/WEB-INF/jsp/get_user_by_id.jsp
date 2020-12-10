<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.messages"/>
<%@ include file="head_styles_scripts.jsp" %>
<html>
<head>
    <title><fmt:message key="title"/></title>
</head>
<body>
<form class="form-signin" action="controller" method="get">
    <input type="hidden" name ="command" value="get_user" />
    <h1 class="h3 mb-3 font-weight-normal">Please enter user id</h1>
    user <input type="text" name="USER_ID" value="">
    <b/>
    password<input type="text" name="PASS" value="">

    <button class="btn btn-lg btn-primary btn-block" type="submit" value="login">Sign in</button>
</form>

<hr/>
${user_to}
<hr/>
</body>
</html>
