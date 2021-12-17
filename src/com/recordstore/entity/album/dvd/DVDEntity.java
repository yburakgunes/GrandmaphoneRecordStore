package com.recordstore.entity.album.dvd;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.recordstore.entity.ArtistEntity;
import com.recordstore.entity.album.Genre;
import com.recordstore.entity.album.IAlbumUtils;
import com.recordstore.entity.album.cd.CDEntity;
import com.recordstore.entity.album.cd.CDTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "dvd_entity")
public class DVDEntity implements IAlbumUtils {
	private static final long serialVersionUID = 7366058467335543420L;
	
	@Enumerated(value = EnumType.STRING)
	private DVDResolution resolution;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	
	@Column(name = "album_name")
	private String name;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "discount")
	private double discount;
	
	@Column(name = "discounted_price")
	private double discPrice;
	
	@Enumerated(value = EnumType.STRING)
	private Genre genre;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "artist_id", referencedColumnName = "id")
	private ArtistEntity artist;
	

	@Override
	public double calcDiscount() {
		double discPrice;
		double price = this.getPrice();
		double disc = this.getDiscount();
		
		if (disc == 0) {
			return price;
			
		} else {
			discPrice = (price - (price * (disc / 100)));
		}
		
		return discPrice;
		
	}
	
}
