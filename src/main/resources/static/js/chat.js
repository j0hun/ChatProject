'use strict';

//보낼 메시지
const messageInput = document.querySelector('#msg');
//보내기 버튼
const sendBtn = document.querySelector("#sendBtn");

let stompClient = null;

let userId = document.querySelector("#userId").value;
let roomId = document.querySelector("#roomId").value;

function connect() {
    userId = document.querySelector("#userId").value;
    roomId = document.querySelector("#roomId").value;

    // WebSocketConfig.java에 설정된 endpoint로 SockJS 객체, StompClient 객체 생성
    const socket = new SockJS("/ws");
    // Handshake
    stompClient = Stomp.over(socket);

    // connect(header, 연결 성공시 콜백, 에러 발생시 콜백)
    stompClient.connect({},onConnected,onError);
}

function onConnected(){
    // subscribe(subscribe url, 해당 url로 메시지를 받을 때마다 실행할 함수)
    stompClient.subscribe('/sub/' + roomId,showMessage,onError);
}

function onError(){
    alert('에러 발생');
}

// 화면에 메시지를 표시하는 함수
function showMessage(e) {
    const data = JSON.parse(e .body);
    const chatting = document.getElementById('chatting');
    const p = document.createElement('p');

    if (data.name === userId) {
        p.className = 'me';
    } else {
        p.className = 'other';
    }
    p.textContent = data.name + " : " + data.content;
    chatting.appendChild(p);

}

// 메시지 브로커로 메시지 전송
function send() {
    const message = messageInput.value.trim();
    if (message === '') {
        return; // 메시지가 비어있을 경우 전송하지 않음
    }
    const data = {
        "name": userId,
        "content": message
    };
    // send(destination, 헤더, 페이로드)
    stompClient.send("/pub/" + roomId, {}, JSON.stringify(data));
    messageInput.value = ''; // 메시지를 보낸 후 입력 필드를 비움
}

connect();
// 이벤트 리스너 추가
sendBtn.addEventListener('click', send);
