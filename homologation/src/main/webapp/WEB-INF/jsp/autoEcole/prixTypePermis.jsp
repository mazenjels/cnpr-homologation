<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Auto école / Prix du type de brevet</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Auto écoles</li>
				<li class="breadcrumb-item active">Prix du type de brevet</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/autoEcole/view/${autoEcole.id }"
					class="btn btn-primary"><i class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Prix du type de brevet</h5>
						<form:form modelAttribute="prixTypePermis" method="post"
							action="/cnpr-homologation/autoEcole/prixTypePermis/save">
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating mb-3">
										<form:select class="form-select" path="typePermis"
											aria-label="State">
											<option value="-1">Selectionner</option>
											<%-- <c:forEach var="autoEcoleTypePermis"
												items="${autoEcoleTypePermisList }">
												<option value="${autoEcoleTypePermis.typePermis.id}">${autoEcoleTypePermis.typePermis.designation}
													- ${autoEcoleTypePermis.typePermis.categorie}</option>
											</c:forEach> --%>
										</form:select>
										<label for="typePermis">Type de brevet</label>
									</div>
									<form:input type="hidden" class="form-control" path="autoEcole"/>
									<form:input type="hidden" class="form-control" path="createdBy"/>
								</div>

								<div class="col-md-3">
									<div class="form-floating  mb-3">
										<form:input type="number" min="1" class="form-control"
											required="required" id="prix" path="prix"
											placeholder="Prix du brevet" />
										<label for="prix">Prix du brevet</label>

									</div>
								</div>
								<fieldset class="row mb-3">
									<legend class="col-form-label col-sm-2 pt-0">Devise</legend>
									<div class="col-sm-10">
										<div class="form-check">
											<form:input class="form-check-input" type="radio"
												path="devise" id="cdf" value="CDF" />
											<label class="form-check-label" for="cdf"> CDF </label>
										</div>
										<div class="form-check">
											<form:input class="form-check-input" type="radio"
												path="devise" id="usd" value="USD" />
										 <label class="form-check-label" for="cdf"> USD </label>
										</div>
									</div>
								</fieldset>
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
