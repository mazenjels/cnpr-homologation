<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Questions</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Questions</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-6">
			<c:if test="${loggedUserPermission['write_question'] eq true}">
				<a href="/cnpr-homologation/question/new"
					class="btn btn-primary btn-sm"><i class="bi bi-plus-lg"></i>
					Nouveau</a> </c:if>
					
					<c:if test="${loggedUserPermission['list_question_assertion'] eq true}">
					<a href="/cnpr-homologation/question/apercu"
					class="btn btn-outline-primary btn-sm"><i
					class="bi bi-card-list"></i> Apercu du questionnaire</a></c:if>
			</div>

		</div>
		<hr />
		<c:if test="${not empty message}">
			<div class="alert alert-success alert-dismissible fade show">
				<i class="bi bi-check-circle me-1"></i> ${message}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<div class="row">
			<div class="card">
				<div class="card-header">
					<c:if test="${totalPages gt 1}">
						<ul class="pagination">
							<c:if test="${currentPage != 1}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage - 1}">&#60;&#60;</a></li>
							</c:if>

							<c:if test="${currentPage gt 3}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=1">1</a></li>
								<li class="page-item"></li>
							</c:if>

							<c:if test="${currentPage -2 gt 0}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage - 2}">${currentPage - 2}</a></li>
							</c:if>
							<c:if test="${currentPage -1 gt 0}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage - 1}">${currentPage - 1}</a></li>
							</c:if>

							<li class="page-item active"><a class="page-link"
								href="/cnpr-homologation/question/list?page=${currentPage}">${currentPage}</a></li>

							<c:if test="${currentPage +1 lt totalPages+1}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage +1}">${currentPage +1}</a></li>
							</c:if>
							<c:if test="${currentPage +2 lt totalPages+1}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage +2}">${currentPage +2}</a></li>
							</c:if>

							<c:if test="${currentPage lt totalPages-2}">
								<li class="page-item"></li>
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${totalPages}">${totalPages}</a></li>

							</c:if>

							<c:if test="${currentPage lt totalPages}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage +1}">&#62;&#62;</a></li>

							</c:if>
						</ul>
					</c:if>
				</div>

				<div class="card-body">
					<form:form>
						<table
							class="table table-bordered table-striped table-hover datatable">
							<tr>
								<th>#</th>
								<th>Intitulé</th>
								<th>Réponse</th>
								<th>Leçon</th>
								<th>Module</th>
								<th>Type de brevet</th>
								<th>Statut</th>
								<th>Actions</th>
							</tr>
							<c:forEach var="question" items="${questionList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${question.title}</td>
									<td style="font-weight: bold;">${question.reponse}</td>
									<td>${question.lesson.title}</td>
									<td>${question.lessonModule.title}</td>
									<td>Brevet pour permis de catégorie
										${question.typePermis.designation}</td>
									<td><c:choose>
											<c:when test="${question.activeStatus eq false}">Inactif</c:when>
											<c:otherwise>
								Actif
								</c:otherwise>
										</c:choose></td>
									<td>
									<c:if test="${loggedUserPermission['update_question'] eq true}"><a title="Modifier"
										href="/cnpr-homologation/question/edit/${question.id}"><i
											class="bi bi-pencil"></i></a></c:if>
											 <c:if test="${loggedUserPermission['delete_question'] eq true}">
											 <a href="/cnpr-homologation/question/delete/${question.id}"
										title="Supprimer"><i class="bi bi-trash text-danger"></i></a></c:if>
										<c:if test="${loggedUserPermission['change_status_question'] eq true}">
										<a href="/cnpr-homologation/question/changeStatus/${question.id}"
										title="Changer de statut"><i
											class="bi bi-arrow-left-right text-success"></i></a> </c:if>
											<c:if test="${loggedUserPermission['update_question'] eq true}"> <a href="/cnpr-homologation/question/viewItems/${question.id}"
										title="Afiicher la question"><i
											class="bi bi-eye text-primary"></i></a></c:if>
											</td>
								</tr>
							</c:forEach>
						</table>
					</form:form>
				</div>
				<div class="card-footer">
					<c:if test="${totalPages gt 1}">
						<ul class="pagination">
							<c:if test="${currentPage != 1}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage - 1}">&#60;&#60;</a></li>
							</c:if>

							<c:if test="${currentPage gt 3}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=1">1</a></li>
								<li class="page-item"></li>
							</c:if>

							<c:if test="${currentPage -2 gt 0}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage - 2}">${currentPage - 2}</a></li>
							</c:if>
							<c:if test="${currentPage -1 gt 0}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage - 1}">${currentPage - 1}</a></li>
							</c:if>

							<li class="page-item active"><a class="page-link"
								href="/cnpr-homologation/question/list?page=${currentPage}">${currentPage}</a></li>

							<c:if test="${currentPage +1 lt totalPages+1}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage +1}">${currentPage +1}</a></li>
							</c:if>
							<c:if test="${currentPage +2 lt totalPages+1}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage +2}">${currentPage +2}</a></li>
							</c:if>

							<c:if test="${currentPage lt totalPages-2}">
								<li class="page-item"></li>
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${totalPages}">${totalPages}</a></li>

							</c:if>

							<c:if test="${currentPage lt totalPages}">
								<li class="page-item"><a class="page-link"
									href="/cnpr-homologation/question/list?page=${currentPage +1}">&#62;&#62;</a></li>

							</c:if>
						</ul>
					</c:if>
				</div>
				
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
