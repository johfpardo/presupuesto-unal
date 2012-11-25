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
     private ControlItem controlI=new ControlItem();
    
    public CrearItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Sistema.msistema.setPresupuesto(new ArrayList<Presupuesto>());
        //creando una planeacion en el sistema
        controlS.crearPresupuesto(new String[] {"Plan1","1000000"});
        //creando rubros en el sistema
        controlS.crearRubro(new String[] {"Estimulo a Estudiantes Auxiliares","300000"},"Plan1");
        controlS.crearRubro(new String[] {"Viaticos y Gastos de Viaje","200000"},"Plan1");
        controlS.crearRubro(new String[] {"Materiales y Suministros","500000"},"Plan1");
        //Creando un Item en el sistema
        controlS.crearItem(new String []{"Viaje Ricardo a España","50000"}, "Plan1", 1);
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
        //String datos[] = {"D","10000"};
        Item i = new Item();
        i.setNombreItem("D");
        i.setDineroEjecutado(10000);
        assertEquals(controlI.verificarLongitudNombre(i.getNombreItem()),false);
        i.setNombreItem("Concepto por el evento sobre Colombia 3.0");
        assertEquals(controlI.verificarLongitudNombre(i.getNombreItem()),false);
        //assert(controlS.crearItem(datos,"Plan1",1).equals("Nombre Invalido"));
        //String datos1[] = {"Concepto por el evento sobre Colombia 3.0","200000"};
        //assert(controlS.crearItem(datos1,"Plan1",2).equals("Nombre Invalido"));
     }
     
     @Test
     public void ValidezDineroTest(){
         // Prueba de la validez del dinero asignado
       /* String datos[] = {"Evento Colombia 3.0","-1000"};
        assert(controlS.crearItem(datos,"Plan1",0).equals("Monto de dinero no valido"));
        String datos1[] = {"Ing Hernandez a Costa Rica","2000000"};
        assert(controlS.crearItem(datos1,"Plan1",1).equals("Monto de dinero no valido"));
          */
         
        Rubro r = new Rubro();
        r.setId(1);
        r.setPresupuestoAprobado(1000000);
        r.setPresupuestoEjecutado(0);
        Item i = new Item();
        i.setNombreItem("Computadores para Sala 1");
        i.setDineroEjecutado(-1000);
        
        assertEquals(controlI.verificarValidezDineroAsignado(i.getDineroEjecutado(),r),false);
        
        i.setDineroEjecutado(20000000);
        
        assertEquals(controlI.verificarValidezDineroAsignado(i.getDineroEjecutado(),r),false);
         
      }
     
     @Test
     public void noRepetidoTest(){
        // Prueba de que no haya otro Item con el mismo nombre
        ArrayList<Item> rubro = new ArrayList<Item>();
        Rubro r = new Rubro();
        Item i1 = new Item();
        Item i2 = new Item();
        
        
        i1.setNombreItem("Viaje Ricardo a España");
        i1.setDineroEjecutado(20000000);
        rubro.add(i1);
       
     
        
        r.setItem(rubro);
        
          i2.setNombreItem("Viaje Ricardo a España");
        assertEquals(controlI.noRepetido(i2.getNombreItem(), r),false);
        
        //String datos[] = {"Viaje Ricardo a España","100000"};
        //assert(controlS.crearItem(datos,"Plan1",1).equals("Nombre ya existente"));
        
     }
     
     @Test
     public void ItemCreado(){
         
          ArrayList<Item> rubro = new ArrayList<Item>();
        Rubro r = new Rubro();
        r.setId(1);
        r.setPresupuestoAprobado(100000);
        Item i1 = new Item();
        Item i2 = new Item();
        
        
        i1.setNombreItem("Viaje Juan a Brasil");
        i1.setDineroEjecutado(5000);
        rubro.add(i1);
        r.setItem(rubro);
        
        i2.setNombreItem("Viaje de Camilo a Argentina");
        assertEquals(controlI.verificarLongitudNombre(i2.getNombreItem()),true);
        
        i2.setDineroEjecutado(7000);
        assertEquals(controlI.verificarValidezDineroAsignado(i2.getDineroEjecutado(),r),true);
        
          assertEquals(controlI.noRepetido(i2.getNombreItem(), r),true);
          
          assertEquals(controlI.agregarItem(i2, r),"Item creado Satisfactoriamente");
             
         
       // String datos[] = {"Viaje Carlos a Italia","50000"};
        //assert(controlS.crearItem(datos,"Plan1",0).equals("Item creado Satisfactoriamente")); 
     }
}