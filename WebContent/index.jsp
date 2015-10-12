<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
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
		<div class="wrap">
			<nav>
				<ul id="top-menu">
					<li><a href="#home">Home</a></li>
					<li><a href="#about">About us</a></li>
					<li><a href="#login"><a href="member/login.jsp">Login</a></a></li>
				</ul>
				<div id="pull">
					<span class="btn">Menu</span>
				</div>
			</nav>
		</div>
	</header>
	<section id="home">
		<img src="image/logo.png" alt="Your logo" /><br />
		<form id="slick-login" action="member?action=register" method="POST">
		
		<c:if test="${not empty errorMsgs}">
		<div class="error">
		<c:forEach items="${errorMsgs}" var="msgs">
			<li>${msgs}</li>
			</c:forEach>
			</div>
		</c:if>
		
			<label for="email">email</label> 
			<input type="email" name="email" class="placeholder" placeholder="이메일">
			 <label for="username">name</label>
			<input type="text" name="name" class="placeholder" placeholder="닉네임">
			<label for="password">password</label>
			<input type="password" name="password" class="placeholder" placeholder="비밀번호"> 
				
				<input type="submit" value="회원가입">
				
		
				<input type="hidden" name="${errorMsgs}"
			value="${errorMsgs}" />

		</form>
		<br />
		<br />
		<br /> <a href="searchPassword.jsp">비밀번호를 잊어 버리셨나요?</a>
	</section>
	<section id="about">
		<div class="wrap">
			<h2>About Micro Blog</h2>
			<p>
				This is the Web Projects. That name is Micro Blog : ) <br /> 이 프로젝트는
				"kitsch" 라는 이름의 마이크로블로깅 웹 서비스를 구현한 프로젝트입니다. <br /> 서버측의 구현은 java기반의
				jsp, 클라이언트 구현은 HTML, CSS, JavaScript를 사용합니다. <br /> 데이터저장소는 oracle
				데이터베이스를 사용합니다. <br />
			</p>
			</div>
			</section>
	<footer>
		Micro Blog에 오신 걸 환영합니다. ^^ <br /> 청년 취업 아카데미 5조 김형석, 김가애, 정다혜, 박신후의
		Web Project입니다.
	</footer>
</body>