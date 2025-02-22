<%@ include file="../templates/header.jsp"%>
<%@ include file="../templates/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Profils - permissions</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Tableau de bord</a></li>
				<li class="breadcrumb-item active"><a
					href="/cnpr-homologation/role/list">Profil</a></li>
				<li class="breadcrumb-item active">${rolePermission.role.designation}
				</li>
				<li class="breadcrumb-item active">Permissions</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section dashboard">

		<div class="row">
			<div class="col-md-2">
				<a href="/cnpr-homologation/role/list" class="btn btn-primary"><i
					class="bi bi-arrow-left"></i> Retour</a>
			</div>
		</div>
		<hr />

		<div class=row>
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Ajouter une permission au profil
							${rolePermission.role.designation}</h5>
						<form method="post" action="/cnpr-homologation/role/addPermission">

							<div class="row">
								<div class="col-md-6">
									<select name="permission" id="moduleSelector"
										class="form-control input-sm" required="required">
										<option value="-1">Selectionner le module</option>
										<c:forEach var="module" items="${moduleList}">
											<option value="${module.shortCode}">${module.designation}</option>
										</c:forEach>
									</select>
								</div>
							
								<div class="col-md-6">

<%-- 									<select name="permission" id="permissionCheckBoxes"
											class="form-control input-sm" required="required">
											 <option value="-1">Selectionner la permission</option>
											<c:forEach var="permission" items="${permissionsList}">
												<option value="${permission.id}">${permission.shortCode}</option>
											</c:forEach> 
										</select>  --%>
										<input type="hidden" name="role" value="${rolePermission.role.id}" id="role" class="form-control input-sm" required="required" />
									<div class="form-group col-md-12" id="div-checkboxes">
										<!-- <div> <input type="checkbox" name="action" value="selectionner_tout" /> <label>Selectionner tout</label> </div>
										<div> <input type="checkbox" name="action" value="selectionner_tout" /> <label>Selectionner tout</label> </div> -->
									</div>

									<p>
									<div class="col-md-2" id="divBtnAddPermission"><!--  <button type="submit" value="Register" class="btn btn-success">Enregistrer</button> --> </div>
								</div>

							</div>

						</form>

					</div>
				</div>
				<hr />

			</div>

			<div class="row"
				style="overflow-x: hidden; overflow-y: auto; height: 600px;">
				<div class="card">
					<div class="card-header">Liste des permissions</div>
					<div class="card-body">

						<form:form>
							<table class="table table-bordered datatable">
								<tr>
									<th>#</th>
									<th>Permission</th>
									<th>Profil</th>
									<!-- <th>Code</th> -->
									<th>Statut</th>
									<th>Actions</th>
								</tr>
								<c:forEach var="rolePermission" items="${rolePermissions}"
									varStatus="loopCounter">
									<tr>
										<td>${loopCounter.count}</td>
										<td>${rolePermission.permission.shortCode}</td>
										<%-- <td>${rolePermission.permission.designation}</td> --%>
										<td>${rolePermission.role.designation}</td>

										<td>${rolePermission.activeStatus}</td>
										<td><a title="Modifier"
											href="/cnpr-homologation/role/${rolePermission.role.id}/permissions/edit/${rolePermission.id}"><i
												class="bi bi-pencil"></i></a> <a title="Supprimer"
											href="/cnpr-homologation/role/${rolePermission.role.id}/permissions/delete/${rolePermission.id}"
											title="Supprimer"><i class="bi bi-trash text-danger"></i></a>
											<a title="Changer d'etat"
											href="/cnpr-homologation/role/${rolePermission.role.id}/permissions/changeStatus/${rolePermission.id}"
											title="Changer de statut"><i
												class="bi bi-arrow-left-right text-success"></i></a></td>
									</tr>
								</c:forEach>
							</table>
						</form:form>
					</div>
				</div>
			</div>


		</div>

	</section>

</main>

<%@ include file="../templates/footer.jsp"%>
