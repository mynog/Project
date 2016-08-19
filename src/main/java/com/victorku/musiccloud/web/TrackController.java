package com.victorku.musiccloud.web;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.victorku.musiccloud.exceptions.*;
import com.victorku.musiccloud.model.Comments;
import com.victorku.musiccloud.model.MoreTrackInfo;
import com.victorku.musiccloud.model.Rating;
import com.victorku.musiccloud.model.Track;
import com.victorku.musiccloud.service.CommentsService;
import com.victorku.musiccloud.service.MoreTrackInfoService;
import com.victorku.musiccloud.service.RatingService;
import com.victorku.musiccloud.service.TrackService;
import com.victorku.musiccloud.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/track")
@MultipartConfig(fileSizeThreshold = 20971520) // Максимальный размер файла 20mb
public class TrackController {

    @Autowired
    private TrackService trackService;
/*    @Autowired
    private MoreTrackInfoService moreTrackInfoService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private CommentsService commentsService;*/

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFileRef) throws TracklistIsNotExistsException, TrackIsNotExistsException, GenreHasExistsException, GenreIsNotExistsException {
        // Получаем имя загруженного файла
        String fileName = uploadedFileRef.getOriginalFilename();
        // Путь, где загруженный файл будет сохранен.
        String path = "/home/kyluginvv/Project/Download/" + fileName;
        // Буффер для хранения данных из uploadedFileRef
        byte[] buffer = new byte[1000];
        // Теперь создаем выходной файл outputFile на сервере
        File outputFile = new File(path);

        FileInputStream reader = null;
        FileOutputStream writer = null;
        int totalBytes = 0;
        try {
            outputFile.createNewFile();
            // Создаем входной поток для чтения данных из него
            reader = (FileInputStream) uploadedFileRef.getInputStream();
            // Создаем выходной поток для записи данных
            writer = new FileOutputStream(outputFile);
            // Считываем данные uploadedFileRef и пишем их в outputFile
            int bytesRead = 0;
            while ((bytesRead = reader.read(buffer)) != -1) {
                writer.write(buffer);
                totalBytes += bytesRead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return createTrack(fileName);
    }

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

    private ResponseEntity<?> createTrack(String filename) throws TracklistIsNotExistsException, TrackIsNotExistsException, GenreHasExistsException, GenreIsNotExistsException {
        Track track = null;
        try {
            track = trackService.createTrack(filename);
        }
        catch (TrackHasExistsExceptions trackHasExists) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_HAS_EXISTS);
        } catch (FileIsNotExistsException e) {
            return getErrorResponseBody(ApplicationErrorTypes.FILE_NOT_FOUND);
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(convert(track), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateTrack(@RequestParam("id") Long trackId, @RequestBody TrackDTO trackInfo) {
        Track track = null;
        try {
            track = trackService.updateTrack(trackId, trackInfo.getTitle(), trackInfo.getArtist(), trackInfo.getAlbum(),
                                             trackInfo.getYear(), trackInfo.getFilename(), trackInfo.getDuration());
        } catch (TrackIsNotExistsException trackIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_ID_NOT_FOUND);
        }
        return new ResponseEntity<Object>(convert(track), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/genre", method = RequestMethod.PUT)
    public ResponseEntity<?> addTrackGenre(@PathVariable("id") Long trackId, @RequestParam("genreId") Long genreId) {
        Track track = null;
        try {
            track = trackService.addTrackGenre(trackId, genreId);
        } catch (TrackIsNotExistsException trackIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_ID_NOT_FOUND);
        } catch (GenreIsNotExistsException genreIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.GENRE_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(track), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/genre", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeTrackGenre(@PathVariable("id") Long trackId, @RequestParam("genreId") Long genreId) {
        Track track = null;
        try {
            track = trackService.removeTrackGenre(trackId, genreId);
        } catch (TrackIsNotExistsException trackIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_ID_NOT_FOUND);
        } catch (GenreIsNotExistsException genreIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.GENRE_ID_NOT_FOUND);
        } catch (TrackHasNotGenreException trackHasNotGenreException) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_HAS_NOT_GENRE);
        }
        return new ResponseEntity<>(convert(track), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/mood", method = RequestMethod.PUT)
    public ResponseEntity<?> addTrackMood(@PathVariable("id") Long trackId, @RequestParam("moodId") Long moodId, @RequestParam("accountInfoId") Long accountInfoId) {
        Track track = null;
        try {
            track = trackService.addTrackMood(trackId, moodId, accountInfoId);
        } catch (TrackIsNotExistsException trackIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_ID_NOT_FOUND);
        } catch (MoodIsNotExistsException moodIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.MOOD_ID_NOT_FOUND);
        } catch (AccountIsNotExistsException e) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(track), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/more_track_info", method = RequestMethod.PUT)
    public ResponseEntity<?> addMoreTrackInfo(@PathVariable("id") Long trackId,@RequestParam("text") String text, @RequestParam("accountInfoId") Long accountInfoId) {
        Track track = trackService.getTrackById(trackId);
        try {
            track = trackService.addMoreTrackInfo(track,text,accountInfoId);
        } catch (AccountIsNotExistsException accountIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        } catch (MoreTrackInfoHasExistsException moreTrackInfoHasExists) {
            return getErrorResponseBody(ApplicationErrorTypes.MORE_TRACK_INFO_HAS_EXISTS);
        }
        return new ResponseEntity<>(convert(track), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/rating", method = RequestMethod.PUT)
    public ResponseEntity<?> addTrackRating(@PathVariable("id") Long trackId,@RequestParam("ratingValue") Integer ratingValue, @RequestParam("accountInfoId") Long accountInfoId) {
        Track track = trackService.getTrackById(trackId);
        try {
            track = trackService.addTrackRating(track,ratingValue,accountInfoId);
        } catch (AccountIsNotExistsException accountIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(track), HttpStatus.OK);
    }

    // Контроллеры для получения и удаления доп.ины, переделать

/*        @RequestMapping(value = "/more_track_info/{id}", method = RequestMethod.GET)
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


    private MoreTrackInfoDTO convert(MoreTrackInfo dbModel){
        MoreTrackInfoDTO jsonModel = new MoreTrackInfoDTO(dbModel.getId(),dbModel.getText());
        return jsonModel;
    }*/

    // Контроллеры для получения и удаления рейтинга, переделать

/*    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRating(@PathVariable("id") Long ratingId){
        Rating rating = ratingService.getRatingById(ratingId);
        if (rating == null) {
            return getErrorResponseBody(ApplicationErrorTypes.RATING_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(rating), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRating(@PathVariable("id") Long ratingId) {
        try {
            ratingService.deleteRatingById(ratingId);
        }catch (RatingIsNotExistsException ratingIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.RATING_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    private RatingDTO convert(Rating dbModel){
        RatingDTO jsonModel = new RatingDTO(dbModel.getId(),dbModel.getRatingValue());
        return jsonModel;
    } */

    // Контроллеры для получения и удаления комментариев к треку, переделать

/*    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getComments(@PathVariable("id") Long commentsId){
        Comments comments = commentsService.getCommentsById(commentsId);
        if (comments == null) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(comments),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteComments(@PathVariable("id") Long commentsId) throws CommentsIsNotExistsException {
        try {
            commentsService.deleteCommentsById(commentsId);
        }catch (CommentsIsNotExistsException commentsIsNotExists){
            return getErrorResponseBody(ApplicationErrorTypes.COMMENT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    private CommentsDTO convert(Comments dbModel){
        CommentsDTO jsonModel = new CommentsDTO(dbModel.getId(),dbModel.getText(),dbModel.getOrderComments());
        return jsonModel;
    }*/
    
    private TrackDTO convert(Track dbModel){
        return (dbModel == null) ? null : new TrackDTO(dbModel);
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }

}
