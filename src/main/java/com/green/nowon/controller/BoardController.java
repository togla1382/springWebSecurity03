package com.green.nowon.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.nowon.domain.dto.BoardSaveDTO;
import com.green.nowon.domain.dto.BoardUpdateDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.BoardService;

@Controller
public class BoardController {
	
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/boards") // ?page=1 : 쿼리스트링파라미터가 존재하지 않을경우 예외발생: @RequestParam(defaultValue = "1") 해결
	public String board(@RequestParam(defaultValue = "1") int page , Model model) {//문자열로 파라미터 매핑--> int형 parse
		service.getListAll(page ,model);
		return "board/list";
	}
	
	//상세페이지 조회
	@GetMapping("/boards/{bno}")
	public String detail(@PathVariable long bno, Model model) {
		service.sendDetail(bno, model);
		return "board/detail";
	}
	
	@GetMapping("/boards/write")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/boards")            //   Principal principal 객체도 가능 username 정보만확인가능
	public String write(BoardSaveDTO dto, Authentication auth) {
		MyUserDetails myUserDetails=(MyUserDetails)auth.getPrincipal();
		dto.setMno(myUserDetails.getMno());
		service.save(dto);
		
		return "redirect:/boards";
	}
	
	//삭제
	@DeleteMapping("/boards/{bno}")
	public String delete(@PathVariable long bno) {
		
		service.delete(bno);
		
		return "redirect:/boards";
	}
	//삭제
	@PutMapping("/boards/{bno}")                 //setter 있어야함.
	public String update(@PathVariable long bno, BoardUpdateDTO dto) {
		
		//System.out.println(">>>>>>>>>>"+bno +"수정처리:"+ dto);
		//service.update(bno, dto);
		
		service.updateProc(bno, dto);
		
		return "redirect:/boards/{bno}";
	}

}
