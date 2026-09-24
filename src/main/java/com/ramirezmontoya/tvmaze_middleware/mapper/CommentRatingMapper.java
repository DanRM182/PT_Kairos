package com.ramirezmontoya.tvmaze_middleware.mapper;

import com.ramirezmontoya.tvmaze_middleware.document.CommentRatingDocument;
import com.ramirezmontoya.tvmaze_middleware.dto.CommentRating.CommentRatingRequest;
import com.ramirezmontoya.tvmaze_middleware.dto.CommentRating.CommentRatingResponse;
import org.springframework.stereotype.Component;

@Component
public class CommentRatingMapper {
    public CommentRatingDocument requestADocumento(CommentRatingRequest request) {
        return request != null ?
                new CommentRatingDocument(
                        null, request.showId(),
                        request.comment(), request.rating())
                : null;
    }

    public CommentRatingResponse documentoAResponse(CommentRatingDocument documento) {
        return documento != null ?
                new CommentRatingResponse(
                        documento.getId(),
                        documento.getShowId(),
                        documento.getComment(),
                        documento.getRating())
                : null;
    }
}
