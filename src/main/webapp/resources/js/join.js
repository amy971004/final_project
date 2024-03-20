$(document).ready(function() {

    // ############################### 회원가입 버튼 ###############################
    let joinBtn = $('#joinBtn'); // 회원가입 버튼
    let joinChkIcon = $('#joinChkIcon'); // 회원가입 유효성 체크 아이콘
    function joinChk() {
        // 모든 유효성 검사 성공시 회원가입버튼 활성화
        if (idValid && pwValid && pwCValid && nicknameValid && nameValid && emailValid && birthdayValid && phoneNumberValid) {
            joinBtn.addClass('joinBtn1').removeClass('joinBtn2').prop('disabled', false);
            joinChkIcon.css('color', 'green');
        // 유효성 검사가 1개라도 실패시 회원가입 버튼 비활성화
        } else {
            joinBtn.removeClass('joinBtn1').addClass('joinBtn2').prop('disabled', true);
            joinChkIcon.css('color', '#ce1b1b');
        }
    }
    
    // 회원가입 버튼 클릭시
    joinBtn.click(function() {
        if (idValid && pwValid && pwCValid && nicknameValid && nameValid && emailValid && birthdayValid && phoneNumberValid){
            alert("회원가입 완료!");
        } else {
            alert("회원정보를 확인해주세요.");
        }
    });



    // ############################### 각 필드의 유효성 상태를 나타내는 변수들 ###############################
    let idValid = false; // 아이디 유효성 상태 - 기본값 실패
    let pwValid = false; // 비밀번호 유효성 상태 - 기본값 실패
    let pwCValid = false; // 비밀번호 확인 유효성 상태 - 기본값 실패
    let nicknameValid = false; // 닉네임 유효성 상태 - 기본값 실패
    let nameValid = false; // 이름 유효성 상태 - 기본값 실패
    let emailValid = false; // 이메일 유효성 상태 - 기본값 실패
    let birthdayValid = false; // 생년월일 유효성 상태 - 기본값 실패
    let phoneNumberValid = false; // 핸드폰 번호 유효성 상태 - 기본값 실패

    // ############################### 아이디 ###############################
    let idInput = $('#idInput'); // 아이디 입력창
    let idInputBox = $('#idInputBox'); // 아이디 입력창 테두리
    let idInputText1 = $('#idInputText1'); // 아이디를 입력해주세요.
    let idInputText2 = $('#idInputText2'); // 아이디는 5~14자리로 입력해주세요.
    let idInputText3 = $('#idInputText3'); // 한글, 특수문자는 포함될 수 없습니다.
    let idInputText4 = $('#idInputText4'); // 사용 가능한 아이디입니다.
    let idInputText5 = $('#idInputText5'); // 이미 사용중인 아이디입니다.

    <!-- 포커스 잡혔을 때 이벤트 -->
    idInput.focus(function () {
        idInputBox.addClass('inputBox-focus'); // 테두리를 파란색으로
    });
    <!-- 포커스 풀렸을 때 이벤트 -->
    idInput.blur(function () {
        idInputBox.removeClass('inputBox-focus'); // 파란색 테두리 제거
    });

    // 키를 입력했을 때 이벤트 / 글자수, 영어와 숫자 외 입력 유효성 검사
    idInput.on("input blur", function () {
        let inputVal = $(this).val();

        // 초기 상태 설정
        idInputText1.toggle(inputVal.length === 0); // 아이디 입력 유도 메시지
        idInputText2.hide(); // 글자수 제한 안내 메시지 숨김
        idInputText3.hide(); // 한글, 특수문자 포함 안내 메시지 숨김
        idInputText4.hide(); // 사용 가능한 아이디 메시지 숨김
        idInputText5.hide(); // 이미 사용중인 아이디 메시지 숨김

        // 유효성 초기 상태 설정
        idValid = false;

        // 입력값이 비었을 경우, 이후 로직은 실행하지 않고 종료
        if (inputVal.length === 0) {
            joinChk();
            return;
        }

        // 영어와 숫자 외 문자가 포함된 경우, 이후 로직은 실행하지 않고 종료
        if (!/^[a-zA-Z0-9]+$/.test(inputVal)) {
            idInputText3.show();
            joinChk();
            return;
        }

        // 아이디 길이가 5보다 적을 경우, 이후 로직은 실행하지 않고 종료
        if (inputVal.length < 5) {
            idInputText2.show();
            joinChk();
            return;
        }

        // 모든 클라이언트 측 유효성 검사를 통과했을 때만 서버로 중복 검사 요청
        $.ajax({
            url: "/member/checkId.do",
            type: "GET",
            data: { userId: inputVal },
            success: function(response) {
                if(response === 'OK') {
                    // 사용 가능한 아이디
                    idValid = true;
                    idInputBox.addClass('success').removeClass('error');
                    idInputText4.show();
                } else {
                    // 이미 사용중인 아이디
                    idInputBox.addClass('error').removeClass('success');
                    idInputText5.show();
                }
                joinChk();
            },
            error: function() {
                alert("서버 요청 중 오류가 발생했습니다. 다시 시도해 주세요.");
                joinChk();
            }
        });
    });

    // 유효성 검증 결과 시각화
    idInput.on("input blur", function () {
        if (idValid === true){
            idInputBox.addClass('success'); // 테두리를 초록색으로
            idInputBox.removeClass('error'); // 빨간색 테두리 제거
            idInput.removeClass('errorText'); // font-color - red 제거
        } else {
            idInputBox.addClass('error'); // 테두리를 빨간색으로
            idInputBox.removeClass('success'); // 초록색 테두리 제거
        }
        joinChk();
    });

    <!-- ############################### 비밀번호 ############################### -->
    let pwInput = $('#pwInput'); // 비밀번호 입력창
    let pwInputBox = $('#pwInputBox'); // 비밀번호 입력창 테두리
    let pwInputText1 = $('#pwInputText1'); // 비밀번호를 입력해주세요.
    let pwInputText2 = $('#pwInputText2'); // 비밀번호는 6~16자리로 입력해주세요.
    let pwInputText3 = $('#pwInputText3'); // 특수문자를 포함시켜주세요.

    <!-- 포커스 잡혔을 때 이벤트 -->
    pwInput.focus(function () {
        pwInputBox.addClass('inputBox-focus'); // 테두리를 파란색으로
    });

    <!-- 포커스 풀렸을 때 이벤트 -->
    pwInput.blur(function () {
        pwInputBox.removeClass('inputBox-focus'); // 파란색 테두리 제거
    });

    <!-- 키를 입력했을 때 이벤트 / 글자수, 입력값 일치 유효성 검사 -->
    pwInput.on("input blur", function () {
        // 비밀번호 입력창의 값이 비어있을 때
        if(pwInput.val().length === 0){
            pwInputText1.show(); // 비밀번호를 입력해주세요. <- 보이기
            pwInputText2.hide(); // 비밀번호는 6~16자리로 입력해주세요. <- 숨기기
            pwInputText3.hide(); // 특수문자를 포함시켜주세요. <- 숨기기
            pwValid = false; // 유효성 검증 실패

        // 비밀번호 입력창의 글자수가 6보다 작을 때
        } else if (pwInput.val().length < 6){
            pwInputText1.hide(); // 비밀번호를 입력해주세요. <- 숨기기
            pwInputText2.show(); // 비밀번호는 6~16자리로 입력해주세요. <- 보이기
            pwInputText3.hide(); // 특수문자를 포함시켜주세요. <- 숨기기
            pwValid = false; // 유효성 검증 실패

        // 비밀번호 입력창의 글자수가 6보다 크거나 같을 때
        } else if (pwInput.val().length >= 6) {
            pwInputText2.hide(); // 비밀번호는 6~16자리로 입력해주세요. <- 숨기기
            if(/[\!@#\$%\^&\*\(\)]/.test(pwInput.val())){
                pwInputText3.hide();  // 특수문자를 포함시켜주세요. <- 숨기기
                pwValid = true; // 유효성 검증 성공
                joinChk();

                // 비밀번호 확인 입력창의 입력값과 비밀번호 확인의 입력값이 같을 때
                if (pwInput.val() === pwCInput.val()){
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
    });

    // 유효성 검증 결과 시각화
    pwInput.on("input blur", function () {
        // 유효성 검증 결과에 따른 입력창 테두리의 시각적인 변화
        if (pwValid === true){
            pwInputBox.addClass('success'); // 테두리를 초록색으로
            pwInputBox.removeClass('error'); // 테두리를 빨간색으로
            pwInput.removeClass('errorText'); // font-color - red 제거
        } else {
            pwInputBox.addClass('error'); // 테두리를 빨간색으로
            pwInputBox.removeClass('success'); // 초록색 테두리 제거
            pwCInputBox.addClass('error'); // 테두리를 빨간색으로
            pwCInputBox.removeClass('success'); // 초록색 테두리 제거
        }
        if (pwCValid === true){
            pwCInputBox.addClass('success'); // 테두리를 초록색으로
            pwCInputBox.removeClass('error'); // 테두리를 빨간색으로
            pwCInput.removeClass('errorText'); // font-color - red 제거
        } else {
            pwInputBox.addClass('error'); // 테두리를 빨간색으로
            pwInputBox.removeClass('success'); // 초록색 테두리 제거
            pwCInputBox.addClass('error'); // 테두리를 빨간색으로
            pwCInputBox.removeClass('success'); // 초록색 테두리 제거
        }
        joinChk();
    });

    <!-- ############################### 비밀번호 확인 ############################### -->
    let pwCInput = $('#pwCInput'); // 비밀번호 확인 입력창
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
                joinChk();
            } else {
                pwCInputText3.show(); // 비밀번호가 일치하지 않습니다. <- 보이기
                pwCValid = false; // 유효성 검증 실패
            }
        }
    });

    // 유효성 검증 결과 시각화
    pwCInput.on("input blur", function () {
        // 유효성 검증 결과에 따른 입력창 테두리의 시각적인 변화
        if (pwValid === true){
            pwInputBox.addClass('success'); // 테두리를 초록색으로
            pwInputBox.removeClass('error'); // 테두리를 빨간색으로
            pwInput.removeClass('errorText'); // font-color - red 제거
        } else {
            pwInputBox.addClass('error'); // 테두리를 빨간색으로
            pwInputBox.removeClass('success'); // 초록색 테두리 제거
            pwCInputBox.addClass('error'); // 테두리를 빨간색으로
            pwCInputBox.removeClass('success'); // 초록색 테두리 제거
        }
        if (pwCValid === true){
            pwCInputBox.addClass('success'); // 테두리를 초록색으로
            pwCInputBox.removeClass('error'); // 테두리를 빨간색으로
            pwCInput.removeClass('errorText'); // font-color - red 제거
        } else {
            pwInputBox.addClass('error'); // 테두리를 빨간색으로
            pwInputBox.removeClass('success'); // 초록색 테두리 제거
            pwCInputBox.addClass('error'); // 테두리를 빨간색으로
            pwCInputBox.removeClass('success'); // 초록색 테두리 제거
        }
        joinChk();
    });

    <!-- ############################### 닉네임 ############################### -->
    let nicknameInput = $('#nicknameInput'); // 닉네임 입력창
    let nicknameInputBox = $('#nicknameInputBox'); // 닉네임 입력창 테두리
    let nicknameInputText1 = $('#nicknameInputText1'); // 닉네임을 입력해주세요.
    let nicknameInputText2 = $('#nicknameInputText2'); // 닉네임은 2~10자리로 입력해주세요.
    let nicknameInputText3 = $('#nicknameInputText3'); // 사용 가능한 닉네임입니다.
    let nicknameInputText4 = $('#nicknameInputText4'); // 이미 사용중인 닉네임입니다.

    <!-- 포커스 잡혔을 때 이벤트 -->
    nicknameInput.focus(function () {
        nicknameInputBox.addClass('inputBox-focus'); // 테두리를 파란색으로
    });

    <!-- 포커스 풀렸을 때 이벤트 -->
    nicknameInput.blur(function () {
        nicknameInputBox.removeClass('inputBox-focus'); // 파란색 테두리 제거
    });

    <!-- 키를 입력했을 때 이벤트 / 글자수, 중복 유효성 검사 -->
    nicknameInput.on("input blur", function () {
        
        // 입력값이 없을 때
        if(nicknameInput.val().length === 0){
            nicknameInputText1.show(); // 닉네임을 입력해주세요. <- 보이기
            nicknameInputText2.hide(); // 닉네임은 2~10자리로 입력해주세요. <- 숨기기
            nicknameInputText3.hide(); // 사용 가능한 닉네임입니다. <- 숨기기
            nicknameInputText4.hide(); // 이미 사용중인 닉네임입니다. <- 숨기기
            nicknameValid = false; // 유효성 검증 실패
            
        // 입력값이 1글자 이하일 때
        } else if (nicknameInput.val().length <= 1){
            nicknameInputText1.hide(); // 닉네임을 입력해주세요. <- 숨기기
            nicknameInputText2.show(); // 닉네임은 2~10자리로 입력해주세요. <- 보이기
            nicknameInputText3.hide(); // 사용 가능한 닉네임입니다. <- 숨기기
            nicknameInputText4.hide(); // 이미 사용중인 닉네임입니다. <- 숨기기
            nicknameValid = false; // 유효성 검증 실패
            
        // 입력값이 1보다 클 때
        } else if (nicknameInput.val().length > 1) {
            nicknameInputText2.hide(); // 닉네임은 2~10자리로 입력해주세요. <- 숨기기
            
            // 입력된 닉네임 값을 가져옵니다.
            let userNickname = nicknameInput.val();

            // AJAX 요청을 시작합니다.
            $.ajax({
                url: "/member/checkNickname.do", // 서버의 URL, 사용자 닉네임 중복 검사를 처리할 주소
                type: "GET", // HTTP 요청 방식
                data: { userNickname: userNickname }, // 서버로 보낼 데이터, 사용자가 입력한 닉네임
                success: function(response) {
                    // 서버로부터의 응답 처리
                    if(response === 'OK') {
                        // 사용 가능한 닉네임
                        nicknameValid = true; // 유효성 검증 성공
                        nicknameInputBox.addClass('success'); // 테두리를 초록색으로
                        nicknameInputBox.removeClass('error'); // 빨간색 테두리 제거
                        nicknameInput.removeClass('errorText'); // font-color - red 제거
                        nicknameInputText3.show(); // 사용 가능한 닉네임입니다. <- 보이게
                        nicknameInputText4.hide(); // 이미 사용중인 닉네임입니다. <- 숨기기
                        joinChk();
                    } else {
                        // 이미 사용중인 닉네임
                        nicknameValid = false; // 유효성 검증 실패
                        nicknameInputBox.addClass('error'); // 테두리를 빨간색으로
                        nicknameInputBox.removeClass('success'); // 초록색 테두리 제거
                        nicknameInputText4.show(); // 이미 사용중인 닉네임입니다. <- 보이게
                        nicknameInputText3.hide(); // 사용 가능한 닉네임입니다. <- 숨기기
                        joinChk();
                    }
                },
                error: function() {
                    // 요청 실패 처리
                    alert("서버 요청 중 오류가 발생했습니다. 다시 시도해 주세요.");
                }
            });
            joinChk();
        }
    });
    
    // 유효성 검증 결과 시각화
    nicknameInput.on("input blur", function () {
        // 유효성 검증 결과에 따른 입력창 테두리의 시각적인 변화
        if (nicknameValid === true){
            nicknameInputBox.addClass('success'); // 테두리를 초록색으로
            nicknameInputBox.removeClass('error'); // 테두리를 빨간색으로
            idInput.removeClass('errorText'); // font-color - red 제거
        } else {
            nicknameInputBox.addClass('error'); // 테두리를 빨간색으로
            nicknameInputBox.removeClass('success'); // 초록색 테두리 제거
        }
        joinChk();
    });

    <!-- ############################### 이름 ############################### -->
    let nameInput = $('#nameInput'); // 이름 입력창
    let nameInputBox = $('#nameInputBox'); // 이름 입력창 테두리
    let nameInputText1 = $('#nameInputText1'); // 이름을 입력해주세요.
    let nameInputText2 = $('#nameInputText2'); // 한글만 입력해주세요.
    let nameInputText3 = $('#nameInputText3'); // 이름을 확인해주세요.

    <!-- 포커스 잡혔을 때 이벤트 -->
    nameInput.focus(function () {
        nameInputBox.addClass('inputBox-focus'); // 테두리를 파란색으로
    });

    <!-- 포커스 풀렸을 때 이벤트 -->
    nameInput.blur(function () {
        nameInputBox.removeClass('inputBox-focus'); // 파란색 테두리 제거
    });

    // 키를 입력했을 때 이벤트 / 한글 외 입력 유효성 검사
    nameInput.on("input blur", function () {
        nameInputText1.hide();  // 이름을 입력해주세요. <- 숨기기
        
        // 입력값이 없을 때
        if(nameInput.val().length === 0){
            nameInputText1.show(); // 이름을 입력해주세요. <- 보이게
            nameInputText2.hide(); // 한글만 입력해주세요. <- 숨기기
            nameInputText3.hide(); // 이름을 확인해주세요. <- 숨기기
            nameValid = false; // 유효성 검증 실패
        }
        // 입력된 값이 모두 한글로만 이루어져 있는지 확인
        else if (/^[가-힣]*$/.test($(this).val())) {
            // 모든 글자가 한글로만 이루어진 경우 처리할 내용
            nameInputText2.hide(); // 한글만 입력해주세요. <- 숨기기
            nameInputText3.hide(); // 이름을 확인해주세요. <- 숨기기
            nameValid = true; // 유효성 검증 성공
            joinChk();
        }
        // 입력된 값이 자음, 모음으로만 이루어져 있는지 확인
        else if (/^[ㄱ-ㅎㅏ-ㅣ]*$/.test($(this).val())) {
            // 모든 글자가 자음, 모음으로만 이루어진 경우 처리할 내용
            nameInputText3.show(); // 이름을 확인해주세요. <- 보이게
            nameValid = false; // 유효성 검증 실패
        }
        else {
            // 한글, 자음, 모음 이외의 문자가 포함된 경우 처리할 내용
            nameInputText2.show(); // 한글만 입력해주세요. <- 보이게
            nameValid = false; // 유효성 검증 실패
        }

    });

    // 유효성 검증 결과에 따른 입력창 테두리의 시각적인 변화
    nameInput.on("input blur", function () {
        if (nameValid === true){
            nameInputBox.addClass('success'); // 테두리를 초록색으로
            nameInputBox.removeClass('error'); // 테두리를 빨간색으로
            nameInput.removeClass('errorText'); // font-color - red 제거
        } else {
            nameInputBox.addClass('error'); // 테두리를 빨간색으로
            nameInputBox.removeClass('success'); // 초록색 테두리 제거
        }
        joinChk();
    });

    <!-- ############################### 이메일 ############################### -->
    let emailInput = $('#emailInput'); // 이메일 입력창
    let emailInputBox = $('#emailInputBox'); // 이메일 입력창 테두리
    let emailInputText1 = $('#emailInputText1'); // 이메일을 입력해주세요.
    let emailInputText2 = $('#emailInputText2'); // 한글은 입력할 수 없습니다.
    let emailInputText3 = $('#emailInputText3'); // 이메일 양식을 확인해주세요.

    <!-- 포커스 잡혔을 때 이벤트 -->
    emailInput.focus(function () {
        emailInputBox.addClass('inputBox-focus'); // 테두리를 파란색으로
    });

    <!-- 포커스 풀렸을 때 이벤트 -->
    emailInput.blur(function () {
        emailInputBox.removeClass('inputBox-focus'); // 파란색 테두리 제거
    });

    // 키를 입력했을 때 한글 입력 방지 및 이메일 형식(@와 . 포함)의 유효성 이벤트
    emailInput.on("input blur", function () {

        // 입력값이 없을 경우 에러 메시지 표시
        if (emailInput.val().length === 0) {
            emailInputText1.show(); // 이메일을 입력해주세요. <- 보이게
            emailInputText2.hide(); // 한글은 입력할 수 없습니다. <- 숨기기
            emailInputText3.hide(); // 이메일 양식을 확인해주세요. <- 숨기기
            emailValid = false; // 유효성 검증 실패
        } else {
            
            // 입력값이 한글 또는 자음 모음을 포함하는지 확인
            if (/[ㄱ-ㅎㅏ-ㅣ가-힣]/.test(emailInput.val())) {
                // 한글 또는 자음 모음이 포함된 경우 에러 처리
                emailInputText1.hide(); // 이메일을 입력해주세요. <- 숨기기
                emailInputText2.show(); // 한글은 입력할 수 없습니다. <- 보이게
                emailInputText3.hide(); // 이메일 양식을 확인해주세요. <- 숨기기
                emailValid = false; // 유효성 검증 실패
            } else {
                // 입력값에 @와 . 중 하나라도 빠져있는지 확인하여 처리
                if (emailInput.val().indexOf('@') === -1 || emailInput.val().indexOf('.') === -1) {
                    // @나 . 중 하나라도 빠져있는 경우 에러 처리
                    emailInputText1.hide(); // 이메일을 입력해주세요. <- 숨기기
                    emailInputText2.hide(); // 한글은 입력할 수 없습니다. <- 숨기기
                    emailInputText3.show(); // 이메일 양식을 확인해주세요. <- 보이게
                    emailValid = false; // 유효성 검증 실패
                } else {
                    // 이메일 형식(@와 . 포함)인 경우
                    emailInputText1.hide(); // 이메일을 입력해주세요. <- 숨기기
                    emailInputText2.hide(); // 한글은 입력할 수 없습니다. <- 숨기기
                    emailInputText3.hide(); // 이메일 양식을 확인해주세요. <- 숨기기
                    emailValid = true; // 유효성 검증 성공
                    joinChk();
                }
            }
        }
        joinChk();
    });

    // 유효성 검증 결과에 따른 입력창 테두리의 시각적인 변화
    emailInput.on("input blur", function () {
        if (emailValid === true) {
            emailInputBox.addClass('success'); // 테두리를 초록색으로
            emailInputBox.removeClass('error'); // 테두리를 빨간색으로
            emailInput.removeClass('errorText'); // font-color - red 제거
        } else {
            emailInputBox.addClass('error'); // 테두리를 빨간색으로
            emailInputBox.removeClass('success'); // 초록색 테두리 제거
        }
        joinChk();
    });

    <!-- ############################### 생년월일 ############################### -->
    let birthdayInput = $('#birthdayInput'); // 생년월일 입력창
    let birthdayInputBox = $('#birthdayInputBox'); // 생년월일 입력창 테두리
    let birthdayInputText1 = $('#birthdayInputText1'); // 생년월일을 입력해주세요.
    let birthdayInputText2 = $('#birthdayInputText2'); // 숫자 외에는 입력할 수 없습니다.
    let birthdayInputText3 = $('#birthdayInputText3'); // 생년월일을 확인해주세요.

    <!-- 포커스 잡혔을 때 이벤트 -->
    birthdayInput.focus(function () {
        birthdayInputBox.addClass('inputBox-focus'); // 테두리를 파란색으로
    });

    <!-- 포커스 풀렸을 때 이벤트 -->
    birthdayInput.blur(function () {
        birthdayInputBox.removeClass('inputBox-focus'); // 파란색 테두리 제거
    });

    // 키를 입력했을 때 숫자 외 입력 방지 유효성 이벤트
    birthdayInput.on("input blur", function () {
        // 입력값이 없을 경우 에러 메시지 표시
        if(birthdayInput.val().length === 0){
            birthdayInputText1.show(); // 생년월일을 입력해주세요. <- 보이게
            birthdayInputText2.hide(); // 숫자 외에는 입력할 수 없습니다. <- 숨기기
            birthdayInputText3.hide(); // 생년월일을 확인해주세요. <- 숨기기
            birthdayValid = false; // 유효성 검증 실패
        } else {
            // 입력값이 숫자가 아닌 경우 마지막 입력을 제거하고 에러 메시지 표시
            let inputValue = birthdayInput.val().replace(/\D/g, ''); // 숫자 이외의 문자 모두 제거
            if (inputValue !== birthdayInput.val()) {
                birthdayInput.val(inputValue); // 숫자 이외의 문자를 제거한 값으로 입력값 변경
                birthdayInputText2.show(); // 숫자 외에는 입력할 수 없습니다. <- 보이게
                birthdayValid = false; // 유효성 검증 실패
            } else {
                // 입력값이 숫자인 경우 에러 메시지 숨기고 성공 클래스 추가
                birthdayInputText1.hide(); // 숫자 외에는 입력할 수 없습니다. <- 숨기기
                birthdayInputText2.hide(); // 생년월일을 확인해주세요. <- 숨기기
                birthdayInputBox.removeClass('error');
            }

            if (birthdayInput.val().length < 8) {
                birthdayInputText3.show(); // 생년월일을 확인해주세요. <- 보이게
                birthdayValid = false; // 유효성 검증 실패
            } else if (birthdayInput.val().length === 8 && /^\d+$/.test(birthdayInput.val())){
                birthdayInputText2.hide(); // 숫자 외에는 입력할 수 없습니다. <- 숨기기
                birthdayInputText3.hide(); // 생년월일을 확인해주세요. <- 숨기기
                birthdayValid = true; // 유효성 검증 성공
                joinChk();
            } else {
                birthdayValid = false; // 유효성 검증 실패
            }
        }
    });

    // 유효성 검증 결과에 따른 입력창 테두리의 시각적인 변화
    birthdayInput.on("input blur", function () {
        if (birthdayValid === true){
            birthdayInputBox.addClass('success'); // 테두리를 초록색으로
            birthdayInputBox.removeClass('error'); // 테두리를 빨간색으로
            birthdayInput.removeClass('errorText'); // font-color - red 제거
        } else {
            birthdayInputBox.addClass('error'); // 테두리를 빨간색으로
            birthdayInputBox.removeClass('success'); // 초록색 테두리 제거
        }
        joinChk();
    });

    <!-- 핸드폰 번호 -->
    let phoneNumberInput = $('#phoneNumberInput'); // 핸드폰 번호 입력창
    let phoneNumberInputBox = $('#phoneNumberInputBox'); // 핸드폰 번호 입력창 테두리
    let phoneNumberInputText1 = $('#phoneNumberInputText1'); // 핸드폰번호를 입력해주세요.
    let phoneNumberInputText2 = $('#phoneNumberInputText2'); // 숫자 외에는 입력할 수 없습니다.
    let phoneNumberInputText3 = $('#phoneNumberInputText3'); // 핸드폰번호를 확인해주세요.

    <!-- 포커스 잡혔을 때 이벤트 -->
    phoneNumberInput.focus(function () {
        phoneNumberInputBox.addClass('inputBox-focus'); // 테두리를 파란색으로
    });

    <!-- 포커스 풀렸을 때 이벤트 -->
    phoneNumberInput.blur(function () {
        phoneNumberInputBox.removeClass('inputBox-focus'); // 파란색 테두리 제거
    });

    // 키를 입력했을 때 숫자 외 입력 방지 유효성 이벤트
    phoneNumberInput.on("input blur", function () {
        // 입력값이 없을 경우 에러 메시지 표시
        if(phoneNumberInput.val().length === 0){
            phoneNumberInputText1.show(); // 핸드폰번호를 입력해주세요. <- 보이게
            phoneNumberInputText2.hide(); // 숫자 외에는 입력할 수 없습니다. <- 숨기기
            phoneNumberInputText3.hide(); // 핸드폰번호를 확인해주세요. <- 숨기기
            phoneNumberValid = false; // 유효성 검사 실패
        } else {
            // 입력값이 숫자가 아닌 경우 마지막 입력을 제거하고 에러 메시지 표시
            let inputValue = phoneNumberInput.val().replace(/\D/g, ''); // 숫자 이외의 문자 모두 제거
            if (inputValue !== phoneNumberInput.val()) {
                phoneNumberInput.val(inputValue); // 숫자 이외의 문자를 제거한 값으로 입력값 변경
                // 에러 메시지 표시
                phoneNumberInputText2.show(); // 숫자 외에는 입력할 수 없습니다. <- 숨기기
                phoneNumberValid = false; // 유효성 검사 실패
            } else {
                // 입력값이 숫자인 경우 에러 메시지 숨기고 성공 클래스 추가
                phoneNumberInputText1.hide(); // 핸드폰번호를 입력해주세요. <- 숨기기
                phoneNumberInputText2.hide(); // 숫자 외에는 입력할 수 없습니다. <- 숨기기
                phoneNumberInputBox.removeClass('error');
            }

            if (phoneNumberInput.val().length === 11 && /^\d+$/.test(phoneNumberInput.val())){
                phoneNumberInputText3.hide(); // 핸드폰번호를 확인해주세요. <- 숨기기
                phoneNumberValid = true; // 유효성 검사 성공
                joinChk();
            } else {
                phoneNumberValid = false; // 유효성 검사 실패
                phoneNumberInputText3.show(); // 핸드폰번호를 확인해주세요. <- 보이게
            }
        }
    });

    // 유효성 검증 결과에 따른 입력창 테두리의 시각적인 변화
    phoneNumberInput.on("input blur", function () {
        if (phoneNumberValid === true) {
            phoneNumberInputBox.addClass('success'); // 테두리를 초록색으로
            phoneNumberInputBox.removeClass('error'); // 테두리를 빨간색으로
            phoneNumberInput.removeClass('errorText'); // font-color - red 제거
        } else {
            phoneNumberInputBox.addClass('error'); // 테두리를 빨간색으로
            phoneNumberInputBox.removeClass('success'); // 초록색 테두리 제거
        }
        joinChk();
    });

    // 프로필 및 닉네임 자동갱신
    // 사용자 입력을 가져와서 출력 요소에 반영하는 함수
    nicknameInput.on('keyup', function() {
        let input = $(this).val(); // 입력값 가져오기
        $('#output').text(input); // 출력 요소에 입력값 반영
    });

    // 프로필 소개
    let profileInput = $('#aboutMe');
    let profileInputBox = $('#e1');

    <!-- 포커스 잡혔을 때 이벤트 -->
    profileInput.focus(function () {
        profileInputBox.css('border-color', '#213454'); // 테두리를 color: #213454; 색으로
        profileInputBox.css('background-color', 'rgba(255, 255, 255, 0.5)');
    });

    <!-- 포커스 풀렸을 때 이벤트 -->
    profileInput.blur(function () {
        profileInputBox.css('border-color', '#646161'); // 테두리를 #646161 색으로
        profileInputBox.css('background-color', 'rgba(255, 255, 255, 0)');
    });


});
