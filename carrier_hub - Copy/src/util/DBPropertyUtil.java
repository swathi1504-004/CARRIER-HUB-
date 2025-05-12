package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getConnectionString(String filename) {
        Properties properties = new Properties();

        try {
            File file = new File(filename);
            System.out.println("🔍 Looking for file: " + file.getAbsolutePath());
            System.out.println("📦 File exists? " + file.exists());

            FileInputStream input = new FileInputStream(file);
            properties.load(input);

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            if (url == null || username == null || password == null) {
                System.out.println("❌ One or more properties are missing in the file.");
                return null;
            }

            System.out.println("✅ Properties loaded: " + url + ", " + username);
            return url + "," + username + "," + password;

        } catch (IOException e) {
            System.out.println("❌ Failed to load properties file: " + e.getMessage());
            return null;
        }
    }
}
