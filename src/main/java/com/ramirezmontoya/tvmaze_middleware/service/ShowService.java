package com.ramirezmontoya.tvmaze_middleware.service;

import com.ramirezmontoya.tvmaze_middleware.client.TvmazeClient;
import com.ramirezmontoya.tvmaze_middleware.dto.ResumenResponse;
import com.ramirezmontoya.tvmaze_middleware.dto.TvMazeSearchResult;
import com.ramirezmontoya.tvmaze_middleware.dto.TvmazeShow;
import com.ramirezmontoya.tvmaze_middleware.mapper.ShowMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShowService {
    private final TvmazeClient tvmazeClient;
    private final ShowMapper showMapper;

    public List<ResumenResponse> buscar(String query) {
        return tvmazeClient.buscarShows(query).stream()
                .map(TvMazeSearchResult::show)
                .map(showMapper::convertirAResponse)
                .toList();
    }
}
