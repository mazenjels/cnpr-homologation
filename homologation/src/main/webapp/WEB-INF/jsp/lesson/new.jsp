<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Leçons</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Leçons</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/lesson/list" class="btn btn-primary"><i class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
					<h5 class="card-title">Nouvelle leçon</h5>
						<form:form modelAttribute="lesson" method="post" action="/cnpr-homologation/lesson/save">
							<div class="row">
								<div class="col-md-10">
											<div class="form-floating  mb-3">
												<form:input type="text" class="form-control"
													required="required" id="designation" path="title" placeholder="Intitulé" />
													<form:input type="hidden"  path="createdBy" />
												<label for="designation">Intitulé</label>
												
											</div>
										</div>
							</div>
							
							<div class="row">
								<div class="col-md-12">

									<div class="form-floating">
										<form:textarea class="form-control"   id="floatingTextarea" path="description"></form:textarea>
										<label for="floatingTextarea">Description</label>

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
