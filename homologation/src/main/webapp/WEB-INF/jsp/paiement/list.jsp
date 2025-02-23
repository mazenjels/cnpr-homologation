<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Paiements</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Paiements enregistrés</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/payment/banque"
					class="btn btn-sm btn-outline-primary"><i
					class="bi bi-arrow-down"></i> Paiements de la banque</a>
			</div>
			<div class="col-md-4">
				<fmt:setLocale value="fr_FR" />
				<a href="#" class="btn btn-sm btn-outline-primary"> TOTAL USD :
					<c:choose>
						<c:when test="${summaryUSD eq null}">0</c:when>
						<c:otherwise>
							<fmt:formatNumber value="${summaryUSD}" type="number"
								groupingUsed="true" />
						</c:otherwise>
					</c:choose>
				</a> <a href="#" class="btn btn-sm btn-outline-primary"> TOTAL CDF :
					<c:choose>
						<c:when test="${summaryCDF eq null}">0</c:when>
						<c:otherwise>
							<fmt:formatNumber value="${summaryCDF}" type="number"
								groupingUsed="true" />
						</c:otherwise>
					</c:choose>
				</a>
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
		<div class="row"
			style="overflow-x: hidden; overflow-y: auto; height: 500px;">
			<div class="card">
				<div class="card-header">
					<div class="row">
						<div class="col-md-6">
							<c:if test="${totalPages gt 1}">
								<ul class="pagination">
									<c:if test="${currentPage != 1}">
										<li class="page-item"><a class="page-link"
											href="/cnpr-homologation/payment/list?page=${currentPage - 1}">&#60;&#60;</a></li>
									</c:if>

									<c:if test="${currentPage gt 3}">
										<li class="page-item"><a class="page-link"
											href="/cnpr-homologation/payment/list?page=1">1</a></li>
										<li class="page-item"></li>
									</c:if>

									<c:if test="${currentPage -2 gt 0}">
										<li class="page-item"><a class="page-link"
											href="/cnpr-homologation/payment/list?page=${currentPage - 2}">${currentPage - 2}</a></li>
									</c:if>
									<c:if test="${currentPage -1 gt 0}">
										<li class="page-item"><a class="page-link"
											href="/cnpr-homologation/payment/list?page=${currentPage - 1}">${currentPage - 1}</a></li>
									</c:if>

									<li class="page-item active"><a class="page-link"
										href="/cnpr-homologation/payment/list?page=${currentPage}">${currentPage}</a></li>

									<c:if test="${currentPage +1 lt totalPages+1}">
										<li class="page-item"><a class="page-link"
											href="/cnpr-homologation/payment/list?page=${currentPage +1}">${currentPage +1}</a></li>
									</c:if>
									<c:if test="${currentPage +2 lt totalPages+1}">
										<li class="page-item"><a class="page-link"
											href="/cnpr-homologation/payment/list?page=${currentPage +2}">${currentPage +2}</a></li>
									</c:if>

									<c:if test="${currentPage lt totalPages-2}">
										<li class="page-item"></li>
										<li class="page-item"><a class="page-link"
											href="/cnpr-homologation/payment/list?page=${totalPages}">${totalPages}</a></li>

									</c:if>

									<c:if test="${currentPage lt totalPages}">
										<li class="page-item"><a class="page-link"
											href="/cnpr-homologation/payment/list?page=${currentPage +1}">&#62;&#62;</a></li>

									</c:if>
								</ul>
							</c:if>
						</div>
						<div class="col-md-6" style="text-align: right">
							<a href="#"
								class="btn btn-sm btnModalFilterPayment btn-outline-success"
								data-toggle="modal" data-target=""><i class="bi bi-search"></i> Filtre de recherche</a>
						</div>
					</div>


				</div>

				<div class="card-body">
					<form:form>
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
					</form:form>
				</div>
			</div>


		</div>

<%@ include file="modalFilterPayment.jsp"%>
	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
