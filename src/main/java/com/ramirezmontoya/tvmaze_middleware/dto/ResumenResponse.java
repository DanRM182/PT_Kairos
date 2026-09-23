package com.ramirezmontoya.tvmaze_middleware.dto;

import java.util.List;

public record ResumenResponse(
        Long id,
        String name,
        String channel,
        String summary,
        List<String> genres
) { }
