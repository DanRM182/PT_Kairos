package com.ramirezmontoya.tvmaze_middleware.dto;

import com.ramirezmontoya.tvmaze_middleware.dto.CommentRating.CommentRatingResponse;

import java.util.List;

public record ResumenResponse(
        Long id,
        String name,
        String channel,
        String summary,
        List<String> genres,
        List<CommentRatingResponse> commentsRatings
) { }
