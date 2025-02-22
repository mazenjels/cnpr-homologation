<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title class="appName">CNPR</title>
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
<link
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
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
											<!-- <h4 class="mt-1 mb-5 pb-1">Sponsorisez vos equipes</h4> -->
										</div>
										<hr />

										<c:url value="${contextPath}/login" var="loginUrl" />

										<form class="row g-3 needs-validation" method="post"
											action="${loginUrl}">

											<div class="col-12">

												<div class="input-group has-validation">
													<span class="input-group-text" id="inputGroupPrepend"><i
														class="bi bi-person"></i></span> <input type="text"
														placeholder="Identifiant de connexion" name="username"
														class="form-control" id="username" required>
													<div class="invalid-feedback">Veuillez saisir votre
														identifiant.</div>
												</div>
											</div>
											<div class="col-12">

												<div class="input-group has-validation">
													<span class="input-group-text" id="inputGroupPrepend"><i
														class="bi bi-key"></i></span> <input type="password"
														placeholder="Mot de passe" name="password"
														class="form-control" id="password" required>
													<div class="invalid-feedback">Veuillez saisir votre
														mot de passe</div>
												</div>
											</div>
											<div class="col-12">
												<button type="submit" name="action" value="login"
													class="btn btn-success">
													<i class="bi bi-arrow-bar-right"></i> Se connecter
												</button>
											</div>



										</form>
										 <c:if test="${param.error != null}">
											<p style="color: red;">Identifiant ou mot de passe incorrect. Contactez l'administrateur</p>
										</c:if>
										<c:if test="${param.logout != null}">
											<p style="color: green;">You have been logged out.</p>
										</c:if> 
										<%-- <c:if test="${not empty param.error}">
											<p style="color: red;">${param.error}</p>
										</c:if> --%>

									</div>
								</div>
								 <div
									class="col-lg-6 d-flex align-items-center gradient-custom-2">
									<div class="text-white px-3 py-4 p-md-5 mx-md-4">
										<h4 class="mb-4">Comission Nationale de Prévention Routiere</h4>
										<p class="small mb-0"></p>
									</div>
								</div> 
								<!-- <div
									class="col-lg-6 d-flex align-items-center gradient-custom-2">
									<div class="text-white px-3 py-4 p-md-5 mx-md-4">
										<h4 class="mb-4">Fédération congolaise de football association</h4>
										<p class="small mb-0"></p>
									</div>
								</div> -->
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<fmt:setBundle basename="messages" />
								<fmt:message key="message.password" var="noPass" />
								<fmt:message key="message.username" var="noUser" />

								<%-- <c:if test="${authenticated != null and authenticated eq false}">
									<p class="invalid-feedback">Invalid username or password</p>
								</c:if> --%>
								<%-- <c:if test="${param.regSucc == true}">
									<div id="status">
										<fmt:message key="message.regSucc" />
									</div>
								</c:if>
								<c:if test="${param.regError == true}">
									<div id="error">
										<fmt:message key="message.regError" />
									</div>
								</c:if>
								<c:if test="${param.error != null}">
									<div id="error">
										<fmt:message key="message.badCredentials" />

									</div>
								</c:if> --%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</main>

<script type="text/javascript">
	function validate() {
		if (document.f.username.value == "" && document.f.password.value == "") {
			alert("Username and password are required");
			document.f.username.focus();
			return false;
		}
		if (document.f.username.value == "") {
			alert("Username is required");
			document.f.username.focus();
			return false;
		}
		if (document.f.password.value == "") {
			alert("Password is required");
			document.f.password.focus();
			return false;
		}
	}
</script>
<script
	src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


<!-- Template Main JS File -->
<script src="${pageContext.request.contextPath}/js/main.js"></script>