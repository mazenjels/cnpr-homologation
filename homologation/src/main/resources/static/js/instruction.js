$(document).ready(function() {

	$(".edit-item-btn").click(function() {



		var codeUniqueInstruction = $(this).closest('tr').find('.codeUniqueInstruction').text();
		//var statusInstruction = $(this).closest('tr').find('.statusInstruction').text();
		$("#refInstructionLabel").text('Dossier No. ' + codeUniqueInstruction);
		var data = { "codeUnique": codeUniqueInstruction };
		var jsonString = JSON.stringify(data);
		var url = "http://localhost:8080/sirh-pnc/instruction/getById";
		var method = "POST";
		$.ajax({
			url: url,
			type: method,
			data: jsonString,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(response) {
				//console.log(response);

				$("#codeUnique").val(response.instruction_code_unique);
				$("#dateReceptionCourrier").val(response.instruction_date_reception);
				$("#dateTransmissionCourrier").val(response.instruction_date_transmission);
				$("#objet").val(response.instruction_objet);
				$("#numeroCourrier").val(response.instruction_numero_courrier);
				$("#numeroIndicateur").val(response.instruction_numero_indicateur);
				$("#status").val(response.instruction_statut);
				$("#currentStatus").val(response.instruction_current_status);
				$("#priorite").val(response.instruction_priorite);
				$("#matriculePolicier").val(response.instruction_matricule_policier);
				$("#nomsDuPolicier").val(response.instruction_noms_policier);
				$("#decisionDirecteur").val(response.instruction_decision_directeur);
				$("#avisDirecteurAdjoint").val(response.instruction_note_directeur);
				$("#noteDirecteurAdjoint").val(response.instruction_noms_policier);
				$("#noteDirecteur").val(response.instruction_note_directeur);
				$("#actionSpecialeAfaire").val(response.instruction_noms_policier);
				$("#document").val(response.instruction_document);
				$("#departement").val(response.departement.departement_id);
				$("#site").val(response.site.site_id);
				$("#personnel").val(response.personnel.personnel_id);
				$("#createdBy").val(response.user.user_id);

				if ($("#status").val() === 'brouillon') {
					$('#submitInstruction').show();
				} else { $('#submitInstruction').hide(); }

				if ($("#status").val() === 'transmis') {
					$('#labelTransmis').show();
				} else {
					$('#labelTransmis').hide();
				}

			},
			error: function(xhr, status, error) {
				console.log(xhr.responseText);
			}
		});





	});

	$('.view-data').on('click', function() {
		// Get the parent row
		//e.preventDefault();


		var $row = $(this).closest('tr');
		/*	var data = $row.children("td").map(function() {
				return $(this).text();
			}).get();*/


		// Extract data from the row
		var codeUnique = $(this).parents("tr").find(".codeUniqueInstruction").text();
		var objet = $row.find(".objetInstruction").text();
		//	var heureDate = $(this).parents("tr").find(".gfgusername").text(); 
		//var numCourrier =$(this).parents("tr").find(".gfgusername").text(); 
		// var indicateur = $row.find('td:nth-child(4)').text();
		//var statut = $row.find('td:nth-child(6)').text();
		//var pourTraitement = $row.find('td:nth-child(7)').text();
		//var priorite = $row.find('td:nth-child(8)').text();
		//var origine = $row.find('td:nth-child(9)').text();
		// var matriculePolicier = 

		// Set the data in the modal
		$('#refInstructionLabel').text(codeUnique);
		$('#objet').val(objet);
		//$('#numeroCourrier').val(numCourrier);
		//$('#dateReceptionCourrier').val(heureDate);
		// $('#numeroCourrier').text(numCourrier);
		// $('#numeroCourrier').text(numCourrier);
		// $('#numeroCourrier').text(numCourrier);
		// $('#numeroCourrier').text(numCourrier);
		// $('#numeroCourrier').text(numCourrier);
		//  $('#numeroCourrier').text(numCourrier);

	});

	/*	$('formInstructionId').on('submit', function(e) {
	
			e.preventDefault();
	
			$.ajax({
				type: 'post',
				url: 'your_complete url',
				data: $('form').serialize(),
				success: function(response) {
					//$('form')[0].reset();
					// $("#feedback").text(response);
					if (response == "True") {
						$('form')[0].reset();
						$("#feedback").text("Your information has been stored.");
					}
					else
						$("#feedback").text(" Some Error has occured Errror !!! ID duplicate");
				}
			});
	
		});*/

	$('#submitInstruction').on('click', function(e) {

		e.preventDefault();

		//var form = document.getElementById('formInstructionId');
		//var formData = new FormData(form);

		var data = {
			codeUnique: $("#codeUnique").val(),
			objet: $("#objet").val(),
			numeroCourrier: $("#numeroCourrier").val(),
			matriculePolicier: $("#matriculePolicier").val(),
			numeroIndicateur: $("#numeroIndicateur").val(),
			nomsDuPolicier: $("#nomsDuPolicier").val(),
			dateReceptionCourrier: $("#dateReceptionCourrier").val(),
			dateTransmissionCourrier: $("#dateTransmissionCourrier").val(),
			decisionDirecteur: $("#decisionDirecteur").val(),
			avisDirecteurAdjoint: $("#avisDirecteurAdjoint").val(),
			noteDirecteurAdjoint: $("#noteDirecteurAdjoint").val(),
			noteDirecteur: $("#noteDirecteur").val(),
			status: 'brouillon',
			dataSource: "Identification",
			currentStatus: 'brouillon',
			priorite: $("#priorite").val(),
			actionSpecialeAfaire: $("#actionSpecialeAfaire").val(),
			document: $("#document").val(),
			site: {
				id: $("#site").val()
			},
			personnel: {
				id: $("#personnel").val()
			},
			departement: {
				id: $("#departement").val()
			},
			createdBy: {
				userId: $("#createdBy").val()
			}
		};

		//var jsonString = $('formInstructionId').serialize();
		var url = 'http://192.168.88.53:8080/sirh-rest/api/v1/instruction/send';
		var method = 'POST';

		$.ajax({
			url: url,
			type: method,
			data: JSON.stringify(data),
			contentType: 'application/json',
			success: function(response) {
				console.log(response);
				data = {
					codeUnique: response.codeUnique
				}
				$.ajax({
					url: '/sirh-pnc/instruction/updateStatus',
					type: method,
					data: JSON.stringify(data),
					contentType: 'application/json',
					success: function(response) {
						console.log(response);
						window.location.href = "/sirh-pnc/admin/instruction/list";
					},
					error: function(xhr, status, error) {
						console.log(xhr.responseText);
					}
				});
			},
			error: function(xhr, status, error) {
				console.log(xhr.responseText);
			}
		});


	});

	$.ajax({
		type: "GET",
		url: "/sirh-pnc/departementsCanTreatList",
		data: "json",
		contentType: "application/json",
		success: function(data) {
			let obj = $.parseJSON(data);
			$.each(obj, function(key, value) {
				$('#departementId').append('<option value="' + value.departement_id + '">' + value.departement_designation + '</option>');
			});
		},

		error: function(data) {

			$('#departementId').append('<option value=0>Aucun departement</option>');
		},
	});
	//////////////////////////////////////////

	$('#departementId').change(function() {
		$('#instructionToTreatBy').text('A traiter par le ' + $('#departementId :selected').text());
	});
	////////////////////////////////////////////////
	$('.btnModalFormulaireInstruction').on('click', function() {

		$('#modalFormulaireInstruction').modal('show');
		$tr = $(this).closest('tr');
		var data = $tr.children("td").map(function() {
			return $(this).text();
		}).get();
		$('#modalFormulaireInstructionTitle"').html("Instruction a partir de l'identite trouvee MBENDE MBOLIFALANGA JEAN-BLAISE, matricule 1196201711709");
	});

	$(document).on('click', '#btnMatriculeToSearch', function(e) {

		if ($('#matriculeToSearch').val().length === 0) {

			$("#tableCarriereFoundMatricule tbody > tr").remove();
			return;
		}
		var matriculeToSearch = $('#matriculeToSearch').val();

		var route = "/sirh-pnc/instruction/search?matriculeToSearch=" + matriculeToSearch;

		console.log("Matricule : " + matriculeToSearch);

		$.ajax(
			{
				url: route,
				type: "GET",
				contentType: "application/json",
				success: function(data) {
					$('#matriculeToSearch').val('');
					$("#tableCarriereFoundMatricule").find("tr:gt(0)").remove();
					let obj = $.parseJSON(data);
					var row = '<tr> <td ></td><td>' + obj.personnel.matricule + '</td><td>' + obj.personnel.nom + '</td><td>' + obj.personnel.postnom + '</td><td>' + obj.personnel.prenom + '</td><td><a id="btnModalFormulaireInstruction" title="Creer une instruction  a partir cette entree" class="btn btn-success btn-sm btnModalFormulaireInstruction" data-bs-toggle="modal" data-bs-target="#modalFormulaireInstruction"><i class="bi bi-info-square"></i></a></td></tr>';
					$(row).appendTo('#tableCarriereFoundMatricule tbody');

				}
			});
	});

	////////////////////
	/*$(document).on('click', '#btnSearchMatriculeParticipant', function(e) {

			if ($('#matriculeParticipant').val().length === 0) {

				$("#tableFormationMatricule tbody > tr").remove();
				return;
			}
			var matriculeToSearch = $('#matriculeParticipant').val();

			var route = "/sirh-pnc/formation/search?matriculeToSearch=" + matriculeToSearch;

			//console.log("Matricule : " + matriculeToSearch);

			$.ajax(
				{
					url: route,
					type: "GET",
					contentType: "application/json",
					success: function(data) {
						$('#matriculeParticipant').val('');
						$("#tableFormationMatricule").find("tr:gt(0)").remove();
						let obj = $.parseJSON(data);
						var row = '<tr> <td ></td><td>' + obj.personnel.matricule + '</td><td>' + obj.personnel.nom + '</td><td>' + obj.personnel.postnom + '</td><td>' + obj.personnel.prenom + '</td><td><a id="btnModalFormulaireInstruction" title="Creer une instruction  a partir cette entree" class="btn btn-success btn-sm btnModalFormulaireInstruction" data-bs-toggle="modal" data-bs-target="#modalFormulaireInstruction"><i class="bi bi-info-square"></i></a></td></tr>';
						$(row).appendTo('#tableFormationMatricule tbody');

					}
				});
		});
*/
});