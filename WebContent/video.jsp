<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<title>Kitsch</title>
		<meta name="generator" content="Bootply" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<link href="css/styles.css" rel="stylesheet">
	</head>
	<body>
<nav class="navbar navbar-fixed-top header">
 	<div class="col-md-12">
        <div class="navbar-header">
          
          <c:choose>
          	<c:when test="${empty sessionScope.loginMember}"><a href="index.jsp" class="navbar-brand">Kitsch</a></c:when>
          	<c:otherwise><a href="blog.jsp" class="navbar-brand">Kitsch</a></c:otherwise>
          </c:choose>
          
          
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse1">
          <i class="glyphicon glyphicon-search"></i>
          </button>
        </div>
        
        <div class="collapse navbar-collapse" id="navbar-collapse1">
        
          <form class="navbar-form pull-left">
              <div class="input-group" style="max-width:470px;">
                <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
                <div class="input-group-btn">
                  <button class="btn btn-default btn-primary" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                </div>
              </div>
          </form>
          
          <ul class="nav navbar-nav navbar-right">
           <li><a href="#" id="btnToggle"><i class="glyphicon glyphicon-th-large"></i></a></li>
             <li>
             <c:if test="${not empty sessionScope.loginMember }">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span></a></c:if>
                <ul class="dropdown-menu">
                  <li><a href="updateMember.jsp"><span class="badge pull-right"></span>회원정보</a></li>
                  <li><a href="<c:url value='/member?action=logout'/>"><span class="badge pull-right"></span>로그아웃</a></li>
                  <!-- <li><a href="#"><span class="label label-info pull-right">1</span>Link</a></li> -->
                </ul>
             </li>
           </ul>
        </div>	
     </div>	
</nav>
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
          
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse2">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          </button>
      
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse2">
        <ul class="nav navbar-nav navbar-left">
        	<li><a href="blogMain.jsp" ><span class="glyphicon glyphicon-globe"></span> 최신 글</a></li>
        	<li><a href="picture.jsp"><span class="glyphicon glyphicon-picture"></span> 사진</a></li>
        	<li><a href="text.jsp"><span class="glyphicon glyphicon-font"></span> 텍스트</a></li>
        	<li class="active"><a href="video.jsp"><span class="glyphicon glyphicon-film"></span> 비디오</a></li>
        	<li><a href="audio.jsp"><span class="glyphicon glyphicon-music"></span> 오디오</a></li>
        	<li><a href="qna.jsp"><span class="glyphicon glyphicon-question-sign"></span> 질문</a></li>
        </ul>
          <ul class="nav navbar-nav navbar-right">
             <li>
             <c:if test="${empty sessionScope.loginMember }"><a href="#loginModal" role="button" data-toggle="modal">Login</a></c:if></li>
             <li><a href="#aboutModal" role="button" data-toggle="modal">About</a></li>
           </ul>
        </div>	
     </div>	
</div>

<!--main-->
<div class="container" id="main">
   <div class="row">
   <div class="col-md-4 col-sm-6">
        <div class="panel panel-default">
          <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Bootstrap Examples</h4></div>
   			<div class="panel-body">
              <div class="list-group">
                <a href="http://bootply.com/tagged/modal" class="list-group-item">Modal / Dialog</a>
                <a href="http://bootply.com/tagged/datetime" class="list-group-item">Datetime Examples</a>
                <a href="http://bootply.com/tagged/datatable" class="list-group-item">Data Grids</a>
              </div>
            </div>
   		</div>
        <div class="well"> 
             <form class="form-horizontal" role="form">
              <h4>What's New</h4>
               <div class="form-group" style="padding:14px;">
                <textarea class="form-control" placeholder="Update your status"></textarea>
              </div>
              <button class="btn btn-success pull-right" type="button">Post</button><ul class="list-inline"><li><a href="#"><i class="glyphicon glyphicon-align-left"></i></a></li><li><a href="#"><i class="glyphicon glyphicon-align-center"></i></a></li><li><a href="#"><i class="glyphicon glyphicon-align-right"></i></a></li></ul>
            </form>
        </div>
     
        <div class="panel panel-default">
           <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>More Templates</h4></div>
   			<div class="panel-body">
              <img src="//placehold.it/150x150" class="img-circle pull-right"> <a href="#">Free @Bootply</a>
              <div class="clearfix"></div>
              There a load of new free Bootstrap 3 ready templates at Bootply. All of these templates are free and don't require extensive customization to the Bootstrap baseline.
              <hr>
              <ul class="list-unstyled"><li><a href="http://www.bootply.com/templates">Dashboard</a></li><li><a href="http://www.bootply.com/templates">Darkside</a></li><li><a href="http://www.bootply.com/templates">Greenfield</a></li></ul>
            </div>
         </div> 

	</div>
  	<div class="col-md-4 col-sm-6">
      	 
          <div class="well"> 
             <form class="form">
              <h4>Sign-up</h4>
              <div class="input-group text-center">
              <input type="text" class="form-control input-lg" placeholder="Enter your email address">
                <span class="input-group-btn"><button class="btn btn-lg btn-primary" type="button">OK</button></span>
              </div>
            </form>
          </div>

      	 <div class="panel panel-default">
           <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Bootply Editor &amp; Code Library</h4></div>
   			<div class="panel-body">
              <p><img src="//placehold.it/150x150" class="img-circle pull-right"> <a href="#">The Bootstrap Playground</a></p>
              <div class="clearfix"></div>
              <hr>
              Design, build, test, and prototype using Bootstrap in real-time from your Web browser. Bootply combines the power of hand-coded HTML, CSS and JavaScript with the benefits of responsive design using Bootstrap. Find and showcase Bootstrap-ready snippets in the 100% free Bootply.com code repository.
            </div>
         </div>
      
      	 <div class="panel panel-default">
           <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Portlet Heading</h4></div>
   			<div class="panel-body">
              <ul class="list-group">
              <li class="list-group-item">Modals</li>
              <li class="list-group-item">Sliders / Carousel</li>
              <li class="list-group-item">Thumbnails</li>
              </ul>
            </div>
   		 </div>
      
  	</div>
  	<div class="col-md-4 col-sm-6">
         <div class="panel panel-default">
           <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Portlet Heading</h4></div>
   			<div class="panel-body">
              <ul class="list-group">
              <li class="list-group-item">Bootply Playground</li>
              <li class="list-group-item">Bootstrap Editor</li>
              <li class="list-group-item">Bootstrap Templates</li>
              </ul>
            </div>
   		</div>
        
        <div class="panel panel-default">
           <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Stackoverflow</h4></div>
   			<div class="panel-body">
              <img src="//placehold.it/150x150" class="img-circle pull-right"> <a href="#">Keyword: Bootstrap</a>
              <div class="clearfix"></div>
              <hr>
              
              <p>If you're looking for help with Bootstrap code, the <code>twitter-bootstrap</code> tag at <a href="http://stackoverflow.com/questions/tagged/twitter-bootstrap">Stackoverflow</a> is a good place to find answers.</p>
              
              <hr>
              <form>
              <div class="input-group">
                <div class="input-group-btn">
                <button class="btn btn-default">+1</button><button class="btn btn-default"><i class="glyphicon glyphicon-share"></i></button>
                </div>
                <input type="text" class="form-control" placeholder="Add a comment..">
              </div>
          	  </form>
              
            </div>
         </div>
 
        <div class="panel panel-default">
          <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Top Items</h4></div>
   			<div class="panel-body">
              <div class="list-group">
                <a href="http://bootply.com/tagged/bootstrap-3" class="list-group-item active">Bootstrap 3</a>
                <a href="http://bootply.com/tagged/snippets" class="list-group-item">Snippets</a>
                <a href="http://bootply.com/tagged/example" class="list-group-item">Examples</a>
              </div>
            </div>
   		</div>
      
    </div>
  </div>
    
  <!--playground-->
    <br>
    
    <div class="clearfix"></div>
      
    <hr>
    <div class="col-md-12 text-center"><p>성결대학교 웹프로젝트<br>5조 김형석, 정다혜, 박신후, 김가애</p></div>
    <hr>
    
  </div>
<!--/row-->
  

  
  
<!--/main-->

<!--login modal-->
<div id="loginModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h2 class="text-center"><img src="image/profile.png" class="img-circle"><br>로그인</h2>
      </div>
      <div class="modal-body">
          <form class="form col-md-12 center-block" action="<c:url value='/member?action=login'/>" method="POST">
            <div class="form-group">
              <input type="email" name="email" class="form-control input-lg" placeholder="Email">
            </div>
            <div class="form-group">
              <input type="password" name="password" class="form-control input-lg" placeholder="Password">
            </div>
            <div class="form-group">
           <!-- <input type="submit" class="btn btn-primary btn-lg btn-block" value="로그인"> -->
              <button class="btn btn-primary btn-lg btn-block" type="submit">로그인</button>
              <span class="pull-left"><a href="index.jsp">회원가입</a></span>
            </div>
          </form>
      </div>
      <div class="modal-footer">
          <div class="col-md-12">
          <button class="btn" data-dismiss="modal" aria-hidden="true">취소</button>
		  </div>	
      </div>
  </div>
  </div>
</div>


<!--about modal-->
<div id="aboutModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
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
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/scripts.js"></script>
	</body>
</html>