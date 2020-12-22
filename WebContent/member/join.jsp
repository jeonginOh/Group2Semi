<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>member/join.html</title>
    <script src="join.js"></script>
    <style type="text/css">
        #content{
            width:400px;
        }
        .inputrow{
            border: 1px solid black;
            /* padding:10px; */
            width:100%;
        }
        .inputtext{
            width:100%;
            height: 2.0em;
        }
        .btns {
            white-space: nowrap;
            width:50%;
        }
        .btn{
            width:100%;
        }
    </style>
</head>
<body>
    <div id="content">
        <form action="join.do" method="post" onsubmit="return canpass()">
            <div class="join">
                <div>
                    <h4><label for="id">아이디</label></h4>
                    <span id="idMsg"></span>
                    <div id="idrow" class="inputrow"><input type="text" name="id" id="id" class='inputtext' required></div>
                </div>
                <div>
                    <h4><label for="pwd">비밀번호</label></h4>
                    <span id="pwdMsg"></span>
                    <div id="pwdrow" class="inputrow"><input type="password" name="pwd" id="pwd" class='inputtext' required></div>
                </div>
                <div>
                    <h4><label for="pwdchk">비밀번호 확인</label></h4>
                    <span id="pwdchkMsg"></span>
                    <div id="pwdchkrow" class="inputrow"><input type="password" name="pwdchk" id="pwdchk" class='inputtext' required></div>
                </div>
                <div>
                    <h4><label for="birthday">생년월일과 주민등록번호 뒷부분 1번째까지</label></h4>
                    <span id="birthdayMsg"></span>
                    <div id="birthdayrow" class="inputrow"><input type="text" name="birthday" id="birthday" class='inputtext' placeholder="9912251" required></div>
                </div>
                <div>
                    <h4><label for="email">이메일</label></h4>
                    <span id="emailMsg"></span>
                    <div id="emailrow" class="inputrow"><input type="email" name="email" id="email" class='inputtext' required></div>
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
                    <div id="addrrow" class="inputrow"><input type="text" name="addr" id="addr" class='inputtext' required></div>
                </div>
                <div>
                    <h4><label for="phone">전화번호</label></h4>
                    <span id="phoneMsg"></span>
                    <div id="phonerow" class="inputrow"><input type="text" name="phone" id="phone" class='inputtext' placeholder="01011112222" required></div>
                </div>
            </div>
            <input type="submit" value="가입하기" class='btn'>
        </form>
    </div>
</body>
</html>