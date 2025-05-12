package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnUtil {

    public static Connection getConnectionFromClasspath() {
        try {
            Properties props = new Properties();

            // ✅ Load db.properties from the classpath
            InputStream input = DBConnUtil.class.getClassLoader().getResourceAsStream("db.properties");

            // ✅ Debug: Check if file was found
            System.out.println("InputStream is null? " + (input == null));

            if (input == null) {
                System.err.println("❌ Could not find db.properties in classpath.");
                return null;
            }

            props.load(input);

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
