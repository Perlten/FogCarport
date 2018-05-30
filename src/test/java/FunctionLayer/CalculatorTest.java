/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Product;
import FunctionLayer.entities.Shed;
import FunctionLayer.entities.StyleOption;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Perlt
 */
public class CalculatorTest {

    @Test
    public void testPolesWithoutShed() throws Exception {
        Order order = new Order(null, new Customization(600, 600, 100, 0, null, new StyleOption("test", "test", 0), new StyleOption("test", "test", 0)));
        Calculator cal = new Calculator(order);
        int expected = 6;
        int actual = -1;
        cal.calculate();
        List<Product> list = cal.getProducts();
        for (Product p : list) {
            if (p.getId() == 4) {
                actual = p.getAmount();
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testPolesWithShed() throws Exception {
        Order order = new Order(null, new Customization(700, 700, 100, 0, new Shed(200, 200), new StyleOption("test", "test", 0), new StyleOption("test", "test", 0)));
        Calculator cal = new Calculator(order);
        cal.calculate();
        List<Product> list = cal.getProducts();
        boolean found = false;
        for (Product p : list) {
            int id = p.getId();
            if (id == 6) {
                found = true;
                break;
            }
        }
        if (found) {
            assert true;
        } else {
            assert false;
        }
    }

    @Test
    public void testPitStop() throws Exception {
        Order order = new Order(null, new Customization(601, 601, 100, 0, null, new StyleOption("test", "test", 0), new StyleOption("test", "test", 0)));
        Calculator cal = new Calculator(order);
        int expected = 9;
        int actual = -1;
        cal.calculate();
        List<Product> list = cal.getProducts();
        for (Product p : list) {
            if (p.getId() == 4) {
                actual = p.getAmount();
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testAngledRoof() throws Exception {
        Order order1 = new Order(null, new Customization(600, 600, 100, 0, null, new StyleOption("test", "test", 0), new StyleOption("test", "test", 0)));
        Calculator cal = new Calculator(order1);
        cal.calculate();
        int order1Amount = productAmount(8, cal.getProducts());
        
        Order order2 = new Order(null, new Customization(600, 600, 100, 45, null, new StyleOption("test", "test", 0), new StyleOption("test", "test", 0)));
        Calculator cal2 = new Calculator(order2);
        cal2.calculate();
        int order2Amount = productAmount(8, cal2.getProducts());
        
        assertTrue(order2Amount > order1Amount);
    }
    
    private int productAmount(int productId, List<Product> list){
        for (Product product : list) {
            if(product.getId() == productId){
                return product.getAmount();
            }
        }
        return 0;
    }

    /**
     * Test of getProducts method, of class Calculator.
     */
    @Test
    public void testGetProducts() throws Exception {
        Order order = new Order(null, new Customization(600, 600, 100, 0, null, new StyleOption("test", "test", 0), new StyleOption("test", "test", 0)));
        Calculator cal = new Calculator(order);
        cal.calculate();
        List<Product> list = cal.getProducts();
        assertTrue(!list.isEmpty());
    }
}
