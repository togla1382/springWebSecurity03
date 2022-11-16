package com.green.nowon;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.BoardEntity;
import com.green.nowon.domain.entity.BoardEntityRepository;
import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.domain.entity.ReplyEntity;
import com.green.nowon.domain.entity.ReplyEntityRepository;
import com.green.nowon.security.MyRole;

@SpringBootTest
class SpringWebSecurity03ApplicationTests {

	@Autowired
	BoardEntityRepository bRepository;
	
	@Autowired
	MemberEntityRepository mRepository;
	
	@Autowired
	ReplyEntityRepository rRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	//@Test
	void 댓글저장() {
		long bno=1003L; //댓글대상
		long mno=23L; //작성자의pk
		ReplyEntity entity=ReplyEntity.builder()
				.text(bno+"의 댓글2")
				.board(BoardEntity.builder().bno(bno).build())//게시글정보
				.member(MemberEntity.builder().mno(mno).build())
				.build();
		rRepository.save(entity);
	}
	
	//@Test
	void 관리자() {
		String email="admin@test.com";
		mRepository.save(MemberEntity.builder()
				.email(email)
				.name("관리자")
				.pass(encoder.encode("1234"))
				.nickName("admin")
				.build()
				.addRole(MyRole.GUEST).addRole(MyRole.USER).addRole(MyRole.ADMIN)
				);
	}
	
	//@Test
	void 회원가입() {
		//*
		IntStream.rangeClosed(1, 20).forEach(i->{
			String email="test"+i+"@test.com";
			mRepository.save(MemberEntity.builder()
					.email(email)
					.name("테스트"+i)
					.pass(encoder.encode(email))
					.nickName("test"+i+"Nick")
					.build()
					.addRole(MyRole.USER)
					)
					
			;
		});
		//*/
		/*
		for(int i=1; i<=20; i++) {
			String email="test"+i+"@test.com";
			mRepository.save(MemberEntity.builder()
					.email(email)
					.name("테스트"+i)
					.nickName("test"+i+"Nick")
					.build().addRole(MyRole.USER))
					
			;
		}
		*/
		
		
	}
	
	//@Test
	void 게시글저장() {
		mRepository.findAll().forEach(e->{
			
			IntStream.rangeClosed(1, 5).forEach(i->{
				bRepository.save(BoardEntity.builder()
						.member(e)//작성자
						.title(e.getNickName()+"이 작성한 제목"+i)
						.content(e.getNickName()+"이 작성한 내용"+i)
						.build());
			});
		});;
		
		
	}

}
