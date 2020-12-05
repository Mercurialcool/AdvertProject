<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.messages"/>
<%@ include file="head_styles_scripts.jsp" %>
<html>
<head>
    <%@ include file="header.jsp" %>
</head>
<body>
<div class="h-100 d-flex flex-column justify-content-center align-items-center">
    <div class="w-25 mb-5">
<form action="controller" method="get">
    <input type="hidden" name="command" value="Edit_User_Profile">
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="credentials"/></h1>
    <label for="inputNick" class="sr-only"><fmt:message key="nickname"/></label>
    <input type="text" name="nickname" value="${sessionScope.user.username}" id="inputNick" class="form-control" required autofocus>
    <label for="inputEmail" class="sr-only"><fmt:message key="email"/></label>
    <input type="text" name="email" id="inputEmail" class="form-control" placeholder="e-mail" required autofocus>
    <button class="btn btn-lg btn-primary btn-block" type="submit" value="register"><fmt:message key="register"/></button>
<c:if test="${update_user}">
    <fmt:message key="updateUserTrue"/>
</c:if>

    <c:if test="${update_user eq false}">
        <fmt:message key="updateUserFalse"/>
    </c:if>

</form>
    </div>
</div>
</body>
</html>