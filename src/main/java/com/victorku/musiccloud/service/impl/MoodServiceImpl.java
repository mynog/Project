package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.MoodHasExistsException;
import com.victorku.musiccloud.exceptions.MoodIsNotExistsException;
import com.victorku.musiccloud.model.Mood;
import com.victorku.musiccloud.repository.MoodRepository;
import com.victorku.musiccloud.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoodServiceImpl implements MoodService {

    @Autowired
    private MoodRepository moodRepository;

    @Override
    public Mood getMoodById(Long id) {
        return moodRepository.findOne(id);
    }

    @Override
    public void deleteMoodById(Long id) throws MoodIsNotExistsException {
        if(!moodRepository.exists(id)){
            throw new MoodIsNotExistsException();
        }
        moodRepository.delete(id);
    }

    @Override
    public Mood createMood(String name) throws MoodHasExistsException {
        Mood mood = moodRepository.findByName(name);
        if (mood != null) {
            throw new MoodHasExistsException();
        }
        mood = new Mood(name);
        return moodRepository.save(mood);
    }

}
