<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Vehicule</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Mouvements du vehicule /
					${vehicule.designation } / ${vehicule.numeroPlaque}</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/vehicule/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-12">
				<div class="card">
					<div class="card-header">
						<div class="row">
							<div class="col-xl-4">
								<c:if test="${totalPages gt 1}">
									<ul class="pagination">
										<c:choose>
											<c:when test="${querySearch ='byDates' }">
<c:if test="${currentPage != 1}">
											<li class="page-item"><a class="page-link"
												href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage - 1}">&#60;&#60;</a></li>
										</c:if>

										<c:if test="${currentPage gt 3}">
											<li class="page-item"><a class="page-link"
												href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=1">1</a></li>
											<li class="page-item"></li>
										</c:if>

										<c:if test="${currentPage -2 gt 0}">
											<li class="page-item"><a class="page-link"
												href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage - 2}">${currentPage - 2}</a></li>
										</c:if>
										<c:if test="${currentPage -1 gt 0}">
											<li class="page-item"><a class="page-link"
												href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage - 1}">${currentPage - 1}</a></li>
										</c:if>

										<li class="page-item active"><a class="page-link"
											href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage}">${currentPage}</a></li>

										<c:if test="${currentPage +1 lt totalPages+1}">
											<li class="page-item"><a class="page-link"
												href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage +1}">${currentPage +1}</a></li>
										</c:if>
										<c:if test="${currentPage +2 lt totalPages+1}">
											<li class="page-item"><a class="page-link"
												href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage +2}">${currentPage +2}</a></li>
										</c:if>

										<c:if test="${currentPage lt totalPages-2}">
											<li class="page-item"></li>
											<li class="page-item"><a class="page-link"
												href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${totalPages}">${totalPages}</a></li>

										</c:if>

										<c:if test="${currentPage lt totalPages}">
											<li class="page-item"><a class="page-link"
												href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage +1}">&#62;&#62;</a></li>

										</c:if>
											</c:when>
											<c:otherwise>
												<c:if test="${currentPage != 1}">
													<li class="page-item"><a class="page-link"
														href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage - 1}">&#60;&#60;</a></li>
												</c:if>

												<c:if test="${currentPage gt 3}">
													<li class="page-item"><a class="page-link"
														href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=1">1</a></li>
													<li class="page-item"></li>
												</c:if>

												<c:if test="${currentPage -2 gt 0}">
													<li class="page-item"><a class="page-link"
														href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage - 2}">${currentPage - 2}</a></li>
												</c:if>
												<c:if test="${currentPage -1 gt 0}">
													<li class="page-item"><a class="page-link"
														href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage - 1}">${currentPage - 1}</a></li>
												</c:if>

												<li class="page-item active"><a class="page-link"
													href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage}">${currentPage}</a></li>

												<c:if test="${currentPage +1 lt totalPages+1}">
													<li class="page-item"><a class="page-link"
														href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage +1}">${currentPage +1}</a></li>
												</c:if>
												<c:if test="${currentPage +2 lt totalPages+1}">
													<li class="page-item"><a class="page-link"
														href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage +2}">${currentPage +2}</a></li>
												</c:if>

												<c:if test="${currentPage lt totalPages-2}">
													<li class="page-item"></li>
													<li class="page-item"><a class="page-link"
														href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${totalPages}">${totalPages}</a></li>

												</c:if>

												<c:if test="${currentPage lt totalPages}">
													<li class="page-item"><a class="page-link"
														href="/cnpr-homologation/vehicule/mouvements/${vehicule.id }?page=${currentPage +1}">&#62;&#62;</a></li>

												</c:if>
											</c:otherwise>
										</c:choose>

									</ul>
								</c:if>
							</div>
							<div class="col-xxl-8">
								<div class="row">
									<div class="col-md-10">
										<form method="POST"
											action="/cnpr-homologation/vehicule/mouvements/${vehicule.id}/searchByDates">
											<table>
												<tr>
													<td>Rechercher par dates</td>
													<td><input type="text" onfocus="(this.type='date')"
														class="form-control input-sm" required="required"
														id="startDate" name="startDate" placeholder="dd-mm-yyyy" /></td>
													<td>
													<td><input type="text" onfocus="(this.type='date')"
														class="form-control input-sm" required="required"
														id="endDate" name="endDate" placeholder="dd-mm-yyyy" /></td>
													<td>
														<button type="submit" title="Rechercher"
															class="btn btn-sm btn-primary">
															<i class="bi bi-search"></i>
														</button>
													</td>


												</tr>
											</table>

										</form>
									</div>
									<div class="col-md-2" style="text-align: right">
										<c:if test="${courseVehiculeList.size() gt 0}">
											<a
												href="/cnpr-homologation/courseVehicule/exportKmVehiculePdf"
												class="btn btn-sm btn-outline-danger"><i
												class="ri ri-file-pdf-line"></i> PDF</a>
										</c:if>
									</div>
								</div>


							</div>
						</div>
					</div>
					<div class="card-body">
						<h5 class="card-title">Kilometrage initial : ${kilometrage.startKm}. Dernier kilométrage enregistré : ${kilometrage.endKm}. Kilomètres parcourus : ${kilometrage.diferenceKm} Km</h5>
						<table
							class="table table-bordered table-sm table-striped table-hover datatable">
							<tr>
								<th>#</th>
								<th>Chauffeur</th>
								<th>Date et heure de départ</th>
								<th>Km/Départ</th>
								<th>Km/Arrivée</th>
								<th>Point de départ</th>
								<th>Destination</th>
								<th>Passager a bord</th>
								<th>Date et heure d'arrivée</th>
								<th>Statut</th>
							</tr>
							<c:forEach var="courseVehicule" items="${courseVehiculeList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${courseVehicule.driver.fullname}</td>
									<td style="text-align: center">${courseVehicule.startDate}
										à ${courseVehicule.startTime}</td>
									<td style="text-align: right">${courseVehicule.startKm}</td>
									<td style="text-align: right"><c:choose>
											<c:when test="${courseVehicule.endKm eq 0}">-</c:when>
											<c:otherwise>${courseVehicule.endKm}</c:otherwise>
										</c:choose></td>
									<td>${courseVehicule.from}</td>
									<td>${courseVehicule.destination}</td>
									<td>${courseVehicule.passengers}</td>
									<td style="text-align: center"><c:choose>
											<c:when
												test="${empty courseVehicule.endTime and courseVehicule.activeStatus eq true}">
												<!-- <span class="card-title text-success">Vehicule en
													activité</span> -->-
											</c:when>
											<c:otherwise>
												<fmt:parseDate pattern="yyyy-MM-dd HH:mm"
													value="${courseVehicule.endTime}" var="parsedDate" />
												<fmt:formatDate value="${parsedDate}"
													pattern="dd-MM-yyyy HH:mm" />

											</c:otherwise>
										</c:choose></td>
									<td><c:choose>
											<c:when
												test="${empty courseVehicule.endTime and courseVehicule.activeStatus eq false}">
												<span class="card-title text-success"></span>En attente</c:when>
											<c:when
												test="${empty courseVehicule.endTime and courseVehicule.activeStatus eq true}">
												<span class="card-title text-success">Vehicule en
													activité</span>
											</c:when>
											<c:otherwise>
												<span class="card-title text-warning">Course terminée</span>
											</c:otherwise>
										</c:choose></td>

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
