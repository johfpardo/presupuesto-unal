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
public class eliminarItemTest {

    private static ControlSistema controlS = new ControlSistema();
    private ControlItem cItem = new ControlItem();

    public eliminarItemTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Sistema.msistema.setPresupuesto(new ArrayList<Presupuesto>());
        //creando una planeacion en el sistema
        controlS.crearPresupuesto(new String[]{"Plan1", "1000000"});
        //creando rubros en el sistema
        controlS.crearRubro(new String[]{"Estimulo a Estudiantes Auxiliares", "300000"}, "Plan1");
        //Creando Items en el sistema
        controlS.crearItem(new String[]{"Premio por desempeño", "100000", "25-12-2012"}, "Plan1", "Estimulo a Estudiantes Auxiliares");
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
    public void eliminacionFallidaTest() {

        Rubro r = new Rubro();
        ArrayList<Item> items = new ArrayList<Item>();
        r.setNombreRubro("Conferencias");
        r.setId(1);
        Item i = new Item();
        i.setNombreItem("Viaje Ricardo a España");
        i.setId(1);
        items.add(i);
        r.setNombreRubro("Conferencias");
        r.setId(1);
        r.setItems(items);

        assertEquals(cItem.eliminarItem(2), "Item inexistente, eliminacion no satisfactoria");
        //Con un id inexistente se prueba el procedimiento
        // assert(controlS.eliminarItem("Plan1",0,3).equals("Item inexistente, eliminacion no satisfactoria")); 
    }

    @Test
    public void eliminacionSatisfactoriaTest() {

        Rubro r = new Rubro();
        ArrayList<Item> items = new ArrayList<Item>();
        r.setNombreRubro("Conferencias");
        r.setId(1);
        Item i = new Item();
        i.setNombreItem("Viaje Ricardo a España");
        i.setId(1);
        items.add(i);
        r.setNombreRubro("Conferencias");
        r.setId(1);
        r.setItems(items);

        assertEquals(cItem.eliminarItem(2), "Item eliminado satisfactoriamente");
        //Se elimina un item existente

    }
}
