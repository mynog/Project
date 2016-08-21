package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.*;
import com.victorku.musiccloud.model.Tracklist;
import com.victorku.musiccloud.service.TracklistService;
import com.victorku.musiccloud.web.model.DateDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.TracklistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tracklist")
public class TracklistController {

    @Autowired
    private TracklistService tracklistService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTracklist(@PathVariable("id") Long tracklistId){
        Tracklist tracklist = tracklistService.getTracklistById(tracklistId);
        if (tracklist == null) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACKLIST_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(tracklist),HttpStatus.OK);
    }

    private TracklistDTO convert(Tracklist dbModel){
        return (dbModel == null) ? null : new TracklistDTO(dbModel);
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }

}
