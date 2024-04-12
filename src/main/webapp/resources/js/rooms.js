let roomsSocket; // 채팅방 목록 웹소켓 연결을 관리할 변수
let userId = $('#userID').text();
let userName = $('#userName').text();

$(document).ready(function() {

    // 페이지 로드 시 웹소켓 연결 시도
    openRoomsSocket();

    console.log("data-userAccountId 값 가져오기 테스트 userId : " + accountId);

});

// 웹소켓 연결 열기
function openRoomsSocket() {


    if (roomsSocket !== undefined && roomsSocket.readyState !== WebSocket.CLOSED) {
        console.log("rooms WebSocket is already opened.");
        return;
    }

    // 웹소켓 객체 생성
    roomsSocket = new WebSocket("ws://localhost:8081/rooms?accountId=" + accountId);

    // 웹소켓 이벤트 핸들러 설정
    roomsSocket.onopen = function(event) {
        console.log("Connected to /rooms?userId=" + accountId);
    };

    roomsSocket.onmessage = function(event) {
        let data = JSON.parse(event.data);
        if (data.action === "updateRoomList") { // 예를 들어 'updateRoomList' 액션이라고 가정
            let roomId = data.roomId;
            let lastMessage = data.lastMessage;
            let lastMessageDate = data.lastMessageDate;

            // 채팅방 목록에서 해당 roomId를 가진 채팅방 아이템 찾기
            let roomItem = $('.rooms[data-roomId="' + roomId + '"]');
            if (roomItem.length) {
                // 최근 메시지와 날짜 정보 업데이트
                roomItem.find('.roomContents').text(lastMessage);
                roomItem.find('.rateDate').text(lastMessageDate);
            }
        }
    };


    // 웝소켓 연결 끊겼을때
    roomsSocket.onclose = function(event) {
        console.log("rooms Connection closed");
    };
}

// 페이지를 옮기거나 브라우저가 닫힐 때 웹소켓 연결 끊기
$(window).on("unload", function() {
    if (roomsSocket) {
        roomsSocket.close();
        alert("room 웹소켓 연결 종료")
    }
});

function deleteRoom(roomId) {
    $.ajax({
        url: '/main/chatRooms/deleteRoom.do?roomId=' + roomId, // URL에 쿼리 스트링 추가
        type: 'DELETE', // HTTP 메소드
        success: function(result) {
            if(result === true) {
                alert("채팅방 삭제 성공!");
                window.location.href = '/main/chatRooms'; // 성공 후 페이지 리다이렉션
            } else {
                alert('채팅방 삭제에 실패했습니다. ERROR:1');
            }
        },
        error: function(error) {
            alert('채팅방 삭제에 실패했습니다. ERROR:2');
        }
    });
}
