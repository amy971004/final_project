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
            <form>
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

<script src="../../resources/js/login.js"></script>

</body>
</html>