/**
 * 
 */


$(document).ready(function() {


	/*function refreshTotalPolicier() {

		var xhr = new XMLHttpRequest();
		xhr.onload = function() {
			if (xhr.status === 200) {
				////console.log("response : "+xhr.responseText);
				var data = xhr.responseText;
				let obj = $.parseJSON(data);
				//document.getElementById('total_policier').innerHTML = obj.totalPolicier+"";
				$.each(obj, function(key, value) {
					$('#total_policier').html(obj.totalPolicier);
					$('#total_policier_homme').html(obj.nbHomme);
					$('#total_policier_femme').html(obj.nbFemme);
					//$('#total_policier_undefined').html(obj.nbUndefined);

				});

			}
		};
		xhr.open('GET', '/sirh-pnc/effectifPolicier', true);
		xhr.send(null);
	}
	setInterval(refreshTotalPolicier, 1000 * 10);*/

	$.ajax({
		type: "GET",
		url: "/sirh-pnc/appInfo",
		data: "json",
		contentType: "application/json",
		success: function(data) {

			let obj = $.parseJSON(data);
			////console.log(data.instructionTransmis);
			$.each(obj, function(key, value) {
				$('.appName').html(obj.appName);
				$('.partenaireName').html(obj.partenaireName);

			});
		},

	});
	//////////////////////////////////////////////////
	function refreshTotalInstruction() {

		var xhr = new XMLHttpRequest();
		xhr.onload = function() {
			if (xhr.status === 200) {
				//console.log("response : "+xhr.responseText);
				var data = xhr.responseText;
				let obj = $.parseJSON(data);
			/*	//console.log("Objet transmis =>"+obj.instructionTransmis);
				//console.log("Objet en attente enregistrement =>"+obj.instructionEnAttenteEnregistrement);
				//console.log("Objet en attente approbation =>"+obj.instructionEnAttenteApprobation);
				//console.log("Objet suspendue =>"+obj.instructionSuspendue);
				//console.log("Objet en cours de traitement =>"+obj.instructionEnCoursDeTraitement);
				//console.log("Objet en traitement =>"+obj.instructionEnTraitement);
				//console.log("Objet executee =>"+obj.instructionExecuted);*/
				////console.log("Objet =>"+obj);
				//$('#total_instruction_en_cours').html(obj.instructionTransmis);
				//$('#total_instruction_executees').innerHTML = obj.instructionExecuted;
				//$('#notification_instruction_executed').innerHTML = obj.instructionExecuted;
				//$('#notification_new_message').innerHTML = 'Vous avez '+obj.instructionExecuted+' messages';
				$.each(obj, function(key, value) {
					//////console.log(key+"=>"+value);
					//document.getElementById('total_instruction_en_cours').innerHTML = obj.instructionTransmis+"";
					//document.getElementById('total_instruction_executees').innerHTML = obj.instructionExecuted+"";
					//document.getElementById('notification_instruction_executed').innerHTML = obj.instructionExecuted+"";
					//document.getElementById('notification_new_message').innerHTML = 'Vous avez '+obj.instructionExecuted+' messages';

										/*$('#total_policier').html(obj.totalPolicier);
										$('#total_policier_homme').html(obj.nbHomme);
										$('#total_policier_femme').html(obj.nbFemme);*/
										
					$('#total_instruction_en_cours').html(obj.instructionTransmis);
					$('#total_instruction_executees').html(obj.instructionExecuted);
					$('#totalInstruction').html(obj.totalInstruction);
					if (parseInt(obj.instructionTransmis) > 0) {
						$('#notification_instruction_transmis').html(obj.instructionTransmis);
						$('#notification_new_instruction').html(obj.instructionTransmis + ' nouvelle(s) instruction(s) transmise(s)');
					} else {
						$('#notification_new_instruction').html('Aucune nouvelle instruction transmise');
					}
					if (parseInt(obj.instructionEnTraitement) > 0) {
						$('#notification_recus_bureau').html(obj.instructionEnTraitement);
						$('#notification_bureau_new').html(obj.instructionEnTraitement + 'instruction(s) en attente pour execution');
					} else {
							$('#notification_bureau_new').html('Aucune instruction en attente pour execution');
					}
					if (parseInt(obj.instructionEnAttenteApprobation) > 0) {
											$('#notification_recus_departement').html(obj.instructionEnAttenteApprobation);
											$('#notification_departement_new').html(obj.instructionEnAttenteApprobation + 'instruction(s) en attente pour approbation');
										} else {
												$('#notification_departement_new').html('Aucune instruction en attente pour approbation');
										}
					if (parseInt(obj.instructionExecuted) > 0) {
						$('#notification_instruction_executed').html(obj.instructionExecuted);
						$('#notification_new_message').html(obj.instructionExecuted + ' instructions finalisees');

					}
				});

			}
		};
		xhr.open('GET', '/sirh-pnc/totalInstruction', true);
		xhr.send(null);
	}
	setInterval(refreshTotalInstruction, 1000 * 10);

	//////////////////////////////////////////////////
	/*function refreshRecentsActiviies() {

		var xhr = new XMLHttpRequest();
		xhr.onload = function() {
			if (xhr.status === 200) {
				//////console.log("response : "+xhr.responseText);
				var data = xhr.responseText;
				let obj = $.parseJSON(data);
				$.each(obj, function(key, value) {
					
				});

			}
		};
		xhr.open('GET', '/instructionHistorique', true);
		xhr.send(null);
	}
	setInterval(refreshRecentsActiviies, 1000 * 10);
	*/
	////////////////////////////////////////////////

	$.ajax({
		type: "GET",
		url: "/sirh-pnc/totalInstruction",
		data: "json",
		contentType: "application/json",
		success: function(data) {

			let obj = $.parseJSON(data);
			////console.log(data.instructionTransmis);
			$.each(obj, function(key, value) {
				$('#total_instruction_en_cours').html(obj.instructionTransmis);
				$('#total_instruction_executees').html(obj.instructionExecuted);
				$('#totalInstruction').html(obj.totalInstruction);
				$('#instructionEntrant').html(obj.instructionEntrant);
				$('#instructionTransmis').html(obj.instructionTransmis);
				$('#instructionEnCoursDeTraitement').html(obj.instructionEnCoursDeTraitement);
				$('#instructionExecuted').html(obj.instructionExecuted);
				if (parseInt(obj.instructionTransmis) > 0) {
					$('#notification_instruction_transmis').html(obj.instructionTransmis);
					$('#notification_new_instruction').html(obj.instructionTransmis + ' nouvelle(s) instruction(s) transmise(s)');
				} else {
					$('#notification_new_instruction').html('Aucune nouvelle instruction transmise');
				}
				if (parseInt(obj.instructionExecuted) > 0) {
					$('#notification_instruction_executed').html(obj.instructionExecuted);
					$('#notification_new_message').html(obj.instructionExecuted + ' instructions finalisees');

				}



			});
		},

	});
	//////////////////////////////////////////////////////////
	$.ajax({
		type: "GET",
		url: "/sirh-pnc/effectifPolicier",
		data: "json",
		contentType: "application/json",
		success: function(data) {

			let obj = $.parseJSON(data);
			////console.log(data.instructionTransmis);
			$.each(obj, function(key, value) {
				$('#total_policier').html(obj.totalPolicier);
				$('#total_policier_homme').html(obj.nbHomme);
				$('#total_policier_femme').html(obj.nbFemme);
				//$('#total_policier_undefined').html(obj.nbUndefined);

			});
		},

	});
	//////////////////////////////////////////////////////////
	$.ajax({
		type: "GET",
		url: "/sirh-pnc/instructionHistorique",
		data: "json",
		contentType: "application/json",
		success: function(data) {

			let obj = $.parseJSON(data);
			$.each(obj, function(key, value) {
				////console.log("Message : "+value.message);
				var badgeColor = '';
				if (value.statut === 'brouillon')
					badgeColor = 'text-warning';
				else if (value.statut === 'transmis')
					badgeColor = 'text-primary';
				else if (value.statut === 'En traitement')
					badgeColor = 'text-danger';
				else if (value.statut === 'executed')
					badgeColor = 'text-success';
				else if (value.statut === 'en_attente_approbation') 
					badgeColor = 'text-info';
				
				else if (value.statut === 'attente_validation') 
					badgeColor = 'text-info';
				
				else if (value.statut === 'attente_prise_en_compte') 
					badgeColor = 'text-primary';
				

				//$('#activite_message').append('<h5>' + value.message + '</h5>');
				$('#activite_message').append("<div class=activity-item d-flex'' ><div class='activite-label'>" + value.date + " à " + value.heure + " (il y a " + value.elapsedTime + ")</div> <i class='bi bi-circle-fill activity-badge " + badgeColor + " align-self-start'></i>" + value.message + "<div class='activity-content'  > <a href='' class='fw-bold text-dark'>code," + value.code + "</a>, numero du courrier " + value.numero_courrier + ", indicateur: " + value.indicateur + ", statut :" + value.statut + "</div>");

			});
		},
		error: function(data) {
			$('#activite_message').append('<h5>Aucun processus metier disponible</h5>');
		}

	});

	//////////////////////////////////////////////////////////
	
	//$('#divTraitementDossier').empty();
	
	$.ajax({
		type: "GET",
		url: "/sirh-pnc/instructions",
		data: "json",
		contentType: "application/json",
		success: function(data) {
			//$('#spinnerTotalPolicier').hide();
			//$('#divTotalPolicier').show();
			//$('<div class="col-lg-8"><div class="card"><div class="card-body" style="overflow-x: hidden; overflow-y: auto; height: 500px;"><h5 class="card-title"> Traitement des dossiers </h5><table class="table table-secondary" id="table-instructions-dashboard"><thead><tr><th scope="col">Courier/Indicateur</th><th scope="col">Date et heure d\'enregistrement</th><th scope="col">Objet</th><th scope="col">Site</th><th scope="col">Statut</th></tr></thead><tbody></tbody></table></div></div></div>').appendTo('#divTraitementDossier');

		//	$('<div class="col-lg-4"><div class="card"><div class="card-body"><h5 class="card-title">Activités récentes <span>| Aujourd\'hui</span></h5><div class="row activity" style="overflow-x: hidden; overflow-y: auto; height: 500px;" id="activite_message"></div></div></div></div>').appendTo('#divTraitementDossier');
			let obj = $.parseJSON(data);
			var index = 0;

			$.each(obj, function(key, value) {
				var badgeColor = '';
				var badgeText = '';
				var styleColor='';
				if (obj[index].statut === 'brouillon') {
					badgeColor = 'text-warning';
					badgeText = 'Entrant';
					styleColor ='table-primary';
				}

				else if (obj[index].statut === 'transmis') {
					badgeColor = 'text-primary';
					badgeText = 'Transmis';
					styleColor ='table-info';
				}

				else if (obj[index].statut === 'En traitement') {
					badgeColor = 'text-danger';
					badgeText = 'En traitement';
					styleColor ='table-light';
				}

				else if (obj[index].statut === 'executed') {
					badgeColor = 'text-success';
					badgeText = 'Finalisée';
					styleColor ='table-success';
				}
				else if (obj[index].statut === 'en_attente_approbation') {
					badgeColor = 'text-info';
					badgeText = 'En attente approbation';
					styleColor ='table-secondary';
				}
				else if (obj[index].statut === 'attente_validation') {
					badgeColor = 'text-info';
					badgeText = 'En attente de validation';
					styleColor ='table-warning';
				}
				else if (obj[index].statut === 'attente_prise_en_compte') {
					badgeColor = 'text-primary';
					badgeText = 'En attente de prise en compte';
					styleColor ='table-dark';
				}
				
				
				var row = '<tr class="'+styleColor+'"> <td>' + obj[index].numero_courrier + '/' + obj[index].numero_courrier + '</td><td>' + obj[index].date + ' '+ obj[index].heure +'</td><td>' + obj[index].objet + '</td><td>' + obj[index].site + '</td><td><span class="badge ' + badgeColor + '">' + badgeText + '</span></td></tr>';
				$(row).appendTo('#table-instructions-dashboard tbody');
				index++;
			});
		},
		error: function(data) {

		}

	});

	//////////////////////////////////////////////////////////
	$.ajax({
		type: "GET",
		url: "/sirh-pnc/newInstructions",
		data: "json",
		contentType: "application/json",
		success: function(data) {

			let obj = $.parseJSON(data);
			$.each(obj, function(key, value) {
				////console.log("Message : "+value.message);

				//$('#activite_message').append('<h5>' + value.message + '</h5>');
				$('#notification_new_instruction_list').append("<li class='notification-item'><i class='bi bi-exclamation-circle text-warning'></i><div><h4>Courrier No." + value.numero_courrier + "</h4><p>Numero indicateur: " + value.indicateur + "</p><p>" + value.date + "</p></div></li><li><hr class='dropdown-divider'></li>");

				//var li = "<li class='notification-item'><i class='bi bi-exclamation-circle text-warning'></i><div><h4>Courrier No." + value.numero_courrier + "</h4><p>Numero indicateur: " + value.indicateur + "</p><p>" + value.date + "</p></div></li><li><hr class='dropdown-divider'></li>"
				$//(li).appendTo('#activite_message');
			});
		},
		error: function(data) {
			$('#notification_new_instruction_list').append('<h5>Aucun processus metier disponible</h5>');
		}

	});

});
