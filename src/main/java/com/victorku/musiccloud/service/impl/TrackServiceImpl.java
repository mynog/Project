package com.victorku.musiccloud.service.impl;

import com.mpatric.mp3agic.*;
import com.victorku.musiccloud.exceptions.*;
import com.victorku.musiccloud.model.*;
import com.victorku.musiccloud.repository.*;
import com.victorku.musiccloud.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private TrackHasMoodRepository trackHasMoodRepository;
    @Autowired
    private MoreTrackInfoRepository moreTrackInfoRepository;
    @Autowired
    private GenreService genreService;


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
    public Track createTrack(String filename) throws TrackHasExistsExceptions, InvalidDataException, IOException,
                                                     UnsupportedTagException, FileIsNotExistsException, TracklistIsNotExistsException,
                                                     TrackIsNotExistsException, GenreHasExistsException, GenreIsNotExistsException {
            return parsingMp3File(filename);
        }

    @Override
    public Track updateTrack(Long trackId, String title, String album, String artist, Integer year, String filename, String duration) throws TrackIsNotExistsException {
        Track track = trackRepository.findOne(trackId);
        if (track == null) {
            throw new TrackIsNotExistsException();
        }
        if (title != null) {
            track.setTitle(title);
        }
        if (artist != null) {
            track.setArtist(artist);
        }
        if (album != null) {
            track.setAlbum(album);
        }
        if (year != null) {
            track.setYear(year);
        }
        if (filename != null) {
            track.setFilename(filename);
        }
        if (duration != null) {
            track.setDuration(duration);
        }
        return trackRepository.save(track);
    }

    @Override
    public Track addTrackGenre(Long trackId, Long genreId) throws TrackIsNotExistsException, GenreIsNotExistsException {
        Track track = getTrackById(trackId);
        if (track == null) {
            throw new TrackIsNotExistsException();
        }
        Genre genre = genreService.getGenreById(genreId);
        if (genre == null) {
            throw new GenreIsNotExistsException();
        }
        Set<Genre> genres = track.getGenres();
        genres.add(genre);
        track.setGenres(genres);
        return trackRepository.save(track);
    }

    @Override
    public Track removeTrackGenre(Long trackId, Long genreId) throws TrackIsNotExistsException, GenreIsNotExistsException, TrackHasNotGenreException {
        Track track = getTrackById(trackId);
        if (track == null) {
            throw new TrackIsNotExistsException();
        }
        Genre genre = genreService.getGenreById(genreId);
        if (genre == null) {
            throw new GenreIsNotExistsException();
        }
        Set<Genre> genres = track.getGenres();
        if(genres.contains(genre)) {
            genres.remove(genre);
        } else {
            throw new TrackHasNotGenreException();
        }
        track.setGenres(genres);
        return trackRepository.save(track);
    }

    @Override
    public Track addTrackMood(Track track, Mood mood, AccountInfo accountInfo) {
        TrackHasMood trackHasMood = new TrackHasMood(track,mood,accountInfo);
        trackHasMoodRepository.save(trackHasMood);
        return track;
    }

    @Override
    public Track addMoreTrackInfo(Track track, String text, AccountInfo accountInfo) {
        MoreTrackInfo moreTrackInfo = new MoreTrackInfo(track,text,accountInfo);
        moreTrackInfoRepository.save(moreTrackInfo);
        return track;
    }

    private Track parsingMp3File(String filename) throws InvalidDataException, IOException, UnsupportedTagException, FileIsNotExistsException, TrackHasExistsExceptions, TracklistIsNotExistsException, TrackIsNotExistsException {
        // Создаем mp3 файл
        Mp3File mp3File = null;
        try {
            mp3File = new Mp3File("/home/kyluginvv/Project/Download/" + filename);
        } catch (FileNotFoundException fileNotFound) {
            throw new FileIsNotExistsException();
        }
        // Проверяем существование файла в базе
        Track track = trackRepository.findByFilename(filename);
        if (track != null) {
            throw new TrackHasExistsExceptions();
        }
        // Создаем переменные для сохранения параметров файла и создания плейлистов "на лету"
        String title = "";
        String artist = "";
        String album = "";
        String duration;
        Integer year = 0;
        String genre = "";
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
            if (id3v1Tag.getYear() == null) {
                year = 0;
            } else {
                year = Integer.parseInt(id3v1Tag.getYear());
            }
            genre = id3v1Tag.getGenreDescription();
        }
        // Версия данных ID3v2
        if (mp3File.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3File.getId3v2Tag();
            title = id3v2Tag.getTitle();
            artist = id3v2Tag.getArtist();
            album = id3v2Tag.getAlbum();
            if (id3v2Tag.getYear() == null) {
                year = 0;
            } else {
                year = Integer.parseInt(id3v2Tag.getYear());
            }
            genre = id3v2Tag.getGenreDescription();
        }
        // Создаем и сохраняем готовый трэк
        track = new Track(title, artist, album, year, filename, duration, null);
        trackRepository.save(track);
        // Автоматически добавляем жанр к треку
        Genre trackgenre = null;
        if (genre != null) {
            try {
                trackgenre = genreService.createGenre(genre);
                Set<Genre> genres = new HashSet<>();
                genres.add(trackgenre);
                track.setGenres(genres);
                trackRepository.save(track);

            } catch (GenreHasExistsException genreHasExists) {
                trackgenre = genreService.getGenreByName(genre);
                Set<Genre> genres = new HashSet<>();
                genres.add(trackgenre);
                track.setGenres(genres);
                trackRepository.save(track);
            }
        }
        // Создаем автоматические плеэйлисты
        Tracklist tracklist = null;
        // По жанру
        // По альбому
        return track;
    }
}

