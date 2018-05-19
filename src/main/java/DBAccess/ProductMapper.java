/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import DBAccess.LiveConnection;
import FunctionLayer.FOGException;
import FunctionLayer.entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adamlass
 */
public class ProductMapper {

    private Connection con;

    public ProductMapper() throws FOGException {
        try {
            con = new LiveConnection().connection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new FOGException("Could not find connection");
        }
    }

    public ProductMapper(Connection con) {
        this.con = con;
    }

    public void writeLine(int idProduct, int orderId, int amount, double lengthUsed) throws FOGException {
        try {
            String sql = "INSERT INTO product_line(idproduct,idorder,amount,length_used) values (?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, idProduct);
            pre.setInt(2, orderId);
            pre.setInt(3, amount);
            pre.setDouble(4, lengthUsed);
            pre.execute();
        } catch (SQLException e) {
            throw new FOGException("Could not write line");
        }
    }

    public void removeLines(int orderid) throws FOGException {
        try {
            String sql = "DELETE FROM product_line WHERE idorder = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, orderid);
            pre.execute();
        } catch (SQLException e) {
            throw new FOGException("Could not remove line");
        }
    }

    public List<Product> getOrderProducts(int orderid) throws FOGException {
        List<Product> res = null;
        try {
            String sql = "SELECT * FROM product_line INNER JOIN product "
                    + "ON product_line.idproduct = product.idproduct WHERE idorder = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, orderid);
            res = convert(pre.executeQuery());
        } catch (SQLException e) {
            throw new FOGException("could not get products");
        }
        return res;
    }

    public Product getProduct(int id) throws FOGException {
        try {
            String sql = "SELECT * FROM product WHERE idproduct = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            return convertProducts(pre.executeQuery()).get(0);
        } catch (SQLException e) {
            throw new FOGException("Could not get product");
        }
    }
    
    

    private List<Product> convert(ResultSet res) throws SQLException {
        List<Product> products = new ArrayList<>();
        
            while (res.next()) {
                double lengthUsed = res.getDouble("length_used");
                int amount = res.getInt("amount");

                int id = res.getInt("idproduct");
                String title = res.getString("title");
                String description = res.getString("description");
                String unit = res.getString("unit");
                double length = res.getDouble("length");
                double price = res.getDouble("price");

                products.add(new Product(title, description, unit, amount, id, price, length, lengthUsed));
            }
         
        return products;
    }
    
    private List<Product> convertProducts(ResultSet res) throws SQLException {
        List<Product> products = new ArrayList<>();
            while (res.next()) {

                int id = res.getInt("idproduct");
                String title = res.getString("title");
                String description = res.getString("description");
                String unit = res.getString("unit");
                double length = res.getDouble("length");
                double price = res.getDouble("price");

                products.add(new Product(title, description, unit, 0, id, price, length, 0));
            }
        return products;
    }
}
