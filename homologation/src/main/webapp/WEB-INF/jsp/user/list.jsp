<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Utilisateurs</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau
						de bord</a></li>
				<li class="breadcrumb-item active">Utilisateurs</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/user/new" class="btn btn-primary"><i
					class="bi bi-plus-lg"></i> Nouveau</a>
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
									<td colspan="4"></td>
									<td colspan="3" style="text-align:center">Informations de connexion</td>
									<td colspan="2" style="text-align:center">Statut du compte</td>
									<td colspan="3"></td>
							</tr>
							<tr>
								<th>#</th>
								<th>Nom</th>
								<th>Postnom</th>
								<th>Prenom</th>
								<th >Identifiant</th>
								<th>Profil</th>
								<th>Site de travail</th>
								<th >Actif</th>
								<th >Verrouille</th>
								<th>Date de creation</th>
								<th>Statut</th>
								<th>Actions</th>
							</tr>
							<c:forEach var="user" items="${userList}"
								varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td><c:if test="${user.role.designation !='Administrateur'}">${user.nom}</c:if></td>
									<td><c:if test="${user.role.designation !='Administrateur'}">${user.postnom}</c:if></td>
									<td><c:if test="${user.role.designation !='Administrateur'}">${user.prenom}</c:if></td>
									<td>${user.username}</td>
									<td>${user.role.designation}</td>
									<td>${user.site}</td>
									<td>
									<c:choose>
										<c:when test="${user.accountLocked eq false}">Oui</c:when>
										<c:otherwise>
											Non
										</c:otherwise>
									</c:choose>
									</td>
									<td>${user.accountExpired}</td>
									
									<td><fmt:parseDate pattern="yyyy-MM-dd HH:mm"
													value="${user.createdAt}" var="parsedDate" /> <fmt:formatDate
													value="${parsedDate}" pattern="dd-MM-yyyy HH:mm" /></td>
									<td><c:choose>
											<c:when test="${user.activeStatus eq false}">Inactif</c:when>
											<c:otherwise>
								Actif
								</c:otherwise>
										</c:choose></td>
									<td>
									<c:if test="${user.role.designation !='Administrateur'}">
									<a title="Modifier"
										href="/cnpr-homologation/user/edit/${user.id}"><i
											class="bi bi-pencil"></i></a> 
										
										<a
										href="/cnpr-homologation/user/changeStatus/${user.id}"
										title="Activer ou desactiver"><i
											class="bi bi-lock text-success"></i></a>
										</c:if>
										<c:if test="${user.site =='auto_ecole'}">
											<a
										href="/cnpr-homologation/user/autoecole/${user.id}"
										title="Auto ecole"><i
											class="ri ri-building-4-fill"></i></a>
										</c:if>
										
										
																			
											</td>
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
