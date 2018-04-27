/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.entities.StyleOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Perlt
 */
public class StyleMapper {

    public static List<StyleOption> getCladding(int id) throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }
        return list;
    }

    public static List<StyleOption> getTile(int id) throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {

    }
}
