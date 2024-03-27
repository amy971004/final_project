
let socket;

// 페이지를 옮기거나 브라우저가 닫히면 웹소켓 연결 끊기
$(window).on("unload", function() {
    if (socket) {
        socket.close();
    }
});

$(document).ready(function() {
    openSocket()
});
    // 웹소켓 연결 열기
    function openSocket() {
        if (socket !== undefined && socket.readyState !== WebSocket.CLOSED) {
            writeResponse("WebSocket is already opened.");
            return;
        }
        // 웹소켓 객체 생성
        socket = new WebSocket("ws://localhost:8081/ws");

        // 웹소켓 이벤트 핸들러 설정
        socket.onopen = function(event) {
            if (event.data === undefined)
                return;

            writeResponse(event.data);
        };

        socket.onmessage = function(event) {
            writeResponse(event.data);
        };

        socket.onclose = function(event) {
            writeResponse("Connection closed");
        };
    }


// 웹소켓 연결 닫기
function closeSocket() {
    socket.close();
}

// 메시지 보내기
function sendMessage() {
    if (socket.readyState !== WebSocket.OPEN) {
        console.error('WebSocket is not open.');
        console.log("버튼 누름 연결안되어있음")
        return;
    }
    let text = document.getElementById("messageInput").value;
    console.log("버튼 누름 정상작동")
    socket.send(text);
}

// 메시지 출력
function writeResponse(text) {
    let messages = document.getElementById("messages");
    messages.innerHTML += "<br/>" + text;
}