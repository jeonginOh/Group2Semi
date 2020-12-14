window.onload=function() {
    let xhr=null;

    let YouShellNotPass=true;

    let id = document.getElementById('id');
    let pwd = document.getElementById('pwd');
    let pwdchk = document.getElementById('pwdchk');
    let birthday = document.getElementById('birthday');
    let email = document.getElementById('email');
    let addr = document.getElementById('addr');
    let phone = document.getElementById('phone');

    // let idMsg = document.getElementById('idMsg');
    // let pwdMsg = document.getElementById('pwdMsg');
    // let pwdchkMsg = document.getElementById('pwdchkMsg');
    // let birthdayMsg = document.getElementById('birthdayMsg');
    // let emailMsg = document.getElementById('emailMsg');
    // let addrMsg = document.getElementById('addrMsg');
    // let phoneMsg = document.getElementById('phoneMsg');
    let inputrows = document.getElementsByClassName("inputrow");

    pwdchk.addEventListener("focusout", function(e) {
        // console.log(pwdchk.value + ":" + pwd.value);
        if(pwdchk.value==pwd.value) e.target.parentNode.previousSibling.previousSibling.textContent="비밀번호가 맞지 않습니다.";
        else pwdchk.parentNode.previousSibling.previousSibling.textContent="";
    }, false);

    

    // console.log(phone.parentNode.parentNode.firstElementChild.firstElementChild.textContent);
    //     // .previousSibling.previousSibling.firstChild);
    // console.log(inputrows.length);
    //비어있는지 확인
    for(let i=0; i<inputrows.length; i++) {
        // console.log(inputrows[i].firstChild);
        inputrows[i].firstChild.addEventListener("focusout", function(e) {
            checkinputevent(e.target);
        }, false);
    }
    // function checkinput(e) {
    //     // console.log(e);
    //     // console.log(e.target);
    //     checkinputevent(e.target);
    // }
    function checkinputevent(einput) {
        YouShellNotPass=false;
        einput.parentNode.previousSibling.previousSibling.textContent="";
        if (einput.value==null || einput.value.trim()=="") {
            // console.log(einput.value);
            let word = josa(einput.parentNode.parentNode.firstElementChild.firstElementChild.textContent, "을");
            einput.parentNode.previousSibling.previousSibling.textContent=word+" 입력해주세요.";
            YouShellNotPass=true;
        }else {
            let tgt = einput.parentNode.previousSibling.previousSibling;
            //pwd와 pwdchk 동일한지 확인
            if (einput==pwdchk) {
                // console.log(einput.value==pwd.value);
                if(einput.value!=pwd.value) tgt.textContent="비밀번호가 맞지 않습니다.";
                YouShellNotPass=true;
            }
            if (einput==pwd) {
                // let regex = /'(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,}$'/;
                let regex = /(?=.*\d{1,})(?=.*[~`!@#$%\^&*()-+=]{1,})(?=.*[a-zA-Z]{1,}).{6,15}$/;
                console.log(regex.test(einput.value));
                if(!regex.test(einput.value)) {
                    tgt.textContent="6글자 이상 15글자 이하, 영어 대소문자, 숫자, 특수문자 포함";
                    YouShellNotPass=true;
                }
                checkinputevent(pwdchk);
            }
            //id제약조건
            if (einput==id) {
                let regex = /^([a-z]{1,}[0-9a-z]*)/;
                if(/\s/.test(einput.value)) {
                    tgt.textContent="공백문자는 사용 불가능 합니다.";
                    YouShellNotPass=true;
                }
                else if (!regex.test(einput.value)) {
                    tgt.textContent="영소문자로 시작하는 영어 소문자, 숫자로 이루어진 ID를 입력하세요.";
                    YouShellNotPass=true;
                }
                //TODO : 아이디중복체크
            }
            if (einput==email) {
                let regex = /^[a-z0-9_+.]+@[a-z0-9.]+\.\w{2,4}/;
                if(!regex.test(einput.value)) {
                    tgt.textContent="올바르지 않은 이메일 주소입니다.";
                    YouShellNotPass=true;
                }
            }
            if (einput==birthday) {
                //윤년도 계산 가능함.
                let regex = /^(?:(?:(?:0[48]|[2468][048]|[13579][26])|00)0229|\d{2}(?:(?:0[13578]|1[02])(?:0[1-9]|[1-2][0-9]|3[01])|(?:0[469]|11)(?:0[1-9]|[1-2][0-9]|30)|02(?:0[1-9]|1[0-9]|2[0-8])))(?:0|1|2|3)$/;
                if (!regex.test(einput.value)) {
                    tgt.textContent="올바르지 않은 생년월일입니다.";
                    YouShellNotPass=true;
                }
            }
            if (einput==phone) {
                let regex = /(\d{3})-?(\d{4})-?(\d{4})/;
                if(regex.test(einput.value)) {
                    einput.value=einput.value.replace(regex, "$1$2$3");
                }else {
                    tgt.textContent="올바르지 않은 전화번호입니다.";
                    YouShellNotPass=true;
                }
            }
        }
    }
    function canpass() {
        return !YouShellNotPass;
    }
}
//word의 마지막 글자를 받아서 은/는 이/가 을/를 등의 처리가 가능하다.
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