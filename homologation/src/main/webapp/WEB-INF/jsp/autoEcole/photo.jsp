<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Auto écoles</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Auto écoles /
					${autoEcole.designation}</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-4">
				<a href="/cnpr-homologation/autoEcole/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>

				<c:choose>
					<c:when test="${empty autoEcole.codeUnique}">
						<span class="badge bg-info text-dark"><i
							class="bi bi-warning-circle me-1"></i> Pas encore homologué </span>
					</c:when>
					<c:otherwise>
						<span class="badge bg-info text-dark"><i
							class="bi bi-info-circle me-1"></i> Auto ecole déjà homologué </span>
						<span class="badge bg-success"><i
							class="bi bi-check-circle me-1"></i> ${autoEcole.codeUnique}</span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<hr />

		<div class=row>

			<div class="col-xl-6">

				<div class="card">
					<div class="card-header">Charger le logo</div>
					<div class="card-body">
						<form method="POST" action="/cnpr-homologation/autoEcole/addPhoto" enctype="multipart/form-data">
								
								<input type="file" class="form-control input-sm" name="file"
									accept="jpeg,png,jpg" required /><br /> <input
									type="hidden" class="form-control" required="required"
									name="photoEcole" value="doc_${autoEcole.codeUnique}" />
								<input type="submit" value="Charger"
									class="btn-xs btn btn-primary" />
							</form>
					</div>
				</div>

			</div>
		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
