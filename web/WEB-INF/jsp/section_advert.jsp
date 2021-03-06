<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.messages"/>
<%@ include file="head_styles_scripts.jsp" %>

<html>
<head>
    <%@ include file="head_styles_scripts.jsp" %>
    <head>

        <link href="css/sign-in.css" rel="stylesheet" type="text/css"/>

        <%@ include file="head_styles_scripts.jsp" %>
        <link href="css/starter.css" rel="stylesheet" type="text/css"/>

    </head>
</head>
<body>
<jsp:include  page="header.jsp" />
</body>
<br>
<br>
<br>
${section_selected}
<c:forEach var="ad" items="${section_adverts}">
    <c:set var="classSuccess" value=""/>

    <tr>
        <td>${ad.id}</td>
        <td>${ad.text}</td>
        <td>
            <a href="ad-view-page?id=${ad.id}">${ad.title}</a>
        </td>
    </tr>
</c:forEach>
</html>
