package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.BoardEntity;
import com.green.nowon.domain.entity.MemberEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardSaveDTO {//write페이지에서 입력한 제목,내용-->DB
	
	private String title; //method mapping
	private String content;//method mapping
	private long mno;//작성자 MemberEntity의 pk
	
	//셋팅된 dto data->Entity객체로 변환
	public BoardEntity toBoardEntity() {
		return BoardEntity.builder()
				.title(title).content(content).member(MemberEntity.builder().mno(mno).build())
				.build();
	}
	
}
