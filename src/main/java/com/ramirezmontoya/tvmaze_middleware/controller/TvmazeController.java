package com.ramirezmontoya.tvmaze_middleware.controller;

import com.ramirezmontoya.tvmaze_middleware.dto.ResumenResponse;
import com.ramirezmontoya.tvmaze_middleware.service.ShowService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{idShow}")
    public Map<String, Object> obtenerShowPorId(
            @PathVariable @Positive Long idShow) {
        return servicio.obtenerShowPorId(idShow);
    }
}
