package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.MoreTrackInfoIsNotExists;
import com.victorku.musiccloud.model.MoreTrackInfo;
import com.victorku.musiccloud.repository.MoreTrackInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoreTrackInfoServiceImpl implements MoreTrackInfoService {

    @Autowired
    MoreTrackInfoRepository moreTrackInfoRepository;

    @Override
    public MoreTrackInfo getMoreTrackInfoById(Long id) {
        return moreTrackInfoRepository.findOne(id);
    }

    @Override
    public void deleteMoreTrackInfoById(Long id) throws MoreTrackInfoIsNotExists {
        if(!moreTrackInfoRepository.exists(id)){
            throw new MoreTrackInfoIsNotExists();
        }
        moreTrackInfoRepository.delete(id);
    }
}
