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
    
    public CrearPlaneacionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        
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
        // Prueba Longitud Nombre menor a 5 caracteres
        String[] datos = new String[] {"Plan","100000"};
        assertEquals(controlS.crearPresupuesto(datos),"Longitud de Nombre Incorrecto");
        // Prueba Longitud Nombre mayor a 30 caracteres
        datos = new String[] {"Planeacion Departamento de ingenieria de Sistemas e Industrial","100000"};
        assertEquals(controlS.crearPresupuesto(datos),"Longitud de Nombre Incorrecto");
              
    }
    
    @Test
    public void NombreDisponibleTest() {
        String datos[] = {null,null};
        // Prueba Nombre Disponible, revisa que no haya una planeacion con el mismo nombre
        datos = new String[] {"Planeacion 2013","100000"};
        controlS.crearPresupuesto(datos);
        datos = new String[] {"Planeacion 2013","100000"};
        assertEquals(controlS.crearPresupuesto(datos),"Nombre no Disponible");
        
    }
    
    @Test
    public void valorValidoTest() {
        
        String datos[] = {null,null};
        datos = new String[] {"Planeacion 2014","-100000"};
        assertEquals(controlS.crearPresupuesto(datos),"Valor invalido en el presupuesto");
               
    }
    
    @Test
    public void crearPlaneacionTest() {
        
        // Prueba Creacion con datos Correctos
        String[] datos = new String[] {"Planeacion 2015","100000"};
        assertEquals(controlS.crearPresupuesto(datos),"Planeacion Creada");
        
        
    }
}
