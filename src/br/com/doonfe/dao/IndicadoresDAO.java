package br.com.doonfe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.doonfe.modelo.NotaFiscal;
import br.com.doonfe.util.JPAUtil;

public class IndicadoresDAO {
	
	public List<NotaFiscal> getNotasFiscais() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		String jpql = "select nf from NotaFiscal nf";
		TypedQuery<NotaFiscal> query = em.createQuery(jpql, NotaFiscal.class);
		
		List<NotaFiscal> notaFiscal = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return notaFiscal;
	}
	
	public Long totalNotasFicaisCadastradas() {
		
		try {
			EntityManager em = JPAUtil.getEntityManager();
			
			em.getTransaction().begin();
			
			String jpql = "select count(nf) from NotaFiscal nf";
			Query query = em.createQuery(jpql);
			
			Long totalResult = (Long) query.getSingleResult();
			
			em.getTransaction().commit();
			em.close();
			
			return totalResult;
		} catch (NoResultException error) {
			JOptionPane.showMessageDialog(null, "Não há nota fiscal cadastrada!");
		}
		
		return null;
	}
	
	public Double mediaValorNotas() {
		
		try {
			EntityManager em = JPAUtil.getEntityManager();
			
			em.getTransaction().begin();
	
			String jpql = "select sum(i.preco*i.quantidade)/ count(i) from Itens i";
			Query query = em.createQuery(jpql);
			
			Double mediaValorNotas = (Double) query.getSingleResult();
			
			em.getTransaction().commit();
			em.close();
			
			return mediaValorNotas;
		} catch(NoResultException error) {
			JOptionPane.showMessageDialog(null, "Não há média das notas!");
		}
		return null;
	}
	
	public Double mediaValorItens() {
		
		try {
			EntityManager em = JPAUtil.getEntityManager();
			
			em.getTransaction().begin();
			
			String jpql = "select avg(i.preco) from Itens i";
			Query query = em.createQuery(jpql);
			
			Double media = (Double) query.getSingleResult();
			
			em.getTransaction().commit();
			em.close();
			
			return media;
		} catch(NoResultException error) {
			JOptionPane.showMessageDialog(null, "Não há média dos itens!");
		}
		return null;
	}
	
	public Double maiorValorNota() {
		
		try {
			EntityManager em = JPAUtil.getEntityManager();
			
			em.getTransaction().begin();
			
			String jpql = "select i.preco*i.quantidade as total from Itens i group by i.notaFiscal order by total desc";
			Query query = em.createQuery(jpql);
			
			Double maiorValorNota = (Double) query.setMaxResults(1).getSingleResult();
			
			em.getTransaction().commit();
			em.close();
			
			return maiorValorNota;
		} catch(NoResultException error) {
			JOptionPane.showMessageDialog(null, "Não há maior valor nota!");
		}
		return null;
	}
	
	public String estadoEmiteNf() {
	
		try {
			EntityManager em = JPAUtil.getEntityManager();
	
			em.getTransaction().begin();
			
			String jpql = "select max(p.estado) from Pessoa p, NotaFiscal nf where nf.emitente = p.id group by p.estado";
			Query query = em.createQuery(jpql);
			
			String estado = (String) query.setMaxResults(1).getSingleResult();
			
			em.getTransaction().commit();
			em.close();
			
			return estado;
		} catch(NoResultException error) {
			JOptionPane.showMessageDialog(null, "Não há estado que mais emitiu!");
		}
		return null;
	}
	
	public String estadoDestinoNf() {
		
		try {
			EntityManager em = JPAUtil.getEntityManager();
	
			em.getTransaction().begin();
			
			String jpql = "select max(p.estado) from Pessoa p, NotaFiscal nf where nf.destinatario = p.id group by p.estado";
			Query query = em.createQuery(jpql);
			
			String estado = (String) query.setMaxResults(1).getSingleResult();
			
			em.getTransaction().commit();
			em.close();
			
			return estado;
		} catch(NoResultException error) {
			JOptionPane.showMessageDialog(null, "Não há estado que mais comprou!");
		}
		return null;
	}
	
	public String destinatarioComprador() {
		
		try {
			EntityManager em = JPAUtil.getEntityManager();
	
			em.getTransaction().begin();
			
			String jpql = "select max(pj.razaoSocial) from PessoaJuridica pj, NotaFiscal nf, Itens i "
					+ "where nf.destinatario = pj.id and nf.id = i.notaFiscal group by pj.id";
			Query query = em.createQuery(jpql);
			
			String estado = (String) query.setMaxResults(1).getSingleResult();
			
			em.getTransaction().commit();
			em.close();		
			
			return estado;
		} catch(NoResultException error) {
			JOptionPane.showMessageDialog(null, "Não há maior comprador!");
		}
		return null;
	}
	
	public String emitenteComprador() {
		
		try {
			EntityManager em = JPAUtil.getEntityManager();
	
			em.getTransaction().begin();
			
			String jpql = "select max(pj.razaoSocial) from PessoaJuridica pj, NotaFiscal nf, Itens i "
					+ "where nf.emitente = pj.id and nf.id = i.notaFiscal group by pj.id";
			Query query = em.createQuery(jpql);
			
			String estado = (String) query.setMaxResults(1).getSingleResult();
			
			em.getTransaction().commit();
			em.close();		
			
			return estado;
		} catch(NoResultException error) {
			JOptionPane.showMessageDialog(null, "Não há maior vendedor!");
		}
		return null;
	}
	
	public Integer notasSuperiorDezMil() {
		
		Integer supeiorDezMil = 0;
		List<NotaFiscal> nf = getNotasFiscais();
		
		for (NotaFiscal notaFiscal : nf) {
			if(notaFiscal.getValorItens() > 10000d) {
				supeiorDezMil++;
			}
		}
		
		return supeiorDezMil;
	}
	
	public Integer notasMaiorDez() {
		
		Integer qtdItem = 0;
		List<NotaFiscal> nf = getNotasFiscais();
		
		for (NotaFiscal notaFiscal : nf) {
			if(notaFiscal.getQtdItem() > 10) {
				qtdItem++;
			}
		}
		
		return qtdItem;
	}
}
