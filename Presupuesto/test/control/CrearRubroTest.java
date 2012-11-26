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
public class CrearRubroTest {
    
    public CrearRubroTest() {
    }
    static ControlSistema csistema=new ControlSistema();
    @BeforeClass
    public static void setUpClass() {
        csistema.crearPresupuesto(new String[] {"planeacion de prueba","500000"});        
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
    public void testLongitudNombre(){
        String datos[]={"abcd","100"};
        String r1=csistema.crearRubro(datos, "planeacion de prueba");
        assertEquals(r1,"Nombre inválido");
        String datos1[]={"abcdefghijabcdefghijabcdefghij","100"};
        String r2=csistema.crearRubro(datos1, "planeacion de prueba");
        assertEquals(r2,"Nombre inválido");
    }
    
    @Test
    public void testNombreRepetido(){
        String datos[]={"Mantenimiento de equipos","100"};
        String r1=csistema.crearRubro(datos, "planeacion de prueba");
        
        String datos1[]={"Mantenimiento de equipos","101"};
        String r2=csistema.crearRubro(datos1, "planeacion de prueba");
        assertEquals(r2,"Nombre ya asignado");
        
       
    }
        
    @Test
    public void testValidezDinero(){
        String datos[]={"abcde","-100"};
        String r1=csistema.crearRubro(datos, "planeacion de prueba");
        assertEquals(r1,"Monto de dinero inválido");
        String datos1[]={"abcde","500001"};
        String r2=csistema.crearRubro(datos1, "planeacion de prueba");
        assertEquals(r2,"Monto de dinero inválido");
    }
    
    @Test
    public void testRubroCreado(){
        String datos1[]={"abcde","10000"};
        String r1=csistema.crearRubro(datos1, "planeacion de prueba");
        assertEquals(r1,"Rubro agregado satisfactoriamente");
        String datos2[]={"abcdef","20000"};
        String r2=csistema.crearRubro(datos2, "planeacion de prueba");
        assertEquals(r2,"Rubro agregado satisfactoriamente");
        String datos3[]={"abcdefg","20000"};
        String r3=csistema.crearRubro(datos3, "planeacion de prueba");
        assertEquals(r3,"Rubro agregado satisfactoriamente");
        
    }
    
 
}
