/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Perlt
 */
public class OrderMapperTest {

    private String sql;

    public OrderMapperTest() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("sql/test_db.sql"), "UTF-8"));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        this.sql = sb.toString();
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {

        Statement statement = new TestConnection().connection().createStatement();
        statement.executeUpdate(sql);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        Assert.fail("you fail");
    }

}
