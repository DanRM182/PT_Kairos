package com.ramirezmontoya.tvmaze_middleware.controller;

import com.ramirezmontoya.tvmaze_middleware.dto.CommentRating.CommentRatingRequest;
import com.ramirezmontoya.tvmaze_middleware.service.CommentRatingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment-rating")
@AllArgsConstructor
@Validated
public class CommentRatingController {
    private final CommentRatingService service;

    @PostMapping
    public ResponseEntity<Void> guardarComentariosRating(
            @Valid @RequestBody CommentRatingRequest request
    ) {
        service.guardarComentariosRating(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
