<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>메인 페이지</title>
    <link href="../../resources/css/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../../resources/css/reset.css">
    <link rel="stylesheet" href="../../resources/css/common.css">
    <link rel="stylesheet" href="../../resources/css/style.css">
</head>
<body>
<div class="logo">
    <a href="#" class="no-underline" style="padding-top: 20px">L</a>
    <!-- 홈 -->
    <a onclick="location.href='http://localhost:8081/post/mainPost.do'" class="no-underline"><i class="fa-solid fa-house"></i></a>
    <!-- 검색 -->
    <a href="#" class="no-underline"><i class="fa-solid fa-magnifying-glass "></i></a>
    <!-- 알림 -->
    <a href="#" class="no-underline"><i class="fa-solid fa-bell "></i></a>
    <!-- 북마크 -->
    <a href="#" class="no-underline"><i class="fa-regular fa-bookmark "></i></a>
    <!-- 업로드 -->
    <a onclick="location.href='http://localhost:8081/post/uploadPost.do'" class="no-underline"><i class="fa-solid fa-plus "></i></a>
    <!-- 내프로필 -->
    <a href="#" class="no-underline"><i class="fa-regular fa-user "></i></a>
    <!-- 로그아웃 -->
    <a class="no-underline"><i type="button" onclick="location.href='http://localhost:8081/logout.do'" id="logoutBtn" class="fa-solid fa-arrow-right-from-bracket" style="cursor: pointer"></i></a>
    <!-- 더보기 -->
    <a href="#" class="no-underline"><i class="fa-solid fa-bars "></i></a>
</div>
<script>
    $(document).ready(function() {
        // $('#logoutBtn').on('click', function (){
        //     $.ajax({
        //         type: "POST",
        //         url: "http://localhost:8081/logout.do",
        //         success: function(response) {
        //             // 로그아웃 처리 후 홈 페이지로 리다이렉트, 또는 사용자에게 로그아웃되었음을 알림
        //             window.location.href = 'http://localhost:8081';
        //         },
        //         error: function(xhr, status, error) {
        //             // 에러 처리
        //             alert("로그아웃 실패");
        //         }
        //     });
        // });
    });
</script>

