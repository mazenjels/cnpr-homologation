<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Auto écoles</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Auto écoles / Détail</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-4">
				<c:if test="${autoEcoleUser eq null}">
					<a href="/cnpr-homologation/autoEcole/list"
						class="btn btn-primary btn-sm"><i class="bi bi-arrow-left"></i>
						Retour</a>
				</c:if>

				<c:choose>
					<c:when test="${empty autoEcole.codeUnique}">
						<c:if
							test="${autoEcoleVehicleList.size() gt 0 and autoEcoleTypePermisList.size() gt 0 and paymentList.size() gt 0 and formateurList.size() gt 0}">
							<c:if
								test="${loggedUserPermission['validate_homologation'] eq true}">
								<a
									href="/cnpr-homologation/autoEcole/changeStatus/${autoEcole.id}"
									class="btn btn-success btn-sm"><i class="bi bi-check"></i>
									Homologuer</a>
							</c:if>
						</c:if>
						<c:if test="${loggedUserPermission['write_paiement'] eq true}">
							<a href="#"
								class="btn btn-sm btnModalRegisterPayment btn-success"
								data-toggle="modal" data-target=""><i
								class="bi bi-pencil-square"></i> Enregistrer le paiement</a>
						</c:if>
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
							<c:if test="${autoEcole.headquarter eq true}">
								<tr>
									<td colspan="2">Siège social :
										${autoEcole.adresseHeadquarter}</td>
								</tr>
							</c:if>
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
							<tr>
								<td colspan="2" style="background-color: grey"></td>
							</tr>
							<tr>
								<td colspan="2">Types de documents attendus: <span
									class="card-title">${autoEcole.typeDocuments}</span></td>
							</tr>
							<tr>
								<td colspan="2" style="background-color: grey"></td>
							</tr>
							<tr>
								<td><i class="bi bi-file-earmark-pdf text-danger"></i></i> Type
									de documents signalés : <c:choose>
										<c:when test="${autoEcoleDocumentList== '[]'}">Aucun</c:when>
										<c:otherwise>${autoEcoleDocumentList}</c:otherwise>
									</c:choose></td>
							</tr>
						</table>

					</div>
				</div>

			</div>

			<div class="col-xl-8">
				<div class="card">
					<div class="card-body pt-3">
						<ul class="nav nav-tabs nav-tabs-bordered">

							<li class="nav-item">
								<button class="nav-link active" data-bs-toggle="tab"
									data-bs-target="#autoecole-vehicle">Types de vehicule</button>
							</li>

							<li class="nav-item">
								<button class="nav-link" data-bs-toggle="tab"
									data-bs-target="#autoecole-type-permis">Types de
									brevet octroyé</button>
							</li>
							<li class="nav-item">
								<button class="nav-link" data-bs-toggle="tab"
									data-bs-target="#autoecole-paiements">Paiements</button>
							</li>
							<li class="nav-item">
								<button class="nav-link" data-bs-toggle="tab"
									data-bs-target="#autoecole-formateurs">Formateurs</button>
							</li>
							<li class="nav-item">
								<button class="nav-link" data-bs-toggle="tab"
									data-bs-target="#autoecole-candidats">Candidats</button>
							</li>
							<li class="nav-item">
								<button class="nav-link" data-bs-toggle="tab"
									data-bs-target="#autoecole-users">Utilisateurs</button>
							</li>
							<li class="nav-item">
								<button class="nav-link" data-bs-toggle="tab"
									data-bs-target="#autoecole-map">Map</button>
							</li>

						</ul>
						<div class="tab-content pt-2">
							<div class="tab-pane fade show active autoecole-vehicle"
								id="autoecole-vehicle">
								<h5 class="card-title">Types de vehicules</h5>

								<table
									class="table table-bordered table-striped table-hover datatable">
									<tr>
										<th>#</th>
										<th>Designation</th>
										<th>Nombre de tonne</th>
										<th>Categorie</th>
										<th>Statut</th>
									</tr>
									<c:forEach var="autoEcoleVehicleType"
										items="${autoEcoleVehicleList}" varStatus="counter">
										<tr>
											<td>${counter.count}</td>
											<td>${autoEcoleVehicleType.cnprVehiculeType.designation}</td>
											<td>${autoEcoleVehicleType.cnprVehiculeType.nbTonne}</td>
											<td>${autoEcoleVehicleType.cnprVehiculeType.categorie}</td>
											<td><c:choose>
													<c:when test="${vehicle.activeStatus eq false}">Inactif</c:when>
													<c:otherwise>
								Actif
								</c:otherwise>
												</c:choose></td>

										</tr>
									</c:forEach>
								</table>
							</div>
							<div class="tab-pane fade show  autoecole-type-permis"
								id="autoecole-type-permis">
								<h5 class="card-title">Types de brevet octroyé</h5>

								<table
									class="table table-bordered table-striped table-hover datatable">
									<tr>
										<th>#</th>
										<th>Type de permis</th>
										<th>Categories de vehicule</th>
									</tr>
									<c:forEach var="autoEcoleTypePermis"
										items="${autoEcoleTypePermisList}" varStatus="counter">
										<tr>
											<td>${counter.count}</td>
											<td>${autoEcoleTypePermis.typePermis.designation}</td>
											<td>${autoEcoleTypePermis.typePermis.categorie}</td>


										</tr>
									</c:forEach>
								</table>
							</div>
							<div class="tab-pane fade show  autoecole-paiements"
								id="autoecole-paiements">
								<fmt:setLocale value="fr_FR"/>
								<h5 class="card-title">Paiements. <button type="button" class="btn btn-outline-primary">USD <c:choose><c:when test="${summaryUSD eq null}">0</c:when><c:otherwise><fmt:formatNumber value="${summaryUSD}" type="number" groupingUsed="true"/></c:otherwise></c:choose></button>  <button type="button" class="btn btn-outline-success">CDF <fmt:formatNumber value="${summaryCDF}" type="number" groupingUsed="true"/></button></h5>
								<table
									class="table table-bordered table-striped table-hover datatable">
									<tr>
										<th>#</th>
										<th>Reference</th>
										<th>ID transaction</th>
										<th>Banque</th>
										<th>Agence</th>
										<th>Code du candidat</th>
										<th>Montant</th>
										<th>Motif</th>
										<th>Devise</th>
										<th>Date d'enregistrement</th>
										<th>Statut de paiement</th>
										<th>Statut</th>
									</tr>
									<c:forEach var="payment" items="${paymentList}"
										varStatus="counter">
										<tr>
											<td>${counter.count}</td>
											<td>${payment.reference}</td>
											<td>${payment.transactionId}</td>
											<td>${payment.bank}</td>
											<td>${payment.bankBranch}</td>
											<td>${payment.candidatCode}</td>
											<td>${payment.amount}</td>
											<td>${payment.motif}</td>
											<td>${payment.currencyCode}</td>
											<td><fmt:parseDate pattern="yyyy-MM-dd HH:mm"
													value="${payment.createdAt}" var="parsedDate" /> <fmt:formatDate
													value="${parsedDate}" pattern="dd-MM-yyyy HH:mm" /></td>
													<td>${payment.paymentStatus}</td>
											<td><c:choose>
													<c:when test="${payment.activeStatus eq true}">
														<span class="badge rounded-pill bg-success">Verifié</span>
													</c:when>
													<c:otherwise>
														<span class="badge rounded-pill bg-danger">Non
															verifié</span>
													</c:otherwise>
												</c:choose></td>

										</tr>
									</c:forEach>
								</table>
							</div>
							<div class="tab-pane fade show  autoecole-formateurs"
								id="autoecole-formateurs">
								<h5 class="card-title">Liste des formateurs</h5>
								<table
									class="table table-bordered table-striped table-hover datatable">
									<tr>
										<th>#</th>
										<th>Noms complet du formateur</th>
										<th>Telephone</th>
										<th>Adresse email</th>
										<!-- <th>Statut</th>		 -->
									</tr>
									<c:forEach var="formateur" items="${formateurList}"
										varStatus="counter">
										<tr>
											<td>${counter.count}</td>
											<td>${formateur.fullname}</td>
											<td>${formateur.phone}</td>
											<td>${formateur.email}</td>

										</tr>
									</c:forEach>
								</table>
							</div>

							<div class="tab-pane fade show  autoecole-candidats"
								id="autoecole-candidats">
								<h5 class="card-title">Liste des candidats</h5>
								<table
									class="table table-bordered table-striped table-hover datatable">
									<tr>
										<th>#</th>
										<th>Noms complet du candidat</th>
										<th>Telephone</th>
										<th>Adresse email</th>
										<th>Code unique</th>
										<th>Etat du recyclage</th>
									</tr>
									<c:forEach var="candidat" items="${candidatList}"
										varStatus="counter">
										<tr>
											<td>${counter.count}</td>
											<td>${candidat.nom}${candidat.postnom}${candidat.prenom}</td>
											<td>${candidat.phone}</td>
											<td>${candidat.email}</td>
											<td>${candidat.codeUnique}</td>
											<td><c:choose>
													<c:when test="${candidat.recyclageValide eq true}">
												Validé
											</c:when>
													<c:otherwise>
												En attente de validation
											</c:otherwise>
												</c:choose></td>

										</tr>
									</c:forEach>
								</table>
							</div>

							<div class="tab-pane fade show  autoecole-users"
								id="autoecole-users">
								<h5 class="card-title">Liste des utilisateurs</h5>
								<table
									class="table table-bordered table-striped table-hover datatable">
									<tr>
										<th>#</th>
										<th>Noms complet de l'utilisateur</th>
										<th>Identifiant</th>
										<!-- <th>Adresse email</th> -->
										<!-- <th>Statut</th>		 -->
									</tr>
									<c:forEach var="autoEcoleUser" items="${autoEcoleUserList}"
										varStatus="counter">
										<tr>
											<td>${counter.count}</td>
											<td>${autoEcoleUser.cnprUser.nom}
												${autoEcoleUser.cnprUser.postnom}
												${autoEcoleUser.cnprUser.prenom}</td>
											<th>${autoEcoleUser.cnprUser.username}</th>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<%@ include file="modalRegisterPayment.jsp"%>
	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
