<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Provinces - Districts</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item">Province</li>
				<li class="breadcrumb-item">${province.designation}</li>
				<li class="breadcrumb-item active">Nouveau district</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/province/${province.id}/districts"
					class="btn btn-primary"><i class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>

			<form:form modelAttribute="district" method="post"
				action="/cnpr-homologation/district/save">
				<div class="row">

					<div class="col-md-3">
						<div class="form-floating  mb-3">
							<form:input type="text" class="form-control" required="required"
								id="designation" path="designation" placeholder="Designation" />
							<form:input type="hidden" class="form-control" required="required"
								id="province" path="province" />
							<label for="designation">Designation du district</label>

						</div>
					</div>
					<div class="col-md-4">
						<div class="form-floating  mb-3">
							<button type="submit" value="Register" class="btn btn-success">Enregistrer</button>


						</div>
					</div>

				</div>



			</form:form>

		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
