<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
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
    <div class="row">
        <div class="col-md-9">
            <div class="row mb-2">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-9">
                                    <div class="card-body">
                                        <div class="news-content">
                                            <a href="#"><h2>${advert.title} </h2></a>
                                            <p>${advert.text}</p>
                                        </div>
                                        <div class="news-footer">
                                            <div class="news-author">
                                                <ul class="list-inline list-unstyled">
                                                    <li class="list-inline-item text-secondary">
                                                        <i class="fa fa-user"></i>
                                                        ${advert.userName}
                                                        <br/>
                                                        ${advert.section}

                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

</body>
<link rel="stylesheet" href="./css/comment.css">
<section class="content-item" id="comments">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <form>
                    <h3 class="pull-left">New Comment</h3>

                    <fieldset>
                        <div class="row">
                            <div class="col-sm-3 col-lg-2 hidden-xs">
                            </div>
                            <div class="form-group col-xs-12 col-sm-9 col-lg-10">
                                <textarea class="form-control" id="message" placeholder="Your comment" required=""></textarea>
                            </div>
                        </div>
                    </fieldset>
                    <button type="submit" class="btn btn-normal pull-right">Submit</button>
                </form>

                <h3> Comments</h3>

                <!-- Comment test -->

                <c:forEach var="comment" items="${comments}">
                    <div class="media">
                        <div class="media-body">
                            <h4 class="media-heading">${comment.username}</h4>
                            <p>${comment.text}</p>
                        </div>
                    </div>
                </c:forEach>

                <!-- Comment test -->
            </div>
        </div>
    </div>
</section>
</html>