package com.sana.apple.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Manage Versionable Entity
 * 
 */
@MappedSuperclass
public abstract class VersionableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;

	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	//@Version
	private Integer version = 0;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	

}
