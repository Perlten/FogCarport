/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adamlass
 */
public class ConnectorTest {

    public ConnectorTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of the connection to the mysql server.
     * @throws Exception 
     */
    @Test
    public void testConnection() throws Exception {
        Connector.connection();
        assert true;
    }

}
