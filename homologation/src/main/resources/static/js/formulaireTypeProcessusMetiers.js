/**
 * 
 */
$(document).ready(function() {

	const months = [
		"Janvier",
		"Fevrier",
		"Mars",
		"Avril",
		"Mai",
		"Juin",
		"Juillet",
		"Aout",
		"Septembre",
		"Octobre",
		"Novembre",
		"Decembre",
	];


	$('.btnModalRegisterPayment').on('click', function() {

			$('#formModalRegisterPayment').modal('show');
			/*$tr = $(this).closest('tr');
			var data = $tr.children("td").map(function() {
				return $(this).text();
			}).get();
			const monthName = months[new Date().getMonth()];*/
			$('#formModalregisterPaymentTitle').html('Enregisterement du paiement');
		});


	

})