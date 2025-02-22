$(document).ready(function() {
	$('#totalValideSpinner').show();
	$('#totalValideContentieuxSpinner').show();
	$('#totalATraiterSpinner').show();


	$('#btnValides').on('click', function() {
		$('#divContentieux').empty();
		$('#divValides').load('/sirh-pnc/page/histo/valide');

		loadDatas('VALIDE');
		//$('#divValides').hide();
		//$('#divValides').show();

	});
	$('#btnContentieux').on('click', function() {
		$('#divValides').empty();
		$('#divContentieux').load('/sirh-pnc/page/histo/contentieux');

		loadDatas('CONTENTIEUX');
		//loadContentieux();
		//$('#divValides').hide();
		//$('#divValides').show();

	});

	function refreshTotalPolicier() {

		var xhr = new XMLHttpRequest();
		xhr.onload = function() {
			if (xhr.status === 200) {
				console.log("response : " + xhr.responseText);
				var data = xhr.responseText;
				let obj = $.parseJSON(data);


				var totalContentieux = parseInt(obj[0].homme.contentieux) + parseInt(obj[1].femme.contentieux);
				var totalValide = parseInt(obj[0].homme.valide) + parseInt(obj[1].femme.valide);


				Highcharts.chart('containerSituationGenerale', {
								chart: {
									type: 'column'
								},
								title: {
									text: '',
									align: 'left'
								},
								xAxis: {
									categories: ['VALIDE', 'CONTENTIEUX'],
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
									}
								},
								series: [{
									name: 'Hommes',
									data: [
										obj[0].homme.valide,
										obj[0].homme.contentieux],
									color: '#1E90FF'

								}, {
									name: 'Femmes',
									data: [
										obj[1].femme.valide,
										obj[1].femme.contentieux],
									color: '#FF1E90'

								}]
							});


			}
		};
		xhr.open('GET', '/sirh-pnc/controlStatus', true);
		xhr.send(null);
	}
	//setInterval(refreshTotalPolicier, 1000 * 10);

	var totalContentieux;
	var totalValide;

	$.ajax({
		type: "GET",
		url: "/sirh-pnc/controlStatus",
		data: "json",
		contentType: "application/json",
		success: function(data) {
			$('#totalValideSpinner').hide();
			$('#totalValideContentieuxSpinner').hide();

			let obj = $.parseJSON(data);
			/*$('#nbHommeValide').html(toCommas(obj[0].homme.valide));
			$('#nbHommeContentieux').html(toCommas(obj[0].homme.contentieux));


			$('#nbFemmeValide').html(toCommas(obj[1].femme.valide));
			$('#nbFemmeContentieux').html(toCommas(obj[1].femme.contentieux));*/

			totalContentieux = parseInt(obj[0].homme.contentieux) + parseInt(obj[1].femme.contentieux);
			totalValide = parseInt(obj[0].homme.valide) + parseInt(obj[1].femme.valide);
			
			$('#totalContentieuxValides').html('Contentieux : '+toCommas(parseInt(totalContentieux))+', validés : '+toCommas(parseInt(totalValide)));

			var totalFiche = parseInt(totalContentieux) + parseInt(totalValide);
			$('#totalFiche').html(toCommas(totalFiche));

			/*$('#totalContentieux').html(toCommas(totalContentieux));
			$('#totalValide').html(toCommas(totalValide));*/
			//const maleColor = '#1E90FF',femaleColor = '#FF1E90';

			Highcharts.chart('containerPieSituationGenerale', {
				chart: {
					plotBackgroundColor: null,
					plotBorderWidth: null,
					plotShadow: false,
					type: 'pie'
				},
				title: {
					text: 'Repartition  generale  des valides et non-valides(Contentieux)'
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
								name: 'VALIDES',
								y: totalValide,
								color: '#1E90FF'
							},
							{
								name: 'CONTENTIEUX',
								sliced: true,
								selected: true,
								y: totalContentieux,
								color: '#FF1E90'
							}
						]
					}
				]
			});

			Highcharts.chart('containerSituationGenerale', {
				chart: {
					type: 'column'
				},
				title: {
					text: '',
					align: 'left'
				},
				xAxis: {
					categories: ['VALIDE', 'CONTENTIEUX'],
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
					}
				},
				series: [{
					name: 'Hommes',
					data: [
						obj[0].homme.valide,
						obj[0].homme.contentieux],
					color: '#1E90FF'

				}, {
					name: 'Femmes',
					data: [
						obj[1].femme.valide,
						obj[1].femme.contentieux],
					color: '#FF1E90'

				}]
			});
		},

		////

	});
	////////////////////////////////////////
	//loadDatas('VALIDE');
	////////////////////////////////
	//loadContentieux();

})

function loadDatas(decision) {
	//var decision = 'VALIDE';
	var myURL = "/sirh-pnc/generaleVerificationValidation?decision=" + decision;
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
					$('<button type="button" data-bs-target="#carouselSituationGenerale" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 0"></button>').appendTo('.carousel-indicators');
				} else {
					$('<button type="button" data-bs-target="#carouselSituationGenerale" data-bs-slide-to="' + i + '" aria-label="Slide ' + i + '"></button>').appendTo('.carousel-indicators');
				}
			}
			for (var i = 0; i < nbPages; i++) {
				if (i === 0) {
					$('<div class="carousel-item active"><div id="containerCarrousel' + i + '"><h5 style="display:none">Slide ' + i + '</h5></div></div>').appendTo('.carousel-inner')
				} else {
					$('<div class="carousel-item"><div id="containerCarrousel' + i + '"><h5 style="display:none">Slide ' + i + '</h5></div></div>').appendTo('.carousel-inner')
				}
			}
			var series = [{ name: decision, data: datas[0].data }];
			Highcharts.chart('containerCarrousel0', {
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
				myURL = "/sirh-pnc/generaleVerificationValidation?decision=" + decision + "&page=" + event.to;
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

							var series = [{ name: decision, data: datas[0].data ,color: '#FF1E90'}];					

							Highcharts.chart('containerCarrousel' + event.to + '', {
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
									},
									series: {
										cursor: 'pointer',
										point: {
											events: {
												click: function() {

													$('#bodyTableSituationGenerale').empty();;
													$('#statutTraitement').text(decision);
													$('#modalSituationGenerale').modal('show');
													$('#titleSituationGenerale').text(this.category + ', effectif : ' + this.y);
													var url = "/sirh-pnc/detailSituationGenerale?statut=" + decision + "&structure=" + this.category + "&page=" + event.to;

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
																var row = '<tr> <td>' + (index + 1) + '</td><td>' + datas[index].matricule + '</td><td>' + datas[index].nom + '</td><td>' + datas[index].postnom + '</td><td>' + datas[index].prenom + '</td><td>' + datas[index].sexe + '</td><td>' + datas[index].grade + '</td><td>' + decision + '</td><td>' + datas[index].structure + '</td></tr>';
																$(row).appendTo('#tableSituationGenerale tbody');
																index++;
															});
															var structureDesignation = datas[0].structure;
															var downloadUrl = "/sirh-pnc/admin/rapport/downloadDiagrammeSituationGenerale?statut=" + decision + "&structure=" + structureDesignation;
															$('#linkDownloadSituationGenerale').attr("href", downloadUrl)
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