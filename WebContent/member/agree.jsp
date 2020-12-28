<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>member/agree.html</title>
    <script type="text/javascript">
        window.onload=function() {
            let agree1 = document.getElementById('agree1');
            let agree2 = document.getElementById('agree2');
            let checkall = document.getElementById('checkall');
            
            
            checkall.addEventListener('change', agreeall, false);
            function agreeall() {
                agree1.checked=checkall.checked;
                agree2.checked=checkall.checked;
            }
        }
        function checkagree() {
            let errMsg = document.getElementById('errMsg');
            let agreeMsg="";
            let agreeallMsg="";
            let pass=true;
            errMsg.innerText=agreeMsg;
            if (!agree1.checked) {
                agreeMsg+="이용약관"
                pass=false;
            }
            if (!agree2.checked) {
                if(!pass) {
                    agreeMsg+="과 ";
                    agreeallMsg="모두";
                }
                agreeMsg+="개인정보 수집 및 이용";
                pass=false;
            }
            if (!pass) {
                agreeMsg+="에 "
                agreeMsg+=agreeallMsg;
                agreeMsg+=" 동의해주세요."
                errMsg.innerText=agreeMsg;
                return false;
            }
            console.log(pass);
            // location.href="join.html";
            return true;

        }
    </script>
    <style type="text/css">
        #content{
            /* text-align: center; */
            align-items: center;
            margin:auto;
            padding: auto;
            width:600px;
        }
        .agreements{
            /* width:80%; */
            background-color: white;
            position: relative;
            height: 200px;
            overflow: auto;
            border: 1px solid black;
        }
        label{
            text-align: left;
        }
        #btnarea{
            margin-top: 10px;
            white-space: nowrap;
        }
        #btnarea input{
            height:40px;
            width:50%;
        }
        h1{
            margin-top:100px;
            text-align: center;
	    }
        #next{
            background-color: #0062cc;
        }
    </style>
</head>
<body>
    <div id="content">
        <h1>회원가입</h1>
        <form action="<%=request.getContextPath()%>/jeungIn/main.jsp?spage=/member/join.jsp" method="POST" onsubmit="return checkagree()">
            <label for="checkall"><input type="checkbox" name="" id="checkall">이용약관, 개인정보 수집 및 이용 및 만점 채점에 동의합니다.</label>
            <div id="service">
                <label for="agree1"><input type="checkbox" name="" id="agree1">이용약관 동의</label>
                <div id="sterms" class="agreements">
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Delectus natus molestias at, ipsa ex modi quae aspernatur animi officiis aliquid obcaecati, repellat odio veritatis earum architecto, error tenetur. Alias, minus!</p>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Delectus natus molestias at, ipsa ex modi quae aspernatur animi officiis aliquid obcaecati, repellat odio veritatis earum architecto, error tenetur. Alias, minus!</p>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Delectus natus molestias at, ipsa ex modi quae aspernatur animi officiis aliquid obcaecati, repellat odio veritatis earum architecto, error tenetur. Alias, minus!</p>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Delectus natus molestias at, ipsa ex modi quae aspernatur animi officiis aliquid obcaecati, repellat odio veritatis earum architecto, error tenetur. Alias, minus!</p>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Delectus natus molestias at, ipsa ex modi quae aspernatur animi officiis aliquid obcaecati, repellat odio veritatis earum architecto, error tenetur. Alias, minus!</p>
                </div>
            </div>
            <div id="personal">
                <label for="agree2"><input type="checkbox" name="" id="agree2">개인정보 수집 및 이용 동의</label>
                <div id="pterms" class="agreements">
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Delectus natus molestias at, ipsa ex modi quae aspernatur animi officiis aliquid obcaecati, repellat odio veritatis earum architecto, error tenetur. Alias, minus!</p>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Delectus natus molestias at, ipsa ex modi quae aspernatur animi officiis aliquid obcaecati, repellat odio veritatis earum architecto, error tenetur. Alias, minus!</p>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Delectus natus molestias at, ipsa ex modi quae aspernatur animi officiis aliquid obcaecati, repellat odio veritatis earum architecto, error tenetur. Alias, minus!</p>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Delectus natus molestias at, ipsa ex modi quae aspernatur animi officiis aliquid obcaecati, repellat odio veritatis earum architecto, error tenetur. Alias, minus!</p>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Delectus natus molestias at, ipsa ex modi quae aspernatur animi officiis aliquid obcaecati, repellat odio veritatis earum architecto, error tenetur. Alias, minus!</p>
                </div>
            </div>
            <div id="btnarea">
                <div id="errMsg"></div>
                <input type="button" value="취소" onclick="history.back()" class='btn'>
                <input type="submit" value="확인" class='btn' id='next'>
            </div>
        </form>
    </div>
</body>
</html>