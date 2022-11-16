package com.green.nowon.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long>{

	//jpa에서 기본으로 제공하고 있는 쿼리 메서드를 이용할수 있음.
	//개발자가 필요로하는 쿼리 메서드를 구성할수 있는 영역
	//JPQL->MariaDB106Dialect 
	Optional<MemberEntity> findByEmail(String username);//문법에 맞지 않으면 실행오류
}
