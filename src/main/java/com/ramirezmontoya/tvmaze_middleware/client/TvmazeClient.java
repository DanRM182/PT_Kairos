package com.ramirezmontoya.tvmaze_middleware.client;

import com.ramirezmontoya.tvmaze_middleware.dto.TvMazeSearchResult;
import com.ramirezmontoya.tvmaze_middleware.dto.TvmazeShow;
import com.ramirezmontoya.tvmaze_middleware.exception.ShowNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class TvmazeClient {
    private final RestClient restClient;

    public TvmazeClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<TvMazeSearchResult> buscarShows(String query) {
        return restClient.get()
                .uri("/search/shows?q={q}", query)
                .retrieve()
                .body(new ParameterizedTypeReference<List<TvMazeSearchResult>>() {});
    }

    public Map<String, Object> obtenerShow(Long id) {
        try {
            return restClient.get()
                    .uri("/shows/{id}", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<Map<String, Object>>() {});
        } catch (HttpClientErrorException.NotFound e) {
            throw new ShowNotFoundException("No se encontró el show con ID: " + id);
        }
    }
}
