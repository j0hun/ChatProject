'use strict';

// 보낼 메시지
var messageInput = document.querySelector('#msg');
// 보내기 버튼
var sendBtn = document.querySelector("#sendBtn");
// 방 나가기 버튼
var leaveBtn = document.querySelector("#leaveBtn");

var stompClient = null;
let roomId = document.querySelector("#roomId").value;

let member = null;
let memberId = null;
let subscribe = null;

function user() {
    // 사용자 정보를 가져오는 API 호출
    $.ajax({
        url: '/user',
        type: 'GET',
        headers: {
            "Authorization": "Bearer " + sessionStorage.getItem('token') // 로컬 스토리지에서 토큰을 가져와 헤더에 추가
        },
        success: function (response) {
            member = response;
            memberId = response.id;
            console.log(memberId);
        }
    });
}

function connect() {
    // WebSocketConfig.java에 설정된 endpoint로 SockJS 객체, StompClient 객체 생성
    var socket = new SockJS("/ws");
    // Handshake
    stompClient = Stomp.over(socket);

    // connect(header, 연결 성공시 콜백, 에러 발생시 콜백)
    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    console.log(subscribe);

    stompClient.subscribe('/sub/chat/' + roomId, (body) => {
        const chat = JSON.parse(body.body); // JSON 문자열을 객체로 파싱
        if(chat.length > 1) {
            chat.forEach(item => {
                showMessage({
                    body: JSON.stringify(item) // 각 메시지를 showMessage 함수에 전달
                });
            });
        }
        else if(!Array.isArray(chat)){
            showMessage({
                body: JSON.stringify(chat)
            })
        }
    });
    stompClient.send('/pub/enterMember',
        {},
        JSON.stringify({
            sender: memberId,
            receiver: roomId,
            type: 'ENTER'
        })
    );

}

function onError() {
    alert('에러 발생');
}

// 화면에 메시지를 표시하는 함수
function showMessage(e) {
    var data = JSON.parse(e.body);
    var chatting = document.getElementById('chatting');
    var p = document.createElement('p');

    if (data.type === 'ENTER' || data.type === 'LEAVE') {
        p.className = 'event';
        p.textContent = member.name + " 님이 " + data.message;
    } else {
        if (data.sender == memberId) {
            p.className = 'me';
        } else {
            p.className = 'other';
        }
        p.textContent = member.name + " : " + data.message;
    }
    chatting.appendChild(p);
}

// 메시지 브로커로 메시지 전송
function send() {
    var message = messageInput.value.trim();
    if (message === '') {
        return; // 메시지가 비어있을 경우 전송하지 않음
    }
    var data = {
        sender: memberId,
        receiver: roomId,
        message: message,
        type: 'TALK'
    };
    // send(destination, 헤더, 페이로드)
    stompClient.send("/pub/send/room", {}, JSON.stringify(data));
    messageInput.value = ''; // 메시지를 보낸 후 입력 필드를 비움
}

function leave() {
    stompClient.send('/pub/leaveMember',
        {},
        JSON.stringify({
            sender: memberId,
            receiver: roomId,
            type: 'LEAVE'
        })
    );
    window.location.href = '/chat/room';
}

function check(callback) {
    $.ajax({
        url: '/api/member/check/' + memberId + '/' + roomId,
        type: 'GET',
        contentType: 'application/json',
        success: function (data) {
            subscribe = data;
            callback();
        }
    });
}

user();
connect();
// 이벤트 리스너 추가
messageInput.onkeypress = e => e.key === 'Enter' && send();
sendBtn.addEventListener('click', send);
leaveBtn.addEventListener('click', leave);
