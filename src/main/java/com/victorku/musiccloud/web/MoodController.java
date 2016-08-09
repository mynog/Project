package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.exceptions.MoodIsNotExistsException;
import com.victorku.musiccloud.model.Mood;
import com.victorku.musiccloud.service.MoodService;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.MoodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mood")
public class MoodController {

    @Autowired
    private MoodService moodService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMood(@PathVariable("id") Long moodId){
        Mood mood = moodService.getMoodById(moodId);
        if (mood == null) {
            return getErrorResponseBody(ApplicationErrorTypes.MOOD_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(mood),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMood(@PathVariable("id") Long moodId) throws MoodIsNotExistsException {
        try {
            moodService.deleteMoodById(moodId);
        }catch (MoodIsNotExistsException moodIsNotExists){
            return getErrorResponseBody(ApplicationErrorTypes.MOOD_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    private MoodDTO convert(Mood dbModel){
        MoodDTO jsonModel = new MoodDTO(dbModel.getId(),dbModel.getName());
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
