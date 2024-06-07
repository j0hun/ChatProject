var loginBtn = document.querySelector("#loginBtn");

function login(e) {
    e.preventDefault();

    var loginemail = document.querySelector("#email").value;
    var loginpassword = document.querySelector("#password").value;
    var token = null;

    $.ajax({
        url: '/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({email: loginemail, password: loginpassword}),
        success: function (data) {
            var token = data.token;
            alert(token);
            if (token) {
                sessionStorage.setItem('token', token);
            }
            window.location.href = 'chat/room';
        },
        error: function (xhr, status, error) {
            alert('Invalid email or password');
        }
    });
}

loginBtn.addEventListener('click', login);
