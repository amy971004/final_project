<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>메인 페이지</title>
    <link href="../../resources/css/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../../resources/css/nav.css">
    <link rel="stylesheet" href="../../resources/css/reset.css">
    <link rel="stylesheet" href="../../resources/css/common.css">
    <link rel="stylesheet" href="../../resources/css/style.css">
</head>
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
<script>

</script>

