package com.example.exam0528_2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
// NoArgsConstructor < = > Post post = new Post(); 와 같음. 즉 post라는 객체를 만들고 선언
// 했지만 안에는 아무것도 없음, 기본생성자를 만들어줌

public class Post {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdAt;
}
