// 삭제 여부를 확인 후 해당 url로 이동하는 기능
function deregisterCheck(url) {
	if (confirm('정말로 탈퇴하시겠습니까?')) {
		location.href = url;
	}
}
