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
public class CrearPlaneacionTest {
    
    private ControlSistema controlS = new ControlSistema();
    private ControlPresupuesto controlP = new ControlPresupuesto();
    
    public CrearPlaneacionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Sistema.msistema.setPresupuesto(new ArrayList<Presupuesto>());
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
    public void longitudNombreTest() {
       // String datos[] = {null,null};
       
        Presupuesto p2 = new Presupuesto();
        
     
        
        p2.setNombrePlaneacion("Plan");
        p2.setPresupuestoAprobado(100000000);
        assertEquals(controlP.verificarLongitudNombre(p2.getNombrePlaneacion()),false);
        
        p2.setNombrePlaneacion("Esta planeacion tiene demasiados caracteres y asi no sirve");
        assertEquals(controlP.verificarLongitudNombre(p2.getNombrePlaneacion()),false);
        
       
        
        
        /* // Prueba Longitud Nombre menor a 5 caracteres
        datos = new String[] {"Plan","100000"};
        assert(controlS.crearPresupuesto(datos).equals("Longitud de Nombre Incorrecto"));
        // Prueba Longitud Nombre mayor a 30 caracteres
        datos = new String[] {"Planeacion Departamento de ingenieria de Sistemas e Industrial","100000"};
        assert(controlS.crearPresupuesto(datos).equals("Longitud de Nombre Incorrecto"));
        // Prueba planeacion no repetida
        datos = new String[] {"Planeacion 2012","100000"};
        assert(!controlS.crearPresupuesto(datos).equals("Longitud de Nombre Incorrecto"));
        
        */
    }
    
    @Test
    public void NombreDisponibleTest() {
        /*String datos[] = {null,null};
        // Prueba Nombre Disponible, revisa que no haya una planeacion con el mismo nombre
        datos = new String[] {"Planeacion 2013","100000"};
        assert(!controlS.crearPresupuesto(datos).equals("Nombre no Disponible"));
        datos = new String[] {"Planeacion 2013","100000"};
        assert(controlS.crearPresupuesto(datos).equals("Nombre no Disponible"));
        * 
        */
        
         ArrayList<Presupuesto> sistema = new ArrayList<Presupuesto>();
        Presupuesto p1 = new Presupuesto();
        Presupuesto p2 = new Presupuesto();
        Sistema s = new Sistema();
        
        p1.setNombrePlaneacion("Planeacion 2010");
        p1.setPresupuestoAprobado(100000000);
        
        sistema.add(p1);
        s.setPresupuesto(sistema);
        controlS.setSistema(s);
        
         p2.setNombrePlaneacion("Planeacion 2010");
        assertEquals(controlP.nombreDisponible(p2.getNombrePlaneacion()),false);
          
    }
    
    @Test
    public void valorValidoTest() {
        
        Presupuesto p2 = new Presupuesto();
        p2.setNombrePlaneacion("Planeacion 2012");
        p2.setPresupuestoAprobado(-100000000);
        assertEquals(controlP.verificarAsignado(p2.getPresupuestoAprobado()),false);
       
        /*
        String datos[] = {null,null};
        // Prueba Valor Asignado negativo
        datos = new String[] {"Planeacion 2014","-100000"};
        assert(controlS.crearPresupuesto(datos).equals("Valor invalido en el presupuesto"));
        // Prueba valor asignado positivo
        datos = new String[] {"Planeacion 2014","100000"};
        assert(!controlS.crearPresupuesto(datos).equals("Valor invalido en el presupuesto"));
        * 
        */
        
        
    }
    
    @Test
    public void crearPlaneacionTest() {
        
        Sistema s = new Sistema();
        ArrayList<Presupuesto> sistema = new ArrayList<Presupuesto>();
        Presupuesto p1 = new Presupuesto();
        Presupuesto p2 = new Presupuesto();
        
        p1.setNombrePlaneacion("Planeacion 2010");
        p1.setPresupuestoAprobado(100000000);
        
        sistema.add(p1);
        s.setPresupuesto(sistema);
        controlS.setSistema(s);
        
        p2.setNombrePlaneacion("Planeacion 2012");
        p2.setPresupuestoAprobado(120000000);
        assertEquals(controlP.verificarLongitudNombre(p2.getNombrePlaneacion()),true);
        assertEquals(controlP.verificarAsignado(p2.getPresupuestoAprobado()),true);
        
        assertEquals(controlP.nombreDisponible(p2.getNombrePlaneacion()),true);
        
        assertEquals(controlP.crearPresupuesto(p2),"Planeacion Creada");
        
        /*
        // Prueba Creacion con longitud de nombre incorrecta
        String datos[] = {"Plan","100000"};
        assert(!controlS.crearPresupuesto(datos).equals("Planeacion Creada"));
        datos = new String[] {"Planeacion Departamento de ingenieria de Sistemas e Industrial","100000"};
        assert(!controlS.crearPresupuesto(datos).equals("Planeacion Creada"));
        // Prueba Creacion con valor asignado negativo
        datos = new String[] {"Planeacion 2015","-10"};
        assert(!controlS.crearPresupuesto(datos).equals("Planeacion Creada"));
        // Prueba Creacion con datos Correctos
        datos = new String[] {"Plaeacion 2015","100000"};
        assert(controlS.crearPresupuesto(datos).equals("Planeacion Creada"));
        // Prueba Creacion con nombre repetido
        datos = new String[] {"Plaeacion 2015","100000"};
        assert(!controlS.crearPresupuesto(datos).equals("Planeacion Creada"));
        * 
        */
    }
}
