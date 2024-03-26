<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link href="../../resources/css/login.css" rel="stylesheet" type="text/css" />
    <!-- jQuery 라이브러리 먼저 추가 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- 그 다음 jQuery UI 라이브러리 추가 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <!-- jQuery UI CSS 추가 -->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <%-- 아이콘 js --%>
    <script src="https://kit.fontawesome.com/56a665fb69.js" crossorigin="anonymous"></script>
</head>
<body>
<div id="backgroundImg">
    <div id="a1">
        <br>
        <p><h1 style="padding-top: 18px; text-align: center"><i class="fa-solid fa-lock fa-shake"></i> Login</h1>
        <div id="a2">
            <form action="login.do" method="post" id="loginForm">
                <p><h6>아이디</h6>
                <div id="idInputBox" class="inputBox" style="margin-bottom: 15px">
                    <i class="fa-solid fa-user" style="padding-left: 15px"></i>
                    <input type="text" id="idInput" name="userId" class="input" autofocus maxlength="14" placeholder="ID">
                </div>
                <p><h6>비밀번호</h6>
                <div id="pwInputBox1" class="inputBox">
                    <i class="fa-solid fa-key" style="padding-left: 14px"></i>
                    <input type="password" id="pwInput" name="userPw" class="input" maxlength="14" placeholder="Password">
                </div>
                <div style="margin-top: 34px">
                    <button type="button" id="loginBtn" class="btn1"><i class="fa-solid fa-arrow-right-to-bracket"></i>  Login</button>
                    <button type="button" id="joinBtn" class="btn2" onclick="location.href='http://localhost:8081/member/joinMember.do'"><i class="fa-solid fa-users"></i> Sign in</button>
                </div>
                <div id="find" style="margin-top: 26px">
                    <a type="button" id="findById">forget ID?</a> / <a type="button" id="findByPw">forget PW?</a>
                </div>
            </form>
        </div>
    </div>

    <%--    아이디 찾기 창--%>
    <div id="b1" style="display: none">
        <button id="idClose" class="close">X</button>
        <br><h1 style="padding-top: 18px; text-align: center"><i class="fa-solid fa-magnifying-glass fa-beat-fade"></i> Find Your ID</h1>
        <div id="b2">
            <form action="findById.do" method="post" id="findByIdForm" accept-charset="UTF-8">
                <br><h6>이름</h6>
                <div class="inputBox">
                    <i class="fa-solid fa-user" style="padding-left: 15px"></i>
                    <input type="text" id="nameInput" name="userName" class="input" autofocus maxlength="7" placeholder="이름">
                </div>
                <br><h6>생년월일</h6>
                <div  class="inputBox">
                    <i class="fa-regular fa-calendar" style="padding-left: 15px"></i>
                    <input type="text" id="birthInput1" name="userBirth" class="input" maxlength="8" placeholder="생년월일 8자리">
                </div>
                <input type="button" value="Go" id="findByIdBtn" class="btn1">
            </form>
        </div>
    </div>

    <%--    비밀번호 찾기 창--%>
    <div id="c1" style="display:none;">
        <button id="pwClose" class="close">X</button>
        <br>
        <h1 style="padding-top: 18px; text-align: center"><i class="fa-solid fa-magnifying-glass fa-beat-fade"></i> Find Your PW</h1>
        <div id="c2">
            <form action="findByPw.do" method="post" id="findByPwForm" accept-charset="UTF-8">
                <br><h6>아이디</h6>
                <div class="inputBox">
                    <i class="fa-solid fa-user" style="padding-left: 15px"></i>
                    <input type="text" id="idInput1" name="userId" class="input" autofocus maxlength="14" placeholder="아이디">
                </div>
                <br><h6>생년월일</h6>
                <div  class="inputBox">
                    <i class="fa-regular fa-calendar" style="padding-left: 15px"></i>
                    <input type="password" id="birthInput2" name="userBirth" class="input" maxlength="8" placeholder="생년월일 8자리">
                </div>
                <input type="button" value="Go" id="findByPwBtn" class="btn1">
            </form>
        </div>
    </div>

    <%--    비밀번호 재설정 창 --%>
    <div id="d1" style="display: none;">
        <button id="pwChangeClose" class="close">X</button>
        <br><h1 style="padding-top: 18px; text-align: center"><i class="fa-solid fa-repeat fa-flip"></i> New PW</h1>
        <div id="d2">
            <form action="pwChange.do" method="post" id="pwChangeForm" accept-charset="UTF-8">
                <!-- 비밀번호 -->
                <br><h6>비밀번호</h6>
                <div id="pwInputBox" class="inputBox1">
                    <i class="fa-solid fa-key" style="padding-left: 14px"></i>
                    <input type="password" name="userPw" id="pwInput1" class="input" maxlength="16" placeholder="Password" autocomplete="new-password">
                </div>
                <h5 id="pwInputText1" class="InputText">* 비밀번호를 입력해주세요.</h5>
                <h5 id="pwInputText2" class="InputText">* 비밀번호는 6~16자리로 입력해주세요.</h5>
                <h5 id="pwInputText3" class="InputText">* 특수문자를 포함시켜 주세요.</h5>

                <!-- 비밀번호 확인 -->
                <br><h6>비밀번호 확인</h6>
                <div id="pwCInputBox" class="inputBox1">
                    <i class="fa-solid fa-key" style="padding-left: 14px"></i>
                    <input type="password" name="userPwC" id="pwCInput1" class="input" maxlength="14" placeholder="Password" autocomplete="new-password">
                </div>
                <h5 id="pwCInputText1" class="InputText">* 비밀번호 입력해주세요.</h5>
                <h5 id="pwCInputText2" class="InputText">* 비밀번호는 6~16자리로 입력해주세요.</h5>
                <h5 id="pwCInputText3" class="InputText">* 비밀번호가 일치하지 않습니다.</h5>
            </form>
        </div>
        <input type="button" value="Go" id="pwChangeBtn" class="btn1" disabled>
    </div>

    <%-- 비회원 오류창 --%>
    <div id="loginWarningDialog" title="알림" style="display:none;">
        <p>로그인 후 이용해주세요.</p>
    </div>

</div>
<script src="../../resources/js/login.js"></script>

<script>
    // $(window).on("load", function() {
    //
    //     setTimeout(function() {
    //         let warning = new URLSearchParams(window.location.search).get('warning');
    //         console.log(warning);
    //
    //         if (warning === 'loginRequired') {
    //             alert('로그인 후 이용해주세요.');
    //             window.history.replaceState(null, null, window.location.pathname);
    //         } else if (warning === 'loginFail') {
    //             alert('아이디와 비밀번호가 일치하지 않습니다.');
    //             window.history.replaceState(null, null, window.location.pathname);
    //         }
    //
    //     }, 10);
    // });
</script>

</body>
</html>