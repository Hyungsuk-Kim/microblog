/**
 * 
 */
var  xhr2;
var emailcheck=0;
function loginEmailCheck(){
	xhr2 = new XMLHttpRequest();
	var loginemail = document.getElementById("loginemail").value;
    var queryString = "&email="+loginemail;
    var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    if(regex.test(loginemail)==false){
        document.getElementById("loginemailcheckLayer").innerHTML = "<font color=red style='font-family:raleway-bold, sans-serif;'>&nbsp;적절한 이메일 주소를 입력하세요.</font>";     
    }else{
    	loginPassCheck();
        // 1. XMLHttpReqeust 객체 생성
        // 2. 이벤트 핸들러 등록
        xhr2.onreadystatechange = callback2;  // callback 함수를 등록
        // 3. open()를 통해 요청관련 설정을 설정
        xhr2.open("POST", "member?action=loginEmailCheck", true);
        // 4. Header에 contentType 지정 - post
        xhr2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        // 5. send()를 통해 요청
        xhr2.send(queryString);  // 요청 쿼리를 보내준다.
        emailcheck=0;
    }
}

function loginPassCheck(){
	 var loginpassword = document.getElementById("loginpassword").value;
	 var loginemail = document.getElementById("loginemail").value;
	    var queryString = "&password="+loginpassword+"&email="+loginemail;
	    if (loginpassword.length<5) {
	    	document.getElementById("loginpasscheckLayer").innerHTML = "<font color=red style='font-family:raleway-bold, sans-serif;'>&nbsp;5자리 이상 입력하세요.</font>";   
		}else{
			//loginEmailCheck();
	        // 1. XMLHttpReqeust 객체 생성
	        // 2. 이벤트 핸들러 등록
	        xhr2.onreadystatechange = callback2;  // callback 함수를 등록
	        // 3. open()를 통해 요청관련 설정을 설정
	        xhr2.open("POST", "member?action=loginPassCheck", true);
	        // 4. Header에 contentType 지정 - post
	        xhr2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	        // 5. send()를 통해 요청
	        xhr2.send(queryString);  // 요청 쿼리를 보내준다.
	        emailcheck=1;
		}
}

function callback2(){
    if(xhr2.readyState==4){      // 응답을 다 받은 경우
        if(xhr2.status == 200){  // 응답코드가 200인 경우 - 정상인 경우
           var resTxt = xhr2.responseText;  // 서버가 보낸 응답 text
            //alert(resTxt);
           //alert("까꿍");
           if (emailcheck==0) {
        	   document.getElementById("loginemailcheckLayer").innerHTML = resTxt;   			
		}else if(emailcheck==1) {
			document.getElementById("loginpasscheckLayer").innerHTML = resTxt;
		}
                 
        }else{
            alert("요청 처리가 정상적으로 되지 않았습니다.\n"+xhr2.status);
        }
    }
}