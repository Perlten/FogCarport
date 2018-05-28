/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import org.junit.Test;

/**
 *
 * @author adamlass
 */
public class ConnectorTest {

    /**
     * Test of the connection to the mysql server.
     *
     * @throws Exception
     */
    @Test
    public void testConnection() throws Exception {
        new LiveConnection().connection();
        new TestConnection().connection();
        assert true;
    }

}
