'use strict';

//보낼 메시지
var messageInput = document.querySelector('#msg');
//보내기 버튼
var sendBtn = document.querySelector("#sendBtn");
//방나가기 버튼
var leaveBtn = document.querySelector("#leaveBtn");

var stompClient = null;

var memberId = document.querySelector("#memberId").value;
var roomId = document.querySelector("#roomId").value;

var member = null;
findMemberById(memberId);

function connect() {

    // WebSocketConfig.java에 설정된 endpoint로 SockJS 객체, StompClient 객체 생성
    var socket = new SockJS("/ws");
    // Handshake
    stompClient = Stomp.over(socket);

    // connect(header, 연결 성공시 콜백, 에러 발생시 콜백)
    stompClient.connect({},onConnected,onError);
}

function onConnected() {

    // subscribe(subscribe url, 해당 url로 메시지를 받을 때마다 실행할 함수)
    stompClient.subscribe('/sub/' + roomId, showMessage, onError);

    stompClient.send('/pub/enterMember',
        {},
        JSON.stringify({
            sender : memberId,
            receiver : roomId,
            type : 'ENTER'
        })
    );
}

function onError(){
    alert('에러 발생');
}

// 화면에 메시지를 표시하는 함수
function showMessage(e) {
    var data = JSON.parse(e .body);
    var chatting = document.getElementById('chatting');
    var p = document.createElement('p');
    var name = member.name;

    if (data.type === 'ENTER' || data.type === 'LEAVE') {
        p.className = 'event';
        p.textContent = name + " 님이 " + data.message;
    }
    else {
        if (data.sender == memberId) {
            p.className = 'me';
        } else {
            p.className = 'other';
        }
        p.textContent = name + " : " + data.message;
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
        sender : memberId,
        receiver : roomId,
        message : message,
        type : 'TALK'
    };
    // send(destination, 헤더, 페이로드)
    stompClient.send("/pub/send/room", {}, JSON.stringify(data));
    messageInput.value = ''; // 메시지를 보낸 후 입력 필드를 비움
}

function leave(){
    stompClient.send('/pub/leaveMember',
        {},
        JSON.stringify({
            sender : memberId,
            receiver : roomId,
            type : 'LEAVE'
        })
    );
    window.location.href='/chat/room';
}

function findMemberById(id){
    // jQuery를 사용한 Ajax 요청
    $.ajax({
        url: "/member/" + id, // 멤버 ID를 경로에 포함
        type: "GET",
        success: function(data) {
            // 요청이 성공적으로 처리된 경우 실행될 콜백 함수
            console.log("Member data:", data);
            // 데이터 처리 로직 추가
            member = JSON.parse(JSON.stringify(data));
        },
        error: function(xhr, status, error) {
            // 요청이 실패한 경우 실행될 콜백 함수
            console.error("Error:", error);
            // 에러 처리 로직 추가
            alert('에러 발생');
        }
    });
}

connect();
// 이벤트 리스너 추가
sendBtn.addEventListener('click', send);
leaveBtn.addEventListener('click',leave);
