$(document).ready(function() {

	$.ajax({
		url: '/seller/register',
		type: 'get',
		dataType: 'json',

		success: function(data) {

			var jsonData = data;

			var cate1Arr = new Array();
			var cate2Arr = new Array();
			var cate1Obj = new Object();
			var cate2Obj = new Object();

			// 1차 분류 셀렉트 박스에 삽입할 데이터 준비
			for (var i = 0; i < jsonData.length; i++) {

				if (jsonData[i].big_ctg == "1") {
					cate1Obj = new Object(); //초기화
					cate1Obj.cno = jsonData[i].cno;
					cate1Obj.category_name = jsonData[i].category_name;
					cate1Arr.push(cate1Obj);
				}
			}

			// 1차 분류 셀렉트 박스에 데이터 삽입
			var cate1Select = $("select.category1")

			for (var i = 0; i < cate1Arr.length; i++) {
				cate1Select.append("<option value='" + cate1Arr[i].cno + "'>" + cate1Arr[i].category_name + "</option>");
			}

			$(document).on("change", "select.category1", function() {
				cate2Arr = [];

				// 2차 분류 셀렉트 박스에 삽입할 데이터 준비
				for (var i = 0; i < jsonData.length; i++) {

					if (jsonData[i].big_ctg == "2") {
						cate2Obj = new Object(); //초기화
						cate2Obj.cno = jsonData[i].cno;
						cate2Obj.category_name = jsonData[i].category_name;
						cate2Obj.category_level = jsonData[i].category_level;

						cate2Arr.push(cate2Obj);
					}
				}

				var cate2Select = $("select.category2");

				/*
				for(var i = 0; i < cate2Arr.length; i++) {
				  cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
					   + cate2Arr[i].cateName + "</option>");
				}
				 */

				cate2Select.empty();

				$("option:selected", this).each(function() {

					var selectVal = $(this).val();

					cate2Select.append("<option value='" + selectVal + "'>전체</option>");

					for (var i = 0; i < cate2Arr.length; i++) {
						if (selectVal == cate2Arr[i].category_level) {
							cate2Select.append("<option value='" + cate2Arr[i].cno + "'>" + cate2Arr[i].category_name + "</option>");
						}
					}

				});

			});
		},
		error: function(xhr, status, error) {
			console.log("Error: " + error);
		}


	});

});
var myEditor;
ClassicEditor
	.create(document.querySelector('#product_description'), {
		ckfinder: {
			uploadUrl: '/seller/register/ckUpload' // 내가 지정한 업로드 url (post로 요청감)
		},
		removePlugins: ['Heading'],
		language: "ko"
	})
	.then(editor => {
		console.log('Editor was initialized', editor);
		myEditor = editor;
	})
	.catch(error => {
		console.error(error);
	});

// 이미지 미리보기
$("#product_img").change(function() {
	if (this.files && this.files[0]) {
		var reader = new FileReader;
		reader.onload = function(data) {
			$(".select_img img").attr("src", data.target.result).width(500);
		}
		reader.readAsDataURL(this.files[0]);
	}
});

// 문자열 정규화
var regExp = /[^0-9]/gi;

$("#product_price").keyup(function() { numCheck($(this)); });
$("#product_stock").keyup(function() { numCheck($(this)); });

function numCheck(selector) {
	var tempVal = selector.val();
	selector.val(tempVal.replace(regExp, ""));
}