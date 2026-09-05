package com.project.recommendation_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.recommendation_service.entity.UserGenrePreference;

public interface GenrePreferenceRepository extends JpaRepository<UserGenrePreference, Long>{

	public List<UserGenrePreference> findByUserId(Long userId);
	public List<UserGenrePreference> findByUserIdOrderByAverageRatingDesc(Long userId);
	Optional<UserGenrePreference> findByUserIdAndGenreId(
	        Long userId, Long genreId);
}
