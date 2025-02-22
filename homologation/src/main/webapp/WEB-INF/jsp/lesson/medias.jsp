<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Médias</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active">Médias</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/lesson/medias" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Nouveau média</h5>

						<form method="POST" action="/cnpr-homologation/lesson/medias/save"
							enctype="multipart/form-data">
							<div class="row">
								<div class="col-md-4">
									<div class="form-floating mb-3">
										<select class="form-select" id="type_media" name="type_media"
											aria-label="State">
											<option value="-1">---</option>
											<option value="image">Image</option>
											<option value="video">Vidéo</option>
											<option value="audio">Audio</option>
										</select> <label for="role">Selectionner le type de média</label>
									</div>
								</div>
								<div class="col-md-8">
									<div class="form-floating  mb-3">
										<input type="file" class="form-control input-sm" name="file"
											accept="image/*, video/*, audio/*" required /><br />


									</div>

								</div>
							</div>
						<c:if test="${loggedUserPermission['joindre_document_media'] eq true}">
							<input type="submit" value="Upload"
								class="btn-xs btn btn-primary" />
								</c:if>
						</form>

					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<ul>
					<c:forEach items="${files}" var="file">
						<li><a href="http://${ipAddress}:8080/medias/${file}">http://${ipAddress}:8080/medias/${file}</a></li>
					</c:forEach>
				</ul>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
