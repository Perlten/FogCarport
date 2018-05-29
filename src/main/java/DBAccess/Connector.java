/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Perlt
 */
public interface Connector {

    /**
     * Sets connection
     *
     * @param con Connection
     */
    public void setConnection(Connection con);

    /**
     * Gets Connection
     *
     * @return Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection connection() throws ClassNotFoundException, SQLException;

}
