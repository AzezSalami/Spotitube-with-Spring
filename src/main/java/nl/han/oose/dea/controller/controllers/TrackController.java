package nl.han.oose.dea.controller.controllers;

import nl.han.oose.dea.controller.dto.TrackDTO;
import nl.han.oose.dea.controller.dto.TracksDTO;
import nl.han.oose.dea.datasource.dao.TrackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.inject.Inject;


import javax.ws.rs.core.Response;

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

    @RequestMapping(method = RequestMethod.GET, value = "" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TracksDTO> getAllTracksNotInPlaylist(@RequestParam("forPlaylist") int forPlaylist, @RequestParam("token") String token){
        return ResponseEntity.ok().body(trackDAO.getTracksDTO(token,forPlaylist));
    }


}
