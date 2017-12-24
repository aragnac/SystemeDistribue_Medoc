/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Nicolas
 */
public class MySql {
    
    private Connection Connection;

    /**
     * @param type
     * @throws IOException
     * @throws SQLException
     */
    public MySql(BdType type) throws IOException, SQLException {
        this.Connection = createConnection(type);
    }

    /**
     * @param type
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public synchronized static Connection createConnection(BdType type) throws SQLException, IOException {
        String confFile = MySql.class.getResource("Properties").getFile() + File.separator;
        switch (type) {
            case MySql:
                confFile += "mysql.properties";
                break;
            case Oracle:
                confFile += "oracle.properties";
                break;
            default:
                System.exit(-1);
        }
        Properties p = new Properties();
        p.load(new FileReader(new File(confFile)));
        String url = p.getProperty("url");
        String user = p.getProperty("user");
        String passwd = p.getProperty("password");
        try {
            Class.forName(p.getProperty("driver")).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, passwd);
    }
    
        public synchronized int InsertAnalyse(int refPatient, String item, String valeur) throws SQLException {
        PreparedStatement ps = Connection.prepareStatement("Update Analyses set item =  ?, valeur =  ? where RefPatient like ?");
        ps.setString(2, item);
        ps.setString(3, valeur);
        ps.setInt(1, refPatient);
        return ps.executeUpdate();
}
}
