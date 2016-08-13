package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre,Long> {

    Genre findByName(String name);

}
