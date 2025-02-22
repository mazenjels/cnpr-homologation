
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title class="appName">Xertis RH - Connexion</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="<c:url value="/img/cnpr.png"/>" rel="icon">
<link href="<c:url value="/img/cnpr.png"/>" rel="apple-touch-icon">

<!-- Google Fonts -->
<!-- <link href="https://fonts.gstatic.com" rel="preconnect"> -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/vendor/bootstrap-icons/bootstrap-icons.css"/>"
	rel="stylesheet">
<link href="<c:url value="/vendor/boxicons/css/boxicons.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/css/style.css"/>" rel="stylesheet">

<style>
.gradient-custom-2 {
	/* fallback for old browsers */
	background: #fccb90;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
}

@media ( min-width : 768px) {
	.gradient-form {
		height: 100vh !important;
	}
}

@media ( min-width : 769px) {
	.gradient-custom-2 {
		border-top-right-radius: .3rem;
		border-bottom-right-radius: .3rem;
	}
}
</style>
</head>

<main>
	<div class="container">

		<section class="h-100 gradient-form">
			<div class="container py-5 h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-xl-10">
						<div class="card rounded-3 text-black">
							<div class="row g-0">
								<div class="col-lg-6">
									<div class="card-body p-md-5 mx-md-4">

										<div class="text-center">
											<img src="<c:url value="/img/cnpr.png"/>"
												style="width: 185px;" alt="logo">
											 <h4 class="mt-1 mb-5 pb-1">Veuillez changer votre mot de passe pour continuer - ${loggedUser.username}</h4>
										</div>
										<hr/>

										<c:url value="${contextPath}/login" var="loginUrl" />

										<form class="row g-3 needs-validation" method="post"
											action="/cnpr-homologation/user/changeDefaultPassword">
											
											<div class="col-12">
												<!-- <label for="yourUsername" class="form-label">Mot de
													passe</label> -->
												<div class="input-group has-validation">
													<span class="input-group-text" id="inputGroupPrepend"><i
														class="bi bi-key"></i></span> <input type="password" placeholder="Nouveau mot de passe"
														name="newPassword" class="form-control" id="password"
														required>
													<div class="invalid-feedback">Nouveau mot de passe</div>
												</div>
											</div>
											<div class="col-12">
												<!-- <label for="yourUsername" class="form-label">Mot de
													passe</label> -->
												<div class="input-group has-validation">
													<span class="input-group-text" id="inputGroupPrepend"><i
														class="bi bi-key"></i></span> <input type="password" placeholder="Confirmer nouveau mot de passe"
														name="confirmNewPassword" class="form-control" id="password"
														required>
													<div class="invalid-feedback">Confirmer votre mot de passe</div>
												</div>
											</div>
											<div class="col-12">
												<button class="btn btn-primary w-100" type="submit">Confirmer</button>
											</div>



										</form>

									</div>
								</div>
								<div
									class="col-lg-6 d-flex align-items-center gradient-custom-2">
									<div class="text-white px-3 py-4 p-md-5 mx-md-4">
										<h4 class="mb-4">CNPR RDC - Commission Nationale de
											Prévention Routière</h4>
										<p class="small mb-0"></p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</main>

<%-- <script src="<c:url value="/vendor/apexcharts/apexcharts.min.js"/>"></script> --%>
<script
	src="<c:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<%-- <script src="<c:url value="/vendor/chart.js/chart.min.js"/>"></script>
<script src="<c:url value="/vendor/echarts/echarts.min.js"/>"></script>
<script src="<c:url value="/vendor/quill/quill.min.js"/>"></script>
<script
	src="<c:url value="/vendor/simple-datatables/simple-datatables.js"/>"></script> --%>
<%-- <script src="<c:url value="/vendor/tinymce/tinymce.min.js"/>"></script>
<script src="<c:url value="/vendor/php-email-form/validate.js"/>"></script> --%>

<!-- Template Main JS File -->
<script src="<c:url value="/js/main.js"/>"></script>