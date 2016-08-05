package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.MoreTrackInfoIsNotExists;
import com.victorku.musiccloud.model.MoreTrackInfo;

public interface MoreTrackInfoService {

    MoreTrackInfo getMoreTrackInfoById(Long id);

    void deleteMoreTrackInfoById(Long id) throws MoreTrackInfoIsNotExists;
}
