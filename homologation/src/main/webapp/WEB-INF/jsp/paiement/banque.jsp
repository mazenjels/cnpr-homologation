<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Relévé bancaire des paiements</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Relévé bancaire des paiements</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		 <div class="row">
			<div class="col-md-4">
				<a href="/cnpr-homologation/payment/list" class="btn btn-sm btn-outline-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
					<a href="/cnpr-homologation/payment/banque/upload" class="btn btn-sm btn-outline-primary"><i
					class="bi bi-arrow-up"></i> Upload</a>
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
				<div class="card-header"></div>

				<div class="card-body">
					<form:form>
						<table
							class="table table-bordered table-striped table-hover datatable">
							<tr>
								<th>#</th>
								<th>Reférences</th>
								<th>Montant</th>
								<th>Devise</th>
								<th>Date d'enregistrement</th>
							</tr>
							<c:forEach var="paiement" items="${bankPaymentList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${paiement.reference}</td>
									<td>${paiement.montant}</td>
									<td>${paiement.devise}</td>
									<td><fmt:parseDate pattern="yyyy-MM-dd HH:mm"
											value="${paiement.createdAt}" var="parsedDate" /> <fmt:formatDate
											value="${parsedDate}" pattern="dd-MM-yyyy HH:mm" /></td>
											<td>${paiement.autoEcole.designation}</td>
									

								</tr>
							</c:forEach>
						</table>
					</form:form>
				</div>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
