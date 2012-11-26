/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidad.Presupuesto;
import java.util.List;
import javax.persistence.*;

public class PresupuestoDAO{

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PresupuestoPU");

    public void crear(Presupuesto object) {
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

    public boolean eliminar(Presupuesto object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            em.remove(object);
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

    public Presupuesto leer(Presupuesto par) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Presupuesto planeacion = null;
        Query q = em.createQuery("SELECT p from Presupuesto p WHERE p.nombrePlaneacion LIKE :nombre")
                .setParameter("nombre", par.getNombrePlaneacion());
        try {
                planeacion = (Presupuesto) q.getSingleResult();
        } catch (NonUniqueResultException e) {
                planeacion = (Presupuesto) q.getResultList().get(0);
        }  catch (NoResultException e) {
        } catch (Exception e) {
            
        } finally {
            em.close();
            return planeacion;
        }
    }

    public Presupuesto leer(String nombrePresupuesto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Presupuesto planeacion = null;
        Query q = em.createQuery("SELECT p from Presupuesto p "
                + "WHERE p.nombrePlaneacion LIKE :nombrePlaneacion")
                .setParameter("nombrePlaneacion", nombrePresupuesto);
        try {
            planeacion = (Presupuesto) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            planeacion = (Presupuesto) q.getResultList().get(0);
        } catch (NoResultException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return planeacion;
        }
    }

    public Presupuesto leer(long presupuestoId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Presupuesto planeacion = null;
        Query q = em.createQuery("SELECT p from Presupuesto p "
                + "WHERE p.id = :id")
                .setParameter("id", presupuestoId);
        try {
            planeacion = (Presupuesto) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            planeacion = (Presupuesto) q.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return planeacion;
        }
    }

    public List<Presupuesto> leer() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT p FROM Presupuesto p");
        List r = q.getResultList();
        em.close();
        return r;
    }

    public boolean actualizar(Presupuesto object, Presupuesto nuevoObjeto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            object = leer(object);
            object.setPresupuestoComprometido(nuevoObjeto.getPresupuestoComprometido());
            object.setPresupuestoEjecutado(nuevoObjeto.getPresupuestoEjecutado());
            object.setRubros(nuevoObjeto.getRubros());
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

    public boolean actualizar(Presupuesto nuevoObjeto) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            Presupuesto object = leer(nuevoObjeto);
            object.setPresupuestoComprometido(nuevoObjeto.getPresupuestoComprometido());
            object.setPresupuestoEjecutado(nuevoObjeto.getPresupuestoEjecutado());
            object.setRubros(nuevoObjeto.getRubros());
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
}
