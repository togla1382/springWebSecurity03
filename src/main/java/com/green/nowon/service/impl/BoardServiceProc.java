package com.green.nowon.service.impl;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.BoardDetailDTO;
import com.green.nowon.domain.dto.BoardListDTO;
import com.green.nowon.domain.dto.BoardSaveDTO;
import com.green.nowon.domain.dto.BoardUpdateDTO;
import com.green.nowon.domain.dto.ReplyListDTO;
import com.green.nowon.domain.entity.BoardEntity;
import com.green.nowon.domain.entity.BoardEntityRepository;
import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.ReplyEntity;
import com.green.nowon.domain.entity.ReplyEntityRepository;
import com.green.nowon.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceProc implements BoardService{
	
	private final BoardEntityRepository repository;
	private final ReplyEntityRepository replyRepo;

	@Override
	public void getListAll(int page, Model model) {
		//조회한 board list를 페이지로 전송
		//*
		//int page=1;
		int size=10;
		Sort sort=Sort.by(Direction.DESC, "bno");
		Pageable pageable=PageRequest.of(page-1, size, sort);
		Page<BoardEntity> result=repository.findAll(pageable);
		
		
		//Pageable pageable=PageRequest.of(page-1, size);
		//Page<BoardEntity> result=repository.findAllByOrderByBnoDesc(pageable);
		
		//List<BoardEntity> list=result.getContent();
		//int pageTot=result.getTotalPages();
		//result.
		model.addAttribute("p", result);
		//System.out.println("getNumber():"+result.getNumber());
		//System.out.println("getNumberOfElements():"+result.getNumberOfElements());
		//System.out.println("isFirst():"+result.isFirst());
		//System.out.println("isLast():"+result.isLast());
		//System.out.println("hasNext():"+result.hasNext());
		//System.out.println("hasPrevious():"+result.hasPrevious());
		
		model.addAttribute("list", result.stream()
				//.map(ent->new BoardListDTO(ent))//Entity->DTO
				.map(BoardListDTO::new)//Entity->DTO
				.collect(Collectors.toList()));
		 //*/
		/*
		model.addAttribute("list", repository.findAllByOrderByBnoDesc().stream()
				//.map(ent->new BoardListDTO(ent))//Entity->DTO
				.map(BoardListDTO::new)//Entity->DTO
				.collect(Collectors.toList()));
		//*/
		
	}

	@Override
	public void sendDetail(long bno, Model model) {
		
		//Id : pk컬럼 
		//Optional<BoardEntity> result=repository.findById(bno);
		model.addAttribute("detail", repository.findById(bno)
				.map(BoardDetailDTO::new)
				.orElseThrow());
		
		model.addAttribute("replies", replyRepo.findAllByBoardBnoOrderByRnoDesc(bno)//1. List<ReplyEntity> 
										.stream()//2.Stream<ReplyEntity>
										.map(ReplyListDTO::new)//3.Stream<ReplyListDTO>
										.collect(Collectors.toList()));//4.List<ReplyListDTO> 
		
	}

	@Override
	public void save(BoardSaveDTO dto, String email) {
	}

	@Override
	public void save(BoardSaveDTO dto) {
		//repository.save(dto.toBoardEntity());
		BoardEntity entity=BoardEntity.builder()
				.title(dto.getTitle()).content(dto.getContent())
				.member(MemberEntity.builder().mno(dto.getMno()).build())
				.build();
		repository.save(entity);
		
	}

	@Override
	public void delete(long bno) {
		//bno : pk 이므로 
		repository.deleteById(bno);
		
	}

	@Override
	public void update(long bno, BoardUpdateDTO dto) {
		//수정처리 대상은 bno(pk), 수정할데이터 dto:제목,내용
		//1.대상의 존재여부
		Optional<BoardEntity> result= repository.findById(bno);
		//2.존재하면 수정
		if(result.isPresent()) {
			BoardEntity entity=result.get();
			//수정하기위한 편의메서드 아니면 setter메서드 이용하세요
			entity.update(dto);
			///원본-업데이트 반영
			repository.save(entity);//이미 존재하는 Pk이면 수정반영됩니다.
		}
	}

	//수정처리 간결하게 가능함...
	@Transactional // map()에서 수정한정보가 적용되어 update 쿼리가 실행됨
	@Override
	public void updateProc(long bno, BoardUpdateDTO dto) {
		System.out.println(">>>>>>>>>>>>수정처리");
		repository.findById(bno).map(entity->entity.update(dto));
		System.out.println(">>>>>>>>>>>>수정처리완료");
	}//@Transactional  메서드가 끝나면 commit 이루어지므로 이전에 Entity객체가 변경되면 update가 반영

}
