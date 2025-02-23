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
				<li class="breadcrumb-item active">Auto ecoles</li>
				<li class="breadcrumb-item active">Toutes les autos écoles enregistrées. <c:if test="${searchCodeUnique ne null and autoEcoleList.size()==0}">Aucun resultat trouvé pour le coce unique '${searchCodeUnique}'</c:if></li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-3">
			<c:if test="${loggedUserPermission['download_xls_auto_ecole'] eq true}">
				<a href="/cnpr-homologation/autoEcole/downloadXls"
					class="btn btn-success btn-sm"><i
					class="bi bi-file-earmark-arrow-down"></i> Excel</a> </c:if>
					<c:if test="${loggedUserPermission['download_pdf_auto_ecole'] eq true}"><a
					href="/cnpr-homologation/autoEcole/downloadPdf"
					class="btn btn-danger btn-sm"><i
					class="bi bi-file-earmark-arrow-down"></i> PDF</a></c:if>
			</div>
			<div class="col-lg-3">
				<form method="POST" action="/cnpr-homologation/autoEcole/searchCodeUnique">
					<input type="text" name="codeUnique" 
						placeholder="Code unique" title="Rechercher par code unique">
					<button type="submit" title="Rechercher par code unique"
						class="btn btn-sm btn-primary">
						<i class="bi bi-search"></i>
					</button>
				</form>
			</div>
			<div class="col-lg-6">
				<form method="POST" action="/cnpr-homologation/autoEcole/searchByProvinceOrDistrictOrCommune">
					<select id="provinceAutoecole" name="province"
												aria-label="State">
												<option value="-1">Selectionner la province</option>
												 <c:forEach var="province" items="${provinceList}">
													<option value="${province.id}">${province.designation}</option>
												</c:forEach> 
											</select>
						<select id="districtAutoecole" name="district" aria-label="State"> </select>
						<select id="communeAutoecole" name="commune" aria-label="State"> </select>
						
					<button type="submit" title="Lancer la recherche"
						class="btn btn-sm btn-primary">
						<i class="bi bi-search"></i>
					</button>
				</form>
			</div>
		<%--	<div class="col-lg-3">
				<form method="POST" action="/recapByNames">
					<input type="text" name="names"
						placeholder="Nom, postnom ou prenom" title="Rechercher le mot clé">
					<button type="submit" title="Rechercher par code unique"
						class="btn btn-sm btn-primary">
						<i class="bi bi-search"></i>
					</button>
				</form>
			</div> --%>
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
			<div class="card">
			
					
				<div class="card-header">
				<c:if test="${totalPages gt 1}">
							<ul class="pagination">
								<c:if test="${currentPage != 1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${currentPage - 1}">&#60;&#60;</a></li>
								</c:if>

								<c:if test="${currentPage gt 3}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=1">1</a></li>
									<li class="page-item"></li>
								</c:if>

								<c:if test="${currentPage -2 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${currentPage - 2}">${currentPage - 2}</a></li>
								</c:if>
								<c:if test="${currentPage -1 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${currentPage - 1}">${currentPage - 1}</a></li>
								</c:if>

								<li class="page-item active"><a class="page-link"
									href="/cnpr-homologation/autoEcole/list?page=${currentPage}">${currentPage}</a></li>

								<c:if test="${currentPage +1 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${currentPage +1}">${currentPage +1}</a></li>
								</c:if>
								<c:if test="${currentPage +2 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${currentPage +2}">${currentPage +2}</a></li>
								</c:if>

								<c:if test="${currentPage lt totalPages-2}">
									<li class="page-item"></li>
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${totalPages}">${totalPages}</a></li>

								</c:if>

								<c:if test="${currentPage lt totalPages}">
									<li class="page-item"><a class="page-link"
								href="/cnpr-homologation/autoEcole/list?page=${currentPage +1}">&#62;&#62;</a></li>

								</c:if>
							</ul>
						</c:if>
						
				</div>

				<div class="card-body">
					<form:form>
						<table
							class="table table-bordered table-striped table-hover datatable">
							<tr>
								<th>#</th>
								<th>Code unique</th>
								<th>Denomination</th>
								<th>Enregistré le</th>
								<th>Documents attendus</th>
								<!-- <th>Telephone</th>
								<th>Email</th> -->
								<!-- <th>Adresse</th> -->
								<!-- <th>Commune</th>
								<th>District</th>
								<th>Province</th> -->
								<!-- <th>Auteur</th> -->
								<th>Statut</th>
								<th>Actions</th>
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
									<td><fmt:parseDate pattern="yyyy-MM-dd HH:mm"
													value="${autoEcole.createdAt}" var="parsedDate" /> <fmt:formatDate
													value="${parsedDate}" pattern="dd-MM-yyyy HH:mm" /></td>
									<td>${autoEcole.typeDocuments}</td>
									<%-- <td>${autoEcole.phone}</td>
									<td>${autoEcole.email}</td> --%>
									<%-- <td>${autoEcole.adresseNumber},${autoEcole.adresseAvenue}/${autoEcole.commune.designation}, ${autoEcole.commune.district.province.designation}/${autoEcole.commune.district.designation}</td> --%>
									<%-- <td>${autoEcole.commune.designation}</td>
									<td>${autoEcole.commune.district.designation}</td>
									<td>${autoEcole.commune.district.province.designation}</td> --%>
									<%-- <td>${autoEcole.createdBy.username}</td> --%>
									<td><c:choose>
											<c:when test="${autoEcole.activeStatus eq true}">
												<span class="badge rounded-pill bg-success">Homologué</span>
											</c:when>
											<c:otherwise>
												<span class="badge rounded-pill bg-danger">Pas encore
													homologué</span>
											</c:otherwise>
										</c:choose> <c:if test="${empty autoEcole.documents}">
											<span class="badge rounded-pill bg-info">Document en
												attente de soumission</span>
										</c:if></td>
									<td>
									<c:if test="${loggedUserPermission['detail_identification_auto_ecole'] eq true}">
									<a href="/cnpr-homologation/autoEcole/view/${autoEcole.id}" title="Voir le detail" class="btn btn-sm btn-outline-success" ><i class="bi bi-eye"></i></a> 
									</c:if>
									<%-- <c:if test="${loggedUserPermission['update_identification_auto_ecole'] eq true}">
									<a title="Modifier" href="/cnpr-homologation/autoEcole/edit/${autoEcole.id}" class="btn btn-sm btn-outline-primary" ><i class="bi bi-pencil"></i></a> 
									</c:if>
									
									<c:if test="${loggedUserPermission['afficher_document_auto_ecole'] eq true}">
									<a title="Afficher les documents" href="/cnpr-homologation/autoEcole/viewDoc/${autoEcole.id}" class="btn btn-sm btn-outline-warning" ><i class="bi bi-file-earmark-pdf"></i></a>
									</c:if> 
									<c:if test="${loggedUserPermission['detail_type_vehicule'] eq true}">
									<a title="Types de vehicule" href="/cnpr-homologation/autoEcole/vehicleType/${autoEcole.id}" class="btn btn-sm btn-outline-danger" ><i class="ri-car-line"></i></a> 
									</c:if>
									<c:if test="${loggedUserPermission['detail_type_brevet'] eq true}">
									<a title="Types de brevet" href="/cnpr-homologation/autoEcole/typePermis/${autoEcole.id}" class="btn btn-sm btn-outline-secondary" ><i class="bi bi-card-heading"></i></a> 
									</c:if>
									<c:if test="${loggedUserPermission['joindre_document_auto_ecole'] eq true}">
									<a title="Joindre un document" href="/cnpr-homologation/autoEcole/document/${autoEcole.id}" class="btn btn-sm btn-outline-primary" ><i class="bi bi-file-earmark-diff-fill"></i></a> 
									</c:if>
									<c:if test="${loggedUserPermission['joindre_logo_auto_ecole'] eq true}">
									<a title="Charger le logo" href="/cnpr-homologation/autoEcole/photo/${autoEcole.id}" class="btn btn-sm btn-outline-info" ><i class="bi bi-card-image"></i></a>
									</c:if>
									<c:if test="${loggedUserPermission['detail_formateur'] eq true}">
									<a title="Formateurs" href="/cnpr-homologation/autoEcole/formateur/${autoEcole.id}" class="btn btn-sm btn-outline-warning" ><i class="bi bi-person-plus-fill"></i></a>
									</c:if>
									 <c:if test="${loggedUserPermission['write_candidat'] eq true}"> 
									<a title="Candidats" href="/cnpr-homologation/autoEcole/candidat/${autoEcole.id}" class="btn btn-sm btn-outline-success" ><i class="ri ri-file-ppt-2-fill "></i></a>
									 </c:if> 
									
									 <c:if test="${loggedUserPermission['write_utilisateur'] eq true}"> 
									<a title="Utilisateurs" href="/cnpr-homologation/autoEcole/user/${autoEcole.id}" class="btn btn-sm btn-outline-dark" ><i class="ri ri-account-pin-box-fill "></i></a>
									</c:if>  --%>
											</td>
								</tr>
							</c:forEach>
						</table>
					</form:form>
				</div>
				<div class="card-footer">
				<c:if test="${totalPages gt 1}">
							<ul class="pagination">
								<c:if test="${currentPage != 1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${currentPage - 1}">&#60;&#60;</a></li>
								</c:if>

								<c:if test="${currentPage gt 3}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=1">1</a></li>
									<li class="page-item"></li>
								</c:if>

								<c:if test="${currentPage -2 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${currentPage - 2}">${currentPage - 2}</a></li>
								</c:if>
								<c:if test="${currentPage -1 gt 0}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${currentPage - 1}">${currentPage - 1}</a></li>
								</c:if>

								<li class="page-item active"><a class="page-link"
									href="/cnpr-homologation/autoEcole/list?page=${currentPage}">${currentPage}</a></li>

								<c:if test="${currentPage +1 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${currentPage +1}">${currentPage +1}</a></li>
								</c:if>
								<c:if test="${currentPage +2 lt totalPages+1}">
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${currentPage +2}">${currentPage +2}</a></li>
								</c:if>

								<c:if test="${currentPage lt totalPages-2}">
									<li class="page-item"></li>
									<li class="page-item"><a class="page-link"
										href="/cnpr-homologation/autoEcole/list?page=${totalPages}">${totalPages}</a></li>

								</c:if>

								<c:if test="${currentPage lt totalPages}">
									<li class="page-item"><a class="page-link"
								href="/cnpr-homologation/autoEcole/list?page=${currentPage +1}">&#62;&#62;</a></li>

								</c:if>
							</ul>
						</c:if>
						
				</div>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
