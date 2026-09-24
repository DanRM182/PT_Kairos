package com.ramirezmontoya.tvmaze_middleware.service;

import com.ramirezmontoya.tvmaze_middleware.client.TvmazeClient;
import com.ramirezmontoya.tvmaze_middleware.document.ShowDocument;
import com.ramirezmontoya.tvmaze_middleware.dto.ResumenResponse;
import com.ramirezmontoya.tvmaze_middleware.dto.TvMazeSearchResult;
import com.ramirezmontoya.tvmaze_middleware.dto.TvmazeShow;
import com.ramirezmontoya.tvmaze_middleware.mapper.ShowMapper;
import com.ramirezmontoya.tvmaze_middleware.repository.ShowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
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
                                    Map<String, Object> show = tvmazeClient.obtenerShowPorId(idShow);
                                    showRepository.save(new ShowDocument(idShow, show));
                                    return show;
                                });
    }
}
