<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Utilisateur</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Utilisateur</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/user/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"></h5>
						<form:form modelAttribute="user" method="post"
							action="/cnpr-homologation/user/save">
							<div class="row">
								<div class="col-md-3">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="nom" path="nom" placeholder="Nom" />

										<label for="designation">Nom</label>

									</div>
								</div>

								<div class="col-md-3">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="postnom" path="postnom"
											placeholder="Postnom" />
										<label for="designation">Postnom</label>

									</div>
								</div>
								<div class="col-md-3">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="prenom" path="prenom"
											placeholder="Prenom" />
										<label for="designation">Prenom</label>

									</div>
								</div>

							</div>
							<fieldset>
								<legend>Information de connexion</legend>
								<div class="row">
									<div class="col-md-3">
										<div class="form-floating mb-3">
											<form:select class="form-select" id="role" path="role" aria-label="State">
												<c:forEach var="role" items="${roleList}">
													<option value="${role.id}">${role.designation}</option>
												</c:forEach>
											</form:select>
											<label for="role">Profil</label>
										</div>
									</div>
									
									<div class="col-md-9">
										<div class="form-floating mb-3">
											<form:select class="form-select" id="site" path="site" aria-label="State">
													<option value="auto_ecole">AUTO ECOLE</option>											
													<option value="bureau_cnpr">BUREAU DE LA COMMISSION NATIONALE DE PREVENTION ROUTIERE</option>
													<option value="bureau_hoja">BUREAU HOJA</option>
													<option value="fede">SIEGE</option>
											</form:select>
											<label for="site">Site de travail</label>
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
