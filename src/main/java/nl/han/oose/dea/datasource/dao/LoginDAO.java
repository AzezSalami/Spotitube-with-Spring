package nl.han.oose.dea.datasource.dao;

import nl.han.oose.dea.controller.dto.LoginRespondeDTO;
import nl.han.oose.dea.datasource.connection.DatabaseConnection;

import java.sql.*;

import nl.han.oose.dea.controller.dto.LoginDTO;
import nl.han.oose.dea.datasource.datamapper.LoginDataMapper;
import nl.han.oose.dea.datasource.datamapper.UserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;

@Component
@Service
@ComponentScan({"nl.han.oose.dea.datasource.datamapper"})
@ComponentScan({"nl.han.oose.dea.datasource.connection"})
public class LoginDAO {

    private DatabaseConnection databaseConnection;
    private LoginDataMapper loginDataMapper;
    private UserDataMapper userDataMapper;
    Connection connection;

    @Autowired
    public void setLoginDataMapper(LoginDataMapper loginDataMapper) {
        this.loginDataMapper = loginDataMapper;
    }

    @Autowired
    public void setUserDataMapper(UserDataMapper userDataMapper) {
        this.userDataMapper = userDataMapper;
    }

    @Autowired
    public void setDatabaseConnection(DatabaseConnection databaseConnection) throws SQLException {
        this.databaseConnection = databaseConnection;
     //   makeConnection();
    }

//    private void makeConnection() throws SQLException {
//        connection = databaseConnection.getConnection();
//    }

    public LoginDAO() {
    }

    public LoginDTO findUser(String username) {
        try {
            connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from users where username =?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return loginDataMapper.mapResultSetToDTO(resultSet);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }

    public LoginRespondeDTO findData(String username) {
        try {
            connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from users where username =?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return userDataMapper.mapResultSetToDTO(resultSet);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }

    public LoginRespondeDTO validateInfo(LoginDTO loginDTO) {
        if (loginDTO.getPassword().equals(findUser(loginDTO.getUser()).getPassword())) {
            return findData(loginDTO.getUser());
        } else {
            throw new NotAuthorizedException("username or password is not correct");
        }
    }
}
