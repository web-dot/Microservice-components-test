package com.project.recommendation_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.recommendation_service.entity.UserBookHistory;

@Repository
public interface BookHistoryRepository extends JpaRepository<UserBookHistory, Long> {

	Optional<UserBookHistory> findByUserIdAndBookId(Long userId, Long bookId);
	List<UserBookHistory> findByUserId(Long userId);
}
