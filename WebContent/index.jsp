<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
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
<title>Kitsch</title>
</head>
<body>
	<header>
		<div class="wrap">
			<nav>
				<ul id="top-menu">
					<li><a href="#home">Home</a></li>
					<li><a href="#about">About us</a></li>
					<li><a href="#loginModal" role="button" data-toggle="modal">Login</a></li>
				</ul>
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
			<input type="email" name="email" id="email" onkeyup="idcheck()"
				class="placeholder" placeholder="이메일"> <span id="idcheckLayer"></span>
				<label for="username">name</label>
			<input type="text" name="name" id="name" class="placeholder" onkeyup="namechecked()" placeholder="닉네임">
			<span id="namecheckLayer"></span>
			<label for="password">password</label> 
			<input type="password" id="password" onkeyup="passwordcheck()"
				name="password" class="placeholder" placeholder="비밀번호"> <span id="passwordcheckLayer"></span>
				
			<input type="password" id="chkpassword" onkeyup="chkpasswordcheck()"
				name="chkpassword" class="placeholder" placeholder="비밀번호 확인"><span id="chkpasswordcheckLayer"></span>
				<input
				type="submit" id="join" value="회원가입"> <input type="hidden"
				name="${errorMsgs}" value="${errorMsgs}" />

		</form>
		<br /> <br /> <br /> <a href="blogMain.jsp">Kitsch 둘러보기</a>
	</section>

	<section id="about">
		<div class="wrap">
			<h2>About Micro Blog</h2>
			<p>
				This is the Web Projects. That name is Micro Blog : ) <br /> 이
				프로젝트는 "kitsch" 라는 이름의 마이크로블로깅 웹 서비스를 구현한 프로젝트입니다. <br /> 서버측의 구현은
				java기반의 jsp, 클라이언트 구현은 HTML, CSS, JavaScript를 사용합니다. <br /> 데이터저장소는
				oracle 데이터베이스를 사용합니다. <br />
			</p>
		</div>
	</section>


	<div id="loginModal" class="modal fade" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h2 class="text-center">
						<img src="image/profile.png" class="img-circle"><br>로그인
					</h2>
				</div>
				<div class="modal-body">
					<form class="form col-md-12 center-block"
						action="<c:url value='/member?action=login'/>" method="POST">
						<div class="form-group">
							<input type="email" name="email" class="form-control input-lg"
								placeholder="Email">
						</div>
						<div class="form-group">
							<input type="password" name="password"
								class="form-control input-lg" placeholder="Password">
						</div>
						<div class="form-group">
							<button class="btn btn-primary btn-lg btn-block" type="submit">로그인</button>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<div class="col-md-12">
						<button class="btn" data-dismiss="modal" aria-hidden="true">취소</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer>
		Micro Blog에 오신 걸 환영합니다. ^^ <br /> 청년 취업 아카데미 5조 김형석, 김가애, 정다혜, 박신후의
		Web Project입니다.
	</footer>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/scripts.js"></script>
		
</body>
</html>