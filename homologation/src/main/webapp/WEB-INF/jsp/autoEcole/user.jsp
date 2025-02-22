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
		<div class=row>

			<div class="col-xl-6">

				<div class="card">
					<div class="card-header">Ajouter un utilisateur</div>
					<div class="card-body">
						<form method="post" action="/cnpr-homologation/autoEcole/addUser">
						
						<div class="row">
								<div class="col-md-4">
									<div class="form-floating  mb-3">
										<input type="text" class="form-control"
											required="required"  name="nom" placeholder="Nom" />
											
											<input type="hidden" class="form-control" required="required"  name="autoEcoleId" value="${autoEcole.id}" />
											<input type="hidden" class="form-control" required="required"  name="loggedUserId" value="${loggedUser.id}" />

										<label for="designation">Nom</label>

									</div>
								</div>

								<div class="col-md-4">
									<div class="form-floating  mb-3">
										<input type="text" class="form-control"
											required="required" id="postnom" name="postnom"
											placeholder="Postnom" />
										<label for="designation">Postnom</label>

									</div>
								</div>
								<div class="col-md-4">
									<div class="form-floating  mb-3">
										<input type="text" class="form-control"
											required="required" id="prenom" name="prenom"
											placeholder="Prenom" />
										<label for="designation">Prenom</label>

									</div>
								</div>

							</div>
							<fieldset>
								<legend>Information de connexion</legend>
								<div class="row">
									<div class="col-md-6">
										<div class="form-floating mb-3">
											<select class="form-select" id="role" name="role" aria-label="State">
												<c:forEach var="role" items="${roleList}">
													<option value="${role.id}">${role.designation}</option>
												</c:forEach>
											</select>
											<label for="role">Profil</label>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-floating mb-3">
											<select class="form-select" id="site" name="site" aria-label="State">
													<option value="auto_ecole">AUTO ECOLE</option>											
											</select>
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
						</form>
					</div>
				</div>

			</div>

			<div class="col-xl-6">
				<div class="card">
					<div class="card-header">					
						<c:choose>
							<c:when test="${autoEcoleUserList.size() eq 0}"> Nombre d'utilisateurs enregistré(s) : 0</c:when>
							<c:otherwise>
						Nombre d'utilisateurs(s) enregistré(s) : ${autoEcoleUserList.size()}
						</c:otherwise>
						</c:choose>
					</div>
					<div class="card-body">
						<table
							class="table table-bordered table-striped table-hover datatable">
							<tr>
								<th>#</th>
								<th>Noms</th>
								<th>Username</th>
								<th>Profil</th>
								<!-- <th>Telephone</th>
								<th>Adresse email</th> -->
							</tr>
							<c:forEach var="autoEcoleUser" items="${autoEcoleUserList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${autoEcoleUser.cnprUser.nom} ${autoEcoleUser.cnprUser.postnom} ${autoEcoleUser.cnprUser.prenom}</td>
									<td>${autoEcoleUser.cnprUser.username}</td>
									<td>${autoEcoleUser.cnprUser.role.designation}</td>
									<%-- <td>${candidat.phone}</td>
									<td>${candidat.email}</td> --%>
								</tr>
							</c:forEach>
						</table>
					</div>

				</div>
			</div>
		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
