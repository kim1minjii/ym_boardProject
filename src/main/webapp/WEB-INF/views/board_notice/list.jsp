<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<body>
	<div id="bodywrap" class="container">


		<!-- 게시판 목록 -->
		<div id="board-menu-wrap" class=""></div>
		<nav class="navbar navbar-expand-lg">
			<div class="container-fluid">
				<div class="collapse navbar-collapse justify-content-center"
					id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item border-end border-secondary"><a
							class="nav-link active" aria-current="page"
							href="/myapp/rlist.do">리뷰게시판</a></li>
						<li class="nav-item border-end border-secondary"><a
							class="nav-link active" aria-current="page"
							href="/myapp/flist.do">자유게시판</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/myapp/qlist.do">질문게시판</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<h4>공지게시판</h4>

		<!-- 검색창 -->
		<form action="/myapp/nlist.do" method="get">
			<div class="form-wrap">
				<div class="search-wrap" style="text-align: -webkit-right;">
					<div class="input-group mb-3 w-50">
						<select name="searchKey" class="form-control">
							<option value="subject" <c:if test="${pv.searchKey eq 'subject'}">selected</c:if>>제목</option>
							<option value="content" <c:if test="${pv.searchKey eq 'content'}">selected</c:if>>내용</option>
							<option value="total" <c:if test="${pv.searchKey eq 'total'}">selected</c:if>>제목+내용</option>
						</select> 
						<input type="text" name="searchWord" class="w-75 form-control"
							placeholder="검색어를 입력해주세요." value="${pv.searchWord}">
						<button class="btn btn-outline-secondary" type="submit">검색</button>
					</div>
				</div>
			</div>
		</form>
		<!-- END 검색창 -->

		<table class="table table-hover">
			<thead>
				<tr>
					<th width="10%">번호</th>
					<th width="40%">제목</th>
					<th width="15%">작성자</th>
					<th width="15%">작성일</th>
					<th width="10%">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${aList}" var="dto">
					<tr>
						<td>${dto.num}</td>
						<td><c:url var="path" value="nview.do">
								<c:param name="currentPage" value="${pv.currentPage}" />
								<c:param name="num" value="${dto.num}" />
							</c:url> <c:if test="${dto.re_level>0}">
								<img src="resources/images/level.gif" width="${20*dto.re_level}"
									height="15" />
								<img src="resources/images/re.gif" />
							</c:if> <a href="${path}">${dto.subject}</a></td>
						<td>${dto.writer}</td>
						<td>${dto.reg_date}</td>
						<td>${dto.readcount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<!-- 페이징 -->
		<div class="pagelist text-center">
			<!-- 이전 출력 시작 -->
			<c:if test="${pv.startPage>1}">
				<a href="nlist.do?currentPage=${pv.startPage-pv.blockPage}">이전</a>
			</c:if>
			<!-- 이전 출력 끝 -->

			<!-- 페이지 출력 시작 -->
			<c:forEach var="i" begin="${pv.startPage}" end="${pv.endPage}">
				<span> <c:url var="currPage" value="nlist.do">
						<c:param name="currentPage" value="${i}" />
					</c:url> <c:choose>
						<c:when test="${i==pv.currentPage}">
							<a href="${currPage}" class="pagecolor">${i}</a>
						</c:when>
						<c:otherwise>
							<a href="${currPage}">${i}</a>
						</c:otherwise>
					</c:choose>
				</span>
			</c:forEach>
			<!-- 페이지 출력 끝 -->

			<!-- 다음 출력 시작 -->
			<c:if test="${pv.endPage<pv.totalPage}">
				<a href="nlist.do?currentPage=${pv.startPage+pv.blockPage}">다음</a>
			</c:if>
			<!-- 다음 출력 끝 -->
		</div>
		<!-- 페이징 -->


		<!-- 게시물 count -->
		<div class="container px-4">
			<div class="row gx-5">
				<div class="container">총 ${cntt}개 게시물이 있습니다.</div>
			</div>

			<!-- 글쓰기 버튼 -->
			<div class="container">
				<div class="col text-end">
					<button class="btn btn-light row justify-content-end" type="button"
						onclick="location.href='/myapp/nwrite.do'">글쓰기</button>
				</div>
			</div>
		</div>
</body>
</html>