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
    static ControlSistema csistema=new ControlSistema();
    private ControlRubro cRubro = new ControlRubro();
    private ControlPresupuesto cPresupuesto = new ControlPresupuesto();
    
    public DeleteRubroTest() {
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
           Object[] presupuesto;
           presupuesto = csistema.getPresupuesto("Planeacion 1");
           long id = csistema.getControlRubro().getRubro((String) presupuesto[0], "Rubro1").getId();
           String s1 = csistema.eliminarRubro("Planeacion 2","Rubro 1") ;
           assertEquals(s1,"Eliminacion fallida");
                   
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
          
              
              assertEquals(csistema.eliminarRubro(p.getNombrePlaneacion(), (String) r.toObjectArray()[0]),"Eliminacion fallida");
        }
       
  /*     @Test
       public void testEliminarRubro(){
           
            ArrayList<Presupuesto>  presupuestos = new ArrayList<Presupuesto>(); 
             Presupuesto p = new Presupuesto();
             p.setNombrePlaneacion("Planeacion 1");
             presupuestos.add(p);
             Sistema.msistema.setPresupuesto(presupuestos);
             
              ArrayList<Rubro> rubros = new ArrayList<Rubro>();
              Rubro a = new Rubro();
              
              a.setNombreRubro("Mantenimiento de equipos");
              a.setId(1);
              rubros.add(a);
              
              p.setRubro(rubros);
             presupuestos.add(p);
             Sistema.msistema.setPresupuesto(presupuestos);
              
              assertEquals(cRubro.eliminarRubro(p, 2),"Eliminacion fallida");
     
       }
       */
       @Test
       
       public void testEliminarRubroCorrecto(){
             
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
             assertEquals(cRubro.getRubro("Planeacion 1", "Mantenimiento de equipos"),a);
             assertEquals(cRubro.getRubro(1),a);
             assertEquals(cRubro.getRubro("Planeacion 1", "Viajes"),b);
             assertEquals(cRubro.getRubro(2),b);
             assertEquals(cRubro.getRubro("Planeacion 1","Conferencias"),c);
             assertEquals(cRubro.getRubro(3),c);
             
             assertEquals(cRubro.eliminarRubro("Planeacion 1", "Mantenimiento de equipos"),"Rubro eliminado satisfactoriamente");
             assertEquals(cRubro.eliminarRubro("Planeacion 1", "Mantenimiento de equipos"),"Rubro eliminado satisfactoriamente");
             assertEquals(cRubro.eliminarRubro("Planeacion 1", "Mantenimiento de equipos"),"Rubro eliminado satisfactoriamente");
              
       }
       
    
  /*  
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
    * 
    */
       
}
