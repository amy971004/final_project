<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link href="../../resources/css/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../../resources/css/reset.css">
    <link rel="stylesheet" href="../../resources/css/common.css">
    <link rel="stylesheet" href="../../resources/css/style.css">
    <link rel="stylesheet" href="../../resources/css/nav.css">
    <link rel="stylesheet" href="../../resources/css/search.css">
    <title>인스타그램</title>
</head>
<div class="sideBar">
    <a href="#" class="side_icon" style="padding-top: 20px;font-size: 25px">L</a>
    <!-- 검색 -->
    <a class="side_icon"><i class="fa-solid fa-magnifying-glass " onclick="$('#searchBackground').show()"></i></a>
    <!-- 홈 -->
    <a class="side_icon"><i onclick="location.href='http://localhost:8081/main/post/mainPost.do'" class="fa-solid fa-house"></i></a>
    <!-- 팔로우 목록 게시물 -->
    <a class="side_icon"><i onclick="location.href='http://localhost:8081/main/post/followPost.do'" class="fa-solid fa-user-plus"></i></a>
    <!-- 업로드 -->
    <a class="side_icon"><i onclick="location.href='http://localhost:8081/main/post/uploadPost.do'" id="uploadBtn" class="fa-solid fa-plus"></i></a>
    <!-- 내프로필 -->
    <a class="side_icon"><i onclick="location.href='http://localhost:8081/main/profile/profileView.do'" class="fa-regular fa-user"></i></a>
    <!-- 메세지 -->
    <a class="side_icon"><i onclick="location.href='http://localhost:8081/main/chatRooms'" class="fa-regular fa-message"></i></a>
    <!-- 알림 -->
    <a href="#" class="side_icon"><i class="fa-solid fa-bell "></i></a>
    <!-- 북마크 -->
    <a href="#" class="side_icon"><i onclick="location.href='http://localhost:8081/main/post/bookMark.do'" class="fa-regular fa-bookmark "></i></a>
    <!-- 로그아웃 -->
    <a class="side_icon"><i id="logoutBtn" class="fa-solid fa-arrow-right-from-bracket"></i></a>
</div>
<div id="searchBackground" style="display: none">
    <div id="searchC">
        <div id="searchT">
            <div id="searchBox">
                <input id="searchInput" type="text" placeholder="검색">
                <i id="searchBtn" class="searchI fa-solid fa-magnifying-glass " style="cursor: pointer"></i>
            </div>
        </div>
        <!-- 본문 1 -->
        <div id="searchM1">
            <!-- 카테고리 선택 -->
            <div id="cate1" class="M1-1"><h5>유저</h5></div>
            <div id="cate2" class="M1-1"><h5>게시판</h5></div>
            <div id="cate3" class="M1-1"><h5>태그</h5></div>
            <div id="searchPoint" style="display: none"></div>
        </div>
        <div id="searchM2">

        </div>
        <div id="searchB">

        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../../resources/js/search.js"></script>

