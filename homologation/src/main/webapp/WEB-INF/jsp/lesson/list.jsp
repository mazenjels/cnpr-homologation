<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>E-Learning > Leçons</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau
						de bord</a></li>
				<li class="breadcrumb-item active">Leçons</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		 <div class="row">
			<div class="col-md-2">
				<c:if test="${loggedUserPermission['write_lecon'] eq true}"> <a href="/cnpr-homologation/lesson/new" class="btn btn-primary"><i
					class="bi bi-plus-lg"></i> Nouveau</a></c:if>
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
				<div class="card-header"></div>

				<div class="card-body">
					<form:form>
						<table
							class="table table-bordered table-striped table-hover datatable">
							<tr>
								<th>#</th>
								<th>Intitulé</th>
								<th>Description</th>							
								<th>Auteur</th>
								<th>Statut</th>
								<th>Actions</th>
							</tr>
							<c:forEach var="lecon" items="${lessonList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${lecon.title}</td>
									<td>${lecon.description}</td>
									<td>${lecon.createdBy.username}</td>
									<td><c:choose>
											<c:when test="${lecon.activeStatus eq false}">Inactif</c:when>
											<c:otherwise>
								Actif
								</c:otherwise>
										</c:choose></td>
									 <td>
									 <c:if test="${loggedUserPermission['update_lecon'] eq true}">
									 <a title="Modifier" class="btn btn-sm btn-outline-success"
										href="/cnpr-homologation/lesson/edit/${lecon.id}"><i
											class="bi bi-pencil"></i></a> </c:if>
											<c:if test="${loggedUserPermission['change_status_lecon'] eq true}">
										<a href="/cnpr-homologation/lesson/changeStatus/${lecon.id}" class="btn btn-sm btn-outline-success"
										title="Changer de statut"><i
											class="bi bi-arrow-left-right "></i></a></c:if>
											<c:if test="${loggedUserPermission['write_module_lecon'] eq true}">
											<a href="/cnpr-homologation/lesson/addModules/${lecon.id}" class="btn btn-sm btn-outline-success"
										title="Ajouter des modules"><i
											class="bi bi-plus-lg "></i></a></c:if>									
											</td> 
								</tr>
							</c:forEach>
						</table>
					</form:form>
				</div>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
