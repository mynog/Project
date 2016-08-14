package com.victorku.musiccloud.service.impl;

import com.mpatric.mp3agic.*;
import com.victorku.musiccloud.exceptions.FileIsNotExistsException;
import com.victorku.musiccloud.exceptions.TrackHasExistsExceptions;
import com.victorku.musiccloud.exceptions.TrackIsNotExistsException;
import com.victorku.musiccloud.model.Track;
import com.victorku.musiccloud.repository.TrackRepository;
import com.victorku.musiccloud.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Override
    public Track getTrackById(Long id) {
        return trackRepository.findOne(id);
    }

    @Override
    public void deleteTrackById(Long id) throws TrackIsNotExistsException {
        if(!trackRepository.exists(id)){
            throw new TrackIsNotExistsException();
        }
        trackRepository.delete(id);
    }

    @Override
    public Track createTrack(String filename) throws TrackHasExistsExceptions, InvalidDataException, IOException, UnsupportedTagException, FileIsNotExistsException {
        // Создаем mp3 файл
        Mp3File mp3File = null;
        try {
            mp3File = new Mp3File("/home/kyluginvv/Project/Download/" + filename);
        }catch (FileNotFoundException fileNotFound){
            throw new FileIsNotExistsException();
        }
        // Проверяем существование файла в базе
        Track track = trackRepository.findByFilename(filename);
        if (track != null) {
            throw new TrackHasExistsExceptions();
        }
        // Создаем переменные для сохранения параметров файла
        String title = "";
        String artist = "";
        String album = "";
        String duration;
        Integer year = 0;
        // Парсинг mp3 файла
        // Получаем продолжительность файла в секундах
        duration = mp3File.getLengthInSeconds() + " cекунд";
        // Сканируем теги
        // Версия данных ID3v1
        if (mp3File.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3File.getId3v1Tag();
            title = id3v1Tag.getTitle();
            artist = id3v1Tag.getArtist();
            album = id3v1Tag.getAlbum();
            if(id3v1Tag.getYear()==null){
                year = 0;
            } else {
                year = Integer.parseInt(id3v1Tag.getYear());
            }
        }
        // Версия данных ID3v2
        if (mp3File.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3File.getId3v2Tag();
            title = id3v2Tag.getTitle();
            artist = id3v2Tag.getArtist();
            album = id3v2Tag.getAlbum();
            if(id3v2Tag.getYear()==null){
                year = 0;
            } else {
            year = Integer.parseInt(id3v2Tag.getYear());
            }
        }
        track = new Track(title,artist,album,year,filename,duration,null);
        return trackRepository.save(track);
    }

}
