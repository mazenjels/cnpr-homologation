<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Vehicule</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Types de vehicule / Mettre a
					jour</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/vehicule/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"></h5>
						<form:form modelAttribute="vehicule" method="post"
							action="/cnpr-homologation/vehicule/save">
							<div class="row">
								<div class="col-md-6">
										<div class="form-floating mb-3">
											<form:select class="form-select" id="role" path="typeVehicule" aria-label="State">
											<option value="${typeVehicule.id}">${typeVehicule.designation}</option>
												<c:forEach var="typeVehicule" items="${typeVehiculeList}">
													<option value="${typeVehicule.id}" ${vehicule.typeVehicule.id == typeVehicule.id ? 'selected' : ''}>${typeVehicule.designation}</option>
												</c:forEach>
											</form:select>
											<label for="role">Type de vehicule</label>
										</div>
									</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="designation" path="designation"
											placeholder="Designation" />
											<form:input type="hidden" path="id" />
										<label for="designation">Marque</label>

									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="numeroPlaque" path="numeroPlaque"
											placeholder="Numero de la plaque" />
										<label for="numeroPlaque">Numero de la plaque</label>

									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control" id="numeroChassis" path="numeroChassis"
											placeholder="Numero du chassis" />
										<label for="numeroChassis">Numero du chassis</label>

									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"  id="couleur" path="couleur"
											placeholder="Couleur" />
										<label for="couleur">Couleur</label>

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


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
