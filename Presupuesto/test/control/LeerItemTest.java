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
public class LeerItemTest {

    private static ControlSistema controlS = new ControlSistema();

    public LeerItemTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        controlS.crearPresupuesto(new String[]{"Plan1", "1000000"});
        controlS.crearRubro(new String[]{"Viajes", "300000"}, "Plan1");
        controlS.crearItem(new String[]{"Viaje Ricardo a España", "50000","25-11-2012"}, "Plan1", "Viajes");
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
    public void testNoExisteItem() {


        assertEquals(controlS.getItem("Plan1", "Viajes", "dsdfsfs"), null);
    }

    @Test
    public void testLecturaCorrectaItem() {

        assertEquals(controlS.getItem("Plan1","Viajes","Viaje Ricardo a España")[0],"Viaje Ricardo a España");
    }
}
