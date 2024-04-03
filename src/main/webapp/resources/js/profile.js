
    function sendMessageProc(receiverId) {
        // AJAX 요청을 통해 서버에 아이디 찾기 요청
        console.log("receiverId : " + receiverId);
        $.ajax({
            url: "/findRoom.do",
            type: "POST", // 요청의 유형을 POST로 지정합니다.
            data: {receiverId: receiverId}, // 'receiverId'를 데이터로 추가합니다.
            success: function(response) {
                if(confirm("채팅방 생성이 완료되었습니다.")){
                    // 로그인 페이지로 리다이렉트
                    window.location.href = '/main/chatRooms';
                }else {
                    window.history.replaceState(null, null, window.location.pathname);
                }
            },
            error: function(xhr, status, error) {
                alert("오류가 발생했습니다.");
            }
        });
    }