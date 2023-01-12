<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<style>
.profile {
	width: 30px;
	height: 30px;
	object-fit: cover;
}
</style>


<body>

	<form id="frm" name="frm" method="get" action="rwrite.do">
		<input type="submit" id="btnWrite" class="btn btn-outline-secondary"
			value="글쓰기" />
	</form>
	
	
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


		<h5>리뷰게시판</h5>

		<!-- 검색창 -->
		<form action="/myapp/rlist.do" method="get">
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


		<!-- Bootstrap Card  -->
		<div class="row g-4">
			<!--<c:if test="${empty aList}">
				<c:out value="${pv.searchWord}"/> 에 대한 검색결과가 없습니다.
			</c:if>-->
			<c:if test="${!empty aList}">
				<c:forEach items="${aList}" var="dto" varStatus="status">
					<div class="col-3">
						<c:url var="path" value="rview.do">
							<c:param name="currentPage" value="${pv.currentPage}" />
							<c:param name="num" value="${dto.num}" />
						</c:url>
						<a href="${path}">
							<div class="card">
								<img src="resources/images/review/${dto.upload}"
									class="card-img-top" alt="..." onerror="this.src='resources/images/review/noimage.png'">
								<div class="card-body">
									<div>
										<img class="profile"
											src="/myapp/resources/images/review/${dto.upload}"
											class="card-img-top" alt="..."> 
										<span class="card-title">${dto.writer}</span>
										<h6 class="card-title">${dto.reg_date}</h6>
										<p class="card-text">${dto.subject}</p>	
									</div>
								</div>
							</div>
						</a>
					</div>
				</c:forEach>
			</c:if>
		</div>
		<!-- End Bootstrap Card  -->

		<!-- 페이징 -->
		<div class="pagelist text-center">
			<!-- 이전 출력 시작 -->
			<c:if test="${pv.startPage>1}">
				<a href="rlist.do?currentPage=${pv.startPage-pv.blockPage}">이전</a>
			</c:if>
			<!-- 이전 출력 끝 -->

			<!-- 페이지 출력 시작 -->
			<c:forEach var="i" begin="${pv.startPage}" end="${pv.endPage}">
				<span> <c:url var="currPage" value="rlist.do">
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
				<a href="rlist.do?currentPage=${pv.startPage+pv.blockPage}">다음</a>
			</c:if>
			<!-- 다음 출력 끝 -->
		</div>
		<!-- 페이징 -->


		<!-- 게시물 count -->
		<div class="container px-4">
			<div class="row gx-5">
				<div class="container">총 ${cntt}개 게시물이 있습니다.</div>
			</div>
		<!-- 게시물 count -->


		</div>
	</div>
</body>
</html>

