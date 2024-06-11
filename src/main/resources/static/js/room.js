function fetchChatRooms() {
    $.ajax({
        url: '/api/chat/room',
        method: 'GET',
        success: function (data) {
            var chatRoomsContainer = $('#chatRoomsContainer');
            chatRoomsContainer.empty();
            if (data && data.length > 0) {
                data.forEach(function (room) {
                    var chatRoomElement = $('<tr></tr>').append(
                        $('<td></td>').append(
                            $('<a></a>').attr('href', '/chat/room/' + room.id).text(room.roomName)
                        ),
                        $('<td></td>').text(room.memberName)

                    );
                    chatRoomsContainer.append(chatRoomElement);
                });
            } else {
                chatRoomsContainer.append('<tr><td>No chat rooms available.</td></tr>');
            }
        },
        error: function (error) {
            console.error('Error fetching chat rooms:', error);
        }
    });
}

function createRoom(name) { // 채팅방 이름을 매개변수로 추가
    $.ajax({
        type: "POST",
        url: "/api/chat/room",
        contentType: "application/json",
        data: JSON.stringify({ roomName: name }), // 전달된 이름을 사용하여 요청을 보냄
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.status === 201) {
                alert('채팅방이 생성되었습니다.');
                $('#createRoomModal').modal('hide');
                fetchChatRooms();
            } else {
                alert('채팅방 생성에 실패했습니다.');
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('채팅방 생성 중에 오류가 발생했습니다: ' + jqXHR.status + ' ' + errorThrown);
            console.error('Error details:', jqXHR.responseText);
        }
    });
}

$(document).ready(function () {
    fetchChatRooms();

    $('#createRoomButton').click(function () {
        var roomName = $('#roomName').val(); // 입력 필드에서 채팅방 이름 가져오기
        createRoom(roomName); // 채팅방 이름을 매개변수로 전달하여 createRoom 함수 호출
    });
});
