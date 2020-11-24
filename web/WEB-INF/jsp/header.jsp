<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.messages"/>
<%@ include file="head_styles_scripts.jsp" %>
<nav class="navbar navbar-expand-md navbar-dark bg-light fixed-top container-fluid align-items-center justify-content-around">
    <div></div>
    <ul class="mb-0 list-unstyled d-flex">
    <c:if test="${sessionScope.role == 0 || sessionScope.role == 1 }">
                <form class="mb-0" method="get" action="controller">
                <li  class="nav-item">
                    <input type="hidden" name="command" value="Logout">
                    <input type="submit" value="<fmt:message key="logout"/>">
                </li>
                </form>
            </c:if>
            <c:if test="${sessionScope.role == null || sessionScope.role == -1}">

                <form class="mb-0" method="get" action="controller">
                    <li class="nav-item">
                        <input type="hidden" name="command" value="Login">
                        <input type="submit" value="<fmt:message key="login"/>">

                    </li>
                </form>
                 <form class="mb-0 ml-5" method="get" action="controller">
                <li class="nav-item">
                    <input type="hidden" name="command" value="To_Register">
                    <input type="submit" value="<fmt:message key="register"/>">
                </li>
                 </form>
            </c:if>
<form class="mb-0 ml-5" method="get" action="controller">
    <li class="nav-item">
        <input type="hidden" name ="command" value="All_Adverts"/>
        <input type="submit" value="<fmt:message key="adverts"/>"/>
    </li>
</form>

<form class="mb-0 ml-5" method="get" action="controller">
            <li class="nav-item">
                <input type="hidden" name="command" value="Sections" />
                <input type="submit" value="<fmt:message key="sections"/>">
            </li>
</form>
<c:if test="${user ne null}">
<form class="mb-0 ml-5" method="get" action="controller">
            <li class="nav-item">
                <input type="hidden" name="command" value="To_Profile" />
                <input type="submit" value="<fmt:message key="profile"/>">
            </li>
</form>
</c:if>
            <c:if test="${sessionScope.id != null}">
                <li class="nav-item">
                    <a class="nav-link" href="new-advert-page.jsp">New Advert</a>
                </li>
            </c:if>
        <form class="form-inline my-2 my-lg-0">
            <c:if test="${sessionScope.name != null}">
                <a class="nav-link" href="profile-page">
                    <c:out value="${sessionScope.name}"/>
                </a>
            </c:if>
        </form>
    </ul>
    <div class="header_language">
        <div class="menu">
            <a class="menu_title" href="#"><fmt:message key="bar.language"/></a>
            <div class="menu_down">
                <div class="menu_section">
                    <form class="mb-0 ml-5" method="get" action="controller">
                        <li class="nav-item">
                            <input type="hidden" name="command" value="LANGUAGE" />
                            <input type="hidden" name="language" value="ru" />
                            <input type="submit" value="<fmt:message key="bar.language"/>">
                        </li>
                    </form>
            <!--        <a href="${pageContext.request.contextPath}/controller?command=LANGUAGE&language=ru">
                            <img src="${pageContext.request.contextPath}/img/ru.svg" alt="">
                    </a></div>
                    -->
                <div class="menu_section">
                    <form class="mb-0 ml-5" method="get" action="controller">
                        <li class="nav-item">
                            <input type="hidden" name="command" value="LANGUAGE" />
                            <input type="hidden" name="language" value="en" />
                            <input type="submit" value="<fmt:message key="bar.language"/>">
                        </li>
                    </form>
<!--
                    <a href="${pageContext.request.contextPath}/controller?command=LANGUAGE&language=eng">
                        <img src="${pageContext.request.contextPath}/img/eng.svg" alt="">
                    </a> -->
                    </div>

            </div>
        </div>
    </div>
    </div>
</nav>