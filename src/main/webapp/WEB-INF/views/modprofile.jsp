<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 정보 변경</title>
    <link href="../../resources/css/join.css" rel="stylesheet" type="text/css" />
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
<div id="backgroundImg">
    <div id="joinB">
        <!-- 닫기 버튼 -->
        <button class="close" onclick="location.href='http://localhost:8081'">X</button>
        <form action="main/profile/updateProfile.do" method="post">
            <div id="joinS">
                <!-- 회원가입 폼 -->
                <!-- 아이디 -->
                <p><h6>아이디</h6>
                <div id="idInputBox" class="inputBox">
                    <input type="text" name="userId" id="idInput" class="input" maxlength="14" placeholder="${modprofile.userId}" autofocus>
                </div>
                <h5 id="idInputText1" class="InputText">* 아이디를 입력해주세요.</h5>
                <h5 id="idInputText2" class="InputText">* 아이디는 5~14자리로 입력해주세요.</h5>
                <h5 id="idInputText3" class="InputText">* 한글, 특수문자는 포함될 수 없습니다.</h5>
                <h5 id="idInputText4" class="InputText" style="color: green">* 사용 가능한 아이디입니다.</h5>
                <h5 id="idInputText5" class="InputText">* 이미 사용중인 아이디입니다.</h5>
                <!-- 비밀번호 -->
                <p><h6>비밀번호</h6>
                <div id="pwInputBox" class="inputBox">
                    <input type="password" name="password" id="pwInput" class="input" maxlength="16" placeholder="${modprofile.password}" autocomplete="new-password">
                </div>
                <h5 id="pwInputText1" class="InputText">* 비밀번호를 입력해주세요.</h5>
                <h5 id="pwInputText2" class="InputText">* 비밀번호는 6~16자리로 입력해주세요.</h5>
                <h5 id="pwInputText3" class="InputText">* 특수문자를 포함시켜 주세요.</h5>

                <!-- 비밀번호 확인 -->
                <p><h6>비밀번호 확인</h6>
                <div id="pwCInputBox" class="inputBox">
                    <input type="password" name="pwC" id="pwCInput" class="input" maxlength="14" placeholder="Password" autocomplete="new-password">
                </div>
                <h5 id="pwCInputText1" class="InputText">* 비밀번호 입력해주세요.</h5>
                <h5 id="pwCInputText2" class="InputText">* 비밀번호는 6~16자리로 입력해주세요.</h5>
                <h5 id="pwCInputText3" class="InputText">* 비밀번호가 일치하지 않습니다.</h5>

                <!-- 닉네임 -->
                <p><h6>닉네임</h6>
                <div id="nicknameInputBox" class="inputBox">
                    <input type="text" name="userNickname" id="nicknameInput" class="input" maxlength="10" placeholder="${modprofile.userNickname}">
                </div>
                <h5 id="nicknameInputText1" class="InputText">* 닉네임을 입력해주세요.</h5>
                <h5 id="nicknameInputText2" class="InputText">* 닉네임은 2~10자리로 입력해주세요.</h5>
                <h5 id="nicknameInputText3" class="InputText" style="color: green">* 사용 가능한 닉네임입니다.</h5>
                <h5 id="nicknameInputText4" class="InputText">* 이미 사용중인 닉네임입니다.</h5>
                <!--                <h5 id="nickInputText2" class="InputText">사용할 수 없는 닉네임 입니다.</h5>-->

                <!-- 이름 -->
                <p><h6>이름</h6>
                <div id="nameInputBox" class="inputBox">
                    <input type="text" name="userName" id="nameInput" class="input" maxlength="5" placeholder="${modprofile.userName}">
                </div>
                <h5 id="nameInputText1" class="InputText">* 이름을 입력해주세요.</h5>
                <h5 id="nameInputText2" class="InputText">* 한글만 입력해주세요.</h5>
                <h5 id="nameInputText3" class="InputText">* 이름을 확인해주세요.</h5>

                <!-- 이메일 -->
                <p><h6>이메일</h6>
                <div id="emailInputBox" class="inputBox">
                    <input type="text" name="email" id="emailInput" class="input" maxlength="25" placeholder="${modprofile.email}">
                </div>
                <h5 id="emailInputText1" class="InputText">* 이메일을 입력해주세요.</h5>
                <h5 id="emailInputText2" class="InputText">* 한글은 입력할 수 없습니다.</h5>
                <h5 id="emailInputText3" class="InputText">* 이메일 양식을 확인해주세요.</h5>

                <!-- 생년월일 -->
                <p><h6>생년월일</h6>
                <div id="birthdayInputBox" class="inputBox">
                    <input type="text" name="birthday" id="birthdayInput" class="input" maxlength="8" placeholder="${modprofile.birthday}">
                </div>
                <h5 id="birthdayInputText1" class="InputText">* 생년월일을 입력해주세요.</h5>
                <h5 id="birthdayInputText2" class="InputText">* 숫자 외에는 입력할 수 없습니다.</h5>
                <h5 id="birthdayInputText3" class="InputText">* 생년월일을 확인해주세요.</h5>

                <!-- 핸드폰 번호 -->
                <p><h6>핸드폰 번호</h6>
                <div id="phoneNumberInputBox" class="inputBox">
                    <input type="text" name="phoneNumber" id="phoneNumberInput" class="input" maxlength="11" placeholder="${modprofile.phoneNumber}">
                </div>
                <h5 id="phoneNumberInputText1" class="InputText">* 핸드폰번호를 입력해주세요.</h5>
                <h5 id="phoneNumberInputText2" class="InputText">* 숫자 외에는 입력할 수 없습니다.</h5>
                <h5 id="phoneNumberInputText3" class="InputText">* 핸드폰번호를 확인해주세요.</h5>

                <!-- 회원가입 -->
                <button type="submit" id="joinBtn" disabled>수정</button>
            </div>
        </form>
    </div>
</div>

<script src="../../resources/js/modprofile.js"></script>

</body>
</html>
