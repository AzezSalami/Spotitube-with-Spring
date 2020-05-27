package nl.han.oose.dea.controller.controllers;

import nl.han.oose.dea.controller.dto.PlaylistsDTO;
import nl.han.oose.dea.controller.dto.TrackDTO;
import nl.han.oose.dea.controller.dto.TracksDTO;
import nl.han.oose.dea.datasource.dao.PlaylistDAO;
import nl.han.oose.dea.controller.dto.PlaylistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.inject.Inject;

@RestController
@Service
@RequestMapping("/playlists")
public class PlaylistController {

    private PlaylistDAO playlistDAO;

    public PlaylistController() {
    }

    @Autowired
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PlaylistsDTO> acquirePlaylists(@RequestParam("token") String token) {
        return ResponseEntity.ok().body(playlistDAO.getPlaylistsDTO(token));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public @ResponseBody ResponseEntity<PlaylistsDTO> deletePlaylist(@RequestParam("token") String token, @PathVariable int id) {
        playlistDAO.deletePlaylists(token, id);
        return ResponseEntity.ok().body(playlistDAO.getPlaylistsDTO(token));
    }


    @RequestMapping(method = RequestMethod.POST, value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PlaylistsDTO> addPlaylist(@RequestParam("token") String token,@RequestBody PlaylistDTO playlistDTO) {
        playlistDAO.addPlaylists(token, playlistDTO);
        return ResponseEntity.ok().body(playlistDAO.getPlaylistsDTO(token));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PlaylistsDTO> editPlaylist(@RequestParam("token") String token, @PathVariable("id") int id,@RequestBody PlaylistDTO playlistDTO) {
        playlistDAO.editPlaylists(token, id, playlistDTO);
        return ResponseEntity.ok().body(playlistDAO.getPlaylistsDTO(token));
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}/tracks", produces = MediaType.APPLICATION_JSON_VALUE)

    public @ResponseBody ResponseEntity<TracksDTO> getAllTracksInPlaylist(@RequestParam("token") String token, @PathVariable("id") int id) {
        return ResponseEntity.ok().body(playlistDAO.getTracksDTO(token, id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{playlistId}/tracks/{trackId}", produces = MediaType.APPLICATION_JSON_VALUE)

    public @ResponseBody ResponseEntity<TracksDTO> deleteTrackFromPlaylist(@RequestParam("token") String token, @PathVariable("playlistId") int playlistId, @PathVariable("trackId") int trackId) {
        playlistDAO.deleteTrackFromPlaylist(token, playlistId, trackId);
        return ResponseEntity.ok().body(playlistDAO.getTracksDTO(token, playlistId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/tracks", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<TracksDTO> addTrackToPlaylist(@RequestParam("token") String token, @PathVariable("id") int playlistId,@RequestBody TrackDTO trackDTO) {
        playlistDAO.addTrackToPlaylist(token, playlistId, trackDTO);
        return ResponseEntity.ok().body(playlistDAO.getTracksDTO(token, playlistId));
    }

}
