package ca.murcia.dev.pattison;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {

    private String databaseDriver;
    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;
    private String databaseMaxPool;

    private Connection connection;
    private Properties properties;

    public MySQLConnection() throws BookException {
        Properties applicationProperties = readApplicationProperties();
        this.databaseDriver = applicationProperties.getProperty( "database.driver" );
        this.databaseUrl = applicationProperties.getProperty( "database.url" );
        this.databaseUsername = applicationProperties.getProperty( "database.username" );
        this.databasePassword = applicationProperties.getProperty( "database.password" );
        this.databaseMaxPool = applicationProperties.getProperty( "database.max-pool" );
    }

    private Properties readApplicationProperties() throws BookException {
        Properties applicationProperties = new Properties();
        try {
            InputStream is = MySQLConnection.class.getResourceAsStream("/application.properties");
            applicationProperties.load(is);
        } catch ( IOException e ) {
            throw new BookException( "Book Properties Exception", "Could Not Read From application.properties", e );
        }
        return applicationProperties;
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", databaseUsername);
            properties.setProperty("password", databasePassword);
            properties.setProperty("MaxPooledStatements", databaseMaxPool);
        }
        return properties;
    }

    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(databaseDriver);
                connection = DriverManager.getConnection(databaseUrl, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}