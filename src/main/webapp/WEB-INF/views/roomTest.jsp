<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- jQuery 라이브러리 먼저 추가 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>프로필 페이지</h1>
<%--${userId} 님의 프로필 <br><br>--%>
<input type="text" id="userId"> 님의 프로필 <br><br>

<%--<button id="sendMessageBtn" type="button" data-userId="${userId}">메세지 보내기</button>--%>
<button id="sendMessageBtn" type="button">메세지 보내기</button>

</body>
<script>

    $(document).ready(function() {
        let sendMessageBtn = $('#sendMessageBtn');

        sendMessageBtn.on('click', function (){
            let receiverId = $('#userId').val();
            // AJAX 요청을 통해 서버에 아이디 찾기 요청
            $.ajax({
                url: "/findRoom.do",
                type: "POST", // 요청의 유형을 POST로 지정합니다.
                data: {receiverId: receiverId}, // 'receiverId'를 데이터로 추가합니다.
                success: function(response) {
                    alert("채팅방 생성이 완료되었습니다.");
                },
                error: function(xhr, status, error) {
                    alert("오류가 발생했습니다.");
                }
            });
        });
    });

</script>
</html>