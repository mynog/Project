package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.MoodIsNotExists;
import com.victorku.musiccloud.model.Mood;

public interface MoodService {

    Mood getMoodById(Long id);

    void deleteMoodById(Long id) throws MoodIsNotExists;
}
