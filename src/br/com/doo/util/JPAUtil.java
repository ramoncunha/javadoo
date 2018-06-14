package br.com.doo.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtil {
	private JPAUtil(){
		
	}
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("doonfe");
	
	public static EntityManager getEntityManager () {
		return emf.createEntityManager();
	}
}
