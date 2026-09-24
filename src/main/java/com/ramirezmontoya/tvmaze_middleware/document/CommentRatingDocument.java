package com.ramirezmontoya.tvmaze_middleware.document;

import com.ramirezmontoya.tvmaze_middleware.utils.StringCustomUtils;
import com.ramirezmontoya.tvmaze_middleware.utils.ValoresNumericosUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "showsCommentsRating")
public class CommentRatingDocument {
    @Id
    private String id;

    private Long showId;
    private String comment;
    private Integer rating;

    private void validarDatos(Long showId, String comment, Integer rating){
        ValoresNumericosUtils.validarLongPositivo(showId, "El showId es necesario y debe ser positivo");

        StringCustomUtils.validarNoVacio(comment, "El comentario no debe ser vacío");

        ValoresNumericosUtils.validarRangoInteger(rating, 0, 5, "El rango del rating es de 0 a 5");
    }

    public CommentRatingDocument(Long showId, String comment, Integer rating) {
        validarDatos(showId, comment, rating);

        this.showId = showId;
        this.comment = comment;
        this.rating = rating;
    }
}
