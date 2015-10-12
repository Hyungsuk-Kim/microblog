<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
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
			<a class="navbar-brand" href="blogList.jsp">-- Kitsch --</a>
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


	<div class="imgContainer">
		<img class="myBlogImage" src="../image/team2.png" alt="myBlogImage">
	</div>




	<div class="blogName">
		<h1 class="page-header"> 가애의 테스트블로그
			<a class="btn btn-primary" href="#">Follow</a>

		</h1>
		<h3 class="user-name">By. 가애가애</h3>
		<hr />
	</div>

	<a href = "../blog/createBlogForm.jsp"><p>블로그생성하기</p></a>
	<a href = "../blog/updateBlogForm.jsp"><p>블로그수정하기</p></a>





	<div class="bannersContainer">

      <div class="row">

         <div class="banners"></div>
         
         <button class="btn btn-info" onclick="goUrl('../posting/writeForm.jsp');"> 글쓰기 </button>
         <button class="btn btn-info"> 이미지 </button>
         <button class="btn btn-info"> 비디오 </button>
         <button class="btn btn-info"> 오디오 </button>

        <!-- 
        <ul>
        <li class="btn btn-info">글쓰기 </li>
        <li class="btn btn-info">이미지 </li>
        <li class="btn btn-info">비디오 </li>
        <li class="btn btn-info">오디오 </li>

        
        
        </ul>
         -->
        
        
        
      </div>
      
   </div>


	<!--  writer form -->



	<!-- Page Content -->



	



	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
