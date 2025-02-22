<!DOCTYPE html>
<%
response.setContentType("text/html; charset=utf-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title class="appName">CNPR</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="<c:url value="/img/cnpr.png"/>" rel="icon">
<link href="<c:url value="/img/cnpr.png"/>" rel="apple-touch-icon">


<link
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />

<link
	href="${pageContext.request.contextPath}/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/quill/quill.snow.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/quill/quill.bubble.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/remixicon/remixicon.css"
	rel="stylesheet">



<script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/json2/20130526/json2.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.min.js"></script>


<style>
.canvas-container {
	width: 800px;  /* Set the visible width */
	height: 800px;  /* Set the visible height */
	overflow: auto; /* Enable scrolling */
	border: 2px solid black;
}

/* canvas {
	width: 1000px; 
	height: 600px; 
	background-color: lightgray;
} */
</style>



<style type="text/css">
#main {
	/* font-size: 13px; */
	
}

#result {
	border: 1px dotted #ccc;
	padding: 3px;
}

#result ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
}

#result ul li {
	padding: 5px 0;
}

#result ul li:hover {
	background: #eee;
}

body {
	font-size: 13px;
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
}

#loader {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(255, 255, 255, 0.9);
	display: flex;
	justify-content: center;
	align-items: center;
	z-index: 1000;
}

#loader i {
	font-size: 3rem;
	animation: spin 1s linear infinite;
}

@
keyframes spin { 100% {
	transform: rotate(360deg);
}

}
.content {
	display: none;
}
</style>


<!-- Template Main CSS File -->
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">

</head>

<body>

	<div id="loader">
		<i class="fas fa-spinner"></i>
	</div>
	<%
	// Set refresh with autoload time 1 seconds
	//response.setIntHeader("Refresh", 5);
	//response.setHeader("Refresh", "10; URL=/sirh-pnc/");
	%>


	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">

		<div class="d-flex align-items-center justify-content-between">
			<a href="index.html" class="logo d-flex align-items-center"> <img
				src="<c:url value="/img/cnpr.png"/>" alt=""> <span
				class="d-none d-lg-block appName">${loggedUserRole} </span>
			</a> <i class="bi bi-list toggle-sidebar-btn"></i>
		</div>


		<nav class="header-nav ms-auto">
			<ul class="d-flex align-items-center">


				<li class="nav-item d-block "><a
					class="nav-link nav-icon search-bar-toggle " href="#"> <c:if
							test="${autoEcoleUser ne null}">
							<i class="ri ri-building-4-fill"></i> ${autoEcoleUser.cnprAutoEcole.designation}
					</c:if>

				</a></li>


				<li class="nav-item dropdown pe-3"><a
					class="nav-link nav-profile d-flex align-items-center pe-0"
					href="#" data-bs-toggle="dropdown"> <img
						src="<c:url value="/img/avatar.png"/>" alt="Profile"
						class="rounded-circle"> <!-- <i class="bi bi-check-circle me-1"></i> -->
						<span class="d-none d-md-block dropdown-toggle ps-2">Bienvenu(e)
							${username}!</span>
				</a> <!-- End Profile Iamge Icon -->

					<ul
						class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
						<li><a class="dropdown-item d-flex align-items-center"
							href="/cnpr-homologation/logout"> <i
								class="bi bi-box-arrow-right"></i> <span>Deconnexion</span>
						</a></li>
						<li><a class="dropdown-item d-flex align-items-center"
							href="/cnpr-homologation/user/userdetails"> <i
								class="bi bi-box-arrow-right"></i> <span>Info du compte</span>
						</a></li>

					</ul> <!-- End Profile Dropdown Items --></li>
				<!-- End Profile Nav -->

			</ul>
		</nav>
		<!-- End Icons Navigation -->

	</header>
	<!-- End Header -->