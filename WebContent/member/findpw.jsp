<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>member/findpw.html</title>
    <script type="text/javascript">
        window.onload=function() {
            let findword = document.getElementById('findword');
            let idexp = /^(?:[a-z]{1,}[0-9a-z]*)$/;
            let findMsg = document.getElementById('findMsg');
            let result = document.getElementById('result');
            let find = document.getElementById('find');
            
            find.addEventListener('click', findpw, false);
            findword.addEventListener("keyup", check, false);
            findword.addEventListener("keydown", function (e) {
            	
                // console.log(e.keyCode);
                if (e.keyCode==13) {
                    findpw();
                }
            }, false);
            
            function check() {
                findMsg.textContent="";
                let param = null;
                let tmp = findword.value;
                if (idexp.test(tmp)) {
                    param = "id="+tmp;
                }else if (tmp.length!=0) {
                    if (param!=null) param=null;
                    findMsg.textContent="잘못된 입력입니다.";
                }else {
                    if (param!=null) param=null;
                    findMsg.textContent="";
                }
                return param;
            }

            function findpw() {
                result.innerHTML="";
                let param = check();
                // console.log(param);
                if (param==null) {}
                else {
                    // findword.setAttribute("readonly");
                    let xhr = new XMLHttpRequest();
                    xhr.onreadystatechange=function() {
                        let resultMsg = document.createElement("p");
                        if (xhr.readyState==1) {
                            resultMsg.textContent="확인중입니다";
                        }else if (xhr.readyState==4 && xhr.status==200) {
                            let json = JSON.parse(xhr.responseText);
                            //TODO:CSS
                            //@jeungInOh
                            /* resultMsg.setAttribute('id' )
                            resultMsg.setAttribute('class' ) */

                            if (JSON.parse(json.find)) {
                                //TODO:
                                resultMsg.textContent="id에 등록된 이메일로 비밀번호 재설정 링크를 보냈습니다. "+json.result+"까지 링크를 눌러주세요.";
                            }else resultMsg.textContent=json.result;
                        }
                        result.append(resultMsg);
                    };
                    xhr.open('post', 'findpw.do', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.send(param);
                }
            }
        }
    </script>
</head>
<body>
    <div id="content">
        <h1>비밀번호찾기</h1>
        <!-- <form action="" method="get"> -->
            <div id="result"></div>
            <div>
                <label for="findword">아이디에 등록된 이메일로 비밀번호 변경 링크를 보냅니다.</label>
                <span id="findMsg"></span>
                <div id="findrow" class="inputrow">
                    <input type="text" name="" id="findword" placeholder="아이디를 입력하세요.">
                </div>
            </div>
        <!-- </form> -->
        <div id="btns">
            <input type="button" value="돌아가기" onclick="history.back()">
            <!-- <input type="submit" value="조회하기"> -->
            <input type="button" value="조회하기" id='find'>
        </div>
    </div>
</body>
</html>