package control;

import dao.PresupuestoDAO;
import entidad.Presupuesto;
import java.util.List;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.A1F8B963-4B2F-0FD0-B762-BAA002123F13]
// </editor-fold> 
public class ControlPresupuesto {
    
    private PresupuestoDAO dao = new PresupuestoDAO();
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5A09A392-170C-0C9B-0CC0-AB6087D8DABA]
    // </editor-fold> 
    public ControlPresupuesto() {
    }

    public boolean verificarLongitudNombre(String nombrePlaneacion) {
        //Verifica que la longitud del nombre no sea menor a 5 ni mayor a 30
        return (nombrePlaneacion.length() >= 5 && nombrePlaneacion.length() <= 30);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D6135295-13E6-CE5F-205D-AB4B91DBF984]
    // </editor-fold> 
    public boolean nombreDisponible(String nombrePlaneacion) {
        //Revisa si la cadena de entrada es diferente a los nombres de las planeaciones,
        //si es asi, la cadena estÃ¡ disponible
        return (dao.leer(nombrePlaneacion)==null);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.51B47C0A-4375-DFD2-E9C3-ABDC18629339]
    // </editor-fold> 
    public boolean verificarAsignado(float asignado) {
        //Revisa que el presupuesto asignado no sea negativo
        return (asignado > 0);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.733A7352-607C-F9F6-D108-8E3D60919AFD]
    // </editor-fold> 
    public Presupuesto getPresupuesto(String nombre) {
        //Busca entre los presupuestos del sistema el que tenga como nombre la cadena de entrada
        //y retorna el apuntador
        if (nombre != null) {
                return dao.leer(nombre);
        }
        //si no lo encuentra, retorna NULL
        return null;
    }

    public List<Presupuesto> getPresupuesto() {
        //Retorna el apuntador al los presupuestos del sistema
        return dao.leer();
    }

    public String crearPresupuesto(Presupuesto planeacion) {
        //Verifica la longitud del nombre
        if (!this.verificarLongitudNombre(planeacion.getNombrePlaneacion())) {
            return "Longitud de Nombre Incorrecto";
        }
        //Verifica la disponibilidad del nombre
        if (!this.nombreDisponible(planeacion.getNombrePlaneacion())) {
            return "Nombre no Disponible";
        }
        //Verifica la validez del valor asignado al presupuesto
        if (!this.verificarAsignado(planeacion.getPresupuestoAprobado())) {
            return "Valor invalido en el presupuesto";
        }

        //Sistema.msistema.getPresupuesto().add(planeacion);
        dao.crear(planeacion);
        if(dao.leer(planeacion)!=null)
            return "Planeacion Creada";
        return "Error al crear la Planeación";
    }
}