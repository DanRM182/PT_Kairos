package com.ramirezmontoya.tvmaze_middleware.dto.CommentRating;

import jakarta.validation.constraints.*;

public record CommentRatingRequest(
        @NotNull(message = "El ID del Show es requerido")
        @Positive(message = "El ID del Show debe ser mayor a 0")
        Long showId,

        @NotBlank(message = "El comentario es requerido")
        String comment,

        @NotNull(message = "El rating es requerido")
        @Min(value = 0, message = "El valor mínimo de rating es 0")
        @Max(value = 5, message = "El valor máximo de rating es 5")
        Integer rating
) { }