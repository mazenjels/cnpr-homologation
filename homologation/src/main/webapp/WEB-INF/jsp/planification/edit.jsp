
<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Planifications</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Planifications / Mettre a
					jour</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/planification/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"></h5>
						<form:form modelAttribute="planification" method="post"
							action="/cnpr-homologation/planification/save">
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="designation" path="designation"
											placeholder="Designation" />
											<form:input type="hidden" required="required" id="designation" path="id" />
										<label for="designation">Intitulé de la planification</label>

									</div>
								</div>
								<div class="col-md-3">
									<div class="form-floating  mb-3">
										<form:input type="text" onfocus="(this.type='date')"
											min="1970-01-01" class="form-control input-sm"
											required="required" id="dateDebut" path="dateDebut"
											placeholder="dd-mm-yyyy" />
										<label for="dateDebut">Date de début prévue</label>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-floating  mb-3">
										<form:input type="number"  min="1" class="form-control input-sm" required="required" id="duree" path="duree"/>
										<label for="dateDebut">Durée de la mission (en jour)</label>
									</div>
								</div>
							</div>
							<fieldset>
								<legend class="card-title">Zone géographique</legend>
								<div class="row">
								<div class="col-md-3">
										<div class="form-floating mb-3">
											<form:select class="form-select" id="province" path="province"
												aria-label="State">
												<c:forEach var="province" items="${provinceList}">
													<option value="${province.id}" ${planification.province.id == province.id ? 'selected' : ''}>${province.designation}</option>
												</c:forEach>
											</form:select>
											<label for="province">Province</label>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-floating mb-3">
											<form:select class="form-select" id="ville" path="ville"
												aria-label="State">
												<c:forEach var="ville" items="${villeList}">
													<option value="${ville.id}" ${planification.ville.id == ville.id ? 'selected' : ''}>${ville.designation}</option>
												</c:forEach>
											</form:select>
											<label for="ville">Ville</label>
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-floating mb-3">
											<form:select class="form-select" id="commune" path="commune"
												aria-label="State">
												<c:forEach var="commune" items="${communeList}">
													<option value="${commune.id}" ${planification.commune.id == commune.id ? 'selected' : ''}>${commune.designation}</option>
												</c:forEach>
											</form:select>
											<label for="commune">Commune</label>
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
