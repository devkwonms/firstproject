package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // 해당 댓글 엔티티 여러개가 하나의 Article에 연관된다!
    @JoinColumn(name = "article_id")    // "article_id" 컬럼에 Article의 대표값을 저장!
    private Article article ;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 처리
        if(dto.getId() != null)
            throw new IllegalStateException("댓글 생성 실패! id 가 없어야합니다");
        // 엔티티 생성 및 반환
        if(dto.getArticleId() != article.getId())
            throw new IllegalStateException("댓글 생성 실패! 게시글의 id가 잘못되었습니다!");
        return null;
    }
}
