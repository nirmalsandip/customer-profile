package com.rbc.assignment.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This is a Pojo mapping to the Database table - Customer.
 * 
 * @author Sandip.Nirmal
 *
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer {
	@Id
	@Column(name = "mobile")
	@NotNull(message="Mobile number can not be empty")
	@Size(min=10, max=10, message="Mobile number should be 10 digits")
	private String mobile;

	@Column(name = "first_name")
	@NotNull(message="First Name can not be empty")
	private String firstName;

	@Column(name = "last_name")
	@NotNull(message="Last Name can not be empty")
	private String lastName;

	@Column(name = "email")
	@NotNull(message="Email Id can not be empty")
	@Email(message="Invalid email id")
	private String email;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [mobile=" + mobile + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}
	
	

}
