<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Téléphones</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau
						de bord</a></li>
				<li class="breadcrumb-item active">Téléphones</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/deviceRegistration/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"></h5>
						<form:form modelAttribute="deviceRegistration" method="post"
							action="/cnpr-homologation/deviceRegistration/save">
		
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="deviceId" path="deviceId"
											placeholder="Device ID" />
										<label for="deviceId">Device ID</label>

									</div>
								</div>
								<div class="col-md-4">
										<div class="form-floating mb-3">
											<form:select class="form-select"  path="driver"
												aria-label="State">
												<option value="-1">Selectionner le chauffeur</option>
												 <c:forEach var="driver" items="${driverList}">
													<option value="${driver.id}">${driver.fullname}</option>
												</c:forEach> 
											</form:select>
											<label for="driver">Chauffeur</label>
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
