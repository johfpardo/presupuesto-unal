/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidad.Presupuesto;
import entidad.Sistema;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Oscar Alberto Bustos
 */
public class ModificarItemTest {

    private static ControlSistema control = new ControlSistema();

    public ModificarItemTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        control.crearPresupuesto(new String[]{"Plan1", "1000000"});
        control.crearRubro(new String[]{"Materiales y Suministros", "500000", "13-12-2022"}, "Plan1");
        control.crearItem(new String[]{"Cable coaxial", "85000", "13-12-2022"}, "Plan1", "Materiales y Suministros");

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void changeNameTest() {

        String datos[] = {"Item", "10000", "13-12-2022"};
        long id = (long) control.getItem("Plan1", "Materiales y Suministros", "Cable coaxial")[3];
        assertEquals(control.editarItem("Plan1", "Materiales y Suministros", id, datos), "Nombre inválido");
        String datos1[] = {"Item relacionado con la reparación de laboratorios", "200000", "13-12-2022"};
        assertEquals(control.editarItem("Plan1", "Materiales y Suministros", id, datos1), "Nombre inválido");

    }

    @Test
    public void changeValueTest() {
        long id = (long) control.getItem("Plan1", "Materiales y Suministros", "Cable coaxial")[3];
        String datos[] = {"Hojas tamaño carta", "550000", "13-12-2022"};
        assertEquals(control.editarItem("Plan1", "Materiales y Suministros", id, datos), "Monto de dinero inválido");
        String datos1[] = {"Hojas tamaño carta", "-30000", "13-12-2022"};
        assertEquals(control.editarItem("Plan1", "Materiales y Suministros", id, datos1), "Monto de dinero inválido");

    }

    @Test
    public void allRightChangeTest() {
        long id = (long) control.getItem("Plan1", "Materiales y Suministros", "Cable coaxial")[3];
        String data[] = {"Instalación de cable coaxial", "97000", "13-12-2022"};
        assertEquals(control.editarItem("Plan1", "Materiales y Suministros", id, data), "Modificación del Item Satisfactoria");
    }

    @Test
    public void inexistentItemChangeTest() {
        Object[] i = control.getItem("Plan1", "Materiales y Suministros", "Cable r-coaxial");
        long id = -1;
        if (i != null) {
            id = (long) i[3];
        }
        String data[] = {"Revisión de material escrito", "96000", "13-12-2022"};
        assertEquals(control.editarItem("Plan1", "Materiales y Suministros", id, data), "Edición fallida");



    }
}
