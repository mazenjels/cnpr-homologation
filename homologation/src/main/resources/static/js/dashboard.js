/**
 * 
 */

$(document).ready(function() {

	$.ajax({
		type: "GET",
		url: "/cnpr-homologation/cnpr/api/dashboard-summary",
		data: "json",
		contentType: "application/json",
		success: function(data) {
			let obj = $.parseJSON(data);
			console.log(obj);
			if (obj.total_cdf === null) {
				$('#totalCdf').html('0');
			} else {
				$('#totalCdf').html(toCommas(obj.total_cdf));
			}
			if (obj.total_usd === null) {
				$('#totalUsd').html('0');
			} else {
				$('#totalUsd').html(toCommas(obj.total_usd));
			};
			if (obj.total_candidats_inscrits === null) {
				$('#totalCandidatsInscrits').html('0');
			} else {
				$('#totalCandidatsInscrits').html(toCommas(obj.total_candidats_inscrits));
			};
			$('#totalAutoEcole').html(toCommas(obj.auto_ecole));
			$('#totalHomologations').html(obj.total_hommologation);

			/*
						Highcharts.chart('personneSalarieStatut', {
							chart: {
								plotBackgroundColor: null,
								plotBorderWidth: null,
								plotShadow: false,
								type: 'pie'
							},
							title: {
								text: 'Repartion selon le statut de paie'
							},
			
							subtitle: {
								text: 'Source: Base de paie'
							},
							tooltip: {
								formatter: function() {
									return '<b>' + this.point.name + '</b>: ' + Math.round(this.percentage) + ' %';
								}
							},
							accessibility: {
								point: {
									//valueSuffix: '%'
								}
							},
							plotOptions: {
								pie: {
									allowPointSelect: true,
									cursor: 'pointer',
									dataLabels: {
										enabled: true,
										formatter: function() {
											return '<b>' + this.point.name + '</b>: ' + Math.round(this.percentage) + ' %';
										}
									}
								}
							},
							series: [{
								name: 'Effectif',
								colorByPoint: true,
								data: [{
									name: 'Payes',
									y: obj.payes,
									sliced: true,
									selected: true
								}, {
									name: 'Non payes',
									y: obj.nonPayes
								},]
							}]
						});
			
						Highcharts.chart('personneSalarieGenre', {
							chart: {
								plotBackgroundColor: null,
								plotBorderWidth: null,
								plotShadow: false,
								type: 'pie'
							},
							title: {
								text: 'Repartion selon le genre'
							},
							subtitle: {
								text: 'Source: Ressources Humaines'
							},
							tooltip: {
								pointFormat: '{series.name}: <b>{point.percentage}</b>'
							},
							tooltip: {
								formatter: function() {
									return '<b>' + this.point.name + '</b>: ' + Math.round(this.percentage) + ' %';
								}
							},
							accessibility: {
								point: {
									//valueSuffix: '%'
								}
							},
							plotOptions: {
								pie: {
									allowPointSelect: true,
									cursor: 'pointer',
									dataLabels: {
										enabled: true,
										format: '<b>{point.name}</b>: {point.percentage}'
										formatter: function() {
											return '<b>' + this.point.name + '</b>: ' + Math.round(this.percentage) + ' %';
										}
									}
								}
							},
							series: [{
								name: 'Effectif',
								colorByPoint: true,
								data: [{
									name: 'Hommes',
									y: obj.nbHomme,
									sliced: true,
									selected: true
								}, {
									name: 'Femmes',
									y: obj.nbFemme
								}, {
									name: 'Non definis',
									y: obj.nbUndefined
								},]
							}]
						});*/

		},

	});

	////////////////////////////////////////////////


	////////////////////////////////////////////////
	function toCommas(value) {
		return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}


});


