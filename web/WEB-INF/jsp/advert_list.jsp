<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="resources.messages"/>
<fmt:message key="str"/>
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
    <h2><fmt:message key="adverts"/></h2>
    <form action="ad" method="post" id="advertForm" role="form" >
        <c:choose>
            <c:when test="${not empty add_list}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td>Id</td>
                        <td>Text</td>
                        <td>Title</td>

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
                                <a href="ad-view-page?id=${ad.id}">${ad.title}</a>
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
    <form method="get" action="controller">
        <li class="nav-item">
            <input type="hidden" name="command" value="To_Create_Advertisement">
            <input type="submit" value="<fmt:message key="addAdvertisement"/>">
        </li>
    </form>
</div>
</body>
</html>
