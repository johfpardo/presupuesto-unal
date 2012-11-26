/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import org.junit.*;
import static org.junit.Assert.*;
import entidad.*;
import java.util.ArrayList;

/**
 *
 * @author Tato
 */
public class DeleteRubroTest {

    static ControlSistema csistema = new ControlSistema();

    public DeleteRubroTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        
        String datos[] = {"Planeacion 1", "1000"};
        csistema.crearPresupuesto(datos);
        String datos1[] = {"Rubro 1", "100"};
        csistema.crearRubro(datos1, "Planeacion 1");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testNoExistePlaneacion() {

        String s1 = csistema.eliminarRubro("Planeacion 2", "Rubro 1");
        assertEquals(s1, "Eliminacion fallida");

    }
    
    @Test
    public void testNoExisteRubro() {

        String s1 = csistema.eliminarRubro("Planeacion 1", "Rubro 2");
        assertEquals(s1, "Eliminacion fallida");

    }
    
    @Test
    public void testEliminacionCorrecta() {

        String s1 = csistema.eliminarRubro("Planeacion 1", "Rubro 1");
        assertEquals(s1, "Rubro eliminado satisfactoriamente");

    }
}
