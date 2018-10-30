package lib;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyValuesGetter {

    private String user;
    private String password;

    public PropertyValuesGetter() {
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            user = prop.getProperty("user");
            password = prop.getProperty("password");
        }
        catch (Exception ex) {
            Logger.getLogger(PropertyValuesGetter.class.getName()).log(Level.SEVERE, "Exception:", ex);
        }
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
