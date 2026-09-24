package com.ramirezmontoya.tvmaze_middleware.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Document(collection = "showsCommentsRating")
public class CommentRatingDocument {
    @Id
    private String id;

    private Long showId;
    private String comment;
    private Integer rating;
}
