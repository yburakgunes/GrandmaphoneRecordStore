package com.recordstore.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.recordstore.entity.AdminEntity;
import com.recordstore.entity.ArtistEntity;
import com.recordstore.entity.UserEntity;
import com.recordstore.entity.album.cd.CDEntity;
import com.recordstore.entity.album.dvd.DVDEntity;
import com.recordstore.entity.album.vinyl.VinylEntity;


public class HibernateUtils {
	
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
	
	private static final SessionFactory sessionFactory = sessionFactoryHibernate();
	
	private static SessionFactory sessionFactoryHibernate() {
		try {
			Configuration configuration = new Configuration();
			
			configuration.addAnnotatedClass(UserEntity.class);
			configuration.addAnnotatedClass(VinylEntity.class);
			configuration.addAnnotatedClass(DVDEntity.class);
			configuration.addAnnotatedClass(CDEntity.class);
			configuration.addAnnotatedClass(ArtistEntity.class);
			configuration.addAnnotatedClass(AdminEntity.class);
			
			SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			return factory;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
