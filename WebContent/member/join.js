window.onload=function() {
    /**
     * gandalf
     */
    let YouShallNotPass=true;

    let id = document.getElementById('id');
    let pwd = document.getElementById('pwd');
    let pwdchk = document.getElementById('pwdchk');
    let birthday = document.getElementById('birthday');
    let email = document.getElementById('email');
    let addr = document.getElementById('addr');
    let phone = document.getElementById('phone');
    let emailauth = document.getElementById('emailauth');
    

    // let idMsg = document.getElementById('idMsg');
    // let pwdMsg = document.getElementById('pwdMsg');
    // let pwdchkMsg = document.getElementById('pwdchkMsg');
    // let birthdayMsg = document.getElementById('birthdayMsg');
    let emailMsg = document.getElementById('emailMsg');
    // let addrMsg = document.getElementById('addrMsg');
    // let phoneMsg = document.getElementById('phoneMsg');
    let inputrows = document.getElementsByClassName("inputrow");


    //이메일주소인증용 초기설정
    let mailcancel = document.getElementById('mailcancel');
    let usemail = document.getElementById('usemail');
    let emailcode = document.getElementById('emailcode');
    let emailcodebtns = document.getElementById('emailcodebtns');
    
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
            xhr.open('get', 'joincheck.do?email='+email.value, true);
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

    pwdchk.addEventListener("keyup", function(e) {
        let tgt = pwdchk.parentNode.previousSibling.previousSibling;
        if(pwdchk.value==pwd.value) {
            tgt.textContent="비밀번호가 맞지 않습니다.";
            YouShallNotPass=true;
        }
        else tgt.textContent="";
    }, false);

    //비어있는지 확인하는 용도의 이벤트 생성
    for(let i=0; i<inputrows.length; i++) {
        // console.log(inputrows[i].firstChild);
        inputrows[i].firstChild.addEventListener("focusout", function(e) {
            checkinputevent(e.target);
        }, false);
    }

    /**
     * id, email, phone 중복확인
     * @param {EventTarget} einput EventTarget
     */
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
                    tgt.textContent="사용가능"; 
                    tmp=true;
                }else if (!JSON.parse(json.result)){
                    let text="다른 ";
                    if (einput.id=="id") text+="아이디";
                    else if (einput.id=="phone") text+="전화번호";
                    else if (einput.id=="email") {
                        text+="이메일";
                        emailauth.style.display="none";
                        emailauthready=false;
                        email.removeAttribute("readonly");
                    }
                    text = josa(text, "을")+" 사용해주세요.";
                    YouShallNotPass=true;
                    tgt.textContent=text;
                }else {
                    console.log("error");
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

    function checkinputevent(einput) {
        YouShallNotPass=false;
        let tgt = einput.parentNode.previousSibling.previousSibling;
        tgt.textContent="";
        if (einput.value==null || einput.value.trim()=="") {
            // console.log(einput.value);
            let word = josa(einput.parentNode.parentNode.firstElementChild.firstElementChild.textContent, "을");
            tgt.textContent=word+" 입력해주세요.";
            YouShallNotPass=true;
        }else {
            //pwd와 pwdchk 동일한지 확인
            // if (einput==pwdchk) {
            //     // console.log(einput.value==pwd.value);
            //     if(einput.value!=pwd.value) tgt.textContent="비밀번호가 맞지 않습니다.";
            //     YouShallNotPass=true;
            // }
            if (einput==pwd) {
                // let regex = /'(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,}$'/;
                let regex = /^(?=.*\d{1,})(?=.*[~`!@#$%\^&*()-+=]{1,})(?=.*[a-zA-Z]{1,}).{6,15}$/;
                console.log(regex.test(einput.value));
                if(!regex.test(einput.value)) {
                    tgt.textContent="6글자 이상 15글자 이하, 영어 대소문자, 숫자, 특수문자 포함";
                    YouShallNotPass=true;
                }
                checkinputevent(pwdchk);
            }
            //id제약조건
            if (einput==id) {
                let regex = /^(?:[a-z]{1,}[0-9a-z]*)$/;
                if(/\s/.test(einput.value)) {
                    tgt.textContent="공백문자는 사용 불가능 합니다.";
                    YouShallNotPass=true;
                }else if (einput.value.length<=6 || !regex.test(einput.value)) {
                    tgt.textContent="영문자로 시작하는 영어 소문자, 숫자만으로 이루어진 6자 이상의 ID를 입력하세요.";
                    YouShallNotPass=true;
                }else {
                    checkavail(einput);
                }
            }
            //email 조건
            if (einput==email) {
                let regex = /^[a-z0-9_+.]+@[a-z0-9.]+\.\w{2,4}$/;
                if(!regex.test(einput.value)) {
                    tgt.textContent="올바르지 않은 이메일 주소입니다.";
                    YouShallNotPass=true;
                }else {
                    checkavail(einput);
                }
            }
            //생년월일 조건
            if (einput==birthday) {
                //윤년도 계산 가능함.
                let regex = /^(?:(?:(?:0[48]|[2468][048]|[13579][26])|00)0229|\d{2}(?:(?:0[13578]|1[02])(?:0[1-9]|[1-2][0-9]|3[01])|(?:0[469]|11)(?:0[1-9]|[1-2][0-9]|30)|02(?:0[1-9]|1[0-9]|2[0-8])))(?:0|1|2|3)$/;
                if (!regex.test(einput.value)) {
                    tgt.textContent="올바르지 않은 생년월일입니다.";
                    YouShallNotPass=true;
                }
            }
            if (einput==phone) {
                let regex = /^(\d{3})-?(\d{4})-?(\d{4})$/;
                if(regex.test(einput.value)) {
                    einput.value=einput.value.replace(regex, "$1$2$3");
                    checkavail(einput);
                }else {
                    tgt.textContent="올바르지 않은 전화번호입니다.";
                    YouShallNotPass=true;
                }
            }
        }
    }
    function canpass() {
        if (mailverified && !YouShallNotPass) return true;
        else {
            if (!YouShallNotPass) alert("이메일 인증을 진행하세요.");
            else if (!YouShallNotPass) alert("아이디와 전화번호를 다시 확인해주세요.");
            else alert("아이디, 전화번호, 이메일을 다시 확인해주세요.");
            return false;
        }
    }
}
/**
 * word의 마지막 글자를 받아서 은/는 이/가 을/를 등의 처리가 가능하다.
 * @param word 조사를 붙일 문장/단어
 * @param josa 은, 을, 이 등의 조사.
 * @returns word + 는, 를, 가
 */
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

