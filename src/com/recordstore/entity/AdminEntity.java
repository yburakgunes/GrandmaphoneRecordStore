package com.recordstore.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_entity")
public class AdminEntity implements Serializable {
	private static final long serialVersionUID = -85418123055694338L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	public AdminEntity() {
		
	}
	
	public AdminEntity(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "AdminEntity [id=" + id + ", email=" + email + ", password=" + password + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, id, password);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminEntity other = (AdminEntity) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(password, other.password);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
	
}
