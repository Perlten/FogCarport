package FunctionLayer;

import DBAccess.DatabaseFacade;
import DBAccess.EmployeeMapper;
import DBAccess.EventMapper;
import DBAccess.StyleMapper;
import DBAccess.OrderMapper;
import DBAccess.ProductMapper;
import FunctionLayer.entities.Product;
import FunctionLayer.entities.Employee;
import FunctionLayer.entities.Event;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.StyleOption;
import static FunctionLayer.Hashing.HashPassword;
import java.util.List;
import java.util.Random;

public class LogicFacade {

    /**
     * returns all of the orders by inputing -1 in getOrders
     *
     * @return
     * @throws FOGException
     */
    public static List<Order> getOrders() throws FOGException {
        return DatabaseFacade.getAllOrders();
    }

    public static List<Order> getCustomerList(int limit) throws FOGException {
        return DatabaseFacade.getCustomerList(limit);
    }

    /**
     * Returns a specific order.
     *
     * @param orderId A valid id of an order.
     * @return if valid input, the
     * @throws FOGException
     */
    public static Order getOrder(int orderId) throws FOGException {
        if (orderId < 0) {
            throw new FOGException("orderid can't be negative");
        }
        return DatabaseFacade.getOrder(orderId);
    }

    public static void makeOrder(Order order) throws FOGException {
        DatabaseFacade.makeOrder(order);
    }

    /**
     * Confirms order
     *
     * @param orderId
     * @throws FOGException
     */
    public static void confirmOrder(int orderId) throws FOGException {
        DatabaseFacade.confirmOrder(orderId);
    }

    /**
     * Changes to order in
     *
     * @param order
     * @throws FOGException
     */
    public static void changeOrder(Order order) throws FOGException {
        DatabaseFacade.changeOrder(order);
    }

    /**
     * Removes shed
     *
     * @param orderId
     * @throws FOGException
     */
    public static void removeOrder(int orderId) throws FOGException {
        DatabaseFacade.removeOrder(orderId);
    }

    /**
     *
     * @return @throws FOGException
     */
    public static List<StyleOption> getCladdingList() throws FOGException {
        return DatabaseFacade.getCladdingList();
    }

    /**
     *
     * @return @throws FOGException
     */
    public static List<StyleOption> getTileList() throws FOGException {
        return DatabaseFacade.getTileList();
    }

    /**
     *
     * @param id
     * @return
     * @throws FOGException
     */
    public static StyleOption getCladding(int id) throws FOGException {
        return DatabaseFacade.getCladding(id);
    }

    public static void unconfirmOrder(int id) throws FOGException {
        DatabaseFacade.unconfirmOrder(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws FOGException
     */
    public static StyleOption getTile(int id) throws FOGException {
        return DatabaseFacade.getTile(id);
    }

    public static void updateCladding(StyleOption cladding, int id) throws FOGException {
        DatabaseFacade.updateCladding(cladding, id);
    }

    public static void updateTile(StyleOption tile, int id) throws FOGException {
        DatabaseFacade.updateTile(tile, id);
    }

    public static void createCladding(StyleOption cladding) throws FOGException {
        DatabaseFacade.createCladding(cladding);
    }

    public static void createTile(StyleOption tile) throws FOGException {
        DatabaseFacade.createTile(tile);
    }

    public static void removeCladding(int id) throws FOGException {
        DatabaseFacade.removeCladding(id);
    }

    public static void removeTile(int id) throws FOGException {
        DatabaseFacade.removeTile(id);
    }

    public static void sendEmailToCustomer(Order order) throws FOGException {
        String title = "Fog carport offer";

        String textMessage = "Dear " + order.getCustomer().getFirstname() + ",\n\n"
                + "We thank you for your recent carport request!"
                + "\nTo follow the process of your request, please use your"
                + " reference link:\n\n"
                + " http://159.89.19.132/FogCarport/FrontController?command=LoadOrder&id=" + order.getOrderid()
                + "\n\nRegards,\nFog";

        SendEmail.sendMail(order.getCustomer().getEmail(), title, textMessage);
    }

    public static Employee verfyLogin(String username, String password) throws FOGException {
        String salt = DatabaseFacade.getSalt(username);
        password = password.concat(salt);
        String hash = HashPassword(password);
        return DatabaseFacade.verfyLogin(username, hash);
    }

    public static void SendNewPasswordToEmployee(String email) throws FOGException {
        Employee emp = DatabaseFacade.getEmployeeByEmail(email);

        String password = Hashing.randomString(20);

        String salt = Hashing.randomString(10);
        String newPassword = password.concat(salt);
        String hash = HashPassword(newPassword);

        DatabaseFacade.resetPasswordAndSetResetTrue(emp.getEmployeeId(), hash, salt);

        String title = "New password";
        String text = "Here stupid here is your new password... DONT LOSE IT AGAIN... moron!!\n\n"
                + "Password: " + password;

        SendEmail.sendMail(emp.getEmail(), title, text);
    }

    public static Employee getEmployeeByEmail(String email) throws FOGException {
        return DatabaseFacade.getEmployeeByEmail(email);
    }

    public static int numberOfConfirmedOrder() throws FOGException {
        return DatabaseFacade.numberOfConfirmedOrder();
    }

    public static List<Order> getLatest10UnconfirmedOrders() throws FOGException {
        return DatabaseFacade.getLatestUnconfirmedOrders(10);
    }

    public static void writeOrderEmployeeEvent(Event event) throws FOGException {
        DatabaseFacade.writeOrderEmployeeEvent(event);
    }

    public static void writeOrderEvent(Event event) throws FOGException {
        DatabaseFacade.writeOrderEvent(event);
    }

    public static void writeEmployeeEvent(Event event) throws FOGException {
        DatabaseFacade.writeEmployeeEvent(event);
    }

    public static List<Event> getOrderEvent(int orderid) throws FOGException {
        return DatabaseFacade.getOrderEvent(orderid);
    }

    public static List<Event> getEmployeeEvent(int employeeId, int limit) throws FOGException {
        return DatabaseFacade.getEmployeeEvent(employeeId, limit);
    }

    public static List<Employee> getAllEmployees() throws FOGException {
        return DatabaseFacade.getAllEmployees(false);
    }

    public static Employee getEmployeeById(int id) throws FOGException {
        return DatabaseFacade.getEmployeeById(id);
    }

    public static void UpdateEmployee(Employee employee) throws FOGException {
        DatabaseFacade.UpdateEmployee(employee);
    }

    public static void fireEmployee(int employeeId) throws FOGException {
        DatabaseFacade.fireEmployee(employeeId);
    }

    public static void resetEmployeePassword(int employeeId) throws FOGException {
        DatabaseFacade.resetEmployeePassword(employeeId);
    }

    public static void changePassword(int employeeId, String password) throws FOGException {
        String salt = Hashing.randomString(10);
        password = password.concat(salt);
        String hash = HashPassword(password);

        DatabaseFacade.changePassword(employeeId, hash, salt);
    }

    public static void createEmployee(Employee emp) throws FOGException {

        String password = Hashing.randomString(20);

        String salt = Hashing.randomString(10);
        String newPassword = password.concat(salt);
        String hash = HashPassword(newPassword);

        DatabaseFacade.CreateEmployee(emp, hash, salt);

        String title = "Welcome to Fog";
        String message = "Welcome to fog we are very excited to work with you.\n"
                + "Below you will find your new password, the first time toy log in you wil be asked to create a new.\n"
                + "Password: " + password
                + "\n Kindest regards FOG A/S";

        SendEmail.sendMail(emp.getEmail(), title, message);
    }

    public static void emailToAllEmployeeWithNewOrder(int orderId) throws FOGException {
        List<Employee> empList = DatabaseFacade.getAllEmployees(true);
        Order order = getOrder(orderId);
        String Tile = "New Order";
        String message = "At " + order.simpleDate() + " We recived a new order from " + order.getCustomer().getFirstname()
                + " " + order.getCustomer().getLastname() + ". We should do our best to fit his/hers needs.\n\n"
                + "And remember teamwork makes the dream work...";

        for (Employee emp : empList) {
            try {
                SendEmail.sendMail(emp.getEmail(), Tile, message);
            } catch (Exception e) {
            }
        }
    }

    public static void writeLine(Product prod, int orderId) throws FOGException {
        DatabaseFacade.writeLine(prod, orderId);
    }

    public static void writeLines(List<Product> prods, int orderId) throws FOGException {
        DatabaseFacade.writeLines(prods, orderId);
    }

    public static void removeLines(int orderId) throws FOGException {
        DatabaseFacade.removeLines(orderId);
    }

    public static List<Product> orderProducts(int orderId) throws FOGException {
        return DatabaseFacade.orderProducts(orderId);
    }

    public static Product getProduct(int id) throws FOGException {
        return DatabaseFacade.getProduct(id);
    }

    public static Calculator getCalculator(Order order) throws FOGException {
        return new Calculator(order);
    }

}
