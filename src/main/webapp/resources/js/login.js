window.onload = function() {

}

$(document).ready(function() {

    let loginBtn = $('#loginBtn'); // 로그인 버튼
    let idInput = $('#idInput'); // 아이디 입력창
    let pwInput = $('#pwInput'); // 비밀번호 입력창

    // 로그인 버튼을 눌렀을 때 이벤트
    loginBtn.on("click", function () {
        
        // 아이디와 비밀번호 모두 입력하지 않았을 때
        if(idInput.val().length === 0 && pwInput.val().length === 0){
            alert("정보를 입력해주세요.")
            
        // 아이디만 입력하지 않았을 때
        } else if(idInput.val().length === 0){
            alert("아이디를 입력해주세요.");
            
        // 비밀번호만 입력하지 않았을 때
        } else if(pwInput.val().length === 0){
            alert("비밀번호를 입력해주세요.");

        // 아디이와 비밀번호 모두 입력됐을 때만 폼으 제출
        } else {
            $("#loginForm").submit(); // 폼을 제출합니다.
        }
    });

    let loginModal = $('#a1');
    let findById = $('#findById');
    let findByPw = $('#findByPw');
    let findByIdModal = $('#b1');
    let findByPwModal = $('#c1');
    let pwChangeModal = $('#d1');
    let idClose = $('#idClose');
    let pwClose = $('#pwClose');
    let findByIdBtn = $('#findByIdBtn');
    let findByPwBtn = $('#findByPwBtn');

    let idInput1 = $('#idInput1');
    let nameInput = $('#nameInput');
    let birthInput1 = $('#birthInput1');
    let birthInput2 = $('#birthInput2');

    findById.on("click", function () {
        findByIdModal.show();
        jQuery("#b1").draggable();
    });

    findByPw.on("click", function () {
        findByPwModal.show();
        jQuery("#c1").draggable();
    });

    idClose.on("click", function () {
        findByIdModal.hide();
    });

    pwClose.on("click", function () {
        findByPwModal.hide();
    });

    findByIdBtn.on("click", function () {
        let userName = nameInput.val();
        let userBirth = birthInput1.val();

        // AJAX 요청을 통해 서버에 아이디 찾기 요청
        $.ajax({
            url: "/findById.do",
            type: "post",
            data: {userName: userName, userBirth: userBirth},
            success: function(response) {
                if(response !== "not found") {
                    alert("찾으시는 아이디는 " + response + "입니다.");
                } else {
                    alert("일치하는 정보가 없습니다.");
                }
            },
            error: function(xhr, status, error) {
                alert("오류가 발생했습니다.");
            }
        });
    });

    findByPwBtn.on("click", function () {
        let userId = idInput1.val();
        let userBirth = birthInput2.val();

        // AJAX 요청을 통해 서버에 아이디 찾기 요청
        $.ajax({
            url: "/findByPw.do",
            type: "post",
            data: {userId: userId, userBirth: userBirth},
            success: function(response) {
                if(response === "OK") {
                    alert("일치하는 정보 찾기 성공");
                    findByPwModal.hide();
                    pwChangeModal.show();
                    jQuery("#d1").draggable();
                } else {
                    alert("일치하는 정보가 없습니다.");
                }
            },
            error: function(xhr, status, error) {
                alert("오류가 발생했습니다.");
            }
        });
    });

    let pwValid = false; // 비밀번호 유효성 상태 - 기본값 실패
    let pwCValid = false; // 비밀번호 확인 유효성 상태 - 기본값 실패

    <!-- ############################### 비밀번호 ############################### -->
    let pwInput1 = $('#pwInput1'); // 비밀번호 입력창
    let pwInputBox = $('#pwInputBox'); // 비밀번호 입력창 테두리
    let pwInputText1 = $('#pwInputText1'); // 비밀번호를 입력해주세요.
    let pwInputText2 = $('#pwInputText2'); // 비밀번호는 6~16자리로 입력해주세요.
    let pwInputText3 = $('#pwInputText3'); // 특수문자를 포함시켜주세요.

    <!-- 포커스 잡혔을 때 이벤트 -->
    pwInput1.focus(function () {
        pwInputBox.addClass('inputBox-focus'); // 테두리를 파란색으로
    });

    <!-- 포커스 풀렸을 때 이벤트 -->
    pwInput1.blur(function () {
        pwInputBox.removeClass('inputBox-focus'); // 파란색 테두리 제거
    });

    <!-- 키를 입력했을 때 이벤트 / 글자수, 입력값 일치 유효성 검사 -->
    pwInput1.on("input blur", function () {
        // 비밀번호 입력창의 값이 비어있을 때
        if(pwInput1.val().length === 0){
            pwInputText1.show(); // 비밀번호를 입력해주세요. <- 보이기
            pwInputText2.hide(); // 비밀번호는 6~16자리로 입력해주세요. <- 숨기기
            pwInputText3.hide(); // 특수문자를 포함시켜주세요. <- 숨기기
            pwValid = false; // 유효성 검증 실패

            // 비밀번호 입력창의 글자수가 6보다 작을 때
        } else if (pwInput1.val().length < 6){
            pwInputText1.hide(); // 비밀번호를 입력해주세요. <- 숨기기
            pwInputText2.show(); // 비밀번호는 6~16자리로 입력해주세요. <- 보이기
            pwInputText3.hide(); // 특수문자를 포함시켜주세요. <- 숨기기
            pwValid = false; // 유효성 검증 실패

            // 비밀번호 입력창의 글자수가 6보다 크거나 같을 때
        } else if (pwInput1.val().length >= 6) {
            pwInputText2.hide(); // 비밀번호는 6~16자리로 입력해주세요. <- 숨기기
            if(/[\!@#\$%\^&\*\(\)]/.test(pwInput1.val())){
                pwInputText3.hide();  // 특수문자를 포함시켜주세요. <- 숨기기
                pwValid = true; // 유효성 검증 성공

                // 비밀번호 확인 입력창의 입력값과 비밀번호 확인의 입력값이 같을 때
                if (pwInput1.val() === pwCInput.val()){
                    pwCInputText3.hide(); // 비밀번호가 일치하지 않습니다. <- 숨기기
                    pwValid = true; // 유효성 검증 성공
                    pwCValid = true; // 유효성 검증 성공
                } else {
                    pwCInputText3.show();  // 비밀번호가 일치하지 않습니다. <- 보이기
                    pwCValid = false; // 유효성 검증 실패
                }

            } else {
                pwInputText3.show();  // 특수문자를 포함시켜주세요. <- 보이기
                pwValid = false; // 유효성 검증 실패
            }
        }
        changeChk()
    });

    // 유효성 검증 결과 시각화
    pwInput1.on("input blur", function () {
        changeChk()
        // 유효성 검증 결과에 따른 입력창 테두리의 시각적인 변화
        if (pwValid === true){
            pwInputBox.addClass('success'); // 테두리를 초록색으로
            pwInputBox.removeClass('error'); // 테두리를 빨간색으로
            pwInput1.removeClass('errorText'); // font-color - red 제거
            changeChk()
        } else {
            pwInputBox.addClass('error'); // 테두리를 빨간색으로
            pwInputBox.removeClass('success'); // 초록색 테두리 제거
            pwCInputBox.addClass('error'); // 테두리를 빨간색으로
            pwCInputBox.removeClass('success'); // 초록색 테두리 제거
            changeChk()
        }
        if (pwCValid === true){
            pwCInputBox.addClass('success'); // 테두리를 초록색으로
            pwCInputBox.removeClass('error'); // 테두리를 빨간색으로
            pwCInput.removeClass('errorText'); // font-color - red 제거
            changeChk()
        } else {
            pwInputBox.addClass('error'); // 테두리를 빨간색으로
            pwInputBox.removeClass('success'); // 초록색 테두리 제거
            pwCInputBox.addClass('error'); // 테두리를 빨간색으로
            pwCInputBox.removeClass('success'); // 초록색 테두리 제거
            changeChk()
        }
        changeChk()
    });

    <!-- ############################### 비밀번호 확인 ############################### -->
    let pwCInput = $('#pwCInput1'); // 비밀번호 확인 입력창
    let pwCInputBox = $('#pwCInputBox'); // // 비밀번호 확인 입력창 테두리
    let pwCInputText1 = $('#pwCInputText1'); // 비밀번호를 입력해주세요.
    let pwCInputText2 = $('#pwCInputText2'); // 비밀번호는 6~16자리로 입력해주세요.
    let pwCInputText3 = $('#pwCInputText3'); // 비밀번호가 일치하지 않습니다.

    <!-- 포커스 잡혔을 때 이벤트 -->
    pwCInput.focus(function () {
        pwCInputBox.addClass('inputBox-focus'); // 테두리를 파란색으로
    });

    <!-- 포커스 풀렸을 때 이벤트 -->
    pwCInput.blur(function () {
        pwCInputBox.removeClass('inputBox-focus'); // 파란색 테두리 제거
    });

    <!-- 키를 입력했을 때 이벤트 / 글자수, 입력값 일치 유효성 검사 -->
    // idInput.keydown(function () {
    pwCInput.on("input blur", function () {
        // 비밀번호 확인 입력창의 값이 비어있을 때
        if (pwCInput.val().length === 0){
            pwCInputText1.show(); // 비밀번호를 입력해주세요. <- 보이기
            pwCInputText2.hide(); // 비밀번호는 6~16자리로 입력해주세요. <- 숨기기
            pwCInputText3.hide(); // 비밀번호가 일치하지 않습니다. <- 숨기기
            pwCValid = false; // 유효성 검증 실패

            // 비밀번호 확인 입력창의 글자수가 6보다 작을 때
        } else if (pwCInput.val().length < 6) {
            pwCInputText1.hide(); // 비밀번호를 입력해주세요. <- 숨기기
            pwCInputText2.show(); // 비밀번호는 6~16자리로 입력해주세요. <- 보이기
            pwCValid = false; // 유효성 검증 실패

            // 비밀번호 확인 입력창의 글자수가 6이상일 때
        } else if (pwCInput.val().length >= 6) {
            pwCInputText2.hide(); // 비밀번호는 6~16자리로 입력해주세요. <- 숨기기

            // 비밀번호 입력창의 입력값과 비밀번호 확인의 입력값이 같을 때
            if (pwInput.val() === pwCInput.val()) {
                pwCInputText3.hide(); // 비밀번호가 일치하지 않습니다. <- 숨기기
                pwCValid = true; // 유효성 검증 성공
            } else {
                pwCInputText3.show(); // 비밀번호가 일치하지 않습니다. <- 보이기
                pwCValid = false; // 유효성 검증 실패
            }
        }
        changeChk()
    });

    // 유효성 검증 결과 시각화
    pwCInput.on("input blur", function () {
        changeChk()
        // 유효성 검증 결과에 따른 입력창 테두리의 시각적인 변화
        if (pwValid === true){
            pwInputBox.addClass('success'); // 테두리를 초록색으로
            pwInputBox.removeClass('error'); // 테두리를 빨간색으로
            pwInput.removeClass('errorText'); // font-color - red 제거
            changeChk()
        } else {
            pwInputBox.addClass('error'); // 테두리를 빨간색으로
            pwInputBox.removeClass('success'); // 초록색 테두리 제거
            pwCInputBox.addClass('error'); // 테두리를 빨간색으로
            pwCInputBox.removeClass('success'); // 초록색 테두리 제거
            changeChk()
        }
        if (pwCValid === true){
            pwCInputBox.addClass('success'); // 테두리를 초록색으로
            pwCInputBox.removeClass('error'); // 테두리를 빨간색으로
            pwCInput.removeClass('errorText'); // font-color - red 제거
            changeChk()
        } else {
            pwInputBox.addClass('error'); // 테두리를 빨간색으로
            pwInputBox.removeClass('success'); // 초록색 테두리 제거
            pwCInputBox.addClass('error'); // 테두리를 빨간색으로
            pwCInputBox.removeClass('success'); // 초록색 테두리 제거
            changeChk()
        }
        changeChk()
    });

    // ############################### 비밀번호 재설정 버튼 ###############################
    let pwChangeBtn = $('#pwChangeBtn'); // 비밀번호 재설정 버튼
    function changeChk() {
        // 모든 유효성 검사 성공시 회원가입버튼 활성화
        if (pwValid && pwCValid) {
            pwChangeBtn.addClass('joinBtn1').removeClass('joinBtn2').prop('disabled', false);

            // 유효성 검사가 1개라도 실패시 회원가입 버튼 비활성화
        } else {
            pwChangeBtn.removeClass('joinBtn1').addClass('joinBtn2').prop('disabled', true);
        }
    }

});