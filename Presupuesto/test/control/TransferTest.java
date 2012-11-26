/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

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
 * @author Oscar Alberto Bustos
 */
public class TransferTest {
    
    private static ControlSistema control = new ControlSistema();
    
    public TransferTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        Sistema.msistema.setPresupuesto(new ArrayList<Presupuesto>());
        control.crearPresupuesto(new String[] {"Plan1","10000"});
        control.crearRubro(new String[] {"Fuente","500"},"Plan1");
        control.crearRubro(new String[] {"Destino","700"},"Plan1");
        
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
    public void testFondosInsuficientes() {
        
 
        
        assertEquals(control.transferirFondos((String) control.getPresupuesto("Plan1")[0], "fuente", "destino", 900),
                "Fondos insuficientes para realizar la transferencia");
        
    }
    
    @Test
    public void testTodoCorrecto() {
      
        
       
        
        //assertEquals(control.transferirFondos(control.getPresupuesto("Plan1"), fuente, destino, 500),
          //      "Se transfirieron 500.0 de " + fuente.getNombreRubro() + " a " + destino.getNombreRubro());
            assertEquals(control.transferirFondos("Plan1","fuente","destino", 500),
                    "Se transfirieron " + "500.0" + " de " + "fuente" + " a " + "destino");
    }
    
}
