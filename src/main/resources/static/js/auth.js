$(document).ready(function () {
    // 사용자 정보를 가져오는 API 호출
    $.ajax({
        url: '/user',
        type: 'GET',
        success: function (response) {
            console.log(response);
            if (response) {
                // 사용자가 인증된 경우
                $('#logoutLink').show();
            } else {
                // 사용자가 인증되지 않은 경우
                $('#loginLink').show();
            }
        },
        error: function (xhr, status, error) {
            console.log('Error fetching user details');
            $('#loginLink').show();
        }
    });
});