<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Communes</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Communes / Mettre a jour</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/commune/list" class="btn btn-primary"><i class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
					<h5 class="card-title"></h5>
						<form:form modelAttribute="commune" method="post" action="/cnpr-homologation/commune/save">
							<div class="row">
								<div class="col-md-10">
											<div class="form-floating  mb-3">
												<form:input type="text" class="form-control"
													required="required" id="designation" path="designation" placeholder="Designation" />
													<form:input type="hidden" class="form-control"
													required="required" id="id" path="id" placeholder="id" />
												<label for="designation">Nom de la commune</label>
												
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
