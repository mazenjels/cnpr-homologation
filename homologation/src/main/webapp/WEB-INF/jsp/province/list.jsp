<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Provinces</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau
						de bord</a></li>
				<li class="breadcrumb-item active">Provinces</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/province/new" class="btn btn-primary"><i
					class="bi bi-plus-lg"></i> Nouveau</a>
			</div>
		</div>
		<hr />
		<c:if test="${not empty message}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<i class="bi bi-check-circle me-1"></i> ${message}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<div class="row"
			style="overflow-x: hidden; overflow-y: auto; height: 500px;">
			<div class="card">
				<div class="card-header">
					
						<c:if test="${totalPages gt 1}">
							<ul class="pagination">
								<c:if test="${currentPage != 1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${currentPage - 1}">&#60;&#60;</a></li>
								</c:if>

								<c:if test="${currentPage gt 3}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=1">1</a></li>
									<li class="page-item"></li>
								</c:if>

								<c:if test="${currentPage -2 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${currentPage - 2}">${currentPage - 2}</a></li>
								</c:if>
								<c:if test="${currentPage -1 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${currentPage - 1}">${currentPage - 1}</a></li>
								</c:if>

								<li class="page-item active"><a class="page-link"
									href="/cnpr-homologation/province/list?page=${currentPage}">${currentPage}</a></li>

								<c:if test="${currentPage +1 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${currentPage +1}">${currentPage +1}</a></li>
								</c:if>
								<c:if test="${currentPage +2 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${currentPage +2}">${currentPage +2}</a></li>
								</c:if>

								<c:if test="${currentPage lt totalPages-2}">
									<li class="page-item"></li>
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${totalPages}">${totalPages}</a></li>

								</c:if>

								<c:if test="${currentPage lt totalPages}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${currentPage +1}">&#62;&#62;</a></li>

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
								<th>Designation</th>
								<th>Code court</th>
								<th>Statut</th>
								<th>Actions</th>
							</tr>
							<c:forEach var="province" items="${provinceList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${province.designation}</td>
									<td>${province.code}</td>
									<td><c:choose>
											<c:when test="${province.activeStatus eq false}">Inactif</c:when>
											<c:otherwise>
								Actif
								</c:otherwise>
										</c:choose></td>
									<td><a title="Modifier"
										href="/cnpr-homologation/province/edit/${province.id}"><i
											class="bi bi-pencil"></i></a> 
										<a href="/cnpr-homologation/province/changeStatus/${province.id}"
										title="Changer de statut"><i
											class="bi bi-arrow-left-right text-success"></i></a>		
											<a
										href="/cnpr-homologation/province/${province.id}/districts"
										title="Lister des districts"><i
											class="bi bi-distribute-horizontal text-primary"></i></a>							
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
										href="/cnpr-homologation/province/list?page=${currentPage - 1}">&#60;&#60;</a></li>
								</c:if>

								<c:if test="${currentPage gt 3}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=1">1</a></li>
									<li class="page-item"></li>
								</c:if>

								<c:if test="${currentPage -2 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${currentPage - 2}">${currentPage - 2}</a></li>
								</c:if>
								<c:if test="${currentPage -1 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${currentPage - 1}">${currentPage - 1}</a></li>
								</c:if>

								<li class="page-item active"><a class="page-link"
									href="/cnpr-homologation/province/list?page=${currentPage}">${currentPage}</a></li>

								<c:if test="${currentPage +1 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${currentPage +1}">${currentPage +1}</a></li>
								</c:if>
								<c:if test="${currentPage +2 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${currentPage +2}">${currentPage +2}</a></li>
								</c:if>

								<c:if test="${currentPage lt totalPages-2}">
									<li class="page-item"></li>
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${totalPages}">${totalPages}</a></li>

								</c:if>

								<c:if test="${currentPage lt totalPages}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/province/list?page=${currentPage +1}">&#62;&#62;</a></li>

								</c:if>
							</ul>
						</c:if>
					
				</div>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
