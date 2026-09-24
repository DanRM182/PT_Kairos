package com.ramirezmontoya.tvmaze_middleware.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@AllArgsConstructor
@Getter
@Document(collection = "shows")
public class ShowDocument {
    @Id
    private Long id;
    private Map<String, Object> data;
}
