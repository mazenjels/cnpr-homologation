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

		<div class=row>
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"></h5>
						<form:form modelAttribute="question" method="post"
							action="/cnpr-homologation/question/save">
							<fieldset>
									<p>
										<span class="card-title">${queryResult}</span>
									</p>
								</fieldset>
								<div class="row">
									<div class="col-md-6">
										<div class="form-floating  mb-3">
											<form:input type="text" class="form-control"
												required="required" id="title" path="title"
												placeholder="Intitulé de la question" />
											<form:input type="hidden" path="id" />
											<label for="title">Intitulé de la question</label>

										</div>
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


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
