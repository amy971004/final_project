let chatSocket; // 채팅 웹소켓 연결을 관리할 변수
let selectedRoomId = ""; // 선택된 채팅방의 ID를 저장할 변수
let messageInput = $('#messageInput');
let messagesDiv = $('#message');

$(document).ready(function() {

});

// 채팅방을 선택하는 함수
function selectRoom(roomId) {

    // 이미 연결이 열려 있는 경우, 기존 연결을 종료
    if (chatSocket && chatSocket.readyState === WebSocket.OPEN) {
        chatSocket.close();
    }

    // 해당 roomId로 웹소켓 연결 생성
    openChatSocket(roomId);

    // 이전 메시지 내용을 지웁니다.
    messagesDiv.html('');

    selectedRoomId = roomId; // 선택된 채팅방의 ID 저장
    console.log("Selected Room ID: " + selectedRoomId);


    // 추가적인 웹소켓 이벤트 핸들러 구현...
    // 서버로부터 해당 채팅방 정보 요청
    $.ajax({
        url: '/main/chatRooms/getRoomInfo.do',
        type: 'GET',
        data: {
            roomId: selectedRoomId
        },
        success: function(roomInfo) {
            let recName = $('#recName');
            let recRoomName = $('#recRoomName');
            recName.text(roomInfo.opponentName);
            recRoomName.text(roomInfo.roomName);
        },
        error: function(error) {
            console.error('Error fetching rooms:', error);
        }
    });

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

// 웹소켓 연결 열기
function openChatSocket(roomId) {

    // 웹소켓 객체 생성
    chatSocket = new WebSocket("ws://localhost:8081/chat?roomId="+roomId);


    // 웹소켓 이벤트 핸들러 설정
    chatSocket.onopen = function(event) {
        console.log("Connected to /chat?roomId=" + roomId);
        alert("chat 웹소켓 연결 성공")
    };

    // 웹소켓 메세지를 받았을때
    chatSocket.onmessage = function(event) {
        let message = JSON.parse(event.data);
        displayMessages([message]); // 새 메시지를 화면에 표시하는 함수 호출
        showMessage(); // 새 메시지가 도착했을 때 메시지를 자연스럽게 표시하는 함수 호출
    };

}

// 메시지 목록을 화면에 표시하는 함수
function displayMessages(messages) {
    // 메시지를 표시할 부모 요소를 선택합니다.
    let messageContainer = $('#message');

    // 각 메시지에 대해 반복합니다.
    messages.forEach(function(message) {
        // 메시지를 표시할 요소를 생성합니다.
        let messageElement = $('<div class="messageC messageAppear"></div>'); // 여기에 'messageAppear' 클래스를 추가

        // 메시지의 보낸 사람 이름을 표시하는 요소를 생성합니다.
        let senderNameElement = $('<div class="msA1"></div>').text(message.senderUserName);
        // 메시지 내용을 표시하는 요소를 생성합니다.
        let messageContentElement = $('<div class="msB1"></div>').text(message.messageText);
        // 메시지 전송 시간을 표시하는 요소를 생성합니다.
        let timeElement = $('<div class="msC1"></div>').text(formatDateTime(message.sentAt));

        // 발신자와 수신자에 따라 다른 클래스를 적용합니다.
        if (message.senderUserId === userId) {
            senderNameElement.addClass('sender');
            messageContentElement.addClass('sender');
        } else {
            senderNameElement.addClass('receiver');
            messageContentElement.addClass('receiver');
        }

        // 각 요소를 부모 요소에 추가합니다.
        messageElement.append(senderNameElement);
        messageElement.append(messageContentElement);
        messageElement.append(timeElement);

        // 생성된 메시지 요소를 메시지 컨테이너에 추가합니다.
        messageContainer.append(messageElement);

        // 새로 추가된 메시지에 애니메이션 적용
        setTimeout(function() {
            messageElement.removeClass('messageAppear');
        }, 500); // 애니메이션 지속 시간과 동일하게 설정
    });

    // 메시지가 추가된 후 스크롤을 아래로 이동시킵니다.
    messageContainer.scrollTop(messageContainer.prop("scrollHeight"));
}



function formatDateTime(isoString) {
    const date = new Date(isoString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // getMonth()는 0부터 시작하므로 +1을 해줍니다.
    const day = String(date.getDate()).padStart(2, '0');
    const hour = String(date.getHours()).padStart(2, '0');
    const minute = String(date.getMinutes()).padStart(2, '0');

    return `${year}-${month}-${day} ${hour}:${minute}`;
}

// 메시지 전송 버튼 이벤트 핸들러
function sendMessage() {

    let userId = $('#userID').text();
    let userName = $('#userName').text();

    if (chatSocket.readyState !== WebSocket.OPEN) {
        console.error('WebSocket is not open.');
        return;
    }

    let messageText = messageInput.val(); // 입력된 메시지 텍스트를 가져옵니다.

    // 전송할 메세지의 데이터들
    let messageData = {
        roomId: selectedRoomId, // 선택된 채팅방의 ID
        senderUserId: userId, // 보낸 사람의 사용자 ID
        senderUserName: userName, // 보낸 사람의 이름
        messageText: messageText, // 메시지 내용
        sentAt: new Date().toISOString() // 메세지 전송 시간
    };

    // WebSocket을 통해 메시지 데이터를 서버로 전송
    chatSocket.send(JSON.stringify(messageData));

    // 메시지 입력 필드 초기화
    messageInput.val('');
}

// 페이지를 옮기거나 브라우저가 닫힐 때 웹소켓 연결 끊기
$(window).on("unload", function() {
    if (chatSocket) {
        chatSocket.close();
        alert("chat 웹소켓 연결 종료")
    }
});

// 메시지 입력 필드에 keypress 이벤트 리스너를 추가합니다.
messageInput.keypress(function(event) {
    // event.which === 13은 엔터키의 키 코드입니다.
    if (event.which === 13) {
        event.preventDefault(); // 폼의 기본 제출 이벤트를 방지합니다.
        sendMessage(); // 메시지 전송 함수를 호출합니다.
    }
});