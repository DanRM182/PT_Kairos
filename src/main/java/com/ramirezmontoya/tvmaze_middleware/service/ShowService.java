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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        Map<Long, List<CommentRatingResponse>> comentariosRatings =
                commentRatingService.obtenerComentariosRating(obtenerShowIds(shows));

        return shows.stream()
                .map(show ->
                        showMapper.convertirAResponse(
                                show, comentariosRatings.getOrDefault(show.id(), List.of())))
                .toList();
    }

    public Map<String, Object> obtenerShowPorIdConComentariosRatings(Long idShow) {
        Map<String, Object> show = obtenerShow(idShow);

        log.info("Obtenemos el show");

        List<CommentRatingResponse> comentariosRatings = comentariosRatingsDe(idShow);

        log.info("Obtenemos los comentarios del show");

        return conComentarios(show, comentariosRatings);
    }

    private List<CommentRatingResponse> comentariosRatingsDe(Long showId) {
        return commentRatingService.obtenerComentariosRating(Set.of(showId))
                .getOrDefault(showId, List.of());
    }

    private Map<String, Object> conComentarios(Map<String, Object> show,
                                               List<CommentRatingResponse> comentariosRatings) {
        Map<String, Object> todoJunto = new LinkedHashMap<>(show);

        todoJunto.put("comments", comentariosRatings);

        return todoJunto;
    }

    private List<Long> obtenerShowIds(List<TvmazeShow> shows) {
        return shows.stream().map(TvmazeShow::id).toList();
    }

    private Map<String, Object> obtenerShow(Long idShow) {
        return showRepository.findById(idShow)
                .map(ShowDocument::getData)
                .orElseGet(() -> consultarYGuardar(idShow));
    }

    private Map<String, Object> consultarYGuardar(Long idShow) {
        log.info("No se encontró el Show con ID {} en la caché", idShow);

        Map<String, Object> show = tvmazeClient.obtenerShowPorId(idShow);

        showRepository.save(new ShowDocument(idShow, show));

        log.info("Almacenando datos del Show con ID {} en la caché", idShow);

        return show;
    }


}
