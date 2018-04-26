package DBAccess;

import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adamlass
 */
public class OrderMapper {

    /**
     * Get a list of all the orders or a specific order if orderid is 0 or over.
     *
     * @param orderid under 0 for all orders. 0 or over for a specific order
     * @return A list of orders
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static List<Order> getOrders(int orderid) throws ClassNotFoundException, SQLException {
        List<Order> orderList = new ArrayList<>();
        Connection con = Connector.connection();
        String sql = "SELECT * FROM fog.order";
        if (orderid >= 0) {
            sql += " where idorder=?";
        }

        PreparedStatement pre = con.prepareStatement(sql);

        if (orderid >= 0) {
            pre.setInt(1, orderid);
        }

        ResultSet res = pre.executeQuery();

        while (res.next()) {
            int idorder = res.getInt("idorder");
            boolean confirmed = res.getBoolean("confirmed");
            Calendar date = Calendar.getInstance();
            Timestamp ts = res.getTimestamp("date");
            date.setTime((Date) ts);
            String firstname = res.getString("firstname");
            String lastname = res.getString("lastname");
            String email = res.getString("email");
            int phonenumber = res.getInt("phonenumber");
            int length = res.getInt("length");
            int width = res.getInt("width");
            int height = res.getInt("height");
            double roofangle = res.getDouble("roofangle");
            boolean shed = res.getBoolean("shed");
            Shed shedEntity = null;

            if (shed) {
                int shedLength = res.getInt("shed_length");
                int shedWidth = res.getInt("shed_width");
                shedEntity = new Shed(shedLength, shedWidth);

            }

            int tile = res.getInt("tile");
            int cladding = res.getInt("cladding");
            Customer customer = new Customer(firstname, lastname, email, phonenumber);
            Customization customization = new Customization(length, width, height, roofangle, shedEntity);
            Order order = new Order(idorder, confirmed, date, customer, customization);
            orderList.add(order);
        }
        return orderList;
    }

    /**
     * Changes the order in the database
     *
     * @param order
     */
    public static void changeOrder(Order order) {
        String sql = "UPDATE fog.order SET "
                + "confirmed = ?, firstname = ?, lastname = ?, email = ?, phonenumber = ?, length = ?, width = ?, height = ?,"
                + "roofangle = ?, shed = ?, shed_length = ?, shed_width = ?, tile = ?, cladding = ? where idorder = ?";
        Customization customization = order.getCustomization();
        Customer customer = order.getCustomer();
        int shedLength = 0;
        int shedWidth = 0;
        boolean shed = false;
        if (customization.getShed() != null) {
            shedLength = customization.getShed().getLength();
            shedWidth = customization.getShed().getWidth();
            shed = true;
        }
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, order.isConfirmed());
            ps.setString(2, customer.getFirstname());
            ps.setString(3, customer.getLastname());
            ps.setString(4, customer.getEmail());
            ps.setInt(5, customer.getPhonenumber());
            ps.setInt(6, customization.getLength());
            ps.setInt(7, customization.getWidth());
            ps.setInt(8, customization.getHeight());
            ps.setDouble(9, customization.getRoofangle());
            ps.setBoolean(10, shed);
            ps.setInt(11, shedLength);
            ps.setInt(12, shedWidth);
            ps.setInt(13, 1);
            ps.setInt(14, 2);
            ps.setInt(15, order.getOrderid());
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Confirms order
     *
     * @param id
     */
    public static void confirmOder(int id) {
        String sql = "UPDATE fog.order SET confirmed = true WHERE idorder = ?;";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            //TODO: change the way i handle exceptions
            ex.printStackTrace();
        }
    }

    public static void removeOrder(int orderId) {
        String sql = "DELETE FROM fog.order WHERE idorder = ?";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates a order
     *
     * @param order
     */
    public static void MakeOrder(Order order) {
        String sql = "INSERT INTO fog.order(firstname, lastname, email, phonenumber, length, width, height, roofangle, shed, shed_length, shed_width, tile, cladding)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        boolean shed = order.getCustomization().getShed() != null;
        int shedLength = 0;
        int shedWidth = 0;
        if (shed) {
            shedLength = order.getCustomization().getShed().getLength();
            shedWidth = order.getCustomization().getShed().getWidth();
        }
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, order.getCustomer().getFirstname());
            ps.setString(2, order.getCustomer().getLastname());
            ps.setString(3, order.getCustomer().getEmail());
            ps.setInt(4, order.getCustomer().getPhonenumber());
            ps.setInt(5, order.getCustomization().getLength());
            ps.setInt(6, order.getCustomization().getWidth());
            ps.setInt(7, order.getCustomization().getHeight());
            ps.setDouble(8, order.getCustomization().getRoofangle());
            ps.setBoolean(9, shed);
            ps.setInt(10, shedLength);
            ps.setInt(11, shedWidth);
            //TODO: change order to incorporate tile and cladding
            ps.setInt(12, 1);
            ps.setInt(13, 1);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void removeLast() {
        String sql = "delete from fog.order"
                + " order by idorder desc limit 1";
        try {
            Statement state = Connector.connection().createStatement();
            state.execute(sql);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static Order getLast() {
        String sql = "select idorder from fog.order"
                + " order by idorder desc limit 1";
        try {
            Statement state = Connector.connection().createStatement();
            ResultSet rs = state.executeQuery(sql);
            rs.next();
            List<Order> list = getOrders(rs.getInt("idorder"));
            return list.get(0);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(OrderMapper.getLast());
    }
}
