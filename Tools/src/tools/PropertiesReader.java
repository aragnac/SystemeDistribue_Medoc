package tools;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nicolas
 */
public abstract class PropertiesReader {
    public static final String filename = "config.properties";
    private static final Properties p = new Properties();

    static {
        try {
            p.load(new FileInputStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static String getProperties(String propertiesName) {
        return p.getProperty(propertiesName);
    }

    public static Set<String> list() {
        return p.stringPropertyNames();
    }

    public static void Print(PrintStream ps) {
        p.list(ps);
    }
}
