package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.exceptions.TracklistIsNotExistsException;
import com.victorku.musiccloud.model.Tracklist;
import com.victorku.musiccloud.service.TracklistService;
import com.victorku.musiccloud.web.model.DateDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.TracklistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTracklist(@PathVariable("id") Long tracklistId) throws TracklistIsNotExistsException {
        try {
            tracklistService.deleteTracklistById(tracklistId);
        }catch (TracklistIsNotExistsException tracklistIsNotExists){
            return getErrorResponseBody(ApplicationErrorTypes.TRACKLIST_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    private TracklistDTO convert(Tracklist dbModel){
        TracklistDTO jsonModel = new TracklistDTO(dbModel.getId(),dbModel.getName(),new DateDTO(dbModel.getDateCreate()));
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
