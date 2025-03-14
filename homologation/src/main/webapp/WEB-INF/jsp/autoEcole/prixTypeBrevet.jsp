<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Auto école / Prix du type de brevet</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Auto écoles</li>
				<li class="breadcrumb-item active">Prix du type de brevet</li>
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
			<div class="col-md-4" style="text-align: right">

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

		<div class="row">
			<div class="col-lg-6">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Prix du type de brevet</h5>
						<form:form modelAttribute="prixTypePermis" method="post"
							action="/cnpr-homologation/autoEcole/prixTypePermis/save">
							<div class="row">
								<div class="col-md-6">
									<div class="form-floating mb-3">
										<form:select class="form-select" path="typePermis"
											aria-label="State">
											<option value="-1">Selectionner</option>
											<c:forEach var="typePermis" items="${typePermisList }">
												<option value="${typePermis.id}">${typePermis.designation}
													- ${typePermis.categorie}</option>
											</c:forEach>
										</form:select>
										<label for="typePermis">Type de brevet</label>
									</div>
									<form:input type="hidden" class="form-control" path="autoEcole" />
									<form:input type="hidden" class="form-control" path="createdBy" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-floating  mb-3">
										<form:input type="number" min="1" class="form-control"
											required="required" id="prix" path="prix" placeholder="Prix" />
										<label for="prix">Prix</label>

									</div>
									<div class="form-floating mb-3">
										<form:select class="form-select" path="devise"
											aria-label="State">
											<option value="-1">Selectionner</option>

											<option value="CDF">CDF</option>
											<option value="USD">USD</option>
										</form:select>
										<label for="devise">Devise</label>
									</div>
								</div>

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
			<div class="col-lg-6">
				<div class="card">
					<div class="card-header"></div>

					<div class="card-body">
						<form:form>
							<table
								class="table table-bordered table-striped table-hover datatable">
								<tr>
									<th>#</th>
									<th>Type de brevet</th>
									<th>Prix</th>
									<th>Devise</th>
									<th>Statut</th>
									 <th>Actions</th> 
								</tr>
								<c:forEach var="prixTypePermis" items="${prixTypePermisAutoEcoleList}"
									varStatus="counter">
									<tr>
										<td>${counter.count}</td>
										<td>CATEGORIE ${prixTypePermis.typePermis.designation} - ${prixTypePermis.typePermis.categorie}</td>
										<td>${prixTypePermis.prix}</td>
										<td>${prixTypePermis.devise}</td>
										<td><c:choose>
												<c:when test="${prixTypePermis.activeStatus eq false}">Inactif</c:when>
												<c:otherwise>
								Actif
								</c:otherwise>
											</c:choose></td>
										<td>
										<%-- <a title="Modifier"
										href="/cnpr-homologation/commune/edit/${commune.id}"><i
											class="bi bi-pencil"></i></a>  --%>
										<a href="/cnpr-homologation/autoEcole/prixTypePermis/${prixTypePermis.id}/changeStatus"
										title="Changer de statut" class="btn btn-outline-primary btn-sm"><i
											class="bi bi-arrow-left-right text-success"></i></a>									
											</td> 
									</tr>
								</c:forEach>
							</table>
						</form:form>
					</div>
				</div>
			</div>
		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
