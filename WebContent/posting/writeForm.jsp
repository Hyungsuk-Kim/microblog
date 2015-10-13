<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>게시글 등록</title>

<script src="<c:url value='/ckeditor/ckeditor.js' />"></script>
<script src="<c:url value='/js/board.js' />"></script>
<script type="text/javascript" src="js/scripts.js"></script>
<link href="../css/followingList.css" rel="stylesheet">

</head>
<body>

<c:import url="../blog/blog.jsp" />

	<div class="tableContainer">
		<div class="tableRow">

			<div class="boardpage">
				<form name="writeForm" action="/microblog/posting?action=write" method="POST">

					<div class="table-responsive">
						<table id="writetable" class="maintable">
							<caption>게시글 입력</caption>
							
								<tr>
									<th>제 목</th>
									<td><input class="form-control titleinput" type="text" name="title" maxlength="100"></td>
								</tr>
							
								<tr>
									<th>내용</th>
									<td colspan="2"><textarea class=" form-control contentsinput" name="textContent"></textarea>
										<script>
											CKEDITOR.replace('contents')
										</script></td>
								</tr>
							
							<tr>
								<th>tags</th>
								<td><input class="form-control titleinput" type="text" name="tags" maxlength="100"></td>
							</tr>
							<tr>
								<th>공개여부</th>
								<td>
									<div class="btn-group" data-toggle="buttons">
										<input type="radio" name="exposure" id="option1" value="public_all">공개
										<input type="radio" name="exposure" id="option2" value="private">비공개
										<input type="hidden" name="contentType" value="textContent">
										<input type="hidden" name="postingType" value="normal">
									</div>
								</td>
							</tr>
							<%-- 
=======


							
							<tr>
								<th>tags</th>
								<td><input class="form-control titleinput" type="text"
									name="title" maxlength="100"></td>
							</tr>

							<tr>
								<th>공개여부</th>
								<td><div class=btn-group " data-toggle="buttons">

										<input type="radio" name="options" id="option1">공개


										<input type="radio" name="options" id="option2">비공개



									</div></td>
							</tr>
>>>>>>> gaae-view3
							<tr>
								<th>파일첨부</th>
								<td>
									<button class="btn">첨부하기</button>
								</td>
							</tr>
<<<<<<< HEAD
							 --%>

							<tr class="buttonbar" >
								<td>
									<div class ="puttable">
										<input class="btn btn-primary" type="submit" value="작성"> 
										<input class="btn" type="button" value="취소" onclick="goUrl('../posting.jsp');">
									</div>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>