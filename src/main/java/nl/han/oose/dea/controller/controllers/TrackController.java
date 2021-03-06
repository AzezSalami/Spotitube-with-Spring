package nl.han.oose.dea.controller.controllers;

import nl.han.oose.dea.controller.dto.TracksDTO;
import nl.han.oose.dea.datasource.dao.TrackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@Service
@RequestMapping("/tracks")
public class TrackController {

    private TrackDAO trackDAO ;

    public TrackController() {
    }

    @Autowired
    public void setTrackDAO(TrackDAO trackDAO){
        this.trackDAO = trackDAO;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "" , produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<TracksDTO> getAllTracksNotInPlaylist(@RequestParam("forPlaylist") int forPlaylist, @RequestParam("token") String token){
        return ResponseEntity.ok().body(trackDAO.getTracksDTO(token,forPlaylist));
    }


}
