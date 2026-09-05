package com.project.recommendation_service.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.recommendation_service.entity.BookRecommendation;
import com.project.recommendation_service.entity.BookResponseDto;
import com.project.recommendation_service.entity.UserBookHistory;
import com.project.recommendation_service.entity.UserGenrePreference;
import com.project.recommendation_service.event.BookFinishedEvent;
import com.project.recommendation_service.event.ReviewSubmittedEvent;
import com.project.recommendation_service.feignclient.BookServiceClient;
import com.project.recommendation_service.repository.BookHistoryRepository;
import com.project.recommendation_service.repository.GenrePreferenceRepository;

@Service
public class RecommendationService {
	
	@Autowired
	private BookHistoryRepository bookHistoryRepository;
	
	@Autowired
	private GenrePreferenceRepository genrePreferenceRepository;
	
	@Autowired
	private BookServiceClient bookServiceClient;

	public void saveUserBookHistory(BookFinishedEvent event) {
		
		Long userId = event.getUserId();
		Long bookId = event.getBookId();
		
		UserBookHistory history = bookHistoryRepository.findByUserIdAndBookId(userId, bookId)
				.orElse(new UserBookHistory());
		
		
		history.setUserId(userId);
		history.setBookId(bookId);
		history.setFinished(true);
		history.setUpdatedAt(LocalDateTime.now());
		
		bookHistoryRepository.save(history);
	}
	
	
	public void saveUserGenrePreference(ReviewSubmittedEvent event) {
		
		Long userId = event.getUserId();
		Long bookId = event.getBookId();
		Integer rating = event.getRating();
		
		UserBookHistory history = bookHistoryRepository.findByUserIdAndBookId(userId, bookId)
				.orElse(new UserBookHistory());
		
		history.setUserId(userId);
		history.setBookId(bookId);
		history.setRating(rating);
		history.setUpdatedAt(LocalDateTime.now());
		
		bookHistoryRepository.save(history);
		
		BookResponseDto bookResponse = fetchBookForGenreId(bookId);

		Long genreId = bookResponse.getGenreId();
		
		UserGenrePreference preference = genrePreferenceRepository.findByUserIdAndGenreId(userId, genreId)
		.orElse(new UserGenrePreference());
		
		preference.setUserId(userId);
		preference.setGenreId(genreId);
		setAverageRatingAndRatingCount(rating, preference);		
		genrePreferenceRepository.save(preference);
	}


	private void setAverageRatingAndRatingCount(Integer rating, UserGenrePreference preference) {
		if(preference.getRatingCount() == 0) {
			
			preference.setAverageRating(rating.doubleValue());
			preference.setRatingCount(1);
		}else {
			double newAverage = ((preference.getAverageRating() * preference.getRatingCount()) + rating) / (preference.getRatingCount() + 1);
			preference.setAverageRating(newAverage);
			preference.setRatingCount(preference.getRatingCount() + 1);
		}
	}
	
	
	private BookResponseDto fetchBookForGenreId(Long bookId) {
		
		return bookServiceClient.getBook(bookId);
	}
	
	
	public List<BookRecommendation> getRecommendedBooks(Long userId) {
		
		List<UserBookHistory> history = bookHistoryRepository.findByUserId(userId);
		List<UserGenrePreference> genrePrefs = genrePreferenceRepository.findByUserIdOrderByAverageRatingDesc(userId);
		
	
		List<Long> preferredGenreIds = genrePrefs.stream()
		.map(UserGenrePreference::getGenreId)
		.toList();
		
		
		List<BookResponseDto> allCandidates = new ArrayList<>();
		
		for(Long genreId : preferredGenreIds) {
			allCandidates.addAll(bookServiceClient.getBooksByGenre(genreId));
		}
		
		
		Set<Long> interactedBookIds = history.stream()
		.map(UserBookHistory::getBookId)
		.collect(Collectors.toSet());
		
		List<BookResponseDto> candidates = allCandidates.stream()
		.filter(book -> !interactedBookIds.contains(book.getBookId()))
		.distinct()
		.toList();
		
		Map<Long, Double> genreScoreMap = genrePrefs.stream()
		.collect(Collectors.toMap(
				UserGenrePreference::getGenreId, 
				UserGenrePreference::getAverageRating
				));
		
		List<BookResponseDto> ranked = candidates.stream()
		.sorted((a,b) -> {
			double scoreA = genreScoreMap.getOrDefault(a.getGenreId(), 0.0);
			double scoreB = genreScoreMap.getOrDefault(b.getGenreId(), 0.0);
			return Double.compare(scoreB, scoreA);
		}).toList();
		System.out.println("RANK SIZE: " + ranked.size());
		List<BookRecommendation> recommendations = new ArrayList<>();
		
		for(BookResponseDto book : ranked) {
			BookRecommendation recommendedBook = new BookRecommendation();
			recommendedBook.setBookId(book.getBookId());
			recommendedBook.setTitle(book.getTitle());
			recommendedBook.setAuthor(book.getAuthor());
//			recommendedBook.setGenre(book.getGenreId());
			recommendations.add(recommendedBook);
		}
		
		return recommendations;
	}
}
