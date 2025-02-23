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
					${formateur.cnprAutoEcole.designation}</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

				<div class="row">
			<div class="col-md-4">
				<a href="/cnpr-homologation/autoEcole/view/${autoEcole.id }"
					class="btn btn-primary"><i class="bi bi-arrow-left"></i> Retour</a>

				
			</div>
			<div class="col-md-4" style="text-align:right">
			
			<c:choose>
					<c:when test="${empty autoEcole.codeUnique}">
						<span class="badge bg-info text-dark"><i
							class="bi bi-warning-circle me-1"></i> Pas encore homologuée </span>
					</c:when>
					<c:otherwise>
						
						<h5>
							<span class="badge bg-success"><i
								class="bi bi-info-circle me-1"></i> Homologué </span> <span
								class="badge bg-primary"><i
								class="bi bi-check-circle me-1"></i> ${autoEcole.codeUnique}</span>
						</h5>
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
					<div class="card-header">Formateur</div>
					<div class="card-body">
						<form:form modelAttribute="formateur" method="post"
							action="/cnpr-homologation/autoEcole/addFormateur">
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating mb-3">
										<form:input type="text" class="form-control"
											required="required" id="fullname" path="fullname"
											placeholder="Noms complet du formateur" />

										<label for="designation">Noms complet du formateur</label>

										<form:input type="hidden" class="form-control"
											id="designation" path="cnprAutoEcole" />

									</div>
								</div>
								<fieldset>
									<legend class="card-title">Contacts</legend>
									<div class="row">
										<div class="col-md-6">
											<div class="form-floating  mb-3">
												<form:input type="text" class="form-control"
													required="required" path="phone" id="phone" maxlength="15"
													placeholder="+243" />
												<label for="phone">Numero de telephone (+243)</label>

											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-floating  mb-3">
												<form:input type="text" class="form-control" path="email"
													id="email" placeholder="Adresse email" />
												<label for="email">Adresse email</label>

											</div>
										</div>

									</div>

								</fieldset>
							</div>
							<div class="row p-2">
								<div class="col-md-2">
									<button type="submit" value="Register" class="btn btn-success">Enregistrer</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>

			</div>

			<div class="col-xl-6">
				<div class="card">
				<div class="card-header">
					Nombre de formateur mentioné :  ${formateur.cnprAutoEcole.nbInstructeur}. 
					<c:choose>
						<c:when test="${formateurList.size() eq 0}"> Nombre de formateurs enregistré(s) : 0</c:when>
						<c:otherwise>
						Nombre de formateur(s) enregistré(s) : ${formateurList.size()}
						</c:otherwise>
					</c:choose> 
				</div>
					<div class="card-body">
						<table
							class="table table-bordered table-striped table-hover datatable">
							<tr>
								<th>#</th>
								<th>Noms complet du formateur</th>
								<th>Telephone</th>
								<th>Adresse email</th>
								<!-- <th>Statut</th>		 -->
							</tr>
							<c:forEach var="formateur"
								items="${formateurList}" varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>
										${formateur.fullname}</td>
									<td>${formateur.phone}</td>
									<td>${formateur.email}</td>
									<%-- <td>
									<c:choose>
										<c:when test="${autoEcoleTypePermis.activeStatus eq true}">Actif</c:when>
										<c:otherwise>
											Inactif
										</c:otherwise>
									</c:choose>
									</td> --%>

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
