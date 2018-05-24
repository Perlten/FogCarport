/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.entities.StyleOption;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Perlt
 */
public class StyleMapperTest {

    private final String sql;
    private final StyleMapper mapper;
    private final Connection con;

    public StyleMapperTest() throws IOException, ClassNotFoundException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("sql/test_db.sql"), "UTF-8"));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
        }
        this.sql = sb.toString();
        this.con = new TestConnection().connection();
        this.mapper = new StyleMapper(con);
    }

    @Before
    public void setUp() throws SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }

    /**
     * Test of getCladding method, of class StyleMapper.
     */
    @Test
    public void testGetCladding() throws Exception {
        int expected = 4;
        int actual = mapper.getCladding(-1).size();
        assertEquals(expected, actual);
    }

    /**
     * Test of getTile method, of class StyleMapper.
     */
    @Test
    public void testGetTile() throws Exception {
        int expected = 4;
        int actual = mapper.getTile(-1).size();
        assertEquals(expected, actual);
    }

    /**
     * Test of createCladding method, of class StyleMapper.
     */
    @Test
    public void testCreateCladding() throws Exception {
        StyleOption so = new StyleOption("Test", "test", 1);
        mapper.createCladding(so);
        int expected = 5;
        int actual = mapper.getCladding(-1).size();
        assertEquals(expected, actual);
    }

    /**
     * Test of createTile method, of class StyleMapper.
     */
    @Test
    public void testCreateTile() throws Exception {
        StyleOption so = new StyleOption("Test", "test", 1);
        mapper.createTile(so);
        int expected = 5;
        int actual = mapper.getTile(-1).size();
        assertEquals(expected, actual);
    }

    /**
     * Test of updateCladding method, of class StyleMapper.
     */
    @Test
    public void testUpdateCladding() throws Exception {
        StyleOption so = mapper.getCladding(1).get(0);
        so.setName("test");
        mapper.updateCladding(so, 1);
        String expected = "test";
        String actual = mapper.getCladding(1).get(0).getName();
        assertEquals(expected, actual);
    }

    /**
     * Test of updateTile method, of class StyleMapper.
     */
    @Test
    public void testUpdateTile() throws Exception {
        StyleOption so = mapper.getTile(1).get(0);
        so.setName("test");
        mapper.updateTile(so, 1);
        String expected = "test";
        String actual = mapper.getTile(1).get(0).getName();
        assertEquals(expected, actual);
    }

    /**
     * Test of removeStyleOption method, of class StyleMapper.
     */
    @Test
    public void testRemoveStyleOption() throws Exception {
        mapper.removeStyleOption(1, "cladding");
        mapper.removeStyleOption(1, "tile");
        int claddingExpected = 3;
        int tileExpected = 3;
        int claddingActual = mapper.getCladding(-1).size();
        int tileActual = mapper.getTile(-1).size();

        assertEquals(tileExpected, tileActual);
        assertEquals(claddingExpected, claddingActual);
    }
}
