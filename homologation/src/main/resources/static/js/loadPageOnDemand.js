$(document).ready(function() {

	$('#diagrammeSituationGenerale').load('/sirh-pnc/diagrammeSituationGenerale');
	
	$('#btnSituationGenerale').on('click', function() {
		$('#diagrammeRapprochementPaie').empty();
		$('#diagrammeDetailContentieux').empty();
		$('#diagrammeSituationGenre').empty();
		$('#diagrammeSituationGrade').empty();
		$('#diagrammeRetraitable').empty();
		$('#diagrammeSituationGenerale').load('/sirh-pnc/diagrammeSituationGenerale');
		//loadDatas('Données conformes');
	});
	$('#btnRapprochementFichierPaie').on('click', function() {
		$('#diagrammeSituationGenerale').empty();
		$('#diagrammeDetailContentieux').empty();
		$('#diagrammeSituationGenre').empty();
		$('#diagrammeSituationGrade').empty();
		$('#diagrammeRetraitable').empty();
		$('#diagrammeRapprochementPaie').load('/sirh-pnc/diagrammeRapprochementPaie');
		//loadDatas('Données conformes');
	});
	$('#btnDetailContentieux').on('click', function() {
		$('#diagrammeSituationGenerale').empty();
		$('#diagrammeRapprochementPaie').empty();
		$('#diagrammeSituationGenre').empty();
		$('#diagrammeSituationGrade').empty();
		$('#diagrammeRetraitable').empty();
		$('#diagrammeDetailContentieux').load('/sirh-pnc/diagrammeDetailContentieux');
		//loadDatas('Données conformes');
	});
	$('#btnSituationGenre').on('click', function() {
		$('#diagrammeSituationGenerale').empty();
		$('#diagrammeRapprochementPaie').empty();
		$('#diagrammeDetailContentieux').empty();
		$('#diagrammeSituationGrade').empty();
		$('#diagrammeRetraitable').empty();
		$('#diagrammeSituationGenre').load('/sirh-pnc/diagrammeSituationGenre');
		//loadDatas('Données conformes');
	});
	$('#btnSituationGrade').on('click', function() {
		$('#diagrammeSituationGenerale').empty();
		$('#diagrammeRapprochementPaie').empty();
		$('#diagrammeDetailContentieux').empty();
		$('#diagrammeSituationGenre').empty();
		$('#diagrammeRetraitable').empty();
		$('#diagrammeSituationGrade').load('/sirh-pnc/diagrammeSituationGrade');
		//loadDatas('Données conformes');
	});
	$('#btnEligibleRetraite').on('click', function() {
		$('#diagrammeSituationGenerale').empty();
		$('#diagrammeRapprochementPaie').empty();
		$('#diagrammeDetailContentieux').empty();
		$('#diagrammeSituationGenre').empty();
		$('#diagrammeSituationGrade').empty();
		$('#diagrammeRetraitable').load('/sirh-pnc/diagrammeRetraitable');
		//loadDatas('Données conformes');
	});
	
	
})