package com.exam.sbb.question;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);


    List<Question> findBySubjectLike(String subject);

    @Modifying
    @Transactional
    @Query(value ="TRUNCATE question" ,nativeQuery = true)
    void truncate();
}
