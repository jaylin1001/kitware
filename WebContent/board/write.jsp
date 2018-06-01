<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="div1"></div>
<div class="container">
	<h3 class="write">글쓰기</h3>
	<div class="col-lg-12 write">
		<div class="row">
			<form enctype="multipart/form-data">
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-9 form-group">
							<label>글 제목</label>
							<input name="title" type="text" class="form-control">
						</div>
						<div class="col-sm-12 form-group">
							<label>글 내용</label>
						</div>
					</div>

					<div id="summernote">
						<textarea hidden="hidden" name="content"></textarea>
					</div>
				</div>
				<div class="col-sm-4 form-group">
					 <label>첨부파일</label>
					 <input type="file" class="form-control-file" name="file1"><br>
					<div>
						<button type = "submit" class="btn_save">완료</button>
						<button type = "button">취소</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<style>
h3{
	padding-left: 12px;
	margin-top: 20px;
	margin-bottom: 20px;
}
button {
	background-color: #337ab7; /* Green */
	border: none;
	color: white;
	padding: 8px 11px;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
<script>

$(function(){			
	$(document).ready(function() {
		$('#summernote').summernote({
			height : 300,
			width : 800,
			lang : 'ko-KR'
		});
	});
	
	$('button[type=submit]').click(function(){
		var markupStr = $('#summernote').summernote('code').trim();
	    $('textarea').text(markupStr);
	    
	    $.ajax({
			  url: '${pageContext.request.contextPath}/boardwrite.do',
			  type:'post',
			  data:$('form').serialize(),
			  success:function(data){
				 $('div#page-wrapper').empty(); 
				 var resultObj =data.trim();
				 if(resultObj == "1"){
					 location.href="${pageContext.request.contextPath}/boardlist.do";
				 }else{
					 
				 }
			  }
		  });
	    return false;
	});
		
	
	
});

$(function() {
		$('.btn_save').click(function() {
			var $targetObj = $("div.container");
			$targetObj.empty();
			$("#div1").load("board/board.jsp");
		});
	});
	var className = 'board';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
</script>