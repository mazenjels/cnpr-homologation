<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Planification / Auto écoles</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Planification / Auto écoles</li>
				<li class="breadcrumb-item active">Identification</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/planification/open/${planification.id}" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">${planification.designation } - Date prevue : ${planification.dateDebut }</h5>
						<form:form modelAttribute="autoEcole" method="post"
							action="/cnpr-homologation/autoEcole/save">
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="designation" path="designation"
											placeholder="Designation" />
											<form:input type="hidden" class="form-control"  path="planification" />
											<form:input type="hidden" class="form-control"  path="createdBy" />
										<label for="designation">Denomination</label>

									</div>
								</div>
								<%-- <div class="col-md-3">
									<div class="form-floating  mb-3">
										<form:input type="number" min="5" class="form-control"
											required="required" id="capaciteSalle" path="capaciteSalle"
											placeholder="Designation" />
											<form:input type="hidden" class="form-control"  path="capaciteSalle" />
										<label for="capaciteSalle">Capacite de la salle</label>

									</div>
								</div>
								<div class="col-md-3">
									<div class="form-floating  mb-3">
										<form:input type="number" min="1" class="form-control"
											required="required" id="nbInstructeur" path="nbInstructeur"
											placeholder="Nombre d'instructeur" />
											<form:input type="hidden" class="form-control"  path="nbInstructeur" />
										<label for="nbInstructeur">Nombre d'instructeur</label>

									</div>
								</div> --%>
							</div>
							<fieldset>
								<legend class="card-title">Contacts</legend>
								<div class="row">
									<div class="col-md-3">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control"
												required="required" path="phone" id="phone" maxlength="15"
												placeholder="+243" />
											<label for="phone">Numero de telephone (+243)</label>

										</div>
									</div>
									<div class="col-md-3">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control" maxlength="15"
												 path="phone2" id="phone2"
												placeholder="Autre numero de telephone" />
											<label for="phone2">Autre numero de telephone (+243)</label>

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control" 
												 path="email" id="email"
												placeholder="Adresse email" />
											<label for="email">Adresse email</label>

										</div>
									</div>
									
									<div class="col-md-4">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control"
												 path="directeurGerant" id="directeurGerant"
												placeholder="Noms complet du promoteur" />
											<label for="directeurGerant">Nom, postnom et prenom du promoteur</label>

										</div>
									</div>
								</div>
								
							</fieldset>


							<fieldset>
								<legend class="card-title">Adresses</legend>
								<div class="row">
									<div class="col-md-1">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control"
												required="required" path="adresseNumber" id="adresseNumber"
												placeholder="No." />
											<label for="adresseNumber">No</label>

										</div>
									</div>
									<div class="col-md-2">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control"
												required="required" path="adresseAvenue" id="adresseAvenue"
												placeholder="Avenue, rue ou quartier" />
											<label for="adresseAvenue">Avenue, rue ou quartier</label>

										</div>
									</div>
									<div class="col-md-2">
										<div class="form-floating mb-3">
											<form:select class="form-select" id="province" path="province"
												aria-label="State">
												
													<option value="${planification.commune.district.province.id}">${planification.commune.district.province.designation}</option>
												
											</form:select>
											<label for="province">Province</label>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-floating mb-3">
											<form:select class="form-select" id="ville" path="ville"
												aria-label="State">
													<option value="${planification.commune.district.id}">${planification.commune.district.designation}</option>
											</form:select>
											<label for="ville">District</label>
										</div>
									</div>
									
									<div class="col-md-2">
										<div class="form-floating mb-3">
											<form:select class="form-select" id="commune" path="commune"
												aria-label="State">
													<option value="${planification.commune.id}">${planification.commune.designation}</option>
											</form:select>
											<label for="commune">Commune</label>
										</div>
									</div>
									
									<div class="col-md-2">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control"
												required="required" path="ville" id="ville"
												placeholder="Ville" />
											<label for="ville">Ville</label>

										</div>
									</div>
									
									
								</div>

							</fieldset>

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
