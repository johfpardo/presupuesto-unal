package control;

import entidad.Item;
import entidad.Presupuesto;
import entidad.Rubro;
import entidad.Sistema;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.4ECAC819-AA97-E620-8D94-98B1FC843D3E]
// </editor-fold> 
public class ControlSistema {

    private Sistema mSistema;
    private ControlPresupuesto mControlPresupuesto = new ControlPresupuesto();
    private ControlRubro mControlRubro = new ControlRubro();
    private ControlItem mControlItem = new ControlItem();

    public ControlSistema() {
    }

    public ControlItem getControlItem() {
        return mControlItem;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.DDEB65DC-6D52-EEA3-3D6A-E6FE1097F323]
    // </editor-fold> 
    public void setControlItem(ControlItem val) {
        this.mControlItem = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.AFCC93DC-C4ED-95CD-A4CE-2A9C28BFB401]
    // </editor-fold> 
    public ControlPresupuesto getControlPresupuesto() {
        return mControlPresupuesto;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.034AB6D0-65BA-A614-A35E-4743F50E2FCD]
    // </editor-fold> 
    public void setControlPresupuesto(ControlPresupuesto val) {
        this.mControlPresupuesto = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.7715FFEE-5501-62AD-C256-3893425F65ED]
    // </editor-fold> 
    public ControlRubro getControlRubro() {
        return mControlRubro;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9500FB92-F7AF-1759-52AF-18B17AF9922B]
    // </editor-fold> 
    public void setControlRubro(ControlRubro val) {
        this.mControlRubro = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C2042D03-83BA-912F-EE88-1E711A305080]
    // </editor-fold> 
    public Sistema getSistema() {
        return mSistema;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.43318813-3307-94C7-350F-A03D253EA9B1]
    // </editor-fold> 
    public void setSistema(Sistema val) {
        this.mSistema = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2FE19B3E-B9C0-4DF5-2BDE-26AE3F0898C2]
    // </editor-fold> 
    public String crearPresupuesto(String[] datos) {
        try {
            return mControlPresupuesto.crearPresupuesto(new Presupuesto(datos[0], Float.parseFloat(datos[1])));
        } catch (Exception e) {
            return "Datos Invalidos";
        }
    }

    public Object[] getPresupuesto(String nombre) {
        //retorna el presupuesto correspondiente al nombre dado
        Presupuesto p = mControlPresupuesto.getPresupuesto(nombre);
        if (p == null) {
            return null;
        }
        Object[] o = {p.getNombrePlaneacion(), p.getFechaPlaneacion(), p.getPresupuestoAprobado(),
            p.getPresupuestoComprometido(), p.getPresupuestoAprobado() - p.getPresupuestoComprometido(),
            p.getPresupuestoEjecutado(), p.getPresupuestoAprobado() - p.getPresupuestoEjecutado()};
        return o;
    }

    public List<Object[]> getPresupuesto() {
        //retorna el presupuesto correspondiente al nombre dado
        List<Presupuesto> planeaciones = mControlPresupuesto.getPresupuesto();
        List<Object[]> lista = new ArrayList<>();
        for (Presupuesto p : planeaciones) {
            Object[] o = {p.getNombrePlaneacion(), p.getFechaPlaneacion(), p.getPresupuestoAprobado(),
                p.getPresupuestoComprometido(), p.getPresupuestoAprobado() - p.getPresupuestoComprometido(),
                p.getPresupuestoEjecutado(), p.getPresupuestoAprobado() - p.getPresupuestoEjecutado()};
            lista.add(o);
        }
        return lista;
    }

    public Object[] getItem(String nombrePresupuesto, String nombreRubro, String nombreItem) {

        Item i = mControlItem.getItem(nombrePresupuesto, nombreRubro, nombreItem);
        if (i != null) {
            Object[] o = {i.getNombreItem(), i.getFecha(), i.getDineroEjecutado(), i.getId()};
            return o;
        }
        return null;
    }

    public String editarItem(String nombrePresupuesto, String nombreRubro, long idItem, String[] datos) {
        //variables auxiliares
        Presupuesto p;
        Rubro r = null;
        float t = -120;

        p = mControlPresupuesto.getPresupuesto(nombrePresupuesto);//se identifica la planeación
        if (p != null) {
            r = mControlRubro.getRubro(p.getNombrePlaneacion(), nombreRubro); //se identifica el rubro
        }
        try {
            t = Float.parseFloat(datos[1]);
        } catch (NumberFormatException e) {
        }
        return mControlItem.editarItem(idItem, r, datos[0], t);   //se cambia el estado de Item
    }

    public Object[] getRubro(String nombrePresupuesto, String nombreRubro) {
        Rubro r = mControlRubro.getRubro(nombrePresupuesto, nombreRubro);
        if (r == null) {
            return null;
        }
        return r.toObjectArray();
    }

    public String editarRubro(String nombrePresupuesto, String nombreRubro, String[] datos) {
        float p = -120;
        try {
            p = Float.parseFloat(datos[1]);
        } catch (NumberFormatException e) {
        }
        return mControlRubro.editarRubro(nombrePresupuesto, nombreRubro, datos[0], p); //se cambia el estado del rubro

    }

    public String crearRubro(String[] datos, String nombrePresupuesto) {
        return mControlRubro.agregarRubro(nombrePresupuesto, datos);
    }

    public String crearItem(String[] datos, String nombrePlaneacion, String nombreRubro) {
        //variables auxiliares
        Rubro r;
        r = mControlRubro.getRubro(nombrePlaneacion, nombreRubro); //se identifica el item
        return mControlItem.agregarItem(new Item(datos), r); //se crea el item
    }

    public String eliminarItem(long idItem) {

        return mControlItem.eliminarItem(idItem);  //se elimina el item
    }

    public String eliminarRubro(String nombrePresupuesto, String nombreRubro) {
        return mControlRubro.eliminarRubro(nombrePresupuesto, nombreRubro);
    }

    public String transferirFondos(String nombrePresupuesto, String nombreFuente, String nombreDestino, float monto) {
        return mControlRubro.transferirFondos(nombrePresupuesto, nombreFuente, nombreDestino, monto);// se realiza la transferencia

    }

    public List<Object[]> getRubro(String nombrePresupuesto) {
        ArrayList<Object[]> a = new ArrayList<Object[]>();
        for (Rubro r : mControlRubro.getRubros(nombrePresupuesto)) {
            a.add(r.toObjectArray());
        }
        return a;
    }

    public List<Object[]> getItem(String presupuestoSeleccionado, String rubroSeleccionado) {
        //variables auxiliares
        Rubro r;
        r = mControlRubro.getRubro(presupuestoSeleccionado, rubroSeleccionado); //se identifica el rubro
        List<Item> items = mControlItem.getItems(r);
        List<Object[]> lista = new ArrayList<>();
        for (Item i : items) {
            Object[] o = {i.getNombreItem(), i.getFecha(), i.getDineroEjecutado()};
            lista.add(o);
        }
        return lista;
    }
}
