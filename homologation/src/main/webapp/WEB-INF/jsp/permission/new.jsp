<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Permission</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Permission</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/permission/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"></h5>
						<form  method="post" action="/cnpr-homologation/permission/save">
							<div class="row">
								<div class="col-md-3">
									<div class="form-floating mb-3">
										<select class="form-select" id="module" name="module"
											aria-label="State">
											<c:forEach var="module" items="${moduleList}">
												<option value="${module.shortCode}">${module.designation}</option>
											</c:forEach>
										</select>
										<label for="module">Module</label>
									</div>
								</div>
								<div class="col-md-6">
								 <!-- <legend>Choose your interests</legend> -->
									<fieldset>
										<div>
											<input type="checkbox" id="detail" name="action" value="detail" /> <label for="detail">Afficher les details</label>
										</div>
										<div>
											<input type="checkbox" id="list" name="action" value="list" /> <label for="list">Afficher tout</label>
										</div>
										<div>
											<input type="checkbox" id="write" name="action" value="write" /> <label for="list">Ecrire</label>
										</div>
										<div>
											<input type="checkbox" id="update" name="action" value="update" /> <label for="update">Mettre a jour</label>
										</div>
										<div>
											<input type="checkbox" id="change_status" name="action" value="change_status" /> <label for="change_status">Activer ou desactiver</label>
										</div>
										<div>
											<input type="checkbox" id="view_pdf" name="action" value="view_pdf" /> <label for="view_pdf">Afficher les documents</label>
										</div>
										<div>
											<input type="checkbox" id="access" name="action" value="access" /> <label for="access">Acceder au module</label>
										</div>
										<div>
											<input type="checkbox" id="filter" name="action" value="filter" /> <label for="filter">Filtrer les recherches</label>
										</div>
										<div>
											<input type="checkbox" id="display_menu" name="action" value="display_menu" /> <label for="display_menu">Afficher le menu</label>
										</div>
										<div>
											<input type="checkbox" id="validate" name="action" value="validate" /> <label for="validate">Valider</label>
										</div>
										<div>
											<input type="checkbox" id="joindre_document" name="action" value="joindre_document" /> <label for="joindre_document">Joindre le document</label>
										</div>
										<div>
											<input type="checkbox" id="joindre_logo" name="action" value="joindre_logo" /> <label for="joindre_logo">Joindre le logo</label>
										</div>
										<div>
											<input type="checkbox" id="download_xls" name="action" value="download_xls" /> <label for="download_xls">Telecharger XLS</label>
										</div>
										<div>
											<input type="checkbox" id="download_pdf" name="action" value="download_pdf" /> <label for="download_pdf">Telecharger PDF</label>
										</div>
										
										
										
									</fieldset>

								</div>
							</div>


							<div class="row p-2">
								<div class="col-md-2">
									<button type="submit" value="Register" class="btn btn-success">Enregistrer</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
