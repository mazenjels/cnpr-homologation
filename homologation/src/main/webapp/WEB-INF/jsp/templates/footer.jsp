<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!-- ======= Footer ======= -->
<footer id="footer" class="footer">
	<div class="copyright">
		&copy; Copyright <strong><span id="appName" class="appName">
				<sup>Hoja</sup>
		</span></strong>. All Rights Reserved
	</div>
	<div class="credits">
		<!-- All the links in the footer should remain intact. -->
		<!-- You can delete the links only if you purchased the pro version. -->
		<!-- Licensing information: https://bootstrapmade.com/license/ -->
		<!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
		Designed by <a href="#" id="partenaireName" class="partenaireName">
			Beaudry Mazengele </a>
	</div>
</footer>
<!-- End Footer -->

<a href="#"
	class="back-to-top d-flex align-items-center justify-content-center"><i
	class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script
	src="${pageContext.request.contextPath}/vendor/apexcharts/apexcharts.min.js" /></script>
<script
	src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/chart.js/chart.min.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/echarts/echarts.min.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/quill/quill.min.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/simple-datatables/simple-datatables.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/tinymce/tinymce.min.js" /></script>
<script
	src="${pageContext.request.contextPath}/vendor/php-email-form/validate.js"></script>



<!-- Template Main JS File -->
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

<%-- <script src="${jqueryJs}"></script>
 --%>



<%-- <script src="${pageContext.request.contextPath}/js/formulaireTypeProcessusMetiers.js"/></script>
<script src="${pageContext.request.contextPath}/js/instruction.js"></script> --%>


<script>
$(window).on("load", function() {
    $("#loader").fadeOut("slow", function() {
        $(".content").fadeIn();
    });
});
	function hideLoader() {
		$('.page-loader').fadeOut('slow');
	}

	$('.btnModalRegisterPayment').on('click', function() {
		$('#formModalRegisterPayment').modal('show');
	});
	
	$('.btnModalFilterPayment').on('click', function() {
		$('#formModalFilterPayment').modal('show');
	});

/* 	$
	.ajax({
		type : "GET",
		url : "/cnpr-homologation/api/provinceList",
		data : "json",
		contentType : "application/json",
		success : function(data) {
			let obj = $.parseJSON(data);
			$
					.each(
							obj,
							function(key, value) {
								$(
										'#provincePlanification')
										.append(
												'<option value="' + value.province_id + '">'
														+ value.province_designation
														+ '</option>');
							});
		},

		error : function(data) {

			$('#provincePlanification')
					.append(
							'<option value=0>Aucune province</option>');
		},
	}); */
	
	//
	$('#cardResult').hide();
	$('#rowSelectChauffeur').hide();
	$('#fieldsetInfoInit').hide();
		$('#selectedVehicule')
	.change(
			function() {

				 $('#cardResult').show();
				var vehicule1 = $('#selectedVehicule').val();

				var inputValObj = {};

				inputValObj.vehiculeId = vehicule1;
				var inputVal = JSON.stringify(inputValObj);

				var data = inputVal.toString();

				$.ajax({
							type : "GET",
							url : "/cnpr-homologation/api/checkVehiculeAvailability?vehiculeId="
									+ vehicule1,

							contentType : "application/json",
							success : function(data) {
								console.log("Result: "+data);
								$('#table-vehicule-availability tbody').empty();
								$('#cardbodyResult').empty();
								
								
								let obj = $.parseJSON(data);
								
								if(jQuery.isEmptyObject(obj)){
									$('#rowSelectChauffeur').show();
									$('#cardbodyResult ').append('<h5 class="card-title" ><span class="badge bg-success" ><i class="bi bi-exclamation-octagon me-1"></i> Vehicule disponible</span></h5>');
								}else{
									$('#rowSelectChauffeur').hide();
									$('#fieldsetInfoInit').hide();
									$('#cardbodyResult ').append('<h5 class="card-title" ><span class="badge bg-danger" ><i class="bi bi-exclamation-octagon me-1"></i> Vehicule occupe</span></h5>');
									$('#cardbodyResult ').append('<table class="table table-sm table-vehicule-availability" id="table-vehicule-availability"> <tbody></tbody> </table>');
									$
									.each(
											obj,
											function(key, value) {
												var newResult = '<tr><td>Vehicule</td><td>'+value.vehicule+'</td></tr>';
												newResult  += '<tr><td>Chauffeur</td><td>'+value.chauffeur+'</td></tr>';
												newResult  += '<tr><td>Date de requisition</td><td> le '+value.date_requisition+' a '+value.heure_requisition+'</td></tr>';
												newResult  += '<tr><td>Destination initiale</td><td>'+value.destination_initiale+'</td></tr>';
												newResult  += '<tr><td>Passager initial</td><td>'+value.passager_initial+'</td></tr>';
												newResult  += '<tr><td>Kilometrage initial</td><td>'+value.kilometrage_initial+' Km</td></tr>';
												newResult  += '<tr><td>Enregistre le </td><td>'+value.created_at+'</td></tr>';
												$('#table-vehicule-availability tbody').append(newResult);
												
											});
								}
								
							},
							error : function(data) {
								
								$('#resultVehicule').text('Aucune course enregistree');
								
							},
						});
			});
	/////////////////////////////////////
	
	$('#selectedDriver')
	.change(
			function() {

				 $('#cardResult').show();
				var driver1 = $('#selectedDriver').val();

				var inputValObj = {};

				inputValObj.driverId = driver1;
				var inputVal = JSON.stringify(inputValObj);

				var data = inputVal.toString();

				$.ajax({
							type : "GET",
							url : "/cnpr-homologation/api/checkDriverAvailability?driverId="
									+ driver1,

							contentType : "application/json",
							success : function(data) {
								console.log("Result: "+data);
								$('#table-vehicule-availability tbody').empty();
								$('#cardbodyResult').empty();
								
								
								let obj = $.parseJSON(data);
								
								if(jQuery.isEmptyObject(obj)){
									$('#cardbodyResult ').append('<h5 class="card-title" ><i class="bi bi-exclamation-octagon me-1"></i> Chauffeur disponible</h5>');
									$('#fieldsetInfoInit').show();
								}else{
									$('#fieldsetInfoInit').hide();
									$
									.each(
											obj,
											function(key, value) {
												/* var newResult = '<tr><td>Vehicule</td><td>'+value.vehicule+'</td></tr>';
												newResult  += '<tr><td>Chauffeur</td><td>'+value.chauffeur+'</td></tr>';
												newResult  += '<tr><td>Date de requisition</td><td> le '+value.date_requisition+' a '+value.heure_requisition+'</td></tr>';
												newResult  += '<tr><td>Destination initiale</td><td>'+value.destination_initiale+'</td></tr>';
												newResult  += '<tr><td>Passager initial</td><td>'+value.passager_initial+'</td></tr>';
												newResult  += '<tr><td>Kilometrage initial</td><td>'+value.kilometrage_initial+' Km</td></tr>';
												newResult  += '<tr><td>Enregistre le </td><td>'+value.created_at+'</td></tr>'; */
												$('#cardbodyResult ').append('<h5 class="card-title" ><i class="bi bi-exclamation-octagon me-1"></i> Le chaufeur '+value.chauffeur+' est indisponible. <br/>Il occupe le vehicule '+value.vehicule+'</h5>');
												
												
											});
								}
								
							},
							error : function(data) {
								
								$('#resultVehicule').text('Aucune course enregistree');
								
							},
						});
			});
	////////////////////////////////////
	$('#lessonQuestion')
	.change(
			function() {

				$('#moduleQuestion').find('option').remove();
				 $('#moduleQuestion').append(
						'<option>Selectionner le module</option>'); 			 

				var province1 = $('#lessonQuestion').val();


				var inputValObj = {};

				inputValObj.provinceId = province1;
				var inputVal = JSON.stringify(inputValObj);

				var data = inputVal.toString();

				$.ajax({
							type : "GET",
							url : "/cnpr-homologation/api/moduleByLesson?lessonId="
									+ province1,

							contentType : "application/json",
							success : function(data) {
								//console.log("territoire list: "+data);
								
								let obj = $.parseJSON(data);
								$
										.each(
												obj,
												function(key, value) {
													$(
															'#moduleQuestion')
															.append(
																	'<option value="' + value.lesson_module_id + '">'
																			+ value.lesson_module_title
																			+ '</option>');
													
												});
							},
							error : function(data) {
								
								$('#moduleQuestion')
										.append(
												'<option>Aucun module disponible</option>');
								
							},
						});
			});
	
	
	
	$('#provincePlanification')
			.change(
					function() {

						$('#districtPlanification').find('option').remove();
						 $('#districtPlanification').append(
								'<option>Selectionner le district</option>'); 
						 
						 $('#communePlanification').find('option').remove();

						var province1 = $('#provincePlanification').val();


						var inputValObj = {};

						inputValObj.provinceId = province1;
						var inputVal = JSON.stringify(inputValObj);

						var data = inputVal.toString();

						$.ajax({
									type : "GET",
									url : "/cnpr-homologation/api/districtByProvince?provinceId="
											+ province1,

									contentType : "application/json",
									success : function(data) {
										//console.log("territoire list: "+data);
										let obj = $.parseJSON(data);
										$
												.each(
														obj,
														function(key, value) {
															$(
																	'#districtPlanification')
																	.append(
																			'<option value="' + value.district_id + '">'
																					+ value.district_designation
																					+ '</option>');
															$(
																	'#communePlanification')
																	.find(
																			'option')
																	.remove();
														});
									},
									error : function(data) {
										$('#districtPlanification')
												.append(
														'<option>Aucun district disponible</option>');
										$('#communePlanification').find('option')
												.remove();
									},
								});
					});
	
	$('#districtPlanification').change(
			function() {

				$('#communePlanification').find('option').remove();
				 $('#communePlanification').append(
						'<option>Selectionner la commune</option>'); 

				var district1 = $('#districtPlanification').val();


				var inputValObj = {};

				inputValObj.districtId = district1;
				var inputVal = JSON.stringify(inputValObj);

				var data = inputVal.toString();

				$.ajax({
							type : "GET",
							url : "/cnpr-homologation/api/communeByDistrict?districtId="
									+ district1,

							contentType : "application/json",
							success : function(data) {
								//console.log("territoire list: "+data);
								let obj = $.parseJSON(data);
								$
										.each(
												obj,
												function(key, value) {
													$(
															'#communePlanification')
															.append(
																	'<option value="' + value.commune_id + '">'
																			+ value.commune_designation
																			+ '</option>');
													
												});
							},
							error : function(data) {
								$('#communePlanification')
										.append(
												'<option>Aucune commune disponible</option>');
								
							},
						});
			});
	
	/////////////////////////////////////
		$('#provinceAutoecole')
			.change(
					function() {

						$('#districtAutoecole').find('option').remove();
						 $('#districtAutoecole').append(
								'<option value="-1">Selectionner le district</option>'); 
						 
						 $('#communeAutoecole').find('option').remove();

						var province1 = $('#provinceAutoecole').val();


						var inputValObj = {};

						inputValObj.provinceId = province1;
						var inputVal = JSON.stringify(inputValObj);

						var data = inputVal.toString();

						$.ajax({
									type : "GET",
									url : "/cnpr-homologation/api/districtByProvince?provinceId="
											+ province1,

									contentType : "application/json",
									success : function(data) {
										//console.log("territoire list: "+data);
										let obj = $.parseJSON(data);
										$
												.each(
														obj,
														function(key, value) {
															$(
																	'#districtAutoecole')
																	.append(
																			'<option value="' + value.district_id + '">'
																					+ value.district_designation
																					+ '</option>');
															$(
																	'#communeAutoecole')
																	.find(
																			'option')
																	.remove();
														});
									},
									error : function(data) {
										$('#districtAutoecole')
												.append(
														'<option>Aucun district disponible</option>');
										$('#communeAutoecole').find('option')
												.remove();
									},
								});
					});
	
	$('#districtAutoecole').change(
			function() {

				$('#communeAutoecole').find('option').remove();
				 $('#communeAutoecole').append(
						'<option value="-1">Selectionner la commune</option>'); 

				var district1 = $('#districtAutoecole').val();


				var inputValObj = {};

				inputValObj.districtId = district1;
				var inputVal = JSON.stringify(inputValObj);

				var data = inputVal.toString();

				$.ajax({
							type : "GET",
							url : "/cnpr-homologation/api/communeByDistrict?districtId="
									+ district1,

							contentType : "application/json",
							success : function(data) {
								//console.log("territoire list: "+data);
								let obj = $.parseJSON(data);
								$
										.each(
												obj,
												function(key, value) {
													$(
															'#communeAutoecole')
															.append(
																	'<option value="' + value.commune_id + '">'
																			+ value.commune_designation
																			+ '</option>');
													
												});
							},
							error : function(data) {
								$('#communeAutoecole')
										.append(
												'<option>Aucune commune disponible</option>');
								
							},
						});
			});
	
	//////////////////////////////
	$('#moduleSelector')
			.change(
					function() {

						//$('#permissionCheckBoxes').find('option').remove();
						$("#div-checkboxes").empty();
						$("#divBtnAddPermission").empty();
						// $('#permissionCheckBoxes').append('<option>Selectionner la permission</option>'); 
						
						

						var module1 = $('#moduleSelector').val();

						// console.log('Selected module : '+module1);

						var inputValObj = {};

						inputValObj.moduleId = module1;
						var inputVal = JSON.stringify(inputValObj);

						var data = inputVal.toString();

						$.ajax({
									type : "GET",
									url : "/cnpr-homologation/api/permissionByModule?moduleShortCode="
											+ module1,

									contentType : "application/json",
									success : function(data) {
										$("#divBtnAddPermission").append('<button type="submit" value="Register" class="btn btn-success">Enregistrer</button>');
										//console.log("permission list: "+data);
										var url = "/cnpr-homologation/api/permissionByModule?moduleShortCode="
											+ module1;
										//console.log("url: "+url);
										let obj = $.parseJSON(data);
										$
												.each(
														obj,
														function(key, value) {
															//$('#permissionCheckBoxes') .append( '<option value="' + value.permission_id + '">'+ value.permission_code+ '</option>');
																	$("#div-checkboxes").append('<div> <input type="checkbox" name="action" value="' + value.permission_id + '" /> <label>' + value.permission_code + '</label> </div>');		
																	
														});
									},
									error : function(data) {
										$('#div-checkboxes') .append('<option>Aucune permission disponible</option>');
										$("#divBtnAddPermission").empty();
										
									},
								});
					});
</script>

</body>
<%-- <jsp:include page="autologout-script.jsp"></jsp:include>
 --%>
</html>