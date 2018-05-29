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

    /**
     * Creates Order
     *
     * @param order
     * @throws FOGException
     */
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

    /**
     * Updates Cladding
     *
     * @param cladding
     * @param id
     * @throws FOGException
     */
    public static void updateCladding(StyleOption cladding, int id) throws FOGException {
        DataFacade.updateCladding(cladding, id);
    }

    /**
     * Update Tile
     *
     * @param tile
     * @param id
     * @throws FOGException
     */
    public static void updateTile(StyleOption tile, int id) throws FOGException {
        DataFacade.updateTile(tile, id);
    }

    /**
     * Create Cladding
     *
     * @param cladding
     * @throws FOGException
     */
    public static void createCladding(StyleOption cladding) throws FOGException {
        DataFacade.createCladding(cladding);
    }

    /**
     * Create Tile
     *
     * @param tile
     * @throws FOGException
     */
    public static void createTile(StyleOption tile) throws FOGException {
        DataFacade.createTile(tile);
    }

    /**
     * Remove Cladding
     *
     * @param id
     * @throws FOGException
     */
    public static void removeCladding(int id) throws FOGException {
        DataFacade.removeCladding(id);
    }

    /**
     * Remove Tile
     *
     * @param id
     * @throws FOGException
     */
    public static void removeTile(int id) throws FOGException {
        DataFacade.removeTile(id);
    }

    /**
     * Send email to customer with confirmation of purchase
     *
     * @param order
     * @throws FOGException
     */
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

    /**
     * Return an Employee if username and password exits for a employee in
     * database, or Exception if it does not. Is case sensitive
     *
     * @param username
     * @param password
     * @return Employee
     * @throws FOGException
     */
    public static Employee verfyLogin(String username, String password) throws FOGException {
        String salt = DataFacade.getSalt(username);
        password = password.concat(salt);
        String hash = hashPassword(password);
        return DataFacade.verfyLogin(username, hash);
    }

    /**
     * Sends a new password to a employee trough mail
     *
     * @param email
     * @throws FOGException
     */
    public static void SendNewPasswordToEmployee(String email) throws FOGException {
        Employee emp = DataFacade.getEmployeeByEmail(email);

        String password = Hashing.randomString(20);

        String salt = Hashing.randomString(10);
        String newPassword = password.concat(salt);
        String hash = hashPassword(newPassword);

        DataFacade.resetPasswordAndSetResetTrue(emp.getEmployeeId(), hash, salt);

        String title = "New password";
        String text = "Here is your new password... DONT LOSE IT AGAIN...\n\n"
                + "Password: " + password;
        SendEmail emailSender = new SendEmail(email, title, text);
        Thread thread = new Thread(emailSender);
        thread.start();
    }

    /**
     * Gets Employee by email
     *
     * @param email
     * @return Employee
     * @throws FOGException
     */
    public static Employee getEmployeeByEmail(String email) throws FOGException {
        return DataFacade.getEmployeeByEmail(email);
    }

    /**
     * Gets number of unconfirmed Order
     *
     * @return int
     * @throws FOGException
     */
    public static int numberOfConfirmedOrder() throws FOGException {
        return DataFacade.numberOfConfirmedOrder();
    }

    /**
     * Return the 10 latest orders
     *
     * @return List of Order
     * @throws FOGException
     */
    public static List<Order> getLatest10UnconfirmedOrders() throws FOGException {
        return DataFacade.getLatestUnconfirmedOrders(10);
    }

    /**
     * Write Event relating to a Employee and Order
     *
     * @param event
     * @throws FOGException
     */
    public static void writeOrderEmployeeEvent(Event event) throws FOGException {
        DataFacade.writeOrderEmployeeEvent(event);
    }

    /**
     * Write Event relating to a Order
     *
     * @param event
     * @throws FOGException
     */
    public static void writeOrderEvent(Event event) throws FOGException {
        DataFacade.writeOrderEvent(event);
    }

    /**
     * Write Event relating to a Employee
     *
     * @param event
     * @throws FOGException
     */
    public static void writeEmployeeEvent(Event event) throws FOGException {
        DataFacade.writeEmployeeEvent(event);
    }

    /**
     * Gets Order event
     *
     * @param orderid
     * @return List of Events
     * @throws FOGException
     */
    public static List<Event> getOrderEvent(int orderid) throws FOGException {
        return DataFacade.getOrderEvent(orderid);
    }

    /**
     * Gets Employee event
     *
     * @param orderid
     * @return List of Events
     * @throws FOGException
     */
    public static List<Event> getEmployeeEvent(int employeeId, int limit) throws FOGException {
        return DataFacade.getEmployeeEvent(employeeId, limit);
    }

    /**
     * Gets all Employees
     *
     * @return List with Employee
     * @throws FOGException
     */
    public static List<Employee> getAllEmployees() throws FOGException {
        return DataFacade.getAllEmployees(false);
    }

    /**
     * Gets Employee by id
     *
     * @param id
     * @return Employee
     * @throws FOGException
     */
    public static Employee getEmployeeById(int id) throws FOGException {
        return DataFacade.getEmployeeById(id);
    }

    /**
     * Update Employee
     *
     * @param employee
     * @throws FOGException
     */
    public static void UpdateEmployee(Employee employee) throws FOGException {
        DataFacade.UpdateEmployee(employee);
    }

    /**
     * Fire Employee
     *
     * @param employeeId
     * @throws FOGException
     */
    public static void fireEmployee(int employeeId) throws FOGException {
        DataFacade.fireEmployee(employeeId);
    }

    /**
     * Resets Employee´s password
     *
     * @param employeeId
     * @throws FOGException
     */
    public static void resetEmployeePassword(int employeeId) throws FOGException {
        DataFacade.resetEmployeePassword(employeeId);
    }

    /**
     * Changes Employee´s password
     *
     * @param employeeId
     * @param password
     * @throws FOGException
     */
    public static void changePassword(int employeeId, String password) throws FOGException {
        String salt = Hashing.randomString(10);
        password = password.concat(salt);
        String hash = hashPassword(password);

        DataFacade.changePassword(employeeId, hash, salt);
    }

    /**
     * Creates Employee
     *
     * @param emp
     * @throws FOGException
     */
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

    /**
     * Sends out an email to all employee regarding a order
     *
     * @param orderId
     * @throws FOGException
     */
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

    /**
     * Write an order line
     *
     * @param prod
     * @param orderId
     * @throws FOGException
     */
    public static void writeLine(Product prod, int orderId) throws FOGException {
        DataFacade.writeLine(prod, orderId);
    }

    /**
     * Write multiple order lines
     *
     * @param prods
     * @param orderId
     * @throws FOGException
     */
    public static void writeLines(List<Product> prods, int orderId) throws FOGException {
        DataFacade.writeLines(prods, orderId);
    }

    /**
     * Removes order line
     *
     * @param orderId
     * @throws FOGException
     */
    public static void removeLines(int orderId) throws FOGException {
        DataFacade.removeLines(orderId);
    }

    /**
     * List of Product for a Order
     *
     * @param orderId
     * @return List of Product
     * @throws FOGException
     */
    public static List<Product> orderProducts(int orderId) throws FOGException {
        return DataFacade.orderProducts(orderId);
    }

    /**
     * Gets Product
     *
     * @param id
     * @return Product
     * @throws FOGException
     */
    public static Product getProduct(int id) throws FOGException {
        return DataFacade.getProduct(id);
    }

    /**
     * Return Calculator
     *
     * @param order
     * @return Calculator
     * @throws FOGException
     */
    public static Calculator getCalculator(Order order) throws FOGException {
        return new Calculator(order);
    }

    /**
     * Get all events
     *
     * @param limit
     * @return List of Events
     * @throws FOGException
     */
    public static List<Event> getAllEvents(int limit) throws FOGException {
        return new DataFacade().getAllEvents(limit);
    }

}
