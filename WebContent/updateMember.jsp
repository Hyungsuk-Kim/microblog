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
	<script type="text/javascript" src="<c:url value='js/scripts.js'/>"></script>
<link href="<c:url value='css/bootstrap.min.css'/>" rel="stylesheet">
<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<link href="<c:url value='css/styles.css'/>" rel="stylesheet">
</head>
<body>
	<c:import url="top.jsp"></c:import>
	<div class="navbar navbar-default" id="subnav">
		<div class="col-md-12">
			<div class="navbar-header">
				<!-- 
          <a href="#" style="margin-left:15px;" class="navbar-btn btn btn-default btn-plus dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-home" style="color:#dd1111;"></i> Home <small><i class="glyphicon glyphicon-chevron-down"></i></small></a>
          <ul class="nav dropdown-menu">
              <li><a href="#"><i class="glyphicon glyphicon-user" style="color:#1111dd;"></i> 회원정보수정</a></li>
              <li><a href="#"><i class="glyphicon glyphicon-dashboard" style="color:#0000aa;"></i> 로그아웃</a></li>
              <li><a href="#"><i class="glyphicon glyphicon-inbox" style="color:#11dd11;"></i> Pages</a></li>
              <li class="nav-divider"></li>
              <li><a href="#"><i class="glyphicon glyphicon-cog" style="color:#dd1111;"></i> Settings</a></li>
              <li><a href="#"><i class="glyphicon glyphicon-plus"></i> More..</a></li>
          </ul>
		  -->

				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse2">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse2">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="blogMain.jsp"><span
							class="glyphicon glyphicon-globe"></span> 최신 글</a></li>
					<li><a href="picture.jsp"><span
							class="glyphicon glyphicon-picture"></span> 사진</a></li>
					<li><a href="text.jsp"><span
							class="glyphicon glyphicon-font"></span> 텍스트</a></li>
					<li><a href="video.jsp"><span
							class="glyphicon glyphicon-film"></span> 비디오</a></li>
					<li><a href="audio.jsp"><span
							class="glyphicon glyphicon-music"></span> 오디오</a></li>
					<li><a href="qna.jsp"><span
							class="glyphicon glyphicon-question-sign"></span> 질문</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#aboutModal" role="button" data-toggle="modal">About</a></li>
				</ul>
			</div>
		</div>
	</div>

	<!--main-->
	<div class="container" id="main">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>회원정보</h4>
					</div>
					<div class="panel-body">

						<ul class="nav nav-tabs">
							<li class="active"><a href="#A" data-toggle="tab">회원정보수정</a></li>
							<li><a href="#B" data-toggle="tab">내 블로그 관리</a></li>
							<li><a href="#C" data-toggle="tab">팔로잉 관리</a></li>
							<li><a href="#D" data-toggle="tab">회원 탈퇴</a></li>
						</ul>
						<div class="tabbable">
							<div class="tab-content">
								<div class="tab-pane active" id="A">
									<form id ="updateForm" action="<c:url value='/member?action=update'/>" method="POST">
									<div class="well well-sm">
									
									<c:if test="${not empty updateErrorMsgs}">
				<div class="error">
					<c:forEach items="${updateErrorMsgs}" var="msgs">
						<li>${msgs}</li>
					</c:forEach>
				</div>
			</c:if>
									
									<label for="password">비밀번호</label> 
			<input type="password" id="password" class="placeholder" placeholder="비밀번호"> <span id="passwordcheckLayer"></span><br/>
				
				<label for="password">비밀번호 확인</label> 
			<input type="password" id="chkpassword" class="placeholder" placeholder="비밀번호 확인">
				
				<input type="submit" value="수정">
				
				<input type="hidden"
				name="${updateErrorMsgs}" value="${updateErrorMsgs}" />
									</div>
										</form>
								</div>
								<div class="tab-pane" id="B">
									<div class="well well-sm">
										<c:import url="/blog/blogList.jsp"></c:import>
									</div>
									
								</div>
								<div class="tab-pane" id="C">
									<div class="well well-sm">
										<c:import url="/blog/follow.jsp"></c:import>
									</div>
									
								</div>
								<div class="tab-pane" id="D">
									<div class="well well-sm">
									<form id="deregister" action="<c:url value='/member?action=deregister'/>" method="POST">
									<label for="password">비밀번호</label> <input type="password" id="deletepass"
											name="password" class="placeholder" placeholder="비밀번호">
										<input type="button" value="확인" onclick="deletepasscheck()">
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- /tabbable -->
					</div>
				</div>
			</div>
			<!--playground-->

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


	<!--/main-->


	<!--about modal-->
	<div id="aboutModal" class="modal fade" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h2 class="text-center">About</h2>
				</div>
				<div class="modal-body">
					<div class="col-md-12 text-center">
						This is the Web Projects. That name is Micro Blog : ) <br />
						성결대학교 컴퓨터공학부 5조 김형석, 정다혜, 박신후, 김가애<br />
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn" data-dismiss="modal" aria-hidden="true">확인</button>
				</div>
			</div>
		</div>
	</div>
	<!-- script references -->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script src="<c:url value='js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='js/scripts.js'/>"></script>
</body>
</html>