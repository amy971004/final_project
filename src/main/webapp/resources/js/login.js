window.onload = function() {

}

$(document).ready(function() {

    let pwChangeClose = $('#pwChangeClose');

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

    pwChangeClose.on("click", function () {
        pwChangeModal.hide()
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
                    findByIdModal.hide();
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

// 키를 입력했을 때 이벤트 / 글자수, 입력값 일치 유효성 검사
    pwInput1.on("input blur", function () {
        const pwLength = pwInput1.val().length;
        const hasSpecialChar = /[\!@#\$%\^&\*\(\)]/.test(pwInput1.val());
        const passwordsMatch = pwInput1.val() === pwCInput.val();

        // 초기 상태 설정
        pwInputText1.hide();
        pwInputText2.hide();
        pwInputText3.hide();
        pwCInputText3.hide();

        pwValid = false;
        pwCValid = false;

        if (pwLength === 0) {
            pwInputText1.show(); // "비밀번호를 입력해주세요." 메시지 보이기
        } else if (pwLength < 6) {
            pwInputText2.show(); // "비밀번호는 6~16자리로 입력해주세요." 메시지 보이기
        } else if (!hasSpecialChar) {
            pwInputText3.show(); // "특수문자를 포함시켜주세요." 메시지 보이기
        } else {
            // 비밀번호 유효성 검증 성공
            pwValid = true;
            // 비밀번호와 비밀번호 확인이 일치하는지 검사
            if (!passwordsMatch) {
                pwCInputText3.show(); // "비밀번호가 일치하지 않습니다." 메시지 보이기
            } else {
                // 비밀번호 확인 유효성 검증 성공
                pwCValid = true;
            }
        }

        // 변경 사항 적용
        changeChk();

        // 유효성 검증 결과 시각화
        if (pwValid) {
            pwInputBox.removeClass('error').addClass('success');
            pwInput1.removeClass('errorText');
        } else {
            pwInputBox.removeClass('success').addClass('error');
        }

        // 비밀번호 확인 유효성 검증
        if (pwCValid) {
            pwCInputBox.removeClass('error').addClass('success');
            pwCInput.removeClass('errorText');
        } else {
            pwCInputBox.removeClass('success').addClass('error');
        }

        // 유효성 검증 후 변경 사항 적용을 위한 함수 한 번만 호출
        changeChk();
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

    // 비밀번호 확인 입력창에 대한 이벤트 핸들러
    pwCInput.on("input blur", function () {
        let pwCValue = pwCInput.val(); // 비밀번호 확인 입력창의 값
        let pwValue = pwInput1.val(); // 비밀번호 입력창의 값

        // 메시지를 초기 상태로 숨깁니다.
        pwCInputText1.hide();
        pwCInputText2.hide();
        pwCInputText3.hide();

        // 유효성 초기 상태 설정
        pwCValid = false;

        if (pwCValue.length === 0) {
            // 비밀번호를 입력해주세요.
            pwCInputText1.show();
            pwCValid = false;
        } else if (pwCValue.length < 6) {
            // 비밀번호는 6~16자리로 입력해주세요.
            pwCInputText2.show();
            pwCValid = false;
        } else if (pwValue !== pwCValue) {
            // 비밀번호가 일치하지 않습니다.
            pwCInputText3.show();
            pwCValid = false;
        } else {
            // 비밀번호가 일치하고, 적절한 길이를 가지고 있습니다.
            pwCValid = true;
        }

        changeChk(); // 유효성 검사 결과에 따른 시각적 변화를 적용하는 함수

        // 유효성 검증 결과에 따른 시각적 변화 적용
        if (pwValid) {
            pwInputBox.removeClass('error').addClass('success');
            pwInput1.removeClass('errorText');
        } else {
            pwInputBox.removeClass('success').addClass('error');
        }

        if (pwCValid) {
            pwCInputBox.removeClass('error').addClass('success');
            pwCInput.removeClass('errorText');
        } else {
            pwCInputBox.removeClass('success').addClass('error');
        }

        // 변경 사항을 체크하는 함수를 한 번만 호출합니다.
        changeChk();

    });

    // ############################### 비밀번호 재설정 버튼 ###############################
    let pwChangeBtn = $('#pwChangeBtn'); // 비밀번호 재설정 버튼
    function changeChk() {
        // 모든 유효성 검사 성공시 회원가입버튼 활성화
        if (pwValid && pwCValid) {
            pwChangeBtn.prop('disabled', false);

            // 유효성 검사가 1개라도 실패시 회원가입 버튼 비활성화
        } else {
            pwChangeBtn.prop('disabled', true);
        }
    }



});