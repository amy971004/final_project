<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link href="../../resources/css/login.css" rel="stylesheet" type="text/css" />

</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
<div id="backgroundImg">
    <div id="a1">
        <div id="a2">
            <form action="login.do" method="post" id="loginForm">
                <div class="inputBox" style="margin-bottom: 15px">
                    <input type="text" id="idInput" name="userId" class="input" autofocus maxlength="14" placeholder="ID">
                </div>
                <div  class="inputBox">
                    <input type="password" id="pwInput" name="userPw" class="input" maxlength="14" placeholder="Password">
                </div>
                <p> <button type="button" id="loginBtn" class="btn1">로그인</button>
                    <button type="button" id="joinBtn" class="btn2" onclick="location.href='http://localhost:8081/member/joinMember.do'">회원가입</button>
            </form>
        </div>
    </div>
</div>
<% if(session.getAttribute("errorMessage") != null) { %>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        // 세션에서 에러 메시지를 가져옴
        let errorMessage = "<%= session.getAttribute("errorMessage") %>";
        // 에러 메시지가 있으면 경고창을 띄움
        if(errorMessage.trim().length > 0) {
            alert(errorMessage);
        }
        <%
            // JavaScript 코드가 실행된 후 서버 측에서 세션에서 에러 메시지를 삭제
            // 주의: JSP 코드는 서버에서 처리되므로, 클라이언트의 이벤트와 동기화되지 않음
            session.removeAttribute("errorMessage");
        %>
    });
</script>
<% } %>
<script src="../../resources/js/login.js"></script>

</body>
</html>