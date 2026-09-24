package com.ramirezmontoya.tvmaze_middleware.service;

import com.ramirezmontoya.tvmaze_middleware.document.CommentRatingDocument;
import com.ramirezmontoya.tvmaze_middleware.dto.CommentRating.CommentRatingRequest;
import com.ramirezmontoya.tvmaze_middleware.dto.CommentRating.CommentRatingResponse;
import com.ramirezmontoya.tvmaze_middleware.mapper.CommentRatingMapper;
import com.ramirezmontoya.tvmaze_middleware.repository.CommentRatingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CommentRatingService {
    private final CommentRatingRepository commentRatingRepository;
    private final CommentRatingMapper commentRatingMapper;

    public void guardarComentariosRating(CommentRatingRequest commentRatingRequest) {
        log.info("Obtenemos y guardamos el documento");

        commentRatingRepository.save(commentRatingMapper.requestADocumento(commentRatingRequest));
    }

    public Map<Long, List<CommentRatingResponse>> obtenerComentariosRating(Collection<Long> showIds) {
        if(showIds.isEmpty()) return Map.of();

        log.info("Buscamos comentarios y ratings con los ShowId proveídos");

        return commentRatingRepository.findByShowIdIn(showIds).stream()
                .collect(Collectors.groupingBy(
                        CommentRatingDocument::getShowId,
                        Collectors.mapping(commentRatingMapper::documentoAResponse,
                                Collectors.toList())));
    }
}
