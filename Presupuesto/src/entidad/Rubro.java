package entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Rubro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String nombreRubro;
    private float presupuestoAprobado;
    private float presupuestoEjecutado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "rubro")
    private List<Item> items;
    @ManyToOne
    private Presupuesto presupuesto;

    public Rubro() {
    }

    public Rubro(String nombre, float pa) {
        this.nombreRubro = nombre;
        this.presupuestoAprobado = pa;
        this.presupuestoEjecutado = 0;
        this.fechaModificacion = new Date();
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date val) {
        this.fechaModificacion = val;
    }

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }

    public String getNombreRubro() {
        return nombreRubro;
    }

    public void setNombreRubro(String val) {
        this.nombreRubro = val;
    }

    public float getPresupuestoAprobado() {
        return presupuestoAprobado;
    }

    public void setPresupuestoAprobado(float val) {
        this.presupuestoAprobado = val;
    }

    public float getPresupuestoEjecutado() {
        return presupuestoEjecutado;
    }

    public void setPresupuestoEjecutado(float presupuestoEjecutado) {
        this.presupuestoEjecutado = presupuestoEjecutado;
    }

    public void agregarItem(Item item) {
        this.items.add(item);
    }

    public void setRubro(Rubro val) {
        this.nombreRubro = val.nombreRubro;
        this.presupuestoAprobado = val.presupuestoAprobado;
        this.presupuestoEjecutado = val.presupuestoEjecutado;
        this.fechaModificacion = new Date();
    }

    public void actualizarPresupuestoApropiado(float f) {
        this.presupuestoAprobado += f;
    }

    public void actualizarPresupuestoEjecutado(float f) {
        this.presupuestoEjecutado += f;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Object[] toObjectArray() {
        Object datos[] = new Object[5];
        datos[0] = getNombreRubro();
        datos[1] = getFechaModificacion();
        datos[2] = getPresupuestoAprobado();
        datos[3] = getPresupuestoEjecutado();
        datos[4] = getPresupuestoAprobado() - getPresupuestoEjecutado();
        return datos;
    }
}
