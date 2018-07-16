package com.giridhari.preachingassistant.db.repo;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.giridhari.preachingassistant.db.model.Yatra;

@Repository
public interface YatraRepo extends PagingAndSortingRepository<Yatra, Long> {

	public Page<Yatra> findByYatraAdmin_id(long id, Pageable pageable);

	@Query("SELECT y from Program p INNER JOIN p.parentYatra y INNER JOIN p.mentor d where d.id = ?1")
	public List<Yatra> findYatrasForMentor(long devoteeId);

	@Query("SELECT y from Yatra y INNER JOIN y.yatraAdmin d where d.id = ?1")
	public List<Yatra> findYatrasForYatraAdmin(long devoteeId);
}
