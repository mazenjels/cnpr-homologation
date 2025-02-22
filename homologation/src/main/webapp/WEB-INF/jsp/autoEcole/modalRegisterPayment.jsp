<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="formModalRegisterPayment" tabindex="-1"
	data-bs-backdrop="false">
	<div class="modal-dialog modal-lg">

		<div class="modal-content">
			<form method="post" action="/cnpr-homologation/payment/save">
				<div class="card">
					<div class="card-body">
						<div class="modal-header">
							<h5 class="modal-title">Enregistrement du paiement -
								${payment.autoEcole.designation}</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-floating  mb-3">
									<input type="text" class="form-control" required="required"
										name="reference"
										placeholder="Saisissez la référence du paiement" /> <label
										for="reference">Saisissez la référence du paiement</label>

								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-floating  mb-3">
									<input type="text" class="form-control" required="required"
										name="motif"
										placeholder="Motif de paiement" /> <label
										for="motif">Motif de paiement</label>

								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-floating  mb-3">
									<input type="text" class="form-control" required="required"
										name="transactionId"
										placeholder="ID de la transaction" /> <label
										for="transactionId">ID de la transaction</label>

								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-floating  mb-3">
									<input type="text" class="form-control" required="required"
										name="candidatCode"
										placeholder="Code unique du candidat" /> <label
										for="candidatCode">Code unique du candidat</label>

								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-floating  mb-3">
									<input type="number" min="10" class="form-control" required="required"
										name="amount" placeholder="Montant" /> <label for="amount">Montant</label>

								</div>
							</div>
							<div class="col-md-6">
								<div class="form-floating mb-3">
									<select class="form-select" name="currencyCode"
										aria-label="State">
										<option value="CDF">CDF</option>
										<option value="USD">USD</option>
									</select> <label for="currencyCode">Devise</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-floating mb-3">
									<select class="form-select" name="paymentMode"
										aria-label="State">
										<c:forEach var="paymentMode" items="${paymentModeList}">
											<option value="${paymentMode.id}">${paymentMode.designation}</option>
										</c:forEach>
									</select> <label for="paymentMode">Mode de paiement</label>
								</div>
							</div>

						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-floating  mb-3">
									<input type="text" class="form-control" required="required"
										name="bankBranch"
										placeholder="Agence ou point de paiement" /> <label
										for="bankBranch">Agence ou point de paiement</label>

								</div>
							</div>
						</div>
						<div class="modal-footer">
							<h6></h6>

							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Annuler</button>
							<button type="submit" class="btn btn-primary "
								data-bs-dismiss="modal">Valider</button>
						</div>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>