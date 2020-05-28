package nl.han.oose.dea.datasource.dao;

import nl.han.oose.dea.controller.dto.TrackDTO;
import nl.han.oose.dea.controller.dto.TracksDTO;
import nl.han.oose.dea.controller.exceptions.InternalServerErrorException;
import nl.han.oose.dea.datasource.connection.DatabaseConnection;
import nl.han.oose.dea.datasource.datamapper.TracksDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.List;

@Component
@Service
public class TrackDAO {
    private DatabaseConnection databaseConnection;
    private TracksDataMapper tracksDataMapper;
    Connection connection;

    @Autowired
    public void setDatabaseConnection(DatabaseConnection databaseConnection) throws SQLException {
        this.databaseConnection = databaseConnection;
        makeConnection();
    }

    @Autowired
    private void setTracksDataMapper(TracksDataMapper tracksDataMapper) {
        this.tracksDataMapper = tracksDataMapper;
    }

    private void makeConnection() throws SQLException {
        connection = databaseConnection.getConnection();
    }

    public TrackDAO() {
    }

    public List<TrackDTO> getAllTracksNotInPlaylist(String token, int playlistId) throws InternalServerErrorException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from track where trackId not in (select trackId from tracks_in_playlist " +
                            "where playlistId  in (select playlistId from playlist where token = ? and playlistId =?))");
            statement.setString(1, token);
            statement.setInt(2, playlistId);
            ResultSet resultSet = statement.executeQuery();
            return tracksDataMapper.mapResultSetToDTO(resultSet);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }

    public TracksDTO getTracksDTO(String token, int playlistId) throws InternalServerErrorException {
        return new TracksDTO(getAllTracksNotInPlaylist(token,playlistId));
    }

}
