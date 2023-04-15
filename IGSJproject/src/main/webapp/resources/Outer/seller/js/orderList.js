/* 배송상태 변경 로직 ajax */
function changePaymentStatus(odNum) {
	var statusValue = $('#StatusValue-' + odNum).val();

  console.log('odnum:', odNum); // 선택된 값 출력
  console.log('statusValue:', statusValue); // 선택된 값 출력


  	if(confirm("배송 상태를 변경 하시겠습니까?")){

			$.ajax({
				url: "/seller/changePaymentStatus",
				type: "post",
				data: {
					odNum : odNum,
					"paymentStatus": statusValue
				},

				success: function(result) {
					if (result == 1) {

						alert("배송상태 변경이 완료되었습니다.");
						location.reload();

					}
				},
				error: function(error) {
					alert("알 수 없는 오류로 변경에 실패하였습니다.");
					return false;
				}
			});
} else {
		alert("변경을 취소하셨습니다.");
}		
}