<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Question</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Aprecu du questionnaire</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/question/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class="row">
			<div class="col-lg-12">
				<div class="card basic">
					<div class="card-header">
						<c:if test="${totalPages gt 1}">
							<ul class="pagination">
								<c:if test="${currentPage != 1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage - 1}">&#60;&#60;</a></li>
								</c:if>

								<c:if test="${currentPage gt 3}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=1">1</a></li>
									<li class="page-item"></li>
								</c:if>

								<c:if test="${currentPage -2 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage - 2}">${currentPage - 2}</a></li>
								</c:if>
								<c:if test="${currentPage -1 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage - 1}">${currentPage - 1}</a></li>
								</c:if>

								<li class="page-item active"><a class="page-link"
									href="/cnpr-homologation/question/apercu?page=${currentPage}">${currentPage}</a></li>

								<c:if test="${currentPage +1 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage +1}">${currentPage +1}</a></li>
								</c:if>
								<c:if test="${currentPage +2 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage +2}">${currentPage +2}</a></li>
								</c:if>

								<c:if test="${currentPage lt totalPages-2}">
									<li class="page-item"></li>
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${totalPages}">${totalPages}</a></li>

								</c:if>

								<c:if test="${currentPage lt totalPages}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage +1}">&#62;&#62;</a></li>

								</c:if>
							</ul>
						</c:if>
					</div>
					<div class="card-body">
						<!-- <h5 class="card-title">Questions et reponses</h5> -->
						<c:forEach var="question" items="${mapQuestion}"
							varStatus="counter">
							<div class="row">
								<div class="col-lg-6">

									<div>
										<h6 class="card-title">${counter.count}-
											${question.key.title }</h6> <span style="font-style: italic; color: red;">[${question.key.lesson.title} - ${question.key.lessonModule.title} - Brevet pour permis de categorie ${question.key.typePermis.designation}]</span>
										<c:forEach var="item" items="${question.value}"
											varStatus="counter">

											<ul>
												<li style="list-style-type: none">${counter.count}.
													${item.value}</li>
											</ul>

										</c:forEach>
										<span style="font-weight: bold;">R/
											${question.key.reponse }</span>
									</div>

								</div>

								<div class="col-lg-6">
									<c:if test="${not empty question.key.imageUrl}">
										<img style="float: left; margin: 5px;"
											src="${question.key.imageUrl}" width="80px" height="80px"
											alt="Illustration">
									</c:if>
									<table class="table borderless">
										<tr>
											<c:if test="${not empty question.key.audioUrl}">
												<td>
													<figure>
														<audio controls src="${question.key.audioUrl}"></audio>
													</figure>
												</td>
											</c:if>
											<c:if test="${not empty question.key.videoUrl}">
												<td><video width="500px" height="400px"
														controls="controls">
														<source src="${question.key.videoUrl}" type="video/mp4" />
													</video></td>
											</c:if>



										</tr>
									</table>
								</div>

							</div>

						</c:forEach>
					</div>
					<div class="card-footer">
					
						<c:if test="${totalPages gt 1}">
							<ul class="pagination">
								<c:if test="${currentPage != 1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage - 1}">&#60;&#60;</a></li>
								</c:if>

								<c:if test="${currentPage gt 3}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=1">1</a></li>
									<li class="page-item"></li>
								</c:if>

								<c:if test="${currentPage -2 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage - 2}">${currentPage - 2}</a></li>
								</c:if>
								<c:if test="${currentPage -1 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage - 1}">${currentPage - 1}</a></li>
								</c:if>

								<li class="page-item active"><a class="page-link"
									href="/cnpr-homologation/question/apercu?page=${currentPage}">${currentPage}</a></li>

								<c:if test="${currentPage +1 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage +1}">${currentPage +1}</a></li>
								</c:if>
								<c:if test="${currentPage +2 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage +2}">${currentPage +2}</a></li>
								</c:if>

								<c:if test="${currentPage lt totalPages-2}">
									<li class="page-item"></li>
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${totalPages}">${totalPages}</a></li>

								</c:if>

								<c:if test="${currentPage lt totalPages}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/question/apercu?page=${currentPage +1}">&#62;&#62;</a></li>

								</c:if>
							</ul>
						</c:if>
					
				</div>

			</div>
		</div>

</div>



	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
