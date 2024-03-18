<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link href="../../resources/css/login.css" rel="stylesheet" type="text/css" />
    <!-- jQuery 라이브러리 추가 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- jQuery UI 라이브러리 추가 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <!-- jQuery UI CSS 추가 -->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
</head>
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
                <div id="find">
                    <a type="button" id="findById">ID 찾기</a> / <a type="button" id="findByPw">PW 찾기</a>
                </div>
            </form>
        </div>
    </div>

    <%--    아이디 찾기 창--%>
    <div id="b1" style="display: none; height: 200px">
        <button id="idClose" class="close">X</button>
        <div id="b2">
            <form action="findById.do" method="post" id="findByIdForm" accept-charset="UTF-8">
                <div class="inputBox" style="margin-bottom: 15px; margin-top: 15px">
                    <input type="text" id="nameInput" name="userName" class="input" autofocus maxlength="7" placeholder="이름">
                </div>
                <div  class="inputBox">
                    <input type="text" id="birthInput1" name="userBirth" class="input" maxlength="8" placeholder="생년월일 8자리">
                </div>
                <p> <input type="button" value="찾기" id="findByIdBtn" class="btn1">
            </form>
        </div>
    </div>

    <%--    비밀번호 찾기 창--%>
    <div id="c1" style="display: none; height: 200px">
        <button id="pwClose" class="close">X</button>
        <div id="c2">
            <form action="findByPw.do" method="post" id="findByPwForm" accept-charset="UTF-8">
                <div class="inputBox" style="margin-bottom: 15px; margin-top: 15px">
                    <input type="text" id="idInput1" name="userId" class="input" autofocus maxlength="14" placeholder="아이디">
                </div>
                <div  class="inputBox">
                    <input type="password" id="birthInput2" name="userBirth" class="input" maxlength="8" placeholder="생년월일 8자리">
                </div>
                <p> <input type="button" value="찾기" id="findByPwBtn" class="btn1">
            </form>
        </div>
    </div>

    <%--    비밀번호 재설정 창 --%>
    <div id="d1" style="display: none; height: 500px">
        <button id="pwChangeClose" class="close">X</button>
        <div id="d2">
            <form action="pwChange.do" method="post" id="pwChangeForm" accept-charset="UTF-8">
                <!-- 비밀번호 -->
                <div id="pwInputBox" class="inputBox1">
                    <input type="password" name="userPw" id="pwInput1" class="input" maxlength="16" placeholder="Password" autocomplete="new-password">
                </div>
                <h5 id="pwInputText1" class="InputText">* 비밀번호를 입력해주세요.</h5>
                <h5 id="pwInputText2" class="InputText">* 비밀번호는 6~16자리로 입력해주세요.</h5>
                <h5 id="pwInputText3" class="InputText">* 특수문자를 포함시켜 주세요.</h5>

                <!-- 비밀번호 확인 -->
                <div id="pwCInputBox" class="inputBox1">
                    <input type="password" name="pwC" id="pwCInput1" class="input" maxlength="14" placeholder="Password" autocomplete="new-password">
                </div>
                <h5 id="pwCInputText1" class="InputText">* 비밀번호 입력해주세요.</h5>
                <h5 id="pwCInputText2" class="InputText">* 비밀번호는 6~16자리로 입력해주세요.</h5>
                <h5 id="pwCInputText3" class="InputText">* 비밀번호가 일치하지 않습니다.</h5>
                <p> <input type="button" value="변경" id="pwChangeBtn" class="btn1">
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