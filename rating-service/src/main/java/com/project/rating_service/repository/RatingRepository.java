package com.project.rating_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rating_service.entity.RatingEntity;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
	Optional<RatingEntity> findByUserIdAndBookId(Long userId, Long bookId);
}
