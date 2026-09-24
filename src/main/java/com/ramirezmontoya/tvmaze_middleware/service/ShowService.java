package com.ramirezmontoya.tvmaze_middleware.service;

import com.ramirezmontoya.tvmaze_middleware.client.TvmazeClient;
import com.ramirezmontoya.tvmaze_middleware.document.ShowDocument;
import com.ramirezmontoya.tvmaze_middleware.dto.CommentRating.CommentRatingResponse;
import com.ramirezmontoya.tvmaze_middleware.dto.ResumenResponse;
import com.ramirezmontoya.tvmaze_middleware.dto.TvMazeSearchResult;
import com.ramirezmontoya.tvmaze_middleware.dto.TvmazeShow;
import com.ramirezmontoya.tvmaze_middleware.mapper.ShowMapper;
import com.ramirezmontoya.tvmaze_middleware.repository.ShowRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ShowService {
    private final TvmazeClient tvmazeClient;
    private final CommentRatingService commentRatingService;
    private final ShowMapper showMapper;
    private final ShowRepository showRepository;

    public List<ResumenResponse> buscar(String query) {
        List<TvmazeShow> shows = tvmazeClient.buscarShows(query).stream()
                .map(TvMazeSearchResult::show)
                .toList();

        List<Long> showIds = shows.stream().map(TvmazeShow::id).toList();

        Map<Long, List<CommentRatingResponse>> commentsRatings = commentRatingService.obtenerComentariosRating(showIds);

        return shows.stream()
                .map(show ->
                        showMapper.convertirAResponse(
                                show, commentsRatings.getOrDefault(show.id(), List.of())))
                .toList();
    }

    public Map<String, Object> obtenerShowPorId(Long idShow) {
        return showRepository.findById(idShow)
                        .map(ShowDocument::getData)
                                .orElseGet(() -> {
                                    log.info("No se encontró el Show con ID {} en la caché", idShow);
                                    Map<String, Object> show = tvmazeClient.obtenerShowPorId(idShow);
                                    showRepository.save(new ShowDocument(idShow, show));
                                    log.info("Almacenando datos del Show con ID {} en la caché", idShow);
                                    return show;
                                });
    }
}
