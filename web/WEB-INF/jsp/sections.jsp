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
<div class="container">
    <h2><fmt:message key="sections"/></h2>
    <form>
        <c:forEach items="${sessionScope.sections}" var="section">
            <form action="controller">
                <input type="hidden" name = "command" value="Section_list"/>
                <input type="submit" name="section"  value="${section.name()}"/><br/>
            </form>
        </c:forEach>

        <table  class="table table-striped">
            <thead>
            <tr>

                <td><fmt:message key="sections_title"/></td>
            </tr>
            </thead>
                <tr>
                    <td>
                        <form action="controller">
                            <input type="hidden" name = "command" value=""/>
                        <input type="submit"  value=<fmt:message key="furniture"/>>
                         </form>

                    </td>
                </tr>
            <tr>
                <td>
                    <form action="controller">
                        <input type="hidden" name = "command" value=""/>
                        <input type="submit"  value=<fmt:message key="electronics"/>>
                    </form>

                </td>
            </tr>
            <tr>
            <td>
                <form action="controller">
                    <input type="hidden" name = "command" value=""/>
                    <input type="submit"  value=<fmt:message key="transport"/>>
                </form>

            </td>
        </tr>
            <tr>
            <td>
                <form action="controller">
                    <input type="hidden" name = "command" value=""/>
                    <input type="submit"  value=<fmt:message key="building"/>>
                </form>

            </td>
        </tr>
        </table>
    </form>
</div>
</body>
</html>