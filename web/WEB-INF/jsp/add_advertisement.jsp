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
    <title><fmt:message key="new_advert"/></title>
</head>
<body>
<div class="h-100 d-flex flex-column justify-content-center align-items-center">
    <div class="w-25 mb-5">
<form class="d-flex flex-column justify-content-center align-items-center" action="controller" method="get">
    <h3 class="h3 mb-3"><fmt:message key="add_advert"/></h3>
    <select name="section_selected" class="mb-3">
        <option disabled><fmt:message key="choose_category"/></option>
        <c:forEach items="${sessionScope.sections}" var="sec_elem">
            <option value=${sec_elem.id} >${sec_elem}</option>
        </c:forEach>
    </select>
    <input type="hidden" name = "command" value="New_Advert"/>
    <input type="text" name="advert_title" class="form-control mb-1" placeholder=<fmt:message key="advert_title"/> required autofocus>
    <input type="text" name="advert_text" class="form-control mb-3" placeholder=<fmt:message key="advert_plot"/> required autofocus>
    <button class="btn btn-lg btn-primary btn-block" type="submit" value="login"><fmt:message key="confirm"/></button>
</form>
    </div>
</div>
</body>
</html>
