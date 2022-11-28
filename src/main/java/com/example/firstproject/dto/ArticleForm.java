package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data //( = @Getter + @Setter + @Tostring + @EqualsAndHashCode + @RequiredArgsConstructor )
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
