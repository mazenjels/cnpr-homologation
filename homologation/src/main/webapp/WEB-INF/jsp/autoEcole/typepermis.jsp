<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Auto écoles</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Auto écoles /
					${autoEcoleTypePermis.cnprAutoEcole.designation}</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

				<div class="row">
			<div class="col-md-4">
				<a href="/cnpr-homologation/autoEcole/view/${autoEcole.id }"
					class="btn btn-primary"><i class="bi bi-arrow-left"></i> Retour</a>

				
			</div>
			<div class="col-md-4" style="text-align:right">
			
			<c:choose>
					<c:when test="${empty autoEcole.codeUnique}">
						<span class="badge bg-info text-dark"><i
							class="bi bi-warning-circle me-1"></i> Pas encore homologuée </span>
					</c:when>
					<c:otherwise>
						
						<h5>
							<span class="badge bg-success"><i
								class="bi bi-info-circle me-1"></i> Homologué </span> <span
								class="badge bg-primary"><i
								class="bi bi-check-circle me-1"></i> ${autoEcole.codeUnique}</span>
						</h5>
					</c:otherwise>
				</c:choose>
			
			</div>
		</div>
		<hr />

		<div class=row>

			<div class="col-xl-6">

				<div class="card">
					<div class="card-header">Type de permis</div>
					<div class="card-body">
						<form:form modelAttribute="autoEcoleTypePermis" method="post"
							action="/cnpr-homologation/autoEcole/addTypePermis">
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating mb-3">
										<form:select class="form-select" id="typePermis"
											path="typePermis" aria-label="State">
											<c:forEach var="typePermis" items="${typePermisList}">
												<option value="${typePermis.id}">Brevet de categorie ${typePermis.designation}
													=> ${typePermis.categorie}</option>
											</c:forEach>
										</form:select>
										<form:input type="hidden" class="form-control"
											id="designation" path="cnprAutoEcole" />
										<label for=typePermis>Type de brevet</label>
									</div>
								</div>
							</div>
							<div class="row p-2">
								<div class="col-md-2">
									<button type="submit" value="Register" class="btn btn-success">Enregistrer</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>

			</div>

			<div class="col-xl-6">
				<div class="card">
					<div class="card-body">
						<table
							class="table table-bordered table-striped table-hover datatable">
							<tr>
								<th>#</th>
								<th>Type de brevet</th>
								<th>Categories de vehicule</th>
								<!-- <th>Statut</th>		 -->
							</tr>
							<c:forEach var="autoEcoleTypePermis"
								items="${autoEcoleTypePermisList}" varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>Brevet de categorie ${autoEcoleTypePermis.typePermis.designation}</td>
									<td>${autoEcoleTypePermis.typePermis.categorie}</td>
									<%-- <td>
									<c:choose>
										<c:when test="${autoEcoleTypePermis.activeStatus eq true}">Actif</c:when>
										<c:otherwise>
											Inactif
										</c:otherwise>
									</c:choose>
									</td> --%>

								</tr>
							</c:forEach>
						</table>
					</div>

				</div>
			</div>
		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
