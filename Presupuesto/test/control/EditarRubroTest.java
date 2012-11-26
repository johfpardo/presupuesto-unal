/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidad.Presupuesto;
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
 * @author fredy
 */
public class EditarRubroTest {
    
    public EditarRubroTest() {
    }
    static ControlSistema csistema=new ControlSistema();
    @BeforeClass
    public static void setUpClass() {
        
        Sistema.msistema.setPresupuesto(new ArrayList<Presupuesto>());
        csistema.crearPresupuesto(new String[] {"planeacion de prueba","50000"});  
        csistema.crearRubro(new String[] {"abcde","10000"}, "planeacion de prueba");
        csistema.crearItem(new String[]{"item de prueba","1500","25-11-2012"}, "planeacion de prueba", "abcde");
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
    
    @Test
    public void testRubroNoExiste(){
        String r1=csistema.editarRubro("planeacion de prueba","nnnnnnn",new String[] {"abcd","1000"});
        assertEquals(r1,"Edición fallida");
    }
    
    @Test
    public void testLongitudNombre(){
        String r1=csistema.editarRubro("planeacion de prueba","abcde",new String[] {"abcd","1000"});
        assertEquals(r1,"Nombre inválido");
        
        String r2=csistema.editarRubro("planeacion de prueba","abcde",new String[] {"abcdefghijabcdefghijabcdefghij","1000"});
        assertEquals(r2,"Nombre inválido");
    }
    
    @Test 
    public void testValidezDinero1(){
        String r1=csistema.editarRubro("planeacion de prueba","abcde",new String[] {"abcdef","-20"});
        assertEquals(r1,"Monto de dinero inválido");
        String r2=csistema.editarRubro("planeacion de prueba","abcde",new String[] {"abcdef","1000"});
        assertEquals(r2,"Monto de dinero inválido");
    }
    
    @Test 
    public void testValidezDinero2(){
        String r1=csistema.editarRubro("planeacion de prueba","abcde",new String[] {"abcdef","50001"});
        assertEquals(r1,"Monto de dinero inválido");
    }
    
    @Test
    public void testRubroEditado(){
        String r1=csistema.editarRubro("planeacion de prueba","abcde",new String[] {"abcdef","5000"});
        assertEquals(r1,"Rubro editado satisfactoriamente");
    }
}
