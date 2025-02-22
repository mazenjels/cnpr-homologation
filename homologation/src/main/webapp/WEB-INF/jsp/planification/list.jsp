<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Planifications</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Planifications</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<c:if test="${loggedUserPermission['write_planification'] eq true}">
					<a href="/cnpr-homologation/planification/new"
						class="btn btn-primary"><i class="bi bi-plus-lg"></i> Nouvelle</a>
				</c:if>
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
		<div class="row">
			<div class="card">
				<div class="card-header">
				<c:if test="${totalPages gt 1}">
							<ul class="pagination">
								<c:if test="${currentPage != 1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${currentPage - 1}">&#60;&#60;</a></li>
								</c:if>

								<c:if test="${currentPage gt 3}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=1">1</a></li>
									<li class="page-item"></li>
								</c:if>

								<c:if test="${currentPage -2 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${currentPage - 2}">${currentPage - 2}</a></li>
								</c:if>
								<c:if test="${currentPage -1 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${currentPage - 1}">${currentPage - 1}</a></li>
								</c:if>

								<li class="page-item active"><a class="page-link"
									href="/cnpr-homologation/planification/list?page=${currentPage}">${currentPage}</a></li>

								<c:if test="${currentPage +1 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${currentPage +1}">${currentPage +1}</a></li>
								</c:if>
								<c:if test="${currentPage +2 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${currentPage +2}">${currentPage +2}</a></li>
								</c:if>

								<c:if test="${currentPage lt totalPages-2}">
									<li class="page-item"></li>
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${totalPages}">${totalPages}</a></li>

								</c:if>

								<c:if test="${currentPage lt totalPages}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${currentPage +1}">&#62;&#62;</a></li>

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
								<th></th>
								<th>Intitulé de la planification</th>							
								<!-- <th>Durée de la mission</th> -->
								<th>Province</th>
								<th>District</th>
								<th>Commune</th>
								<th>Enregistré le</th>
								<th>Statut</th>
								<th>Crée par</th>
								<th>Actions</th>
							</tr>
							<c:forEach var="planification" items="${planificationList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td><c:choose>
											<c:when test="${planification.currentStatus=='launched'}">
												<i class="bi bi-check-circle-fill text-success"></i>
											</c:when>
											<c:when test="${planification.currentStatus=='closed'}">
												<span class="badge bg-danger"><i class="bi bi-stop-circle"></i></span>
											</c:when>
											<c:otherwise>
												<div class="spinner-grow text-warning" role="status">
													<span class="visually-hidden">Loading...</span>
												</div>
											</c:otherwise>
										</c:choose></td>
									<td>${planification.designation}. <span class="card-title"> Prévue le <fmt:parseDate pattern="yyyy-MM-dd"
													value="${planification.dateDebut}" var="parsedDate" /> <fmt:formatDate
													value="${parsedDate}" pattern="dd-MM-yyyy" /> pour ${planification.duree} jour(s) </span>
									
									<c:choose>
											<c:when test="${planification.currentStatus=='draft'}">
												<span class="badge bg-danger"><i
													class="bi bi-exclamation-octagon me-1"></i> Planification
													en attente</span>
											</c:when>
											<c:when test="${planification.currentStatus=='closed'}">
												<span class="badge bg-danger">Planification arretée</span>
											</c:when>
											<c:otherwise>
												<span class="badge bg-success"><i
													class="bi bi-exclamation-octagon me-1"></i>Planification
													lancée</span>
											</c:otherwise>
										</c:choose>

									</td>
									
									<%-- <td>${planification.duree} jour(s)</td> --%>
									<td>${planification.province.designation}</td>
									<td>${planification.ville.designation}</td>
									<td>${planification.commune.designation}</td>
									<td><fmt:parseDate pattern="yyyy-MM-dd HH:mm"
													value="${planification.createdAt}" var="parsedDate" /> <fmt:formatDate
													value="${parsedDate}" pattern="dd-MM-yyyy HH:mm" /></td>
									
									<td><c:choose>
											<c:when test="${planification.activeStatus eq false}">Inactif</c:when>
											<c:otherwise>
								Actif
								</c:otherwise>
										</c:choose></td>
										<td>${planification.createdBy.username}</td>
									<td><c:if
											test="${loggedUserPermission['update_planification'] eq true}">
											<a title="Modifier" class="btn btn-sm btn-outline-info" 
												href="/cnpr-homologation/planification/edit/${planification.id}"><i
												class="bi bi-pencil"></i></a>
										</c:if> <c:if
											test="${loggedUserPermission['change_status_planification'] eq true}">
											<a class="btn btn-sm btn-outline-primary" 
												href="/cnpr-homologation/planification/changeStatus/${planification.id}"
												title="Changer de statut"><i
												class="bi bi-arrow-left-right text-success"></i></a>
										</c:if> <c:if
											test="${loggedUserPermission['detail_planification'] eq true}">
											<a class="btn btn-sm btn-outline-success" 
												href="/cnpr-homologation/planification/open/${planification.id}"
												title="Voir le detail"><i class="bi bi-eye"></i></a>
										</c:if></td>
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
										href="/cnpr-homologation/planification/list?page=${currentPage - 1}">&#60;&#60;</a></li>
								</c:if>

								<c:if test="${currentPage gt 3}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=1">1</a></li>
									<li class="page-item"></li>
								</c:if>

								<c:if test="${currentPage -2 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${currentPage - 2}">${currentPage - 2}</a></li>
								</c:if>
								<c:if test="${currentPage -1 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${currentPage - 1}">${currentPage - 1}</a></li>
								</c:if>

								<li class="page-item active"><a class="page-link"
									href="/cnpr-homologation/planification/list?page=${currentPage}">${currentPage}</a></li>

								<c:if test="${currentPage +1 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${currentPage +1}">${currentPage +1}</a></li>
								</c:if>
								<c:if test="${currentPage +2 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${currentPage +2}">${currentPage +2}</a></li>
								</c:if>

								<c:if test="${currentPage lt totalPages-2}">
									<li class="page-item"></li>
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${totalPages}">${totalPages}</a></li>

								</c:if>

								<c:if test="${currentPage lt totalPages}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/planification/list?page=${currentPage +1}">&#62;&#62;</a></li>

								</c:if>
							</ul>
						</c:if>
					
				</div>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
