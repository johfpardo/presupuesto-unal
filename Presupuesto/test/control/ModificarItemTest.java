/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidad.Presupuesto;
import entidad.Sistema;
import java.util.ArrayList;
import java.util.Date;
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
public class ModificarItemTest {
    
    private static ControlSistema control = new ControlSistema();
    
    public ModificarItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        Sistema.msistema.setPresupuesto(new ArrayList<Presupuesto>());
        control.crearPresupuesto(new String[] {"Plan1","1000000"});
        control.crearRubro(new String[] {"Materiales y Suministros","500000"},"Plan1");
        control.crearItem(new String [] {"Cable coaxial","85000"}, "Plan1", 0);
        control.crearItem(new String [] {"Hojas tamaño carta","25000"}, "Plan1", 0);
                
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
    public void changeNameTest() {
        
        String datos[] = {"Item","10000"};
        assertEquals(control.editarItem("Plan1",0,0,datos), "Nombre inválido");
        String datos1[] = {"Item relacionado con la reparación de laboratorios","200000"};
        assertEquals(control.editarItem("Plan1",0,0,datos1), "Nombre inválido");
        String datos2[] = {"Instalación de cable coaxial","85000"};
       
    
    }

    @Test
    public void changeValueTest() {
        String datos[] = {"Hojas tamaño carta","550000"};
        assertEquals(control.editarItem("Plan1",0,1,datos), "Monto de dinero inválido");
        String datos1[] = {"Hojas tamaño carta","-30000"};
        assertEquals(control.editarItem("Plan1",0,1,datos1), "Monto de dinero inválido");
        String datos2[] = {"Hojas tamaño carta","460000"};
        assertEquals(control.editarItem("Plan1",0,1,datos2), "Monto de dinero inválido");
                
        String datos3[] = {"Hojas tamaño carta","27000"};
        assertEquals(control.editarItem("Plan1",0,1,datos3), "Modificación del Item Satisfactoria");
    
    }
    
    @Test
    public void allRightChangeTest() {
        
        String data[] = {"Instalación de cable coaxial","97000"};
        assertEquals(control.editarItem("Plan1",0,1,data), "Modificación del Item Satisfactoria");
    
    }
    
    @Test
    public void inexistentItemChangeTest() {
        
        String data[] = {"Revisión de material escrito","96000"};
        assertEquals(control.editarItem("Plan1",0,2,data), "Edición fallida");
           
    }

}
