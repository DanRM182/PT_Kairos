package com.ramirezmontoya.tvmaze_middleware.dto;

import java.util.List;

public record TvmazeShow(
        Long id,
        String name,
        String summary,
        List<String> genres,
        TvmazeChannel network,
        TvmazeChannel webChannel
) { }