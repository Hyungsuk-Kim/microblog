<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Blog Home - createBlog</title>

<!-- Bootstrap Core CSS -->
<link href="../css/createBlog.css" rel="stylesheet">
</head>

<body>


	<div class="main">
	<div class="main2">
		<h1 class="des1">새로운 블로그 생성</h1>
		<hr />

		<p class="step1">STEP 1.</p>
		<p class="des2">사용할 블로그 이름을 정해 주세요.</p>

		<form name = "createBlog"action= "create" method="POST">


			<table class="registertable">
				<tr>

					<td><input type="text" name="blogNameinput" size="60" maxlength="15" class="form-control"></td>
							<td>&nbsp;</td>
					<!--  <td><input type="submit" value="중복확인" class="btn"></td> -->
				</tr>


			</table>

			<br /> <br />

			<p class="step1">STEP 2.</p>
			<p class="des2">내 프로필 이미지를 선택해 주세요.</p>

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

</body>
</html>