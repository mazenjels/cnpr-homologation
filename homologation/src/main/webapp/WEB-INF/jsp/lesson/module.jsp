<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>E-Learning > Leçons > Modules</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Leçons : ${lessonModule.lesson.title }</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/lesson/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-8">
				<div class="card">
					<div class="card-header">
						<h5 class="card-title">Leçon: ${lessonModule.lesson.title }</h5>
						Description : ${lessonModule.lesson.description }
					</div>
					<div class="card-body">
						

						<form:form modelAttribute="lessonModule" method="post"
							action="/cnpr-homologation/lesson/saveModule">
							<div class="row">
								<div class="col-md-10">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="title" path="title"
											placeholder="Intitulé du module" />
										<form:input type="hidden" path="lesson" />
										<form:input type="hidden" path="createdBy" />
										<label for="designation">Intitulé du module</label>

									</div>
								</div>
							</div>

							<div class="row p-2">
								<div class="col-md-2">
									<button type="submit" value="Register" class="btn btn-success">Ajouter</button>
								</div>
							</div>
						</form:form>
					</div>

				</div>
			</div>
			<div class="col-lg-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Les modules contenus dans cette leçon</h5>

						<!-- List group with custom content -->
						<ol class="list-group list-group-numbered">
						
						<c:forEach var="lessonModule" items="${lessonModuleList}"
								varStatus="counter">
							<li
								class="list-group-item d-flex justify-content-between align-items-start">
								<div class="ms-2 me-auto">
									<div class="fw-bold">${lessonModule.title }</div>
									<c:choose>
										<c:when test="${lessonModule.activeStatus eq true}"> Actif</c:when>
										<c:otherwise>Desactivé</c:otherwise>
									</c:choose>
								</div> <!-- <span class="badge bg-primary rounded-pill"><i class="bi bi-plus-lg"></i></span> -->
								<c:if test="${loggedUserPermission['change_status_module_lecon'] eq true}">
								<a href="/cnpr-homologation/lesson/modules/${lessonModule.id}/changeStatus" class="btn btn-sm btn-outline-success" title="Activer ou desactiver"><i class="bi bi-arrow-left-right"></i></a>
								</c:if>
								<c:if test="${loggedUserPermission['write_module_contenu'] eq true}">
									<c:if test="${lessonModule.activeStatus eq true}">
									<a href="/cnpr-homologation/lesson/modules/${lessonModule.id}" class="btn btn-sm btn-outline-success" title="Ajouter du contenu"><i class="bi bi-plus-lg"></i></a>
								</c:if>
								</c:if>
							</li>
							
							</c:forEach>
							<!-- <li
								class="list-group-item d-flex justify-content-between align-items-start">
								<div class="ms-2 me-auto">
									<div class="fw-bold">Subheading</div>
									Cras justo odio
								</div> <span class="badge bg-primary rounded-pill">14</span>
							</li>
							<li
								class="list-group-item d-flex justify-content-between align-items-start">
								<div class="ms-2 me-auto">
									<div class="fw-bold">Subheading</div>
									Cras justo odio
								</div> <span class="badge bg-primary rounded-pill">14</span>
							</li> -->
						</ol>
						<!-- End with custom content -->

					</div>
				</div>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
