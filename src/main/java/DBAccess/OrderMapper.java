package DBAccess;

import FunctionLayer.FOGException;
import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import FunctionLayer.entities.StyleOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author adamlass
 */
public class OrderMapper {

    private Connection con;

    public OrderMapper() throws FOGException {
        try {
            con = new LiveConnection().connection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new FOGException("Could not find connection");
        }
    }

    public OrderMapper(Connection con) {
        this.con = con;
    }

    /**
     * Get a list of all the orders or a specific order if orderid is 0 or over.
     *
     * @param orderid under 0 for all orders. 0 or over for a specific order
     * @return A list of orders
     * @throws FOGException
     */
    public List<Order> getOrders(int orderid) throws FOGException {
        try {
            String sql = "SELECT * FROM .order";
            if (orderid > 0) {
                sql += " where idorder=?";
            } else {
                sql += " order by idorder desc;";
            }

            PreparedStatement pre = con.prepareStatement(sql);

            if (orderid > 0) {
                pre.setInt(1, orderid);
            }

            ResultSet res = pre.executeQuery();

            List<Order> list = orderConverter(res);

            if (orderid > 0 && list.size() < 1) {
                throw new FOGException("Could not find order");
            }

            return list;

        } catch (SQLException e) {
            throw new FOGException(e.getMessage());
        }
    }

    public List<Order> getOrderCustomerList(int limit) throws FOGException {

        String sql = "SELECT idorder, confirmed, date, firstname, lastname, email, phonenumber FROM .order order by idorder desc LIMIT ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, limit);

            ResultSet res = ps.executeQuery();
            return customerConverter(res);
        } catch (SQLException ex) {
            throw new FOGException("Could not get order");
        }
    }

    /**
     * Changes the order in the database
     *
     * @param order
     */
    public void changeOrder(Order order) throws FOGException {
        String sql = "UPDATE .order SET "
                + "confirmed = ?, firstname = ?, lastname = ?, email = ?, phonenumber = ?, length = ?, width = ?, height = ?,"
                + "roofangle = ?, shed = ?, shed_length = ?, shed_width = ?, tile = ?, cladding = ?, price = ? where idorder = ?";
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
            ps.setInt(13, customization.getTile().getId());

            StyleOption cladding = order.getCustomization().getCladding();
            if (cladding != null) {
                ps.setInt(14, cladding.getId());
            } else {
                ps.setNull(14, java.sql.Types.INTEGER);
            }

            ps.setDouble(15, order.getPrice());
            ps.setInt(16, order.getOrderid());

            ps.execute();
        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage());
        }
    }

    /**
     * Confirms order
     *
     * @param id
     */
    public void confirmOrder(int id) throws FOGException {
        String sql = "UPDATE .order SET confirmed = true WHERE idorder = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            throw new FOGException("Cound not confirm order");
        }
    }

    public void unconfirmOrder(int id) throws FOGException {
        String sql = "UPDATE .order SET confirmed = false WHERE idorder = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage());
        }
    }

    public void removeOrder(int orderId) throws FOGException {
        String sql = "DELETE FROM .order WHERE idorder = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.execute();
        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage());
        }
    }

    /**
     * Creates a order
     *
     * @param order
     */
    public void makeOrder(Order order) throws FOGException {
        String sql = "INSERT INTO .order(firstname, lastname, email, phonenumber, length, width, height, roofangle, shed, shed_length, shed_width, tile, cladding, price)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        boolean shed = order.getCustomization().getShed() != null;
        int shedLength = 0;
        int shedWidth = 0;
        if (shed) {
            shedLength = order.getCustomization().getShed().getLength();
            shedWidth = order.getCustomization().getShed().getWidth();
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
            ps.setInt(12, order.getCustomization().getTile().getId());

            if (order.getCustomization().getShed() != null) {
                ps.setInt(13, order.getCustomization().getCladding().getId());
            } else {
                ps.setNull(13, java.sql.Types.INTEGER);

            }
            ps.setDouble(14, order.getPrice());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            order.setOrderid(rs.getInt(1));

        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage());
        }
    }

    public int numberOfUnconfirmedOrders() throws FOGException {
        String sql = "SELECT COUNT(idorder) FROM .order WHERE confirmed = false";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new FOGException("Could not find number of unconfirmed orders");
        }
        return 0;
    }

    /**
     * Returns a list with unconfirmed orders
     *
     * @param limit Limits the number of orders. give 0 for all unconfirmed
     * orders
     * @return List with orders
     */
    public List<Order> getUnconfirmedOrders(int limit) throws FOGException {
        String sql = "SELECT idorder, confirmed, date, firstname, lastname, email, phonenumber FROM .order WHERE confirmed = false order by idorder desc";
        if (limit > 0) {
            sql += " LIMIT ?";
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (limit > 0) {
                ps.setInt(1, limit);
            }
            ResultSet rs = ps.executeQuery();
            return customerConverter(rs);
        } catch (SQLException e) {
            throw new FOGException("Could not get orders");
        }
    }

    private List<Order> orderConverter(ResultSet res) throws FOGException {
        try {
            List<Order> orderList = new ArrayList<>();
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
                double price = res.getDouble("price");
                boolean shed = res.getBoolean("shed");

                int tile = res.getInt("tile");
                int cladding = res.getInt("cladding");
                StyleMapper styleMapper = new StyleMapper(con);
                List<StyleOption> claddingList = styleMapper.getCladding(cladding);
                List<StyleOption> tileList = styleMapper.getTile(tile);
                StyleOption tileStyle = null;
                StyleOption claddingStyle = null;

                if (!claddingList.isEmpty()) {
                    claddingStyle = claddingList.get(0);
                }

                if (!tileList.isEmpty()) {
                    tileStyle = tileList.get(0);
                }

                Shed shedEntity = null;
                if (shed) {
                    int shedLength = res.getInt("shed_length");
                    int shedWidth = res.getInt("shed_width");
                    shedEntity = new Shed(shedLength, shedWidth);

                }
                Customer customer = new Customer(firstname, lastname, email, phonenumber);
                Customization customization = new Customization(length, width, height, roofangle, shedEntity, claddingStyle, tileStyle);
                Order order = new Order(idorder, true, confirmed, date, customer, customization, price);
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            throw new FOGException("Could not find order");
        }
    }

    private List<Order> customerConverter(ResultSet res) throws FOGException {
        try {
            List<Order> list = new ArrayList<>();
            while (res.next()) {
                int idorder = res.getInt("idorder");
                boolean confirmed = res.getBoolean("confirmed");
                Calendar date = Calendar.getInstance();
                Timestamp ts = res.getTimestamp("date");
                date.setTime((Date) ts);
                String firstName = res.getString("firstname");
                String lastName = res.getString("lastname");
                String email = res.getString("email");
                int phoneNumber = res.getInt("phonenumber");

                list.add(new Order(idorder, false, confirmed, date, new Customer(firstName, lastName, email, phoneNumber), null, 0));
            }
            return list;

        } catch (SQLException ex) {
            throw new FOGException("Could not find customer");
        }
    }
}
