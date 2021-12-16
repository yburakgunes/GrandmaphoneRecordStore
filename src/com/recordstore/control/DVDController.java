package com.recordstore.control;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.recordstore.entity.album.dvd.DVDEntity;
import com.recordstore.entity.album.vinyl.VinylEntity;
import com.recordstore.utils.IDatabaseCrud;

public class DVDController implements IDatabaseCrud<DVDEntity>, Serializable {
	
	private static final long serialVersionUID = 804193454661192393L;
	
	private static final Logger logger = LogManager.getLogger(DVDController.class);
	
	public static void main(String[] args) {
		logger.trace("trace logger durumu");
		logger.debug("debug logger durumu");
		logger.info("info logger durumu");
		logger.warn("warn logger durumu");
		logger.error("error logger durumu");
		logger.fatal("fatal logger durumu");
	}
	
	// DML:Create Delete Update : transaction
	// DQL:select : transaction isteğe bağlı
	// create:persist
	// delete: remove
	// update: merge
	// find : find
	@Override
	public void create(DVDEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + DVDController.class);
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + DVDController.class);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete(DVDEntity entity) {
		
		try {
			DVDEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + DVDEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + DVDController.class);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(DVDEntity entity) {
		try {
			DVDEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				findEntity.setName(entity.getName());
				findEntity.setArtist(entity.getArtist());
				findEntity.setDiscount(entity.getDiscount());
				findEntity.setGenre(entity.getGenre());
				findEntity.setResolution(entity.getResolution());
				findEntity.setPrice(entity.getPrice());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + VinylEntity.class);
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + DVDController.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<DVDEntity> list() {
		Session session = databaseConnectionHibernate();
		
		// unutma: buradaki sorgulama entity sorgulaması yani java classına göre
		// çağıracağız.
		String hql = "select str from CityEntity as str where str.id>=:key";
		TypedQuery<DVDEntity> typedQuery = session.createQuery(hql, DVDEntity.class);
		
		int id = 1;
		typedQuery.setParameter("key", id);
		
		ArrayList<DVDEntity> arrayList = (ArrayList<DVDEntity>) typedQuery.getResultList();
		logger.info("listelendi " + DVDEntity.class);
		return arrayList;
	}
	
	// find
	@Override
	public DVDEntity find(long id) {
		Session session = databaseConnectionHibernate();
		DVDEntity dvdEntity;
		try {
			dvdEntity = session.find(DVDEntity.class, id);
			
			if (dvdEntity != null) {
				System.out.println("bulundu... " + dvdEntity);
				return dvdEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + DVDController.class);
			e.printStackTrace();
		}
		return null;
	}
	
}