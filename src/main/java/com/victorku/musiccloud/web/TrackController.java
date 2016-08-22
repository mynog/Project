package com.victorku.musiccloud.web;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.victorku.musiccloud.exceptions.*;
import com.victorku.musiccloud.model.*;
import com.victorku.musiccloud.service.*;
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
import java.util.UUID;

@RestController
@RequestMapping("/track")
@MultipartConfig(fileSizeThreshold = 20971520) // Максимальный размер файла 20mb
public class TrackController {

    @Autowired
    private TrackService trackService;
    @Autowired
    private MoodService moodService;
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private MoreTrackInfoService moreTrackInfoService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private CommentsService commentsService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFileRef) throws TracklistIsNotExistsException, TrackIsNotExistsException, GenreHasExistsException, GenreIsNotExistsException, FileIOException {
        // Получаем имя загруженного файла
        String fileName = uploadedFileRef.getOriginalFilename();
        // Генерируем уникальное имя файла
        UUID uuid = UUID.randomUUID();
        fileName = uuid.toString();
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
        } catch (IOException iO) {
            throw new FileIOException();
        }finally{
            try {
                reader.close();
                writer.close();
            } catch (IOException iO) {
                throw new FileIOException();
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
        } catch (FileIsNotExistsException FileIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.FILE_NOT_FOUND);
        } catch (InvalidDataException invalidData) {
            return getErrorResponseBody(ApplicationErrorTypes.INVALID_DATA);
        } catch (IOException iO) {
            getErrorResponseBody(ApplicationErrorTypes.IO_ERROR);
        } catch (UnsupportedTagException unsupportedTag) {
            getErrorResponseBody(ApplicationErrorTypes.UNSOPPORTED_TAG);
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

    @RequestMapping(value = "{id}/mood", method = RequestMethod.PUT)
    public ResponseEntity<?> addTrackMood(@PathVariable("id") Long trackId, @RequestParam("moodId") Long moodId, @RequestParam("accountInfoId") Long accountInfoId) {

        Track track = trackService.getTrackById(trackId);
        if (track == null) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_ID_NOT_FOUND);
        }
        Mood mood = moodService.getMoodById(moodId);
        if (mood == null) {
            return getErrorResponseBody(ApplicationErrorTypes.MOOD_ID_NOT_FOUND);
        }
        AccountInfo accountInfo = accountInfoService.getAccountInfoById(accountInfoId);
        if (accountInfo == null) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        track = trackService.addTrackMood(track,mood,accountInfo);
        return new ResponseEntity<>(convert(track),HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/more_track_info", method = RequestMethod.PUT)
    public ResponseEntity<?> addMoreTrackInfo(@PathVariable("id") Long trackId, @RequestParam("text") String text, @RequestParam("accountInfoId") Long accountInfoId) {

        Track track = trackService.getTrackById(trackId);
        if (track == null) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_ID_NOT_FOUND);
        }
        AccountInfo accountInfo = accountInfoService.getAccountInfoById(accountInfoId);
        if (accountInfo == null) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        track = trackService.addMoreTrackInfo(track,text,accountInfo);
        return new ResponseEntity<>(convert(track),HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/rating", method = RequestMethod.PUT)
    public ResponseEntity<?> addTrackRating(@PathVariable("id") Long trackId, @RequestParam("ratingValue") Integer ratingValue, @RequestParam("accountInfoId") Long accountInfoId) {

        Track track = trackService.getTrackById(trackId);
        if (track == null) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_ID_NOT_FOUND);
        }
        AccountInfo accountInfo = accountInfoService.getAccountInfoById(accountInfoId);
        if (accountInfo == null) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        track = trackService.addRating(track,ratingValue,accountInfo);
        return new ResponseEntity<>(convert(track),HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/comments", method = RequestMethod.PUT)
    public ResponseEntity<?> addComment(@PathVariable("id") Long trackId, @RequestParam("text") String text,
                                         @RequestParam("orderComments") Integer orderComments, @RequestParam("parentId") Long parentId,
                                         @RequestParam("accountInfoId") Long accountInfoId) {

        Track track = trackService.getTrackById(trackId);
        if (track == null) {
            return getErrorResponseBody(ApplicationErrorTypes.TRACK_ID_NOT_FOUND);
        }
        AccountInfo accountInfo = accountInfoService.getAccountInfoById(accountInfoId);
        if (accountInfo == null) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        Comments parentCommnent;
        if (parentId == null) {
            parentCommnent = null;
        } else {
            parentCommnent = commentsService.getCommentsById(parentId);
            if (parentCommnent == null) {
                return getErrorResponseBody(ApplicationErrorTypes.COMMENT_ID_NOT_FOUND);
            }
        }
        track = trackService.addComment(track,text,orderComments,parentCommnent,accountInfo);
        return new ResponseEntity<>(convert(track),HttpStatus.OK);
    }

    @RequestMapping(value = "/more_track_info/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMoreTrackInfo(@PathVariable("id") Long moreTrackInfoId){
        MoreTrackInfo moreTrackInfo = moreTrackInfoService.getMoreTrackInfoById(moreTrackInfoId);
        if (moreTrackInfo == null) {
            return getErrorResponseBody(ApplicationErrorTypes.MORE_TRACK_INFO_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(moreTrackInfo),HttpStatus.OK);
    }

    @RequestMapping(value = "/rating/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRating(@PathVariable("id") Long ratingId){
        Rating rating = ratingService.getRatingById(ratingId);
        if (rating == null) {
            return getErrorResponseBody(ApplicationErrorTypes.RATING_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(rating), HttpStatus.OK);
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getComments(@PathVariable("id") Long commentsId){
        Comments comments = commentsService.getCommentsById(commentsId);
        if (comments == null) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(comments),HttpStatus.OK);
    }

    private MoreTrackInfoDTO convert(MoreTrackInfo dbModel){ return (dbModel == null) ? null : new MoreTrackInfoDTO(dbModel); }

    private RatingDTO convert(Rating dbModel){
        return (dbModel == null) ? null : new RatingDTO(dbModel);
    }

    private CommentsDTO convert(Comments dbModel){ return (dbModel == null) ? null : new CommentsDTO(dbModel); }
    
    private TrackDTO convert(Track dbModel){
        return (dbModel == null) ? null : new TrackDTO(dbModel);
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }

}
