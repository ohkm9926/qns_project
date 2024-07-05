package com.exam.sbb;

import com.exam.sbb.answer.Answer;
import com.exam.sbb.answer.AnswerRepository;
import com.exam.sbb.question.Question;
import com.exam.sbb.question.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class AnswerApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	private int LastSampleDataId;

	@BeforeEach
	void beforeEach() {
		clearData();
		clearSampleData();
	}
	private void clearData() {
		questionRepository.disableForeignKeyChecks();
		answerRepository.truncate();
		questionRepository.enableForeignKeyChecks();
	}
	private void clearSampleData() {
	
	}
	@Test
	void testJPA(){
		Question q = questionRepository.findById(2).get();
		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);
		answerRepository.save(a);
	}
	
}
