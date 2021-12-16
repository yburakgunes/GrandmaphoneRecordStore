package com.recordstore.main;

import com.recordstore.control.AdminController;
import com.recordstore.control.ArtistController;
import com.recordstore.control.UserController;
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
//import com.recordstore.menu.AdminController;
//import com.recordstore.menu.AdminEntity;
//import com.recordstore.menu.UserController;
//import com.recordstore.menu.UserEntity;

public class DatabaseCreator {
	public static void main(String[] args) {
		
		UserController userController = new UserController();
		AdminController adminController = new AdminController();
		
		UserEntity user1 = new UserEntity(1,"Burak", "Gunes", "yburak", "427");
		UserEntity user2 = new UserEntity(2,"İlayda", "Gunes", "ilobi", "081");
		UserEntity user3 = new UserEntity(3,"Nesrin", "Gunes", "gunesgonca", "0606");
		UserEntity user4 = new UserEntity(4,"Yusuf", "Gunes", "ygunes", "0707");
		
			userController.create(user1);
			userController.create(user2);
			userController.create(user3);
			userController.create(user4);
		
		AdminEntity admin = new AdminEntity("admin", "qwerty");
		adminController.create(admin);
				
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
		cd1.setDiscPrice(cd1.discPrice());
		artist1.addCds(cd1);
		
		artistController.create(artist1);
		
		ArtistEntity artist2 = new ArtistEntity();
		artist2.setName("Duman");
		artist2.setBio(
				"Duman, 1999 yılında kurulmuş, vokalist Kaan Tangöze, gitarist Batuhan Mutlugil, bas gitarist Ari Barokas ve baterist Mehmet Demirdelen'den oluşan Türk Rock grubu.");
		DVDEntity dvd1 = new DVDEntity();
		dvd1.setArtist(artist2);
		dvd1.setName("Eski Koprunun Altinda");
		dvd1.setGenre(Genre.Rock);
		dvd1.setResolution(DVDResolution.UltraHD);
		dvd1.setPrice(100.00);
		dvd1.setDiscount(30.00);
		dvd1.setDiscPrice(dvd1.discPrice());
		artist2.addDvds(dvd1);
		
		artistController.create(artist2);
		
		ArtistEntity artist3 = new ArtistEntity();
		artist3.setName("Miles Davis");
		artist3.setBio("Miles Dewey Davis III, Amerikalı caz trompetçisi, şef ve bestecidir.");
		VinylEntity vinyl1 = new VinylEntity();
		vinyl1.setArtist(artist3);
		vinyl1.setName("Kind of Blue");
		vinyl1.setGenre(Genre.Blues);
		vinyl1.setSize(VinylSize.TwelveInches);
		vinyl1.setPrice(300.00);
		vinyl1.setDiscount(25.00);
		vinyl1.setDiscPrice(vinyl1.discPrice());
		artist3.addVinyls(vinyl1);
		
		VinylEntity vinyl2 = new VinylEntity();
		vinyl2.setArtist(artist3);
		vinyl2.setDiscount(0);
		vinyl2.setPrice(600.00);
		vinyl2.setGenre(Genre.Blues);
		vinyl2.setName("Bitches Brew");
		vinyl2.setSize(VinylSize.TenInches);
		vinyl2.setDiscPrice(vinyl2.discPrice());
		artist3.addVinyls(vinyl2);
		
		artistController.create(artist3);
		
		ArtistEntity artist4 = new ArtistEntity();
		artist4.setName("Duman");
		artist4.setBio(
				"Duman, 1999 yılında kurulmuş, vokalist Kaan Tangöze, gitarist Batuhan Mutlugil, bas gitarist Ari Barokas ve baterist Mehmet Demirdelen'den oluşan Türk Rock grubu.");
		artistController.create(artist4);
		
		ArtistEntity artist5 = new ArtistEntity();
		artist5.setName("Mor ve Ötesi");
		artist5.setBio(
				"Mor ve Ötesi, Ocak 1995'te Harun Tekin, Alper Tekin, Derin Esmer ve Kerem Kabadayı tarafından İstanbul'da kurulan Türk alternatif rock müzik grubu");
		artistController.create(artist5);
		
		ArtistEntity artist6 = new ArtistEntity();
		artist6.setName("Gipsy Kings");
		artist6.setBio(
				"Gipsy Kings, Rumba-flamenko tarzı müzikleriyle tanınan, İspanyol Çingenelerinden oluşan müzik grubu");
		artistController.create(artist6);
		
		ArtistEntity artist7 = new ArtistEntity();
		artist7.setName("Dream Theater");
		artist7.setBio(
				"Dream Theater, ABD/New York'tan çıkmış progresif metal grubudur. 1990'ların başında Amerika'da başlayan progresif metal hareketinin başı çeken gruplarındandır.");
		artistController.create(artist7);
		
		ArtistEntity artist8 = new ArtistEntity();
		artist8.setName("Adnan Şenses");
		artist8.setBio("Adnan Şenses, ya da doğum adıyla Adnan Ertuğran, Türk şarkıcı ve oyuncu.");
		artistController.create(artist8);
		
		ArtistEntity artist9 = new ArtistEntity();
		artist9.setName("Ferdi Özbeğen");
		artist9.setBio("Ferdi Özbeğen, Türk piyanist ve şarkıcı.");
		artistController.create(artist9);
		
		ArtistEntity artist10 = new ArtistEntity();
		artist10.setName("Tommy Emmanuel");
		artist10.setBio(
				"William Thomas Emmanuel AM, Avustralyalı bir gitarist. Genellikle tüm zamanların en büyük akustik gitaristlerinden biri olarak kabul edilir");
		artistController.create(artist10);
		
	}
}
