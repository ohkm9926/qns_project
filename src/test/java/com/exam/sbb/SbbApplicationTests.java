package com.exam.sbb;

import com.exam.sbb.question.Question;
import com.exam.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa(){
		Question q1 = new Question();
		q1.setSubject("kyumin이 무엇인가요");
		q1.setContent("kyumin에 대해 알고싶어요");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
	}
	@Test
	void testJpa12(){
		Question q1 = new Question();
		q1.setSubject("kyumin123이 무엇인가요");
		q1.setContent("kyumin123에 대해 알고싶어요");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
	}

    @Test
	void testJpa2(){
		List<Question> all = questionRepository.findAll();
		assertEquals(2,all.size());

		Question q = all.get(0);
		//실행한 결과가 같은지 알려주는 assertEquals
		assertEquals("kyumin이 무엇인가요",q.getSubject());
	}

	@Test
	void testJpa3(){
		Question q = questionRepository.findBySubject("kyumin123이 무엇인가요");
	}

	@Test
	void testJpa4(){
		Question q = questionRepository.findBySubjectAndContent("kyumin123이 무엇인가요","kyumin123에 대해 알고싶어요");
		assertEquals(3,q.getId());
	}

	@Test
	void testJpa5(){
		List<Question> qList = questionRepository.findBySubjectLike("kyumin123%");
		Question q = qList.get(0);
		assertEquals("kyumin123이 무엇인가요" , q.getSubject());
	}
	@Test
	void testJpa6(){
		Optional<Question> oq = questionRepository.findById(1);
		assertThat(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		questionRepository.save(q);
	}
}
