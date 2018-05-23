/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.Calculator;
import FunctionLayer.FOGException;
import FunctionLayer.Hashing;
import static FunctionLayer.Hashing.HashPassword;
import FunctionLayer.entities.Employee;
import FunctionLayer.entities.Event;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Product;
import FunctionLayer.entities.StyleOption;
import java.util.List;

/**
 *
 * @author Perlt
 */
public class DataFacade {

    public static List<Order> getAllOrders() throws FOGException {
        return new OrderMapper().getOrders(-1);
    }

    public static List<Order> getCustomerList(int limit) throws FOGException {
        return new OrderMapper().getOrderCustomerList(limit);
    }

    public static Order getOrder(int orderId) throws FOGException {
        return new OrderMapper().getOrders(orderId).get(0);
    }

    public static void makeOrder(Order order) throws FOGException {
        new OrderMapper().makeOrder(order);
    }

    public static void confirmOrder(int orderId) throws FOGException {
        new OrderMapper().confirmOrder(orderId);
    }

    public static void changeOrder(Order order) throws FOGException {
        new OrderMapper().changeOrder(order);
    }

    public static void removeOrder(int orderId) throws FOGException {
        new OrderMapper().removeOrder(orderId);
    }

    public static List<StyleOption> getCladdingList() throws FOGException {
        return new StyleMapper().getCladding(-1);
    }

    public static List<StyleOption> getTileList() throws FOGException {
        return new StyleMapper().getTile(-1);
    }

    public static StyleOption getCladding(int id) throws FOGException {
        if (!new StyleMapper().getCladding(id).isEmpty()) {
            return new StyleMapper().getCladding(id).get(0);
        }
        throw new FOGException("Could not find cladding");
    }

    public static void unconfirmOrder(int id) throws FOGException {
        new OrderMapper().unconfirmOrder(id);
    }

    public static StyleOption getTile(int id) throws FOGException {
        if (!new StyleMapper().getTile(id).isEmpty()) {
            return new StyleMapper().getTile(id).get(0);
        }
        throw new FOGException("Could not find tile");
    }

    public static void updateCladding(StyleOption cladding, int id) throws FOGException {
        new StyleMapper().updateCladding(cladding, id);
    }

    public static void updateTile(StyleOption tile, int id) throws FOGException {
        new StyleMapper().updateTile(tile, id);
    }

    public static void createCladding(StyleOption cladding) throws FOGException {
        new StyleMapper().createCladding(cladding);
    }

    public static void createTile(StyleOption tile) throws FOGException {
        new StyleMapper().createTile(tile);
    }

    public static void removeCladding(int id) throws FOGException {
        new StyleMapper().removeStyleOption(id, "cladding");
    }

    public static void removeTile(int id) throws FOGException {
        new StyleMapper().removeStyleOption(id, "tile");
    }

    public static String getSalt(String username) throws FOGException {
        return new EmployeeMapper().getSalt(username);
    }

    public static Employee verfyLogin(String username, String password) throws FOGException {
        return new EmployeeMapper().verfyLogin(username, password);
    }

    public static Employee getEmployeeByEmail(String email) throws FOGException {
        return new EmployeeMapper().getEmployeeByEmail(email);
    }

    public static void resetPasswordAndSetResetTrue(int employeeId, String password, String salt) throws FOGException {
        new EmployeeMapper().changePassword(employeeId, password, salt, true);
    }

    public static int numberOfConfirmedOrder() throws FOGException {
        return new OrderMapper().numberOfUnconfirmedOrders();
    }

    public static List<Order> getLatestUnconfirmedOrders(int i) throws FOGException {
        return new OrderMapper().getUnconfirmedOrders(i);
    }

    public static void writeOrderEmployeeEvent(Event event) throws FOGException {
        new EventMapper().writeOrderEmployeeEvent(event);
    }

    public static void writeOrderEvent(Event event) throws FOGException {
        new EventMapper().writeOrderEvent(event);
    }

    public static void writeEmployeeEvent(Event event) throws FOGException {
        new EventMapper().writeEmployeeEvent(event);
    }

    public static List<Event> getOrderEvent(int orderid) throws FOGException {
        return new EventMapper().getOrderEvent(orderid);
    }

    public static List<Event> getEmployeeEvent(int employeeId, int limit) throws FOGException {
        return new EventMapper().getEmployeeEvent(employeeId, limit);
    }

    public static List<Employee> getAllEmployees(boolean noFired) throws FOGException {
        return new EmployeeMapper().getAllEmployees(noFired);
    }

    public static Employee getEmployeeById(int id) throws FOGException {
        return new EmployeeMapper().getEmployeeById(id);
    }

    public static void UpdateEmployee(Employee employee) throws FOGException {
        new EmployeeMapper().updateEmployee(employee);
    }

    public static void fireEmployee(int employeeId) throws FOGException {
        new EmployeeMapper().fireEmployee(employeeId);
    }

    public static void resetEmployeePassword(int employeeId) throws FOGException {
        new EmployeeMapper().resetPassword(employeeId);
    }

    public static void changePassword(int employeeId, String password, String salt) throws FOGException {
        new EmployeeMapper().changePassword(employeeId, password, salt, false);
    }

    public static void CreateEmployee(Employee emp, String password, String salt) throws FOGException {
        new EmployeeMapper().createEmployee(emp, password, salt);
    }

    public static void writeLine(Product prod, int orderId) throws FOGException {
        new ProductMapper().writeLine(prod.getId(), orderId, prod.getAmount(), prod.getLengthUsed());
    }

    public static void writeLines(List<Product> prods, int orderId) throws FOGException {
        ProductMapper productMapper = new ProductMapper();
        for (Product prod : prods) {
            productMapper.writeLine(prod.getId(), orderId, prod.getAmount(), prod.getLengthUsed());
        }
    }
    
    public static void removeLines(int orderId) throws FOGException {
        new ProductMapper().removeLines(orderId);
    }

    public static List<Product> orderProducts(int orderId) throws FOGException {
        return new ProductMapper().getOrderProducts(orderId);
    }

    public static Product getProduct(int id) throws FOGException {
        return new ProductMapper().getProduct(id);
    }
}
