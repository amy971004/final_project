$(document).ready(function() {
    let logoutBtn = $('#logoutBtn');

    logoutBtn.on('click', function (){
        // 로그아웃 여부를 확인하는 경고창 표시
        let confirmLogout2 = confirm('로그아웃 하시겠습니까?');

        if (confirmLogout2) {
            // 사용자가 확인을 선택한 경우에만 로그아웃 처리를 수행함
            $.ajax({
                url: '/logout.do',
                type: 'GET',
                success: function(response) {
                    // 로그아웃 성공 시 콘솔에 메시지 출력
                    console.log('로그아웃 성공');
                    // 로그인 페이지로 리다이렉트
                    window.location.href = '/';
                },
                error: function(xhr, status, error) {
                    // 로그아웃 실패 시 콘솔에 메시지 출력
                    console.error('로그아웃 실패:', error);
                }
            });
        } else {
            // 취소를 눌렀을 때의 동작
            console.log('사용자가 로그아웃을 취소했습니다.');
        }
    });

});

$(window).on("load", function() {

    setTimeout(function() {
        let warning = new URLSearchParams(window.location.search).get('warning');
        console.log(warning);

        if (warning === 'logoutRequest') {
            // 로그아웃 여부를 확인하는 경고창 표시
            let confirmLogout1 = confirm('로그아웃 하시겠습니까?');
            if (confirmLogout1){
                // 사용자가 확인을 선택한 경우에만 로그아웃 처리를 수행함
                $.ajax({
                    url: '/logout.do',
                    type: 'GET',
                    success: function(response) {
                        // 로그아웃 성공 시 콘솔에 메시지 출력
                        console.log('로그아웃 성공');
                        // 로그인 페이지로 리다이렉트
                        window.location.href = '/';
                    },
                    error: function(xhr, status, error) {
                        // 로그아웃 실패 시 콘솔에 메시지 출력
                        console.error('로그아웃 실패:', error);
                    }
                });
            } else {
                window.history.replaceState(null, null, window.location.pathname);
            }
        }

    }, 10);
});
