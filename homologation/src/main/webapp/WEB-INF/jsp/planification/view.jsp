<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Planification</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Planification / Détail</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-4">
				<a href="/cnpr-homologation/planification/list"
					class="btn btn-primary btn-sm"><i class="bi bi-arrow-left"></i>
					Retour</a>
				<c:if test="${planification.currentStatus =='draft'}">
					<a
						href="/cnpr-homologation/planification/lancer/${planification.id}"
						class="btn btn-success btn-sm"><i class="bi bi-check"></i>
						Lancer la planification</a>

				</c:if>
				<c:if test="${planification.currentStatus =='launched'}">
					<a
						href="/cnpr-homologation/planification/fermer/${planification.id}"
						class="btn btn-danger btn-sm"><i class="bi bi-stop-circle"></i>
						Arreter la planification</a>

				</c:if>
				<c:if test="${loggedUserPermission['write_identification_auto_ecole'] eq true}">
					<c:if test="${planification.currentStatus =='launched' }">
						<a href="/cnpr-homologation/autoEcole/new"
							class="btn btn-primary btn-sm"><i class="bi bi-plus-lg"></i>
							Ajouter une auto école </a>
					</c:if>
				</c:if>



			</div>
		</div>
		<hr />

		<div class="row">

			<div class="col-xl-4">

				<div class="card">
					<div class="card-header">
						<div class="card-title">
							<h5>Planification du 
							<fmt:parseDate pattern="yyyy-MM-dd HH:mm" value="${planification.createdAt}" var="parsedDate" /> <fmt:formatDate
													value="${parsedDate}" pattern="dd-MM-yyyy HH:mm" />
							</h5>
						</div>


						<table class="table">
							<tr>
								<td><i class="bi bi-check"></i></td>
								<td>${planification.designation}</td>
							</tr>
							<tr>
								<td><i class="ri-calendar-2-fill"></i></td>
								<td>
								Prévue le <fmt:parseDate pattern="yyyy-MM-dd" value="${planification.dateDebut}" var="parsedDate" /> <fmt:formatDate
													value="${parsedDate}" pattern="dd-MM-yyyy" />, Dureé : ${planification.duree} jour(s)</td>
							</tr>
							<tr>
								<td><i class="bi bi-geo-alt-fill"></i></td>
								<td>Ville/District de ${planification.ville.designation}, Commune de
									${planification.commune.designation}, Province de
									${planification.province.designation}</td>
							</tr>
							<tr>
								<td></td>
								<td><c:choose>
										<c:when test="${planification.currentStatus=='draft'}">
											<span class="badge bg-warning"><i
												class="bi bi-exclamation-octagon me-1"></i> En attente de
												validation</span>
										</c:when>
										<c:when test="${planification.currentStatus=='closed'}">
											<span class="badge bg-danger"><i
												class="bi bi-exclamation-octagon me-1"></i> Fermée</span>
										</c:when>
										<c:otherwise>
											<span class="badge bg-success"><i
												class="bi bi-check me-1"></i> Validée</span>
										</c:otherwise>
									</c:choose></td>
							</tr>


						</table>

					</div>
				</div>

			</div>
			<div class="col-xl-8">
				<c:if test="${planification.currentStatus !='draft'}">
					<div class="row"
						style="overflow-x: hidden; overflow-y: auto; height: 500px;">
						<div class="card">
							<div class="card-header">Liste des autos écoles enregistrées pour cette tache</div>
							<div class="card-body">
							<table
											class="table table-bordered table-striped table-hover table-sm">
											<tr>
												<th>#</th>
												<th>Code unique</th>
												<th>Denomination</th>
												<th>Date</th>
												<!-- <th>Telephone</th>
												<th>Email</th> -->
												<th>Adresse</th>
												<th>Statut</th>
												<!-- <th>Actions</th> -->
											</tr>
											<c:forEach var="autoEcole" items="${autoEcoleList}"
												varStatus="counter">
												<tr>
													<td>${counter.count}</td>
													<td><c:choose>
															<c:when test="${empty autoEcole.codeUnique}"> En attente</c:when>
															<c:otherwise>${autoEcole.codeUnique}</c:otherwise>
														</c:choose></td>
													<td>${autoEcole.designation}</td>
													<td>
													<fmt:parseDate pattern="yyyy-MM-dd" value="${autoEcole.createdAt}" var="parsedDate" /> <fmt:formatDate
													value="${parsedDate}" pattern="dd-MM-yyyy" />
													</td>
													<%-- <td>Tel.: ${autoEcole.phone}, autre:
														${autoEcole.phone2}</td>
													<td>${autoEcole.email}</td> --%>
													<td>${autoEcole.adresseNumber},${autoEcole.adresseAvenue},
														${autoEcole.ville.designation}/${autoEcole.commune.designation}</td>
													<td><c:choose>
															<c:when test="${autoEcole.activeStatus eq true}">
																<span class="badge rounded-pill bg-success">Homologué</span>
															</c:when>
															<c:otherwise>
																<span class="badge rounded-pill bg-danger">Pas
																	encore homologué</span>
															</c:otherwise>
														</c:choose> <c:if test="${empty autoEcole.documents}">
															<span class="badge rounded-pill bg-info">Document
																en attente de soumission</span>
														</c:if></td>
													<%-- <td><a title="Modifier"
														href="/cnpr-homologation/autoEcole/edit/${autoEcole.id}"><i
															class="bi bi-pencil text-warning"></i></a> <a
														href="/cnpr-homologation/autoEcole/view/${autoEcole.id}"
														title="Voir le detail"><i class="bi bi-eye"></i></a> <a
														title="Afficher les documents"
														href="/cnpr-homologation/autoEcole/viewDoc/${autoEcole.id}"><i
															class="bi bi-file-earmark-pdf text-info"></i></a> <a
														title="Types de vehicule"
														href="/cnpr-homologation/autoEcole/vehicleType/${autoEcole.id}"><i
															class="ri-car-line text-black"></i></a> <a
														title="Types de permis"
														href="/cnpr-homologation/autoEcole/typePermis/${autoEcole.id}"><i
															class="bi bi-card-heading"></i></a> <a
														title="Joindre un document"
														href="/cnpr-homologation/autoEcole/document/${autoEcole.id}"><i
															class="bi bi-file-earmark-diff-fill text-danger"></i></a> <a
														title="Charger le logo"
														href="/cnpr-homologation/autoEcole/photo/${autoEcole.id}"><i
															class="bi bi-card-image"></i></a></td> --%>
												</tr>
											</c:forEach>
										</table>
							</div>

						</div>

					</div>

				</c:if>
			</div>
			</div>
	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
