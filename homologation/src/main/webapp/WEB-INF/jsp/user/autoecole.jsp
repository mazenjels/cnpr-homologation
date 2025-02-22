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
				<li class="breadcrumb-item active">${user.nom} ${user.postnom} ${user.prenom}</li>
				<li class="breadcrumb-item active">Auto école ${autoEcole.designation}</li>
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

		<div class="row">
			
			<div class="col-xl-4">

				<div class="card">
					<div
						class="card-body profile-card pt-4 d-flex flex-column align-items-center">

						<img src="<c:url value="/img/cnpr.png"/>" alt="Profile"
							class="rounded-circle">
						<h2>${autoEcole.designation}</h2>
						<table>
							<tr>
								<td><i class="bi bi-geo-alt-fill"></i>
									${autoEcole.adresseNumber}, ${autoEcole.adresseAvenue} -
									${autoEcole.ville.designation}/${autoEcole.commune.designation}
									- ${autoEcole.province.designation}</td>
							</tr>
							<tr>
								<td><i class="bi bi-phone-vibrate"></i> ${autoEcole.phone},
									${autoEcole.phone2}</td>
							</tr>
							<tr>
								<td><i class="bi bi-mailbox"></i> ${autoEcole.email}</td>
							</tr>
							<tr>
								<td><i class="bi bi-file-person-fill"></i>
									${autoEcole.directeurGerant} [Promoteur]</td>
							</tr>
							<tr>
								<td><i class="bi bi-building"></i>
									${autoEcole.capaciteSalle} [Capacité de la salle]</td>
							</tr>
						</table>

					</div>
				</div>

			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
