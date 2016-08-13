package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.*;
import com.victorku.musiccloud.model.MoreTrackInfo;
import com.victorku.musiccloud.model.Track;
import com.victorku.musiccloud.service.MoreTrackInfoService;
import com.victorku.musiccloud.service.TrackService;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.MoreTrackInfoDTO;
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

    @Autowired
    private MoreTrackInfoService moreTrackInfoService;


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
        try {
            track = trackService.createTrack(title, artist, album, year, filename, duration, rating);
        }
        catch (TrackHasExistsExceptions trackHasExists){
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_HAS_EXISTS);
        }
        return new ResponseEntity<>(convert(track), HttpStatus.OK);
    }

    @RequestMapping(value = "/more_track_info/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMoreTrackInfo(@PathVariable("id") Long moreTrackInfoId){
        MoreTrackInfo moreTrackInfo = moreTrackInfoService.getMoreTrackInfoById(moreTrackInfoId);
        if (moreTrackInfo == null) {
            return getErrorResponseBody(ApplicationErrorTypes.MORE_TRACK_INFO_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(moreTrackInfo),HttpStatus.OK);
    }

    @RequestMapping(value = "/more_track_info/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMoreTrackInfo(@PathVariable("id") Long moreTrackInfoId) throws MoreTrackInfoIsNotExistsException {
        try {
            moreTrackInfoService.deleteMoreTrackInfoById(moreTrackInfoId);
        }catch (MoreTrackInfoIsNotExistsException moreTrackInfoIsNotExists){
            return getErrorResponseBody(ApplicationErrorTypes.MORE_TRACK_INFO_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/more_track_info/", method = RequestMethod.PUT)
    public ResponseEntity<?> createMoreTrackInfo(@RequestParam("text") String text) {
        MoreTrackInfo moreTrackInfo = null;
        try {
            moreTrackInfo = moreTrackInfoService.createMoreTrackInfo(text);
        } catch (MoreTrackInfoHasExistsException moreTrackInfoHasExists) {
            return getErrorResponseBody(ApplicationErrorTypes.MORE_TRACK_INFO_HAS_EXISTS);
        }
        return new ResponseEntity<>(convert(moreTrackInfo), HttpStatus.OK);
    }

    private MoreTrackInfoDTO convert(MoreTrackInfo dbModel){
        MoreTrackInfoDTO jsonModel = new MoreTrackInfoDTO(dbModel.getId(),dbModel.getText());
        return jsonModel;
    }

    private TrackDTO convert(Track dbModel){
        TrackDTO jsonModel = new TrackDTO(dbModel.getId(),dbModel.getTitle(),dbModel.getArtist(),dbModel.getAlbum(),dbModel.getYear(),dbModel.getFilename(),dbModel.getDuration(),dbModel.getRating());
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }

}
