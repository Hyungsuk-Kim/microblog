
$(document).ready(function(){/* jQuery toggle layout */
$('#btnToggle').click(function(){
  if ($(this).hasClass('on')) {
    $('#main .col-md-6').addClass('col-md-4').removeClass('col-md-6');
    $(this).removeClass('on');
  }
  else {
    $('#main .col-md-4').addClass('col-md-6').removeClass('col-md-4');
    $(this).addClass('on');
  }
});
});

var  xhr=  new XMLHttpRequest();

function idcheck(){
    var email = document.getElementById("email").value;
    var queryString = "&email="+email;
    var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    registerCheck();
    if(regex.test(email)==false){
        document.getElementById("idcheckLayer").innerHTML = "<font color=red style='font-family:raleway-bold, sans-serif;'>&nbsp;적절한 이메일 주소를 입력하세요.</font>";     
    }else{
        // 1. XMLHttpReqeust 객체 생성
        // 2. 이벤트 핸들러 등록
        xhr.onreadystatechange = callback;  // callback 함수를 등록
        // 3. open()를 통해 요청관련 설정을 설정
        xhr.open("POST", "member?action=idcheck", true);
        // 4. Header에 contentType 지정 - post
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        // 5. send()를 통해 요청
        xhr.send(queryString);  // 요청 쿼리를 보내준다.
        namecheck=0;
    }
};

function namechecked(){
    var name = document.getElementById("name").value;
    var queryString = "&name="+name;
    registerCheck();
    if (name.length=0) {
    	document.getElementById("namecheckLayer").innerHTML = "<font color=red style='font-family:raleway-bold, sans-serif;'>&nbsp;1자리 이상 입력하세요.</font>";   
	}else{
    
        // 1. XMLHttpReqeust 객체 생성
        // 2. 이벤트 핸들러 등록
        xhr.onreadystatechange = callback;  // callback 함수를 등록
        // 3. open()를 통해 요청관련 설정을 설정
        xhr.open("POST", "member?action=namecheck", true);
        // 4. Header에 contentType 지정 - post
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        // 5. send()를 통해 요청
        xhr.send(queryString);  // 요청 쿼리를 보내준다.
        namecheck=1;
	}
};

var password;
var chkpassword;
function passwordcheck(){
	password = document.getElementById("password").value;
       registerCheck();
       if(password.length<5){
           document.getElementById("passwordcheckLayer").innerHTML = "<font color=red style='font-family:raleway-bold, sans-serif;'>&nbsp;6자리 이상 입력하세요.</font>";     
       }else{
          document.getElementById("passwordcheckLayer").innerHTML = "<font color=#45FF74 style='font-family:raleway-bold, sans-serif;'>&nbsp;적절한 패스워드입니다.</font>";   
       }
   
}

function chkpasswordcheck(){
	chkpassword = document.getElementById("chkpassword").value;
      registerCheck();
      if(chkpassword.length<5){
          document.getElementById("chkpasswordcheckLayer").innerHTML = "<font color=red style='font-family:raleway-bold, sans-serif;'>&nbsp;6자리 이상 입력하세요.</font>";     
      }else if(password===chkpassword){
         document.getElementById("chkpasswordcheckLayer").innerHTML = "<font color=#45FF74 style='font-family:raleway-bold, sans-serif;'>&nbsp;동일한 패스워드 입니다.</font>";   
      }
  
}

var check = 0;
var namecheck = 0;
function callback(){
    if(xhr.readyState==4){      // 응답을 다 받은 경우
        if(xhr.status == 200){  // 응답코드가 200인 경우 - 정상인 경우
           var resTxt = xhr.responseText;  // 서버가 보낸 응답 text
            //alert(resTxt);
           if(resTxt=="<font color=#45FF74 style='font-family:raleway-bold, sans-serif;'>&nbsp;사용할 수 있는 email 입니다.</font>"){
              check=1;
           }else {
            check=0;
         }
           registerCheck();
           if (namecheck==0) {
        	   document.getElementById("idcheckLayer").innerHTML = resTxt;   
		}else if (namecheck==1) {
			document.getElementById("namecheckLayer").innerHTML = resTxt;   
		}
                 
        }else{
            alert("요청 처리가 정상적으로 되지 않았습니다.\n"+xhr.status);
        }
    }
}

function registerCheck(){
   var join = document.getElementById("join");
    var email = document.getElementById("email").value;
   var name = document.getElementById("name").value;
   if (email.length >= 4 && name.length >1 && password.length > 5
         && chkpassword.length > 5 && password===chkpassword) {
      join.disabled = false;
      join.style.backgroundColor="orange";
   }else {
      join.disabled = true;
      join.style.backgroundColor="gray";
   }
}