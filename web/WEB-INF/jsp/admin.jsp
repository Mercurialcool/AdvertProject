<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="resources.messages"/>
<html>


<head>
    <%@ include file="head_styles_scripts.jsp" %>
    <link href="static/css/starter.css" rel="stylesheet" type="text/css"/>
</head>

<%--<body>--%>
<%--<%@ include file="header.jsp" %>--%>
<%--</body>--%>
<body>
<div class="container">
    <input type="hidden" name="command" value="Edit_User"/>
    <input type="submit" name="submit" value="Edit user"/>
    <h2><fmt:message key="users"/></h2>
    <form action="ad" method="post" id="advertForm" role="form" >
        <c:choose>
            <c:when test="${not empty users_list}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td>UserName</td>
                        <td>UserId</td>
                        <td>Rating</td>
                        <td>Role</td>
                        <td>Status</td>
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
                usersList= empty
            </c:otherwise>
        </c:choose>
        <input type="submit" name="submit" value="User list management"/>
    </form>
</div>
</body>
</html>

