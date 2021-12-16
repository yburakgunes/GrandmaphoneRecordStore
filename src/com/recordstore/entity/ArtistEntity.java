package com.recordstore.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table(name = "artist")
public class ArtistEntity implements Serializable {
	private static final long serialVersionUID = 4011680950014212188L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "bio")
	private String bio;
	
	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CDEntity> cds = new HashSet<>();
	
	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<DVDEntity> dvds = new HashSet<>();
	
	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<VinylEntity> vinyls = new HashSet<>();
	
	public void addCds(CDEntity t) {
		this.cds.add(t);
	}
	
	public void addVinyls(VinylEntity t) {
		this.vinyls.add(t);
	}
	
	public void addDvds(DVDEntity t) {
		this.dvds.add(t);
	}
	
	public ArtistEntity(String name, String bio, Set<CDEntity> cds, Set<DVDEntity> dvds, Set<VinylEntity> vinyls) {
		super();
		this.name = name;
		this.bio = bio;
		this.cds = cds;
		this.dvds = dvds;
		this.vinyls = vinyls;
	}
	
	public ArtistEntity() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "ArtistEntity [id=" + id + ", name=" + name + ", bio=" + bio + ", cds=" + cds + ", dvds=" + dvds
				+ ", vinyls=" + vinyls + "]";
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBio() {
		return bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public Set<CDEntity> getCds() {
		return cds;
	}
	
	public void setCds(Set<CDEntity> cds) {
		this.cds = cds;
	}
	
	public Set<DVDEntity> getDvds() {
		return dvds;
	}
	
	public void setDvds(Set<DVDEntity> dvds) {
		this.dvds = dvds;
	}
	
	public Set<VinylEntity> getVinyls() {
		return vinyls;
	}
	
	public void setVinyls(Set<VinylEntity> vinyls) {
		this.vinyls = vinyls;
	}
	
}
