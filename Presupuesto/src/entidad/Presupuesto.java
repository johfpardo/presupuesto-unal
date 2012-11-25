package entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Presupuesto implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombrePlaneacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPlaneacion;
    private float presupuestoAprobado;
    private float presupuestoComprometido;
    private float presupuestoEjecutado;
    @OneToMany(mappedBy = "presupuesto")
    private List<Rubro> rubros;

    public Presupuesto () {
    }

    public Presupuesto(String nombre, float aprobado) {
        this.nombrePlaneacion=nombre;
        this.fechaPlaneacion=new Date();
        this.presupuestoAprobado=aprobado;
        this.presupuestoComprometido=0;
        this.presupuestoEjecutado=0;
    }
    
    public Presupuesto(String[] datos) {
        this(datos[0],Float.parseFloat(datos[1]));
    }
    
    public float getPresupuestoEjecutado() {
        return presupuestoEjecutado;
    }

    public void setPresupuestoEjecutado(float presupuestoEjecutado) {
        this.presupuestoEjecutado = presupuestoEjecutado;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.7A520A8A-8462-3202-8301-B60223B1E3EA]
    // </editor-fold> 
    public Date getFechaPlaneacion () {
        return fechaPlaneacion;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.D5AAB5CC-1249-C5CC-4BB1-C470175F3755]
    // </editor-fold> 
    public void setFechaPlaneacion (Date val) {
        this.fechaPlaneacion = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C582AF48-2071-AE5E-66D1-BD873F277B79]
    // </editor-fold> 
    public String getNombrePlaneacion () {
        return nombrePlaneacion;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9A214507-7049-9C79-9AD0-2491D3D6FDEC]
    // </editor-fold> 
    public void setNombrePlaneacion (String val) {
        this.nombrePlaneacion = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.23A00767-38B5-8A0B-3BBF-6A3FF0887FDB]
    // </editor-fold> 
    public float getPresupuestoAprobado () {
        return presupuestoAprobado;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0A698A74-E94D-89CE-CF07-ED786FF58016]
    // </editor-fold> 
    public void setPresupuestoAprobado (float val) {
        this.presupuestoAprobado = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5F21A407-8527-5D18-FEB3-B8332434CE3E]
    // </editor-fold> 
    public float getPresupuestoComprometido () {
        return presupuestoComprometido;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.EB28F230-98E2-F4B4-A5E3-3DC04217CF5F]
    // </editor-fold> 
    public void setPresupuestoComprometido (float val) {
        this.presupuestoComprometido = val;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F5A6A435-65B1-3DBA-7756-F63FC28088CB]
    // </editor-fold> 
    public void actualizarPresupuestoComprometido (float valor) {
        //Al presupuesto comprometido le sumo el valor de entrada
        this.presupuestoComprometido+=valor;
    }

    public void actualizarPresupuestoEjecutado(float f) {
        this.presupuestoEjecutado+=f;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Rubro> getRubros() {
        return rubros;
    }

    public void setRubros(List<Rubro> rubros) {
        this.rubros = rubros;
    }
    
}