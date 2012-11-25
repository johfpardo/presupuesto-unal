package entidad;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.AB87B40F-18D2-B209-E5F4-B4EF24DF31E4]
// </editor-fold>
@Entity
public class Item implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.553E3228-B8A7-D04E-A96D-5562841A4641]
    // </editor-fold> 
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private long id;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C1D93F03-1522-0F45-2661-76FE27351B37]
    // </editor-fold> 
    private String nombreItem;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.171940A1-6E39-2B46-5DE7-72FA4E443F46]
    // </editor-fold> 
    private float dineroAsignado;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1ED9519E-EEF5-82AD-337F-8EE321BA2ACC]
    // </editor-fold> 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @ManyToOne
    private Rubro rubro;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.186267B3-0764-23EB-7252-B00D15357839]
    // </editor-fold> 
    public Item () {
    }

    public Item(String[] datos) {
    
        this.nombreItem = datos[0];
        try{
            this.dineroAsignado = Float.parseFloat(datos[1]);
        }catch(NumberFormatException t){
            this.dineroAsignado=-120;
        }
        //Metodo para meter la Fecha
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaEnviar = null;
        try {
            fechaEnviar = formatoDelTexto.parse(datos[2]);
            this.fecha = fechaEnviar;
        } catch (ParseException ex) {
            this.fecha = new Date();
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.37DA8E59-EFA1-CF32-41F0-465EAA106915]
    // </editor-fold> 
    public float getDineroEjecutado () {
        return dineroAsignado;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.57576991-D457-7340-F2F1-2EA59DBAB860]
    // </editor-fold> 
    public void setDineroEjecutado (float val) {
        this.dineroAsignado = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.BC080534-5963-EC9D-44E3-CA48715BC33D]
    // </editor-fold> 
    public Date getFecha () {
        return fecha;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3D9DDCEB-AED1-D493-4382-CE896CDE2444]
    // </editor-fold> 
    public void setFecha (Date val) {
        this.fecha = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2BCAB584-1D70-B76D-5768-B5DC60623E37]
    // </editor-fold> 
    public long getId () {
        return id;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B92B8DA9-8BA1-7903-F4F9-CC8B7DDEAA63]
    // </editor-fold> 
    public void setId (long val) {
        this.id = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.49DEAA65-3FBF-2249-BDC3-B7E4E5AE9C9F]
    // </editor-fold> 
    public String getNombreItem () {
        return nombreItem;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.FFA8034D-7D94-6246-9A8D-AB62E7F6ABA3]
    // </editor-fold> 
    public void setNombreItem (String val) {
        this.nombreItem = val;
    }
    
    public void setItem(Item item) {
        this.nombreItem = item.getNombreItem();
        this.dineroAsignado = item.getDineroEjecutado();
        this.fecha = item.getFecha();
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }
}

