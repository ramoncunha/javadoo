package br.com.caelum.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtil {
	private JPAUtil(){
		
	}
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
	
	public static EntityManager getEntityManager () {
		return emf.createEntityManager();
	}
}
/*// JPQL JPA Query Language
Query<Conta> query = em.createQuery("select c from Conta c  join c.transacoes t where t.valor > 10000").;
query.setParameter(":cpf", "11.1111.1111-00");

List<Conta> contas - query.list();*/
