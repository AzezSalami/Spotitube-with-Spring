package nl.han.oose.dea.datasource.datamapper;

import nl.han.oose.dea.controller.dto.LoginDTO;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LoginDataMapper  implements DataMapper<LoginDTO>{

    public LoginDTO mapResultSetToDTO(ResultSet resultSet) throws SQLException {
        LoginDTO loginDTO = new LoginDTO();

        while (resultSet.next()) {
            loginDTO = new LoginDTO(
                    resultSet.getString("username"),
                    resultSet.getString("password")
            );
        }
        return loginDTO;
    }
}
