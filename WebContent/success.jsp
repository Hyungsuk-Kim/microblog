<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="Veselka" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,600italic'
	rel='stylesheet' type='text/css'>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript" src="js/scripts.js"></script>
<link rel="stylesheet" href="css/style.css">
<title>Micro Blog</title>
</head>
<body>
	<header>
	</header>
	<section id="home">
		<img src="image/logo.png" alt="Your logo" /><br />
		<form id="slick-login" action="member/login.jsp" method="POST">
		
		<div class="msg">
		<c:forEach items="${requestScope.name}" var="msgs">
			<li>${msgs}의 가입을 축하합니다^^</li>
			</c:forEach>
			</div>
		
		
			<input type="submit" value="로그인">

		</form>
		<br />
		<br />
	</section>
	<footer>
		Micro Blog에 오신 걸 환영합니다. ^^ <br /> 청년 취업 아카데미 5조 김형석, 김가애, 정다혜, 박신후의
		Web Project입니다.
	</footer>
</body>
</html>