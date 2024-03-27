<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>채팅방 목록</title>
</head>
<body>
<h2>채팅방 목록</h2>
    <ul>
        <c:forEach var="room" items="${rooms}">
            <li><a href="/chat/${room.roomId}">${room.roomName}</a></li>
        </c:forEach>
    </ul>
</body>
</html>
