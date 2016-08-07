package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.MoreTrackInfoIsNotExistsException;
import com.victorku.musiccloud.model.MoreTrackInfo;
import com.victorku.musiccloud.service.MoreTrackInfoService;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.MoreTrackInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// todo 2VK: давай объединим с TrackController'ом
@RestController
@RequestMapping("/more_track_info")
public class MoreTrackInfoController {

    @Autowired
    MoreTrackInfoService moreTrackInfoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMoreTrackInfo(@PathVariable("id") Long moreTrackInfoId){
        MoreTrackInfo moreTrackInfo = moreTrackInfoService.getMoreTrackInfoById(moreTrackInfoId);
        if (moreTrackInfo == null) {
            return new ResponseEntity<>(new ErrorResponseBody(1,"MoreTrackInfo ID is not found in DB"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convert(moreTrackInfo),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMoreTrackInfo(@PathVariable("id") Long moreTrackInfoId) throws MoreTrackInfoIsNotExistsException {
        moreTrackInfoService.deleteMoreTrackInfoById(moreTrackInfoId);
    }

    private MoreTrackInfoDTO convert(MoreTrackInfo dbModel){
        MoreTrackInfoDTO jsonModel = new MoreTrackInfoDTO(dbModel.getId(),dbModel.getText());
        return jsonModel;
    }
}
