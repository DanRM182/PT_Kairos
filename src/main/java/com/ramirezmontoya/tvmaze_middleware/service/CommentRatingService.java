package com.ramirezmontoya.tvmaze_middleware.service;

import com.ramirezmontoya.tvmaze_middleware.dto.CommentRating.CommentRatingRequest;
import com.ramirezmontoya.tvmaze_middleware.mapper.CommentRatingMapper;
import com.ramirezmontoya.tvmaze_middleware.repository.CommentRatingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CommentRatingService {
    private final CommentRatingRepository commentRatingRepository;
    private final CommentRatingMapper commentRatingMapper;

    public void guardarComentariosRating(CommentRatingRequest) {
        log.info("Obtenemos y ");
    }
}
