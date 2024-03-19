<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link href="../../resources/css/join.css" rel="stylesheet" type="text/css" />
</head>
<script src="https://kit.fontawesome.com/56a665fb69.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
<div id="backgroundImg">
    <div id="joinB">
        <!-- 닫기 버튼 -->
        <button class="close" onclick="location.href='http://localhost:8081'">X</button>
        <div id="joinS">
            <i id="profile" class="fa-sharp fa-solid fa-circle-user fa-10x" ></i>
            <div id="output"></div>
            <div id="e1">
                <label>
                    <textarea id="aboutMe"></textarea>
                </label>
            </div>
            <!-- 회원가입 폼 -->
            <form action="addMember.do" method="post">
                <div id="joinS1">
                    <!-- 아이디 -->
                    <p><h6>아이디</h6>
                    <div id="idInputBox" class="inputBox">
                        <i class="fa-solid fa-user" style="padding-left: 15px"></i>
                        <input type="text" name="userId" id="idInput" class="input" maxlength="14" placeholder="ID" autofocus >
                    </div>
                    <h5 id="idInputText1" class="InputText">* 아이디를 입력해주세요.</h5>
                    <h5 id="idInputText2" class="InputText">* 아이디는 5~14자리로 입력해주세요.</h5>
                    <h5 id="idInputText3" class="InputText">* 한글, 특수문자는 포함될 수 없습니다.</h5>
                    <h5 id="idInputText4" class="InputText" style="color: green">* 사용 가능한 아이디입니다.</h5>
                    <h5 id="idInputText5" class="InputText">* 이미 사용중인 아이디입니다.</h5>

                    <!-- 비밀번호 -->
                    <p><h6>비밀번호</h6>
                    <div id="pwInputBox" class="inputBox">
                        <i class="fa-solid fa-key" style="padding-left: 14px"></i>
                        <input type="password" name="userPw" id="pwInput" class="input" maxlength="16" placeholder="Password" autocomplete="new-password">
                    </div>
                    <h5 id="pwInputText1" class="InputText">* 비밀번호를 입력해주세요.</h5>
                    <h5 id="pwInputText2" class="InputText">* 비밀번호는 6~16자리로 입력해주세요.</h5>
                    <h5 id="pwInputText3" class="InputText">* 특수문자를 포함시켜 주세요.</h5>

                    <!-- 비밀번호 확인 -->
                    <p><h6>비밀번호 확인</h6>
                    <div id="pwCInputBox" class="inputBox">
                        <i class="fa-solid fa-key" style="padding-left: 14px"></i>
                        <input type="password" name="pwC" id="pwCInput" class="input" maxlength="14" placeholder="Password" autocomplete="new-password">
                    </div>
                    <h5 id="pwCInputText1" class="InputText">* 비밀번호 입력해주세요.</h5>
                    <h5 id="pwCInputText2" class="InputText">* 비밀번호는 6~16자리로 입력해주세요.</h5>
                    <h5 id="pwCInputText3" class="InputText">* 비밀번호가 일치하지 않습니다.</h5>

                    <!-- 닉네임 -->
                    <p><h6>닉네임</h6>
                    <div id="nicknameInputBox" class="inputBox">
                        <i class="fa-regular fa-id-card" style="padding-left: 12px"></i>
                        <input type="text" name="userNickname" id="nicknameInput" class="input" maxlength="10" placeholder="NickName">
                    </div>
                    <h5 id="nicknameInputText1" class="InputText">* 닉네임을 입력해주세요.</h5>
                    <h5 id="nicknameInputText2" class="InputText">* 닉네임은 2~10자리로 입력해주세요.</h5>
                    <h5 id="nicknameInputText3" class="InputText" style="color: green">* 사용 가능한 닉네임입니다.</h5>
                    <h5 id="nicknameInputText4" class="InputText">* 이미 사용중인 닉네임입니다.</h5>
                    <!--                <h5 id="nickInputText2" class="InputText">사용할 수 없는 닉네임 입니다.</h5>-->
                </div>

                <div id="joinS2">
                    <!-- 이름 -->
                    <p><h6>이름</h6>
                    <div id="nameInputBox" class="inputBox">
                        <i class="fa-regular fa-id-card" style="padding-left: 12px"></i>
                        <input type="text" name="userName" id="nameInput" class="input" maxlength="5" placeholder="Name">
                    </div>
                    <h5 id="nameInputText1" class="InputText">* 이름을 입력해주세요.</h5>
                    <h5 id="nameInputText2" class="InputText">* 한글만 입력해주세요.</h5>
                    <h5 id="nameInputText3" class="InputText">* 이름을 확인해주세요.</h5>

                    <!-- 이메일 -->
                    <p><h6>이메일</h6>
                    <div id="emailInputBox" class="inputBox">
                        <i class="fa-regular fa-envelope" style="padding-left: 13px"></i>
                        <input type="text" name="userEmail" id="emailInput" class="input" maxlength="25" placeholder="Email">
                    </div>
                    <h5 id="emailInputText1" class="InputText">* 이메일을 입력해주세요.</h5>
                    <h5 id="emailInputText2" class="InputText">* 한글은 입력할 수 없습니다.</h5>
                    <h5 id="emailInputText3" class="InputText">* 이메일 양식을 확인해주세요.</h5>

                    <!-- 생년월일 -->
                    <p><h6>생년월일</h6>
                    <div id="birthdayInputBox" class="inputBox">
                        <i class="fa-regular fa-calendar" style="padding-left: 15px"></i>
                        <input type="text" name="userBirthday" id="birthdayInput" class="input" maxlength="8" placeholder="ex) 20240101">
                    </div>
                    <h5 id="birthdayInputText1" class="InputText">* 생년월일을 입력해주세요.</h5>
                    <h5 id="birthdayInputText2" class="InputText">* 숫자 외에는 입력할 수 없습니다.</h5>
                    <h5 id="birthdayInputText3" class="InputText">* 생년월일을 확인해주세요.</h5>

                    <!-- 핸드폰 번호 -->
                    <p><h6>핸드폰 번호</h6>
                    <div id="phoneNumberInputBox" class="inputBox">
                        <i class="fa-solid fa-phone" style="padding-left: 13px"></i>
                        <input type="text" name="userPhoneNumber" id="phoneNumberInput" class="input" maxlength="11" placeholder="ex) 01012345678">
                    </div>
                    <h5 id="phoneNumberInputText1" class="InputText">* 핸드폰번호를 입력해주세요.</h5>
                    <h5 id="phoneNumberInputText2" class="InputText">* 숫자 외에는 입력할 수 없습니다.</h5>
                    <h5 id="phoneNumberInputText3" class="InputText">* 핸드폰번호를 확인해주세요.</h5>
                </div>

                <!-- 회원가입 -->
                <button type="submit" id="joinBtn" disabled><i class="fa-solid fa-user-plus fa-bounce"></i></button>
            </form>
        </div>
    </div>
</div>

<script src="../../resources/js/join.js"></script>

</body>
</html>
