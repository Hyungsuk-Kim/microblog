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
<link href="<c:url value='css/blogList.css'/>" rel="stylesheet">
</head>
<body>

<div class="tableContainer">
        <div class="tableRow">
           
			<div class="boardpage">
			 		
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

	<c:forEach items="${blogList}" var="blogItem">

						<tr>
							<td class="image"><img class = "miniimg" src = "<c:url value='../image/team2.png'/>"></td>
							<td class="blogname"> ${blogItem.blogName}</td>
							<td class="update"><a class="btn btn-primary" href="#">수정하기</a></td>
							<td class="del"><a class="btn btn-warning" href="#">삭제하기</a></td>
						</tr>
	 </c:forEach>
					</tbody>
					<tfoot>
					<tr>
					<td id="pagenavigator" colspan="5">
					<c:if test="${startPageNumber >1 }">
                           <a
                              href="list?pageNumber=${startPageNumber-1 }&searchType=${param.searchType}&searchText=${param.searchText}">이전</a>
                        </c:if> <c:forEach begin="${startPageNumber}" end="${endPageNumber}"
                           var="pageNumber">
                           <c:choose>
                              <c:when test="${pageNumber eq currentPageNumber }">
                                 <a class="pagenumber currpage">${ pageNumber}</a>
                              </c:when>
                              <c:otherwise>
                                 <a class="pagenumber"
                                    href="list?pageNumber=${pageNumber}&searchType=${param.searchType}&searchText=${param.searchText}">${pageNumber}</a>
                              </c:otherwise>
                           </c:choose>
                        </c:forEach> <c:if test="${endPageNumber < totalPageCount}">
                           <a
                              href="list?pageNumber=${endPageNumber+1 }&searchType=${param.searchType}&searchText=${param.searchText}">다음</a>
                        </c:if>
                     </td>
                  </tr>
					</tfoot>

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
					
				</table>
				
			</div>
		</div>
	</div>
	


</body>
</html>