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

        }
    });

});