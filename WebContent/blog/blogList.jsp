<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<title>Kitsch</title>
		<meta name="generator" content="Bootply" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		
		<link href="../css/styles.css" rel="stylesheet">
</head>
<body>







<c:import url="../top2.jsp"></c:import>


	
<div class="tableContainer" style="padding: 100px">
        <div class="tableRow">
           
			<div class="boardpage">
		
						 								
			 		
				<table id="listtable" class="maintable">

					<h1 class="des1">블로그 목록</h1>
					<h1 class="des2">내 블로그 목록을 볼 수 있어요.</h1>


					<thead>
				
						<tr>
						
							<th class="image">블로그 이미지</th>
							<th class="blogname">블로그 이름</th>
							<th class="update">블로그 수정</th>
							<th class="del">블로그 삭제</th>
						</tr>
					</thead>
					<tbody>
					 

						
					

	<c:forEach items="${blogList}" var="blogItem">

						<tr>
							<td class="image"><img class = "miniimg" src = "../image/team2.png"></td>
							<td class="blogname"> ${blogItem.blogName}</td>
							<td class="update"><a class="btn btn-primary" href="#">수정하기</a></td>
							<td class="del"><a class="btn btn-warning" href="#">삭제하기</a></td>
						</tr>
	 </c:forEach>
						
						<tr>
							<td class="image"><img class = "miniimg" src = "../image/blog1.JPG"></td>
							<td class="blogname"> Kitsch의 블로그</td>
							<td class="update"><a class="btn btn-primary" href="../blog/updateBlogForm.jsp">수정하기</a></td>
							<td class="del"><a class="btn btn-warning" href="#">삭제하기</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "../image/blog2.JPG"></td>
							<td class="blogname"> 멍멍도 좋아해</td>
							<td class="update"><a class="btn btn-primary"href="../blog/updateBlogForm.jsp">수정하기</a></td>
							<td class="del"><a class="btn btn-warning" href="#">삭제하기</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "../image/blog3.JPG"></td>
							<td class="blogname"> Kitsch's Hello my blog!</td>
							<td class="update"><a class="btn btn-primary" href="../blog/updateBlogForm.jsp">수정하기</a></td>
							<td class="del"><a class="btn btn-warning" href="#">삭제하기</a></td>
						</tr>
	
	

						



						<!--  
						<c:forEach var="following" items="${requestScope.getfollowingList}">
						
					
							<tr>
								<td class="num">${following.num}</td>
								<td class="title">
									<c:forEach begin="1" end="${following.replyStep}">
										<c:out value="&nbsp&nbsp" escapeXml="false" />
									</c:forEach>
									<a href="read?num=${following.num}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${requestScope.currentPageNumber}">${board.title}</a>
								</td>
								<td class="writer">${following.writer}</td>
								<td class="regdate">${following.regDate}</td>
								<td class="readcount">${following.readCount}</td>
							</tr>
						 </c:forEach> -->
					</tbody>
					
				</table>
				
			</div>
		</div>
	</div>
	


</body>
</html>