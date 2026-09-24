package com.ramirezmontoya.tvmaze_middleware.repository;

import com.ramirezmontoya.tvmaze_middleware.document.CommentRatingDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRatingRepository extends MongoRepository<CommentRatingDocument, String> {
}
