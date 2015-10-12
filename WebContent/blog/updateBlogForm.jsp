<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Blog Home - Start Bootstrap Template</title>

<!-- Bootstrap Core CSS -->

<link href="../css/blog.css" rel="stylesheet">
<script src="<c:url value='/js/board.js'/>"></script>


<!-- Custom CSS
    <link href="css/blog-home.css" rel="stylesheet"> -->

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body background="../image/blue.jpg">





	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="blogList.jsp">-- kitsch --</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<!-- <li><a href="#">About</a></li>
				<li><a href="#">Services</a></li>
				 -->
				<li><a href="#">00000 명의 사람들이 이 블로그를 방문했어요!</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>



	<div class="updateBlog">
		<form id="updateBlog" action="update" method="POST">

			<div class="imgContainer">


				<a href="blog.jsp"><img class="myBlogImage"
					src="../image/team2.png" alt="myBlogImage"></a> <br> <br>
			</div>
			<input type="submit" class="btn btn" value="이미지 변경하기">


			<div class="blogName">
				<h1 class="page-header">



					<div class="blogNameSize">
						<input type="text" class="form-control"
							placeholder="블로그 명을 입력해 주세요."> <br />
					</div>


				<input type="submit" class="btn" value="블로그 중복확인">



				</h1>

			</div>


			<hr />
	</div>


	<div class="updateBlog">

		<input type="submit" class="btn btn-primary" value="수정완료"> <input
			type="button" class="btn" value="취소" onclick="goUrl('blog.jsp');">


	</div>

	</form>

	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>

</html>
