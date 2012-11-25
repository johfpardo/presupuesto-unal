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
    private ControlItem cItem = new ControlItem();
    
    
    public LeerItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Sistema.msistema.setPresupuesto(new ArrayList<Presupuesto>());
        controlS.crearPresupuesto(new String[] {"Plan1","1000000"});
        controlS.crearRubro(new String[] {"Estimulo a Estudiantes","300000"},"Plan1");
        controlS.crearItem(new String []{"Viaje Ricardo a España","50000"}, "Plan1", 0);
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
        public void testExisteItem(){
             
            
              ArrayList<Item> items = new ArrayList<Item>();
              Rubro r = new Rubro();
              Item i = new Item();
              i.setNombreItem("Viaje Ricardo a España");
              i.setId(1);
              items.add(i);      
              r.setNombreRubro("Conferencias");
              r.setId(1);
              r.setItem(items);
              
          
              assertEquals(cItem.getItem(r,"dsdfsfs"),null); 
              assertEquals(cItem.getItem(r, 2),null);
        }
    
   @Test
   public void testLeerItem() {
        
           Rubro r  = new Rubro();
           ArrayList<Item> items = new ArrayList<Item>();
           r.setNombreRubro("Conferencias");
           r.setId(1);
           Item i = new Item();
           i.setNombreItem("Viaje Ricardo a España");
           i.setId(1);
           items.add(i);      
           r.setNombreRubro("Conferencias");
           r.setId(1);
           r.setItem(items);
           
           assertEquals(cItem.getItem(r,"Viaje Ricardo a España"),i); 
              assertEquals(cItem.getItem(r,1),i);
           
           
       //assert(controlS.getItem("Plan1",0,"Viaje Ricardo a España").getNombreItem().equals("Viaje Ricardo a España"));
   }
}
