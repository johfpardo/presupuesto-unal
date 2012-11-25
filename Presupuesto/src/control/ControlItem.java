package control;

import dao.ItemDAO;
import dao.PresupuestoDAO;
import dao.RubroDAO;
import entidad.Item;
import entidad.Presupuesto;
import entidad.Rubro;
import java.util.Date;
import java.util.List;

// <editor-fold defaultstate="collapsed" desc=" UML Marker ">
// #[regen=yes,id=DCE.67AE3A80-8B04-C9D7-22FF-D9E9001B370D]
// </editor-fold>
public class ControlItem {

    private ItemDAO dao = new ItemDAO();
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.829222B1-BE95-9AA1-A6EF-691BBFBDCF06]
    // </editor-fold>
    public ControlItem() {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,regenBody=yes,id=DCE.EE6B25AE-BC3C-7630-058D-59A6F7BF8020]
    // </editor-fold>
    public String editarItem(long idItem, Rubro rubro, String nuevoNombre, float nuevoPresupuestoAprobado) {
        
        Item item = this.getItem(idItem);        
        if (item == null) {
            return "Edición fallida";
        } else {
            if (!verificarLongitudNombre(nuevoNombre)) {
                return "Nombre inválido";
            }
            if (nuevoPresupuestoAprobado < 0) {
                return "Monto de dinero inválido";
            }
            if (!verificarValidezDineroAsignado(nuevoPresupuestoAprobado - item.getDineroEjecutado(), rubro)) {
                return "Monto de dinero inválido";
            }
            rubro.actualizarPresupuestoEjecutado(nuevoPresupuestoAprobado - item.getDineroEjecutado());
            Presupuesto p = rubro.getPresupuesto();
            p.actualizarPresupuestoEjecutado(nuevoPresupuestoAprobado - item.getDineroEjecutado());
            item.setFecha(new Date());
            item.setNombreItem(nuevoNombre);
            item.setDineroEjecutado(nuevoPresupuestoAprobado);
            //Se actualiza todo el sistema despues de la accion 
            dao.actualizar(item);
            new RubroDAO().actualizar(rubro);
            new PresupuestoDAO().actualizar(p);
            return "Modificación del Item Satisfactoria";
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.33DCB916-7077-7EB5-CAF8-5420745ADDE7]
    // </editor-fold>
    public Item getItem(String nombrePresupuesto,String nombreRubro, String nombreItem){
    
        return dao.leer(nombrePresupuesto, nombreRubro, nombreItem);
    }
    
    public Item getItem(long idItem) {
        //Se busca el item requerido, si no lo encuentra retorna null
        return dao.leer(idItem);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.D8D9AA5C-AFEC-E182-B20F-AC3E36413F77]
    // </editor-fold>
    public boolean verificarLongitudNombre(String nombre) {
        //Si la longitud del nombre tiene de 5 a 30 caracteres se acepta
        if (nombre.length() >= 5 && nombre.length() < 30) {
            return true;
        }
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.532FE36B-9CC5-AF40-EC27-39CA4B98CE1E]
    // </editor-fold>
    public boolean verificarValidezDineroAsignado(float dinero, Rubro rubro) {
        if (rubro.getPresupuestoAprobado() < rubro.getPresupuestoEjecutado() + dinero) {
            return false;
        }
        //si la cantidad de dinero es positiva y si el nuevo presupuesto comprometido
        //es menor o igual que el presupuesto aprobado, se retorna true
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.26F74196-0274-E8E8-77DE-94086FD5C5D5]
    // </editor-fold>
    public boolean noRepetido(String nombreItem, Rubro rubro) {
        //Por medio del for el recorre todo el ArrayList buscando si no hay otro con el mismo nombre
        //Si lo encuentra retorna false y si no retorna true
        for (Item i : dao.leerDeRubro(rubro.getId())) {
            if (i.getNombreItem().equals(nombreItem)) {
                return false;
            }
        }
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.7AB5882E-7139-A136-C56E-4C0133CE4C53]
    // </editor-fold>
    public List<Item> getItems(Rubro rubro) {
        //Se obtiene los items del rubro mencionado
        return dao.leerDeRubro(rubro.getId());
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.3E8D452D-8188-0E28-06F0-B2641E912012]
    // </editor-fold>
    public String agregarItem(Item item, Rubro rubro) {
        //Se verifica la longitud del nombre
        if (!verificarLongitudNombre(item.getNombreItem())) {
            return "Nombre Invalido";
        }
        //Se verifica si el valor puede ser aceptado
        if (item.getDineroEjecutado() <= 0 || !verificarValidezDineroAsignado(item.getDineroEjecutado(), rubro)) {
            return "Monto de dinero no valido";
        }
        //Se busca si hay algun item con el mismo nombre
        if (!noRepetido(item.getNombreItem(), rubro)) {
            return "Nombre ya existente";
        }
        item.setRubro(rubro);
        //Se agrega el item al modelo,
        //rubro.agregarItem(item);
        //Se actualiza el valor comprometido del rubro asociado al item
        rubro.actualizarPresupuestoEjecutado(item.getDineroEjecutado());
        //Se actualiza el valor comprometido del presupuesto asociado al item
        Presupuesto p = rubro.getPresupuesto();
        p.actualizarPresupuestoEjecutado(item.getDineroEjecutado());
        //Se actualiza todo el sistema despues de la accion 
        dao.crear(item);
        new RubroDAO().actualizar(rubro);
        new PresupuestoDAO().actualizar(p);
        return "Item creado Satisfactoriamente";
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.21469297-AD54-15D1-C2B3-247F5E2170AA]
    // </editor-fold>
    public String eliminarItem(long idItem) {
        //Se le manda al rubro el id del item que debe borrar
        Item item = dao.leer(idItem);
        Rubro rubro = item.getRubro();
        Presupuesto presupuesto = rubro.getPresupuesto();
            if (item != null) {
                rubro.actualizarPresupuestoEjecutado(-item.getDineroEjecutado());
                rubro.getItems().remove(item);
                presupuesto.actualizarPresupuestoEjecutado(-item.getDineroEjecutado());
                //Se actualiza todo el sistema despues de la accion 
                dao.eliminar(item);
                new RubroDAO().actualizar(rubro);
                new PresupuestoDAO().actualizar(presupuesto);
                return "Item eliminado satisfactoriamente";
            }
        return "Item inexistente, eliminacion no satisfactoria";
    }

    private boolean idDisponible(Rubro rubro, int id) {
        int a = 0;
        for (Item i : rubro.getItems()) {
            if (i.getId() == id) {
                return false;
            }
            a++;
        }
        return true;
    }
}
