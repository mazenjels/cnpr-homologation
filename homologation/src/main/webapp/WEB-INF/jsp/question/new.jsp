<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Question</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Question / Nouvelle question</li>
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
		<c:if test="${not empty message}">
			<div class="alert alert-success alert-dismissible fade show">
				<i class="bi bi-check-circle me-1"></i> ${message}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-header">
						<form method="POST"
							action="/cnpr-homologation/question/searchByLessonAndModule">
							<div class="row">
								<div class="col-md-4">
									<select class="form-control" id="lessonQuestion" name="lesson"
										aria-label="State">
										<option value="-1">Selectionner la leçon</option>
										<c:forEach var="lesson" items="${lessonsList}">
											<option value="${lesson.id}">${lesson.title}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-4">
									<select class="form-control" id="moduleQuestion"
										name="moduleQuestionId" aria-label="State">
									</select>

								</div>
								<div class="col-md-4">

									<select class="form-control" id="typePermis" name="typePermis"
										aria-label="State">
										<option value="-1">Type de brevet concerné</option>
										<c:forEach var="typePermis" items="${typePermisList}">
											<option value="${typePermis.id}">${typePermis.designation}</option>
										</c:forEach>
									</select>

									<button type="submit" title="Confirmer"
										class="btn btn-sm btn-primary" id="btnConfirmerLeconModule">
										<!-- <i class="bi bi-search"></i> -->
										OK
									</button>
								</div>

							</div>

						</form>
					</div>

				</div>
			</div>


		</div>
		<c:if test="${queryResult ne null}">
			<form method="post"
				action="/cnpr-homologation/question/save">
				<div class="row">
					<div class="col-lg-8">
						<div class="card">

							<div class="card-body">
								<h5 class="card-title"></h5>

								<fieldset>
									<p>
										<span class="card-title">${queryResult}</span>
									</p>
								</fieldset>
								<div class="row">
									<div class="col-md-12">
										<div class="form-floating  mb-3">
											<input type="text" class="form-control"
												required="required" id="title" name="title"
												placeholder="Intitulé de la question" />
											<!-- <input type="text" name="id" />-->
											<input type="hidden" name="createdBy"  value="${question.createdBy.id }"/> 
											<label for="title">Intitulé de la question</label>

										</div>
									</div>


								</div>
								<div class="row">
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
								


							</div>


						</div>
					</div>
					<div class="col-lg-4">
						<div class="card">
							<div class="card-header">Assertions</div>
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
										<div class="form-floating  mb-3">
											<input type="text" class="form-control"
												required="required" id="reponse" name="reponse"
												placeholder="Reponse" />
											<label for="title">Reponse 1</label>

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-floating  mb-3">
											<input type="text" class="form-control"
												required="required" id="reponse" name="reponse"
												placeholder="Reponse" />
											<label for="title">Reponse 2</label>

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-floating  mb-3">
											<input type="text" class="form-control"
												required="required" id="reponse" name="reponse"
												placeholder="Reponse" />
											<label for="title">Reponse 3</label>

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-floating  mb-3">
											<input type="text" class="form-control"
												required="required" id="reponse" name="reponse"
												placeholder="Reponse" />
											<label for="title">Reponse 4</label>

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-floating  mb-3">
											<input type="text" class="form-control"
												required="required" id="reponse" name="reponse"
												placeholder="Reponse" />
											<label for="title">Reponse 5</label>

										</div>
									</div>
								</div>
								<div class="row p-2">
									<div class="col-md-2">
										<button type="submit" value="Register" class="btn btn-success">Enregistrer</button>
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
