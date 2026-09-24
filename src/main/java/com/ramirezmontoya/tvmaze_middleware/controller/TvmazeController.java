package com.ramirezmontoya.tvmaze_middleware.controller;

import com.ramirezmontoya.tvmaze_middleware.dto.ResumenResponse;
import com.ramirezmontoya.tvmaze_middleware.service.ShowService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@AllArgsConstructor
@Validated
public class TvmazeController {
    private final ShowService servicio;

    @GetMapping("/search")
    public List<ResumenResponse> buscar(
            @RequestParam(name = "search_query") @NotBlank String searchQuery
    ) {
        return servicio.buscar(searchQuery);
    }
}
