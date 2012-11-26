/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidad.Item;
import entidad.Presupuesto;
import entidad.Rubro;
import entidad.Sistema;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author John Fredy
 */
public class eliminarItemTest {

    private static ControlSistema csistema = new ControlSistema();

    public eliminarItemTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        csistema.crearPresupuesto(new String[]{"planeacion de prueba", "50000"});
        csistema.crearRubro(new String[]{"abcde", "10000"}, "planeacion de prueba");
        csistema.crearItem(new String[]{"item de prueba", "1500", "25-11-2012"}, "planeacion de prueba", "abcde");
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
    public void eliminacionFallidaTest() {

        assertEquals(csistema.eliminarItem(-2), "Item inexistente, eliminacion no satisfactoria");

    }

    @Test
    public void eliminacionSatisfactoriaTest() {

        Object[] item = csistema.getItem("planeacion de prueba", "abcde", "item de prueba");
        long iD = (long) item[3];

        //Se eliminan dos items existentes
        assertEquals(csistema.eliminarItem(iD), "Item eliminado satisfactoriamente");



    }
}
