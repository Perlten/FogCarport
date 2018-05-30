/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.FOGException;
import FunctionLayer.entities.StyleOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Perlt
 */
public class StyleMapper {

    private Connection con;

    /**
     * Creates Mapper with Connection to live database
     *
     * @throws FOGException
     */
    public StyleMapper() throws FOGException {
        try {
            con = new LiveConnection().connection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new FOGException("Could not find connection", Level.SEVERE);
        }
    }

    public StyleMapper(Connection con) {
        this.con = con;
    }

    /**
     * Returns List with Cladding. If id is under 1 all Claddings gets returned
     *
     * @param id
     * @return List with Cladding
     * @throws FOGException
     */
    public List<StyleOption> getCladding(int id) throws FOGException {
        List<StyleOption> list = new ArrayList<>();
        String sql = "select * from cladding";
        if (id >= 0) {
            sql += " where idcladding = ?";
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (id >= 0) {
                ps.setInt(1, id);
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int Claddingid = rs.getInt("idcladding");

                list.add(new StyleOption(name, description, price, Claddingid));
            }
        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage(), Level.WARNING);
        }
        return list;
    }

    /**
     * Returns List with tiles. If id is under 1 all tiles gets returned
     *
     * @param id
     * @return List with Tile
     * @throws FOGException
     */
    public List<StyleOption> getTile(int id) throws FOGException {
        List<StyleOption> list = new ArrayList<>();
        String sql = "select * from tile";
        if (id >= 0) {
            sql += " where idtile = ?";
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (id >= 0) {
                ps.setInt(1, id);
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int tileId = rs.getInt("idtile");

                list.add(new StyleOption(name, description, price, tileId));
            }
        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage(), Level.WARNING);
        }
        return list;
    }

    /**
     * Creates Cladding
     *
     * @param cladding
     * @throws FOGException
     */
    public void createCladding(StyleOption cladding) throws FOGException {
        String sql = "INSERT INTO cladding (name, description, price) VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cladding.getName());
            ps.setString(2, cladding.getDescription());
            ps.setDouble(3, cladding.getPrice());
            ps.execute();
        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage(), Level.WARNING);
        }
    }

    /**
     * Creates Tile
     *
     * @param tile
     * @throws FOGException
     */
    public void createTile(StyleOption tile) throws FOGException {
        String sql = "INSERT INTO tile (name, description, price) VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tile.getName());
            ps.setString(2, tile.getDescription());
            ps.setDouble(3, tile.getPrice());
            ps.execute();
        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage(), Level.WARNING);
        }
    }

    /**
     * Updates Cladding
     *
     * @param cladding
     * @param id
     * @throws FOGException
     */
    public void updateCladding(StyleOption cladding, int id) throws FOGException {
        String sql = "UPDATE cladding SET name = ?, description = ?, price = ? WHERE idcladding = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cladding.getName());
            ps.setString(2, cladding.getDescription());
            ps.setDouble(3, cladding.getPrice());
            ps.setInt(4, id);
            ps.execute();
        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage(), Level.INFO);
        }
    }

    /**
     * Updates Cladding
     *
     * @param tile
     * @param id
     * @throws FOGException
     */
    public void updateTile(StyleOption tile, int id) throws FOGException {
        String sql = "UPDATE tile SET name = ?, description = ?, price = ? WHERE idtile = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tile.getName());
            ps.setString(2, tile.getDescription());
            ps.setDouble(3, tile.getPrice());
            ps.setInt(4, id);
            ps.execute();
        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage(), Level.INFO);
        }
    }

    /**
     * Removes StyleOption
     *
     * @param id
     * @param type Type of StyleOption
     * @throws FOGException
     */
    public void removeStyleOption(int id, String type) throws FOGException {
        String sql = "DELETE FROM " + type + " WHERE id" + type + " = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage(), Level.INFO);
        }
    }
}
