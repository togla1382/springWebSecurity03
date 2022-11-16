package com.green.nowon.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.nowon.domain.entity.BoardEntity;

import lombok.Getter;

@Getter
public class BoardListDTO {
	
	private long bno;
	private String title;
	private int readCount;
	private String writer;
	private LocalDateTime updatedDate;
	private LocalDate toDay;
	
	//Entity를 -> BoardListDTO(BoardEntity ent)
	public BoardListDTO(BoardEntity ent) {
		
		this.bno = ent.getBno();
		this.title = ent.getTitle();
		this.readCount = ent.getReadCount();
		this.writer = ent.getMember().getEmail()+"("+ent.getMember().getName()+")";//email을 작성자로 쓴경우
		this.updatedDate = ent.getUpdatedDate();
		toDay=LocalDate.now();
	}
	

}
