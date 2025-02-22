$(document).ready(function() {

	loadPieChart();
	$('#totalValideSpinner').show();
	$('#totalValideContentieuxSpinner').show();
	$('#totalATraiterSpinner').show();

	$('#btnDonneesConformes').on('click', function() {
		$('#divDonneesNonConformes').empty();
		$('#divFichesNonRemplies').empty();
		$('#divFichesNonRetournees').empty();
		$('#divDonneesConformes').load('/sirh-pnc/page/histo/contentieux/donneesConformes');
		loadDatas('Données conformes');
	});
	$('#btnDonneesNonConformes').on('click', function() {
		$('#divDonneesConformes').empty();
		$('#divFichesNonRemplies').empty();
		$('#divFichesNonRetournees').empty();
		$('#divDonneesNonConformes').load('/sirh-pnc/page/histo/contentieux/donneesNonConformes');
		loadDatas('Données non conformes');
	});
	$('#btnFichesNonRemplies').on('click', function() {
		$('#divDonneesConformes').empty();
		$('#divDonneesNonConformes').empty();
		$('#btnFichesNonRetournees').empty();
		$('#divFichesNonRemplies').load('/sirh-pnc/page/histo/contentieux/fichesNonRemplies');
		loadDatas('Fiche non remplie');
	});
	$('#btnFichesNonRetournees').on('click', function() {
		$('#divDonneesConformes').empty();
		$('#divDonneesNonConformes').empty();
		$('#btnFichesNonRemplies').empty();
		$('#divFichesNonRetournees').load('/sirh-pnc/page/histo/contentieux/fichesNonRetournees');
		loadDatas('Fiche non retournée');
	});

})
function loadPieChart() {
	$.ajax({
		type: "GET",
		url: "/sirh-pnc/totalDetailContentieux",
		data: "json",
		contentType: "application/json",
		success: function(data) {

			let obj = $.parseJSON(data);

			var totalDonneesNonConformes = parseInt(obj[0].donnees_non_conformes);
			var totalDonneesConformes = parseInt(obj[1].donnees_conformes);
			var totalFicheNonRemplie = parseInt(obj[2].fiche_non_remplie);
			var totalFicheNonRetournee = parseInt(obj[3].fiche_non_retournee);
			


			$('#totalDetailContentieux').html('Données conformes: ' + toCommas(parseInt(totalDonneesConformes)) + ', Données non conformes: ' + toCommas(parseInt(totalDonneesNonConformes)) + ', Fiches non retournees: ' + toCommas(parseInt(totalFicheNonRetournee)) + ', Fiches non remplies: ' + toCommas(parseInt(totalFicheNonRemplie)));

			console.log(obj);


			Highcharts.chart('containerPieDetailContentieux', {
				chart: {
					plotBackgroundColor: null,
					plotBorderWidth: null,
					plotShadow: false,
					type: 'pie'
				},
				title: {
					text: 'Situation détaillée de cas de contentieux'
				},
				tooltip: {
					formatter: function() {
						return '<b>' + this.point.name + '</b>: ' + Math.round(this.percentage) + ' %';
					}
				},
				subtitle: {
					text:
						'Source:Ressources Humaines'
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
				series: [
					{
						name: 'Total',
						colorByPoint: true,
						data: [
							{
								name: 'DONNEES CONFORMES',
								y: totalDonneesConformes,
								color: '#1E90FF'
							},

							{
								name: 'DONNEES NON CONFORMES',
								sliced: true,
								selected: true,
								y: totalDonneesNonConformes,
								color: '#FF1E90'
							},
							{
								name: 'FICHES NON REMPLIES',
								y: totalFicheNonRemplie,

							},
							{
								name: 'FICHES NON RETOURNEES',
								y: totalFicheNonRetournee,

							}
						]
					}
				]
			});

			////////////////////

			Highcharts.chart('containerHistogrammeDetailContentieux', {
				chart: {
					type: 'column'
				},
				title: {
					text: '',
					align: 'left'
				},
				xAxis: {
					categories: ['DONNEES CONFORMES', 'DONNEES NON CONFORMES','FICHES NON REMPLIES', 'FICHES NON RETOURNEES'],
					crosshair: true,
					accessibility: {
						description: 'Countries'
					}
				},
				yAxis: {
					min: 0,
					title: {
						text: ''
					}
				},
				legend: {
					reversed: true
				},
				plotOptions: {
					column: {
						pointPadding: 0.2,
						borderWidth: 0
					},
					series: {
						cursor: 'pointer',

					}
				},
				series: [
					{
						name: 'Total',
						colorByPoint: true,
						data: [
							{
								name: 'DONNEES CONFORMES',
								y: totalDonneesConformes,
								color: '#1E90FF'
							},

							{
								name: 'DONNEES NON CONFORMES',
								sliced: true,
								selected: true,
								y: totalDonneesNonConformes,
								color: '#FF1E90'
							},
							{
								name: 'FICHES NON REMPLIES',
								y: totalFicheNonRemplie,

							},
							{
								name: 'FICHES NON RETOURNEES',
								y: totalFicheNonRetournee,

							}
						]
					}
				]
			});

		},

		////

	});
}
function loadDatas(statut) {
	var myURL = "/sirh-pnc/detailsContentieux?statut=" + statut;
	$.ajax({
		type: "GET",
		url: myURL,
		data: "json",
		contentType: "application/json",
		success: function(result) {

			let objRes = $.parseJSON(result);

			var categories = objRes[0];
			var datas = objRes[1];


			console.log(categories);
			console.log(datas);

			var nbPages = Math.ceil(datas[0].total / datas[0].size);

			for (var i = 0; i < nbPages; i++) {
				if (i === 0) {
					$('<button type="button" data-bs-target="#carouselSituationContentieux" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 0"></button>').appendTo('.carousel-indicators');
				} else {
					$('<button type="button" data-bs-target="#carouselSituationContentieux" data-bs-slide-to="' + i + '" aria-label="Slide ' + i + '"></button>').appendTo('.carousel-indicators');
				}
			}
			for (var i = 0; i < nbPages; i++) {
				if (i === 0) {
					$('<div class="carousel-item active"><div id="containerCarrouselContentieux' + i + '"><h5 style="display:none">Slide ' + i + '</h5></div></div>').appendTo('.carousel-inner')
				} else {
					$('<div class="carousel-item"><div id="containerCarrouselContentieux' + i + '"><h5 style="display:none">Slide ' + i + '</h5></div></div>').appendTo('.carousel-inner')
				}
			}
			var series = [{ name: statut, data: datas[0].data }];
			Highcharts.chart('containerCarrouselContentieux0', {
				chart: {
					type: 'column'
				},
				title: {
					text: '',
					align: 'left'
				},
				xAxis: {
					categories: categories,
					crosshair: true,
					accessibility: {
						description: 'Countries'
					}
				},
				yAxis: {
					min: 0,
					title: {
						text: ''
					}
				},
				legend: {
					reversed: true
				},
				plotOptions: {
					column: {
						pointPadding: 0.2,
						borderWidth: 0
					},
					series: {
						cursor: 'pointer',
						point: {
							events: {
								click: function() {
									alert(
										'Category: ' + this.category + ', value: ' + this.y
									);
								}
							}
						}
					}
				},
				series: series
			});

			///////
			var carouselEl = $('.carousel');
			carouselEl.carousel({
				interval: 7000
			}).on('slid.bs.carousel', function onSlide(event) {
				myURL = "/sirh-pnc/detailsContentieux?statut=" + statut + "&page=" + event.to;
				var id = event.to;
				console.log("Current item position is : " + event.to);
				console.log("myURL : " + myURL);

				if (event.to < nbPages) {
					//currentIndex++;
					let singleCategorie = [];


					$.ajax({
						type: "GET",
						url: myURL,
						data: "json",
						contentType: "application/json",
						success: function(result) {

							let objRes = $.parseJSON(result);

							var categories = objRes[0];
							var datas = objRes[1];

							var series = [{ name: statut, data: datas[0].data }];

							Highcharts.chart('containerCarrouselContentieux' + event.to + '', {
								chart: {
									type: 'column'
								},
								title: {
									text: '',
									align: 'left'
								},
								xAxis: {
									categories: categories,
									crosshair: true,
									accessibility: {
										description: ''
									}
								},
								yAxis: {
									min: 0,
									title: {
										text: ''
									}
								},
								legend: {
									reversed: true
								},
								plotOptions: {
									column: {
										pointPadding: 0.2,
										borderWidth: 0
									}, series: {
										cursor: 'pointer',
										point: {
											events: {
												click: function() {

													$('#bodyTableDetailContentieux').empty();;
													$('#statutDetailContentieux').text(statut);
													$('#modalDetailContentieux').modal('show');
													$('#titleDetailContentieux').text(this.category + ', effectif : ' + this.y);
													var url = "/sirh-pnc/detailContentieux?statut=" + statut + "&structure=" + this.category + "&page=" + event.to;

													$.ajax({
														type: "GET",
														url: url,
														data: "json",
														contentType: "application/json",
														success: function(result) {
															let obj = JSON.parse(result);
															let datas = obj.data;
															var index = 0;
															$.each(datas, function(key, value) {
																var row = '<tr> <td>' + (index + 1) + '</td><td>' + datas[index].matricule + '</td><td>' + datas[index].nom + '</td><td>' + datas[index].postnom + '</td><td>' + datas[index].prenom + '</td><td>' + datas[index].sexe + '</td><td>' + datas[index].grade + '</td><td>' + statut + '</td><td>' + datas[index].structure + '</td></tr>';
																$(row).appendTo('#tableDetailContentieux tbody');
																index++;
															});
															var structureDesignation = datas[0].structure;
															var downloadUrl = "/sirh-pnc/admin/rapport/downloadDiagrammeDetailContentieux?statut=" + statut + "&structure=" + structureDesignation;
															$('#linkDownloadDetailContentieux').attr("href", downloadUrl)

														}
													});
													/*alert(
														'Category: ' + this.category + ', value: ' + this.y
													);*/
												}
											}
										}
									}
								},
								series: series
							});
						}
					});
					;
				}

			});

		},
	});

}

function toCommas(value) {
	return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
function updateUrl(url, key, value) {
	if (value !== undefined) {
		value = encodeURI(value);
	}
	var hashIndex = url.indexOf("#") | 0;
	if (hashIndex === -1) hashIndex = url.length | 0;
	var urls = url.substring(0, hashIndex).split('?');
	var baseUrl = urls[0];
	var parameters = '';
	var outPara = {};
	if (urls.length > 1) {
		parameters = urls[1];
	}
	if (parameters !== '') {
		parameters = parameters.split('&');
		for (k in parameters) {
			var keyVal = parameters[k];
			keyVal = keyVal.split('=');
			var ekey = keyVal[0];
			var evalue = '';
			if (keyVal.length > 1) {
				evalue = keyVal[1];
			}
			outPara[ekey] = evalue;
		}
	}

	if (value !== undefined) {
		outPara[key] = value;
	} else {
		delete outPara[key];
	}
	parameters = [];
	for (var k in outPara) {
		parameters.push(k + '=' + outPara[k]);
	}

	var finalUrl = baseUrl;

	if (parameters.length > 0) {
		finalUrl += '?' + parameters.join('&');
	}

	return finalUrl + url.substring(hashIndex);
}