<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Blog Home - createBlog </title>

<!-- Bootstrap Core CSS -->
<link href="css/createBlog.css" rel="stylesheet">
</head>

<body>


	<div class="main">
		<h4>[새로운 블로그 생성]</h4>
		<form action="blog?action=create" method="POST">
			<table class="registertable">
				<tr>
					<td class="label">블로그이름:</td>
					<td><input type="text" name="blogName" size="20"
						maxlength="15"></td>
				</tr>
				
				<tr>
					<td colspan="2">
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="블로그생성"> 
					<input type="reset" value="취소"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>