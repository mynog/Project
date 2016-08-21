package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.*;
import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.model.Track;
import com.victorku.musiccloud.model.Tracklist;
import com.victorku.musiccloud.service.AccountInfoService;
import com.victorku.musiccloud.service.TrackService;
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
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private TrackService trackService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTracklist(@PathVariable("id") Long tracklistId){
        Tracklist tracklist = tracklistService.getTracklistById(tracklistId);
        if (tracklist == null) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACKLIST_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(tracklist),HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> createTracklist(@RequestParam("name") String name, @RequestParam("accountInfoId") Long accountInfoId) {
        AccountInfo accountInfo;
        if (accountInfoId == null) {
            accountInfo = null;
        } else {
            accountInfo = accountInfoService.getAccountInfoById(accountInfoId);
            if (accountInfo == null) {
                return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
            }
        }
        Tracklist tracklist = null;
        try {
            tracklist = tracklistService.createTracklist(name,accountInfo);
        } catch (TracklistHasExistsException tracklistHasExists) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACKLIST_HAS_EXISTS);
        }
        return new ResponseEntity<>(convert(tracklist), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/track", method = RequestMethod.PUT)
    public ResponseEntity<?> addTrackIntoTracklist(@PathVariable("id") Long tracklistId, @RequestParam("trackId") Long trackId) {

        Tracklist tracklist = tracklistService.getTracklistById(tracklistId);
        if (tracklist == null) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACKLIST_ID_NOT_FOUND);
        }
        Track track = trackService.getTrackById(trackId);
        if (track == null) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_ID_NOT_FOUND);
        }
        tracklist = tracklistService.addTrackIntoTracklist(tracklist,track);
        return new ResponseEntity<>(convert(tracklist),HttpStatus.OK);
    }

    private TracklistDTO convert(Tracklist dbModel){
        return (dbModel == null) ? null : new TracklistDTO(dbModel);
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }

}
