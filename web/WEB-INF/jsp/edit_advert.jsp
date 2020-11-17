<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

    <link href="css/sign-in.css" rel="stylesheet" type="text/css"/>

    <%@ include file="head_styles_scripts.jsp" %>
    <link href="css/starter.css" rel="stylesheet" type="text/css"/>

</head>
<body class="text-center">
<jsp:include  page="header.jsp" />
<body>
<form action="controller" method="get">
<c:choose>
    <c:when test="${not empty advertList}">
        <table  class="table table-striped">
            <thead>
            <tr>
                <td>Id</td>
                <td>Text</td>
                <td>Title</td>

            </tr>
            </thead>
            <c:forEach var="ad" items="${advertList}">
                <c:set var="classSuccess" value=""/>

                <tr class="${classSuccess}">
                    <td>${ad.id}</td>
                    <td><input type="text" name="advert_text" value="${ad.text}"></td>
                    <td>
                        <input type="text" name="advert_title" value="${ad.title}">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        addList= ${advertList}
    </c:otherwise>
</c:choose>
<td>
    <input type="hidden" name="command" value="Save_Edited_Adverts">
    <input type="submit" value="Save changes">
</td>
</form>
</body>
</html>
