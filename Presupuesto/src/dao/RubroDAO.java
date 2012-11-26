/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.ItemDAO;
import entidad.Item;
import entidad.Presupuesto;
import entidad.Rubro;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author fredy
 */
public class RubroDAO{
    private static EntityManagerFactory 
            emf = Persistence.createEntityManagerFactory("PresupuestoPU");

    public void crear(Rubro object) {
        
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
    
    public boolean eliminar(Rubro object) {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret=false;
        try {
            ItemDAO itemDao=new ItemDAO();
            List<Item> items=itemDao.leerDeRubro(object.getId());
            for(Item i:items)
                itemDao.eliminar(i);
            em.remove(em.merge(object));
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
    
    public boolean eliminar(String p,String r) {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret=false;
        try {
            ItemDAO itemDao=new ItemDAO();
            Rubro object=leer(p,r);
            List<Item> items=itemDao.leerDeRubro(object.getId());
            for(Item i:items)
                itemDao.eliminar(i);
            em.remove(object);
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
    
    public Rubro leer(String nombreRubro,long idPresupuesto) {
        
        EntityManager em = emf.createEntityManager();
        Rubro rubro=null;
        Query q=em.createQuery("SELECT r FROM Rubro r WHERE r.nombreRubro =:nombreRubro"
                + " AND r.presupuesto.id = :presupuestoId")
                .setParameter("nombreRubro", nombreRubro)
                .setParameter("presupuestoId", idPresupuesto);
        try{
            rubro=(Rubro)q.getSingleResult();
        } catch (NonUniqueResultException e){
            rubro=(Rubro)q.getResultList().get(0);
        } catch (NoResultException e) {
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            em.close();
            return rubro;
        }
    }
    
    public Rubro leer(String nombreRubro,String nombrePresupuesto) {
        Presupuesto p=new PresupuestoDAO().leer(nombrePresupuesto);
        if(p!=null){
        return leer(nombreRubro,p.getId());}
        return null;
    }
    
    public Rubro leer(long idRubro) {
        
        EntityManager em = emf.createEntityManager();
        Rubro rubro=null;
        Query q=em.createQuery("SELECT r FROM Rubro r WHERE r.id =:id")
                .setParameter("id", idRubro);
        try{
            rubro=(Rubro)q.getSingleResult();
        } catch (NonUniqueResultException e){
            rubro=(Rubro)q.getResultList().get(0);
        } catch (NoResultException e) {
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            em.close();
            return rubro;
        }
    }
    
    public List<Rubro> leerDePresupuesto(long presupuestoId){
        
        EntityManager em = emf.createEntityManager();
        Query q=em.createQuery("SELECT r FROM Rubro r WHERE r.presupuesto.id=:presupuesto")
                .setParameter("presupuesto", new PresupuestoDAO().leer(presupuestoId));
        List r = q.getResultList();
        em.close();
        return r;
    }
    
    public List<Rubro> leerDePresupuesto(String nombrePresupuesto){
        
        EntityManager em = emf.createEntityManager();
        Query q=em.createQuery("SELECT r FROM Rubro r WHERE r.presupuesto=:presupuesto")
                .setParameter("presupuesto", new PresupuestoDAO().leer(nombrePresupuesto));
        List r = q.getResultList();
        em.close();
        return r;
    }
    
    public boolean actualizar(String nombrePresupuesto,String nombreRubro,String nuevoNombre,float nuevoPresupuestoAprobado) {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret=false;
        try {
            Rubro object=leer(nombreRubro,nombrePresupuesto);
            boolean x=false;
            if(nuevoNombre!=null){object.setNombreRubro(nuevoNombre);x=true;}
            if(nuevoPresupuestoAprobado!=0){object.setPresupuestoAprobado(nuevoPresupuestoAprobado);x=true;}
            object.setFechaModificacion(new Date());
            em.merge(object);
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
    public boolean actualizar(Rubro rubro) {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret=false;
        try {
            rubro.setFechaModificacion(new Date());
            em.merge(rubro);
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
}
