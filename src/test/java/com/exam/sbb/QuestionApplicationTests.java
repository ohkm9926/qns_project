package com.exam.sbb;

import com.exam.sbb.question.Question;
import com.exam.sbb.question.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QuestionApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@BeforeEach
	void beforeEach() {
		clearData();
		clearSampleData();


	}



	private void clearData() {

		questionRepository.disableForeignKeyChecks();
		questionRepository.truncate();
		questionRepository.enableForeignKeyChecks();

	}

	private void clearSampleData() {

		Question q1 = new Question();
		q1.setSubject("kyumin이 무엇인가요");
		q1.setContent("kyumin에 대해 알고싶어요");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);



		Question q2 = new Question();
		q2.setSubject("kyumin123이 무엇인가요");
		q2.setContent("kyumin123에 대해 알고싶어요");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);


	}


	@Test
	void 저장(){


		Question q1 = new Question();
		q1.setSubject("kyumin이 무엇인가요");
		q1.setContent("kyumin에 대해 알고싶어요");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);



		Question q2 = new Question();
		q2.setSubject("kyumin123이 무엇인가요");
		q2.setContent("kyumin123에 대해 알고싶어요");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);



	}

}
