<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>E-Learning > Leçons > Modules > Contenu</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Leçons :
					${moduleContent.lessonModule.lesson.title } / Module :
					${moduleContent.lessonModule.title }</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/lesson/addModules/${lessonModule.id }"
					class="btn btn-primary"><i class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-6">
				<div class="card">
					<div class="card-header"></div>
					<div class="card-body">


						<form:form modelAttribute="moduleContent" method="post"
							enctype="multipart/form-data"
							action="/cnpr-homologation/lesson/module/saveContent">
							<div class="row">
								<div class="col-md-10">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control"
											required="required" id="title" path="title"
											placeholder="Ajouter un titre" />
										<form:input type="hidden" path="id" />
										<form:input type="hidden" path="lessonModule" />
										<form:input type="hidden" path="createdBy" />
										<label for="title">Ajouter un titre</label>

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">

									<div class="form-floating mb-3">
										<form:textarea class="form-control" rows="10"
											style="height:100%;" id="paragraph" path="paragraph"></form:textarea>
										<label for="paragraph">Contenu du titre</label>

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-10">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control" id="videoUrl"
											path="videoUrl" placeholder="Ajouter un lien pour la video" />
										<label for="videoUrl">Ajouter un lien pour la video</label>

									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-10">
									<div class="form-floating  mb-3">
										<form:input type="text" class="form-control" id="audioUrl"
											path="audioUrl" placeholder="Ajouter un lien pour l'audio" />
										<label for="audiooUrl">Ajouter un lien pour l'audio</label>

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-10">
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
			<div class="col-lg-6">
				<div class="card">
					<div class="card-body"
						style="height: 600px; overflow-x: hidden; overflow-y: auto;">
						<h5 class="card-title">Les contenus dans ce module</h5>

						<!-- Default Accordion -->
						<div class="accordion" id="accordionContent">
							<c:forEach var="moduleContent" items="${moduleContentList}"
								varStatus="counter">

								<div class="accordion-item">
									<h2 class="accordion-header" id="heading${counter.count}">
										<button class="accordion-button" type="button"
											data-bs-toggle="collapse"
											data-bs-target="#collapse${counter.count}"
											aria-expanded="${counter.count == 0? true: false }"
											aria-controls="collapse${counter.count}">Section
											#${counter.count}</button>
									</h2>
									<div id="collapse${counter.count}"
										class="accordion-collapse ${counter.count eq 0? 'collapse show': '' }"
										aria-labelledby="heading${counter.count}"
										data-bs-parent="#accordionContent">
										<div class="accordion-body">
											<h2 style="color: green;">${moduleContent.title }
												<c:if test="${moduleContent.activeStatus eq true}">
													<c:if
														test="${loggedUserPermission['update_module_contenu'] eq true}">
														<a
															href="/cnpr-homologation/lesson/content/${moduleContent.id}/edit"
															title="Mettre a jour le contenu"><i
															class="bi bi-pencil text-primary"></i></a>
													</c:if>
												</c:if>
												<c:if
													test="${loggedUserPermission['change_status_module_contenu'] eq true}">
													<a
														href="/cnpr-homologation/lesson/content/${moduleContent.id}/changeStatus"
														title="Activer ou desactiver"><i
														class="bi bi-arrow-left-right text-warning"></i></a>
												</c:if>
											</h2>
											<div class="square">
												<div>
													<c:choose>
														<c:when test="${empty moduleContent.imageUrl}">
															<img style="float: left; margin: 5px;"
																src="http://109.235.67.98:8080/medias/no-image.png"
																width="80px" height="80px" alt="Illustration">
														</c:when>
														<c:otherwise>
															<img style="float: left; margin: 5px;"
																src="${moduleContent.imageUrl}" width="80px"
																height="80px" alt="Illustration">
														</c:otherwise>
													</c:choose>

												</div>
												<p style="text-align: justify; font-size: 16px;">${moduleContent.paragraph}</p>
											</div>

											<table class="table borderless">
												<tr>
													<c:if test="${not empty moduleContent.audioUrl}">
														<td>
															<figure>
																<audio controls src="${moduleContent.audioUrl}"></audio>
															</figure>
														</td>
													</c:if>
													<c:if test="${not empty moduleContent.videoUrl}">
														<td><video width="500px" height="400px"
																controls="controls">
																<source src="${moduleContent.videoUrl}" type="video/mp4" />
															</video></td>
													</c:if>



												</tr>
											</table>

										</div>

									</div>
								</div>
							</c:forEach>
						</div>
						<!-- End with custom content -->

					</div>
				</div>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
