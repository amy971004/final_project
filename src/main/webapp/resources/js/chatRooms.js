let socket; // 웹소켓 연결을 관리할 변수
let selectedRoomId = ""; // 선택된 채팅방의 ID를 저장할 변수
let messageInput = $('#messageInput');

$(document).ready(function() {
    // 페이지 로드 시 웹소켓 연결 시도
    openSocket();

    // 페이지를 옮기거나 브라우저가 닫힐 때 웹소켓 연결 끊기
    $(window).on("unload", function() {
        if (socket) {
            socket.close();
            alert("웹소켓 연결 종료")
        }
    });

    // 메시지 전송 버튼 이벤트 핸들러
    $('#sendMessage').on('click', function() {
        sendMessage();
    });
});



// 웹소켓 연결 열기
function openSocket() {
    if (socket !== undefined && socket.readyState !== WebSocket.CLOSED) {
        console.log("WebSocket is already opened.");
        return;
    }

    // 웹소켓 객체 생성
    socket = new WebSocket("ws://localhost:8081/ws");

    // 웹소켓 이벤트 핸들러 설정
    socket.onopen = function(event) {
        alert("웹소켓 연결 성공")
    };

    socket.onmessage = function(event) {
        let message = JSON.parse(event.data);
        displayMessages([message]); // 새 메시지를 화면에 표시하는 함수 호출
    };

    socket.onclose = function(event) {
        console.log("Connection closed");
    };
}

// 웹소켓 연결 닫기
function closeSocket() {
    socket.close();
}

// 채팅방을 선택하는 함수
function selectRoom(roomId) {
    selectedRoomId = roomId; // 선택된 채팅방의 ID 저장
    console.log("Selected Room ID: " + selectedRoomId);

    // 서버로부터 채팅방의 메시지 목록 요청
    $.ajax({
        url: '/main/chatRooms/getMessage.do', // 서버의 메시지 목록 API 경로
        type: 'GET',
        data: {
            roomId: selectedRoomId
        },
        success: function(messages) {
            displayMessages(messages);
        },
        error: function(error) {
            console.error('Error fetching messages:', error);
        }
    });

}

// 메시지 목록을 화면에 표시하는 함수
function displayMessages(messages) {
    let messagesDiv = document.getElementById("message");
    messagesDiv.innerHTML = ''; // 이전 메시지 내용을 지웁니다.

    messages.forEach(function(message) {
        let messageElement = document.createElement('div');
        messageElement.textContent = message.senderUserName + " : " + message.messageText;
        messagesDiv.appendChild(messageElement);
    });
}

// 메시지 보내기
function sendMessage() {
    if (socket.readyState !== WebSocket.OPEN) {
        console.error('WebSocket is not open.');
        return;
    }
    let messageText = messageInput.val(); // 입력된 메시지 텍스트를 가져옵니다.
    let messageData = {
        roomId: selectedRoomId, // 선택된 채팅방의 ID
        message: messageText // 메시지 내용
    };

    // WebSocket을 통해 메시지 데이터를 서버로 전송
    socket.send(JSON.stringify(messageData));

    // 메시지 입력 필드 초기화
    messageInput.val('');
}


