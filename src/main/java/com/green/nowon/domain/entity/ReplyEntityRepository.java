package com.green.nowon.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyEntityRepository extends JpaRepository<ReplyEntity, Long>{
	//만드는규칙 : 키워드 확인 + Entity의 객체변수이름 또는 컬럼이름(카멜표현)
	List<ReplyEntity> findAllByBoardBnoOrderByRnoDesc(long bno); 

}
