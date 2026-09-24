package com.ramirezmontoya.tvmaze_middleware.mapper;

import com.ramirezmontoya.tvmaze_middleware.dto.CommentRating.CommentRatingResponse;
import com.ramirezmontoya.tvmaze_middleware.dto.ResumenResponse;
import com.ramirezmontoya.tvmaze_middleware.dto.TvmazeShow;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowMapper {
    public ResumenResponse convertirAResponse(TvmazeShow show, List<CommentRatingResponse> commentsRatings) {
        return show != null ?
                new ResumenResponse(
                        show.id(),
                        show.name(),
                        validarCanal(show),
                        show.summary(),
                        show.genres(),
                        commentsRatings)
                : null;
    }

    private String validarCanal(TvmazeShow show) {
        return show.network() != null ?
                show.network().name() : show.webChannel() != null ?
                show.webChannel().name() : null;
    }
}
