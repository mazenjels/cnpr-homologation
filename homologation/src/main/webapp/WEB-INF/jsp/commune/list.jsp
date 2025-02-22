<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Communes</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau
						de bord</a></li>
				<li class="breadcrumb-item active">Communes</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<!-- <div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/commune/new" class="btn btn-primary"><i
					class="bi bi-plus-lg"></i> Nouveau</a>
			</div>
		</div> -->
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
								<th>Nom du commune</th>
								<th>District</th>
								<th>Province</th>
								<th>Statut</th>
								<!-- <th>Actions</th> -->
							</tr>
							<c:forEach var="commune" items="${communeList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${commune.designation}</td>
									<td>${commune.district.designation}</td>
									<td>${commune.district.province.designation}</td>
									<td><c:choose>
											<c:when test="${commune.activeStatus eq false}">Inactif</c:when>
											<c:otherwise>
								Actif
								</c:otherwise>
										</c:choose></td>
									<%-- <td><a title="Modifier"
										href="/cnpr-homologation/commune/edit/${commune.id}"><i
											class="bi bi-pencil"></i></a> 
										<a href="/cnpr-homologation/commune/changeStatus/${commune.id}"
										title="Changer de statut"><i
											class="bi bi-arrow-left-right text-success"></i></a>									
											</td> --%>
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
