package com.recordstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.recordstore.entity.album.cd.CDEntity;
import com.recordstore.entity.album.dvd.DVDEntity;
import com.recordstore.entity.album.vinyl.VinylEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode
//@Entity
//@Table(name = "orders")
//public class OrderEntity {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//	
//	@ToString.Exclude
//	@EqualsAndHashCode.Exclude
//	@ManyToOne
//	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	private CDEntity cd;
//	
//	@ToString.Exclude
//	@EqualsAndHashCode.Exclude
//	@ManyToOne
//	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	private DVDEntity dvd;
//
//	@ToString.Exclude
//	@EqualsAndHashCode.Exclude
//	@ManyToOne
//	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	private VinylEntity vin;
//	
//	
//	
//	
//}
