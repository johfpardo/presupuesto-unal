/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidad.Presupuesto;
import entidad.Rubro;
import entidad.Sistema;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Tato
 */
public class LeerRubroTest {

    private ControlRubro cRubro = new ControlRubro();
    private ControlPresupuesto cPresupuesto = new ControlPresupuesto();
    public static ControlSistema cSistema = new ControlSistema();

    public LeerRubroTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

        Sistema.msistema.setPresupuesto(new ArrayList<Presupuesto>());
        cSistema.crearPresupuesto(new String[]{"Plan1", "1000000"});
        cSistema.crearRubro(new String[]{"Materiales y Suministros", "500000"}, "Plan1");
        cSistema.crearRubro(new String[]{"Viaticos y Gastos de Viaje", "200000"}, "Plan1");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testExistePlaneacion() {

        //Prueb con una planeación no existente
        assertEquals(cSistema.getRubro("Plan2,", "Materiales y Suministros"), null);


    }

    @Test
    public void testExisteRubro() {
        //Prueba con un rubro que no existe     
        assertEquals(cSistema.getRubro("Plan1", "Maquinaria y Equipos"), null);

    }

    @Test
    public void testLeerRubroCorrecto() {

        Object[] r1 = cSistema.getRubro("Plan 1", "Materiales y Suministros");
        Object[] r2 = cSistema.getRubro("Plan 1", "Viaticos y Gastos de Viaje");

        assertEquals(cSistema.getRubro("Plan 1", "Materiales y Suministros"), r1);
        assertEquals(cSistema.getRubro("Plan 1", "Viaticos y Gastos de Viaje"), r2);
    }
}
