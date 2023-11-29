/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatec2.turnero.persistencia;

import com.pruebatec2.turnero.logica.Ciudadanos;
import com.pruebatec2.turnero.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Oscar
 */
public class CiudadanosJpaController implements Serializable {

    public CiudadanosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CiudadanosJpaController() {
        emf = Persistence.createEntityManagerFactory("turneroPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ciudadanos ciudadanos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ciudadanos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ciudadanos ciudadanos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ciudadanos = em.merge(ciudadanos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ciudadanos.getId();
                if (findCiudadanos(id) == null) {
                    throw new NonexistentEntityException("The ciudadanos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudadanos ciudadanos;
            try {
                ciudadanos = em.getReference(Ciudadanos.class, id);
                ciudadanos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudadanos with id " + id + " no longer exists.", enfe);
            }
            em.remove(ciudadanos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ciudadanos> findCiudadanosEntities() {
        return findCiudadanosEntities(true, -1, -1);
    }

    public List<Ciudadanos> findCiudadanosEntities(int maxResults, int firstResult) {
        return findCiudadanosEntities(false, maxResults, firstResult);
    }

    private List<Ciudadanos> findCiudadanosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciudadanos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ciudadanos findCiudadanos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ciudadanos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCiudadanosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudadanos> rt = cq.from(Ciudadanos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
