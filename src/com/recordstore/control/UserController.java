package com.recordstore.control;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.recordstore.entity.UserEntity;
import com.recordstore.utils.IDatabaseCrud;

public class UserController implements IDatabaseCrud<UserEntity>, Serializable {
	
	private static final long serialVersionUID = 804193454661192393L;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
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
	public void create(UserEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + UserController.class);
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + UserController.class);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete(UserEntity entity) {
		
		try {
			UserEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				logger.info("Silme Başarılı " + UserEntity.class);
			}
		} catch (Exception e) {
			logger.error("silme anında hata meydana geldi !!!!! " + UserController.class);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(UserEntity entity) {
		try {
			UserEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				findEntity.setName(entity.getName());
				findEntity.setLastName(entity.getLastName());
				findEntity.setEmail(entity.getEmail());
				findEntity.setPassword(entity.getPassword());
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				logger.info("Güncelleme Başarılı " + UserEntity.class);
			}
			
		} catch (Exception e) {
			logger.error("güncelleme anında hata meydana geldi !!!!! " + UserController.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<UserEntity> list() {
		Session session = databaseConnectionHibernate();
		
		// unutma: buradaki sorgulama entity sorgulaması yani java classına göre
		// çağıracağız.
		String hql = "select str from CityEntity as str where str.id>=:key";
		TypedQuery<UserEntity> typedQuery = session.createQuery(hql, UserEntity.class);
		
		int id = 1;
		typedQuery.setParameter("key", id);
		
		ArrayList<UserEntity> arrayList = (ArrayList<UserEntity>) typedQuery.getResultList();
		logger.info("listelendi " + UserEntity.class);
		return arrayList;
	}
	
	// find
	@Override
	public UserEntity find(long id) {
		Session session = databaseConnectionHibernate();
		UserEntity userEntity;
		try {
			userEntity = session.find(UserEntity.class, id);
			
			if (userEntity != null) {
				System.out.println("bulundu... " + userEntity);
				return userEntity;
			} else {
				System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
				return null;
			}
		} catch (Exception e) {
			logger.error("find anında hata meydana geldi !!!!! " + UserController.class);
			e.printStackTrace();
		}
		return null;
	}
	
}