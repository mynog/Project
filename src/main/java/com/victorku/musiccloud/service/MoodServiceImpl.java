package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.MoodIsNotExists;
import com.victorku.musiccloud.model.Mood;
import com.victorku.musiccloud.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoodServiceImpl implements MoodService {

    @Autowired
    MoodRepository moodRepository;

    @Override
    public Mood getMoodById(Long id) {
        return moodRepository.findOne(id);
    }

    @Override
    public void deleteMoodById(Long id) throws MoodIsNotExists {
        if(!moodRepository.exists(id)){
            throw new MoodIsNotExists();
        }
        moodRepository.delete(id);
    }
}
