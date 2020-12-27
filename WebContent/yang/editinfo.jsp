<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>user/editinfo.jsp</title>
</head>
<body>
    <div id="content">
        <h1>비회원 결제정보입력</h1>
        <legend>정보입력</legend>
        <!-- <form action="myinfo.do" method="post" onsubmit="return canpass()"> -->
            <div>
                <h4><label for="pwd">비밀번호</label></h4>
                <span id="pwdMsg"></span>
                <div id="pwdrow" class="inputrow"><input type="password" name="pwd" id="pwd" class='inputtext'></div>
            </div>
            <div>
                <h4><label for="pwdchk">비밀번호 확인</label></h4>
                <span id="pwdchkMsg"></span>
                <div id="pwdchkrow" class="inputrow"><input type="password" name="pwdchk" id="pwdchk" class='inputtext'></div>
            </div>
            <div>
                <h4><label for="email">이메일</label></h4>
                <span id="emailMsg"></span>
                <div id="emailrow" class="inputrow"><input type="email" name="email" id="email" class='inputtext'></div>
                <div id="btnarea">
                    <input type="button" value="인증코드발송" id="emailauth" class='btn'>
                    <input type="text" name="" id="emailcode" class='inputtext' placeholder="인증코드 입력">
                    <div id='emailcodebtns' class="btns">
                        <input type="button" value="이메일 다시 입력" id="mailcancel">
                        <input type="button" value="인증" id="usemail">
                    </div>
                </div>
            </div>
            <div>
                <h4><label for="addr">주소</label></h4>
                <span id="addrMsg"></span>
                <div id="addrrow" class="inputrow"><input type="text" name="addr" id="addr" class='inputtext'></div>
            </div>
            <div>
                <h4><label for="phone">전화번호(아이디로 사용)</label></h4>
                <span id="phoneMsg"></span>
                <div id="phonerow" class="inputrow"><input type="text" name="phone" id="phone" class='inputtext'></div>
            </div>
            <div class="btns">
                <input type="button" value="취소" id='cancel'>
                <!-- <input type="submit" value="변경하기" id='editthis'> -->
                <input type="button" value="변경하기" id='editthis'>
            </div>
        <!-- </form> -->
    </div>
    <script type="text/javascript">
        window.onload=function() {
            let id = document.getElementById('id');
            let pwd = document.getElementById('pwd');
            let pwdchk = document.getElementById('pwdchk');
            let email = document.getElementById('email');
            let addr = document.getElementById('addr');
            let phone = document.getElementById('phone');
            let cancel = document.getElementById('cancel');
            cancel.addEventListener("click", function(e){
            	history.back();
            })
            let editthis = document.getElementById('editthis');

            let ori_email;
            let ori_addr;
            let ori_phone;

            let emailpass=false;
            let phonepass=false;

            function info_init() {
                let xhr = new XMLHttpRequest();
                xhr.onreadystatechange=function() {
                    if (xhr.readyState==4 && xhr.status==200) {
                        let json = JSON.parse(xhr.responseText);
                        id.value=json.id;
                        email.value=json.email;
                        ori_email=json.email;
                        addr.value=json.addr;
                        ori_addr=json.addr;
                        phone.value=json.phone;
                        ori_phone=json.phone;
                    }
                };
                xhr.open('get', 'myinfo.do', true);
                xhr.send();
            }
            info_init();

            let mailcancel = document.getElementById('mailcancel');
            let usemail = document.getElementById('usemail');
            let emailcode = document.getElementById('emailcode');
            let emailcodebtns = document.getElementById('emailcodebtns');
            let emailauth = document.getElementById('emailauth');
            let emailMsg = document.getElementById('emailMsg'); 
            
            emailauth.style.display="none";
            emailcodebtns.style.display="none";
            emailcode.style.display="none";
            let emailauthready=false;
            // let mailsent=false;
            
            let mailauthcode = "";
            let mailverified=false;
            
            emailauth.addEventListener("click", function(e) {
                emailauth.style.display="none"
                if (emailauthready && !mailverified) {
                    email.setAttribute('readonly', true);
                    // mailsent=true;
                    emailcodebtns.style.display="";
                    emailcode.style.display="";
                    let xhr = new XMLHttpRequest();
                    xhr.onreadystatechange=function() {
                        if (xhr.readyState==4 && xhr.status==200) {
                            let json = JSON.parse(xhr.responseText);
                            mailauthcode=json.mailauthcode;
                        }
                    };
                    xhr.open('get', '../member/joincheck.do?email='+email.value, true);
                    // let param = "email="+email.value;
                    xhr.send();
                }
            }, false);

            mailcancel.addEventListener('click', function(e) {
                email.value="";
                email.removeAttribute('readonly');
                emailMsg.textContent="";
                // emailauth.style.display="none"
                emailcode.style.display="none";
                emailcode.removeAttribute('readonly');
                emailcodebtns.style.display="none";
                mailauthcode="";
                mailverified=false;
                emailauthready=false;
            }, false);

            usemail.addEventListener('click', function(e) {
                if (emailcode.value==mailauthcode) {
                    mailverified=true;
                    emailcode.setAttribute('readonly', true);
                    alert("인증되었습니다.");
                }
            }, false);


            pwdchk.addEventListener("keyup", pwdchkchecker, false);
            pwd.addEventListener("keyup", pwdchecker, false);
            email.addEventListener("keyup", emailchecker, false);
            addr.addEventListener("keyup", addrchecker, false);
            phone.addEventListener("keyup", phonechecker, false);
            editthis.addEventListener("click", editthisclickevent, false);
            
            email.addEventListener("focusout", email_final_checker, false);

            function email_final_checker() {
                if (emailchecker()) {
                    checkavail(event.target);
                }
            }

            phone.addEventListener("focusout", phone_final_checker, false);

            function phone_final_checker() {
                if (phonechecker()) {
                    checkavail(event.target);
                }
            }

            function pwdchkchecker() {
                let tgt = pwdchk.parentNode.previousSibling.previousSibling;
                if(pwd!="" && pwdchk.value!=pwd.value) {
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
                    pwdchkchecker();
                    return true;
                }else {
                    tgt.textContent="6글자 이상 15글자 이하, 영어 대소문자, 숫자, 특수문자 포함";
                    return false;
                }
            }
            
            function emailchecker() {
                let tgt = email.parentNode.previousSibling.previousSibling;
                let regex = /^[a-z0-9_+.]+@[a-z0-9.]+\.\w{2,4}$/;
                if(regex.test(email.value)) {
                    tgt.textContent="";
                    return true;
                }else {
                    tgt.textContent="올바르지 않은 이메일 주소입니다.";
                    return false;
                }
            }

            function addrchecker(){
                //TODO: 주소 api
                return true;
            }

            function phonechecker() {
                let tgt = phone.parentNode.previousSibling.previousSibling;
                let regex = /^(\d{3})-?(\d{4})-?(\d{4})$/;
                if(regex.test(phone.value)) {
                    phone.value=phone.value.replace(regex, "$1$2$3");
                    tgt.textContent="";
                    return true;
                }else {
                    tgt.textContent="올바르지 않은 전화번호입니다.";
                    return false;
                }
            }

            function checkavail(einput) {
                // let tmp=false;
                let tgt = einput.parentNode.previousSibling.previousSibling;
                let xhr = new XMLHttpRequest();
                xhr.onreadystatechange=function() {
                    if (xhr.readyState==4 && xhr.status==200) {
                        let json = JSON.parse(xhr.responseText);
                        // console.log(json.result);
                        if (JSON.parse(json.result)) {
                            if (einput.id=="email" && !emailauthready) {
                                emailauth.style.display=""; //이전값이 남아있어서 생기는 문제?
                                emailauthready=true;
                            }
                            if (einput.id=="phone") phonepass=true;
                            else if (einput.id=="email") emailpass=true;
                            tgt.textContent="사용가능"; 
                            tmp=true;
                        }else if (!JSON.parse(json.result)){
                            if (einput.id=="phone" && phone.value==ori_phone) phonepass=true;
                            else if (einput.id=="email" && email.value==ori_email) emailpass=true;
                            else {
                                let text="다른 ";
                                if (einput.id=="phone") {
                                    text+="전화번호";
                                    phonepass=false;
                                }else if (einput.id=="email") {
                                    emailpass=false;
                                    text+="이메일";
                                    emailauth.style.display="none";
                                    emailauthready=false;
                                    email.removeAttribute("readonly");
                                }
                                text = josa(text, "을")+" 사용해주세요.";
                                tgt.textContent=text;
                            }
                        }else {
                            if (einput.id=="email") {
                                emailauth.style.display="none";
                                emailauthready=false;
                                email.removeAttribute("readonly");
                            }
                        }
                    }
                };
                xhr.open('get', '../member/join.do?type='+einput.id+'&value='+einput.value, true);
                xhr.send();
                // return tmp;
            }



            function canpass() {
                return (/*mailverified && */emailpass && phonepass && pwdchecker() && pwdchkchecker() && addrchecker());
            }
            /**
             * 이거 왜 두번눌러야 작동해요
            */
            function editthisclickevent() {
                checkavail(email);
                checkavail(phone);
                let xhr = new XMLHttpRequest();
                xhr.onreadystatechange=function() {
                    if (xhr.readyState==4 && xhr.status==200) {
                        let json = JSON.parse(xhr.responseText);
                        if(JSON.parse(json.result)) {
                            location.href="<%=request.getContextPath()%>/deleteTemp";
                        }else {
                            confirm("에러가 발생했습니다.");
                        }
                    }
                };
                xhr.open('post', '<%=request.getContextPath()%>/user/myinfo.do', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                let param = "pwd="+pwd.value+"&email="+email.value+"&addr="+addr.value+"&phone="+phone.value;
                xhr.send(param);
            }
        }
        function josa(word, josa) {
            let lastword = word.charCodeAt(word.length-1);
            // console.log(word + josa + lastword)
            if ((lastword-44032)%28==0) {
                switch (josa) {
                    case "을" :
                        return word+"를";
                    case "이" :
                        return word+"가"
                }
            }else return word+josa;
        }
    </script>
</body>
</html>