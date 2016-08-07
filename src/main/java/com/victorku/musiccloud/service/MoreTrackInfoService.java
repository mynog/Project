package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.MoreTrackInfoIsNotExistsException;
import com.victorku.musiccloud.model.MoreTrackInfo;

public interface MoreTrackInfoService {

    MoreTrackInfo getMoreTrackInfoById(Long id);

    void deleteMoreTrackInfoById(Long id) throws MoreTrackInfoIsNotExistsException;
}
