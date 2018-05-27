package FunctionLayer;

import DBAccess.DataFacade;
import FunctionLayer.entities.Employee;
import FunctionLayer.entities.Event;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Product;
import FunctionLayer.entities.StyleOption;
import java.util.ArrayList;
import java.util.List;
import static FunctionLayer.Hashing.hashPassword;

public class LogicFacade {

    /**
     * returns all of the orders by inputing -1 in getOrders
     *
     * @return
     * @throws FOGException
     */
    public static List<Order> getOrders() throws FOGException {
        return DataFacade.getAllOrders();
    }

    public static List<Order> getCustomerList(int limit) throws FOGException {
        return DataFacade.getCustomerList(limit);
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
        return DataFacade.getOrder(orderId);
    }

    public static void makeOrder(Order order) throws FOGException {
        DataFacade.makeOrder(order);
    }

    /**
     * Confirms order
     *
     * @param orderId
     * @throws FOGException
     */
    public static void confirmOrder(int orderId) throws FOGException {
        DataFacade.confirmOrder(orderId);
    }

    /**
     * Changes to order in
     *
     * @param order
     * @throws FOGException
     */
    public static void changeOrder(Order order) throws FOGException {
        DataFacade.changeOrder(order);
    }

    /**
     * Removes shed
     *
     * @param orderId
     * @throws FOGException
     */
    public static void removeOrder(int orderId) throws FOGException {
        DataFacade.removeOrder(orderId);
    }

    /**
     *
     * @return @throws FOGException
     */
    public static List<StyleOption> getCladdingList() throws FOGException {
        return DataFacade.getCladdingList();
    }

    /**
     *
     * @return @throws FOGException
     */
    public static List<StyleOption> getTileList() throws FOGException {
        return DataFacade.getTileList();
    }

    /**
     *
     * @param id
     * @return
     * @throws FOGException
     */
    public static StyleOption getCladding(int id) throws FOGException {
        return DataFacade.getCladding(id);
    }

    public static void unconfirmOrder(int id) throws FOGException {
        DataFacade.unconfirmOrder(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws FOGException
     */
    public static StyleOption getTile(int id) throws FOGException {
        return DataFacade.getTile(id);
    }

    public static void updateCladding(StyleOption cladding, int id) throws FOGException {
        DataFacade.updateCladding(cladding, id);
    }

    public static void updateTile(StyleOption tile, int id) throws FOGException {
        DataFacade.updateTile(tile, id);
    }

    public static void createCladding(StyleOption cladding) throws FOGException {
        DataFacade.createCladding(cladding);
    }

    public static void createTile(StyleOption tile) throws FOGException {
        DataFacade.createTile(tile);
    }

    public static void removeCladding(int id) throws FOGException {
        DataFacade.removeCladding(id);
    }

    public static void removeTile(int id) throws FOGException {
        DataFacade.removeTile(id);
    }

    public static void sendEmailToCustomer(Order order) throws FOGException {
        String title = "Fog carport offer";

        String textMessage = "Dear " + order.getCustomer().getFirstname() + ",\n\n"
                + "We thank you for your recent carport request!"
                + "\nTo follow the process of your request, please use your"
                + " reference link:\n\n"
                + " http://159.89.19.132/FogCarport/FrontController?command=LoadOrder&id=" + order.getOrderid()
                + "\n\nRegards,\nFog";
        SendEmail email = new SendEmail(order.getCustomer().getEmail(), title, textMessage);
        Thread thread = new Thread(email);
        thread.start();
    }

    public static Employee verfyLogin(String username, String password) throws FOGException {
        String salt = DataFacade.getSalt(username);
        password = password.concat(salt);
        String hash = hashPassword(password);
        return DataFacade.verfyLogin(username, hash);
    }

    public static void SendNewPasswordToEmployee(String email) throws FOGException {
        Employee emp = DataFacade.getEmployeeByEmail(email);

        String password = Hashing.randomString(20);

        String salt = Hashing.randomString(10);
        String newPassword = password.concat(salt);
        String hash = hashPassword(newPassword);

        DataFacade.resetPasswordAndSetResetTrue(emp.getEmployeeId(), hash, salt);

        String title = "New password";
        String text = "Here stupid here is your new password... DONT LOSE IT AGAIN... moron!!\n\n"
                + "Password: " + password;
        SendEmail emailSender = new SendEmail(email, title, text);
        Thread thread = new Thread(emailSender);
        thread.start();
    }

    public static Employee getEmployeeByEmail(String email) throws FOGException {
        return DataFacade.getEmployeeByEmail(email);
    }

    public static int numberOfConfirmedOrder() throws FOGException {
        return DataFacade.numberOfConfirmedOrder();
    }

    public static List<Order> getLatest10UnconfirmedOrders() throws FOGException {
        return DataFacade.getLatestUnconfirmedOrders(10);
    }

    public static void writeOrderEmployeeEvent(Event event) throws FOGException {
        DataFacade.writeOrderEmployeeEvent(event);
    }

    public static void writeOrderEvent(Event event) throws FOGException {
        DataFacade.writeOrderEvent(event);
    }

    public static void writeEmployeeEvent(Event event) throws FOGException {
        DataFacade.writeEmployeeEvent(event);
    }

    public static List<Event> getOrderEvent(int orderid) throws FOGException {
        return DataFacade.getOrderEvent(orderid);
    }

    public static List<Event> getEmployeeEvent(int employeeId, int limit) throws FOGException {
        return DataFacade.getEmployeeEvent(employeeId, limit);
    }

    public static List<Employee> getAllEmployees() throws FOGException {
        return DataFacade.getAllEmployees(false);
    }

    public static Employee getEmployeeById(int id) throws FOGException {
        return DataFacade.getEmployeeById(id);
    }

    public static void UpdateEmployee(Employee employee) throws FOGException {
        DataFacade.UpdateEmployee(employee);
    }

    public static void fireEmployee(int employeeId) throws FOGException {
        DataFacade.fireEmployee(employeeId);
    }

    public static void resetEmployeePassword(int employeeId) throws FOGException {
        DataFacade.resetEmployeePassword(employeeId);
    }

    public static void changePassword(int employeeId, String password) throws FOGException {
        String salt = Hashing.randomString(10);
        password = password.concat(salt);
        String hash = hashPassword(password);

        DataFacade.changePassword(employeeId, hash, salt);
    }

    public static void createEmployee(Employee emp) throws FOGException {

        String password = Hashing.randomString(20);

        String salt = Hashing.randomString(10);
        String newPassword = password.concat(salt);
        String hash = hashPassword(newPassword);

        DataFacade.CreateEmployee(emp, hash, salt);

        String title = "Welcome to Fog";
        String message = "Welcome to fog we are very excited to work with you.\n"
                + "Below you will find your new password, the first time toy log in you wil be asked to create a new.\n"
                + "Password: " + password
                + "\n Kindest regards FOG A/S";
        SendEmail email = new SendEmail(emp.getEmail(), title, message);
        Thread thread = new Thread(email);
        thread.start();
    }

    public static void emailToAllEmployeeWithNewOrder(int orderId) throws FOGException {

        List<Employee> empList = DataFacade.getAllEmployees(true);
        Order order = getOrder(orderId);
        String Tile = "New Order";
        String message = "At " + order.simpleDate() + " We recived a new order from " + order.getCustomer().getFirstname()
                + " " + order.getCustomer().getLastname() + ". We should do our best to fit his/hers needs.\n\n"
                + "And remember teamwork makes the dream work...";

        List<String> emailList = new ArrayList<>();
        for (Employee employee : empList) {
            emailList.add(employee.getEmail());
        }
        SendEmail email = new SendEmail(emailList, Tile, message);
        Thread thread = new Thread(email);
        thread.start();
    }

    public static void writeLine(Product prod, int orderId) throws FOGException {
        DataFacade.writeLine(prod, orderId);
    }

    public static void writeLines(List<Product> prods, int orderId) throws FOGException {
        DataFacade.writeLines(prods, orderId);
    }

    public static void removeLines(int orderId) throws FOGException {
        DataFacade.removeLines(orderId);
    }

    public static List<Product> orderProducts(int orderId) throws FOGException {
        return DataFacade.orderProducts(orderId);
    }

    public static Product getProduct(int id) throws FOGException {
        return DataFacade.getProduct(id);
    }

    public static Calculator getCalculator(Order order) throws FOGException {
        return new Calculator(order);
    }

    public static List<Event> getAllEvents(int limit) throws FOGException {
        return new DataFacade().getAllEvents(limit);
    }

}
