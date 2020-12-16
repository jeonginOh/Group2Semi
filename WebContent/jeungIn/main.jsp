<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MainCss.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
</head>

<body>
	<div id = "wrap">
		<div id = header class ="main">
			<jsp:include page="header.jsp"/>
		</div>
		<div id = "leftcontent" class ="main">
			<jsp:include page="leftcontent.jsp"/>
		</div>
		<div id = "totalcontent" class ="main">
			<jsp:include page="totalcontent.jsp"/>
		</div>
		<div id = "rightcontent" class ="main">
			<jsp:include page="rightcontent.jsp"/>
		</div>
		<div id = "footer" class ="main">
			<jsp:include page="footer.jsp"/>
		</div>
	</div>
</body>
</html>