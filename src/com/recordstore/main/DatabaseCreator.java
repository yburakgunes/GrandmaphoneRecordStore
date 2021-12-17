package com.recordstore.main;

import com.recordstore.control.AdminController;
import com.recordstore.control.ArtistController;
import com.recordstore.control.CDController;
import com.recordstore.control.DVDController;
import com.recordstore.control.UserController;
import com.recordstore.control.VinylController;
import com.recordstore.entity.AdminEntity;
import com.recordstore.entity.ArtistEntity;
import com.recordstore.entity.UserEntity;
import com.recordstore.entity.album.Genre;
import com.recordstore.entity.album.cd.CDEntity;
import com.recordstore.entity.album.cd.CDTypes;
import com.recordstore.entity.album.dvd.DVDEntity;
import com.recordstore.entity.album.dvd.DVDResolution;
import com.recordstore.entity.album.vinyl.VinylEntity;
import com.recordstore.entity.album.vinyl.VinylSize;
// import com.recordstore.menu.AdminController;
// import com.recordstore.menu.AdminEntity;
// import com.recordstore.menu.UserController;
// import com.recordstore.menu.UserEntity;

public class DatabaseCreator {
	
	public static void main(String[] args) {
		addArtists();
		adminUserCreator();
	}
	
	private static void addArtists() {
		
		ArtistController artistController = new ArtistController();
		
		ArtistEntity artist1 = new ArtistEntity();
		artist1.setName("Muslum Gurses");
		artist1.setBio(
				"Müslüm Gürses ya da doğum adıyla Müslüm Akbaş, Türk arabesk ve halk müziği sanatçısı, besteci, söz yazarı ve oyuncu.");
		CDEntity cd1 = new CDEntity();
		cd1.setArtist(artist1);
		cd1.setName("Kuskunum");
		cd1.setGenre(Genre.Arabesk);
		cd1.setType(CDTypes.CD);
		cd1.setPrice(30.00);
		cd1.setDiscount(15.00);
		cd1.setDiscPrice(cd1.calcDiscount());
		artist1.addCds(cd1);
		
		DVDEntity dvd1 = new DVDEntity();
		dvd1.setArtist(artist1);
		dvd1.setName("Gitme");
		dvd1.setGenre(Genre.Arabesk);
		dvd1.setResolution(DVDResolution.QuadHD);
		dvd1.setPrice(200.00);
		dvd1.setDiscount(15.00);
		dvd1.setDiscPrice(dvd1.calcDiscount());
		artist1.addDvds(dvd1);
		
		VinylEntity vinyl1 = new VinylEntity();
		vinyl1.setArtist(artist1);
		vinyl1.setName("Mutlu Ol Yeter");
		vinyl1.setGenre(Genre.Arabesk);
		vinyl1.setSize(VinylSize.SevenInches);
		vinyl1.setPrice(300.00);
		vinyl1.setDiscount(0);
		vinyl1.setDiscPrice(vinyl1.calcDiscount());
		artist1.addVinyls(vinyl1);
		
		ArtistEntity artist2 = new ArtistEntity();
		artist2.setName("Duman");
		artist2.setBio(
				"Duman, 1999 yılında kurulmuş, vokalist Kaan Tangöze, gitarist Batuhan Mutlugil, bas gitarist Ari Barokas ve baterist Mehmet Demirdelen'den oluşan Türk Rock grubu.");
		CDEntity cd2 = new CDEntity();
		cd2.setArtist(artist2);
		cd2.setName("Darmaduman");
		cd2.setGenre(Genre.Rock);
		cd2.setType(CDTypes.CDR);
		cd2.setPrice(150.00);
		cd2.setDiscount(12.00);
		cd2.setDiscPrice(cd2.calcDiscount());
		artist2.addCds(cd2);
		
		DVDEntity dvd2 = new DVDEntity();
		dvd2.setArtist(artist2);
		dvd2.setName("Eski Koprunun Altinda");
		dvd2.setGenre(Genre.Rock);
		dvd2.setResolution(DVDResolution.UltraHD);
		dvd2.setPrice(100.00);
		dvd2.setDiscount(30.00);
		dvd2.setDiscPrice(dvd2.calcDiscount());
		artist2.addDvds(dvd2);
		
		VinylEntity vinyl2 = new VinylEntity();
		vinyl2.setArtist(artist2);
		vinyl2.setName("Seni Kendime Sakladım");
		vinyl2.setGenre(Genre.Rock);
		vinyl2.setSize(VinylSize.TenInches);
		vinyl2.setPrice(200.00);
		vinyl2.setDiscount(8.00);
		vinyl2.setDiscPrice(vinyl2.calcDiscount());
		artist2.addVinyls(vinyl2);
		
		ArtistEntity artist3 = new ArtistEntity();
		artist3.setName("Miles Davis");
		artist3.setBio("Miles Dewey Davis III, Amerikalı caz trompetçisi, şef ve bestecidir.");
		
		CDEntity cd3 = new CDEntity();
		cd3.setArtist(artist3);
		cd3.setName("Bitches Brew");
		cd3.setGenre(Genre.Blues);
		cd3.setType(CDTypes.CDRW);
		cd3.setPrice(120.00);
		cd3.setDiscount(18.00);
		cd3.setDiscPrice(cd3.calcDiscount());
		artist3.addCds(cd3);
		
		DVDEntity dvd3 = new DVDEntity();
		dvd3.setArtist(artist3);
		dvd3.setName("In a Silent Way");
		dvd3.setGenre(Genre.Blues);
		dvd3.setResolution(DVDResolution.HD);
		dvd3.setPrice(600.00);
		dvd3.setDiscount(55.00);
		dvd3.setDiscPrice(dvd3.calcDiscount());
		artist3.addDvds(dvd3);
		
		VinylEntity vinyl3 = new VinylEntity();
		vinyl3.setArtist(artist3);
		vinyl3.setName("Kind of Blue");
		vinyl3.setGenre(Genre.Blues);
		vinyl3.setSize(VinylSize.TwelveInches);
		vinyl3.setPrice(300.00);
		vinyl3.setDiscount(25.00);
		vinyl3.setDiscPrice(vinyl3.calcDiscount());
		artist3.addVinyls(vinyl3);
		
		ArtistEntity artist4 = new ArtistEntity();
		artist4.setName("Athena");
		artist4.setBio(
				"Athena; İstanbul, Türkiye kökenli ska ve punk rock grubu. Gökhan Özoğuz ve Hakan Özoğuz kardeşler grubun kurucu üyeleridir.");
		
		CDEntity cd4 = new CDEntity();
		cd4.setArtist(artist4);
		cd4.setName("Holigan");
		cd4.setGenre(Genre.Rock);
		cd4.setType(CDTypes.CD);
		cd4.setPrice(50.00);
		cd4.setDiscount(0);
		cd4.setDiscPrice(cd4.calcDiscount());
		artist4.addCds(cd4);
		
		DVDEntity dvd4 = new DVDEntity();
		dvd4.setArtist(artist4);
		dvd4.setName("Tam Zamanı Şimdi");
		dvd4.setGenre(Genre.Rock);
		dvd4.setResolution(DVDResolution.UltraHD);
		dvd4.setPrice(80.00);
		dvd4.setDiscount(5.00);
		dvd4.setDiscPrice(dvd4.calcDiscount());
		artist4.addDvds(dvd4);
		
		VinylEntity vinyl4 = new VinylEntity();
		vinyl4.setArtist(artist4);
		vinyl4.setName("Herşey Yolunda");
		vinyl4.setGenre(Genre.Rock);
		vinyl4.setSize(VinylSize.TwelveInches);
		vinyl4.setPrice(110.00);
		vinyl4.setDiscount(11.00);
		vinyl4.setDiscPrice(vinyl4.calcDiscount());
		artist4.addVinyls(vinyl4);
		
		ArtistEntity artist5 = new ArtistEntity();
		artist5.setName("Mor ve Ötesi");
		artist5.setBio(
				"Mor ve Ötesi, Ocak 1995'te Harun Tekin, Alper Tekin, Derin Esmer ve Kerem Kabadayı tarafından İstanbul'da kurulan Türk alternatif rock müzik grubu");
		
		CDEntity cd5 = new CDEntity();
		cd5.setArtist(artist5);
		cd5.setName("Dünya Yalan Söylüyor");
		cd5.setGenre(Genre.Rock);
		cd5.setType(CDTypes.CDRW);
		cd5.setPrice(45.00);
		cd5.setDiscount(0);
		cd5.setDiscPrice(cd5.calcDiscount());
		artist5.addCds(cd5);
		
		DVDEntity dvd5 = new DVDEntity();
		dvd5.setArtist(artist5);
		dvd5.setName("Gül Kendine");
		dvd5.setGenre(Genre.Rock);
		dvd5.setResolution(DVDResolution.HD);
		dvd5.setPrice(200.00);
		dvd5.setDiscount(23.00);
		dvd5.setDiscPrice(dvd5.calcDiscount());
		artist5.addDvds(dvd5);
		
		VinylEntity vinyl5 = new VinylEntity();
		vinyl5.setArtist(artist5);
		vinyl5.setName("Büyük Düşler");
		vinyl5.setGenre(Genre.Rock);
		vinyl5.setSize(VinylSize.TwelveInches);
		vinyl5.setPrice(225.00);
		vinyl5.setDiscount(16.00);
		vinyl5.setDiscPrice(vinyl5.calcDiscount());
		artist5.addVinyls(vinyl5);
		
		ArtistEntity artist6 = new ArtistEntity();
		artist6.setName("Gipsy Kings");
		artist6.setBio(
				"Gipsy Kings, Rumba-flamenko tarzı müzikleriyle tanınan, İspanyol Çingenelerinden oluşan müzik grubu");
		
		CDEntity cd6 = new CDEntity();
		cd6.setArtist(artist6);
		cd6.setName("Gipsy Kings Live 2005");
		cd6.setGenre(Genre.Blues);
		cd6.setType(CDTypes.CD);
		cd6.setPrice(120.00);
		cd6.setDiscount(15.00);
		cd6.setDiscPrice(cd6.calcDiscount());
		artist6.addCds(cd6);
		
		DVDEntity dvd6 = new DVDEntity();
		dvd6.setArtist(artist6);
		dvd6.setName("Mosaique");
		dvd6.setGenre(Genre.Blues);
		dvd6.setResolution(DVDResolution.UltraHD);
		dvd6.setPrice(130.00);
		dvd6.setDiscount(15.00);
		dvd6.setDiscPrice(dvd6.calcDiscount());
		artist6.addDvds(dvd6);
		
		VinylEntity vinyl6 = new VinylEntity();
		vinyl6.setArtist(artist6);
		vinyl6.setName("Este Mundo");
		vinyl6.setGenre(Genre.Blues);
		vinyl6.setSize(VinylSize.TwelveInches);
		vinyl6.setPrice(190.00);
		vinyl6.setDiscount(12.00);
		vinyl6.setDiscPrice(vinyl6.calcDiscount());
		artist6.addVinyls(vinyl6);
		
		ArtistEntity artist7 = new ArtistEntity();
		artist7.setName("Dream Theater");
		artist7.setBio(
				"Dream Theater, ABD/New York'tan çıkmış progresif metal grubudur. 1990'ların başında Amerika'da başlayan progresif metal hareketinin başı çeken gruplarındandır.");
		
		CDEntity cd7 = new CDEntity();
		cd7.setArtist(artist7);
		cd7.setName("Octavarium");
		cd7.setGenre(Genre.Rock);
		cd7.setType(CDTypes.CDRW);
		cd7.setPrice(220.00);
		cd7.setDiscount(25.00);
		cd7.setDiscPrice(cd7.calcDiscount());
		artist7.addCds(cd7);
		
		DVDEntity dvd7 = new DVDEntity();
		dvd7.setArtist(artist7);
		dvd7.setName("Images and Words");
		dvd7.setGenre(Genre.Rock);
		dvd7.setResolution(DVDResolution.FullHD);
		dvd7.setPrice(200.00);
		dvd7.setDiscount(15.00);
		dvd7.setDiscPrice(dvd7.calcDiscount());
		artist7.addDvds(dvd7);
		
		VinylEntity vinyl7 = new VinylEntity();
		vinyl7.setArtist(artist7);
		vinyl7.setName("Awake");
		vinyl7.setGenre(Genre.Rock);
		vinyl7.setSize(VinylSize.TenInches);
		vinyl7.setPrice(900.00);
		vinyl7.setDiscount(90);
		vinyl7.setDiscPrice(vinyl7.calcDiscount());
		artist7.addVinyls(vinyl7);
		
		ArtistEntity artist8 = new ArtistEntity();
		artist8.setName("Tarkan");
		artist8.setBio(
				"Tarkan Tevetoğlu, Türk şarkıcı-şarkı yazarıdır. 1990'ların başından itibaren Türk pop müziğinde yakaladığı devamlı liste ve satış başarılarıyla hem Türkiye'de hem de Avrupa'da tanındı.");
		
		CDEntity cd8 = new CDEntity();
		cd8.setArtist(artist8);
		cd8.setName("Dudu");
		cd8.setGenre(Genre.Pop);
		cd8.setType(CDTypes.CDR);
		cd8.setPrice(20);
		cd8.setDiscount(10.00);
		cd8.setDiscPrice(cd8.calcDiscount());
		artist8.addCds(cd8);
		
		DVDEntity dvd8 = new DVDEntity();
		dvd8.setArtist(artist8);
		dvd8.setName("Ölürüm Sana");
		dvd8.setGenre(Genre.Pop);
		dvd8.setResolution(DVDResolution.UltraHD);
		dvd8.setPrice(9000.00);
		dvd8.setDiscount(10.00);
		dvd8.setDiscPrice(dvd8.calcDiscount());
		artist8.addDvds(dvd8);
		
		VinylEntity vinyl8 = new VinylEntity();
		vinyl8.setArtist(artist8);
		vinyl8.setName("Karma");
		vinyl8.setGenre(Genre.Pop);
		vinyl8.setSize(VinylSize.TwelveInches);
		vinyl8.setPrice(10000);
		vinyl8.setDiscount(12);
		vinyl8.setDiscPrice(vinyl8.calcDiscount());
		artist8.addVinyls(vinyl8);
		
		ArtistEntity artist9 = new ArtistEntity();
		artist9.setName("Metallica");
		artist9.setBio("Metallica, 1981 yılında kurulan Amerikalı bir heavy metal grubudur.");
		
		CDEntity cd9 = new CDEntity();
		cd9.setArtist(artist9);
		cd9.setName("And Justice for All");
		cd9.setGenre(Genre.Metal);
		cd9.setType(CDTypes.CDRW);
		cd9.setPrice(1000.00);
		cd9.setDiscount(15.00);
		cd9.setDiscPrice(cd9.calcDiscount());
		artist9.addCds(cd9);
		
		DVDEntity dvd9 = new DVDEntity();
		dvd9.setArtist(artist9);
		dvd9.setName("Master of Puppets");
		dvd9.setGenre(Genre.Metal);
		dvd9.setResolution(DVDResolution.FullHD);
		dvd9.setPrice(1500.00);
		dvd9.setDiscount(15.00);
		dvd9.setDiscPrice(dvd9.calcDiscount());
		artist9.addDvds(dvd9);
		
		VinylEntity vinyl9 = new VinylEntity();
		vinyl9.setArtist(artist9);
		vinyl9.setName("Ride the Lightning");
		vinyl9.setGenre(Genre.Metal);
		vinyl9.setSize(VinylSize.TenInches);
		vinyl9.setPrice(2000.00);
		vinyl9.setDiscount(10.00);
		vinyl9.setDiscPrice(vinyl9.calcDiscount());
		artist9.addVinyls(vinyl9);
		
		ArtistEntity artist10 = new ArtistEntity();
		artist10.setName("Tommy Emmanuel");
		artist10.setBio(
				"William Thomas Emmanuel AM, Avustralyalı bir gitarist. Genellikle tüm zamanların en büyük akustik gitaristlerinden biri olarak kabul edilir");
		
		CDEntity cd10 = new CDEntity();
		cd10.setArtist(artist10);
		cd10.setName("Classical Gas");
		cd10.setGenre(Genre.Blues);
		cd10.setType(CDTypes.CDR);
		cd10.setPrice(200.00);
		cd10.setDiscount(10.00);
		cd10.setDiscPrice(25.00);
		artist10.addCds(cd10);
		
		DVDEntity dvd10 = new DVDEntity();
		dvd10.setArtist(artist10);
		dvd10.setName("Endless Road");
		dvd10.setGenre(Genre.Blues);
		dvd10.setResolution(DVDResolution.HD);
		dvd10.setPrice(1500.00);
		dvd10.setDiscount(12.00);
		dvd10.setDiscPrice(dvd10.calcDiscount());
		artist10.addDvds(dvd10);
		
		VinylEntity vinyl10 = new VinylEntity();
		vinyl10.setArtist(artist10);
		vinyl10.setName("The Journey");
		vinyl10.setGenre(Genre.Blues);
		vinyl10.setSize(VinylSize.SevenInches);
		vinyl10.setPrice(300.00);
		vinyl10.setDiscount(20);
		vinyl10.setDiscPrice(vinyl10.calcDiscount());
		artist10.addVinyls(vinyl10);
		
		artistController.create(artist1);
		artistController.create(artist2);
		artistController.create(artist3);
		artistController.create(artist4);
		artistController.create(artist5);
		artistController.create(artist6);
		artistController.create(artist7);
		artistController.create(artist8);
		artistController.create(artist9);
		artistController.create(artist10);
		
	}
	
	private static void adminUserCreator() {
		
		UserController userController = new UserController();
		AdminController adminController = new AdminController();
		
		UserEntity user1 = new UserEntity(1, "Burak", "Gunes", "yburak", "427");
		UserEntity user2 = new UserEntity(2, "İlayda", "Gunes", "ilobi", "081");
		UserEntity user3 = new UserEntity(3, "Nesrin", "Gunes", "gunesgonca", "0606");
		UserEntity user4 = new UserEntity(4, "Yusuf", "Gunes", "ygunes", "0707");
		
		userController.create(user1);
		userController.create(user2);
		userController.create(user3);
		userController.create(user4);
		
		AdminEntity admin = new AdminEntity("admin", "qwerty");
		adminController.create(admin);
	}
}
