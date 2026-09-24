package com.ramirezmontoya.tvmaze_middleware.repository;

import com.ramirezmontoya.tvmaze_middleware.document.CommentRatingDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface CommentRatingRepository extends MongoRepository<CommentRatingDocument, String> {
    List<CommentRatingDocument> findByShowIdIn(Collection<Long> showIds);

    List<CommentRatingDocument> findByShowId(Long showId);
}
