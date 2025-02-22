<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Module</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Module / Mettre a jour</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/module/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"></h5>
						<form:form modelAttribute="module" method="post"
							action="/cnpr-homologation/module/save">
							<div class="row">
								<div class="col-md-3">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="designation" path="designation"
											placeholder="Designation" />
											<form:input type="hidden" class="form-control" id="designation" path="id"  />

										<label for="designation">Designation</label>

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="shortCode" path="shortCode"
											placeholder="Code court" />

										<label for="shortCode">Code court</label>

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-8">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="description" path="description"
											placeholder="Description" />

										<label for="description">Description</label>

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
