<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title>Faceboot - A Facebook style template for Bootstrap</title>
		<meta name="generator" content="Bootply" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link href="css/bootstrap.min.css" rel="stylesheet">

		<link href="css/styles.css" rel="stylesheet">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	</head>
<body>

	<div class="panel panel-default">
		<div class="panel-heading">
			
			
          <img src="https://lh3.googleusercontent.com/uFp_tsTJboUY7kue5XAsGA=s28" width="28px" height="28px">
             
			작성자 : ${posting.writer}
			
		</div>
		<div class="panel-body">
		<p class="title">
		제목 : ${posting.title}
				<p>
			<p>
			내용 출력 : ${posting.contents}
			</p>
			<hr>
			<div class="comment">
			댓글 ${posting.replyCount}개
			
				<a href="#" class="good">
				<span class="glyphicon glyphicon-heart"></span>
				</a>
				
			</div>
		</div>
	</div>
</body>
</html>