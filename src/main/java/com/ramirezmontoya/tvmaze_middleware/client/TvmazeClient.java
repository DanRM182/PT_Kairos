package com.ramirezmontoya.tvmaze_middleware.client;

import com.ramirezmontoya.tvmaze_middleware.dto.TvMazeSearchResult;
import com.ramirezmontoya.tvmaze_middleware.dto.TvmazeShow;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

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
}
