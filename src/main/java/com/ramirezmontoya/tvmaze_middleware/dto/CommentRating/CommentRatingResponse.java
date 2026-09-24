package com.ramirezmontoya.tvmaze_middleware.dto.CommentRating;

public record CommentRatingResponse(
        String id,
        Long showId,
        String comment,
        Integer rating
) {}