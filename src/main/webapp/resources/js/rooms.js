let roomsSocket; // 채팅방 목록 웹소켓 연결을 관리할 변수
let userId = $('#userID').text();
let userName = $('#userName').text();

$(document).ready(function() {

    // 페이지 로드 시 웹소켓 연결 시도
    openRoomsSocket();

    console.log("data-userAccountId 값 가져오기 테스트 userId : " + userId);

});

// 웹소켓 연결 열기
function openRoomsSocket() {


    if (roomsSocket !== undefined && roomsSocket.readyState !== WebSocket.CLOSED) {
        console.log("rooms WebSocket is already opened.");
        return;
    }

    // 웹소켓 객체 생성
    roomsSocket = new WebSocket("ws://localhost:8081/rooms?userId=" + userId);

    // 웹소켓 이벤트 핸들러 설정
    roomsSocket.onopen = function(event) {
        console.log("Connected to /rooms?userId=" + userId);
        alert("room 웹소켓 연결 성공")
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