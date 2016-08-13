package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.MoreTrackInfo;
import org.springframework.data.repository.CrudRepository;

public interface MoreTrackInfoRepository extends CrudRepository<MoreTrackInfo,Long>{

    MoreTrackInfo findByText(String text);

}
