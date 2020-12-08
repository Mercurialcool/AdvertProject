<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.messages"/>
<html>
<head>

    <link href="css/sign-in.css" rel="stylesheet" type="text/css"/>

    <%@ include file="head_styles_scripts.jsp" %>
    <link href="css/starter.css" rel="stylesheet" type="text/css"/>

</head>
<body class="text-center">
<jsp:include  page="header.jsp" />
<body>
<br>
<br>
<br>
<form action="controller" method="get">
<c:choose>
    <c:when test="${not empty advertList}">
        <table  class="table table-striped">
            <thead>
            <tr>
                <td class="pl-5">Id</td>
                <td>Text</td>
                <td>Title</td>

            </tr>
            </thead>
            <c:forEach var="ad" items="${advertList}">
                <c:set var="classSuccess" value=""/>

                <tr class="${classSuccess}">
                    <td class="pl-5">${ad.id}</td>
                    <td><input class="pl-1 rounded" type="text" name="advert_text" value="${ad.text}"></td>
                    <td>
                        <input class="pl-1 rounded" type="text" name="advert_title" value="${ad.title}">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        addList= ${advertList}
    </c:otherwise>
</c:choose>
    <br/>
    ${changeMessage}
<td>
    <input type="hidden" name="command" value="Save_Edited_Adverts">
    <input type="submit" value="Save changes">
</td>
</form>
</body>
</html>
