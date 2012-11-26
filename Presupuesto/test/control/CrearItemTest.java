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
public class CrearItemTest {
    
    private static ControlSistema controlS = new ControlSistema();
    
    public CrearItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Sistema.msistema.setPresupuesto(new ArrayList<Presupuesto>());
        //creando una planeacion en el sistema
        controlS.crearPresupuesto(new String[] {"Plan1","1100000"});
        //creando rubros en el sistema
        controlS.crearRubro(new String[] {"Viaticos y Gastos de Viaje","200000"},"Plan1");
        controlS.crearRubro(new String[] {"Materiales y Suministros","500000"},"Plan1");
        //Creando un Item en el sistema
        controlS.crearItem(new String []{"Viaje Ricardo a España","50000","25-11-2012"},"Plan1","Viaticos y Gastos de Viaje");
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
     public void longitudNombreTest() {
        // Prueba de la Longitud del nombre
        String datos[] = {"Dado","10000","01-12-2012"};
        assertEquals(controlS.crearItem(datos, "Plan1", "Materiales y Suministros"),"Nombre Invalido");
        datos = new String[]{"abcdeabcdeabcdeabcdeabcdeabcde","10000","01-12-2012"};
        assertEquals(controlS.crearItem(datos, "Plan1", "Materiales y Suministros"),"Nombre Invalido");
        
     }
     
     @Test
     public void ValidezDineroTest(){
         
        String datos[] = {"Evento Colombia 3.0","-1000","01-12-2012"};
        assertEquals(controlS.crearItem(datos,"Plan1","Materiales y Suministros"),"Monto de dinero no valido");
        String datos1[] = {"Ing Hernandez a Costa Rica","500001","01-12-2012"};
        assertEquals(controlS.crearItem(datos1,"Plan1","Materiales y Suministros"),"Monto de dinero no valido");
                 
      }
     
     @Test
     public void noRepetidoTest(){
        // Prueba de que no haya otro Item con el mismo nombre
        String datos[] = {"Viaje Ricardo a España","50000","25-11-2012"};
        assertEquals(controlS.crearItem(datos,"Plan1","Viaticos y Gastos de Viaje"),"Nombre ya existente");
        
     }
     
     @Test
     public void ItemCreado(){
         
        String datos[] = {"Viaje Carlos a Italia","50000","25-11-2012"};
        assertEquals(controlS.crearItem(datos,"Plan1","Viaticos y Gastos de Viaje"),"Item creado Satisfactoriamente"); 
     }
}