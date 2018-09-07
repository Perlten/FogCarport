package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection design to fail
 *
 */
public class BadConnection implements Connector {

    private static final String URL = "jdbc:mysql://165.227.144.45:3306/fog_test?autoReconnect=true&serverTimezone=UTC&useSSL=false&allowMultiQueries=true";
    private static final String USERNAME = "fog";
    private static final String PASSWORD = "fog";

    private static Connection singleton;

    @Override
    public void setConnection(Connection con) {
        singleton = con;
    }

    @Override
    public Connection connection() throws ClassNotFoundException, SQLException {
        if (singleton == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return singleton;
    }

}
