package control;

import dao.ItemDAO;
import dao.PresupuestoDAO;
import dao.RubroDAO;
import entidad.Item;
import entidad.Presupuesto;
import entidad.Rubro;
import entidad.Sistema;
import java.util.Date;
import java.util.List;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.E9101591-7E8D-4D0B-A21F-69572B0ABBF7]
// </editor-fold> 
public class ControlRubro {

    RubroDAO dao = new RubroDAO();

    public Rubro getRubro(long idRubro) {

        return dao.leer(idRubro);
    }

    public Rubro getRubro(String nombrePresupuesto, String nombreRubro) {

        return dao.leer(nombreRubro, nombrePresupuesto);
    }

    public List<Rubro> getRubros(long idPresupuesto) {

        return dao.leerDePresupuesto(idPresupuesto);
    }

    public List<Rubro> getRubros(String nombrePresupuesto) {

        return dao.leerDePresupuesto(nombrePresupuesto);
    }

    public String editarRubro(String nombrePresupuesto, String nombreRubro, String nuevoNombre, float nuevoPresupuestoAprobado) {
        //Busca en los rubros del presupuesto y si el id es igual cambia los valores del rubro
        Rubro rubro = dao.leer(nombreRubro, nombrePresupuesto);
        if (rubro != null) {
            Presupuesto presupuesto = new PresupuestoDAO().leer(nombrePresupuesto);
            if (!validarNombre(nuevoNombre)) {
                return "Nombre inválido";
            }
            if (nuevoPresupuestoAprobado < 0 || nuevoPresupuestoAprobado < rubro.getPresupuestoEjecutado()) {
                return "Monto de dinero inválido";
            }
            if (!verificarValidezDinero(nuevoPresupuestoAprobado - rubro.getPresupuestoAprobado(), nombrePresupuesto)) {
                return "Monto de dinero inválido";
            }
            presupuesto.actualizarPresupuestoComprometido(nuevoPresupuestoAprobado - rubro.getPresupuestoAprobado());
            new PresupuestoDAO().actualizar(presupuesto);
            rubro.setFechaModificacion(new Date());
            rubro.setNombreRubro(nuevoNombre);
            rubro.setPresupuestoAprobado(nuevoPresupuestoAprobado);
            return "Rubro editado satisfactoriamente";
        }
        return "Edición fallida";
    }

    public boolean validarNombre(String val) {
        //Si la longitud del nombre tiene de 5 a 30 caracteres se acepta
        if (val.length() >= 5 && val.length() < 30) {
            return true;
        }
        return false;
    }

    public boolean verificarValidezDinero(float dinero, String nombrePresupuesto) {
        Presupuesto p = new PresupuestoDAO().leer(nombrePresupuesto);
        if (p.getPresupuestoAprobado() - p.getPresupuestoComprometido() - dinero < 0) {
            return false;
        }
        return true;
    }

    public String transferirFondos(String nombrePresupuesto, String nombreFuente, String nombreDestino, float monto) {

        Rubro fuente = dao.leer(nombreFuente, nombrePresupuesto);
        Rubro destino = dao.leer(nombreDestino, nombrePresupuesto);
        if (verificarDisponibilidadFuente(fuente, monto) && monto > 0) {
            fuente.actualizarPresupuestoApropiado(-monto);
            destino.actualizarPresupuestoApropiado(monto);
            dao.actualizar(fuente);
            dao.actualizar(destino);
            return "Se transfirieron " + monto + " de " + nombreFuente + " a " + nombreDestino;
        }
        return "Fondos insuficientes para realizar la transferencia";
    }

    public boolean verificarDisponibilidadFuente(Rubro fuente, float monto) {
        //Verifica si el rubro fuente tiene fondos suficientes
        if (fuente.getPresupuestoAprobado() < fuente.getPresupuestoEjecutado() + monto) {
            //Si no tiene retorna falso
            return false;
        }
        //Si tiene retorna verdadero
        return true;
    }

    public String eliminarRubro(String nombrePresupuesto, String nombreRubro) {
        Presupuesto presupuesto = new PresupuestoDAO().leer(nombrePresupuesto);
        if (presupuesto != null) {
            Rubro rubro = dao.leer(nombreRubro, presupuesto.getId());
            if (rubro != null) {
                presupuesto.actualizarPresupuestoComprometido(-rubro.getPresupuestoAprobado());
                presupuesto.actualizarPresupuestoEjecutado(-rubro.getPresupuestoEjecutado());
                dao.eliminar(rubro);
                return "Rubro eliminado satisfactoriamente";
            }
        }
        return "Eliminacion fallida";
    }

    public String agregarRubro(String nombrePresupuesto, String[] datos) {
        if (!validarNombre(datos[0])) {
            return "Nombre inválido";
        }
        if (nombreRepetido(datos[0], nombrePresupuesto)) {
            return "Nombre ya asignado";
        }
        float presupuestoAprobado = -120;
        try {
            presupuestoAprobado = Float.parseFloat(datos[1]);
        } catch (NumberFormatException t) {
        }
        if (presupuestoAprobado < 0 || !verificarValidezDinero(presupuestoAprobado, nombrePresupuesto)) {
            return "Monto de dinero inválido";
        }
        Presupuesto p = new PresupuestoDAO().leer(nombrePresupuesto);
        p.actualizarPresupuestoComprometido(presupuestoAprobado);
        new PresupuestoDAO().actualizar(p);
        Rubro nuevo = new Rubro(datos[0], presupuestoAprobado);
        nuevo.setPresupuesto(new PresupuestoDAO().leer(nombrePresupuesto));
        new RubroDAO().crear(nuevo);
        return "Rubro agregado satisfactoriamente";
    }

    private boolean nombreRepetido(String nombreRubro, String nombrePresupuesto) {
        if (new ControlPresupuesto().getPresupuesto(nombrePresupuesto) != null) {
            for (Rubro r : new RubroDAO().leerDePresupuesto(nombrePresupuesto)) {
                if (r.getNombreRubro().equals(nombreRubro)) {
                    return true;
                }
            }
        }
        return false;
    }
}
