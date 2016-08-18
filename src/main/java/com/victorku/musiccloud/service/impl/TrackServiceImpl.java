package com.victorku.musiccloud.service.impl;

import com.mpatric.mp3agic.*;
import com.victorku.musiccloud.exceptions.*;
import com.victorku.musiccloud.model.*;
import com.victorku.musiccloud.repository.GenreRepository;
import com.victorku.musiccloud.repository.TrackRepository;
import com.victorku.musiccloud.repository.TracklistRepository;
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
    private GenreService genreService;
    @Autowired
    private MoodService moodService;
    @Autowired
    private TracklistService tracklistService;
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private MoreTrackInfoService moreTrackInfoService;

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
    public Track addTrackMood(Long trackId, Long moodId, Long accountInfoId) throws TrackIsNotExistsException, MoodIsNotExistsException, AccountIsNotExistsException {
        Track track = getTrackById(trackId);
        if (track == null) {
            throw new TrackIsNotExistsException();
        }
        Mood mood = moodService.getMoodById(moodId);
        if (mood == null) {
            throw new MoodIsNotExistsException();
        }
        AccountInfo accountInfo = accountInfoService.getAccountInfoById(accountInfoId);
        if (accountInfo == null) {
            throw new AccountIsNotExistsException();
        }
        Map<AccountInfo, Mood> moods = track.getMoods();
        moods.put(accountInfo,mood);
        track.setMoods(moods);
        return trackRepository.save(track);
    }

    @Override
    public Track addMoreTrackInfo(Track track, String text, Long accountInfoId) throws AccountIsNotExistsException, MoreTrackInfoHasExistsException {
        MoreTrackInfo moreTrackInfo = moreTrackInfoService.createMoreTrackInfo(text);
        AccountInfo accountInfo = accountInfoService.getAccountInfoById(accountInfoId);
        if (accountInfo == null) {
            throw new AccountIsNotExistsException();
        }
        Map<AccountInfo, MoreTrackInfo> moreTrackInfos = track.getMoreTrackInfos();
        moreTrackInfos.put(accountInfo,moreTrackInfo);
        track.setMoreTrackInfos(moreTrackInfos);
        return trackRepository.save(track);
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
        if (genre != null) {
            try {
                tracklist = tracklistService.createTracklist(genre);
                tracklist = tracklistService.addTrackIntoTracklist(tracklist.getId(), track.getId());
            } catch (TracklistHasExistsException tracklistHasExists) {
                tracklist = tracklistService.getTracklistByName(genre);
                tracklist = tracklistService.addTrackIntoTracklist(tracklist.getId(),track.getId());
            }
        }
        // По альбому
        if (album != null) {
            try {
                tracklist = tracklistService.createTracklist(album);
                tracklist = tracklistService.addTrackIntoTracklist(tracklist.getId(), track.getId());
            } catch (TracklistHasExistsException tracklistHasExists) {
                tracklist = tracklistService.getTracklistByName(album);
                tracklist = tracklistService.addTrackIntoTracklist(tracklist.getId(),track.getId());
            }
        }
        return track;
    }

}

