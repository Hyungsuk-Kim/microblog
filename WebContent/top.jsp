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
		
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		
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


	

</body>
</html>