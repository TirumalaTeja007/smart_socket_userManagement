package com.sana.apple.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sana.apple.enums.StatusEnum;

@Entity(name="USERS")
public class User extends VersionableEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "user_seq")
	private Integer id;
	private String name;
	private String password;
	private String userName;
	private String email;
	private String countryCode;
	private String dialCode;
	@Size(min=10,max=10 , message = "MobileNumber should be min/max 10 numbers.")
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobileNumber;
	private String profilePicture;
	@Enumerated(EnumType.STRING)
	private StatusEnum status = StatusEnum.ACTIVE;
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;
	private String brand;
	private String model;
	private String androidVersion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAndroidVersion() {
		return androidVersion;
	}
	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDialCode() {
		return dialCode;
	}
	public void setDialCode(String dialCode) {
		this.dialCode = dialCode;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", userName=" + userName + ", email="
				+ email + ", countryCode=" + countryCode + ", dialCode=" + dialCode + ", mobileNumber=" + mobileNumber
				+ ", status=" + status + ", roles=" + roles + ", brand=" + brand + ", model=" + model
				+ ", androidVersion=" + androidVersion + "]";
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
}
