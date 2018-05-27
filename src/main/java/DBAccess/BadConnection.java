package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 The purpose of Connector is to...

 @author kasper
 */
public class BadConnection implements Connector{

   private static final String URL = "jdbc:mysql://159.89.19.132:3306/fog_test?autoReconnect=true&serverTimezone=UTC&useSSL=false&allowMultiQueries=true";
    private static final String USERNAME = "errorConnection";
    private static final String PASSWORD = "Test.1234";

    private static Connection singleton;

    @Override
    public void setConnection( Connection con ) {
        singleton = con;
    }

    @Override
    public Connection connection() throws ClassNotFoundException, SQLException {
        if ( singleton == null ) {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            singleton = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        }
        return singleton;
    }

}
