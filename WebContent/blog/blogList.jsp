<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Blog Home - BlogList </title>

<!-- Bootstrap Core CSS -->
<link href="../css/blogList.css" rel="stylesheet">
</head>
<body>

<div class="tableContainer">
        <div class="tableRow">
           
			<div class="boardpage">
			
					<h1 class="des1">Blog List </h1>
			 		<h1 class="des2">이 곳에서 내 블로그 목록을 볼 수 있어요.</h1>
			 		
			 		<hr>
			 		
				<table id="listtable" class="maintable">

			 		
			 		
				<thead>
				
						<tr>
						
							<th class="image">블로그 이미지</th>
							<th class="blogname">블로그 이름</th>
							<th class="update">블로그 수정</th>
							<th class="del">블로그 삭제</th>
						</tr>
					</thead>
					<tbody>
					<!--  
						<c:if test="${empty requestScope.followingList}">
							<tr>
								<td colspan="5" class = "nofollow">아직 팔로우 한 사람이 없어요.</td>
							</tr>
						</c:if>
						
					-->



						<tr>
							<td class="image"><img class = "miniimg" src = "../image/team2.png"></td>
							<td class="blogname"> 가애킴의 테스트블로그</td>
							<td class="update"><a class="btn btn-primary" href="#">수정하기</a></td>
							<td class="del"><a class="btn btn-warning" href="#">삭제하기</a></td>
						</tr>
	
						
						<tr>
							<td class="image"><img class = "miniimg" src = "../image/team3.png"></td>
							<td class="blogname"> 가애킴의 테스트블로그</td>
							<td class="update"><a class="btn btn-primary" href="#">수정하기</a></td>
							<td class="del"><a class="btn btn-warning" href="#">삭제하기</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "../image/team1.png"></td>
							<td class="blogname"> 가애킴의 테스트블로그</td>
							<td class="update"><a class="btn btn-primary" href="#">수정하기</a></td>
							<td class="del"><a class="btn btn-warning" href="#">삭제하기</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "../image/team3.png"></td>
							<td class="blogname"> 가애킴의 테스트블로그</td>
							<td class="update"><a class="btn btn-primary" href="#">수정하기</a></td>
							<td class="del"><a class="btn btn-warning" href="#">삭제하기</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "../image/team2.png"></td>
							<td class="blogname"> 가애킴의 테스트블로그</td>
							<td class="update"><a class="btn btn-primary" href="#">수정하기</a></td>
							<td class="del"><a class="btn btn-warning" href="#">삭제하기</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "../image/team1.png"></td>
							<td class="blogname"> 가애킴의 테스트블로그</td>
							<td class="update"><a class="btn btn-primary" href="#">수정하기</a></td>
							<td class="del"><a class="btn btn-warning" href="#">삭제하기</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "../image/team2.png"></td>
							<td class="blogname"> 가애킴의 테스트블로그</td>
							<td class="update"><a class="btn btn-primary" href="#">수정하기</a></td>
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