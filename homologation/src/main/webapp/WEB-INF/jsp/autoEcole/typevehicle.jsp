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
					${autoEcole.designation}</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-4">
				<a href="/cnpr-homologation/autoEcole/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>

				<c:choose>
					<c:when test="${empty autoEcole.codeUnique}">
						<span class="badge bg-info text-dark"><i
							class="bi bi-warning-circle me-1"></i> Pas encore homologué </span>
					</c:when>
					<c:otherwise>
						<span class="badge bg-info text-dark"><i
							class="bi bi-info-circle me-1"></i> Auto ecole déjà homologué </span>
						<span class="badge bg-success"><i
							class="bi bi-check-circle me-1"></i> ${autoEcole.codeUnique}</span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<hr />

		<div class=row>

			<div class="col-xl-6">
				
				<div class="card">
				<div class="card-header">
				Type de vehicule
				</div>
					<div class="card-body">
						<form:form modelAttribute="autoEcoleVehicle" method="post"
							action="/cnpr-homologation/autoEcole/addVehicule">
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating mb-3">
										<form:select class="form-select" id="vehicle"
											path="cnprVehiculeType" aria-label="State">
											<c:forEach var="vehicle" items="${vehicleList}">
												<option value="${vehicle.id}">${vehicle.designation}</option>
											</c:forEach>
										</form:select>
										<label for="vehicle">Type de vehicule</label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-floating mb-3">
										<div class="form-floating  mb-3">
											<form:input type="number" class="form-control"
												required="required" path="nbVehicule" id="nbVehicule"
												placeholder="Nombre de vehicule" />
												<form:input type="hidden"  path="cnprAutoEcole"  />
											<label for="phone">Nombre de vehicule</label>

										</div>
										
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
								<th>Type de vehicule</th>
								<th>Date d'enregistrement</th>
								<th>Statut</th>							
								<th>Actions</th>
							</tr>
							<c:forEach var="autoEcoleVehicle" items="${autoEcoleVehicleList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${autoEcoleVehicle.cnprVehiculeType.designation}</td>
									<td>${autoEcoleVehicle.createdAt}</td>
									<td>
									<c:choose>
										<c:when test="${autoEcoleVehicle.activeStatus eq true}">Actif</c:when>
										<c:otherwise>
											Inactif
										</c:otherwise>
									</c:choose>
									</td>
									<td><a title="Modifier"
										href="/cnpr-homologation/autoEcole/vehicleType/edit/${autoEcoleVehicle.id}"><i
											class="bi bi-pencil"></i></a> 
										<a href="/cnpr-homologation/autoEcole/vehicleType/changeStatus/${autoEcoleVehicle.id}"
										title="Changer de statut"><i
											class="bi bi-arrow-left-right text-success"></i></a>	
											
																				
											</td>
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
