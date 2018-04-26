/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        if(orderid >= 0){
            sql += " where idorder=?";
        }
        
        PreparedStatement pre = con.prepareStatement(sql);
        
        if(orderid >= 0){
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
        con.close();
        return orderList;
    }
    
    
}
