<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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



<c:import url="../top.jsp"></c:import>


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
