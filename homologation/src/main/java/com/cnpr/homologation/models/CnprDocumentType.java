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
@Table(name="tb_cnpr_document_type",schema = "public")
public class CnprDocumentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	
	//@Size(min = 3, max = 50)
	@Column(name="designation")
	private String designation;
	
	@Column(name="duree_validite")
	private int dureeValidite=1;
	
	
	@Column(name = "active_status")
	private boolean activeStatus=false;
	
	
	@Column(name = "created_at",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	@Column(name = "last_updated_at",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = true, nullable = false)
	@UpdateTimestamp
	private Timestamp lastUpdate;
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "createdBy", nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private CnprUser createdBy;


	public CnprDocumentType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	@Override
	public String toString() {
		return "CnprDocumentType [id=" + id + ", designation=" + designation + ", activeStatus=" + activeStatus
				+ ", createdAt=" + createdAt + ", lastUpdate=" + lastUpdate + "]";
	}

	public int getDureeValidite() {
		return dureeValidite;
	}

	public void setDureeValidite(int dureeValidite) {
		this.dureeValidite = dureeValidite;
	}

//	public CnprUser getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(CnprUser createdBy) {
//		this.createdBy = createdBy;
//	}
	
	
	
}
