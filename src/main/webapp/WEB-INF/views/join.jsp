<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="image" value="${image}"/>
<c:set var="nickname" value="${nickname}"/>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

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

    <%-- 회원가입 프로필 이미지 미리보기 --%>
    <script>
        function previewFile() {
            let preview = document.getElementById('profile'); // 이전 예제와 일치하는 ID로 변경
            let file    = document.querySelector('input[type=file]').files[0];
            let reader  = new FileReader();

            reader.onloadend = function () {
                preview.src = reader.result;
                preview.style.display = 'block'; // 이미지가 성공적으로 로드되면 보이도록 설정
            }

            if (file) {
                reader.readAsDataURL(file); // 파일이 존재하면 읽기 시작
            } else {
                preview.src = "";
                preview.style.display = 'none'; // 파일이 없으면 미리보기 숨김
            }
        }

        // onchange 이벤트 리스너에 함수를 직접 할당하는 대신 아래와 같이 jQuery를 사용해 변경 가능
        $('#imgUpload').change(function() {
            previewFile(); // 파일이 변경될 때 함수 호출
        });
    </script>
    <div id="joinB">
        <!-- 닫기 버튼 -->
        <button class="close" onclick="location.href='http://localhost:8081'">X</button>
        <div id="joinS">
            <!-- 회원가입 폼 -->
            <form action="${contextPath}/member/addMember.do" method="post" enctype="multipart/form-data">
                <i id="profileIcon" class="fa-sharp fa-solid fa-circle-user fa-10x" ></i>
                <!-- 이미지 미리보기를 위한 img 태그 추가 -->
                <c:choose>
                    <%-- 카카오톡으로 로그인을 했을 시 image의 값이 있음
                        -> 바로 preview가 뜨고 파라미터 값으로 받을 수 있도록 설정
                    --%>
                    <c:when test="${image != null}">
                        <input type="text" name="kakaoImg" value="${image}" style="display: none">
                        <img id="profile" src="${image}" alt="Image Preview" style="display: block">
                    </c:when>
                    <c:otherwise>
                        <img id="profile" src="#" alt="Image Preview">
                    </c:otherwise>
                </c:choose>
                <input id="imgUpload" type="file" name="file" onchange="previewFile()">
                <p><h5 id="profile1">프로필 소개</h5>
                <div id="output"></div>
                <div id="e1" class="e1">
                    <label>
                        <textarea name="introduction" id="aboutMe" class="aboutMe"></textarea>
                    </label>
                </div>
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
                        <c:choose>
                            <%--카카오톡에서 받은 닉네임으로 닉네임 값을 설정--%>
                            <c:when test="${nickname != null}">
                                <input type="text" name="userNickname" id="nicknameInput" class="input" maxlength="10" value=${nickname}>
                            </c:when>
                            <c:otherwise>
                                <input type="text" name="userNickname" id="nicknameInput" class="input" maxlength="10" placeholder="NickName">
                            </c:otherwise>
                        </c:choose>
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
                <button type="submit" id="joinBtn" disabled><i id="joinChkIcon" class="fa-solid fa-user-plus fa-bounce" style="color: #ce1b1b"></i></button>
            </form>
        </div>
    </div>
</div>

<script src="../../resources/js/join.js"></script>

</body>
</html>
