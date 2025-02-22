$(document).ready(function() {

	$('#divTableSituationGenre').hide();
	loadPieChart();
	loadAgesPyramid();
	$('#totalValideSpinner').show();
	$('#totalValideContentieuxSpinner').show();
	$('#totalATraiterSpinner').show();

	
	/*$('#btnPyramideAge').on('click', function() {
		$('#divPyramideAge').load('/sirh-pnc/page/histo/pyramideAge');
		loadDatas('25-44');
	});*/
})
function loadPieChart() {
	$.ajax({
		type: "GET",
		url: "/sirh-pnc/totalSituationGenre",
		data: "json",
		contentType: "application/json",
		success: function(data) {

			let obj = $.parseJSON(data);

			var totalHomme = parseInt(obj[0].Homme);
			var totalFemme = parseInt(obj[1].Femme);


			$('#totalGenre').html('Hommes : '+toCommas(parseInt(totalHomme))+', Femmes : '+toCommas(parseInt(totalFemme)));
			console.log(obj);

			Highcharts.chart('containerPieGenre', {
				chart: {
					plotBackgroundColor: null,
					plotBorderWidth: null,
					plotShadow: false,
					type: 'pie'
				},
				title: {
					text: 'Situation des genres'
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
								name: 'HOMMES',
								y: totalHomme,
								color: '#1E90FF'
							},
							{
								name: 'FEMME',
								sliced: true,
								selected: true,
								y: totalFemme,
								color: '#FF1E90'
							}
						]
					}
				]
			});



		},

		////

	});
}
function loadAgesPyramid() {

	$.ajax({
		type: "GET",
		url: "/sirh-pnc/situationGenre",
		data: "json",
		contentType: "application/json",
		success: function(data) {
			let obj = $.parseJSON(data);
			//var categories = obj[0];
			console.log(obj);
			const datas = obj[1];
			const hommeData = obj[1][1].Homme;
			const femmeData = obj[1][0].Femme;



			const categories = obj[0];

			const maleColor = '#1E90FF',
				femaleColor = '#FF1E90';
			var clickedSerie;
			Highcharts.chart('containerPyramidAge', {
				chart: {
					type: 'bar'
				},
				title: {
					text: 'Pyramide des ages 2024'
				},
				subtitle: {
					text:
						'Source: Ressources Humaines'
				},
				accessibility: {
					point: {
						valueDescriptionFormat: '{index}. Age {xDescription}, {value}.'
					}
				},
				xAxis: [
					{
						categories: categories,
						reversed: false,
						labels: {
							step: 1
						},
						accessibility: {
							description: 'Age (Homme)'
						}
					},
					{
						// mirror axis on right side
						opposite: true,
						reversed: false,
						categories: categories,
						linkedTo: 0,
						labels: {
							step: 1
						},
						accessibility: {
							description: 'Age (Femme)'
						}
					}
				],
				yAxis: [{
					title: {
						text: null
					},
					labels: {
						format: '{value}'
					},
					accessibility: {
						description: 'Pourcentage de la population',
						rangeDescription: 'Tranche: 0 à 5'
					},
					width: '50%',
					reversed: true
				}, {
					title: {
						text: null
					},
					labels: {
						format: '{value}'
					},
					accessibility: {
						description: 'Pourcentage de la population',
						rangeDescription: 'Tranche: 0 à 5'
					},
					offset: 0,
					left: '50%',
					width: '50%'
				}],

				plotOptions: {
					series: {
						cursor: 'pointer',
						stacking: 'normal',
						groupPadding: 0,
						pointPadding: 0,
						events: {
							click: function() {
								clickedSerie = this.name;
								//alert('Genre : ' + this.name);
							}
						},
						point: {
							events: {
								click: function() {
									//alert('Catrgorie : ' + this.category + ', value : ' + this.y+', Genre : '+clickedSerie);
									$('#bodyTableSituationGenre').empty();;
									//$('#statutSituationGrade').text(statut);
									//$('#modalSituationGrade').modal('show');
									//$('#titleSituationGrade').text(this.category + ', effectif : ' + this.y);
									var url = "/sirh-pnc/detailTrancheAge?tranche=" + this.category + "&sexe=" + clickedSerie;
									var trancheAge = this.category;
									$.ajax({
										type: "GET",
										url: url,
										data: "json",
										contentType: "application/json",
										success: function(result) {
											$('#divTableSituationGenre').show();
											$('#titleSituationGenre').text('Tranche d\'age : '+trancheAge+', genre : '+clickedSerie);
											let obj = JSON.parse(result);
											let datas = obj.data;
											var index = 0;
											console.log(obj);
											console.log('-----')
											console.log(datas);
											$.each(datas, function(key, value) {
												var link = "/sirh-pnc/admin/personnel/detailGenre/"+trancheAge+"/"+datas[index].structure+"/"+clickedSerie;
												var row = '<tr> <td>' + (index + 1) + '</td><td>' + datas[index].structure + '</td><td>' + datas[index].total + '</td><td><a target="_blank" rel="noopener" href="'+link+'" class="btn btn-sm btn-outline-primary" id="link'+index+'"><i class="bi bi-eye-fill text-success"></i> Afficher la liste</a></td></tr>';
												//$('#link'+index).attr("href", link)
												$(row).appendTo('#tableSituationGenre tbody');
												index++;
											});
											var structureDesignation = datas[0].structure;
											var downloadUrl = "/sirh-pnc/admin/rapport/downloadDiagrammeSituationGenre?trancheAge=" + trancheAge+ "&sexe=" + clickedSerie;
											$('#linkDownloadSituationGenre').attr("href", downloadUrl)

										}
									});
								}
							}
						}
					}
				},

				tooltip: {
					headerFormat: '<b>{series.name}, age {point.key}</b><br>',
					pointFormat: 'Population: {point.y:.1f} '
				},

				series: [
					{
						name: 'Homme',
						color: maleColor,
						borderColor: '#000000',
						data: hommeData
					},
					{
						name: 'Femme',
						color: femaleColor,
						borderColor: '#000000',
						data: femmeData,
						yAxis: 1
					}
				]
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