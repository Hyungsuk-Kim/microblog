<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>Insert title here</title>
</head>
<body>
  <!-- top nav -->
              	<div class="navbar navbar-blue navbar-static-top">  
                    <div class="navbar-header">
                      <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle</span>
                        <span class="icon-bar"></span>
          				<span class="icon-bar"></span>
          				<span class="icon-bar"></span>
                      </button>
                      <a href="/" class="navbar-brand logo">b</a>
                  	</div>
                  	<nav class="collapse navbar-collapse" role="navigation">
                  	
                    <form class="navbar-form navbar-right">
                        <div class="input-group input-group-sm" style="max-width:360px;">
                          <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
                          <div class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                          </div>
                        </div>
                    </form>
                    
                    <ul class="nav navbar-nav">
                      
                      
                      <li>
                        <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> 최신 글</a>
                      </li>
                      
                      <li>
                        <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-picture"></i> 사진</a>
                      </li>
                      
                       <li>
                        <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-text-size"></i> 텍스트</a>
                      </li>
                      
                       <li>
                        <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-facetime-video"></i> 비디오</a>
                      </li>
                      
                       <li>
                        <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-music"></i> 오디오</a>
                      </li>
                      
                       <li>
                        <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-globe"></i> 질문</a>
                      </li>
                      
                    </ul>
                    
                    <!-- 탑 메뉴 홈,메시지 상태 컴포넌트
                    <ul class="nav navbar-nav">
                      <li>
                        <a href="#"><i class="glyphicon glyphicon-home"></i> Home</a>
                      </li>
                      <li>
                        <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Post</a>
                      </li>
                      <li>
                        <a href="#"><span class="badge">badge</span></a>
                      </li>
                    </ul>
                     -->
                    <ul class="nav navbar-nav navbar-right">
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i></a>
                        <ul class="dropdown-menu">
                          <li><a href="">More</a></li>
                          <li><a href="">More</a></li>
                          <li><a href="">More</a></li>
                          <li><a href="">More</a></li>
                          <li><a href="">More</a></li>
                        </ul>
                      </li>
                    </ul>
                  	</nav>
                </div>
                
                <!-- /top nav -->
</body>
</html>