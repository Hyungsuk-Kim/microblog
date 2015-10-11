<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Blog Home - followingList </title>

<!-- Bootstrap Core CSS -->
<link href="css/followingList.css" rel="stylesheet">
</head>
<body>

<div class="tableContainer">
        <div class="tableRow">
           
			<div class="boardpage">
			
					<h1 class="des1">Following List </h1>
			 		<h1 class="des2">이곳에서 내가 팔로우 한 사람들의 목록을 볼 수 있어요.</h1>
			 		
			 		<hr>
			 		
				<table id="listtable" class="maintable">

			 		
			 		
				<thead>
				
						<tr>
						
							<th class="num">블로그 이미지</th>
							<th class="title">닉네임</th>
							<th class="writer">블로그 이름</th>
							<th class="regdate">팔로우</th>
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
							<td class="image"><img class = "miniimg" src = "image/team2.png"></td>
							<td class="owner"> 가애킴</td>
							<td class="blogname">가애킴의 블로그입니다.</td>
							<td class="isfollow"><a class="btn btn-primary" href="#">Follow</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "image/team2.png"></td>
							<td class="owner"> 가애킴</td>
							<td class="blogname">가애킴의 블로그입니다.</td>
							<td class="isfollow"><a class="btn btn-primary" href="#">Follow</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "image/team2.png"></td>
							<td class="owner"> 가애킴</td>
							<td class="blogname">가애킴의 블로그입니다.</td>
							<td class="isfollow"><a class="btn btn-primary" href="#">Follow</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "image/team2.png"></td>
							<td class="owner"> 가애킴</td>
							<td class="blogname">가애킴의 블로그입니다.</td>
							<td class="isfollow"><a class="btn btn-primary" href="#">Follow</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "image/team2.png"></td>
							<td class="owner"> 가애킴</td>
							<td class="blogname">가애킴의 블로그입니다.</td>
							<td class="isfollow"><a class="btn btn-primary" href="#">Follow</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "image/team2.png"></td>
							<td class="owner"> 가애킴</td>
							<td class="blogname">가애킴의 블로그입니다.</td>
							<td class="isfollow"><a class="btn btn-primary" href="#">Follow</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "image/team2.png"></td>
							<td class="owner"> 가애킴</td>
							<td class="blogname">가애킴의 블로그입니다.</td>
							<td class="isfollow"><a class="btn btn-primary" href="#">Follow</a></td>
						</tr>
	
						<tr>
							<td class="image"><img class = "miniimg" src = "image/team2.png"></td>
							<td class="owner"> 가애킴</td>
							<td class="blogname">가애킴의 블로그입니다.</td>
							<td class="isfollow"><a class="btn btn-primary" href="#">Follow</a></td>
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