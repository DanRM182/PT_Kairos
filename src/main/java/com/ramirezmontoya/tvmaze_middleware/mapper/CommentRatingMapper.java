package com.ramirezmontoya.tvmaze_middleware.mapper;

import com.ramirezmontoya.tvmaze_middleware.document.CommentRatingDocument;
import com.ramirezmontoya.tvmaze_middleware.dto.CommentRating.CommentRatingRequest;
import com.ramirezmontoya.tvmaze_middleware.dto.CommentRating.CommentRatingResponse;
import org.springframework.stereotype.Component;

@Component
public class CommentRatingMapper {
    public CommentRatingDocument requestADocumento(CommentRatingRequest request) {
        return request != null ?
                CommentRatingDocument.builder()
                        .showId(request.showId())
                        .comment(request.comment())
                        .rating(request.rating())
                        .build() : null;
    }

    public CommentRatingResponse documentoAResponse(CommentRatingDocument documento) {
        return documento != null ?
                new CommentRatingResponse(
                        documento.getComment(),
                        documento.getRating())
                : null;
    }
}
