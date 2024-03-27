<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<link href="../../resources/css/profile.css" rel="stylesheet" type="text/css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires",0);
    response.setHeader("Cache-Control", "no-cache");
%>
<link href="../../resources/css/main.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="../../resources/css/reset.css">
<link rel="stylesheet" href="../../resources/css/common.css">
<link rel="stylesheet" href="../../resources/css/style.css">
<link rel="stylesheet" href="../../resources/css/nav.css">
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<body>
<div class="sideBar">
    <a href="#" class="side_icon" style="padding-top: 20px;font-size: 25px">L</a>
    <!-- 검색 -->
    <a href="#" class="side_icon"><i class="fa-solid fa-magnifying-glass "></i></a>
    <!-- 홈 -->
    <a class="side_icon"><i onclick="location.href='http://localhost:8081/main'" class="fa-solid fa-house"></i></a>
    <!-- 업로드 -->
    <a class="side_icon"><i onclick="location.href='http://localhost:8081/main/post/uploadPost.do'" id="uploadBtn" class="fa-solid fa-plus"></i></a>
    <!-- 내프로필 -->
    <a class="side_icon"><i onclick="location.href='http://localhost:8081/main/profile/profileView.do'" class="fa-regular fa-user"></i></a>
    <!-- 메세지 -->
    <a class="side_icon"><i onclick="location.href='http://localhost:8081/main/chatRooms'" class="fa-regular fa-message"></i></a>
    <!-- 알림 -->
    <a href="#" class="side_icon"><i class="fa-solid fa-bell "></i></a>
    <!-- 북마크 -->
    <a href="#" class="side_icon"><i class="fa-regular fa-bookmark "></i></a>
    <!-- 로그아웃 -->
    <a class="side_icon"><i id="logoutBtn" class="fa-solid fa-arrow-right-from-bracket"></i></a>
</div>
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
                        <img src="${contextPath}/main/profile/download.do?imageFileName=${profile.profileImg}&userId=${profile.userId}"  alt=""/>
                    </c:otherwise>
                </c:choose>

            </div>

            <div class="profile-user-settings">

                <h1 class="profile-user-name">${profile.userId} / ${profile.userNickname}</h1>

                <button class="btn profile-edit-btn" onclick="location.href='http://localhost:8081/main/profile/modprofile.do?id=${profile.userId}'">Edit Profile</button>
                <button class="btn profile-settings-btn" onclick="location.href='http://localhost:8081/main/profile/editImg.do'">Edit Image</button>

            </div>

            <div class="profile-stats">

                <ul>
                    <li><span class="profile-stat-count">164</span> posts</li>
                    <li><span class="profile-stat-count">188</span> followers</li>
                    <li><span class="profile-stat-count">206</span> following</li>
                </ul>

            </div>

            <div class="profile-bio">

                <p><span class="profile-real-name">${profile.userName}</span><br>${profile.introduction}</p>

            </div>

        </div>
        <!-- End of profile section -->

    </div>
    <!-- End of container -->

</header>

<hr style=" width: 1100px; margin-left: calc(50% + 25px); transform: translateX(-50%)">

<main style="margin-top: 10px">

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
<script src="../../resources/js/logout.js"></script>
</body>
</html>
