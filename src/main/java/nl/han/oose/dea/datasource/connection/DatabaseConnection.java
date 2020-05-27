package nl.han.oose.dea.datasource.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Service
public class DatabaseConnection {
    private DatabaseProperties databaseProperties;

    public DatabaseConnection() throws SQLException {
    }

    @Autowired
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseProperties.connectionString());
    }

}
