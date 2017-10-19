package com.giridhari.preachingassistant.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Yatra;

@Repository
public interface YatraRepo extends PagingAndSortingRepository<Yatra, Long> {

}
