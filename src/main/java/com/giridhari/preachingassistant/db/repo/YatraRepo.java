package com.giridhari.preachingassistant.db.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.giridhari.preachingassistant.db.model.Yatra;

@Repository
public interface YatraRepo extends PagingAndSortingRepository<Yatra, Long> {

	public Page<Yatra> findByYatraAdmin_id(long id, Pageable pageable);
}
