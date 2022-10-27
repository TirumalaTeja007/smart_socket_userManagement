package com.sana.apple.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.sana.apple.enums.StatusEnum;


/**
 * This Class Provides For Role
 * 
 */
@Entity(name = "ROLES")
public class Role extends VersionableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String roleName;
	private String type;
	private String description;

	@Enumerated(EnumType.STRING)
	private StatusEnum status = StatusEnum.ACTIVE;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public Role() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @param roleName
	 * @param description
	 * @param type
	 */
	public Role(Integer id, String roleName, String description, String type) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.description = description;
		this.type = type;
	}

	/**
	 * 
	 * @param roleName
	 * @param description
	 * @param type
	 */
	public Role(String roleName, String description, String type) {
		super();
		this.roleName = roleName;
		this.description = description;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", type=" + type + ", description=" + description
				+ ", status=" + status + "]";
	}

}
