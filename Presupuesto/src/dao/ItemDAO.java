/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidad.Item;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author John Fredy
 */
public class ItemDAO {
    
    private static EntityManagerFactory
            emf = Persistence.createEntityManagerFactory("PresupuestoPU");

    public void crear(Item object) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

     public boolean eliminar(Item object) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            em.remove(em.merge(object));
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }
    }

    public Item leer(long idItem) {

        EntityManager em = emf.createEntityManager();
        Item item = null;
        Query q = em.createQuery("SELECT i from Item i " +
                "WHERE i.id = :id")
                .setParameter("id", idItem);
        try {
            item = (Item) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            item = (Item) q.getResultList().get(0);
        } catch (NoResultException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return item;
        }
    }
    
    public Item leer(String nombrePresupuesto, String nombreRubro,String nombreItem) {

        EntityManager em = emf.createEntityManager();
        Item item = null;
        Query q = em.createQuery("SELECT i from Item i " +
                "WHERE i.nombreItem LIKE :nombreItem" +
                " AND i.rubro.nombreRubro LIKE :nombreRubro" +
                " AND i.rubro.presupuesto.nombrePlaneacion LIKE :nombrePresupuesto")
                .setParameter("nombreItem", nombreItem)
                .setParameter("nombreRubro", nombreRubro)
                .setParameter("nombrePresupuesto", nombrePresupuesto);
        try {
            item = (Item) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            item = (Item) q.getResultList().get(0);
        } catch (NoResultException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return item;
        }
    }

    public boolean actualizar(Item object, Item nuevoObjeto) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            object = leer(object.getId());
            object.setNombreItem(nuevoObjeto.getNombreItem());
            object.setDineroEjecutado(nuevoObjeto.getDineroEjecutado());
            object.setFecha(nuevoObjeto.getFecha());
            em.merge(object);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }
    }
    
    public boolean actualizar(Item item) {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret=false;
        try {
            em.merge(item);
            em.getTransaction().commit();
            ret=true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }
    }

    public List<Item> leerDeRubro(long idRubro) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT i from Item i " +
                "WHERE i.rubro.id = :idRubro" )
                .setParameter("idRubro", idRubro);
        List items = q.getResultList();
        em.close();
        return items;
    }
}
