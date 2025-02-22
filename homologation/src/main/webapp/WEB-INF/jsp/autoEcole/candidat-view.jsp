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
				<a href="/cnpr-homologation/autoEcole/candidat/${autoEcole.id }" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>

				<c:choose>
					<c:when test="${empty autoEcole.codeUnique}">
						<span class="badge bg-info text-dark"><i
							class="bi bi-warning-circle me-1"></i> Pas encore homologuée </span>
					</c:when>
					<c:otherwise>
						<span class="badge bg-info text-dark"><i
							class="bi bi-info-circle me-1"></i> Auto école déjà homologuée </span>
						<span class="badge bg-success"><i
							class="bi bi-check-circle me-1"></i> ${autoEcole.codeUnique}</span>
					</c:otherwise>
				</c:choose>
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
		<div class="row">

			<div class="col-xl-4">

				<div class="card">
				
					<div
						class="card-body profile-card pt-4 d-flex flex-column align-items-center">

						<img src="<c:url value="/img/avatar.png"/>" alt="Profile"
							class="rounded-circle">
						<h4>${candidat.nom } ${candidat.postnom } ${candidat.prenom }</h4>
						<!-- <h3>Web Designer</h3> -->
						<div class="social-links mt-2">
							<a href="#" class="twitter"><i class="bi bi-phone"></i> ${candidat.phone }</a> <br/><a
								href="#" class="facebook"><i class="bi bi-mailbox"></i>${candidat.email }</a>
						</div>
					</div>
				</div>

			</div>
			<div class="col-xl-4">
				<form:form modelAttribute="candidat" method="post" action="/cnpr-homologation/autoEcole/candidat/${candidat.id }/approveRecyclage">
					<form:hidden path="id"/>
					<button type="submit" value="Approve" class="btn btn-outline-success">Valider le recyclage</button>
				</form:form>
			</div>

		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
