// 아이디 중복확인 여부
let usernameChecked = false;
let emailChecked = false;

// 아이디가 변경되면 다시 중복확인
document.getElementById("username").addEventListener("input", function () {

    usernameChecked = false;

    document.getElementById("usernameResult").innerHTML =
        "<span style='color:gray'>아이디가 변경되었습니다. 다시 중복확인을 해주세요.</span>";

});

// 아이디 중복확인
function checkUsername() {

    const username = document.getElementById("username").value;

    if (username.trim() === "") {

        alert("아이디를 입력해주세요.");
        return;

    }

    fetch("/member/check-username?username=" + username)

        .then(response => response.json())

        .then(result => {

            const resultDiv = document.getElementById("usernameResult");

            if (result) {

                usernameChecked = true;

                resultDiv.innerHTML =
                    "<span style='color:blue'>✅ 사용 가능한 아이디입니다.</span>";

            } else {

                usernameChecked = false;

                resultDiv.innerHTML =
                    "<span style='color:red'>❌ 이미 존재하는 아이디입니다.</span>";

            }

        });

}

// 회원가입 버튼 클릭
function validateForm() {

     if (!usernameChecked) {

         alert("아이디 중복확인을 먼저 해주세요.");
         return false;

     }

     if (!emailChecked) {

         alert("이메일 중복확인을 먼저 해주세요.");
         return false;

     }

     return true;

 }

document.getElementById("email").addEventListener("input", function(){

    emailChecked = false;

    document.getElementById("emailResult").innerHTML =
        "<span style='color:gray'>이메일을 변경했습니다. 다시 중복확인을 해주세요.</span>";

});


function checkEmail(){

    const email = document.getElementById("email").value;

    if(email.trim() === ""){

        alert("이메일을 입력해주세요.");

        return;

    }

    fetch("/member/check-email?email=" + email)

        .then(response => response.json())

        .then(result => {

            const resultDiv =
                document.getElementById("emailResult");

            if(result){

                emailChecked = true;

                resultDiv.innerHTML =
                    "<span style='color:blue'>✅ 사용 가능한 이메일입니다.</span>";

            }else{

                emailChecked = false;

                resultDiv.innerHTML =
                    "<span style='color:red'>❌ 이미 사용중인 이메일입니다.</span>";

            }

        });

}