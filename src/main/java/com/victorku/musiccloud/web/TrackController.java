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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTrack(@PathVariable("id") Long trackId){
        Track track = trackService.getTrackById(trackId);
        if (track == null) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(track),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTrack(@PathVariable("id") Long trackId) throws TrackIsNotExistsException {
        try {
            trackService.deleteTrackById(trackId);
        }catch (TrackIsNotExistsException trackIsNotExists){
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> createTrack(@RequestParam("title") String title, @RequestParam("artist") String artist,
                                         @RequestParam("album") String album, @RequestParam("year") Integer year,
                                         @RequestParam("filename") String filename, @RequestParam("duration") String duration,
                                         @RequestParam("rating") Double rating) {
        Track track = null;
        track = trackService.createTrack(title, artist, album, year, filename, duration, rating);
        return new ResponseEntity<>(convert(track), HttpStatus.OK);
    }

    private TrackDTO convert(Track dbModel){
        TrackDTO jsonModel = new TrackDTO(dbModel.getId(),dbModel.getTitle(),dbModel.getArtist(),dbModel.getAlbum(),dbModel.getYear(),dbModel.getFilename(),dbModel.getDuration(),dbModel.getRating());
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
