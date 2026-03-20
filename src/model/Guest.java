
package model;

import java.util.Objects;

public class Guest {
	
	private String username;
	private String name;
	private String surnames;
	private String phoneNumber;
	private String career;
	private String email;
	private String password;
	
	public Guest(String username, String name, String surnames, String phoneNumber, String career, String email,
			String password) {
		super();
		this.username = username;
		this.name = name;
		this.surnames = surnames;
		this.phoneNumber = phoneNumber;
		this.career = career;
		this.email = email;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurnames() {
		return surnames;
	}
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Guest [username=" + username + ", name=" + name + ", surnames=" + surnames + ", phoneNumber="
				+ phoneNumber + ", career=" + career + ", email=" + email + ", password=" + password + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(career, email, name, password, phoneNumber, surnames, username);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guest other = (Guest) obj;
		return Objects.equals(career, other.career) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(surnames, other.surnames)
				&& Objects.equals(username, other.username);
	}
	
	}