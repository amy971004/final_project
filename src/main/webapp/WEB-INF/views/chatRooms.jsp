<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>채팅방 목록</title>
    <!-- jQuery 라이브러리 먼저 추가 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../../resources/css/reset.css">
    <link rel="stylesheet" href="../../resources/css/nav.css">
    <link rel="stylesheet" href="../../resources/css/chatRooms.css">
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
    <!-- 테스트용 나중에 지워야함 -->
    <a href="#" class="side_icon"><i onclick="location.href='http://localhost:8081/main/roomTest.do'" class="fa-regular fa-bookmark "></i></a>
    <!-- 로그아웃 -->
    <a class="side_icon"><i id="logoutBtn" class="fa-solid fa-arrow-right-from-bracket"></i></a>
</div>
<main id="mainContents">
    <div id="m1">
        <div id="roomList">
            <div id="roomListTop">
                <img id="profile" src="#" alt="Image Preview">
                <br><span>${user.userName}<br>${user.userId}</span>
                <h2>메세지</h2>
            </div>
            <div id="roomListBottom">
                <ul>
                    <c:forEach var="room" items="${rooms}">
                        <div class="room" onclick="selectRoom('${room.roomId}');" data-roomId="${room.roomId}">
                            <li>${room.roomName}<br>${room.roomReceiverName}<br>해당 채팅방의 roomId: ${room.roomId}</li>
                        </div>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div id="chatting">
            <div id="message">

            </div>
            <input type="text" id="messageInput">
            <button type="button" onclick="sendMessage();">전송</button>
        </div>
    </div>
</main>
</body>
<script src="../../resources/js/chatRooms.js"></script>
<script src="../../resources/js/logout.js"></script>
</html>
