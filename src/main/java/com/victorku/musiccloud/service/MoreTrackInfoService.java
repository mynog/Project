package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.MoreTrackInfoHasExistsException;
import com.victorku.musiccloud.model.MoreTrackInfo;

public interface MoreTrackInfoService {

    MoreTrackInfo getMoreTrackInfoById(Long id);

    MoreTrackInfo createMoreTrackInfo(String text) throws MoreTrackInfoHasExistsException;

}
