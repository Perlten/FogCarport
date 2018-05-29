/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.FOGException;
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

    /**
     * Get a list with all orders
     *
     * @return List with Order
     * @throws FOGException
     */
    public static List<Order> getAllOrders() throws FOGException {
        return new OrderMapper().getOrders(-1);
    }

    /**
     * Return list with Customer
     *
     * @param limit limit amount of Customer in list
     * @return List Customer
     * @throws FOGException
     */
    public static List<Order> getCustomerList(int limit) throws FOGException {
        return new OrderMapper().getOrderCustomerList(limit);
    }

    /**
     * Gets order by id
     *
     * @param orderId
     * @return Order
     * @throws FOGException
     */
    public static Order getOrder(int orderId) throws FOGException {
        return new OrderMapper().getOrders(orderId).get(0);
    }

    /**
     * Makes order
     *
     * @param order
     * @throws FOGException
     */
    public static void makeOrder(Order order) throws FOGException {
        new OrderMapper().makeOrder(order);
    }

    /**
     * Confirms order
     *
     * @param orderId
     * @throws FOGException
     */
    public static void confirmOrder(int orderId) throws FOGException {
        new OrderMapper().confirmOrder(orderId);
    }

    /**
     * Changes Order
     *
     * @param order
     * @throws FOGException
     */
    public static void changeOrder(Order order) throws FOGException {
        new OrderMapper().changeOrder(order);
    }

    /**
     * Removes order from database
     *
     * @param orderId
     * @throws FOGException
     */
    public static void removeOrder(int orderId) throws FOGException {
        new OrderMapper().removeOrder(orderId);
    }

    /**
     * Get all cladding
     *
     * @return List of StyleOption
     * @throws FOGException
     */
    public static List<StyleOption> getCladdingList() throws FOGException {
        return new StyleMapper().getCladding(-1);
    }

    /**
     * Get all tile
     *
     * @return List with Tile
     * @throws FOGException
     */
    public static List<StyleOption> getTileList() throws FOGException {
        return new StyleMapper().getTile(-1);
    }

    /**
     * Get Cladding by id
     *
     * @param id
     * @return StyleOption
     * @throws FOGException
     */
    public static StyleOption getCladding(int id) throws FOGException {
        if (!new StyleMapper().getCladding(id).isEmpty()) {
            return new StyleMapper().getCladding(id).get(0);
        }
        throw new FOGException("Could not find cladding");
    }

    /**
     * Unconfirm order by id
     *
     * @param id
     * @throws FOGException
     */
    public static void unconfirmOrder(int id) throws FOGException {
        new OrderMapper().unconfirmOrder(id);
    }

    /**
     * Get Tile by id
     *
     * @param id
     * @return StyleOption
     * @throws FOGException
     */
    public static StyleOption getTile(int id) throws FOGException {
        if (!new StyleMapper().getTile(id).isEmpty()) {
            return new StyleMapper().getTile(id).get(0);
        }
        throw new FOGException("Could not find tile");
    }

    /**
     * Update Cladding
     *
     * @param cladding StyleOption
     * @param id
     * @throws FOGException
     */
    public static void updateCladding(StyleOption cladding, int id) throws FOGException {
        new StyleMapper().updateCladding(cladding, id);
    }

    /**
     * Update Tile
     *
     * @param tile StyleOption
     * @param id
     * @throws FOGException
     */
    public static void updateTile(StyleOption tile, int id) throws FOGException {
        new StyleMapper().updateTile(tile, id);
    }

    /**
     * Create Cladding
     *
     * @param cladding StyleOption
     * @throws FOGException
     */
    public static void createCladding(StyleOption cladding) throws FOGException {
        new StyleMapper().createCladding(cladding);
    }

    /**
     * Create Tile
     *
     * @param tile StyleOption
     * @throws FOGException
     */
    public static void createTile(StyleOption tile) throws FOGException {
        new StyleMapper().createTile(tile);
    }

    /**
     * Remove cladding on id
     *
     * @param id
     * @throws FOGException
     */
    public static void removeCladding(int id) throws FOGException {
        new StyleMapper().removeStyleOption(id, "cladding");
    }

    /**
     * Remove tile on id
     *
     * @param id
     * @throws FOGException
     */
    public static void removeTile(int id) throws FOGException {
        new StyleMapper().removeStyleOption(id, "tile");
    }

    /**
     * Get salt by username
     *
     * @param username
     * @return salt
     * @throws FOGException
     */
    public static String getSalt(String username) throws FOGException {
        return new EmployeeMapper().getSalt(username);
    }

    /**
     * Returns employee if username and password exits in database throws
     * Exception if not
     *
     * @param username
     * @param password
     * @return Employee
     * @throws FOGException
     */
    public static Employee verfyLogin(String username, String password) throws FOGException {
        return new EmployeeMapper().verfyLogin(username, password);
    }

    /**
     * Gets Employee by email
     *
     * @param email
     * @return String
     * @throws FOGException
     */
    public static Employee getEmployeeByEmail(String email) throws FOGException {
        return new EmployeeMapper().getEmployeeByEmail(email);
    }

    /**
     * Changes password and salt for employee and set reset_password = true in
     * database
     *
     * @param employeeId
     * @param password
     * @param salt
     * @throws FOGException
     */
    public static void resetPasswordAndSetResetTrue(int employeeId, String password, String salt) throws FOGException {
        new EmployeeMapper().changePassword(employeeId, password, salt, true);
    }

    /**
     * Return number of unconfirmed order
     *
     * @return int
     * @throws FOGException
     */
    public static int numberOfConfirmedOrder() throws FOGException {
        return new OrderMapper().numberOfUnconfirmedOrders();
    }

    /**
     * Return latest unconfirmed orders
     *
     * @param i limit
     * @return List with Order
     * @throws FOGException
     */
    public static List<Order> getLatestUnconfirmedOrders(int i) throws FOGException {
        return new OrderMapper().getUnconfirmedOrders(i);
    }

    /**
     * Write OrderEmployeeEvent to database
     *
     * @param event
     * @throws FOGException
     */
    public static void writeOrderEmployeeEvent(Event event) throws FOGException {
        new EventMapper().writeOrderEmployeeEvent(event);
    }

    /**
     * Writes OrderEvent to database
     *
     * @param event
     * @throws FOGException
     */
    public static void writeOrderEvent(Event event) throws FOGException {
        new EventMapper().writeOrderEvent(event);
    }

    /**
     * Writes EmployeeEvent to database
     *
     * @param event
     * @throws FOGException
     */
    public static void writeEmployeeEvent(Event event) throws FOGException {
        new EventMapper().writeEmployeeEvent(event);
    }

    /**
     * Gets OrderEvent from database
     *
     * @param orderid
     * @return List of Events
     * @throws FOGException
     */
    public static List<Event> getOrderEvent(int orderid) throws FOGException {
        return new EventMapper().getOrderEvent(orderid);
    }

    /**
     * Gets EmployeeEvent from database
     *
     * @param employeeId
     * @param limit
     * @return
     * @throws FOGException
     */
    public static List<Event> getEmployeeEvent(int employeeId, int limit) throws FOGException {
        return new EventMapper().getEmployeeEvent(employeeId, limit);
    }

    /**
     * Return all Employee
     *
     * @param noFired false if you want fired Employees included
     * @return List with Employee
     * @throws FOGException
     */
    public static List<Employee> getAllEmployees(boolean noFired) throws FOGException {
        return new EmployeeMapper().getAllEmployees(noFired);
    }

    /**
     * Get Employee by id
     *
     * @param id
     * @return Employee
     * @throws FOGException
     */
    public static Employee getEmployeeById(int id) throws FOGException {
        return new EmployeeMapper().getEmployeeById(id);
    }

    /**
     * Update Employee
     *
     * @param employee
     * @throws FOGException
     */
    public static void UpdateEmployee(Employee employee) throws FOGException {
        new EmployeeMapper().updateEmployee(employee);
    }

    /**
     * Fire Employee
     *
     * @param employeeId
     * @throws FOGException
     */
    public static void fireEmployee(int employeeId) throws FOGException {
        new EmployeeMapper().fireEmployee(employeeId);
    }

    /**
     * Sets reset_password on a employee in the database
     *
     * @param employeeId
     * @throws FOGException
     */
    public static void resetEmployeePassword(int employeeId) throws FOGException {
        new EmployeeMapper().resetPassword(employeeId);
    }

    /**
     * Changes password and salt for employee
     *
     * @param employeeId
     * @param password
     * @param salt
     * @throws FOGException
     */
    public static void changePassword(int employeeId, String password, String salt) throws FOGException {
        new EmployeeMapper().changePassword(employeeId, password, salt, false);
    }

    /**
     * Create Employee
     *
     * @param emp
     * @param password
     * @param salt
     * @throws FOGException
     */
    public static void CreateEmployee(Employee emp, String password, String salt) throws FOGException {
        new EmployeeMapper().createEmployee(emp, password, salt);
    }

    /**
     * Write OrderLine
     *
     * @param prod
     * @param orderId
     * @throws FOGException
     */
    public static void writeLine(Product prod, int orderId) throws FOGException {
        new ProductMapper().writeLine(prod.getId(), orderId, prod.getAmount(), prod.getLengthUsed());
    }

    /**
     * Write Lines
     *
     * @param prods
     * @param orderId
     * @throws FOGException
     */
    public static void writeLines(List<Product> prods, int orderId) throws FOGException {
        ProductMapper productMapper = new ProductMapper();
        for (Product prod : prods) {
            productMapper.writeLine(prod.getId(), orderId, prod.getAmount(), prod.getLengthUsed());
        }
    }

    /**
     * Removes Lines
     *
     * @param orderId
     * @throws FOGException
     */
    public static void removeLines(int orderId) throws FOGException {
        new ProductMapper().removeLines(orderId);
    }

    /**
     * Gets List with all Products
     *
     * @param orderId
     * @return List with Product
     * @throws FOGException
     */
    public static List<Product> orderProducts(int orderId) throws FOGException {
        return new ProductMapper().getOrderProducts(orderId);
    }

    /**
     * Get Product by id
     *
     * @param id
     * @return Product
     * @throws FOGException
     */
    public static Product getProduct(int id) throws FOGException {
        return new ProductMapper().getProduct(id);
    }

    /**
     * Return all Events
     *
     * @param limit
     * @return List with Events
     * @throws FOGException
     */
    public static List<Event> getAllEvents(int limit) throws FOGException {
        return new EventMapper().getAllEvents(limit);
    }
}
