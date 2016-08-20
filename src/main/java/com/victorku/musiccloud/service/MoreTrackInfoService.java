package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.MoreTrackInfoHasExistsException;
import com.victorku.musiccloud.exceptions.MoreTrackInfoIsNotExistsException;
import com.victorku.musiccloud.model.MoreTrackInfo;
import com.victorku.musiccloud.model.Track;

public interface MoreTrackInfoService {

    MoreTrackInfo getMoreTrackInfoById(Long id);

    void deleteMoreTrackInfoById(Long id) throws MoreTrackInfoIsNotExistsException;

    MoreTrackInfo createMoreTrackInfo(String text) throws MoreTrackInfoHasExistsException;

}
