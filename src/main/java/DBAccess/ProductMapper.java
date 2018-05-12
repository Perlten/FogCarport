/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.FOGException;
import FunctionLayer.calculator.Product;
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
            throw new FOGException(e.getMessage());
        }
    }

    public void removeLines(int orderid) throws FOGException {
        try {
            String sql = "DELETE FROM fog.product_line WHERE idorder = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, orderid);
            pre.execute();
        } catch (SQLException e) {
            throw new FOGException(e.getMessage());
        }
    }

    public List<Product> orderProducts(int orderid) throws FOGException {
        List<Product> res = null;
        try {
            String sql = "SELECT * FROM fog.product_line INNER JOIN fog.product "
                    + "ON product_line.idproduct = product.idproduct WHERE idorder = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, orderid);
            res = convert(pre.executeQuery());
        } catch (FOGException e) {
            throw e;
        } catch (SQLException e) {
            throw new FOGException(e.getMessage());
        }

        return res;
    }

    public Product getProduct(int id) throws FOGException {
        Product res = null;
        try {
            String sql = "SELECT * FROM fog.product WHERE idproduct = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            res = convertProducts(pre.executeQuery()).get(0);
        } catch (Exception e) {
            throw new FOGException(e.getMessage());
        }
        return res;
    }
    
    

    public List<Product> convert(ResultSet res) throws FOGException {
        List<Product> products = new ArrayList<>();
        try {
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
        } catch (SQLException e) {
            throw new FOGException(e.getMessage());
        }
        return products;
    }
    
    public List<Product> convertProducts(ResultSet res) throws FOGException {
        List<Product> products = new ArrayList<>();
        try {
            while (res.next()) {

                int id = res.getInt("idproduct");
                String title = res.getString("title");
                String description = res.getString("description");
                String unit = res.getString("unit");
                double length = res.getDouble("length");
                double price = res.getDouble("price");

                products.add(new Product(title, description, unit, 0, id, price, length, 0));
            }
        } catch (SQLException e) {
            throw new FOGException(e.getMessage());
        }
        return products;
    }
}
