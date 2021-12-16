package com.recordstore.menu;

import java.util.ArrayList;
import java.util.Scanner;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.recordstore.control.ArtistController;
import com.recordstore.control.CDController;
import com.recordstore.control.DVDController;
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
import com.recordstore.utils.HibernateUtils;

public class LoginMainTest {
	
	public static void main(String[] args) {
		
		showMainMenu();
		// addInformation();
	}
	
	public static void showMainMenu() {
		System.out.println(
				"WELCOME TO GRANDMAPHONE RECORD STORE" + "\n" + "\nPlease Press 1 for Admin, press 2 for User login");
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			choice = scanner.nextInt();
			scanner.nextLine();
			if (choice < 0 || choice > 3)
				System.out.println("Incorrect input. Please try again.");
		} while (choice < 0 || choice > 3);
		
		switch (choice) {
			case 1:
				adminConnectionVerifier();
				break;
			case 2:
				userConnectionVerifier();
				break;
			case 3:
				System.out.println("System out...");
				System.exit(0);
			default:
				throw new IllegalStateException("Unexpected value: " + choice);
		}
		scanner.close();
	}
	
	private static void adminConnectionVerifier() {
		
		Scanner scanner = new Scanner(System.in);
		AdminEntity adminEntity = new AdminEntity();
		adminEntity.setId(0);
		Session session = HibernateUtils.getSessionfactory().openSession();
		do {
			System.out.println("\n\n\tKullanıcı adınızı giriniz:");
			adminEntity.setEmail(scanner.nextLine());
			System.out.println("\tŞifrenizi giriniz:");
			adminEntity.setPassword(scanner.nextLine());
			System.out.println("ADMIN ENTRY TO STRING" + adminEntity.toString());
			TypedQuery<AdminEntity> query = session.createQuery(
					"select a from AdminEntity as a where a.email =:key1 and a.password =:key2", AdminEntity.class);
			query.setParameter("key1", adminEntity.getEmail());
			query.setParameter("key2", adminEntity.getPassword());
			try {
				adminEntity = new AdminEntity();
				adminEntity = query.getSingleResult();
				System.out.println(adminEntity.toString());
			} catch (Exception e) {
				System.out.println("\n\n\tUsername or password is incorrect");
				System.out.println("\tPlease try again");
			}
		} while (adminEntity.getId() == 0);
		System.out.println("\n\n\tWelcome " + adminEntity.getEmail());
		showAdminPage();
		scanner.close();
	}
	
	private static void showAdminPage() {
		System.out.println("Welcome to the Admin Page");
		System.out.println("1-) Add Information");
		System.out.println("2-) Delete Information");
		System.out.println("3-) Update Information");
		System.out.println("4-) Show Order List");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		sc.nextLine();
		switch (key) {
			case 1:
				addInformation();
				break;
			case 2:
				deleteInformation();
				break;
			case 3:
				updateInformation();
				break;
			case 4:
				showOrderList();
				break;
			
			default:
				break;
		}
		
	}
	
	private static void addInformation() {
		System.out.println("1-)CD" + "\n2-)DVD" + "\n3-)Vinyl" + "\n4-)Artist");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		sc.nextLine();
		
		switch (key) {
			case 1:
				addCD(sc);
				
				break;
			case 2:
				addDvd(sc);
				break;
			case 3:
				addVinyl(sc);
				break;
			case 4:
				addArtist(sc);
				break;
			
			default:
				break;
		}
	}
	
	private static void addArtist(Scanner sc) {
		ArtistEntity artist = new ArtistEntity();
		System.out.println("Please enter name");
		artist.setName(sc.nextLine());
		System.out.println("Please enter bio");
		artist.setBio(sc.nextLine());
		ArtistController artController = new ArtistController();
		artController.create(artist);
	}
	
	private static void addVinyl(Scanner sc) {
		VinylEntity vinyl = new VinylEntity();
		System.out.println("Please enter name");
		vinyl.setName(sc.nextLine());
		System.out.println("Please enter price");
		vinyl.setPrice(sc.nextDouble());
		System.out.println("Please enter discount percentage");
		vinyl.setDiscount(sc.nextDouble());
		System.out.println("Please choose genre");
		vinylGenreChoice();
		System.out.println("Please choose resolution");
		vinylGetSize();
		System.out.println("Please enter artist");
		vinyl.setArtist(newArtistVinyl(vinyl));
		System.out.println("DVD and Artist are created");
		VinylController vincontroller = new VinylController();
		vincontroller.create(vinyl);
	}
	
	private static ArtistEntity newArtistVinyl(VinylEntity vinyl) {
		
		ArtistEntity artist = new ArtistEntity();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter artist name");
		artist.setName(sc.nextLine());
		System.out.println("Please enter artist's bio");
		artist.setBio(sc.nextLine());
		artist.addVinyls(vinyl);
		ArtistController controller = new ArtistController();
		controller.create(artist);
		sc.close();
		return artist;
	}
	
	private static void vinylGetSize() {
		VinylEntity vin = new VinylEntity();
		System.out.println("Please choose a type");
		System.out.println("1-)Seven Inches" + "\n2-)Ten Inches" + "\n3-)TwelveInches");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		sc.nextLine();
		if (key < 0 || key > 5) {
			System.out.println("Incorrect input. Please try again.");
		} else {
			
			switch (key) {
				case 1:
					vin.setSize(VinylSize.SevenInches);
					break;
				case 2:
					vin.setSize(VinylSize.TenInches);
					break;
				case 3:
					vin.setSize(VinylSize.TwelveInches);
					break;
				
				default:
					System.out.println("Please choose between number 1-4");
					break;
			}
		}
	}
	
	private static void vinylGenreChoice() {
		{
			VinylEntity vin = new VinylEntity();
			System.out.println("Please choose a genre");
			System.out.println("1-)Rock" + "\n2-)Blues" + "\n3-)Jazz" + "\n4-)Pop" + "\n5-)Metal" + "\n6-)Arabesk");
			Scanner sc = new Scanner(System.in);
			int key = sc.nextInt();
			sc.nextLine();
			if (key < 0 || key > 6) {
				System.out.println("Incorrect input. Please try again.");
			} else {
				
				switch (key) {
					case 1:
						vin.setGenre(Genre.Rock);
						break;
					case 2:
						vin.setGenre(Genre.Blues);
						break;
					case 3:
						vin.setGenre(Genre.Jazz);
						break;
					case 4:
						vin.setGenre(Genre.Pop);
						break;
					case 5:
						vin.setGenre(Genre.Metal);
						break;
					case 6:
						vin.setGenre(Genre.Arabesk);
						break;
					
					default:
						System.out.println("Please choose between number 1-6");
						break;
				}
			}
		}
		
	}
	
	private static void addDvd(Scanner sc) {
		DVDEntity dvd = new DVDEntity();
		System.out.println("Please enter name");
		dvd.setName(sc.nextLine());
		System.out.println("Please enter price");
		dvd.setPrice(sc.nextDouble());
		System.out.println("Please enter discount percentage");
		dvd.setDiscount(sc.nextDouble());
		System.out.println("Please choose genre");
		dvdGenreChoice();
		System.out.println("Please choose resolution");
		dvdGetResolution();
		System.out.println("Please enter artist");
		dvd.setArtist(newArtistCd(dvd));
		System.out.println("DVD and Artist are created");
		DVDController dvdController = new DVDController();
		dvdController.create(dvd);
	}
	
	private static void addCD(Scanner sc) {
		CDEntity cd = new CDEntity();
		System.out.println("Please enter name");
		cd.setName(sc.nextLine());
		System.out.println("Please enter price");
		cd.setPrice(sc.nextDouble());
		System.out.println("Please enter discount percentage");
		cd.setDiscount(sc.nextDouble());
		System.out.println("Please choose genre");
		cdGenreChoice();
		System.out.println("Please choose type");
		cdGetType();
		System.out.println("Please enter artist");
		cd.setArtist(newArtistCd(cd));
		System.out.println("CD and Artist are created");
		CDController cdController = new CDController();
		cdController.create(cd);
	}
	
	private static ArtistEntity newArtistCd(DVDEntity dvd) {
		
		ArtistEntity artist = new ArtistEntity();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter artist name");
		artist.setName(sc.nextLine());
		System.out.println("Please enter artist's bio");
		artist.setBio(sc.nextLine());
		artist.addDvds(dvd);
		ArtistController controller = new ArtistController();
		controller.create(artist);
		sc.close();
		return artist;
	}
	
	private static void dvdGetResolution() {
		DVDEntity dvd = new DVDEntity();
		System.out.println("Please choose a type");
		System.out.println("1-)CD" + "\n2-)CDR" + "\n3-)CDRW");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		sc.nextLine();
		if (key < 0 || key > 5) {
			System.out.println("Incorrect input. Please try again.");
		} else {
			
			switch (key) {
				case 1:
					dvd.setResolution(DVDResolution.FullHD);
					break;
				case 2:
					dvd.setResolution(DVDResolution.HD);
					break;
				case 3:
					dvd.setResolution(DVDResolution.QuadHD);
					break;
				case 4:
					dvd.setResolution(DVDResolution.UltraHD);
					break;
				
				default:
					System.out.println("Please choose between number 1-4");
					break;
			}
		}
	}
	
	private static Genre dvdGenreChoice() {
		DVDEntity dvd = new DVDEntity();
		System.out.println("Please choose a genre");
		System.out.println("1-)Rock" + "\n2-)Blues" + "\n3-)Jazz" + "\n4-)Pop" + "\n5-)Metal" + "\n6-)Arabesk");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		sc.nextLine();
		if (key < 0 || key > 6) {
			System.out.println("Incorrect input. Please try again.");
		} else {
			
			switch (key) {
				case 1:
					dvd.setGenre(Genre.Rock);
					break;
				case 2:
					dvd.setGenre(Genre.Blues);
					break;
				case 3:
					dvd.setGenre(Genre.Jazz);
					break;
				case 4:
					dvd.setGenre(Genre.Pop);
					break;
				case 5:
					dvd.setGenre(Genre.Metal);
					break;
				case 6:
					dvd.setGenre(Genre.Arabesk);
					break;
				
				default:
					System.out.println("Please choose between number 1-6");
					break;
			}
		}
		return null;
	}
	
	private static ArtistEntity newArtistCd(CDEntity cd) {
		
		ArtistEntity artist = new ArtistEntity();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter artist name");
		artist.setName(sc.nextLine());
		System.out.println("Please enter artist's bio");
		artist.setBio(sc.nextLine());
		artist.addCds(cd);
		ArtistController controller = new ArtistController();
		controller.create(artist);
		sc.close();
		return artist;
	}
	
	private static CDTypes cdGetType() {
		CDEntity cd = new CDEntity();
		System.out.println("Please choose a type");
		System.out.println("1-)CD" + "\n2-)CDR" + "\n3-)CDRW");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		sc.nextLine();
		if (key < 0 || key > 4) {
			System.out.println("Incorrect input. Please try again.");
		} else {
			
			switch (key) {
				case 1:
					cd.setType(CDTypes.CD);
					break;
				case 2:
					cd.setType(CDTypes.CDR);
					break;
				case 3:
					cd.setType(CDTypes.CDRW);
					break;
				
				default:
					System.out.println("Please choose between number 1-3");
					break;
			}
		}
		return null;
	}
	
	private static Genre cdGenreChoice() {
		CDEntity cd = new CDEntity();
		System.out.println("Please choose a genre");
		System.out.println("1-)Rock" + "\n2-)Blues" + "\n3-)Jazz" + "\n4-)Pop" + "\n5-)Metal" + "\n6-)Arabesk");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		sc.nextLine();
		if (key < 0 || key > 6) {
			System.out.println("Incorrect input. Please try again.");
		} else {
			
			switch (key) {
				case 1:
					cd.setGenre(Genre.Rock);
					break;
				case 2:
					cd.setGenre(Genre.Blues);
					break;
				case 3:
					cd.setGenre(Genre.Jazz);
					break;
				case 4:
					cd.setGenre(Genre.Pop);
					break;
				case 5:
					cd.setGenre(Genre.Metal);
					break;
				case 6:
					cd.setGenre(Genre.Arabesk);
					break;
				
				default:
					System.out.println("Please choose between number 1-6");
					break;
			}
		}
		return null;
	}
	
	private static void deleteInformation() {
		System.out.println("1-)CD" + "\n2-)DVD" + "\n3-)Vinyl" + "\n4-)Artist");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		sc.nextLine();
		
		switch (key) {
			case 1:
				
				cdList();
				
				cdDelete(sc);
				
			case 2:
				
				dvdList();
				
				dvdDelete(sc);
				
				break;
			case 3:
				
				vinylList();
				
				vinylDelete(sc);
				
				break;
			case 4:
				artistList();
				artistDelete(sc);
				
				break;
			
		}
		
	}
	
	private static void artistDelete(Scanner sc) {
		System.out.println("Please choose the ID number you want to delete");
		
		int choice = sc.nextInt();
		ArtistEntity artistEntity = new ArtistEntity();
		ArtistController artistController = new ArtistController();
		
		Session session = HibernateUtils.getSessionfactory().openSession();
		TypedQuery<ArtistEntity> typedQuery = session.createQuery("select aE from ArtistEntity as aE where aE.id =:key",
				ArtistEntity.class);
		typedQuery.setParameter("key", choice);
		artistEntity = typedQuery.getSingleResult();
		artistController.delete(artistEntity);
		
		System.out.println("Artist is deleted");
	}
	
	private static void artistList() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select at from ArtistEntity as at ";
		
		TypedQuery<ArtistEntity> typedQuery = session.createQuery(hql, ArtistEntity.class);
		ArrayList<ArtistEntity> entities = (ArrayList<ArtistEntity>) typedQuery.getResultList();
		
		for (ArtistEntity temp : entities) {
			System.out.println(temp);
		}
	}
	
	private static void vinylDelete(Scanner sc) {
		System.out.println("Please choose the ID number you want to delete");
		
		int choice = sc.nextInt();
		VinylEntity vinylEntity = new VinylEntity();
		VinylController vinController = new VinylController();
		
		Session session = HibernateUtils.getSessionfactory().openSession();
		TypedQuery<VinylEntity> typedQuery = session.createQuery("select vE from VinylEntity as vE where id =:key",
				VinylEntity.class);
		typedQuery.setParameter("key", choice);
		vinylEntity = typedQuery.getSingleResult();
		vinController.delete(vinylEntity);
		
		System.out.println("Vinyl is deleted");
	}
	
	private static void vinylList() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select vE from VinylEntity as vE ";
		
		TypedQuery<VinylEntity> typedQuery = session.createQuery(hql, VinylEntity.class);
		ArrayList<VinylEntity> entities = (ArrayList<VinylEntity>) typedQuery.getResultList();
		
		for (VinylEntity temp : entities) {
			System.out.println(temp);
		}
	}
	
	private static void dvdDelete(Scanner sc) {
		System.out.println("Please choose the ID number you want to delete");
		
		int choice = sc.nextInt();
		DVDEntity dvdEntity = new DVDEntity();
		DVDController dvdController = new DVDController();
		
		Session session = HibernateUtils.getSessionfactory().openSession();
		TypedQuery<DVDEntity> typedQuery = session.createQuery("select dvd from DVDEntity as dvd where dvd.id =:key",
				DVDEntity.class);
		typedQuery.setParameter("key", choice);
		dvdEntity = typedQuery.getSingleResult();
		dvdController.delete(dvdEntity);
		
		System.out.println("DVD is deleted");
	}
	
	private static void dvdList() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select dvd from DVDEntity as dvd ";
		
		TypedQuery<DVDEntity> typedQuery = session.createQuery(hql, DVDEntity.class);
		ArrayList<DVDEntity> entities = (ArrayList<DVDEntity>) typedQuery.getResultList();
		
		for (DVDEntity temp : entities) {
			System.out.println(temp);
		}
	}
	
	private static void cdDelete(Scanner sc) {
		System.out.println("Please choose the ID number you want to delete");
		
		int choice = sc.nextInt();
		CDEntity cdEntity = new CDEntity();
		CDController cdController = new CDController();
		
		Session session = HibernateUtils.getSessionfactory().openSession();
		TypedQuery<CDEntity> typedQuery = session.createQuery("select cd from CDEntity as cd where cd.id =:key",
				CDEntity.class);
		typedQuery.setParameter("key", choice);
		cdEntity = typedQuery.getSingleResult();
		cdController.delete(cdEntity);
		System.out.println("CD is deleted");
	}
	
	private static void cdList() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select cd from CDEntity as cd ";
		
		TypedQuery<CDEntity> typedQuery = session.createQuery(hql, CDEntity.class);
		ArrayList<CDEntity> entities = (ArrayList<CDEntity>) typedQuery.getResultList();
		
		for (CDEntity temp : entities) {
			System.out.println(temp);
		}
	}
	
	private static void updateInformation() {
		System.out.println("1-)CD" + "\n2-)DVD" + "\n3-)Vinyl" + "\n4-)Artist");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		sc.nextLine();
		
		switch (key) {
			
			case 1:
				
				cdList();
				cdUpdate(sc);
				break;
			
			case 2:
				
				dvdList();
				dvdUpdate(sc);
				break;
			
			case 3:
				
				vinylList();
				vinylUpdate(sc);
				break;
			
			case 4:
				
				artistList();
				
				updateArtist(sc);
				
				break;
			
		}
	}
	
	private static void updateArtist(Scanner sc) {
		System.out.println("Please choose the ID number you want to delete");
		
		int choice = sc.nextInt();
		ArtistEntity artistEntity = new ArtistEntity();
		ArtistController artistController = new ArtistController();
		
		Session session = HibernateUtils.getSessionfactory().openSession();
		TypedQuery<ArtistEntity> typedQuery = session.createQuery("select aE from ArtistEntity as aE where aE.id =:key",
				ArtistEntity.class);
		typedQuery.setParameter("key", choice);
		artistEntity = typedQuery.getSingleResult();
		System.out.println("Please enter name");
		artistEntity.setName(sc.nextLine());
		System.out.println("Please enter bio");
		artistEntity.setBio(sc.nextLine());
		artistController.update(artistEntity);
	}
	
	private static void vinylUpdate(Scanner sc) {
		System.out.println("Please choose the ID number you want to delete");
		
		int choice = sc.nextInt();
		VinylEntity vinylEntity = new VinylEntity();
		VinylController vinController = new VinylController();
		
		Session session = HibernateUtils.getSessionfactory().openSession();
		TypedQuery<VinylEntity> typedQuery = session.createQuery("select vE from VinylEntity as vE where vE.id =:key",
				VinylEntity.class);
		typedQuery.setParameter("key", choice);
		vinylEntity = typedQuery.getSingleResult();
		System.out.println("Please enter name");
		vinylEntity.setName(sc.nextLine());
		System.out.println("Please enter price");
		vinylEntity.setPrice(sc.nextDouble());
		System.out.println("Please enter discount percentage");
		vinylEntity.setDiscount(sc.nextDouble());
		System.out.println("Please choose genre");
		vinylGenreChoice();
		System.out.println("Please choose resolution");
		vinylGetSize();
		vinController.update(vinylEntity);
	}
	
	private static void dvdUpdate(Scanner sc) {
		System.out.println("Please choose the ID number you want to update");
		
		int choice = sc.nextInt();
		DVDEntity dvdEntity = new DVDEntity();
		DVDController dvdController = new DVDController();
		
		Session session = HibernateUtils.getSessionfactory().openSession();
		TypedQuery<DVDEntity> typedQuery = session.createQuery("select dvd from DVDEntity as dvd where dvd.id =:key",
				DVDEntity.class);
		typedQuery.setParameter("key", choice);
		dvdEntity = typedQuery.getSingleResult();
		System.out.println("Please enter new name");
		dvdEntity.setName(sc.nextLine());
		System.out.println("Please choose new genre");
		dvdGenreChoice();
		System.out.println("Please choose new resolution");
		dvdGetResolution();
		dvdEntity.setPrice(sc.nextDouble());
		dvdEntity.setDiscount(sc.nextDouble());
		dvdController.update(dvdEntity);
	}
	
	private static void cdUpdate(Scanner sc) {
		System.out.println("Please choose the ID number you want to update");
		
		int choice = sc.nextInt();
		CDEntity cdEntity = new CDEntity();
		CDController cdController = new CDController();
		
		Session session = HibernateUtils.getSessionfactory().openSession();
		TypedQuery<CDEntity> typedQuery = session.createQuery("select cd from CDEntity as cd where dvd.id =:key",
				CDEntity.class);
		typedQuery.setParameter("key", choice);
		cdEntity = typedQuery.getSingleResult();
		System.out.println("Please enter new name");
		cdEntity.setName(sc.nextLine());
		System.out.println("Please choose new genre");
		cdGenreChoice();
		System.out.println("Please choose new type");
		cdGetType();
		cdEntity.setPrice(sc.nextDouble());
		cdEntity.setDiscount(sc.nextDouble());
		cdController.update(cdEntity);
	}
	
	private static void showOrderList() {
		// This method can only be completed if database connection is succesfully
		// established
	}
	
	private static void userConnectionVerifier() {
		
		System.out.println("Please enter username: ");
		Scanner sc = new Scanner(System.in);
		String uname = sc.nextLine();
		System.out.println("Please enter password: ");
		String upass = sc.nextLine();
		
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select ue from UserEntity as ue where email_address =:key and password =:key2";
		
		TypedQuery<UserEntity> typedQuery = session.createQuery(hql, UserEntity.class);
		typedQuery.setParameter("key", uname);
		typedQuery.setParameter("key2", upass);
		ArrayList<UserEntity> entities = (ArrayList<UserEntity>) typedQuery.getResultList();
		
		for (UserEntity temp : entities) {
			if (temp == null) {
				System.out.println("Wrong username or password entered");
			} else {
				System.out.println("Connection successful. Welcome!");
				showUserPage();
			}
		}
	}
	
	private static void showUserPage() {
		System.out.println("1-)Latest 10 Albums" + "\n2-)Discounted 15 Albums" + "\n3-)Albums by Genre"
				+ "\n4-)Albums by Artist" + "\n5-)Most Ordered Albums" + "\n6-)Most Popular Artist");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		sc.nextLine();
		
		switch (key) {
			case 1:
				latestTen(sc);
				
				break;
			case 2:
				discountedAlbums(sc);
				
				break;
			case 3:
				albumsByGenres(sc);
				break;
			case 4:
				albumsByArtists(sc);
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			
			default:
				break;
		}
	}
	
	private static void albumsByArtists(Scanner sc) {
		System.out.println("Please choose album types");
		System.out.println("1-) CD" + "\n2-)DVD" + "\n3-)Vinyl");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
			case 1:
				cdByArtist();
				break;
			case 2:
				dvdbyArtist();
				break;
			case 3:
				vinylByArtist();
				break;
		}
	}
	
	private static void vinylByArtist() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select vin from VinylEntity vin left join ArtistEntity as at on at.id=vin.artist_id";
		TypedQuery<VinylEntity> typedQuery = session.createQuery(hql, VinylEntity.class);
		ArrayList<VinylEntity> vinylEntities = (ArrayList<VinylEntity>) typedQuery.getResultList();
		
		for (VinylEntity temp : vinylEntities) {
			System.out.println(temp);
		}
	}
	
	private static void dvdbyArtist() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select dvd from DVDEntity dvd left join ArtistEntity as at on at.id=dvd.artist_id";
		TypedQuery<DVDEntity> typedQuery = session.createQuery(hql, DVDEntity.class);
		ArrayList<DVDEntity> dvdEntities = (ArrayList<DVDEntity>) typedQuery.getResultList();
		
		for (DVDEntity temp : dvdEntities) {
			System.out.println(temp);
		}
	}
	
	private static void cdByArtist() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select cd from CDEntity cd left join ArtistEntity as at on at.id=cd.artist_id";
		TypedQuery<CDEntity> typedQuery = session.createQuery(hql, CDEntity.class);
		ArrayList<CDEntity> cdEntities = (ArrayList<CDEntity>) typedQuery.getResultList();
		
		for (CDEntity temp : cdEntities) {
			System.out.println(temp);
		}
	}
	
	private static void albumsByGenres(Scanner sc) {
		{
			System.out.println("Please choose album types");
			System.out.println("1-) CD" + "\n2-)DVD" + "\n3-)Vinyl");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1:
					
					cdByGenres();
					break;
				case 2:
					dvdByGenres();
					break;
				case 3:
					vinylByGenres();
					break;
			}
		}
	}
	
	private static void vinylByGenres() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select vin.genre from VinylEntity as vin";
		TypedQuery<VinylEntity> typedQuery = session.createQuery(hql, VinylEntity.class);
		ArrayList<VinylEntity> vinylEntities = (ArrayList<VinylEntity>) typedQuery.getResultList();
		
		for (VinylEntity temp : vinylEntities) {
			System.out.println(temp);
		}
	}
	
	private static void dvdByGenres() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select dvd.genre from DVDEntity as dvd";
		TypedQuery<DVDEntity> typedQuery = session.createQuery(hql, DVDEntity.class);
		ArrayList<DVDEntity> dvdEntities = (ArrayList<DVDEntity>) typedQuery.getResultList();
		
		for (DVDEntity temp : dvdEntities) {
			System.out.println(temp);
		}
	}
	
	private static void cdByGenres() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select cd.genre from CDEntity as cd";
		TypedQuery<CDEntity> typedQuery = session.createQuery(hql, CDEntity.class);
		ArrayList<CDEntity> cdEntities = (ArrayList<CDEntity>) typedQuery.getResultList();
		
		for (CDEntity temp : cdEntities) {
			System.out.println(temp);
		}
	}
	
	private static void discountedAlbums(Scanner sc) {
		System.out.println("Please choose album types");
		System.out.println("1-) CD" + "\n2-)DVD" + "\n3-)Vinyl");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
			case 1:
				discountedCDs();
				break;
			case 2:
				discountedDVDs();
				break;
			case 3:
				discountedVinyls();
				break;
		}
	}
	
	private static void discountedVinyls() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select dvd from DVDEntity as dvd where dvd.discPrice is not null";
		TypedQuery<VinylEntity> typedQuery = session.createQuery(hql, VinylEntity.class);
		typedQuery.setMaxResults(15);
		ArrayList<VinylEntity> vinylEntities = (ArrayList<VinylEntity>) typedQuery.getResultList();
		
		for (VinylEntity temp : vinylEntities) {
			System.out.println(temp);
		}
	}
	
	private static void discountedDVDs() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select dvd from DVDEntity as dvd where dvd.discPrice is not null";
		TypedQuery<DVDEntity> typedQuery = session.createQuery(hql, DVDEntity.class);
		typedQuery.setMaxResults(15);
		ArrayList<DVDEntity> dvdEntities = (ArrayList<DVDEntity>) typedQuery.getResultList();
		
		for (DVDEntity temp : dvdEntities) {
			System.out.println(temp);
		}
	}
	
	private static void discountedCDs() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select cE from CDEntity as cE where cE.discPrice is not null";
		TypedQuery<CDEntity> typedQuery = session.createQuery(hql, CDEntity.class);
		typedQuery.setMaxResults(15);
		ArrayList<CDEntity> cdEntities = (ArrayList<CDEntity>) typedQuery.getResultList();
		
		for (CDEntity temp : cdEntities) {
			System.out.println(temp);
		}
	}
	
	private static void latestTen(Scanner sc) {
		System.out.println("Please choose album types");
		System.out.println("1-) CD" + "\n2-)DVD" + "\n3-)Vinyl");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
			case 1:
				getTenCDs();
				break;
			case 2:
				getTenDVDs();
				break;
			case 3:
				getTenVinyls();
				break;
		}
	}
	
	private static void getTenVinyls() {
		String hql3 = "select vin from VinylEntity as vin";
		Session session = HibernateUtils.getSessionfactory().openSession();
		TypedQuery<VinylEntity> typedQuery = session.createQuery(hql3, VinylEntity.class);
		typedQuery.setMaxResults(10);
		ArrayList<VinylEntity> vinylEntities = (ArrayList<VinylEntity>) typedQuery.getResultList();
		for (VinylEntity temp : vinylEntities) {
			System.out.println(temp);
		}
	}
	
	private static void getTenDVDs() {
		String hql2 = "select dvd from DVDEntity as dvd";
		Session session = HibernateUtils.getSessionfactory().openSession();
		TypedQuery<DVDEntity> typedQuery = session.createQuery(hql2, DVDEntity.class);
		typedQuery.setMaxResults(10);
		ArrayList<DVDEntity> dvdEntities = (ArrayList<DVDEntity>) typedQuery.getResultList();
		for (DVDEntity temp : dvdEntities) {
			System.out.println(temp);
		}
	}
	
	private static void getTenCDs() {
		Session session = HibernateUtils.getSessionfactory().openSession();
		String hql = "select cd from CDEntity as cd";
		TypedQuery<CDEntity> typedQuery = session.createQuery(hql, CDEntity.class);
		typedQuery.setMaxResults(10);
		ArrayList<CDEntity> cdEntities = (ArrayList<CDEntity>) typedQuery.getResultList();
		for (CDEntity temp : cdEntities) {
			System.out.println(temp);
		}
	}
}
