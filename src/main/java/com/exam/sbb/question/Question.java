package com.exam.sbb.question;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
@Entity // 아래 Question클래스는 엔티티 클래스이다.
//아래 클래스와 1:1로 매칭되는 테이블이 db없다면, 자동으로으로 생성해준다.
public class Question {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Integer id;

    @Column(length = 200)  //varchar(200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
}
