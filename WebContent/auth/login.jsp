<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>auth/login.html</title>
<!--     <script src="login.js"></script> -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<style type="text/css">
	body{
		background-color: #E9E9E9;
	}
	#content{
		padding-top: auto;
		position: relative;
		left:auto;
		/* text-align: center; */
		width:400px;
		margin: auto;
		white-space: nowrap;
	}
	fieldset{
		padding:5px;
	}
	fieldset div input{
		/* border:0px; */
		width:100%;
		margin:5px;
	}
	#login{
		/* height:50px; */
		display: inline-block;
		width: 25%;
		float:right;
		background-color: #0062cc;
	}
	.checkboxlabel{
		width:25%;
		margin:0px;
	}
	#tempuser{
		display: inline-block;
		width:50%;
	}
	#tempuserlogin{
		display: inline-block;
		width:50%;
	}
	#mainimg{
		margin-top:100px;
		text-align: center;
		/* text-align: center; */
	}
	legend{
		text-align: center;
	}
	.btn{
		/* border-color: #005cbf; */
	}
	#member{
		text-align: center;
	}
	.inputrow{
		background-color: white;
		border: 1px solid grey;
		margin: 10px 0 10px 0;
	}
	#id{
		border:none;
		background:transparent;
	}
	#pwd{
		border:none;
		background:transparent;
	}
</style>
    
</head>
<body>
<%
	Map<String, String[]> map= request.getParameterMap();
    String keys[] = new String[map.keySet().size()];
	for(String key : map.keySet()) {
		System.out.println("key: "+ key);
		
		for(String value : map.get(key)) {
			System.out.println("value: "+ value);
			// sb.append(value+"&");
		}
	}
	
	String method=null;
	String ref=request.getHeader("referer");
	System.out.println("ref:"+request.getHeader("referer"));
	String url="../jeungIn/main.jsp";

	if (request.getAttribute("method")!=null) method = (String) request.getAttribute("method"); 
	if (request.getAttribute("url")!=null) url= (String) request.getAttribute("url"); 
	if (request.getAttribute("ref")!=null) ref= (String) request.getAttribute("ref"); 
%>
	<div id="content">
		<a href="<%=request.getContextPath() %>/jeungIn/main.jsp" ><img src = "<%=request.getContextPath() %>/images/mainimage_mini.png" id="mainimg"></a>
		<fieldset>
			<legend>로그인</legend>
			<div id="idrow" class='inputrow'>
				<input type="text" name="" id="id" placeholder="아이디 / 전화번호(비회원)">
			</div>
			<div id="pwdrow" class='inputrow'>
				<input type="password" name="" id="pwd" placeholder="비밀번호">
			</div>
			<div id="errMsg"></div>
			<label for="autologin" class='checkbox'>
				<input type="checkbox" name="자동로그인" id="autologin">
				자동로그인
			</label>
			<input type="button" value="로그인" id="login" class='btn'>
		</fieldset>
		<input type="button" value="임시회원으로 진행하기" id='tempuser' class='btn'>
		<input type="button" value="임시회원 로그인" id='tempuserlogin' class='btn'>
		<div id="member">
			<a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=/member/agree.jsp">회원가입</a>
			<a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=/member/findid.jsp">아이디찾기</a>
			<a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=/member/findpw.jsp">비밀번호찾기</a>
		</div>
	</div>
   	<script type="text/javascript">
	// Map<String, String[]> map= request.getParameterMap();
    // StringBuilder sb = new StringBuilder();
	// for(String key : map.keySet()) {
	// 	System.out.println("key: "+ key);
	// 	sb.append(key+"=");
	// 	for(String value : map.get(key)) {
	// 		System.out.println("value: "+ value);
	// 		sb.append(value+"&");
	// 	}
	// }
	
	// if (request.getAttribute("method")!=null) sb.append("method="+(String) request.getAttribute("method")+"&"); 
	// if (request.getAttribute("ref")!=null) sb.append("ref="+(String) request.getAttribute("ref"));
	
	
	window.onload=function() {
	    let loginbtn = document.getElementById('login');
	    let id = document.getElementById("id");
	    let pwd = document.getElementById('pwd');
	    let errMsg = document.getElementById('errMsg');
	    let autologin = document.getElementById('autologin');
	    let tempuser = document.getElementById('tempuser');
	    let tempuserlogin = document.getElementById('tempuserlogin');
		
	    
		loginbtn.addEventListener('click', loginbutton, false);
		tempuserlogin.addEventListener('click', loginbutton, false);
		function loginbutton(e) {
			if (e.target.id=='login') login('user');
			else if (e.target.id=='tempuserlogin') login('tempuser');
		}


	    tempuser.addEventListener('click', newtmpuser, false);

	    function login() {
			errMsg.innerHTML="";
			let idexp = (arguments[0]=='tempuser') ? /^(\d{3})-?(\d{4})-?(\d{4})$/ : /^(?:[a-z]{1,}[0-9a-z]*)$/;
			// let idexp = /^(?:[a-z]{1,}[0-9a-z]*)$/;
			console.log(idexp);
	        let em="";
	        let pty="";
	        let pwexp = /^(?=.*\d{1,})(?=.*[~`!@#$%\^&*()-+=]{1,})(?=.*[a-zA-Z]{1,}).{6,15}$/;
			console.log(idexp.test(id.value));
	        if (/\s/.test(id.value) || id.value.length<=6 ||!idexp.test(id.value)) em+="아이디";
	        if (!pwexp.test(pwd.value)) {
	            if (em!="") em+="와 ";
	            em+="비밀번호";
	        }
	        pty="를 입력하세요";

	        if (em!="") {
	            errMsg.innerText="올바른 "+em+pty;
	        }else {
	            let xhr = new XMLHttpRequest();
	            xhr.onreadystatechange=function() {
	                if (xhr.readyState==4 && xhr.status==200) {
	                    let json = JSON.parse(xhr.responseText);
	                    // console.log(json.errMsg);
	                    if (JSON.parse(json.error)) {
							console.log(json.errMsg);
							let err="";
							if (json.errMsg="-2") {err = "에러가 발생했습니다.";}
							else if (json.errMsg="-1") {err = "아이디가 존재하지 않습니다.";}
							else if (json.errMsg="0") {err = "아이디와 비밀번호가 맞지 않습니다.";}
							errMsg.innerText=err;
	                    }else location.href='<%=url%>';
	                }
	            }
	            xhr.open('post', "../auth/login.do", true);
	            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				let param = "id=";
				param+= (arguments[0]=='tempuser') ? id.value.replace(idexp, '$1$2$3') : id.value;
				param+="&pwd="+pwd.value;
				param+="&autologin="+autologin.checked;
	            xhr.send(param);
	        }
		}
		/* <%-- console.log('<%=sb.toString() %>');
		function passparam() {
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange=function() {
				if (xhr.readyState==4 && xhr.status==200) {
					//let json = JSON.parse(xhr.responseText);
					// location.reload();
					let form = document.createElement("form");
					form.setAttribute("action", )
				}
			};
			xhr.open('post', '../countinue', true);
			xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			let param = '<%=sb.toString() %>';
			console.log(param);
			xhr.send(param);
		} --%> */
		function newtmpuser() {
			location.href='<%=request.getContextPath()%>/auth/tmpuser.do?url=<%=url%>';
		}
	}
   	</script>
</body>
</html>