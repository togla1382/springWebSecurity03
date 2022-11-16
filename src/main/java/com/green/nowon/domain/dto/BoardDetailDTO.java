package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

import com.green.nowon.domain.entity.BoardEntity;

import lombok.Getter;

@Getter
public class BoardDetailDTO {
	
	private long bno;
	private String title;
	private String content;
	private int readCount;
	private long writerMno;
	private String writerEmail;
	private String writerNickName;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	//entity-> dto map
	public BoardDetailDTO(BoardEntity ent) {
		this.bno = ent.getBno();
		this.title = ent.getTitle();
		this.content = ent.getContent();
		this.readCount = ent.getReadCount();
		this.writerMno=ent.getMember().getMno();
		this.writerEmail =ent.getMember().getEmail();//작성자는 이메일정보로 대체
		this.writerNickName =ent.getMember().getNickName();//작성자는 이메일정보로 대체
		this.createdDate = ent.getCreatedDate();
		this.updatedDate = ent.getUpdatedDate();
	}
	
	
	

}
