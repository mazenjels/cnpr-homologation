<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Types de vehicule</h1>
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
				<a href="/cnpr-homologation/vehicleType/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"></h5>
						<form:form modelAttribute="vehicle" method="post"
							action="/cnpr-homologation/vehicleType/save">
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="designation" path="designation"
											placeholder="Designation" />
										<label for="designation">Designation</label>
										<form:input type="hidden" path="id" />

									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-3">
									<div class="form-floating  mb-3">
										<form:input type="number" min="1" class="form-control"
											required="required" id="nbTonne" path="nbTonne"
											placeholder="Nombre de tonne" />
										<label for="designation">Nombre de tonne</label>

									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-floating mb-3">
									<form:select class="form-select" id="categorie"
										path="categorie" aria-label="State">
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
										<option value="E">E</option>
									</form:select>
									<label for="categorie">Categorie de permis</label>
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
