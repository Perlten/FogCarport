package DBAccess;

import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderMapper {

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

    public static void changeCarportDimensions(int length, int width, int height, int roofAngle, int id) {
        String sql = "UPDATE fog.order SET length = ?, width = ?, height = ?, roofangle = ? WHERE idorder = ?";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, length);
            ps.setInt(2, width);
            ps.setInt(3, height);
            ps.setInt(4, roofAngle);
            ps.setInt(5, id);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void changeCladding(int orderId, int claddingId) {
        String sql = "UPDATE fog.order SET cladding = ? WHERE idorder = ?";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, claddingId);
            ps.setInt(2, orderId);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void changeTile(int orderId, int tileId) {
        String sql = "UPDATE fog.order SET tile = ? WHERE idorder = ?";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, tileId);
            ps.setInt(2, orderId);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void changeShed(int length, int width, int orderId) {
        String sql = "UPDATE fog.order SET shed_length = ?, shed_width = ? WHERE idorder = ?";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, length);
            ps.setInt(2, width);
            ps.setInt(3, orderId);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void removeShed(int orderId) {
        changeShed(0, 0, orderId);
        String sql = "UPDATE fog.order SET shed = false WHERE idorder = ?";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
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
     * @param order 
     */
    public static void MakeOrder(Order order) {
        String sql = "INSERT INTO fog.order(firstname, lastname, email, phonenumber, length, width, height, roofangle, shed, shed_length, shed_width, tile, cladding)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        boolean shed = order.getCustomization().getShed() != null;
        int shedLength = 0;
        int shedWidth = 0;
        if(shed){
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
    public static void main(String[] args) {
        Order o = new Order(false, null, new Customer("Nikolai p", "pp", "pppp", 32), new Customization(6, 6, 6, 1.5, null));
        OrderMapper.MakeOrder(o);
    }
}
