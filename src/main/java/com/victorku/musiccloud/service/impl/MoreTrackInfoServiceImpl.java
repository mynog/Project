package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.model.MoreTrackInfo;
import com.victorku.musiccloud.repository.MoreTrackInfoRepository;
import com.victorku.musiccloud.service.MoreTrackInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoreTrackInfoServiceImpl implements MoreTrackInfoService {

    @Autowired
    private MoreTrackInfoRepository moreTrackInfoRepository;

    @Override
    public MoreTrackInfo getMoreTrackInfoById(Long id) {
        return moreTrackInfoRepository.findOne(id);
    }

}
