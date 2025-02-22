<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Utilisateurs</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Utilisateur/ Details</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">


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
					<h1>User Details</h1>
					<p>Username: ${username}</p>

					<c:if test="${not empty username}">
						<!-- Add more details here -->
						<p>
							Account Status:
							<c:choose>
								<c:when test="${isAccountNonExpired}"> Expired </c:when>
								<c:otherwise> Active </c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${isAccountLocked}"> Locked </c:when>
								<c:otherwise> Unlocked </c:otherwise>
							</c:choose>
							
						</p>
						
					</c:if>
				</div>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
