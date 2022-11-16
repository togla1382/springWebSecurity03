package com.green.nowon.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SequenceGenerator(name = "gen_seq_re",
	sequenceName = "seq_re", initialValue = 1, allocationSize = 1
)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "j_reply")
@Entity
public class ReplyEntity extends BaseDateTimeColumns{

	@Id
	@GeneratedValue(generator = "gen_seq_re", strategy = GenerationType.SEQUENCE)
	private long rno;
	
	@Column(nullable = false)
	private String text;
	
	//작성자 fk
	@JoinColumn(name = "mno", nullable = false)
	@ManyToOne(cascade = CascadeType.DETACH)
	private MemberEntity member;
	
	//게시글 fk
	@JoinColumn(name = "bno", nullable = false)
	@ManyToOne(cascade = CascadeType.DETACH)
	private BoardEntity board;
	
	
}
