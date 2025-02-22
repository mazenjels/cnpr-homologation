<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Question</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Question / Mettre a jour</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/question/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />
<hr />
		<c:if test="${not empty message}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<i class="bi bi-check-circle me-1"></i> ${message}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<%-- <div class=row>
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"></h5>
						<form:form modelAttribute="question" method="post"
							action="/cnpr-homologation/question/save">
							<fieldset>
									<p>
										<span>${queryResult}</span>
									</p>
								</fieldset>
								<div class="row">
									<div class="col-md-6">
									Q/<span  class="card-title">${question.title}</span>
										
									</div>	
									
									<div class="col-md-6">
									<span class="card-title">
										Bonne réponse : ${question.reponse}
									</span>
		
									</div>							
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control" id="description"
												path="description" placeholder="DeEscription de la question" />
											<label for="title">Description de la question</label>

										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-4">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control" id="videoUrl"
												path="videoUrl" placeholder="Ajouter un lien pour la video" />
											<label for="videoUrl">Ajouter un lien pour la video</label>

										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-4">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control" id="audioUrl"
												path="audioUrl" placeholder="Ajouter un lien pour l'audio" />
											<label for="audiooUrl">Ajouter un lien pour l'audio</label>

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control" id="imageUrl"
												path="imageUrl" placeholder="Ajouter un lien pour l'image" />
											<label for="imageUrl">Ajouter un lien pour l'image</label>

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


		</div> --%>
		<c:if test="${queryResult ne null}">
			<form method="post" action="/cnpr-homologation/question/viewItems/save">
				<div class="row">
					<div class="col-lg-8">
						<div class="card">
							<div class="card-header">${queryResult}</div>
							<div class="card-body">
								
								<div class="row">
									<div class="col-md-12">
										<%-- <div class="form-floating  mb-3">
											<input type="text" class="form-control"
												required="required" id="title" name="title"
												placeholder="Intitulé de la question" />
											<!-- <input type="text" name="id" />-->
											<input type="hidden" name="createdBy"  value="${question.createdBy.id }"/> 
											<label for="title">Intitulé de la question</label>

										</div> --%>
										<table>
											<tr>
												<td>Question / </td>
												<td><span class="card-title">${question.title }</span></td>
											</tr>
											<tr>
												<td>Réponse / </td>
												<td>${question.reponse }</td>
											</tr>
										</table>
										
									</div>


								</div>
								<!-- <div class="row">
									<div class="col-md-6">
										<div class="form-floating  mb-3">
											<input type="text" class="form-control" id="description"
												name="description" placeholder="Description de la question" />
											<label for="title">Description de la question</label>

										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-4">
										<div class="form-floating  mb-3">
											<input type="text" class="form-control" id="videoUrl"
												name="videoUrl" placeholder="Ajouter un lien pour la video" />
											<label for="videoUrl">Ajouter un lien pour la video</label>

										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-4">
										<div class="form-floating  mb-3">
											<input type="text" class="form-control" id="audioUrl"
												name="audioUrl" placeholder="Ajouter un lien pour l'audio" />
											<label for="audiooUrl">Ajouter un lien pour l'audio</label>

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-floating  mb-3">
											<input type="text" class="form-control" id="imageUrl"
												name="imageUrl" placeholder="Ajouter un lien pour l'image" />
											<label for="imageUrl">Ajouter un lien pour l'image</label>

										</div>
									</div>
								</div>
								 -->


							</div>


						</div>
					</div>
					<div class="col-lg-4">
						<div class="card">
							<div class="card-header">Selectionner la bonne assertion</div>
							<div class="card-body">
								
									<div class="row">
										<div class="form-floating mb-3">
											<select class="form-select" id="questionReponseId" name="questionReponseId"
												aria-label="State">
												<option value="-1">Selectionner la bonne assertion</option>
												<c:forEach var="item" items="${questionReponses}">
													<option value="${item.id}">${item.value}</option>
												</c:forEach>
											</select> <label for="module">Choisissez la bonne réponse</label>
											<input type="hidden" name="questionId"  value="${question.id }"/> 
										</div>
									</div>
								
								<div class="row">
									<div class="col-md-6">
										<button type="submit" value="Register" class="btn btn-outline-success"> <i class="bi bi-save2 text-primary"></i> Sauvegarder la réponse</button>
									</div>
									<div class="col-md-6">
									<c:if test="${addnewQuestion ne null }">
									<a href="/cnpr-homologation/question/new" class="btn btn-outline-primary"><i class="bi bi-arrow-right-circle text-primary"></i> Question suivante</a>
									</c:if>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</c:if>


	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
