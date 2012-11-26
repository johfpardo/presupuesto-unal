/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidad.Presupuesto;
import entidad.Sistema;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author SneiderHC
 */
public class VerPlaneacionTest {

    private static ControlSistema controlS = new ControlSistema();

    public VerPlaneacionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Sistema.msistema.setPresupuesto(new ArrayList<Presupuesto>());
        controlS.crearPresupuesto(new String[]{"Plan1", "100000"});

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
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
    public void planeaciónInexistente() {
        // Prueba presupuesto no existente
        Object[] p = controlS.getPresupuesto("planeacion 2012");
        assertEquals(p, null);

    }

    @Test
    public void verPlaneacion() {

        Object[] p = controlS.getPresupuesto("Plan1");
        assertEquals(p[0],"Plan1");
    }
}
