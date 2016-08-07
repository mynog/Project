package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.exceptions.TrackIsNotExistsException;
import com.victorku.musiccloud.model.Track;
import com.victorku.musiccloud.service.TrackService;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.TrackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTrack(@PathVariable("id") Long trackId){
        Track track = trackService.getTrackById(trackId);
        if (track == null) {
            return new ResponseEntity<>(new ErrorResponseBody(1,"Track ID is not found in DB"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convert(track),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTrack(@PathVariable("id") Long trackId) throws TrackIsNotExistsException {
        trackService.deleteTrackById(trackId);
    }

    private TrackDTO convert(Track dbModel){
        TrackDTO jsonModel = new TrackDTO(dbModel.getId(),dbModel.getTitle(),dbModel.getArtist(),dbModel.getAlbum(),dbModel.getYear(),dbModel.getFilename(),dbModel.getDuration(),dbModel.getRating());
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
