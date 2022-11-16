package com.green.nowon.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.green.nowon.domain.dto.BoardUpdateDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gen_j_board_seq",
		sequenceName = "j_board_seq", initialValue = 1, allocationSize = 1)
@Table(name = "j_board")
@Entity
public class BoardEntity extends BaseDateTimeColumns{

	@Id
	@GeneratedValue(generator = "gen_j_board_seq", strategy = GenerationType.SEQUENCE)
	private long bno;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;
	private int readCount;
	//작성자 - MemeberEntity
	
	//@JoinColumn이 명시되면 주엔티티(부모)
	@JoinColumn(name = "mno")//물리DB에생성되는 FK컬럼명: 생략시:필드명_pk컬럼(member_mno)
	//부모엔티니에서 자식 엔티티의 상태변화의 영향을 주는것: cascade
	@ManyToOne(cascade = CascadeType.DETACH)
	private MemberEntity member;//작성자

	//편의메서드
	public BoardEntity update(BoardUpdateDTO dto) {
		this.title=dto.getTitle();
		this.content=dto.getContent();
		return this;
	}
}


