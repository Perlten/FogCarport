package FunctionLayer;

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
        return new OrderMapper().getOrders(-1);
    }

    public static List<Order> getCustomerList(int limit) throws FOGException {
        return new OrderMapper().getOrderCustomerList(limit);
    }

    /**
     * Returns a specific order.
     *
     * @param orderid A valid id of an order.
     * @return if valid input, the
     * @throws FOGException
     */
    public static Order getOrder(int orderid) throws FOGException {
        if (orderid < 0) {
            throw new IllegalArgumentException("orderid can't be negative");
        }
        return new OrderMapper().getOrders(orderid).get(0);
    }

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
     * Changes to order in
     *
     * @param order
     * @throws FOGException
     */
    public static void changeOrder(Order order) throws FOGException {
        new OrderMapper().changeOrder(order);
    }

    /**
     * Removes shed
     *
     * @param orderId
     * @throws FOGException
     */
    public static void removeOrder(int orderId) throws FOGException {
        new OrderMapper().removeOrder(orderId);
    }

    /**
     *
     * @return @throws FOGException
     */
    public static List<StyleOption> getCladdingList() throws FOGException {
        return new StyleMapper().getCladding(-1);
    }

    /**
     *
     * @return @throws FOGException
     */
    public static List<StyleOption> getTileList() throws FOGException {
        return new StyleMapper().getTile(-1);
    }

    /**
     *
     * @param id
     * @return
     * @throws FOGException
     */
    public static StyleOption getCladding(int id) throws FOGException {
        if (!new StyleMapper().getCladding(id).isEmpty()) {
            return new StyleMapper().getCladding(id).get(0);
        }
        return null;
    }

    public static void unconfirmOrder(int id) throws FOGException {
        new OrderMapper().unconfirmOrder(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws FOGException
     */
    public static StyleOption getTile(int id) throws FOGException {
        if (!new StyleMapper().getTile(id).isEmpty()) {
            return new StyleMapper().getTile(id).get(0);
        }
        return null;
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
        String salt = new EmployeeMapper().getSalt(username);
        password = password.concat(salt);
        String hash = HashPassword(password);
        return new EmployeeMapper().verfyLogin(username, hash);
    }

    public static void SendNewPasswordToEmployee(String email) throws FOGException {
        Employee emp = new EmployeeMapper().getEmployeeByEmail(email);

        //TODO: Might use our encryption class when we make it ;)
        Random ra = new Random();
        String password = "";
        String toChange = emp.getUsername() + emp.getEmail();
        for (int i = 0; i < toChange.length(); i++) {
            char x = toChange.charAt(i);
            x += ra.nextInt(5);
            password += x;
        }
        String salt = Hashing.makeSalt();
        String newPassword = password.concat(salt);
        String hash = HashPassword(newPassword);

        new EmployeeMapper().changePasswordAndResetPassword(emp.getEmployeeId(), hash, salt);

        String title = "New password";
        String text = "Here stupid here is your new password... DONT LOSE IT AGAIN... moron!!\n\n"
                + "Password: " + password;

        SendEmail.sendMail(emp.getEmail(), title, text);
    }

    public static Employee getEmployeeByEmail(String email) throws FOGException {
        return new EmployeeMapper().getEmployeeByEmail(email);
    }

    public static int numberOfConfirmedOrder() throws FOGException {
        return new OrderMapper().numberOfUnconfirmedOrders();
    }

    public static List<Order> getLatest10UnconfirmedOrders() throws FOGException {
        return new OrderMapper().getUnconfirmedOrders(10);
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

    public static List<Employee> getAllEmployees() throws FOGException {
        return new EmployeeMapper().getAllEmployees(false);
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

    public static void changePassword(int employeeId, String password) throws FOGException {
        String salt = Hashing.makeSalt();
        password = password.concat(salt);
        String hash = HashPassword(password);

        new EmployeeMapper().changePasswordAndRemoveResetPassword(employeeId, hash, salt);
    }

    public static void createEmployee(String firstname, String lastname, String username, String email, int accessLevel) throws FOGException {

        Random ra = new Random();
        String password = "";

        for (int i = 0; i < 20; i++) {
            char x = 'a';
            x += ra.nextInt(25);
            password += x;
        }

        String salt = Hashing.makeSalt();
        String newPassword = password.concat(salt);
        String hash = HashPassword(newPassword);

        new EmployeeMapper().createEmployee(firstname, lastname, username, email, accessLevel, hash, salt);

        String title = "Welcome to Fog";
        String message = "Welcome to fog we are very excited to work with you.\n"
                + "Below you will find your new password, the first time toy log in you wil be asked to create a new.\n"
                + "Password: " + password
                + "\n Kindest regards FOG A/S";

        SendEmail.sendMail(email, title, message);

    }

    public static void emailToAllEmployeeWithNewOrder(int orderId) throws FOGException {
        List<Employee> empList = new EmployeeMapper().getAllEmployees(true);
        Order order = getOrder(orderId);
        String Tile = "New Order";
        String message = "At " + order.simpleDate() + " We recived a new order from " + order.getCustomer().getFirstname()
                + " " + order.getCustomer().getLastname() + ". We should do our best to fit his/hers needs.\n\n"
                + "And remember teamwork makes the dream work...";

        for (Employee emp : empList) {
            SendEmail.sendMail(emp.getEmail(), Tile, message);
        }
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
        return new ProductMapper().orderProducts(orderId);
    }

    public static Product getProduct(int id) throws FOGException {
        return new ProductMapper().getProduct(id);
    }

    public static Calculator getCalculator(Order order) {
        return new Calculator(order);
    }

}
