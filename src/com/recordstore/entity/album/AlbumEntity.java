package com.recordstore.entity.album;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.recordstore.entity.ArtistEntity;

@Entity
@Table(name = "album_entity")
public class AlbumEntity implements Serializable {
	private static final long serialVersionUID = -3907111947281275521L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	
	@Column(name = "album_name")
	private String name;
	
	@Column(name = "price")
	private float price;
	
	@Lob
	@Column(name = "photo", columnDefinition = "BLOB")
	private byte[] albumCover;
	
	@Column(name = "discount")
	private float discount;
	
	@Column(name = "discounted_price")
	private float discPrice;
	
	@ManyToOne
	@JoinColumn(name = "artist_id", referencedColumnName = "id")
	private ArtistEntity artist;
	
	@Enumerated(value = EnumType.STRING)
	private Genre genre;
	
	
	public AlbumEntity() {
		
	}
	
	public AlbumEntity(String name, float price, byte[] albumCover, float discount, float discPrice,
			ArtistEntity artist, Genre genre) {
		super();
		this.name = name;
		this.price = price;
		this.albumCover = albumCover;
		this.discount = discount;
		this.discPrice = discPrice;
		this.artist = artist;
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return "AlbumEntity [id=" + id + ", name=" + name + ", price=" + price + ", albumCover="
				+ Arrays.toString(albumCover) + ", discount=" + discount + ", artist=" + artist + ", genre=" + genre
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(albumCover);
		result = prime * result + Objects.hash(artist, discount, genre, id, name, price);
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
		AlbumEntity other = (AlbumEntity) obj;
		return Arrays.equals(albumCover, other.albumCover) && Objects.equals(artist, other.artist)
				&& discount == other.discount && genre == other.genre && id == other.id
				&& Objects.equals(name, other.name) && Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
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
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public byte[] getAlbumCover() {
		return albumCover;
	}
	
	public void setAlbumCover(byte[] albumCover) {
		this.albumCover = albumCover;
	}
	
	public float getDiscount() {
		return discount;
	}
	
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	public ArtistEntity getArtist() {
		return artist;
	}
	
	public void setArtist(ArtistEntity artist) {
		this.artist = artist;
	}
	
	public Genre getGenre() {
		return genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public float getDiscPrice() {
		return discPrice;
	}
	
	public void setDiscPrice(float discPrice) {
		this.discPrice = discPrice;
	}
	
}
