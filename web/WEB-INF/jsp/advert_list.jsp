<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.messages"/>
<%@ include file="head_styles_scripts.jsp" %>
<html>
<head>
    <%@ include file="head_styles_scripts.jsp" %>
    <link href="static/css/starter.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<%@ include file="header.jsp" %>
</body>
<body>
<div class="container">
    <br>
    <br>
    <br>
    <br>
    <br>
    <h2><fmt:message key="adverts"/></h2>
    <form action="ad" method="post" id="advertForm" role="form" >
        <c:choose>
            <c:when test="${not empty add_list}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td><fmt:message key="id"/></td>
                        <td><fmt:message key="title"/></td>
                        <td><fmt:message key="text"/></td>

                    </tr>
                    </thead>
                    <c:forEach var="ad" items="${add_list}">
                        <c:set var="classSuccess" value=""/>
                        <c:if test ="${idAdvert == ad.id}">
                            <c:set var="classSuccess" value="info"/>
                        </c:if>
                        <tr class="${classSuccess}">
                            <td>${ad.id}</td>
                            <td>${ad.text}</td>
                            <td>
                                <a href="controller?command=view_advertisement&id=${ad.id}">${ad.title}</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                addList= ${add_list}
            </c:otherwise>
        </c:choose>
    </form>
    <form method="get" action="controller" class="container-fluid d-flex justify-content-center align-items-center">
        <ul class="list-unstyled">
        <li class="nav-item">
            <input type="hidden" name="command" value="To_Create_Advertisement">
            <input type="submit" value="<fmt:message key="addAdvertisement"/>">
        </li>
        </ul>
    </form>
</div>
</body>
</html>
