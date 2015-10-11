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
			
					<h1 class="des1">My Blog List </h1>
			 		<h1 class="des2">이곳에서 내 블로그 리스트를 볼 수있어요.</h1>
			 		
			 		<hr>
			 		
				<table id="listtable" class="maintable">

					<tbody>
						<c:if test="${empty requestScope.getBlogList}">생성된 블로그가없어요.</c:if>
						
						<c:forEach var="blog" items="${requestScope.getBlogList}">
							<tr>
								<td class="num">${blog.num}</td>
								<td class="title">${blog.title}</td>
								<td class="writer">${blog.writer}</td>
								<td class="contents">${blog.contents}</td>
								<td class="ip">${blog.ip}</td>
								<td class="read_count"> ${blog.read_count}</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td id="pagenavigator" colspan="5">
								<!-- 
								<a href="list">이전</a>
								<a class="pagenumber currpage">3</a>
								<a class="pagenumber" href="list">4</a>
								<a class="pagenumber" href="list">5</a>
								<a href="board?action=list">다음</a>
								-->
								<c:if test="${requestScope.startPageNumber > 1}">
									<a href="list?searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${requestScope.startPageNumber - 1}">이전</a>
								</c:if>
								<c:forEach begin="${requestScope.startPageNumber}" end="${requestScope.endPageNumber}" var="pageNumber">
									<c:choose>
										<c:when test="${requestScope.currentPageNumber == pageNumber}">
											<a class="pagenumber currpage" >${pageNumber}</a>
										</c:when>
										<c:otherwise>
											<a class="pagenumber" href="list?pageNumber=${pageNumber}&searchType=${param.searchType}&searchText=${param.searchText}">${pageNumber}</a>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
								<c:if test="${requestScope.endPageNumber < requestScope.totalPageCount}">
									<a href="list?searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${requestScope.endPageNumber + 1}">다음</a>
								</c:if>
							</td>
						</tr>
					</tfoot>
				</table>
				
			</div>
		</div>
	</div>
	


</body>
</html>