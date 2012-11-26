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
    private ControlSistema csistema = new ControlSistema();
    
    public LeerRubroTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Test
    
    public void testExistePlaneacion(){
           
         
              String datos[] = {"Planeacion 1","1000"};
           csistema.crearPresupuesto(datos);
           String datos1[] = {"Rubro 1","100"};
           csistema.crearRubro(datos1,"Planeacion 1");
           Object[] p1;
           p1 = csistema.getPresupuesto("Planeacion 1");
           long id = csistema.getControlRubro().getRubro((String) p1[0], "Rubro1").getId();
           String s1 = csistema.eliminarRubro("Planeacion 2", "Rubro 1");
           assertEquals(s1,"Eliminacion fallida");
                   
          /* ArrayList<Presupuesto>  presupuestos = new ArrayList<Presupuesto>(); 
           Presupuesto p2 = new Presupuesto();
           Presupuesto p1 = new Presupuesto();
           p1.setNombrePlaneacion("Planeacion 1");
           presupuestos.add(p1);
           Sistema.msistema.setPresupuesto(presupuestos);
           

           assertEquals(cPresupuesto.getPresupuesto("mnbj"),null);   
           */
        }
    
     @Test
        public void testExisteRubro(){
              Presupuesto p = new Presupuesto();
              p.setNombrePlaneacion("Planeacion 1");
              ArrayList<Rubro> rubros = new ArrayList<Rubro>();
              Rubro r = new Rubro();
              Rubro a = new Rubro();
              r.setNombreRubro("Conferencias");
              r.setId(1);
              rubros.add(r);
              p.setRubros(rubros);
          
              assertEquals(cRubro.getRubro((String)csistema.getPresupuesto("Planeacion 1")[0], "dsdfsfs"),null); 
              assertEquals(cRubro.getRubro(2),null);
        }
    
       
        @Test
       
       public void testLeerRubroCorrecto(){
             
             ArrayList<Presupuesto>  presupuestos = new ArrayList<Presupuesto>(); 
             Presupuesto p = new Presupuesto();
             p.setNombrePlaneacion("Planeacion 1");
             presupuestos.add(p);
             Sistema.msistema.setPresupuesto(presupuestos);
            
             assertEquals(cPresupuesto.getPresupuesto("Planeacion 1"),p);
             
             ArrayList<Rubro> rubros = new ArrayList<Rubro>();
              Rubro a = new Rubro();
              Rubro b = new Rubro();
              Rubro c = new Rubro();
              
              a.setNombreRubro("Mantenimiento de equipos");
              a.setId(1);
              b.setNombreRubro("Viajes");
              b.setId(2);
              c.setNombreRubro("Conferencias");
              c.setId(3);
              rubros.add(a);
              rubros.add(b);
              rubros.add(c);
             
             p.setRubros(rubros);
             presupuestos.add(p);
             Sistema.msistema.setPresupuesto(presupuestos);
             assertEquals(cRubro.getRubro((String) csistema.getPresupuesto("Planeacion 1")[0], "Mantenimiento de equipos"),a);
             assertEquals(cRubro.getRubro(1),a);
             assertEquals(cRubro.getRubro((String) csistema.getPresupuesto("Planeacion 1")[0], "Viajes"),b);
             assertEquals(cRubro.getRubro(2),b);
             assertEquals(cRubro.getRubro((String) csistema.getPresupuesto("Planeacion 1")[0], "Conferencias"),c);
             assertEquals(cRubro.getRubro(3),c);
             
           
              
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
    // @Test
    // public void hello() {}
}
