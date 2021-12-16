package com.recordstore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.recordstore.entity.album.cd.CDEntity;
import com.recordstore.entity.album.dvd.DVDEntity;
import com.recordstore.entity.album.vinyl.VinylEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user_entity")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = -854182624055694338L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email_address")
	private String email;
	
	@Column(name = "password")
	private String password;
	
//	@OneToMany(mappedBy = "cd", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private List<CDEntity> cds = new ArrayList<CDEntity>();
//	
//	@OneToMany(mappedBy = "dvd", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private List<DVDEntity> dvds = new ArrayList<DVDEntity>();
//	
//	@OneToMany(mappedBy = "vin", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private List<VinylEntity> vins = new ArrayList<VinylEntity>();
//	
//	public void addCds(CDEntity cd) {
//		cds.add(cd);
//	}
//	
//	public void addDvds(DVDEntity dvd) {
//		dvds.add(dvd);
//	}
//	
//	public void addVins(VinylEntity vin) {
//		vins.add(vin);
//	}
	
}
