package com.recordstore.control;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.recordstore.entity.album.cd.CDEntity;
import com.recordstore.entity.album.dvd.DVDEntity;
import com.recordstore.entity.album.vinyl.VinylEntity;
import com.recordstore.utils.IDatabaseCrud;

public class CDController implements IDatabaseCrud<CDEntity>, Serializable {
	
	private static final long serialVersionUID = 804193454661192393L;
	
	private static final Logger logger = LogManager.getLogger(CDController.class);
	
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
	public void create(CDEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + CDController.class);
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + CDController.class);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete(CDEntity entity) {
		
		try {
			CDEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + CDEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + CDController.class);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(CDEntity entity) {
		try {
			CDEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				findEntity.setName(entity.getName());
				findEntity.setArtist(entity.getArtist());
				findEntity.setDiscount(entity.getDiscount());
				findEntity.setGenre(entity.getGenre());
				findEntity.setType(entity.getType());
				findEntity.setPrice(entity.getPrice());
				findEntity.setDiscPrice(entity.calcDiscount());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + CDEntity.class);
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + CDController.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<CDEntity> list() {
		Session session = databaseConnectionHibernate();
		
		// unutma: buradaki sorgulama entity sorgulaması yani java classına göre
		// çağıracağız.
		String hql = "select str from CityEntity as str where str.id>=:key";
		TypedQuery<CDEntity> typedQuery = session.createQuery(hql, CDEntity.class);
		
		int id = 1;
		typedQuery.setParameter("key", id);
		
		ArrayList<CDEntity> arrayList = (ArrayList<CDEntity>) typedQuery.getResultList();
		logger.info("listelendi " + CDEntity.class);
		return arrayList;
	}
	
	// find
	@Override
	public CDEntity find(long id) {
		Session session = databaseConnectionHibernate();
		CDEntity cdEntity;
		try {
			cdEntity = session.find(CDEntity.class, id);
			
			if (cdEntity != null) {
				System.out.println("bulundu... " + cdEntity);
				return cdEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + CDController.class);
			e.printStackTrace();
		}
		return null;
	}
	
}