<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="./templates/header.jsp"%>
<%@ include file="./templates/navigation.jspf"%>
<main id="main" class="main">


		<div class="row">
			<div class="col-lg-16"></div>
		</div>

		<div class="card">
			<div class="card-header">
				<form method="POST" action="/cnpr-homologation/import-timesheet"
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
		<div class="row">
			<div class="col-lg-12">
				 				<c:if test="${uploadedTimeSheetList.size() gt 0}">
					<h1 class="card-title">Les prestations détaillées</h1>

					<tr>
						<td><a href="/cnpr-homologation/valider" class="button btn-sm btn-info">Valider</a>
						</td>
						<td><a href="/cnpr-homologation/grouper" class="button btn-sm btn-success">Afficher
								les prestations</a></td>
						<td><a href="/cnpr-homologation/recap" class="button btn-sm btn-warning">Afficher
								le tableau recap</a></td>
					</tr>
				</c:if> 

				<div class="card">
					<div class="card-header">
					<c:if test="${uploadedTimeSheetList.size() gt 0}">
									<a href="/cnpr-homologation/valider" class="button btn-sm btn-info">Valider</a>
								</c:if>
						<%-- <div class="row">
						<div class="col-lg-3">
								<c:if test="${uploadedTimeSheetList.size() gt 0}">
									<a href="/valider" class="button btn-sm btn-info">Valider</a>
								</c:if>
							</div>
							<div class="col-lg-3">
								<c:if test="${uploadedTimeSheetList.size() gt 0}">
									<form method="POST" action="/searchByNames">
										<input type="text" name="keyword"
											placeholder="Nom, postnom ou prenom"
											title="Rechercher le mot clé">
										<button type="submit"
											title="Rechercher par nom, postnom ou prenom"
											class="btn btn-sm btn-primary">
											<i class="bi bi-search"></i>
										</button>
									</form>
								</c:if>
							</div>
							<div class="col-lg-3">
								<c:if test="${uploadedTimeSheetList.size() gt 0}">
									<form method="POST" action="/searchByDepartement">
										<table>
											<tr>
												<td><select id="departementName" name="departementName"
													aria-label="State">
														<option value="-1">-- Departement --</option>
														<c:forEach var="departement" items="${departementList}">
													<option value="${departement.designation}">${departement.designation}</option>
												</c:forEach>
												</select></td>
												<td>
													<button type="submit" title="Rechercher"
														class="btn btn-sm btn-primary">
														<i class="bi bi-search"></i>
													</button>
												</td>


											</tr>
										</table>

									</form>
								</c:if>
							</div>
							
						</div> --%>

					</div>
					<div class="card-body">
					<h1 class="card-title">Tableau des présences 
						<c:if test="${searchDepartement ne null}"> / Résultats de la recherche > Departement : ${searchDepartement}</c:if>
						<c:if test="${searchNames ne null}"> / Résultats de la recherche > Noms : ${searchNames}</c:if>
						</h1>
						<table class="table table-striped datatable" style="font-size: 13.5px">
							<thead>
								<tr>
									<th>#</th>
									<th>ID</th>
									<th>Noms</th>
									<th>Departement</th>
									<th>Temps</th>
									<th>Statut</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="ts" items="${uploadedTimeSheetList}"
									varStatus="loopCounter">
									<tr>
										<td>${loopCounter.count}</td>
										<td>${ts.uniqueId}</td>
										<td>${ts.name}</td>
										<td>${ts.departement}</td>
										<td>${ts.time}</td>
										<td>${ts.status}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

			</div>

		</div>


</main>
<!-- End #main -->
<%@include file="./templates/footer.jsp"%>