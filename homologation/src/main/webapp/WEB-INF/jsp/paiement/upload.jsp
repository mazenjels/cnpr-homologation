<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Televerser le relévé bancaire des paiements</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Televersement</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		 <div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/payment/banque" class="btn btn-sm btn-outline-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
					
			</div>
			<div class="col-md-6">
			<form method="POST" action="/cnpr-homologation/payment/banque/import-timesheet"
					enctype="multipart/form-data">
					<div class="row">
						Importer le fichier excel
						<div class="col-md-4">
							<div class="form-floating  mb-3">
								<input type="file" id="files" name="files" required />
							</div>
						</div>


						<div class="col-md-2">
							<div class="form-floating  mb-3">
								<button type="submit" name="action" value="importerXls"
									class="btn btn-success btn-sm">
									<i class="bi bi-arrow-bar-up"></i> Importer
								</button>
							</div>
						</div>
					</div>


				</form>
			</div>
		</div> 
		<hr />
		<c:if test="${not empty message}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<i class="bi bi-check-circle me-1"></i> ${message}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<div class="row"
			style="overflow-x: hidden; overflow-y: auto; height: 500px;">
			<div class="card">
				<div class="card-header">
				<c:if test="${uploadedBanquePayementList.size() gt 0}">
				<a href="/cnpr-homologation/payment/banque/validateUpload" class="button btn-sm btn-success">Valider l'importation</a>
				</c:if>
				</div>

				<div class="card-body">
					<form:form>
						<table
							class="table table-bordered table-striped table-hover datatable">
							<tr>
								<th>#</th>
								<th>Reférences</th>
								<th>Montant</th>
								<th>Devise</th>
							</tr>
							<c:forEach var="paiement" items="${uploadedBanquePayementList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${paiement.reference}</td>
									<td>${paiement.montant}</td>
									<td>${paiement.devise}</td>
									
								</tr>
							</c:forEach>
						</table>
					</form:form>
				</div>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
