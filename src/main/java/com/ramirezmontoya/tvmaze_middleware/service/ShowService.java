package com.ramirezmontoya.tvmaze_middleware.service;

import com.ramirezmontoya.tvmaze_middleware.client.TvmazeClient;
import com.ramirezmontoya.tvmaze_middleware.document.ShowDocument;
import com.ramirezmontoya.tvmaze_middleware.dto.ResumenResponse;
import com.ramirezmontoya.tvmaze_middleware.dto.TvMazeSearchResult;
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
    private final ShowMapper showMapper;
    private final ShowRepository showRepository;

    public List<ResumenResponse> buscar(String query) {
        return tvmazeClient.buscarShows(query).stream()
                .map(TvMazeSearchResult::show)
                .map(showMapper::convertirAResponse)
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
