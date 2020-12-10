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
<jsp:include  page="header.jsp" />
<div class="container">
    <input type="hidden" name="command" value="Edit_User"/>
    <input type="submit" name="submit" value="<fmt:message key="edit_user"/>" />

 <form action="ad" method="post" id="advertForm" role="form">
    <c:choose>
        <c:when test="${not empty users_list}">
        <input type="hidden" name="command" value="Edit_User"/>
            <table  class="table table-striped">

                <thead>
                <tr>
                    <td><fmt:message key="user_name"/></td>
                    <td><fmt:message key="user_id"/></td>
                    <td><fmt:message key="rating"/></td>
                    <td><fmt:message key="role"/></td>
                    <td><fmt:message key="status"/></td>
                </tr>
                </thead>
                <c:forEach var="user" items="${users_list}">
                    <c:set var="classSuccess" value=""/>
                    <tr class="${classSuccess}">
                        <td>
                            <a href="controller?command=delete_User&del_user=${user.username}"> ${user.username}</a>
                        </td>
                        <td>
                            <a href="controller?command=edit_Advert&user_id=${user.id}"> <fmt:message key="edit_users_adverts"/></a>
                        </td>
                        <td>${user.rating}</td>
                        <td>
                            <c:if test="${user.role eq 0}">
                                user
                            </c:if>
                            <c:if test="${user.role eq 1}">
                                admin
                            </c:if>
                        </td>
                        <td>${user.status}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            users List = empty
        </c:otherwise>
    </c:choose>
    <input type="submit" name="submit" value="User list management"/>
 </form>
</div>
</body>
</html>

