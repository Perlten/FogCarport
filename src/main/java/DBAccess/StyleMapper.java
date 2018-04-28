/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.DAOException;
import FunctionLayer.entities.StyleOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Perlt
 */
public class StyleMapper {

    public static List<StyleOption> getCladding(int id) throws DAOException {
        List<StyleOption> list = new ArrayList<>();
        String sql = "select * from fog.cladding";
        if (id >= 0) {
            sql += " where idcladding = ?";
        }
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            if (id >= 0) {
                ps.setInt(1, id);
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                int Claddingid = rs.getInt("idcladding");

                list.add(new StyleOption(name, description, price, Claddingid));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException(ex.getMessage());
        }
        return list;
    }

    public static List<StyleOption> getTile(int id) throws DAOException {
        List<StyleOption> list = new ArrayList<>();
        String sql = "select * from fog.tile";
        if (id >= 0) {
            sql += " where idtile = ?";
        }
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            if (id >= 0) {
                ps.setInt(1, id);
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                int tileId = rs.getInt("idtile");

                list.add(new StyleOption(name, description, price, tileId));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException(ex.getMessage());
        }
        return list;
    }

    public static void createCladding(StyleOption cladding) throws DAOException {
        String sql = "INSERT INTO fog.cladding (name, description, price) VALUES(?,?,?)";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cladding.getName());
            ps.setString(2, cladding.getDescription());
            ps.setDouble(3, cladding.getPrice());
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static void createTile(StyleOption tile) throws DAOException {
        String sql = "INSERT INTO fog.tile (name, description, price) VALUES(?,?,?)";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tile.getName());
            ps.setString(2, tile.getDescription());
            ps.setDouble(3, tile.getPrice());
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static void updateCladding(StyleOption cladding, int id) throws DAOException {
        String sql = "UPDATE fog.cladding SET name = ?, description = ?, price = ? WHERE idcladding = ?";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cladding.getName());
            ps.setString(2, cladding.getDescription());
            ps.setDouble(3, cladding.getPrice());
            ps.setInt(4, id);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException(ex.getMessage());
        }
    }
    public static void updateTile(StyleOption tile, int id) throws DAOException {
        String sql = "UPDATE fog.tile SET name = ?, description = ?, price = ? WHERE idtile = ?";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tile.getName());
            ps.setString(2, tile.getDescription());
            ps.setDouble(3, tile.getPrice());
            ps.setInt(4, id);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException(ex.getMessage());
        }
    }
    
    public static void main(String[] args) throws DAOException {
        StyleOption c = new StyleOption("old", "old", 1);
        updateTile(c, 2);
    }
}
