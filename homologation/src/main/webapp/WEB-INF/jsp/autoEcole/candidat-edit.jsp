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

				<%-- <c:choose>
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
				</c:choose> --%>
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
		<div class=row>

			<div class="col-xl-6">

				<div class="card">
					<div class="card-header">Mettre a jour les informations du candidat</div>
					<div class="card-body">
						<form:form modelAttribute="candidat" method="post"
							action="/cnpr-homologation/autoEcole/editCandidat">
							<div class="row">
								<div class="col-md-4">
									<div class="form-floating mb-3">
										<form:input type="text" class="form-control"
											required="required" id="nom" path="nom"
											placeholder="Nom du candidat" />

										<label for="nom">Noms du candidat</label>

										<form:input type="hidden" class="form-control" id="cnprAutoEcole" path="cnprAutoEcole" /> 
										<form:input type="hidden" class="form-control" id="createdBy" path="createdBy" /> 

										<form:input type="hidden" class="form-control" path="id" /> 

									</div>
								</div>
								<div class="col-md-4">
									<div class="form-floating mb-3">
										<form:input type="text" class="form-control"
											required="required" id="postnom" path="postnom"
											placeholder="Postnom du candidat" />

										<label for="postnom">Postnom du candidat</label>

									</div>
								</div>

								<div class="col-md-4">
									<div class="form-floating mb-3">
										<form:input type="text" class="form-control"
											required="required" id="prenom" path="prenom"
											placeholder="Prenom du candidat" />

										<label for="prenom">Prenom du candidat</label>

									</div>
								</div>


							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-floating mb-3">
										<form:input type="text" class="form-control"
											required="required" id="lieuNaissance" path="lieuNaissance"
											placeholder="Lieu de naissance" />

										<label for="lieuNaissance">Lieu de naissance</label>

									</div>
								</div>
								<div class="col-md-4">
									<div class="form-floating  mb-3">
										<form:input type="text" onfocus="(this.type='date')"
											min="1970-01-01" class="form-control input-sm"
											required="required" id="dateNaissance" path="dateNaissance"
											placeholder="dd-mm-yyyy" />
										<label for="dateNaissance">Date de naissance</label>
									</div>
								</div>

							</div>

							<fieldset>
								<legend class="card-title">Contacts</legend>
								<div class="row">
									<div class="col-md-6">
										<div class="form-floating  mb-3">
											<form:input type="phone" class="form-control"
												required="required" path="phone" id="phone" maxlength="15"
												placeholder="+243" />
											<label for="phone">Numero de telephone (+243)</label>

										</div>
									</div>
									<div class="col-md-6">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control" path="email"
												id="email" placeholder="Adresse email" />
											<label for="email">Adresse email</label>

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
									<div class="form-floating  mb-3">
										<form:select class="form-select" id="typePieceIdentite"
											path="typePieceIdentite" aria-label="State">
											<option value="-1">Selectionner la piece d'identite</option>
												<option value="CARTE D'ELECTEUR" ${candidat.typePieceIdentite == "CARTE D'ELECTEUR" ? "selected" : ""}>CARTE D'ELECTEUR</option>
												<option value="PASSEPORT" ${candidat.typePieceIdentite == "PASSEPORT" ? "selected" : ""}>PASSEPORT</option>
												<option value="CARTE D'IDENTITE NATIONALE" ${candidat.typePieceIdentite == "CARTE IDENTITE NATIONALE" ? "selected" : ""}>CARTE D'IDENTITE NATIONALE</option>
												<option value="PERMIS DE CONDUIRE" ${candidat.typePieceIdentite == "PERMIS DE CONDUIRE" ? "selected" : ""}>PERMIS DE CONDUIRE</option>
										</form:select>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control" path="numeroPieceIdentite"
												id="numeroPieceIdentite" placeholder="Numero de la pièce d'identité" />
											<label for="numeroPieceIdentite">Numero de la pièce d'identité</label>

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

<%-- 			<div class="col-xl-6">
				<div class="card">
					<div class="card-header">					
						<c:choose>
							<c:when test="${formateurList.size() eq 0}"> Nombre de candidats enregistré(s) : 0</c:when>
							<c:otherwise>
						Nombre de candidat(s) enregistré(s) : ${candidatList.size()}
						</c:otherwise>
						</c:choose>
					</div>
					<div class="card-body">
						<table
							class="table table-bordered table-striped table-hover datatable">
							<tr>
								<th>#</th>
								<th>Noms complet du candidat</th>
								<th>Telephone</th>
								<th>Adresse email</th>
							</tr>
							<c:forEach var="candidat" items="${candidatList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${candidat.nom} ${candidat.postnom} ${candidat.prenom}</td>
									<td>${candidat.phone}</td>
									<td>${candidat.email}</td>
								</tr>
							</c:forEach>
						</table>
					</div>

				</div>
			</div> --%>
		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
