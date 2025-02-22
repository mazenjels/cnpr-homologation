$(document).ready(function() {

	loadPieChart();
	$('#totalValideSpinner').show();
	$('#totalValideContentieuxSpinner').show();
	$('#totalATraiterSpinner').show();

	$('#btnPayes').on('click', function() {
		$('#divNonPayes').empty();
		$('#divPayes').load('/sirh-pnc/page/histo/payes');
		loadDatas('PAYE');
	});
	$('#btnNonPayes').on('click', function() {
		$('#divPayes').empty();
		$('#divNonPayes').load('/sirh-pnc/page/histo/nonPayes');
		loadDatas('NON PAYE');
	});

})
function loadPieChart() {
	$.ajax({
		type: "GET",
		url: "/sirh-pnc/totalRapprochementValideStatutPaie",
		data: "json",
		contentType: "application/json",
		success: function(data) {

			let obj = $.parseJSON(data);

			var totalPayes = parseInt(obj[0].payes);
			var totalNonPayes = parseInt(obj[1].nonPayes);

			$('#totalRapprochement').html('Payés : '+toCommas(parseInt(totalPayes))+', Non payés : '+toCommas(parseInt(totalNonPayes)));

			console.log(obj);

			Highcharts.chart('containerPieRapprochement', {
				chart: {
					plotBackgroundColor: null,
					plotBorderWidth: null,
					plotShadow: false,
					type: 'pie'
				},
				title: {
					text: 'Rapprochement des validés avec le Fichier Paie'
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
						name: 'Percentage',
						colorByPoint: true,
						data: [
							{
								name: 'PAYES',
								y: totalPayes,
								color: '#1E90FF'
							},
							{
								name: 'NON PAYES',
								sliced: true,
								selected: true,
								y: totalNonPayes,
								color: '#FF1E90'
							}
						]
					}
				]
			});

			////////////////////

			Highcharts.chart('containerHistogrammeRapprochement', {
				chart: {
					type: 'column'
				},
				title: {
					text: '',
					align: 'left'
				},
				xAxis: {
					categories: ['PAYES', 'NON PAYES'],
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
								name: 'PAYES',
								y: totalPayes,
								color: '#1E90FF'
							},
							{
								name: 'NON PAYES',
								sliced: true,
								selected: true,
								y: totalNonPayes,
								color: '#FF1E90'
							},
						]
					}
				]
			});
		},

		////

	});
}
function loadDatas(statut) {
	/*var st = 'PAYE';
	var str = 'SERVICE INFORMATIQUE';
	var source = {
		datatype: "json",
		datafields: [{
			name: 'Matricule',
			type: 'string'
		}, {
			name: 'Nom',
			type: 'string'
		}, {
			name: 'Postnom',
			type: 'string'
		}, {
			name: 'Prenom',
			type: 'string'
		}, {
			name: 'Sexe',
			type: 'string'
		}, {
			name: 'Structure',
			type: 'string'
		}],
		cache: false,
		url: "/sirh-pnc/detailRapprochement?statut=" + st + "&structure=" + str,
		filter: function() {
			// update the grid and send a request to the server.
			$("#jqxgrid").jqxGrid('updatebounddata', 'filter');
		},
		sort: function() {
			// update the grid and send a request to the server.
			$("#jqxgrid").jqxGrid('updatebounddata', 'sort');
		},
		beforeprocessing: function(data) {
			if (data != null && data.length > 0) {
				source.totalrecords = data[0].totalRecords;
			}
		}
	};
	var filterChanged = false;
	var dataadapter = new $.jqx.dataAdapter(source, {
		
		// remove the comment to debug
		formatData: function(data) {
			alert(JSON.stringify(data));
			return data;
		},
		downloadComplete: function(data, status, xhr) {
			if (!source.totalRecords) {
				source.totalRecords = data.length;
			}
		},
		loadError: function(xhr, status, error) {
			throw new Error(error);
		}
	});

	// initialize jqxGrid
	$("#jqxgrid").jqxGrid({
		width: 550,
		source: dataadapter,
		filterable: true,
		sortable: true,
		autoheight: true,
		pageable: true,
		pagesize: 3,
		pagesizeoptions: ['3', '4', '5'],
		virtualmode: true,
		rendergridrows: function(obj) {
			let obj1 = JSON.parse(obj);
			//let datas = obj1.data;
			return obj1.data;
		},
		columns: [{
			text: 'Matricule',
			datafield: 'matricule',
			width: 100
		}, {
			text: 'Postnom',
			datafield: 'postnom',
			width: 100
		}, {
			text: 'Prenom',
			datafield: 'prenom',
			width: 180
		}, {
			text: 'Sexe',
			datafield: 'sexe',
			align: 'right',
			cellsalign: 'right'
		}, {
			text: 'Structure',
			datafield: 'structure',
			align: 'right',
			cellsalign: 'right'
		}]
	});*/

	var myURL = "/sirh-pnc/rapprochementValideStatutPaie?statutPaie=" + statut;
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
					$('<button type="button" data-bs-target="#carouselSituationPaie" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 0"></button>').appendTo('.carousel-indicators');
				} else {
					$('<button type="button" data-bs-target="#carouselSituationPaie" data-bs-slide-to="' + i + '" aria-label="Slide ' + i + '"></button>').appendTo('.carousel-indicators');
				}
			}
			for (var i = 0; i < nbPages; i++) {
				if (i === 0) {
					$('<div class="carousel-item active"><div id="containerCarrouselPaie' + i + '"><h5 style="display:none">Slide ' + i + '</h5></div></div>').appendTo('.carousel-inner')
				} else {
					$('<div class="carousel-item"><div id="containerCarrouselPaie' + i + '"><h5 style="display:none">Slide ' + i + '</h5></div></div>').appendTo('.carousel-inner')
				}
			}
			var series = [{ name: statut, data: datas[0].data }];
			Highcharts.chart('containerCarrouselPaie0', {
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
				myURL = "/sirh-pnc/rapprochementValideStatutPaie?statutPaie=" + statut + "&page=" + event.to;
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

							Highcharts.chart('containerCarrouselPaie' + event.to + '', {
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

													$('#bodyTableRapprochement').empty();;
													$('#statutRapprochement').text(statut);
													$('#modalRapprochement').modal('show');
													$('#titleRapprochement').text(this.category + ', effectif : ' + this.y);
													var url = "/sirh-pnc/detailRapprochement?statut=" + statut + "&structure=" + this.category + "&page=" + event.to;

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
																$(row).appendTo('#tableRapprochement tbody');
																index++;
															});
															var structureDesignation = datas[0].structure;
															var downloadUrl = "/sirh-pnc/admin/rapport/downloadDiagrammeRapprochement?statut=" + statut + "&structure=" + structureDesignation;
															$('#linkDownloadRapprochement').attr("href", downloadUrl)

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