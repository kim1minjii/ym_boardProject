<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!-- include summernote css/js-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="/resources/js/summernote-ko-KR.js"></script>
<title>글쓰기</title>
<link href="resources/css/board.css" type="text/css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : 'content',
			minHeight : 370,
			maxHeight : null,
			focus : true,
			lang : 'ko-KR'
		});
	});

	function goWrite(frm) {
		console.log(frm);
		var subject = frm.subject.value;
		var content = frm.content.value;

		if (subject.trim() == '') {
			alert("제목을 입력해주세요");
			return false;
		}

		if (content.trim() == '') {
			alert("내용을 입력해주세요");
			return false;
		}
		frm.submit();
	}

	$(document).ready(
			function() {
				$('#btnSave').click(
						function() {
							$('[name=content]').val(
									$('[name=content]').val().replace(/\n/gi,
											'<br/>'));
							$('#frm').attr('action', 'qwrite.do').submit();
						});
			});
</script>

<body>
	<div id="bodywrap" class="container">
		<!-- <form id="frm" name="frm" method="get" action="write.do">
			<input type="submit" id="btnWrite" value="글쓰기" />
		</form> -->

		<!-- 게시판 목록 -->
		<!--<div id="board-menu-wrap" class=""></div>
		<nav class="navbar navbar-expand-lg">
			<div class="container-fluid">
				<div class="collapse navbar-collapse justify-content-center"
					id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item border-end border-secondary"><a
							class="nav-link active" aria-current="page"
							href="/myapp/review/list.do">리뷰게시판</a></li>
						<li class="nav-item border-end border-secondary"><a
							class="nav-link active" aria-current="page"
							href="/myapp/free/list.do">자유게시판</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/myapp/qna/list.do">질문게시판</a></li>
					</ul>
				</div>
			</div>
		</nav>-->

		<h4>질문게시판</h4>

		<!-- <h2 style="text-align: center;">글작성</h2> -->
		<br> <br>
		<!-- 썸머노트, 글작성 버튼 -->
		<div style="width: 60%; margin: auto;">
			<form name="frm" method="post" action="/myapp/qwrite.do">
				<input type="text" name="subject" style="width: 40%;"
					placeholder="제목" /> <br> <br>
				<textarea id="summernote" name="content"></textarea>
				<input class="btn btn-light row justify-content-end" id="subBtn"
					type="button" value="글작성" style="float: right;"
					onclick="goWrite(this.form)" />
			</form>


			<!-- 글작성 버튼 -->
			<!-- <div class="container">
					<div class="col text-end">
						<input class="btn btn-light row justify-content-end" type="button"
							id="btnSave" onclick="location.href='/myapp/flist.do'">글작성
						</button> -->
			<!-- onclick="location.href='/myapp/flist.do'" -->
		</div>
	</div>
</body>
</html>