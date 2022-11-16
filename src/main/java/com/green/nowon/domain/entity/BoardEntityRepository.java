package com.green.nowon.domain.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long>{

	//findAll 이용하여 정렬시 OrderBy 키워드 앞에 By키워드 추가
	List<BoardEntity> findAllByOrderByBnoDesc();

	Page<BoardEntity> findAllByOrderByBnoDesc(Pageable pageable);

}
