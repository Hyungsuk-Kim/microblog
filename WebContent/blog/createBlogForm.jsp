<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<title>Bootstrap Google Plus Theme</title>
<meta name="generator" content="Bootply" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
	<script type="text/javascript" src="<c:url value='../js/login.js'/>"></script>
<link href="<c:url value='../css/bootstrap.min.css'/>" rel="stylesheet">
<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<link href="<c:url value='../css/styles.css'/>" rel="stylesheet">
</head>
<body>
	<c:import url="../top.jsp"></c:import>

	<div class="navbar navbar-default" id="subnav">
		<div class="col-md-12">
		<div class="collapse navbar-collapse" id="navbar-collapse2">
		<ul class="nav navbar-nav navbar-left">
		<li><a href="#">새로운 블로그 생성</a></li></ul>
		</div>
		</div>
		</div>
<div class="container" id="main">
<div class="row">
<div class="col-sm-10 col-sm-offset-1">
				<div class="panel panel-default">
				
		<div class="step1">STEP 1.</div>
		<div class="des2">사용할 블로그 이름을 정해 주세요.</div>

		<form name = "createBlog"action= "create" method="POST">


			<table class="registertable">
				<tr>

					<td><input type="text" name="blogNameinput" size="60" maxlength="15" class="form-control"></td>
							<td>&nbsp;</td>
					<!--  <td><input type="submit" value="중복확인" class="btn"></td> -->
				</tr>


			</table>

			<br /> <br />

			<div class="step1">STEP 2.</div>
			<div class="des2">내 프로필 이미지를 선택해 주세요.</div>

			<table id="newblogtable">
				<tr>

					<td ><input type="text" name="blogImginput" size="60"
						maxlength="15" class="form-control"></td>
						
						<td>&nbsp;</td>
					<td><input type="submit" value="찾아보기" class="btn"></td>
				</tr>




			</table>


			<br /> <br /> 
			<!-- <div class = "btncenter"> -->
			<input type="submit" value="블로그생성" class="btn btn-primary"> 
			<input type="reset" value="취소" class="btn" onclick="goUrl('blog.jsp');">
			
			<!-- </div> -->


		</form>
		</div>
		</div>
		<br>

			<div class="clearfix"></div>

			<hr>
			<div class="col-md-12 text-center">
				<p>
					성결대학교 웹프로젝트<br>5조 김형석, 정다혜, 박신후, 김가애
				</p>
			</div>
			<hr>
		</div>
		</div>
	

</body>
</html>