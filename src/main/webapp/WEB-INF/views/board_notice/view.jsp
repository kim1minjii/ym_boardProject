<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html >
<html>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>

<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	
}

h5 {
	text-align: left;
}

thead {
	text-align: center;
}

tboby {
	text-align: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#list").click(function() {
			$('#frm').attr("action", "nlist.do").submit();
		});

		$("#reply").click(function() {
			$('#frm').attr("action", "nwrite.do").submit();
		});

		$('#update').click(function() {
			$('#frm').attr("action", "nupdate.do").submit();
		});

		$('#delete').click(function() {
			$('#frm').attr("action", "ndelete.do").submit();
		});

	});
</script>

</head>




<body>
	<section class="container">
		<br>
		<h4>공지게시판</h4>
		<br>
		<!-- 본글 -->
		<table class="table table-sm">
			<thead>
				<tr>
					<!-- 제목 -->
					<td>
						<h4>${dto.subject}</h4>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<div>
							<span>날짜: </span> ${dto.reg_date}
						</div>
						<div>
							<span>작성자: </span>${dto.writer} <span>조회수: </span>${dto.readcount}
						</div>
					</td>
				</tr>

				<tr>
					<!-- 내용 -->
					<td>
						<div class="pt-4 pb-2">${dto.content}</div>
					</td>
				</tr>
				<tr>
					<!--             <th>파일</th>   -->
					<td><c:if test="${!empty dto.upload}">
							<a href="contentdownload.do?num=${dto.num}">
								${fn:substringAfter(dto.upload,"_")} </a>
						</c:if> <c:if test="${empty dto.upload }">
							<c:out value="첨부파일 없음" />
						</c:if></td>
				</tr>
			</tbody>
		</table>

		<form name="frm" id="frm" method="get" style="text-align: end;">
			<input type="hidden" name="num" value="${dto.num}" />
			<input type="hidden" name="currentPage" id="currentPage" value="${currentPage}" />
			<input type="hidden" name="ref" value="${dto.ref}" />
			<input type="hidden" name="re_step" value="${dto.re_step}" />
			<input type="hidden" name="re_level" value="${dto.re_level}" />
			<input type="button" id="list" class="btn btn-outline-secondary btn-sm" value="목록" />
			<input type="button" id="reply" class="btn btn-outline-secondary btn-sm" value="답변" />
			<input type="button" id="update" class="btn btn-outline-secondary btn-sm" value="수정" />
			<input type="button" id="delete" class="btn btn-outline-secondary btn-sm" value="삭제" />
		</form>


		<br> <br> <br>

		<!-- 댓글창 -->
		<div class="card">
			<div class="card-body">
				<textarea class="form-control" row="1"></textarea>
			</div>
			<div class="card-footer">
				<button class="btn btn-primary">등록</button>
			</div>
		</div>
		<br />

	</section>

</body>
</html>






