<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<link href="../../resources/css/profile.css" rel="stylesheet" type="text/css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires",0);
    response.setHeader("Cache-Control", "no-cache");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<body>
<!--프로필 섹션-->
<header>

    <div class="container">

        <div class="profile">

            <div class="profile-image">
                <c:choose>
                    <c:when test="${profile.profileImg == null}">
                        <img src="/resources/img/profile/defaultProfile.png" alt=""/>
                    </c:when>
                    <c:otherwise>
                        <img src="${contextPath}/profile/download.do?imageFileName=${profile.profileImg}&userId=${profile.userId}"  alt=""/>
                    </c:otherwise>
                </c:choose>

            </div>

            <div class="profile-user-settings">

                <h1 class="profile-user-name">${profile.userId} / ${profile.userNickname}</h1>

                <button class="btn profile-edit-btn" onclick="location.href='http://localhost:8081/profile/modprofile.do?id=${profile.userId}'">Edit Profile</button>
                <button class="btn profile-settings-btn" onclick="location.href='http://localhost:8081/profile/editImg.do'">Edit Image</button>

            </div>

            <div class="profile-stats">

                <ul>
                    <li><span class="profile-stat-count">164</span> posts</li>
                    <li><span class="profile-stat-count">188</span> followers</li>
                    <li><span class="profile-stat-count">206</span> following</li>
                </ul>

            </div>

            <div class="profile-bio">

                <p><span class="profile-real-name">${profile.userName}</span>${profile.introduction}</p>

            </div>

        </div>
        <!-- End of profile section -->

    </div>
    <!-- End of container -->

</header>

<main>

    <div class="container">

        <div class="gallery">

            <div class="gallery-item" tabindex="0">
                <img src="/resources/img/backImg.jpg" class="gallery-image" alt="">
                <div class="gallery-item-info">

                    <ul>
                        <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart" aria-hidden="true"></i> 56</li>
                        <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i class="fas fa-comment" aria-hidden="true"></i> 2</li>
                    </ul>

                </div>

            </div>



            <!-- End of gallery -->

        </div>
        <!-- End of container -->
    </div>
</main>
</body>
</html>
