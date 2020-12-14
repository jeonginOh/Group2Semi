window.onload=function() {
    let xhr=null;

    let id = document.getElementById('id');
    let pwd = document.getElementById('pwd');
    let pwdchk = document.getElementById('pwdchk');
    let birthday = document.getElementById('birthday');
    let email = document.getElementById('email');
    let addr = document.getElementById('addr');
    let phone = document.getElementById('phone');

    let idMsg = document.getElementById('idMsg');
    let pwdMsg = document.getElementById('pwdMsg');
    let pwdchkMsg = document.getElementById('pwdchkMsg');
    let birthdayMsg = document.getElementById('birthdayMsg');
    let emailMsg = document.getElementById('emailMsg');
    let addrMsg = document.getElementById('addrMsg');
    let phoneMsg = document.getElementById('phoneMsg');
    let inputrows = document.getElementsByClassName("inputrow");
    

    // console.log(phone.parentNode.parentNode.firstElementChild.firstElementChild.textContent);
    //     // .previousSibling.previousSibling.firstChild);
    // console.log(inputrows.length);
    for(let i=0; i<inputrows.length; i++) {
        console.log(inputrows[i].firstChild);
        inputrows[i].firstChild.addEventListener("focusout", function(e) {
            console.log("aa");
            if (e.value==null || e.value.trim()=="") {
                let word = josa(e.target.parentNode.parentNode.firstElementChild.firstElementChild.textContent, "을");
                e.target.parentNode.previousSibling.previousSibling.textContent=word+" 입력해주세요.";
            }
        }, false);
    }
}
//word의 마지막 글자를 받아서 은/는 이/가 을/를 등의 처리가 가능하다.
function josa(word, josa) {
    let lastword = word.charCodeAt(word.length-1);
    console.log(word + josa + lastword)
    if ((lastword-44032)%28==0) {
        switch (josa) {
            case "을" :
                return word+"를";
            case "이" :
                return word+"가"
        }
    }else return word+josa;
}