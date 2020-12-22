<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>user/editpw.jsp</title>
</head>
<body>
    <div id="content">
        <h1>비밀번호 변경</h1>
        <div id="inputcol">
            <div>
                <h4><label for="pwd">비밀번호</label></h4>
                <span id="pwdMsg"></span>
                <div id="pwdrow" class="inputrow"><input type="password" name="pwd" id="pwd" class='inputtext' placeholder="변경할 비밀번호를 입력하세요"></div>
            </div>
            <div>
                <h4><label for="pwdchk">비밀번호 확인</label></h4>
                <span id="pwdchkMsg"></span>
                <div id="pwdchkrow" class="inputrow"><input type="password" name="pwdchk" id="pwdchk" class='inputtext' placeholder="변경할 비밀번호를 입력하세요"></div>
            </div>
        </div>
        <div class="btns">
            <input type="button" id='cancel' value="취소">
            <input type="button" id='editthis' value="변경하기">
        </div>
    </div>
    <script type="text/javascript">
    let cancel = document.getElementById('cancel');
    let editthis = document.getElementById('editthis');
    
    let pwd = document.getElementById('pwd');
    let pwdchk = document.getElementById('pwdchk');

    pwdchk.addEventListener("keyup", pwdchkchecker, false);
    pwd.addEventListener("keyup", pwdchecker, false);
    editthis.addEventListener("click", editthisclickevent, false);

    function pwdchkchecker() {
        let tgt = pwdchk.parentNode.previousSibling.previousSibling;
        if(pwdchk.value!=pwd.value) {
            tgt.textContent="비밀번호가 맞지 않습니다.";
            return false;
        }else {
            tgt.textContent="";
            return true;
        }
    }

    function pwdchecker() {
        let tgt = pwd.parentNode.previousSibling.previousSibling;
        let regex = /^(?=.*\d{1,})(?=.*[~`!@#$%\^&*()-+=]{1,})(?=.*[a-zA-Z]{1,}).{6,15}$/;
        // console.log(regex.test(pwd.value));
        if(regex.test(pwd.value)) {
            tgt.textContent="";
            pwdchkchecker;
            return true;
        }else {
            tgt.textContent="6글자 이상 15글자 이하, 영어 대소문자, 숫자, 특수문자 포함";
            return false;
        }
    }

    function editthisclickevent() {
        if (pwdchecker() && pwdchkchecker()) {
            let xhr = new XMLHttpRequest();
            xhr.onreadystatechange=function() {
                if (xhr.readyState==4 && xhr.status==200) {
                    let json = JSON.parse(xhr.responseText);
                    if (JSON.parse(json.result)) {
                        confirm("변경되었습니다.");
                        window.close();
                    }else {
                        alert("에러가 발생했습니다.");
                    }
                }
            };
            xhr.open('post', '../user/myinfo.do', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            let param = "pwd="+pwd.value;
            xhr.send(param);
        }
    }
    </script>
</body>
</html>