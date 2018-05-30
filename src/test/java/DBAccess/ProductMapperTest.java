/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Perlt
 */
public class ProductMapperTest {

    private final String sql;
    private final ProductMapper mapper;
    private final Connection con;

    public ProductMapperTest() throws IOException, ClassNotFoundException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("sql/test_db.sql"), "UTF-8"));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
        }
        this.sql = sb.toString();
        this.con = new TestConnection().connection();
        this.mapper = new ProductMapper(con);
    }

    @Before
    public void setUp() throws SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }

    /**
     * Test of writeLine method, of class ProductMapper.
     */
    @Test
    public void testWriteLine() throws Exception {
        int orderId = 1;
        int expected = mapper.getOrderProducts(orderId).size() + 1;
        mapper.writeLine(1, orderId, 2, 600);
        int actual = mapper.getOrderProducts(orderId).size();
        assertEquals(expected, actual);
    }

    /**
     * Test of removeLines method, of class ProductMapper.
     */
    @Test
    public void testRemoveLines() throws Exception {
        int orderId = 2;
        int expected = mapper.getOrderProducts(orderId).size() - 1;
        mapper.removeLines(orderId);
        int actual = mapper.getOrderProducts(orderId).size();
        assertEquals(expected, actual);
    }

    /**
     * Test of getOrderProducts method, of class ProductMapper.
     */
    @Test
    public void testGetOrderProducts() throws Exception {
        int orderId = 3;
        int expected = 2;
        int actual = mapper.getOrderProducts(orderId).size();
        assertEquals(expected, actual);
    }

    /**
     * Test of getProduct method, of class ProductMapper.
     */
    @Test
    public void testGetProduct() throws Exception {
        String expected = "Rafter";
        String actual = mapper.getProduct(1).getTitle();
        assertEquals(expected, actual);
    }
}
