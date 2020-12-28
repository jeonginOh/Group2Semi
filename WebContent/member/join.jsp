<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>member/join.html</title>
    <script src="<%=request.getContextPath()%>/member/join.js"></script>
    <style type="text/css">
    #content{
        position: relative;
        margin:auto;
        padding: auto;
        /* left:auto; */
        width:600px;
    }
    .inputrow{
        border: 1px solid grey;
        margin: 10px 0 10px 0;
        background-color: white;
    }
    .inputtext{
        border:none;
        background:transparent;
        width:100%;
        font-size: 1.5em;
    }
    .btn{
        width:100%;
    }
    .btns {
        white-space: nowrap;
        width:50%;
    }
    .next{
        width:100%;
        background-color: #0062cc;
    }
    h1{
		margin-top:100px;
		text-align: center;
	}
    </style>
</head>
<body>
    <div id="content">
        <h1>회원가입</h1>
        <form action="<%=request.getContextPath()%>/member/join.do" method="post" onsubmit="return canpass()">
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
                            <input type="button" value="이메일 다시 입력" id="mailcancel" class='btn'>
                            <input type="button" value="인증" id="usemail" class='btn next'>
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
            <input type="submit" value="가입하기" class='btn next'>
        </form>
    </div>
</body>
</html>