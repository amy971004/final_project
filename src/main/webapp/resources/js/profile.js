
    function sendMessageProc(receiverId) {
        // AJAX 요청을 통해 서버에 아이디 찾기 요청
        console.log("receiverId : " + receiverId);
        $.ajax({
            url: "/findRoom.do",
            type: "POST", // 요청의 유형을 POST로 지정합니다.
            data: {receiverId: receiverId}, // 'receiverId'를 데이터로 추가합니다.
            success: function(response) {
                if(response === "SUCCESS"){
                    alert("채팅방 생성이 완료되었습니다.");
                    window.location.href = '/main/chatRooms';
                } else {
                    alert("오류가 발생했습니다. - 생성 오류 -");
                }
            },
            error: function(xhr, status, error) {
                alert("오류가 발생했습니다. - 기타 오류 -");
            }
        });
    }