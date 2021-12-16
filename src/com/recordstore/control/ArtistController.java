package com.recordstore.control;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.recordstore.entity.ArtistEntity;
import com.recordstore.entity.UserEntity;
import com.recordstore.utils.IDatabaseCrud;

public class ArtistController implements IDatabaseCrud<ArtistEntity>, Serializable {
	
	private static final long serialVersionUID = 804193454661192393L;
	
	private static final Logger logger = LogManager.getLogger(ArtistController.class);
	
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
	public void create(ArtistEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + ArtistController.class);
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + ArtistController.class);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete(ArtistEntity entity) {
		
		try {
			ArtistEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + ArtistEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + ArtistController.class);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(ArtistEntity entity) {
		try {
			ArtistEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				findEntity.setName(entity.getName());
				findEntity.setBio(entity.getBio());
				findEntity.setCds(entity.getCds());
				findEntity.setDvds(entity.getDvds());
				findEntity.setVinyls(entity.getVinyls());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + UserEntity.class);
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + ArtistController.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<ArtistEntity> list() {
		Session session = databaseConnectionHibernate();
		
		// unutma: buradaki sorgulama entity sorgulaması yani java classına göre
		// çağıracağız.
		String hql = "select str from CityEntity as str where str.id>=:key";
		TypedQuery<ArtistEntity> typedQuery = session.createQuery(hql, ArtistEntity.class);
		
		int id = 1;
		typedQuery.setParameter("key", id);
		
		ArrayList<ArtistEntity> arrayList = (ArrayList<ArtistEntity>) typedQuery.getResultList();
		logger.info("listelendi " + ArtistEntity.class);
		return arrayList;
	}
	
	// find
	@Override
	public ArtistEntity find(long id) {
		Session session = databaseConnectionHibernate();
		ArtistEntity artistEntity;
		try {
			artistEntity = session.find(ArtistEntity.class, id);
			
			if (artistEntity != null) {
				System.out.println("bulundu... " + artistEntity);
				return artistEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + ArtistController.class);
			e.printStackTrace();
		}
		return null;
	}
	
}