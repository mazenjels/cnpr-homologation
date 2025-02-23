<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="formModalFilterPayment" tabindex="-1"
	data-bs-backdrop="false">
	<div class="modal-dialog modal-lg">

		<div class="modal-content">

			<div class="modal-header">
				<h5 class="modal-title">Filtrer les paiements</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="card">

				<div class="card-body">
					<form action="">

						<div class="row">
							<div class="col-lg-8">
								<div class="form-floating  mb-3">
									<input type="text" class="form-control" required="required"
										name="searchQuery"
										placeholder="Saisissez la référence du paiement" /> <label
										for="reference">Code du candidat, reference ou ID
										transaction</label>
										 <button type="submit" value="Register"
									class="btn btn-outline-success">
									<i class="bi bi-search"></i>
								</button>
								</div>
								<!-- <button type="submit" value="Register"
									class="btn btn-outline-success">
									<i class="bi bi-search"></i>
								</button> -->
							</div>

							<!-- <div class="col-lg-2">
								<button type="submit" value="Register"
									class="btn btn-outline-success">
									<i class="bi bi-search"></i>
								</button>
							</div> -->

						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Fermer</button>

				</div>

			</div>
		</div>


	</div>
</div>
</div>