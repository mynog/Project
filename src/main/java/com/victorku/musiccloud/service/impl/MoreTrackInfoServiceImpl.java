package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.MoreTrackInfoHasExistsException;
import com.victorku.musiccloud.exceptions.MoreTrackInfoIsNotExistsException;
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

    @Override
    public void deleteMoreTrackInfoById(Long id) throws MoreTrackInfoIsNotExistsException {
        if(!moreTrackInfoRepository.exists(id)){
            throw new MoreTrackInfoIsNotExistsException();
        }
        moreTrackInfoRepository.delete(id);
    }

    @Override
    public MoreTrackInfo createMoreTrackInfo(String text) throws MoreTrackInfoHasExistsException {
        MoreTrackInfo moreTrackInfo = moreTrackInfoRepository.findByText(text);
        if (moreTrackInfo != null) {
            throw new MoreTrackInfoHasExistsException();
        }
        moreTrackInfo = new MoreTrackInfo(text);
        return moreTrackInfoRepository.save(moreTrackInfo);
    }
}
