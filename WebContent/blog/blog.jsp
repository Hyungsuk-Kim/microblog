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


<c:import url="../top.jsp"></c:import>






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



	<!-- modal -->
	
<


	<!-- /.container -->

	<!-- jQuery -->

	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/scripts.js"></script>
		
		
</body>

</html>
