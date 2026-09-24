package com.ramirezmontoya.tvmaze_middleware.dto.CommentRating;

public record CommentRatingResponse(
        String comment,
        Integer rating
) {}