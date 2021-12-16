package com.recordstore.control;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.recordstore.entity.AdminEntity;
import com.recordstore.utils.IDatabaseCrud;

public class AdminController implements IDatabaseCrud<AdminEntity>, Serializable {
	
	private static final long serialVersionUID = 804193454661192393L;
	
	private static final Logger logger = LogManager.getLogger(AdminController.class);
	
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
	public void create(AdminEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			logger.info("ekleme tamamdır" + AdminController.class);
		} catch (Exception e) {
			logger.error("ekleme anında hata meydana geldi !!!!! " + AdminController.class);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete(AdminEntity entity) {
		// This method will not be used
		
	}
	
	@Override
	public void update(AdminEntity entity) {
		// This method will not be used
		
	}
	
	public ArrayList<AdminEntity> list() {
		Session session = databaseConnectionHibernate();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<AdminEntity> criteria = builder.createQuery(AdminEntity.class);
		criteria.from(AdminEntity.class);
		
		ArrayList<AdminEntity> admins = (ArrayList<AdminEntity>) session.createQuery(criteria).getResultList();
		
		// if (users.size() > 0) {
		// LogUtil.getInstance().logInfo("Kayitlar bulundu.");
		// } else
		// LogUtil.getInstance().logInfo("Listelenecek kayit bulunamadi !");
		
		return admins;
	}
	
}