package com.exam.sbb;

import com.exam.sbb.question.Question;
import com.exam.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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


}
