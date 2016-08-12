package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.exceptions.MoreTrackInfoHasExistsException;
import com.victorku.musiccloud.exceptions.MoreTrackInfoIsNotExistsException;
import com.victorku.musiccloud.model.MoreTrackInfo;
import com.victorku.musiccloud.service.MoreTrackInfoService;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.MoreTrackInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// todo 2VK: давай объединим с TrackController'ом
@RestController
@RequestMapping("/more_track_info")
public class MoreTrackInfoController {

    @Autowired
    private MoreTrackInfoService moreTrackInfoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMoreTrackInfo(@PathVariable("id") Long moreTrackInfoId){
        MoreTrackInfo moreTrackInfo = moreTrackInfoService.getMoreTrackInfoById(moreTrackInfoId);
        if (moreTrackInfo == null) {
            return getErrorResponseBody(ApplicationErrorTypes.MORE_TRACK_INFO_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(moreTrackInfo),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMoreTrackInfo(@PathVariable("id") Long moreTrackInfoId) throws MoreTrackInfoIsNotExistsException {
        try {
            moreTrackInfoService.deleteMoreTrackInfoById(moreTrackInfoId);
        }catch (MoreTrackInfoIsNotExistsException moreTrackInfoIsNotExists){
            return getErrorResponseBody(ApplicationErrorTypes.MORE_TRACK_INFO_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
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

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
