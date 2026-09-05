package com.project.reading_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.reading_service.entity.ReadingStatus;

@Repository
public interface ReadingStatusRepository extends JpaRepository<ReadingStatus, Long> {
	Optional<ReadingStatus> findByUserIdAndBookId(Long userId, Long bookId);
	List<ReadingStatus> findByUserId(Long userId);
}
