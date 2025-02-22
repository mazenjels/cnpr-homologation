package com.cnpr.homologation.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="tb_cnpr_candidat",schema = "public")
public class Candidat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="postnom")
	private String postnom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="adresse")
	private String adresse;
	
	@Column(name="date_naissance")
	private String dateNaissance;
	
	@Column(name="lieu_naissance")
	private String lieuNaissance;
	
	@Column(name="type_piece_identite")
	private String typePieceIdentite;
	
	@Column(name="numero_piece_identite")
	private String numeroPieceIdentite;
	
	@Column(name="code_unique")
	private String codeUnique;
	
	@Column(name = "code_unique_valide")
	private boolean codeValide=true;
	
	
	@Column(name = "active_status")
	private boolean activeStatus=false;
	
	@Column(name = "recyclage_valide")
	private boolean recyclageValide=false;
	
	
	@Column(name = "created_at",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	@Column(name = "last_updated_at",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = true, nullable = false)
	@UpdateTimestamp
	private Timestamp lastUpdate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cnprAutoEcole", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private CnprAutoEcole cnprAutoEcole;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "createdBy", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private CnprUser createdBy;


	public Candidat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public boolean isCodeValide() {
		return codeValide;
	}

	public void setCodeValide(boolean codeValide) {
		this.codeValide = codeValide;
	}

	public String getCodeUnique() {
		return codeUnique;
	}

	public void setCodeUnique(String codeUnique) {
		this.codeUnique = codeUnique;
	}

	public Candidat(long id) {
		super();
		this.id = id;
	}

	public boolean isRecyclageValide() {
		return recyclageValide;
	}

	public void setRecyclageValide(boolean recyclageValide) {
		this.recyclageValide = recyclageValide;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CnprAutoEcole getCnprAutoEcole() {
		return cnprAutoEcole;
	}

	public void setCnprAutoEcole(CnprAutoEcole cnprAutoEcole) {
		this.cnprAutoEcole = cnprAutoEcole;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public CnprUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(CnprUser createdBy) {
		this.createdBy = createdBy;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPostnom() {
		return postnom;
	}

	public void setPostnom(String postnom) {
		this.postnom = postnom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getTypePieceIdentite() {
		return typePieceIdentite;
	}

	public void setTypePieceIdentite(String typePieceIDentite) {
		this.typePieceIdentite = typePieceIDentite;
	}

	public String getNumeroPieceIdentite() {
		return numeroPieceIdentite;
	}

	public void setNumeroPieceIdentite(String numeroPieceIdentite) {
		this.numeroPieceIdentite = numeroPieceIdentite;
	}
	
	
}
